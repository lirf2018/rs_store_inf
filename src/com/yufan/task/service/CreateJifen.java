package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbAttention;
import com.yufan.pojo.TbJifen;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.dao.SaveInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-24 18:23
 * 功能介绍: 保存积分
 */
@Service("create_jifen")
public class CreateJifen implements ResultOut {

    private Logger LOG = Logger.getLogger(CreateJifen.class);

    @Autowired
    private SaveInfoService saveInfoService;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer taskId = bean.getInteger("task_id");//任务标识0为消耗积分n为任务标识(必需)
            Integer isInout = bean.getInteger("is_inout");//进出帐1入账2出账(必需)
            String items = bean.getString("items");//积分事项(必需)
            Integer jifenValue = bean.getInteger("jifen");//积分大小(必需)
            Integer userId = bean.getInteger("user_id");

            if (!StringUtils.nonEmptyString(items) || taskId == null || userId == null || jifenValue == null || isInout == null) {
                LOG.info("---->缺少必要参数");
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            TbJifen jifen = new TbJifen();
            jifen.setTaskId(taskId);
            jifen.setIsInout(isInout);
            jifen.setItems(items);
            jifen.setJifen(jifenValue);
            jifen.setUserId(userId);

            jifen.setCreatetime(new Date());
            int id = saveInfoService.saveEntity(jifen);
            LOG.info("-------->新增保存积分=" + id);
            dataJson.put("id", id);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
    }

}
