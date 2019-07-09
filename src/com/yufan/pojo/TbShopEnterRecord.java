package com.yufan.pojo;

import java.util.Date;

/**
 * TbShopEnterRecord entity. @author MyEclipse Persistence Tools
 */

public class TbShopEnterRecord implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer shopId;
    private Integer adminId;
    private double money;
    private Date enterTime;
    private Integer intype;
    private String createman;
    private Date createtime;
    private Date lastaltertime;
    private String lastalterman;
    private Integer status;
    private String remark;

    // Constructors

    /**
     * default constructor
     */
    public TbShopEnterRecord() {
    }

    /**
     * full constructor
     */
    public TbShopEnterRecord(Integer shopId, Integer adminId, double money,
                             Date enterTime, Integer intype, String createman, Date createtime,
                             Date lastaltertime, String lastalterman, Integer status,
                             String remark) {
        this.shopId = shopId;
        this.adminId = adminId;
        this.money = money;
        this.enterTime = enterTime;
        this.intype = intype;
        this.createman = createman;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalterman = lastalterman;
        this.status = status;
        this.remark = remark;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public double getMoney() {
        return this.money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Date getEnterTime() {
        return this.enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public Integer getIntype() {
        return this.intype;
    }

    public void setIntype(Integer intype) {
        this.intype = intype;
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

}