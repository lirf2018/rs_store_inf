package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbUserInfo;
import com.yufan.pojo.TbUserSns;
import com.yufan.pojo.TbVerification;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.DatetimeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2018-02-23 14:14
 * 功能介绍: 注册随即码校验
 */
@Deprecated
//@Service("check_register_code")
public class CheckRegister implements ResultOut {

    private Logger LOG = Logger.getLogger(CheckRegister.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer validType = bean.getInteger("valid_type");//验证码类型:1手机绑定
            String userMobile = bean.getString("phone");//验证标识参数 如：手机号13418915182,邮箱
            String validCode = bean.getString("phone_code");
            String openId = bean.getString("open_id");

            if (StringUtils.nonEmptyString(userMobile) && StringUtils.nonEmptyString(validCode) && null != validType && StringUtils.nonEmptyString(openId)) {

                //查询手机是否已经被注册绑定
                TbUserInfo userInfo_ = findInfoDao.loadTbUserInfoByPhone(userMobile);
                if (null != userInfo_) {
                    LOG.info("------->手机是已经被注册绑定");
                    return packagMsg(ResultCode.PHONE_HAS_USED.getResp_code(), dataJson);
                }
                //判断uid是否已经绑定
                TbUserSns userSns_ = findInfoDao.loadTbUserSnsInfoByUID(openId, 4);
                if (null != userSns_) {
                    LOG.info("-------->uid已经被绑定uid=" + openId);
                    return packagMsg(ResultCode.ERROR_USER_ISBANGWEIXIN.getResp_code(), dataJson);
                }


                TbVerification verification = findInfoDao.loadTbVerificationInfo(validType, userMobile, validCode);
                if (null != verification) {
                    String passTime = DatetimeUtil.convertDateToStr(verification.getPassTime(), "yyyy-MM-dd HH:mm:ss");
                    String nowTime = DatetimeUtil.getNow();
                    LOG.info("-------->passTime=" + passTime + "   nowTime=" + nowTime + "  " + DatetimeUtil.compareDate(nowTime, passTime));
                    if (DatetimeUtil.compareDateTime(nowTime, passTime) != 1) {
                        return packagMsg(ResultCode.OK.getResp_code(), dataJson);
                    }
                }
                return packagMsg(ResultCode.CODE_NONEFFECTIVE.getResp_code(), dataJson);
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
