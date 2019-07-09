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
 * 创建时间:  2017-12-14 10:47
 * 功能介绍: 绑定手机
 */
@Service("bound_user_mobile")
public class BoundUserMobile implements ResultOut {

    private Logger LOG = Logger.getLogger(BoundUserMobile.class);

    @Autowired
    private SaveInfoService saveInfoService;

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer userId = bean.getInteger("user_id");
            String userMobile = bean.getString("user_mobile");
            String validCode = bean.getString("valid_code");//验证码

            if (!StringUtils.nonEmptyString(userMobile) || !StringUtils.nonEmptyString(validCode) || null == userId) {
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }

            //查询手机是否已经被绑定
            TbUserInfo userInfo = findInfoDao.loadTbUserInfoByPhone(userMobile.trim());
            if (null != userInfo) {
                LOG.info("------->手机是已经被绑定");
                return packagMsg(ResultCode.FAIL_BOUND_PHONE.getResp_code(), dataJson);
            }

            //验证码类型:1手机绑定2修改密码3重置密码4手机解绑5手机注册
            TbVerification verification = findInfoDao.loadTbVerificationInfo(1, userMobile, validCode);
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
            TbUserInfo userInfo_ = findInfoDao.loadTbUserInfoById(userId);
            if (null == userInfo_) {
                LOG.info("---------->查询用户不存在");
                return packagMsg(ResultCode.FAIL_BOUND_PHONE.getResp_code(), dataJson);
            }
            userInfo_.setUserMobile(userMobile);
            userInfo_.setMobileValite(1);
            userInfo_.setLastaltertime(new Date());

            boolean flag = saveInfoService.boundUserPhone(userInfo_);
            if (!flag) {
                return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
            }
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
    }


}
