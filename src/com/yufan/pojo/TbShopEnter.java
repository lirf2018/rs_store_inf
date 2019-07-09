package com.yufan.pojo;

import java.util.Date;

/**
 * TbShopEnter entity. @author MyEclipse Persistence Tools
 */

public class TbShopEnter implements java.io.Serializable {

    // Fields

    private Integer enterId;
    private Integer shopId;
    private Integer adminId;
    private double enterMoney;
    private Date enterStartTime;
    private Date enterEndTime;
    private String createman;
    private String createtime;
    private Date lastaltertime;
    private String lastalterman;
    private Integer status;
    private String remark;

    // Constructors

    /**
     * default constructor
     */
    public TbShopEnter() {
    }

    /**
     * full constructor
     */
    public TbShopEnter(Integer shopId, Integer adminId, double enterMoney,
                       Date enterStartTime, Date enterEndTime, String createman,
                       String createtime, Date lastaltertime, String lastalterman,
                       Integer status, String remark) {
        this.shopId = shopId;
        this.adminId = adminId;
        this.enterMoney = enterMoney;
        this.enterStartTime = enterStartTime;
        this.enterEndTime = enterEndTime;
        this.createman = createman;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalterman = lastalterman;
        this.status = status;
        this.remark = remark;
    }

    // Property accessors

    public Integer getEnterId() {
        return this.enterId;
    }

    public void setEnterId(Integer enterId) {
        this.enterId = enterId;
    }

    public Integer getShopId() {
        return this.shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getAdminId() {
        return this.adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public double getEnterMoney() {
        return this.enterMoney;
    }

    public void setEnterMoney(double enterMoney) {
        this.enterMoney = enterMoney;
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

    public String getCreateman() {
        return this.createman;
    }

    public void setCreateman(String createman) {
        this.createman = createman;
    }

    public String getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(String createtime) {
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

}