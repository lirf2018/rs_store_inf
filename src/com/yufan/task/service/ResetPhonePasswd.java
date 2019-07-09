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

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2018-02-23 16:01
 * 功能介绍: 重置用户密码(reset_user_passwd)
 */
@Service("reset_phone_passwd")
public class ResetPhonePasswd implements ResultOut {

    private Logger LOG = Logger.getLogger(ResetPhonePasswd.class);

    @Autowired
    private SaveInfoService saveInfoService;

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public synchronized String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            String mobile = bean.getString("phone");
            String passwd = bean.getString("passwd");
            String validCode = bean.getString("valid_code");//验证码

            if (!StringUtils.nonEmptyString(mobile) || !StringUtils.nonEmptyString(passwd) || !StringUtils.nonEmptyString(validCode)) {
                LOG.info("------>缺少参数");
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            mobile = mobile.trim();
            //查询状态是否正常
            TbUserInfo userInfo_ = findInfoDao.loadTbUserInfoByPhone(mobile);
            if (null == userInfo_ || 1 != userInfo_.getUserState()) {
                LOG.info("------->用户无效");
                return packagMsg(ResultCode.FAIL_USER_INVALIDATE.getResp_code(), dataJson);
            }
            //验证码类型:1手机绑定2修改密码3重置密码4手机解绑5手机注册
            TbVerification verification = findInfoDao.loadTbVerificationInfo(3, mobile, validCode);
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
            //重置用户密码
            saveInfoService.updateUserPasswd(mobile, passwd);
            saveInfoService.updateTbVerificationStatus(2, mobile, 3);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        } catch (Throwable e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }

}