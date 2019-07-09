package com.yufan.task.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-27 17:29
 * 功能介绍:
 */
public class OrderBean {
    @JSONField(name = "user_id")
    private Integer userId;

    @JSONField(name = "goods_count")
    private Integer goodsCount;

    @JSONField(name = "order_price")
    private BigDecimal orderPrice;//订单支付总价

    @JSONField(name = "real_price")
    private BigDecimal realPrice;//订单实际支付价格

    @JSONField(name = "advance_price")
    private BigDecimal advancePrice;//订单预付款

    @JSONField(name = "needpay_price")
    private BigDecimal needpayPrice;//订单待付款

    @JSONField(name = "user_addr_id")
    private Integer userAddrId;//收货地址标识

    @JSONField(name = "user_remark")
    private String userRemark;

    @JSONField(name = "business_type")
    private Integer businessType = 1;//业务类型 1.正常下单2.抢购3.预定4租赁

    @JSONField(name = "discounts_id")
    private Integer discountsId;//qr标识

    @JSONField(name = "discounts_price")
    private BigDecimal discountsPrice;//优惠价格

    @JSONField(name = "discounts_remark")
    private String discountsRemark;//优惠说明

    @JSONField(name = "remark")
    private String remark;

    @JSONField(name = "post_price")
    private BigDecimal postPrice;//邮费

    @JSONField(name = "deposit_price_all")
    private BigDecimal depositPriceAll;//押金总额

    @JSONField(name = "advance_pay_way")
    private Integer advancePayWay;

    @JSONField(name = "order_detail_list")
    private List<OrderDetailBean> orderDetailList;

    //非必须参数
    @JSONField(name = "user_name")
    private String userName;

    @JSONField(name = "user_phone")
    private String userPhone;

    private String userAddr;

    private String ticketJson;

    private Integer partnersId;

    private String partnersName;

    private Integer postWay;

    //
    private String postPhone;//快递人员电话
    private String postMan;//快递人员

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public BigDecimal getAdvancePrice() {
        return advancePrice;
    }

    public void setAdvancePrice(BigDecimal advancePrice) {
        this.advancePrice = advancePrice;
    }

    public BigDecimal getNeedpayPrice() {
        return needpayPrice;
    }

    public void setNeedpayPrice(BigDecimal needpayPrice) {
        this.needpayPrice = needpayPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getUserAddrId() {
        return userAddrId;
    }

    public void setUserAddrId(Integer userAddrId) {
        this.userAddrId = userAddrId;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getDiscountsId() {
        return discountsId;
    }

    public void setDiscountsId(Integer discountsId) {
        this.discountsId = discountsId;
    }

    public BigDecimal getDiscountsPrice() {
        return discountsPrice;
    }

    public void setDiscountsPrice(BigDecimal discountsPrice) {
        this.discountsPrice = discountsPrice;
    }

    public String getDiscountsRemark() {
        return discountsRemark;
    }

    public void setDiscountsRemark(String discountsRemark) {
        this.discountsRemark = discountsRemark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPostWay() {
        return postWay;
    }

    public void setPostWay(Integer postWay) {
        this.postWay = postWay;
    }

    public BigDecimal getPostPrice() {
        return postPrice;
    }

    public void setPostPrice(BigDecimal postPrice) {
        this.postPrice = postPrice;
    }

    public BigDecimal getDepositPriceAll() {
        return depositPriceAll;
    }

    public void setDepositPriceAll(BigDecimal depositPriceAll) {
        this.depositPriceAll = depositPriceAll;
    }

    public Integer getAdvancePayWay() {
        return advancePayWay;
    }

    public void setAdvancePayWay(Integer advancePayWay) {
        this.advancePayWay = advancePayWay;
    }

    public List<OrderDetailBean> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetailBean> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public String getTicketJson() {
        return ticketJson;
    }

    public void setTicketJson(String ticketJson) {
        this.ticketJson = ticketJson;
    }

    public Integer getPartnersId() {
        return partnersId;
    }

    public void setPartnersId(Integer partnersId) {
        this.partnersId = partnersId;
    }

    public String getPartnersName() {
        return partnersName;
    }

    public void setPartnersName(String partnersName) {
        this.partnersName = partnersName;
    }

    public String getPostPhone() {
        return postPhone;
    }

    public void setPostPhone(String postPhone) {
        this.postPhone = postPhone;
    }

    public String getPostMan() {
        return postMan;
    }

    public void setPostMan(String postMan) {
        this.postMan = postMan;
    }
}
