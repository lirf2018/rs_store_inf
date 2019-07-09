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
 * 创建时间:  2017-12-19 11:25
 * 功能介绍: 查询用户消息详情
 */
@Service("qurey_user_news_info")
public class QueryUserNewsInfo implements ResultOut {

    private Logger LOG = Logger.getLogger(QueryUserNewsInfo.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer userId = bean.getInteger("user_id");
            Integer newsId = bean.getInteger("news_id");
            if (null == userId || null == newsId) {
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), new JSONObject());
            }
            List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();
            newsList = findInfoDao.loadTBNewsInfoListMap(userId, newsId);
            if (newsList.size() > 0) {
                dataJson.put("user_news_info", newsList.get(0));
            } else {
                dataJson.put("user_news_info", new JSONObject());
            }
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}

