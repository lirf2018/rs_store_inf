package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbComplain;
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
 * 创建时间:  2017-12-14 16:16
 * 功能介绍: 查询用户投诉或者建议详情
 */
@Service("qurey_user_complain_info")
public class QueryUserComplainInfo implements ResultOut {
    private Logger LOG = Logger.getLogger(QueryUserComplainList.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer userId = bean.getInteger("user_id");
            Integer complainId = bean.getInteger("complain_id");

            if (null == userId || null == complainId) {
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }

//            TbComplain complain = findInfoDao.loadTbComplainById(userId,complainId);
//            dataJson.put("complain_info", complain);
            List<Map<String, Object>> list = findInfoDao.loadUserComplainListMap(userId, complainId);
            dataJson.put("complain_info", list.get(0));
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}

