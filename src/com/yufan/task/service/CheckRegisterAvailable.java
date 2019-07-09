package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbUserInfo;
import com.yufan.pojo.TbUserSns;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2018-02-24 10:15
 * 功能介绍:  注册发送短信前校验手机号和微信可用性
 */
@Deprecated
//@Service("check_register_account")
public class CheckRegisterAvailable implements ResultOut {

    private Logger LOG = Logger.getLogger(CheckRegisterAvailable.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            String userMobile = bean.getString("phone");//手机号码吗
            String openId = bean.getString("open_id");

            if (StringUtils.nonEmptyString(userMobile) && StringUtils.nonEmptyString(openId)) {

                //查询手机是否已经被注册绑定
                TbUserInfo userInfo_ = findInfoDao.loadTbUserInfoByPhone(userMobile);
                if (null != userInfo_) {
                    LOG.info("------->手机是已经被注册绑定");
                    return packagMsg(ResultCode.PHONE_HAS_USED.getResp_code(), dataJson);
                }
                //判断uid是否已经绑定(微信)
                TbUserSns userSns_ = findInfoDao.loadTbUserSnsInfoByUID(openId, 4);
                if (null != userSns_) {
                    LOG.info("-------->uid已经被绑定uid=" + openId);
                    return packagMsg(ResultCode.ERROR_USER_ISBANGWEIXIN.getResp_code(), dataJson);
                }
                return packagMsg(ResultCode.OK.getResp_code(), dataJson);
            } else {
                LOG.info("--------缺少参数");
                return packagMsg(ResultCode.PARAM_ERROR.getResp_code(), dataJson);
            }
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }

}
