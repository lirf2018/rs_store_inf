package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbUserInfo;
import com.yufan.pojo.TbVerification;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.task.dao.SaveInfoDao;
import com.yufan.util.DatetimeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2018/11/16 17:43
 * 功能介绍: 删除手机绑定
 */
@Service("delete_user_phone")
public class DeleteUserPhone implements ResultOut {

    private Logger LOG = Logger.getLogger(DeleteUserPhone.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Autowired
    private SaveInfoDao saveInfoDao;

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

            //验证码类型:1手机绑定2修改密码3重置密码4手机解绑5手机注册
            TbVerification verification = findInfoDao.loadTbVerificationInfo(4, userMobile, validCode);
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

            //查询手机是否已经被绑定
            TbUserInfo userInfo = findInfoDao.loadTbUserInfoByPhone(userMobile.trim());
            if (userInfo == null) {
                return packagMsg(ResultCode.USER_PHONE_NOTUSE.getResp_code(), dataJson);
            }
            //如果该用户没有绑定微信,则不允许解绑
            //查询是否绑定微信
            boolean isBWeixin = false;
            int snsTypePhone = 0;
            List<Map<String, Object>> listTbUserSnsListMap = findInfoDao.loadTbUserSnsListMap(userId);
            for (int i = 0; i < listTbUserSnsListMap.size(); i++) {
                int snsId = Integer.parseInt(listTbUserSnsListMap.get(i).get("sns_id").toString());
                //1、腾讯微博；2、新浪微博；3、人人网；4、微信；5、服务窗；6、一起沃；7、QQ;
                int snsType = Integer.parseInt(listTbUserSnsListMap.get(i).get("sns_type").toString());
                String uid = listTbUserSnsListMap.get(i).get("uid").toString();
                if (snsType == 4) {
                    isBWeixin = true;
                }
                if (snsTypePhone == 0 && snsType == 0 && uid.equals(userMobile)) {
                    snsTypePhone = snsId;
                }
            }
            if (!isBWeixin) {
                return packagMsg(ResultCode.NOT_BOUND_WEIXIN.getResp_code(), dataJson);
            }
            userInfo.setUserMobile("");
            userInfo.setMobileValite(0);
            saveInfoDao.saveUpdateEntity(userInfo);
            //更新绑定记录表中手机的绑定记录为已解绑
            if (snsTypePhone > 0) {
                LOG.info("---snsTypePhone=" + snsTypePhone);
                saveInfoDao.updateUserBind(userId, 0);
            }
            //更新验证码
            saveInfoDao.updateTbVerificationStatus(2, userMobile, 4);//验证码类型:1手机绑定2修改密码3重置密码4手机解绑5手机注册
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
