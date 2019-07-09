package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbUserInfo;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.task.dao.SaveInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2018-02-23 18:16
 * 功能介绍: 解绑
 */
@Service("delete_user_bind")
public class DeleteUserBind implements ResultOut {

    private Logger LOG = Logger.getLogger(DeleteUserBind.class);

    @Autowired
    private SaveInfoDao saveInfoDao;

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {

            Integer userId = bean.getInteger("user_id");
            Integer snsType = bean.getInteger("sns_type");//1、腾讯微博；2、新浪微博；3、人人网；4、微信；5、服务窗；6、一起沃；7、QQ;'
            if (userId == null || 0 == userId.intValue() || snsType == null || 0 == snsType.intValue()) {
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }

            //查询手机是否已经被绑定
            TbUserInfo userInfo = findInfoDao.loadTbUserInfoById(userId);
            if (userInfo == null || userInfo.getUserState() == 3 || !StringUtils.nonEmptyString(userInfo.getUserMobile())) {
                return packagMsg(ResultCode.NOT_BOUND_PHONE.getResp_code(), dataJson);
            }

            saveInfoDao.updateUserBind(userId, snsType);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}