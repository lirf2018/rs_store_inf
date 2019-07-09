package com.yufan.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 创建人: lirf
 * 创建时间:  2019/3/15 9:53
 * 功能介绍:
 */
public class TbGoodsSku {
    private Integer skuId;
    private Integer goodsId;
    private String skuName;
    private BigDecimal trueMoney;
    private BigDecimal nowMoney;
    private String skuCode;
    private String propCode;
    private Integer skuNum;
    private Timestamp createtime;
    private String skuImg;
    private BigDecimal purchasePrice;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public BigDecimal getTrueMoney() {
        return trueMoney;
    }

    public void setTrueMoney(BigDecimal trueMoney) {
        this.trueMoney = trueMoney;
    }

    public BigDecimal getNowMoney() {
        return nowMoney;
    }

    public void setNowMoney(BigDecimal nowMoney) {
        this.nowMoney = nowMoney;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getPropCode() {
        return propCode;
    }

    public void setPropCode(String propCode) {
        this.propCode = propCode;
    }

    public Integer getSkuNum() {
        return skuNum;
    }

    public void setSkuNum(Integer skuNum) {
        this.skuNum = skuNum;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getSkuImg() {
        return skuImg;
    }

    public void setSkuImg(String skuImg) {
        this.skuImg = skuImg;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbGoodsSku goodsSku = (TbGoodsSku) o;
        return Objects.equals(skuId, goodsSku.skuId) &&
                Objects.equals(goodsId, goodsSku.goodsId) &&
                Objects.equals(skuName, goodsSku.skuName) &&
                Objects.equals(trueMoney, goodsSku.trueMoney) &&
                Objects.equals(nowMoney, goodsSku.nowMoney) &&
                Objects.equals(skuCode, goodsSku.skuCode) &&
                Objects.equals(propCode, goodsSku.propCode) &&
                Objects.equals(skuNum, goodsSku.skuNum) &&
                Objects.equals(createtime, goodsSku.createtime) &&
                Objects.equals(skuImg, goodsSku.skuImg) &&
                Objects.equals(purchasePrice, goodsSku.purchasePrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(skuId, goodsId, skuName, trueMoney, nowMoney, skuCode, propCode, skuNum, createtime, skuImg, purchasePrice);
    }
}
