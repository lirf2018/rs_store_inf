package com.yufan.task.service;

/**
 * 创建人: lirf
 * 创建时间:  2018/8/13 14:20
 * 功能介绍:
 */

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

@Deprecated
//@Service("check_bound_mobile")
public class checkBoundMobile implements ResultOut {

    private Logger LOG = Logger.getLogger(checkBoundMobile.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer userId = bean.getInteger("user_id");
            String userMobile = bean.getString("user_mobile");
            if (!StringUtils.nonEmptyString(userMobile) || null == userId) {
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            //前提（绑定openId）--->绑定手机号码
            //查询账号是否已绑定微信
            TbUserSns userSns = findInfoDao.loadTbUserSnsInfoByUserId(userId, 4);
            if (null == userSns) {
                LOG.info("------->用户账号未绑定微信");
                return packagMsg(ResultCode.FAIL_NOTBANG_WEIXIN.getResp_code(), dataJson);
            }

            //查询手机是否已经被绑定
            TbUserInfo userInfo = findInfoDao.loadTbUserInfoByPhone(userMobile.trim());
            if (null != userInfo) {
                LOG.info("------->手机是已经被绑定");
                return packagMsg(ResultCode.FAIL_BOUND_PHONE.getResp_code(), dataJson);
            }

            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
    }


}
