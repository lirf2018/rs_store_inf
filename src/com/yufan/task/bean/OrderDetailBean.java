package com.yufan.task.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.List;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-27 17:47
 * 功能介绍:
 */
public class OrderDetailBean {


    @JSONField(name = "goods_id")
    private Integer goodsId;

    @JSONField(name = "goods_spec")
    private String goodsSpec;

    @JSONField(name = "goods_spec_name")
    private String goodsSpecName;

    @JSONField(name = "goods_spec_name_str")
    private String goodsSpecNameStr;

    @JSONField(name = "goods_count")
    private Integer goodsCount;

    @JSONField(name = "get_addr_id")
    private Integer getAddrId;

    @JSONField(name = "get_addr_nName")
    private String getAddrName;

    @JSONField(name = "get_time")
    private String getTime;

    @JSONField(name = "back_addr_id")
    private Integer backAddrId;

    @JSONField(name = "back_addr_name")
    private String backAddrName;

    @JSONField(name = "back_time")
    private String backTime;

    @JSONField(name = "remark")
    private String remark;

    @JSONField(name = "cart_id")
    private Integer cartId;//购物车标识

    @JSONField(name = "get_goods_date")
    private String getGoodsDate;

    @JSONField(name = "time_goods_id")
    private Integer timeGoodsId;//抢购商品必须标识

    /**
     * 非必须参数
     */
    @JSONField(name = "is_ticket")
    private Integer isTicket;

    @JSONField(name = "is_time_goods")
    private Integer isTimeGoods;

    @JSONField(name = "shop_id")
    private Integer shopId;

    @JSONField(name = "is_single")
    private Integer isSingle;

    @JSONField(name = "shop_name")
    private String shopName;

    @JSONField(name = "goods_name")
    private String goodsName;

    @JSONField(name = "sale_money")
    private BigDecimal salePrice;//销售价格

    @JSONField(name = "time_price")
    private BigDecimal timePrice;//抢购价

    @JSONField(name = "purchase_price")
    private BigDecimal purchasePrice;//进货价

    @JSONField(name = "true_money")
    private BigDecimal trueMoney;//原价

    @JSONField(name = "deposit_price")
    private BigDecimal depositPrice;//一个商品的押金

    @JSONField(name = "ticket_json")
    private String ticketJson;//商品券或者优惠券对象

    @JSONField(name = "partners_id")
    private Integer partnersId;

    @JSONField(name = "partners_name")
    private String partnersName;

    @JSONField(name = "goods_img")
    private String goodsImg;

    @JSONField(name = "order_detail_propty_list")
    private List<OrderDetailProptyBean> orderDetailProptyList;


    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public String getGoodsSpecName() {
        return goodsSpecName;
    }

    public void setGoodsSpecName(String goodsSpecName) {
        this.goodsSpecName = goodsSpecName;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Integer getGetAddrId() {
        return getAddrId;
    }

    public void setGetAddrId(Integer getAddrId) {
        this.getAddrId = getAddrId;
    }

    public String getGetAddrName() {
        return getAddrName;
    }

    public void setGetAddrName(String getAddrName) {
        this.getAddrName = getAddrName;
    }

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }

    public Integer getBackAddrId() {
        return backAddrId;
    }

    public void setBackAddrId(Integer backAddrId) {
        this.backAddrId = backAddrId;
    }

    public String getBackAddrName() {
        return backAddrName;
    }

    public void setBackAddrName(String backAddrName) {
        this.backAddrName = backAddrName;
    }

    public String getBackTime() {
        return backTime;
    }

    public void setBackTime(String backTime) {
        this.backTime = backTime;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getGetGoodsDate() {
        return getGoodsDate;
    }

    public void setGetGoodsDate(String getGoodsDate) {
        this.getGoodsDate = getGoodsDate;
    }

    public List<OrderDetailProptyBean> getOrderDetailProptyList() {
        return orderDetailProptyList;
    }

    public void setOrderDetailProptyList(List<OrderDetailProptyBean> orderDetailProptyList) {
        this.orderDetailProptyList = orderDetailProptyList;
    }

    public Integer getTimeGoodsId() {
        return timeGoodsId;
    }

    public void setTimeGoodsId(Integer timeGoodsId) {
        this.timeGoodsId = timeGoodsId;
    }

    public String getGoodsSpecNameStr() {
        return goodsSpecNameStr;
    }

    public void setGoodsSpecNameStr(String goodsSpecNameStr) {
        this.goodsSpecNameStr = goodsSpecNameStr;
    }

    public Integer getIsTicket() {
        return isTicket;
    }

    public void setIsTicket(Integer isTicket) {
        this.isTicket = isTicket;
    }

    public Integer getIsTimeGoods() {
        return isTimeGoods;
    }

    public void setIsTimeGoods(Integer isTimeGoods) {
        this.isTimeGoods = isTimeGoods;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(Integer isSingle) {
        this.isSingle = isSingle;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getTimePrice() {
        return timePrice;
    }

    public void setTimePrice(BigDecimal timePrice) {
        this.timePrice = timePrice;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getTrueMoney() {
        return trueMoney;
    }

    public void setTrueMoney(BigDecimal trueMoney) {
        this.trueMoney = trueMoney;
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

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public BigDecimal getDepositPrice() {
        return depositPrice;
    }

    public void setDepositPrice(BigDecimal depositPrice) {
        this.depositPrice = depositPrice;
    }
}
