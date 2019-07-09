package com.yufan.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 创建人: lirf
 * 创建时间:  2018/11/13 9:54
 * 功能介绍:
 */
public class TbGoods {
    private Integer goodsId;
    private String goodsName;
    private String title;
    private String goodsImg;
    private BigDecimal trueMoney;
    private BigDecimal nowMoney;
    private String intro;
    private Integer shopId;
    private Integer isYuding;
    private Integer getWay;
    private Integer isInvoice;
    private Integer isPutaway;
    private Integer weight;
    private Integer classifyId;
    private Integer areaId;
    private Integer property;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer validDate;
    private String goodsCode;
    private String goodsUnit;
    private Integer isSingle;
    private Integer goodsNum;
    private Integer isReturn;
    private Integer ticketId;
    private String createman;
    private Timestamp createtime;
    private Timestamp lastaltertime;
    private String lastalterman;
    private Integer status;
    private String remark;
    private Integer goodsType;
    private Integer isPayOnline;
    private Integer partnersId;
    private BigDecimal depositMoney;
    private String peisongZcDesc;
    private String peisongPeiDesc;
    private BigDecimal purchasePrice;
    private Integer isTimeGoods;
    private Integer limitNum;
    private Integer limitWay;
    private Timestamp limitBeginTime;
    private Integer leve1Id;
    private BigDecimal advancePrice;
    private String barCode;
    private String barCodeShop;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getIsYuding() {
        return isYuding;
    }

    public void setIsYuding(Integer isYuding) {
        this.isYuding = isYuding;
    }

    public Integer getGetWay() {
        return getWay;
    }

    public void setGetWay(Integer getWay) {
        this.getWay = getWay;
    }

    public Integer getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(Integer isInvoice) {
        this.isInvoice = isInvoice;
    }

    public Integer getIsPutaway() {
        return isPutaway;
    }

    public void setIsPutaway(Integer isPutaway) {
        this.isPutaway = isPutaway;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Integer getValidDate() {
        return validDate;
    }

    public void setValidDate(Integer validDate) {
        this.validDate = validDate;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public Integer getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(Integer isSingle) {
        this.isSingle = isSingle;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Integer getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(Integer isReturn) {
        this.isReturn = isReturn;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getCreateman() {
        return createman;
    }

    public void setCreateman(String createman) {
        this.createman = createman;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Timestamp getLastaltertime() {
        return lastaltertime;
    }

    public void setLastaltertime(Timestamp lastaltertime) {
        this.lastaltertime = lastaltertime;
    }

    public String getLastalterman() {
        return lastalterman;
    }

    public void setLastalterman(String lastalterman) {
        this.lastalterman = lastalterman;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getIsPayOnline() {
        return isPayOnline;
    }

    public void setIsPayOnline(Integer isPayOnline) {
        this.isPayOnline = isPayOnline;
    }

    public Integer getPartnersId() {
        return partnersId;
    }

    public void setPartnersId(Integer partnersId) {
        this.partnersId = partnersId;
    }

    public BigDecimal getDepositMoney() {
        return depositMoney;
    }

    public void setDepositMoney(BigDecimal depositMoney) {
        this.depositMoney = depositMoney;
    }

    public String getPeisongZcDesc() {
        return peisongZcDesc;
    }

    public void setPeisongZcDesc(String peisongZcDesc) {
        this.peisongZcDesc = peisongZcDesc;
    }

    public String getPeisongPeiDesc() {
        return peisongPeiDesc;
    }

    public void setPeisongPeiDesc(String peisongPeiDesc) {
        this.peisongPeiDesc = peisongPeiDesc;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getIsTimeGoods() {
        return isTimeGoods;
    }

    public void setIsTimeGoods(Integer isTimeGoods) {
        this.isTimeGoods = isTimeGoods;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public Integer getLimitWay() {
        return limitWay;
    }

    public void setLimitWay(Integer limitWay) {
        this.limitWay = limitWay;
    }

    public Timestamp getLimitBeginTime() {
        return limitBeginTime;
    }

    public void setLimitBeginTime(Timestamp limitBeginTime) {
        this.limitBeginTime = limitBeginTime;
    }

    public Integer getLeve1Id() {
        return leve1Id;
    }

    public void setLeve1Id(Integer leve1Id) {
        this.leve1Id = leve1Id;
    }

    public BigDecimal getAdvancePrice() {
        return advancePrice;
    }

    public void setAdvancePrice(BigDecimal advancePrice) {
        this.advancePrice = advancePrice;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBarCodeShop() {
        return barCodeShop;
    }

    public void setBarCodeShop(String barCodeShop) {
        this.barCodeShop = barCodeShop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbGoods goods = (TbGoods) o;
        return Objects.equals(goodsId, goods.goodsId) &&
                Objects.equals(goodsName, goods.goodsName) &&
                Objects.equals(title, goods.title) &&
                Objects.equals(goodsImg, goods.goodsImg) &&
                Objects.equals(trueMoney, goods.trueMoney) &&
                Objects.equals(nowMoney, goods.nowMoney) &&
                Objects.equals(intro, goods.intro) &&
                Objects.equals(shopId, goods.shopId) &&
                Objects.equals(isYuding, goods.isYuding) &&
                Objects.equals(getWay, goods.getWay) &&
                Objects.equals(isInvoice, goods.isInvoice) &&
                Objects.equals(isPutaway, goods.isPutaway) &&
                Objects.equals(weight, goods.weight) &&
                Objects.equals(classifyId, goods.classifyId) &&
                Objects.equals(areaId, goods.areaId) &&
                Objects.equals(property, goods.property) &&
                Objects.equals(startTime, goods.startTime) &&
                Objects.equals(endTime, goods.endTime) &&
                Objects.equals(validDate, goods.validDate) &&
                Objects.equals(goodsCode, goods.goodsCode) &&
                Objects.equals(goodsUnit, goods.goodsUnit) &&
                Objects.equals(isSingle, goods.isSingle) &&
                Objects.equals(goodsNum, goods.goodsNum) &&
                Objects.equals(isReturn, goods.isReturn) &&
                Objects.equals(ticketId, goods.ticketId) &&
                Objects.equals(createman, goods.createman) &&
                Objects.equals(createtime, goods.createtime) &&
                Objects.equals(lastaltertime, goods.lastaltertime) &&
                Objects.equals(lastalterman, goods.lastalterman) &&
                Objects.equals(status, goods.status) &&
                Objects.equals(remark, goods.remark) &&
                Objects.equals(goodsType, goods.goodsType) &&
                Objects.equals(isPayOnline, goods.isPayOnline) &&
                Objects.equals(partnersId, goods.partnersId) &&
                Objects.equals(depositMoney, goods.depositMoney) &&
                Objects.equals(peisongZcDesc, goods.peisongZcDesc) &&
                Objects.equals(peisongPeiDesc, goods.peisongPeiDesc) &&
                Objects.equals(purchasePrice, goods.purchasePrice) &&
                Objects.equals(isTimeGoods, goods.isTimeGoods) &&
                Objects.equals(limitNum, goods.limitNum) &&
                Objects.equals(limitWay, goods.limitWay) &&
                Objects.equals(limitBeginTime, goods.limitBeginTime) &&
                Objects.equals(leve1Id, goods.leve1Id) &&
                Objects.equals(advancePrice, goods.advancePrice) &&
                Objects.equals(barCode, goods.barCode) &&
                Objects.equals(barCodeShop, goods.barCodeShop);
    }

    @Override
    public int hashCode() {

        return Objects.hash(goodsId, goodsName, title, goodsImg, trueMoney, nowMoney, intro, shopId, isYuding, getWay, isInvoice, isPutaway, weight, classifyId, areaId, property, startTime, endTime, validDate, goodsCode, goodsUnit, isSingle, goodsNum, isReturn, ticketId, createman, createtime, lastaltertime, lastalterman, status, remark, goodsType, isPayOnline, partnersId, depositMoney, peisongZcDesc, peisongPeiDesc, purchasePrice, isTimeGoods, limitNum, limitWay, limitBeginTime, leve1Id, advancePrice, barCode, barCodeShop);
    }
}
