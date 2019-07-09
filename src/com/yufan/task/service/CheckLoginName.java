package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbUserInfo;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 验证登录名称是否存在
 */
@Deprecated
//@Service("check_login_name")
public class CheckLoginName implements ResultOut {

    private Logger LOG = Logger.getLogger(CheckLoginName.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            String loginName = bean.getString("login_name");

            if (!StringUtils.nonEmptyString(loginName)) {
                LOG.info("------>缺少参数");
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }

            TbUserInfo userInfo = findInfoDao.loadTbUserInfoByLoginName(loginName);
            if (null != userInfo) {
                return packagMsg(ResultCode.FAIL_USER_EXIST.getResp_code(), dataJson);
            }
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("----------->异常");
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
