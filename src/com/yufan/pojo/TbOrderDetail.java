package com.yufan.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TbOrderDetail {
    private Integer detailId;
    private Integer orderId;
    private Integer goodsId;
    private String goodsName;
    private String goodsSpec;
    private String goodsSpecName;
    private Integer goodsCount;
    private BigDecimal saleMoney;
    private BigDecimal goodsTrueMoney;
    private BigDecimal goodsPurchasePrice;
    private BigDecimal timePrice;
    private BigDecimal depositPrice;
    private Integer shopId;
    private String shopName;
    private Integer partnersId;
    private String partnersName;
    private Integer getAddrId;
    private String getAddrName;
    private Timestamp getTime;
    private Integer backAddrId;
    private String backAddrName;
    private Timestamp backTime;
    private Integer detailStatus;
    private Integer isTicket;
    private String ticketJson;
    private Timestamp createtime;
    private Timestamp lastaltertime;
    private String lastalterman;
    private Integer status;
    private String remark;
    private String goodsImg;
    private Integer cartId;
    private Timestamp getGoodsDate;
    private Integer timeGoodsId;
    private String goodsSpecNameStr;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

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

    public BigDecimal getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(BigDecimal saleMoney) {
        this.saleMoney = saleMoney;
    }

    public BigDecimal getGoodsTrueMoney() {
        return goodsTrueMoney;
    }

    public void setGoodsTrueMoney(BigDecimal goodsTrueMoney) {
        this.goodsTrueMoney = goodsTrueMoney;
    }

    public BigDecimal getGoodsPurchasePrice() {
        return goodsPurchasePrice;
    }

    public void setGoodsPurchasePrice(BigDecimal goodsPurchasePrice) {
        this.goodsPurchasePrice = goodsPurchasePrice;
    }

    public BigDecimal getTimePrice() {
        return timePrice;
    }

    public void setTimePrice(BigDecimal timePrice) {
        this.timePrice = timePrice;
    }

    public BigDecimal getDepositPrice() {
        return depositPrice;
    }

    public void setDepositPrice(BigDecimal depositPrice) {
        this.depositPrice = depositPrice;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    public Timestamp getGetTime() {
        return getTime;
    }

    public void setGetTime(Timestamp getTime) {
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

    public Timestamp getBackTime() {
        return backTime;
    }

    public void setBackTime(Timestamp backTime) {
        this.backTime = backTime;
    }

    public Integer getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(Integer detailStatus) {
        this.detailStatus = detailStatus;
    }

    public Integer getIsTicket() {
        return isTicket;
    }

    public void setIsTicket(Integer isTicket) {
        this.isTicket = isTicket;
    }

    public String getTicketJson() {
        return ticketJson;
    }

    public void setTicketJson(String ticketJson) {
        this.ticketJson = ticketJson;
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

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Timestamp getGetGoodsDate() {
        return getGoodsDate;
    }

    public void setGetGoodsDate(Timestamp getGoodsDate) {
        this.getGoodsDate = getGoodsDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbOrderDetail that = (TbOrderDetail) o;

        if (detailId != null ? !detailId.equals(that.detailId) : that.detailId != null) return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (goodsId != null ? !goodsId.equals(that.goodsId) : that.goodsId != null) return false;
        if (goodsName != null ? !goodsName.equals(that.goodsName) : that.goodsName != null) return false;
        if (goodsSpec != null ? !goodsSpec.equals(that.goodsSpec) : that.goodsSpec != null) return false;
        if (goodsSpecName != null ? !goodsSpecName.equals(that.goodsSpecName) : that.goodsSpecName != null)
            return false;
        if (goodsCount != null ? !goodsCount.equals(that.goodsCount) : that.goodsCount != null) return false;
        if (saleMoney != null ? !saleMoney.equals(that.saleMoney) : that.saleMoney != null) return false;
        if (goodsTrueMoney != null ? !goodsTrueMoney.equals(that.goodsTrueMoney) : that.goodsTrueMoney != null)
            return false;
        if (goodsPurchasePrice != null ? !goodsPurchasePrice.equals(that.goodsPurchasePrice) : that.goodsPurchasePrice != null)
            return false;
        if (timePrice != null ? !timePrice.equals(that.timePrice) : that.timePrice != null) return false;
        if (depositPrice != null ? !depositPrice.equals(that.depositPrice) : that.depositPrice != null) return false;
        if (shopId != null ? !shopId.equals(that.shopId) : that.shopId != null) return false;
        if (shopName != null ? !shopName.equals(that.shopName) : that.shopName != null) return false;
        if (partnersId != null ? !partnersId.equals(that.partnersId) : that.partnersId != null) return false;
        if (partnersName != null ? !partnersName.equals(that.partnersName) : that.partnersName != null) return false;
        if (getAddrId != null ? !getAddrId.equals(that.getAddrId) : that.getAddrId != null) return false;
        if (getAddrName != null ? !getAddrName.equals(that.getAddrName) : that.getAddrName != null) return false;
        if (getTime != null ? !getTime.equals(that.getTime) : that.getTime != null) return false;
        if (backAddrId != null ? !backAddrId.equals(that.backAddrId) : that.backAddrId != null) return false;
        if (backAddrName != null ? !backAddrName.equals(that.backAddrName) : that.backAddrName != null) return false;
        if (backTime != null ? !backTime.equals(that.backTime) : that.backTime != null) return false;
        if (detailStatus != null ? !detailStatus.equals(that.detailStatus) : that.detailStatus != null) return false;
        if (isTicket != null ? !isTicket.equals(that.isTicket) : that.isTicket != null) return false;
        if (ticketJson != null ? !ticketJson.equals(that.ticketJson) : that.ticketJson != null) return false;
        if (createtime != null ? !createtime.equals(that.createtime) : that.createtime != null) return false;
        if (lastaltertime != null ? !lastaltertime.equals(that.lastaltertime) : that.lastaltertime != null)
            return false;
        if (lastalterman != null ? !lastalterman.equals(that.lastalterman) : that.lastalterman != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (goodsImg != null ? !goodsImg.equals(that.goodsImg) : that.goodsImg != null) return false;
        if (cartId != null ? !cartId.equals(that.cartId) : that.cartId != null) return false;
        if (getGoodsDate != null ? !getGoodsDate.equals(that.getGoodsDate) : that.getGoodsDate != null) return false;
        if (timeGoodsId != null ? !timeGoodsId.equals(that.timeGoodsId) : that.timeGoodsId != null) return false;
        if (goodsSpecNameStr != null ? !goodsSpecNameStr.equals(that.goodsSpecNameStr) : that.goodsSpecNameStr != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = detailId != null ? detailId.hashCode() : 0;
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (goodsId != null ? goodsId.hashCode() : 0);
        result = 31 * result + (goodsName != null ? goodsName.hashCode() : 0);
        result = 31 * result + (goodsSpec != null ? goodsSpec.hashCode() : 0);
        result = 31 * result + (goodsSpecName != null ? goodsSpecName.hashCode() : 0);
        result = 31 * result + (goodsCount != null ? goodsCount.hashCode() : 0);
        result = 31 * result + (saleMoney != null ? saleMoney.hashCode() : 0);
        result = 31 * result + (goodsTrueMoney != null ? goodsTrueMoney.hashCode() : 0);
        result = 31 * result + (goodsPurchasePrice != null ? goodsPurchasePrice.hashCode() : 0);
        result = 31 * result + (timePrice != null ? timePrice.hashCode() : 0);
        result = 31 * result + (depositPrice != null ? depositPrice.hashCode() : 0);
        result = 31 * result + (shopId != null ? shopId.hashCode() : 0);
        result = 31 * result + (shopName != null ? shopName.hashCode() : 0);
        result = 31 * result + (partnersId != null ? partnersId.hashCode() : 0);
        result = 31 * result + (partnersName != null ? partnersName.hashCode() : 0);
        result = 31 * result + (getAddrId != null ? getAddrId.hashCode() : 0);
        result = 31 * result + (getAddrName != null ? getAddrName.hashCode() : 0);
        result = 31 * result + (getTime != null ? getTime.hashCode() : 0);
        result = 31 * result + (backAddrId != null ? backAddrId.hashCode() : 0);
        result = 31 * result + (backAddrName != null ? backAddrName.hashCode() : 0);
        result = 31 * result + (backTime != null ? backTime.hashCode() : 0);
        result = 31 * result + (detailStatus != null ? detailStatus.hashCode() : 0);
        result = 31 * result + (isTicket != null ? isTicket.hashCode() : 0);
        result = 31 * result + (ticketJson != null ? ticketJson.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        result = 31 * result + (lastaltertime != null ? lastaltertime.hashCode() : 0);
        result = 31 * result + (lastalterman != null ? lastalterman.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (goodsImg != null ? goodsImg.hashCode() : 0);
        result = 31 * result + (cartId != null ? cartId.hashCode() : 0);
        result = 31 * result + (getGoodsDate != null ? getGoodsDate.hashCode() : 0);
        result = 31 * result + (timeGoodsId != null ? timeGoodsId.hashCode() : 0);
        result = 31 * result + (goodsSpecNameStr != null ? goodsSpecNameStr.hashCode() : 0);
        return result;
    }
}
