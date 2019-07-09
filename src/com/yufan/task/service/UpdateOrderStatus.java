package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbOrder;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.task.dao.SaveInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2018-02-26 18:36
 * 功能介绍: 修改订单状态(update_order_status)
 */
@Service("update_order_status")
public class UpdateOrderStatus implements ResultOut {

    private Logger LOG = Logger.getLogger(UpdateOrderStatus.class);

    @Autowired
    private SaveInfoService saveInfoService;
    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            String orderNum = bean.getString("order_num");//订单号
            Integer status = bean.getInteger("order_status");
            String userRemark = bean.getString("user_remark");
            Integer userId = bean.getInteger("user_id");
            //查询订单是否存在

            if (StringUtils.nonEmptyString(orderNum) && null != status && null != userId && 0 != userId) {
                TbOrder order = findInfoDao.loadTbOrderInfo(orderNum);
                if (null != order && order.getUserId() == userId) {
                    saveInfoService.updateOrderStatus(order.getOrderId(), status, userRemark);
                    return packagMsg(ResultCode.OK.getResp_code(), dataJson);
                }
                return packagMsg(ResultCode.ORDER_NOT_EXIST.getResp_code(), dataJson);
            } else {
                LOG.info("--------缺少参数");
                return packagMsg(ResultCode.PARAM_ERROR.getResp_code(), dataJson);
            }
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }

}
