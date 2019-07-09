package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.SaveInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2018-01-10 11:35
 * 功能介绍: 删除购物车商品
 */
@Service("delete_ordercard")
public class DeleteOrderCard implements ResultOut {

    private Logger LOG = Logger.getLogger(DeleteOrderCard.class);

    @Autowired
    private SaveInfoDao saveInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            String cardIds = bean.getString("card_ids");
            Integer userId = bean.getInteger("user_id");
            if (userId == null || !StringUtils.nonEmptyString(cardIds)) {
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            saveInfoDao.updateOrderCardStatus(cardIds, 4, userId);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}