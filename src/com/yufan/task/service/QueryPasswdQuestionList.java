package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbParam;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.CacheData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 查询密保问题
 */
@Deprecated
//@Service("query_passwd_question_list")
public class QueryPasswdQuestionList implements ResultOut {

    private Logger LOG = Logger.getLogger(QueryPasswdQuestionList.class);


    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer userId = bean.getInteger("user_id");

            //查询密保问题列表
            List<Map<String, String>> outList = new ArrayList<Map<String, String>>();
            dataJson.put("question_list", new ArrayList<>());
            dataJson.put("user_question_list", new ArrayList<>());

            List<TbParam> list = CacheData.paramList;
            for (int i = 0; i < list.size(); i++) {
                TbParam param = list.get(i);
                if ("passwd_question".equals(param.getParamKey())) {
                    Map<String, String> questionMap = new HashMap<String, String>();
                    questionMap.put("code", param.getParamKey());
                    questionMap.put("code_name", param.getParamValue());
                    outList.add(questionMap);
                }
            }
            dataJson.put("question_list", outList);
            //查询用户密保问题
            if (null != userId && 0 != userId.intValue()) {
                List<Map<String, Object>> userQuestionListOut = new ArrayList<Map<String, Object>>();
                List<Map<String, Object>> userQuestionList = findInfoDao.queryUserQuestionListMap(userId);
                for (int i = 0; i < userQuestionList.size(); i++) {
                    Map<String, Object> map = userQuestionList.get(i);
                    map.remove("answer");
                    userQuestionListOut.add(map);
                }
                dataJson.put("user_question_list", userQuestionListOut);
            }
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("-->异常" + e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
