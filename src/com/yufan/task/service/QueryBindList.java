package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbUserInfo;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2018-02-23 17:48
 * 功能介绍: 查询用户绑定信息列表
 */
@Service("user_bind_list")
public class QueryBindList implements ResultOut {

    private Logger LOG = Logger.getLogger(QueryBindList.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {

            //查询是否绑定微信
            Integer userId = bean.getInteger("user_id");
            if (userId == null || 0 == userId.intValue()) {
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            //查询是否绑定微信
            List<Map<String, Object>> listTbUserSnsListMap = findInfoDao.loadTbUserSnsListMap(userId);
            dataJson.put("list_bind", listTbUserSnsListMap);
            //查询绑定的手机
            TbUserInfo userInfo = findInfoDao.loadTbUserInfoById(userId);
            dataJson.put("phone", userInfo.getUserMobile());

            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}