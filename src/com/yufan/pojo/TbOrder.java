package com.yufan.pojo;

import java.util.Date;

/**
 * TbOrder entity. @author MyEclipse Persistence Tools
 */

public class TbOrder implements java.io.Serializable {

    // Fields

    private Integer orderId;
    private Integer userId;
    private String orderNo;
    private String orderFrom;
    private Integer goodsCount;
    private double orderPrice;
    private double realPrice;
    private double advancePrice;
    private double needpayPrice;
    private String userName;
    private String userPhone;
    private String userAddr;
    private Integer userAddrId;
    private Integer advancePayWay;
    private String advancePayCode;
    private Date advancePayTime;
    private Integer payWay;
    private String payCode;
    private Date payTime;
    private String tradeChannel;
    private double postPrice;
    private Integer postWay;
    private String postName;
    private String postNo;
    private String userRemark;
    private String serviceRemark;
    private Integer orderStatus;
    private Date orderTime;
    private Date postTime;
    private Integer businessType;
    private Integer discountsId;
    private double discountsPrice;
    private String discountsRemark;
    private String tradeNo;
    private double refundPrice;
    private String refundRemark;
    private Date createtime;
    private Date lastaltertime;
    private String lastalterman;
    private String remark;
    private Integer partnersId;
    private String partnersName;
    private String ticketJson;
    private String postPhone;
    private String postMan;
    private Integer statusOpration;

    // Constructors

    /**
     * default constructor
     */
    public TbOrder() {
    }

    /**
     * full constructor
     */
    public TbOrder(Integer userId, String orderNo, String orderFrom,
                   Integer goodsCount, double orderPrice, double realPrice,
                   double advancePrice, double needpayPrice, String userName,
                   String userPhone, String userAddr, Integer userAddrId,
                   Integer advancePayWay, String advancePayCode, Date advancePayTime,
                   Integer payWay, String payCode, Date payTime, String tradeChannel,
                   double postPrice, Integer postWay, String postName, String postNo,
                   String userRemark, String serviceRemark, Integer orderStatus,
                   Date orderTime, Date postTime, Integer businessType,
                   Integer discountsId, double discountsPrice, String discountsRemark,
                   String tradeNo, double refundPrice, String refundRemark,
                   Date createtime, Date lastaltertime, String lastalterman,
                   String remark, Integer partnersId, String partnersName,
                   String ticketJson, String postPhone, String postMan,
                   Integer statusOpration) {
        this.userId = userId;
        this.orderNo = orderNo;
        this.orderFrom = orderFrom;
        this.goodsCount = goodsCount;
        this.orderPrice = orderPrice;
        this.realPrice = realPrice;
        this.advancePrice = advancePrice;
        this.needpayPrice = needpayPrice;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userAddr = userAddr;
        this.userAddrId = userAddrId;
        this.advancePayWay = advancePayWay;
        this.advancePayCode = advancePayCode;
        this.advancePayTime = advancePayTime;
        this.payWay = payWay;
        this.payCode = payCode;
        this.payTime = payTime;
        this.tradeChannel = tradeChannel;
        this.postPrice = postPrice;
        this.postWay = postWay;
        this.postName = postName;
        this.postNo = postNo;
        this.userRemark = userRemark;
        this.serviceRemark = serviceRemark;
        this.orderStatus = orderStatus;
        this.orderTime = orderTime;
        this.postTime = postTime;
        this.businessType = businessType;
        this.discountsId = discountsId;
        this.discountsPrice = discountsPrice;
        this.discountsRemark = discountsRemark;
        this.tradeNo = tradeNo;
        this.refundPrice = refundPrice;
        this.refundRemark = refundRemark;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalterman = lastalterman;
        this.remark = remark;
        this.partnersId = partnersId;
        this.partnersName = partnersName;
        this.ticketJson = ticketJson;
        this.postPhone = postPhone;
        this.postMan = postMan;
        this.statusOpration = statusOpration;
    }

    // Property accessors

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderFrom() {
        return this.orderFrom;
    }

    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom;
    }

    public Integer getGoodsCount() {
        return this.goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public double getOrderPrice() {
        return this.orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public double getRealPrice() {
        return this.realPrice;
    }

    public void setRealPrice(double realPrice) {
        this.realPrice = realPrice;
    }

    public double getAdvancePrice() {
        return this.advancePrice;
    }

    public void setAdvancePrice(double advancePrice) {
        this.advancePrice = advancePrice;
    }

    public double getNeedpayPrice() {
        return this.needpayPrice;
    }

    public void setNeedpayPrice(double needpayPrice) {
        this.needpayPrice = needpayPrice;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return this.userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddr() {
        return this.userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public Integer getUserAddrId() {
        return this.userAddrId;
    }

    public void setUserAddrId(Integer userAddrId) {
        this.userAddrId = userAddrId;
    }

    public Integer getAdvancePayWay() {
        return this.advancePayWay;
    }

    public void setAdvancePayWay(Integer advancePayWay) {
        this.advancePayWay = advancePayWay;
    }

    public String getAdvancePayCode() {
        return this.advancePayCode;
    }

    public void setAdvancePayCode(String advancePayCode) {
        this.advancePayCode = advancePayCode;
    }

    public Date getAdvancePayTime() {
        return this.advancePayTime;
    }

    public void setAdvancePayTime(Date advancePayTime) {
        this.advancePayTime = advancePayTime;
    }

    public Integer getPayWay() {
        return this.payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public String getPayCode() {
        return this.payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public Date getPayTime() {
        return this.payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getTradeChannel() {
        return this.tradeChannel;
    }

    public void setTradeChannel(String tradeChannel) {
        this.tradeChannel = tradeChannel;
    }

    public double getPostPrice() {
        return this.postPrice;
    }

    public void setPostPrice(double postPrice) {
        this.postPrice = postPrice;
    }

    public Integer getPostWay() {
        return this.postWay;
    }

    public void setPostWay(Integer postWay) {
        this.postWay = postWay;
    }

    public String getPostName() {
        return this.postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostNo() {
        return this.postNo;
    }

    public void setPostNo(String postNo) {
        this.postNo = postNo;
    }

    public String getUserRemark() {
        return this.userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public String getServiceRemark() {
        return this.serviceRemark;
    }

    public void setServiceRemark(String serviceRemark) {
        this.serviceRemark = serviceRemark;
    }

    public Integer getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderTime() {
        return this.orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPostTime() {
        return this.postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Integer getBusinessType() {
        return this.businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getDiscountsId() {
        return this.discountsId;
    }

    public void setDiscountsId(Integer discountsId) {
        this.discountsId = discountsId;
    }

    public double getDiscountsPrice() {
        return this.discountsPrice;
    }

    public void setDiscountsPrice(double discountsPrice) {
        this.discountsPrice = discountsPrice;
    }

    public String getDiscountsRemark() {
        return this.discountsRemark;
    }

    public void setDiscountsRemark(String discountsRemark) {
        this.discountsRemark = discountsRemark;
    }

    public String getTradeNo() {
        return this.tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public double getRefundPrice() {
        return this.refundPrice;
    }

    public void setRefundPrice(double refundPrice) {
        this.refundPrice = refundPrice;
    }

    public String getRefundRemark() {
        return this.refundRemark;
    }

    public void setRefundRemark(String refundRemark) {
        this.refundRemark = refundRemark;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastaltertime() {
        return this.lastaltertime;
    }

    public void setLastaltertime(Date lastaltertime) {
        this.lastaltertime = lastaltertime;
    }

    public String getLastalterman() {
        return this.lastalterman;
    }

    public void setLastalterman(String lastalterman) {
        this.lastalterman = lastalterman;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPartnersId() {
        return this.partnersId;
    }

    public void setPartnersId(Integer partnersId) {
        this.partnersId = partnersId;
    }

    public String getPartnersName() {
        return this.partnersName;
    }

    public void setPartnersName(String partnersName) {
        this.partnersName = partnersName;
    }

    public String getTicketJson() {
        return this.ticketJson;
    }

    public void setTicketJson(String ticketJson) {
        this.ticketJson = ticketJson;
    }

    public String getPostPhone() {
        return this.postPhone;
    }

    public void setPostPhone(String postPhone) {
        this.postPhone = postPhone;
    }

    public String getPostMan() {
        return this.postMan;
    }

    public void setPostMan(String postMan) {
        this.postMan = postMan;
    }

    public Integer getStatusOpration() {
        return this.statusOpration;
    }

    public void setStatusOpration(Integer statusOpration) {
        this.statusOpration = statusOpration;
    }

}