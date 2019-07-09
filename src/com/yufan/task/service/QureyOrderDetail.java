package com.yufan.task.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.common.util.RsConstants;
import com.yufan.pojo.TbOrder;
import com.yufan.pojo.TbOrderDetail;
import com.yufan.pojo.TbParam;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.CacheData;
import com.yufan.util.DatetimeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-23 17:17
 * 功能介绍: 查询订单详情(qurey_order_detail)
 */
@Service("qurey_order_detail")
public class QureyOrderDetail implements ResultOut {

    private Logger LOG = Logger.getLogger(QureyOrderDetail.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            String orderNo = null;
            if (!bean.containsKey("order_no")) {
                LOG.info("--------->订单号不能为空");
                return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
            }
            orderNo = bean.getString("order_no");
            //查询订单信息
            TbOrder order = findInfoDao.loadTbOrderInfo(orderNo);
            if (null == order) {
                LOG.info("--------->订单不存在");
                return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
            }

            //查询参数
            List<TbParam> paramList = CacheData.paramList;
            Map<Integer, String> statusMap = new HashMap<Integer, String>();
            Map<Integer, String> payMap = new HashMap<Integer, String>();
            Map<Integer, String> businessTypeMap = new HashMap<Integer, String>();
            Map<Integer, String> postMap = new HashMap<Integer, String>();

            for (int i = 0; i < paramList.size(); i++) {
                TbParam param = paramList.get(i);
                String paramCode = param.getParamCode();
                String key = param.getParamKey();
                String value = param.getParamValue();
                if ("pay_way".equals(paramCode)) {
                    payMap.put(Integer.parseInt(key), value);
                } else if ("order_status".equals(paramCode)) {
                    statusMap.put(Integer.parseInt(key), value);
                } else if ("business_type".equals(paramCode)) {
                    businessTypeMap.put(Integer.parseInt(key), value);
                } else if ("post_way".equals(paramCode)) {
                    postMap.put(Integer.parseInt(key), value);
                }
            }

            dataJson.put("order_id", order.getOrderId());//订单标识
            dataJson.put("user_id", order.getUserId());//用户标识
            dataJson.put("order_no", order.getOrderNo());//订单号
            dataJson.put("goods_count", order.getGoodsCount());//订购商品数
            dataJson.put("order_price", new BigDecimal(order.getOrderPrice()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//订单支付总价
            dataJson.put("real_price", new BigDecimal(order.getRealPrice()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//订单实际支付价格
            dataJson.put("advance_price", new BigDecimal(order.getAdvancePrice()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//订单预付款
            dataJson.put("needpay_price", new BigDecimal(order.getNeedpayPrice()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//订单待付款
            dataJson.put("user_name", order.getUserName());//收货人姓名
            dataJson.put("user_phone", order.getUserPhone());//收货人手机号
            dataJson.put("user_addr", order.getUserAddr());//收货人地址
            dataJson.put("advance_pay_way", order.getAdvancePayWay());//预付支付方式 0现金付款1微信2支付宝3其他'
            dataJson.put("advance_pay_way_name", payMap.get(order.getAdvancePayWay()));//预付支付方式 0现金付款1微信2支付宝3其他'
            dataJson.put("advance_pay_code", order.getAdvancePayCode());//预付支付交易号
            if (null == order.getAdvancePayTime()) {
                dataJson.put("advance_pay_time", "");//预付支付时间
            } else {
                dataJson.put("advance_pay_time", DatetimeUtil.TimeStamp2Date(String.valueOf(order.getAdvancePayTime().getTime())));//预付支付时间
            }

            dataJson.put("pay_way", order.getPayWay());//支付方式 0现金付款1微信2支付宝3其他
            dataJson.put("pay_way_name", payMap.get(order.getPayWay()));//支付方式 0现金付款1微信2支付宝3其他

            dataJson.put("pay_code", order.getPayCode());//支付交易号
            if (null == order.getPayTime()) {
                dataJson.put("pay_time", "");//支付时间
            } else {
                dataJson.put("pay_time", DatetimeUtil.TimeStamp2Date(String.valueOf(order.getPayTime().getTime())));//支付时间
            }

            dataJson.put("trade_channel", order.getTradeChannel());//交易渠道
            dataJson.put("post_price", new BigDecimal(order.getPostPrice()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//快递费
            dataJson.put("post_way", order.getPostWay());//配送方式 1.快递4.自取5商家配送'
            dataJson.put("post_way_name", postMap.get(order.getPostWay()));//配送方式 1.快递2.平邮3.EMS4.自取5商家配送'

            dataJson.put("post_name", order.getPostName());//快递公司
            dataJson.put("post_no", order.getPostNo());//快递单号
            dataJson.put("user_remark", order.getUserRemark());//用户备注
            dataJson.put("order_status", order.getOrderStatus());//0待付款1已付款2确认中3已失败4待发货5待收货6已完成7已取消8已删除9退款中10已退款',
            dataJson.put("order_status_name", statusMap.get(order.getOrderStatus()));//0待付款1已付款2确认中3已失败4待发货5待收货6已完成7已取消8已删除9退款中10已退款',
            dataJson.put("order_time", DatetimeUtil.TimeStamp2Date(String.valueOf(order.getOrderTime().getTime())));//下单时间
            if (null == order.getPostTime()) {
                dataJson.put("post_time", "");//发货时间
            } else {
                dataJson.put("post_time", DatetimeUtil.TimeStamp2Date(String.valueOf(order.getPostTime().getTime())));//发货时间
            }

            dataJson.put("business_type", order.getBusinessType());//业务类型 1.正常下单2.抢购3.预定
            dataJson.put("business_type_name", businessTypeMap.get(order.getBusinessType()));//业务类型 1.正常下单2.抢购3.预定
            dataJson.put("discounts_price", new BigDecimal(order.getDiscountsPrice()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//优惠金额数
            dataJson.put("discounts_remark", order.getDiscountsRemark());//优惠说明
            dataJson.put("partners_name", order.getPartnersName());//商家名称
            dataJson.put("service_remark", order.getServiceRemark());
            List<Map<String, Object>> detailListMap = new ArrayList<Map<String, Object>>();//
            String path = RsConstants.PHONE_URL;
            List<TbOrderDetail> detailList = findInfoDao.loadTbOrderDetailList(order.getOrderId());
            for (int i = 0; i < detailList.size(); i++) {
                TbOrderDetail detail = detailList.get(i);
                Map<String, Object> detailMap = new HashMap<String, Object>();
                detailMap.put("detail_id", detail.getDetailId());
                detailMap.put("goods_id", detail.getGoodsId());//商品标识
                detailMap.put("goods_name", detail.getGoodsName());//商品名称
                detailMap.put("goods_spec", detail.getGoodsSpec());//已选规格编码
                detailMap.put("goods_spec_name", detail.getGoodsSpecName());//已选规格名称
                detailMap.put("goods_spec_name_str", detail.getGoodsSpecNameStr());//已选规格名称Str
                detailMap.put("goods_count", detail.getGoodsCount());//购买数量
                detailMap.put("sale_money", detail.getSaleMoney().setScale(2, BigDecimal.ROUND_HALF_UP).toString());//商品销售价
                detailMap.put("goods_true_money", detail.getGoodsTrueMoney().setScale(2, BigDecimal.ROUND_HALF_UP).toString());//商品原价
                detailMap.put("goods_purchase_price", detail.getGoodsPurchasePrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());//成本价
                detailMap.put("time_price", detail.getTimePrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());//抢购价
                detailMap.put("deposit_price", detail.getDepositPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());//押金
                dataJson.put("deposit_price", detail.getDepositPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());//押金
                detailMap.put("shop_id", detail.getShopId());//店铺标识
                detailMap.put("shop_name", detail.getShopName());//店铺名称
                detailMap.put("partners_id", detail.getPartnersId());//商家标识
                detailMap.put("partners_name", detail.getPartnersName());//商家名称
                detailMap.put("get_addr_id", detail.getGetAddrId());//领取地址ID
                detailMap.put("get_addr_name", detail.getGetAddrName());//领取地址
                String format = "yyyy-MM-dd";
                if (null == detail.getGetTime()) {
                    detailMap.put("get_time", "");//租赁领取时间
                    dataJson.put("get_time", "");//租赁领取时间
                } else {
                    detailMap.put("get_time", DatetimeUtil.TimeStamp2Date(String.valueOf(detail.getGetTime().getTime())));//领取时间
                    dataJson.put("get_time", DatetimeUtil.TimeStamp2Date(String.valueOf(detail.getGetTime().getTime()), format));//领取时间
                }
                detailMap.put("back_addr_id", detail.getBackAddrId());//归还地址ID
                detailMap.put("back_addr_name", detail.getBackAddrName());//归还地址
                dataJson.put("back_addr_id", detail.getBackAddrId());//归还地址ID
                dataJson.put("back_addr_name", detail.getBackAddrName());//归还地址
                if (null == detail.getBackTime()) {
                    detailMap.put("back_time", "");//租赁归还时间
                    dataJson.put("back_time", "");//租赁归还时间
                } else {
                    detailMap.put("back_time", DatetimeUtil.TimeStamp2Date(String.valueOf(detail.getBackTime().getTime())));//归还时间
                    dataJson.put("back_time", DatetimeUtil.TimeStamp2Date(String.valueOf(detail.getBackTime().getTime()), format));//归还时间
                }
                detailMap.put("detail_status", detail.getDetailStatus());//商品详情状态 0未取货1已取货2已取消3已完成'
                detailMap.put("is_ticket", detail.getIsTicket());//是否是卡券 0不是卡券1是卡券商品
                detailMap.put("time_goods_id", detail.getTimeGoodsId());//抢购商品标识
                detailMap.put("ticket_json", detail.getTicketJson());//卡券对象
                detailMap.put("status", detail.getStatus());//状态0无效1有效
                detailMap.put("goods_img", path + (detail.getGoodsImg() == null || "".equals(detail.getGoodsImg()) ? "null.jpg" : detail.getGoodsImg()));//商品图片
                detailMap.put("is_time_goods", detail.getTimeGoodsId() > 0 ? 0 : 1);//是否是抢购商品0不是1是
                detailListMap.add(detailMap);
            }

            dataJson.put("order_detail", detailListMap);

            if (order.getBusinessType() == 4) {
                //还取货日期
                JSONArray arrayDate = new JSONArray();
                if (null != CacheData.paramMap.get("sys_code-back_day_max")) {
                    String getTime = dataJson.getString("get_time");
                    String nowDate = DatetimeUtil.getNow("yyyy-MM-dd");
                    if (DatetimeUtil.compareDate(nowDate, getTime) > 0) {
                        getTime = nowDate;
                    }
                    int getDateMax = Integer.parseInt(CacheData.paramMap.get("sys_code-back_day_max"));
                    for (int i = 0; i < getDateMax; i++) {
                        String d = DatetimeUtil.getDateLastOrNext("yyyy-MM-dd", getTime, i + 1);
                        arrayDate.add(d);
                    }
                }
                //查询还货地址
                List<Map<String, Object>> addrChar = findInfoDao.loadSendAddrChar(6);
                List<Map<String, Object>> addrList = findInfoDao.loadSendAddrListMap(6);
                //输出数据
                List<Map<String, Object>> outChar = new ArrayList<Map<String, Object>>();
                for (int i = 0; i < addrChar.size(); i++) {
                    Map<String, Object> oMap = new HashMap<String, Object>();
                    String charWord = String.valueOf(addrChar.get(i).get("sort_char"));
                    oMap.put("char_word", charWord);
                    oMap.put("is_zq", false);
                    oMap.put("is_ps", false);

                    List<Map<String, Object>> addrData = new ArrayList<Map<String, Object>>();
                    for (int j = 0; j < addrList.size(); j++) {
                        String charWord_ = String.valueOf(addrList.get(j).get("sort_char"));
                        if (charWord.equals(charWord_)) {
                            int addrType_ = Integer.parseInt(String.valueOf(addrList.get(j).get("addr_type")));
                            if (addrType_ == 4) {//地址类型4.自取 5配送
                                oMap.put("is_ps", true);
                            } else if (addrType_ == 5) {
                                oMap.put("is_zq", true);
                            }
                            addrData.add(addrList.get(j));
                        }
                    }
                    oMap.put("addr_detail", addrData);
                    outChar.add(oMap);
                }

                //设置还货可选地址
                dataJson.put("list_addr", outChar);
                //设置还货可选日期
                dataJson.put("list_back_date", arrayDate);
            }
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
