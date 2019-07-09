package com.yufan.pojo;

import java.util.Date;

/**
 * TbDistributionAddr entity. @author MyEclipse Persistence Tools
 */

public class TbDistributionAddr implements java.io.Serializable {

    // Fields

    private Integer id;
    private String detailAddr;
    private String responsibleMan;
    private String responsiblePhone;
    private double freight;
    private String sortChar;
    private String addrPrefix;
    private String createman;
    private Date createtime;
    private Date lastaltertime;
    private String lastalterman;
    private Integer status;
    private String remark;
    private Integer addrShort;
    private Integer addrType;
    private String addrDesc;
    private String addrName;

    // Constructors

    /**
     * default constructor
     */
    public TbDistributionAddr() {
    }

    /**
     * full constructor
     */
    public TbDistributionAddr(String detailAddr, String responsibleMan,
                              String responsiblePhone, double freight, String sortChar,
                              String addrPrefix, String createman, Date createtime,
                              Date lastaltertime, String lastalterman, Integer status,
                              String remark, Integer addrShort, Integer addrType,
                              String addrDesc, String addrName) {
        this.detailAddr = detailAddr;
        this.responsibleMan = responsibleMan;
        this.responsiblePhone = responsiblePhone;
        this.freight = freight;
        this.sortChar = sortChar;
        this.addrPrefix = addrPrefix;
        this.createman = createman;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalterman = lastalterman;
        this.status = status;
        this.remark = remark;
        this.addrShort = addrShort;
        this.addrType = addrType;
        this.addrDesc = addrDesc;
        this.addrName = addrName;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetailAddr() {
        return this.detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr;
    }

    public String getResponsibleMan() {
        return this.responsibleMan;
    }

    public void setResponsibleMan(String responsibleMan) {
        this.responsibleMan = responsibleMan;
    }

    public String getResponsiblePhone() {
        return this.responsiblePhone;
    }

    public void setResponsiblePhone(String responsiblePhone) {
        this.responsiblePhone = responsiblePhone;
    }

    public double getFreight() {
        return this.freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public String getSortChar() {
        return this.sortChar;
    }

    public void setSortChar(String sortChar) {
        this.sortChar = sortChar;
    }

    public String getAddrPrefix() {
        return this.addrPrefix;
    }

    public void setAddrPrefix(String addrPrefix) {
        this.addrPrefix = addrPrefix;
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

    public Integer getAddrShort() {
        return this.addrShort;
    }

    public void setAddrShort(Integer addrShort) {
        this.addrShort = addrShort;
    }

    public Integer getAddrType() {
        return this.addrType;
    }

    public void setAddrType(Integer addrType) {
        this.addrType = addrType;
    }

    public String getAddrDesc() {
        return this.addrDesc;
    }

    public void setAddrDesc(String addrDesc) {
        this.addrDesc = addrDesc;
    }

    public String getAddrName() {
        return this.addrName;
    }

    public void setAddrName(String addrName) {
        this.addrName = addrName;
    }

}