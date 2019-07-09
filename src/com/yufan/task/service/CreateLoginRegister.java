package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.*;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.CacheData;
import com.yufan.util.DatetimeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-12-14 10:23
 * 功能介绍: 用户注册  (用户名)
 * 注册流程：-(未开发)---->用户名，密码注册----->绑定openId--------->手机号验证----------》正常使用
 */
@Deprecated
//@Service("create_login_register")
public class CreateLoginRegister implements ResultOut {

    private Logger LOG = Logger.getLogger(CreateLoginRegister.class);

    @Autowired
    private SaveInfoService saveInfoService;

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            String loginName = bean.getString("login_name");
            String passwd = bean.getString("passwd");
            String question1 = bean.getString("question1");
            String answer1 = bean.getString("answer1");
            String question2 = bean.getString("question2");
            String answer2 = bean.getString("answer2");
            String question3 = bean.getString("question3");
            String answer3 = bean.getString("answer3");

            if (!StringUtils.nonEmptyString(loginName) || !StringUtils.nonEmptyString(passwd) || !StringUtils.nonEmptyString(question1)
                    || !StringUtils.nonEmptyString(question2) || !StringUtils.nonEmptyString(question3)) {
                LOG.info("------>缺少参数");
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            if (question1.equals(question2) || question1.equals(question3) || question2.equals(question3)) {
                LOG.info("------>密保问题不能相同");
                return packagMsg(ResultCode.ERROR_WORD_QUESTION_.getResp_code(), dataJson);
            }
            //登录名称不能是纯数字
            if (isNumeric(loginName)) {
                LOG.info("------>登录名称不能是纯数字");
                return packagMsg(ResultCode.LOGIN_NAME_CANNOT_NUM.getResp_code(), dataJson);
            }

            //判断登录账号是否已经注册
            TbUserInfo userInfo_LoginName = findInfoDao.loadTbUserInfoByLoginName(loginName.trim());
            if (null != userInfo_LoginName) {
                LOG.info("--------->用户账号已存在");
                return packagMsg(ResultCode.FAIL_USER_EXIST.getResp_code(), dataJson);
            }
            //注册的登录名不能是手机号码
            TbUserInfo userInfo_phone = findInfoDao.loadTbUserInfoByPhone(loginName.trim());
            if (null != userInfo_phone) {
                LOG.info("--------->用户手机账号已存在");
                return packagMsg(ResultCode.FAIL_USER_EXIST.getResp_code(), dataJson);
            }

            //查询密保问题列表
            Map<String, String> questionMap = new HashMap<String, String>();
            List<TbParam> list = CacheData.paramList;
            for (int i = 0; i < list.size(); i++) {
                TbParam param = list.get(i);
                if ("passwd_question".equals(param.getParamKey())) {
                    questionMap.put(param.getParamKey(), param.getParamValue());
                }
            }

            TbUserInfo userInfo = new TbUserInfo();
            userInfo.setCreatetime(new Date());
            userInfo.setMobileValite(0);
            userInfo.setEmailValite(0);
            userInfo.setUserState(1);
            userInfo.setLoginName(loginName.trim());
            userInfo.setLoginPass(passwd.trim());
            userInfo.setNickName("用户" + System.currentTimeMillis());
            userInfo.setLastlogintime(new Date());
            userInfo.setLogCount(1);
            int userId = saveInfoService.saveEntity(userInfo);
            LOG.info("-------->新增用户注册=" + userId);
            if (userId > 0) {
                //保存密保问题
                TbPasswdResetQuestion prq3 = new TbPasswdResetQuestion();
                prq3.setCode(question3);
                prq3.setCreateDate(new Timestamp(new Date().getTime()));
                prq3.setUserId(userId);
                prq3.setAnswer(answer3);
                prq3.setStatus(1);
                prq3.setQustion(questionMap.get(question3));
                saveInfoService.saveEntity(prq3);
                TbPasswdResetQuestion prq2 = new TbPasswdResetQuestion();
                prq2.setCode(question2);
                prq2.setCreateDate(new Timestamp(new Date().getTime()));
                prq2.setUserId(userId);
                prq2.setAnswer(answer2);
                prq2.setStatus(1);
                prq2.setQustion(questionMap.get(question2));
                saveInfoService.saveEntity(prq2);
                TbPasswdResetQuestion prq1 = new TbPasswdResetQuestion();
                prq1.setCode(question1);
                prq1.setAnswer(answer1);
                prq1.setCreateDate(new Timestamp(new Date().getTime()));
                prq1.setUserId(userId);
                prq1.setStatus(1);
                prq1.setQustion(questionMap.get(question1));
                saveInfoService.saveEntity(prq1);
            }
            dataJson.put("id", userId);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        } catch (Throwable e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }

    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

}
