package com.yufan.pojo;

import java.util.Date;

/**
 * TbShop entity. @author MyEclipse Persistence Tools
 */

public class TbShop implements java.io.Serializable {

    // Fields

    private Integer shopId;
    private String shopName;
    private String shopLogo;
    private String shopTel1;
    private String shopTel2;
    private String shopTel3;
    private String shopTel4;
    private String shopLng;
    private String shopLat;
    private Integer weight;
    private String introduce;
    private String toway;
    private Integer adminId;
    private String address;
    private Integer areaId;
    private double deposit;
    private Date depositTime;
    private double shopMoney;
    private String createman;
    private Date createtime;
    private Date lastaltertime;
    private String lastalterman;
    private Integer status;
    private String remark;
    private Date enterStartTime;
    private Date enterEndTime;
    private Integer isOutShop;

    // Constructors

    /**
     * default constructor
     */
    public TbShop() {
    }

    /**
     * full constructor
     */
    public TbShop(String shopName, String shopLogo, String shopTel1,
                  String shopTel2, String shopTel3, String shopTel4, String shopLng,
                  String shopLat, Integer weight, String introduce, String toway,
                  Integer adminId, String address, Integer areaId, double deposit,
                  Date depositTime, double shopMoney, String createman,
                  Date createtime, Date lastaltertime, String lastalterman,
                  Integer status, String remark, Date enterStartTime,
                  Date enterEndTime, Integer isOutShop) {
        this.shopName = shopName;
        this.shopLogo = shopLogo;
        this.shopTel1 = shopTel1;
        this.shopTel2 = shopTel2;
        this.shopTel3 = shopTel3;
        this.shopTel4 = shopTel4;
        this.shopLng = shopLng;
        this.shopLat = shopLat;
        this.weight = weight;
        this.introduce = introduce;
        this.toway = toway;
        this.adminId = adminId;
        this.address = address;
        this.areaId = areaId;
        this.deposit = deposit;
        this.depositTime = depositTime;
        this.shopMoney = shopMoney;
        this.createman = createman;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalterman = lastalterman;
        this.status = status;
        this.remark = remark;
        this.enterStartTime = enterStartTime;
        this.enterEndTime = enterEndTime;
        this.isOutShop = isOutShop;
    }

    // Property accessors

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

    public String getShopLogo() {
        return this.shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getShopTel1() {
        return this.shopTel1;
    }

    public void setShopTel1(String shopTel1) {
        this.shopTel1 = shopTel1;
    }

    public String getShopTel2() {
        return this.shopTel2;
    }

    public void setShopTel2(String shopTel2) {
        this.shopTel2 = shopTel2;
    }

    public String getShopTel3() {
        return this.shopTel3;
    }

    public void setShopTel3(String shopTel3) {
        this.shopTel3 = shopTel3;
    }

    public String getShopTel4() {
        return this.shopTel4;
    }

    public void setShopTel4(String shopTel4) {
        this.shopTel4 = shopTel4;
    }

    public String getShopLng() {
        return this.shopLng;
    }

    public void setShopLng(String shopLng) {
        this.shopLng = shopLng;
    }

    public String getShopLat() {
        return this.shopLat;
    }

    public void setShopLat(String shopLat) {
        this.shopLat = shopLat;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getIntroduce() {
        return this.introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getToway() {
        return this.toway;
    }

    public void setToway(String toway) {
        this.toway = toway;
    }

    public Integer getAdminId() {
        return this.adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAreaId() {
        return this.areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public double getDeposit() {
        return this.deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public Date getDepositTime() {
        return this.depositTime;
    }

    public void setDepositTime(Date depositTime) {
        this.depositTime = depositTime;
    }

    public double getShopMoney() {
        return this.shopMoney;
    }

    public void setShopMoney(double shopMoney) {
        this.shopMoney = shopMoney;
    }

    public String getCreateman() {
        return this.createman;
    }

    public void setCreateman(String createman) {
        this.createman = createman;
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

    public Date getEnterStartTime() {
        return this.enterStartTime;
    }

    public void setEnterStartTime(Date enterStartTime) {
        this.enterStartTime = enterStartTime;
    }

    public Date getEnterEndTime() {
        return this.enterEndTime;
    }

    public void setEnterEndTime(Date enterEndTime) {
        this.enterEndTime = enterEndTime;
    }

    public Integer getIsOutShop() {
        return this.isOutShop;
    }

    public void setIsOutShop(Integer isOutShop) {
        this.isOutShop = isOutShop;
    }

}