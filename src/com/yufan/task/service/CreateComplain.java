package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbAttention;
import com.yufan.pojo.TbComplain;
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
 * 功能介绍: 投诉建议
 */
@Service("create_complain")
public class CreateComplain implements ResultOut {

    private Logger LOG = Logger.getLogger(CreateComplain.class);

    @Autowired
    private SaveInfoService saveInfoService;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer userId = bean.getInteger("user_id");
            String information = bean.getString("information");
            String contents = bean.getString("contents");

            if (null == userId || !StringUtils.nonEmptyString(information) || !StringUtils.nonEmptyString(contents)) {
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            TbComplain complain = new TbComplain();
            complain.setUserId(userId);
            complain.setInformation(information);
            complain.setContents(contents);


            complain.setCreatetime(new Date());
            complain.setStatus(1);
            complain.setIsRead(0);
            int id = saveInfoService.saveEntity(complain);
            LOG.info("-------->新增投诉建议id=" + id);
            dataJson.put("id", id);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
    }

}
