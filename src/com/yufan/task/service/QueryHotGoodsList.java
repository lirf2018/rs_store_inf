package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.common.bean.PageInfo;
import com.yufan.pojo.TbSearchHistory;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.dao.FindInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-21 14:09
 * 功能介绍: 查询热销商品
 */
@Service("qurey_hotgoods_list")
public class QueryHotGoodsList implements ResultOut {

    private Logger LOG = Logger.getLogger(QueryHotGoodsList.class);

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


            //查询最热商品
            PageInfo page = findInfoDao.loadHotGoodsPage(current, goodsName, categoryIds, leve1Ids);
            List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < page.getResultListMap().size(); i++) {
                Map<String, Object> map = page.getResultListMap().get(i);
                map.put("time_goods_id", 0);
                int isSingle = Integer.parseInt(String.valueOf(map.get("is_single")));
                if (0 == isSingle) {
                    //sku商品
                    String skuMoney = String.valueOf(map.get("sku_now_money"));
                    String price[] = skuMoney.split(",");
                    map.put("low_money", price[0]);
                    map.put("high_money", price[price.length - 1]);
                    map.put("sku_now_money", price[0] + "-" + price[price.length - 1]);
                } else {
                    map.put("low_money", map.get("now_money"));
                }
                outList.add(map);
            }

            dataJson.put("hot_goods_list", outList);
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
