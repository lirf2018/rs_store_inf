package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbVerification;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.DatetimeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-24 18:23
 * 功能介绍: 校验验证码(并更新随机码为无效)
 */
@Deprecated
//@Service("verification_code")
public class CheckVerificationCode implements ResultOut {

    private Logger LOG = Logger.getLogger(CheckVerificationCode.class);

    @Autowired
    private SaveInfoService saveInfoService;

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer validType = bean.getInteger("valid_type");//验证码类型:1手机绑定
            String userMobile = bean.getString("valid_param");//验证标识参数 如：手机号13418915182,邮箱
            String validCode = bean.getString("valid_code");
            if (StringUtils.nonEmptyString(userMobile) && StringUtils.nonEmptyString(validCode) && null != validType) {
                TbVerification verification = findInfoDao.loadTbVerificationInfo(validType, userMobile, validCode);
                if (null != verification) {
                    String passTime = DatetimeUtil.convertDateToStr(verification.getPassTime(), "yyyy-MM-dd HH:mm:ss");
                    String nowTime = DatetimeUtil.getNow();
                    LOG.info("-------->passTime=" + passTime + "   nowTime=" + nowTime + "  " + DatetimeUtil.compareDate(nowTime, passTime));
                    if (DatetimeUtil.compareDateTime(nowTime, passTime) != 1) {
                        //更新验证码
                        saveInfoService.updateTbVerificationStatus(2, userMobile, 1);
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
