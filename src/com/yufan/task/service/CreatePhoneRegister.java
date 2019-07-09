package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbUserInfo;
import com.yufan.pojo.TbUserSns;
import com.yufan.pojo.TbVerification;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.DatetimeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2018-02-08 16:01
 * 功能介绍: 手机注册
 */
@Service("create_phone_register")
public class CreatePhoneRegister implements ResultOut {

    private Logger LOG = Logger.getLogger(CreateLoginRegister.class);

    @Autowired
    private SaveInfoService saveInfoService;

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public synchronized String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            String mobile = bean.getString("mobile");
            String passwd = bean.getString("passwd");
            String validCode = bean.getString("valid_code");//验证码

            if (!StringUtils.nonEmptyString(mobile) || !StringUtils.nonEmptyString(passwd) || !StringUtils.nonEmptyString(validCode)) {
                LOG.info("------>缺少参数");
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            mobile = mobile.trim();
            //查询手机是否已经被注册绑定
            TbUserInfo userInfo_ = findInfoDao.loadTbUserInfoByPhone(mobile);
            if (null != userInfo_) {
                LOG.info("------->手机是已经被注册绑定");
                return packagMsg(ResultCode.PHONE_HAS_USED.getResp_code(), dataJson);
            }

            //验证码类型:1手机绑定2修改密码3重置密码4手机解绑5手机注册
            TbVerification verification = findInfoDao.loadTbVerificationInfo(5, mobile, validCode);
            if (null != verification) {
                String now = DatetimeUtil.getNow();
                String passTime = DatetimeUtil.convertDateToStr(verification.getPassTime(), "yyyy-MM-dd HH:mm:ss");
                if (DatetimeUtil.compareDateTime(now, passTime) == 1) {
                    return packagMsg(ResultCode.CODE_NONEFFECTIVE.getResp_code(), dataJson);
                }
            } else {
                LOG.info("---------->查询检验不存在");
                return packagMsg(ResultCode.CODE_NONEFFECTIVE.getResp_code(), dataJson);
            }

            TbUserInfo userInfo = new TbUserInfo();
            userInfo.setCreatetime(new Date());
            userInfo.setMobileValite(1);
            userInfo.setUserMobile(mobile.trim());
            userInfo.setEmailValite(0);
            userInfo.setUserState(1);//
            userInfo.setLoginName("rs" + System.currentTimeMillis());
            userInfo.setLoginPass(passwd.trim());
            userInfo.setNickName("用户" + mobile);
            userInfo.setLastlogintime(new Date());
            userInfo.setLogCount(1);
            int userId = saveInfoService.saveNewUser(userInfo);
            if (userId > 0) {
                LOG.info("-------->新增用户注册=" + userId);
                dataJson.put("id", userId);
                //更新验证码
                saveInfoService.updateTbVerificationStatus(2, mobile, 5);
                //同时生成一个绑定记录
                TbUserSns userSns = new TbUserSns();
                userSns.setUserId(userInfo.getUserId());
                userSns.setSnsType(0);//4微信
                userSns.setUid(mobile);
                userSns.setOpenkey("");
                userSns.setSnsName("");
                userSns.setSnsAccount("");
                userSns.setSnsImg("");
                userSns.setCreatetime(new Date());
                userSns.setIsUseImg(0);
                userSns.setStatus(1);
                int snsId = saveInfoService.saveEntity(userSns);
                LOG.info("-------->新增保存手机绑定=" + snsId);

                return packagMsg(ResultCode.OK.getResp_code(), dataJson);
            }
        } catch (Exception e) {
            LOG.info("异常", e);
        } catch (Throwable e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }

}