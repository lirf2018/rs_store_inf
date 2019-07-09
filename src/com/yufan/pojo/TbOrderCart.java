package com.yufan.pojo;

import java.util.Date;

/**
 * TbOrderCart entity. @author MyEclipse Persistence Tools
 */

public class TbOrderCart implements java.io.Serializable {

    // Fields

    private Integer cartId;
    private Integer userId;
    private Integer goodsId;
    private String goodsName;
    private String goodsImg;
    private String goodsSpec;
    private String goodsSpecName;
    private Integer goodsCount;
    private double goodsPrice;
    private double trueMoney;
    private Integer shopId;
    private String shopName;
    private Integer partnersId;
    private String partnersName;
    private Integer payType;
    private Date createtime;
    private Integer status;
    private String remark;
    private Date lastaltertime;
    private String goodsSpecNameStr;

    // Constructors

    /**
     * default constructor
     */
    public TbOrderCart() {
    }

    /**
     * full constructor
     */
    public TbOrderCart(Integer userId, Integer goodsId, String goodsName,
                       String goodsImg, String goodsSpec, String goodsSpecName,
                       Integer goodsCount, double goodsPrice, double trueMoney,
                       Integer shopId, String shopName, Integer partnersId,
                       String partnersName, Integer payType, Date createtime,
                       Integer status, String remark, Date lastaltertime,
                       String goodsSpecNameStr) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsImg = goodsImg;
        this.goodsSpec = goodsSpec;
        this.goodsSpecName = goodsSpecName;
        this.goodsCount = goodsCount;
        this.goodsPrice = goodsPrice;
        this.trueMoney = trueMoney;
        this.shopId = shopId;
        this.shopName = shopName;
        this.partnersId = partnersId;
        this.partnersName = partnersName;
        this.payType = payType;
        this.createtime = createtime;
        this.status = status;
        this.remark = remark;
        this.lastaltertime = lastaltertime;
        this.goodsSpecNameStr = goodsSpecNameStr;
    }

    // Property accessors

    public Integer getCartId() {
        return this.cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImg() {
        return this.goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsSpec() {
        return this.goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public String getGoodsSpecName() {
        return this.goodsSpecName;
    }

    public void setGoodsSpecName(String goodsSpecName) {
        this.goodsSpecName = goodsSpecName;
    }

    public Integer getGoodsCount() {
        return this.goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public double getGoodsPrice() {
        return this.goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public double getTrueMoney() {
        return this.trueMoney;
    }

    public void setTrueMoney(double trueMoney) {
        this.trueMoney = trueMoney;
    }

    public Integer getShopId() {
        return this.shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return this.shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    public Integer getPayType() {
        return this.payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getLastaltertime() {
        return this.lastaltertime;
    }

    public void setLastaltertime(Date lastaltertime) {
        this.lastaltertime = lastaltertime;
    }

    public String getGoodsSpecNameStr() {
        return this.goodsSpecNameStr;
    }

    public void setGoodsSpecNameStr(String goodsSpecNameStr) {
        this.goodsSpecNameStr = goodsSpecNameStr;
    }

}