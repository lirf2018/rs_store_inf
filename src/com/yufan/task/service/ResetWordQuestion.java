package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbParam;
import com.yufan.pojo.TbPasswdResetQuestion;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.CacheData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 重置密保问题
 */
@Deprecated
//@Service("reset_word_question")
public class ResetWordQuestion implements ResultOut {

    private Logger LOG = Logger.getLogger(ResetWordQuestion.class);

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
            String qustion1 = bean.getString("qustion1");
            String answer1 = bean.getString("answer1");
            String qustion2 = bean.getString("qustion2");
            String answer2 = bean.getString("answer2");
            String qustion3 = bean.getString("qustion3");
            String answer3 = bean.getString("answer3");

            if (null == userId || userId.intValue() == 0 || !StringUtils.nonEmptyString(qustion1)
                    || !StringUtils.nonEmptyString(qustion2) || !StringUtils.nonEmptyString(qustion3) || !StringUtils.nonEmptyString(answer1)
                    || !StringUtils.nonEmptyString(answer2) || !StringUtils.nonEmptyString(answer3)) {
                LOG.info("------>缺少参数");
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }

            //删除密保问题
            saveInfoService.deletePasswdQuestion(userId);

            //查询密保问题列表
            Map<String, String> questionMap = new HashMap<String, String>();
            List<TbParam> list = CacheData.paramList;
            for (int i = 0; i < list.size(); i++) {
                TbParam param = list.get(i);
                if ("passwd_question".equals(param.getParamKey())) {
                    questionMap.put(param.getParamKey(), param.getParamValue());
                }
            }


            //保存密保问题
            TbPasswdResetQuestion prq3 = new TbPasswdResetQuestion();
            prq3.setCode(qustion3);
            prq3.setCreateDate(new Timestamp(new Date().getTime()));
            prq3.setUserId(userId);
            prq3.setAnswer(answer3);
            prq3.setStatus(1);
            prq3.setQustion(questionMap.get(qustion3));
            saveInfoService.saveEntity(prq3);
            TbPasswdResetQuestion prq2 = new TbPasswdResetQuestion();
            prq2.setCode(qustion2);
            prq2.setCreateDate(new Timestamp(new Date().getTime()));
            prq2.setUserId(userId);
            prq2.setAnswer(answer2);
            prq2.setStatus(1);
            prq2.setQustion(questionMap.get(qustion2));
            saveInfoService.saveEntity(prq2);
            TbPasswdResetQuestion prq1 = new TbPasswdResetQuestion();
            prq1.setCode(qustion1);
            prq1.setAnswer(answer1);
            prq1.setCreateDate(new Timestamp(new Date().getTime()));
            prq1.setUserId(userId);
            prq1.setStatus(1);
            prq1.setQustion(questionMap.get(qustion1));
            saveInfoService.saveEntity(prq1);


        } catch (Exception e) {
            LOG.info("----------->异常");
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
