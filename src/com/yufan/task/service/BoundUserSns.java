package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbUserSns;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.bean.UserSnsBean;
import com.yufan.task.dao.FindInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-24 18:23
 * 功能介绍: 保存绑定
 */
@Service("create_user_sns")
public class BoundUserSns implements ResultOut {

    private Logger LOG = Logger.getLogger(BoundUserSns.class);

    @Autowired
    private SaveInfoService saveInfoService;

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            UserSnsBean userSnsBean = JSONObject.toJavaObject(bean, UserSnsBean.class);
            if (null == userSnsBean || null == userSnsBean.getUserId() || !StringUtils.nonEmptyString(userSnsBean.getUid()) ||
                    null == userSnsBean.getSnsType()) {
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            //判断uid是否已经绑定  1、腾讯微博；2、新浪微博；3、人人网；4、微信；5、服务窗；6、一起沃；7、QQ;
            TbUserSns userSns_ = findInfoDao.loadTbUserSnsInfoByUID(userSnsBean.getUid(), userSnsBean.getSnsType());
            if (null != userSns_) {
                LOG.info("-------->uid已经被绑定uid=" + userSnsBean.getUid());
                return packagMsg(ResultCode.ERROR_USER_ISBANGWEIXIN.getResp_code(), dataJson);
            }
            TbUserSns userSns = new TbUserSns();
            userSns.setUserId(userSnsBean.getUserId());
            userSns.setSnsType(userSnsBean.getSnsType());
            userSns.setUid(userSnsBean.getUid());
            userSns.setOpenkey(userSnsBean.getOpenkey());
            userSns.setSnsName(userSnsBean.getSnsName());
            userSns.setSnsAccount(userSnsBean.getSnsAccount());
            userSns.setSnsImg(userSnsBean.getSnsImg());

            userSns.setCreatetime(new Date());
            userSns.setIsUseImg(0);
            userSns.setStatus(1);
            int id = saveInfoService.saveEntity(userSns);
            LOG.info("-------->新增保存绑定=" + id);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }

}
