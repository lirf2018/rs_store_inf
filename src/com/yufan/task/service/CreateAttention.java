package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbAttention;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.task.dao.SaveInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-24 18:23
 * 功能介绍: 增加关注
 */
@Service("create_attention")
public class CreateAttention implements ResultOut {

    private Logger LOG = Logger.getLogger(CreateAttention.class);

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
            Integer attentionId = bean.getInteger("attention_id");//关注id:商家id或者商品id(必须)
            Integer typeId = bean.getInteger("type_id");//关注类型:1店铺2商品(必须)

            if (null == userId || null == attentionId || null == typeId) {
                LOG.info("-------->新增用户关注校验失败");
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }

            //查询关注情况(如果存在关注,则删除关注)
            TbAttention hasAttention = findInfoDao.loadTbAttentionByParam(userId, attentionId, typeId);
            if (null == hasAttention) {
                TbAttention attention = new TbAttention();
                attention.setUserId(userId);
                attention.setAttentionId(attentionId);
                attention.setTypeId(typeId);
                attention.setStatus(1);
                attention.setCreatetime(new Date());
                int id = saveInfoService.saveEntity(attention);
                LOG.info("-------->新增用户关注id=" + id);
                dataJson.put("id", id);
                return packagMsg(ResultCode.OK.getResp_code(), dataJson);
            } else {
                //删除用户已关注的
                saveInfoService.deleteUserAttention(userId, attentionId, typeId);//typeId 关注类型:1店铺2商品
                return packagMsg(ResultCode.OK.getResp_code(), dataJson);
            }
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
    }

}
