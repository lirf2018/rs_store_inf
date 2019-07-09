package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbUserInfo;
import com.yufan.pojo.TbUserSns;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.dao.FindInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-12-25 15:46
 * 功能介绍:  微信登录(weixin_login)
 * 流程-微信登录-->1获取微信openID--->登录(或者注册)
 */
@Service("weixin_login")
public class WeixinLogin implements ResultOut {
    private Logger LOG = Logger.getLogger(WeixinLogin.class);

    @Autowired
    private SaveInfoService saveInfoService;

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            String uId = bean.getString("uid");
            Integer snsType = bean.getInteger("sns_type");
            if (!StringUtils.nonEmptyString(uId) || snsType == null) {
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), new JSONObject());
            }
            //查询绑定信息
            TbUserSns userSns = findInfoDao.loadTbUserSnsInfoByUID(uId, snsType);
            if (userSns != null) {
                LOG.info("--------微信用户存在");
                //查询用户信息
                TbUserInfo userInfo = findInfoDao.laodTbUserInfoBuyUserId(userSns.getUserId());
                //dataJson
                dataJson.put("user_id", userInfo.getUserId());
                dataJson.put("login_name", userInfo.getLoginName());
                dataJson.put("user_mobile", userInfo.getUserMobile());
                dataJson.put("nick_name", userInfo.getNickName());
                userInfo.setLastlogintime(new Date());
                saveInfoService.saveUpdateEntity(userInfo);
                return packagMsg(ResultCode.OK.getResp_code(), dataJson);
            } else {
                LOG.info("------>注册和绑定");
                TbUserInfo userInfo = saveInfoService.registerWeixin(bean);
                if (null == userInfo) {
                    LOG.info("------>注册和绑定失败");
                    return packagMsg(ResultCode.FAIL_REGISTER.getResp_code(), new JSONObject());
                }
                dataJson.put("user_id", userInfo.getUserId());
                dataJson.put("login_name", userInfo.getLoginName());
                dataJson.put("user_mobile", userInfo.getUserMobile());
                dataJson.put("nick_name", userInfo.getNickName());
                return packagMsg(ResultCode.OK.getResp_code(), dataJson);
            }
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
