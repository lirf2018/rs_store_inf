package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-22 9:51
 * 功能介绍: 搜索页面
 */
@Service("qurey_search_history")
public class QuerySearchHistoryList implements ResultOut {

    private Logger LOG = Logger.getLogger(QuerySearchHistoryList.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {

            //查询热门商品搜索关键8个
            List<Map<String, Object>> listHotGoodsSearch = findInfoDao.queryHotGoodsWordSearchListMap();
            //查询用户历史商品搜索关键8个
            List<Map<String, Object>> listUserGoodsSearch = new ArrayList<Map<String, Object>>();
            Integer userId = bean.getInteger("user_id");
            if (null != userId && userId.intValue() != 0) {
                listUserGoodsSearch = findInfoDao.queryUserGoodsWordSearchListMap(userId);
            }
            dataJson.put("hot_search_history", listHotGoodsSearch);
            dataJson.put("user_search_history", listUserGoodsSearch);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
