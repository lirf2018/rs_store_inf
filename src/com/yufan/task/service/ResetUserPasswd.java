package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbUserInfo;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.dao.FindInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-12-26 16:55
 * 功能介绍: 修改用户密码(reset_user_passwd)
 */
@Deprecated
//@Service("reset_user_passwd")
public class ResetUserPasswd implements ResultOut {

    private Logger LOG = Logger.getLogger(ResetUserPasswd.class);

    @Autowired
    private SaveInfoService saveInfoService;

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            String newPasswd = bean.getString("new_passwd");
            String loginName = bean.getString("login_name");
            String question1 = bean.getString("question1");
            String answer1 = bean.getString("answer1");
            String question2 = bean.getString("question2");
            String answer2 = bean.getString("answer2");
            String question3 = bean.getString("question3");
            String answer3 = bean.getString("answer3");

            if (!StringUtils.nonEmptyString(newPasswd) || !StringUtils.nonEmptyString(loginName) || !StringUtils.nonEmptyString(question1)
                    || !StringUtils.nonEmptyString(question2) || !StringUtils.nonEmptyString(question3) || !StringUtils.nonEmptyString(answer1)
                    || !StringUtils.nonEmptyString(answer2) || !StringUtils.nonEmptyString(answer3)) {
                LOG.info("------>缺少参数");
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }

            //登录名查询用户是否存在
            TbUserInfo userInfo_ = findInfoDao.loadTbUserInfoByLoginName(loginName);
            if (null == userInfo_) {
                LOG.info("------->用户无效");
                return packagMsg(ResultCode.FAIL_USER_INVALIDATE.getResp_code(), dataJson);
            }

            //验证密保问题
            Map<String, String> mapQustion = new HashMap<String, String>();
            List<Map<String, Object>> userQuestionList = findInfoDao.queryUserQuestionListMap(userInfo_.getUserId());
            for (int i = 0; i < userQuestionList.size(); i++) {
                String code = userQuestionList.get(i).get("code").toString();
                String answer = userQuestionList.get(i).get("answer").toString();
                mapQustion.put(code, answer);
            }
            if (null == mapQustion.get(question1) || !answer1.equals(mapQustion.get(question1))) {
                LOG.info("------->用户密保问题有误");
                return packagMsg(ResultCode.ERROR_WORD_QUESTION.getResp_code(), dataJson);
            } else {
                mapQustion.remove(question1);
            }
            if (null == mapQustion.get(question2) || !answer2.equals(mapQustion.get(question2))) {
                LOG.info("------->用户密保问题有误");
                return packagMsg(ResultCode.ERROR_WORD_QUESTION.getResp_code(), dataJson);
            } else {
                mapQustion.remove(question2);
            }
            if (null == mapQustion.get(question3) || !answer3.equals(mapQustion.get(question3))) {
                LOG.info("------->用户密保问题有误");
                return packagMsg(ResultCode.ERROR_WORD_QUESTION.getResp_code(), dataJson);
            } else {
                mapQustion.remove(question3);
            }
            //修改用户密码
            saveInfoService.updateUserPasswdByUserId(userInfo_.getUserId(), newPasswd);

            return packagMsg(ResultCode.OK.getResp_code(), new JSONObject());
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }

}
