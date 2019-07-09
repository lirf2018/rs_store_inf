package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.common.bean.PageInfo;
import com.yufan.pojo.TbParam;
import com.yufan.pojo.TbSearchHistory;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.CacheData;
import com.yufan.util.DatetimeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-12-29 18:00
 * 功能介绍: 查询抢购商品
 */
@Service("qurey_timegoods_list")
public class QueryTimeGoodsList implements ResultOut {

    private Logger LOG = Logger.getLogger(QueryTimeGoodsList.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Autowired
    private SaveInfoService saveInfoService;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer userId = bean.getInteger("user_id") == null ? 0 : bean.getInteger("user_id");
            Integer current = bean.getInteger("current");
            if (null == current) {
                current = 1;
            }

            String goodsName = bean.getString("goods_name");
            String categoryIds = bean.getString("category_ids");//类目标识
            String leve1Ids = bean.getString("leve1_ids");//一级分类标识


            //查看抢购结束时间
            String timeGoodsOutStr = CacheData.paramMap.get("time_goods_out-time_goods_out");
            if (StringUtils.nonEmptyString(timeGoodsOutStr)) {
                //判断抢购时间是否过期
                String passTime = timeGoodsOutStr;
                String nowTime = DatetimeUtil.getNow();
                if (null != passTime && !"".equals(passTime) && DatetimeUtil.compareDateTime(nowTime, passTime) == 1) {
                    dataJson.put("time_goods_list", new ArrayList<Map<String, Objects>>());
                    dataJson.put("has_next", false);
                    return packagMsg(ResultCode.OK.getResp_code(), dataJson);
                }
            }

            //查询抢购商品
            PageInfo page = findInfoDao.loadTimeGoodsPage(current, goodsName, categoryIds, leve1Ids);

            dataJson.put("time_goods_list", page.getResultListMap());
            dataJson.put("has_next", page.isHasNext());

            //保存搜索名称
            if (StringUtils.nonEmptyString(goodsName)) {
                TbSearchHistory searchHistory = new TbSearchHistory();
                searchHistory.setUserId(userId);
                searchHistory.setType(1);
                searchHistory.setTypeWord(goodsName.trim());
                searchHistory.setStatus(1);
                searchHistory.setCreatetime(new Date());
                int id = saveInfoService.saveEntity(searchHistory);
            }

            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }

}
