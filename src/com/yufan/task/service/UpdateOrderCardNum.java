package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.SaveInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2018-01-10 11:28
 * 功能介绍: 更新购物车数量(update_ordercard_num)
 */
@Service("update_ordercard_num")
public class UpdateOrderCardNum implements ResultOut {

    private Logger LOG = Logger.getLogger(UpdateOrderCardNum.class);

    @Autowired
    private SaveInfoDao saveInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer cardId = bean.getInteger("card_id");
            Integer goodsCount = bean.getInteger("goods_count");
            Integer userId = bean.getInteger("user_id");
            if (null == cardId || null == goodsCount || null == userId || goodsCount.intValue() < 1) {
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            saveInfoDao.updateOrderCardNum(userId, cardId, goodsCount);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
