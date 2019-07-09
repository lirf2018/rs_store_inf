package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.common.bean.PageInfo;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-23 11:36
 * 功能介绍: 查询订单列表
 */
@Service("qurey_order_list")
public class QueryOrderList implements ResultOut {
    private Logger LOG = Logger.getLogger(QueryOrderList.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer userId = bean.getInteger("user_id");
            Integer current = bean.getInteger("current");
            if (null == userId || userId.intValue() == 0 || null == current) {
                LOG.info("--------->用户标识不能为空");
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            Integer status = bean.getInteger("status");
            List<Map<String, Object>> orderList = new ArrayList<Map<String, Object>>();//输出订单列表
            //查询订单
            PageInfo page = new PageInfo();
            if (null != status && -1 != status.intValue()) {
                page = findInfoDao.loadOrderPage(current, userId, status);
            } else {
                page = findInfoDao.loadOrderPage(current, userId);
            }

            String orderIds = "";
            List<Map<String, Object>> objectList = page.getResultListMap();//订单列表
            if (null != objectList && objectList.size() > 0) {
                for (int i = 0; i < objectList.size(); i++) {
                    Map<String, Object> map = objectList.get(i);
                    int orderId = Integer.parseInt(String.valueOf(map.get("order_id")));
                    orderIds = orderIds + orderId + ",";
                }
                orderIds = orderIds.substring(0, orderIds.length() - 1);
                LOG.info("----->orderIds=" + orderIds);
                //查询订单详情
                List<Map<String, Object>> detailList = findInfoDao.loadOrderDetailListMap(orderIds);
                for (int i = 0; i < objectList.size(); i++) {
                    Map<String, Object> orderMap = objectList.get(i);//订单

                    int orderId = Integer.parseInt(String.valueOf(objectList.get(i).get("order_id")));
                    List<Map<String, Object>> orderDetailList = new ArrayList<Map<String, Object>>();//输出订单详情列表
                    for (int j = 0; j < detailList.size(); j++) {
                        int orderId_ = Integer.parseInt(String.valueOf(detailList.get(j).get("order_id")));
                        if (orderId == orderId_) {
                            orderDetailList.add(detailList.get(j));
                        }
                    }
                    orderMap.put("order_detail", orderDetailList);
                    orderList.add(orderMap);
                }

            }
            dataJson.put("order_list", orderList);
            dataJson.put("has_next", page.isHasNext());
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
