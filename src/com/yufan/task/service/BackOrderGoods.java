package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbDistributionAddr;
import com.yufan.pojo.TbOrder;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.CacheData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2019/3/13 13:54
 * 功能介绍:  还货
 */
@Service("back_order_goods")
public class BackOrderGoods implements ResultOut {

    private Logger LOG = Logger.getLogger(BackOrderGoods.class);

    @Autowired
    private SaveInfoService saveInfoService;

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {

            Integer userId = bean.getInteger("user_id");//
            Integer backAddrId = bean.getInteger("back_addr_id");//还货地址标识
            String orderNum = bean.getString("order_num");//订单号
            String backDate = bean.getString("goods_back_date");//还货日期
            if (null == userId || null == backAddrId || !StringUtils.nonEmptyString(orderNum) || !StringUtils.nonEmptyString(backDate)) {
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            //查询还货地址
            TbDistributionAddr distributionAddr = CacheData.platformMap.get(backAddrId);
            if (null == distributionAddr || distributionAddr.getStatus() != 1) {
                LOG.info("查询还货地址不存在");
                return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
            }
            //查询订单
            TbOrder order = findInfoDao.loadTbOrderInfo(orderNum);
            if (order == null || order.getUserId() != userId) {
                LOG.info("订单不存在或者订单不属于该用户orderNum=" + orderNum);
                return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
            }

            boolean flag = saveInfoService.updateOrderStatus(order.getOrderId(), backDate, distributionAddr.getDetailAddr(), backAddrId, "用户申请还货");
            if (flag) {
                return packagMsg(ResultCode.OK.getResp_code(), dataJson);
            }
            return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
    }
}
