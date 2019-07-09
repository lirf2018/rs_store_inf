package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbAttention;
import com.yufan.pojo.TbUserInfo;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.task.dao.SaveInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-27 16:41
 * 功能介绍: 用户登录(user_login)
 */
@Service("user_login")
public class UserLogin implements ResultOut {

    private Logger LOG = Logger.getLogger(UserLogin.class);

    @Autowired
    private SaveInfoService saveInfoService;

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            String loginName = bean.getString("login_name");
            String loginPasswd = bean.getString("login_passwd");
            if (StringUtils.nonEmptyString(loginName) && StringUtils.nonEmptyString(loginPasswd)) {
                TbUserInfo userInfo = findInfoDao.laodTbUserInfo(loginName);
                if (null != userInfo) {
                    LOG.info("--------用户存在");
                    String userPasswd = userInfo.getLoginPass();
                    if (!userPasswd.equals(loginPasswd.trim())) {
                        LOG.info("--------密码不正确");
                        return packagMsg(ResultCode.PASSWD_ERROR.getResp_code(), new JSONObject());
                    }
                    //用户状态 0待验证 1正常 2锁定 3已注销 (正常账号须绑定手机和微信)
                    int status = userInfo.getUserState();
                    if (status == 0) {
                        LOG.info("-------->账号待验证");
                        return packagMsg(ResultCode.FAIL_USER_NEED_VERIFY.getResp_code(), new JSONObject());
                    } else if (status == 2) {
                        LOG.info("-------->账号已锁定");
                        return packagMsg(ResultCode.FAIL_USER_LOCK.getResp_code(), new JSONObject());
                    } else if (status == 3) {
                        LOG.info("-------->账号已注销");
                        return packagMsg(ResultCode.FAIL_USER_INVALIDATE.getResp_code(), new JSONObject());
                    }

                    //dataJson
                    dataJson.put("user_id", userInfo.getUserId());
                    dataJson.put("login_name", userInfo.getLoginName());
                    dataJson.put("user_mobile", userInfo.getUserMobile());
                    dataJson.put("nick_name", userInfo.getNickName());

                    //是否账号登录
                    String userLoginName = userInfo.getLoginName();
                    if (userLoginName.equals(loginName.trim())) {
                        LOG.info("--登录方式为账号登录------");
                        saveInfoService.updateUserLoginTime(userInfo.getUserId());
                        return packagMsg(ResultCode.OK.getResp_code(), dataJson);
                    }

                    //是否手机作为账号登录
                    String userPhone = userInfo.getUserMobile();
                    int mobileValite = userInfo.getMobileValite();//0 未验证 1 已验证
                    if (1 == mobileValite && userPhone.equals(loginName.trim())) {
                        saveInfoService.updateUserLoginTime(userInfo.getUserId());
                        return packagMsg(ResultCode.OK.getResp_code(), dataJson);
                    }
                    LOG.info("--------手机号码验证失败--未验证");
                    return packagMsg(ResultCode.LOGIN_PHONE_NEED_VERIFY.getResp_code(), new JSONObject());
                }
                LOG.info("------------>用户不存在");
            }
            return packagMsg(ResultCode.FAIL_USER_INVALIDATE.getResp_code(), new JSONObject());
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }

    /**
     * 更新用户购物车和更新用户最后登录时间
     * userMarkTemp 用户客户端临时标识,用于更新购物车
     */
    public void updateUserOrderCard(String userMarkTemp, int userId) {
        saveInfoService.updateOrderCardTemp(userMarkTemp, userId);
        saveInfoService.updateUserLoginTime(userId);
    }

}
