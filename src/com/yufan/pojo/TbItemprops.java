package com.yufan.pojo;

import java.util.Date;

/**
 * TbItemprops entity. @author MyEclipse Persistence Tools
 */

public class TbItemprops implements java.io.Serializable {

    // Fields

    private Integer propId;
    private String propName;
    private Integer categoryId;
    private String outeId;
    private Integer isSales;
    private String showView;
    private String propImg;
    private String propCode;
    private Integer isShow;
    private Integer sort;
    private String createman;
    private Date createtime;
    private Date lastaltertime;
    private String lastalternan;
    private Integer status;
    private String remark;

    // Constructors

    /**
     * default constructor
     */
    public TbItemprops() {
    }

    /**
     * full constructor
     */
    public TbItemprops(String propName, Integer categoryId, String outeId,
                       Integer isSales, String showView, String propImg, String propCode,
                       Integer isShow, Integer sort, String createman, Date createtime,
                       Date lastaltertime, String lastalternan, Integer status,
                       String remark) {
        this.propName = propName;
        this.categoryId = categoryId;
        this.outeId = outeId;
        this.isSales = isSales;
        this.showView = showView;
        this.propImg = propImg;
        this.propCode = propCode;
        this.isShow = isShow;
        this.sort = sort;
        this.createman = createman;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalternan = lastalternan;
        this.status = status;
        this.remark = remark;
    }

    // Property accessors

    public Integer getPropId() {
        return this.propId;
    }

    public void setPropId(Integer propId) {
        this.propId = propId;
    }

    public String getPropName() {
        return this.propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getOuteId() {
        return this.outeId;
    }

    public void setOuteId(String outeId) {
        this.outeId = outeId;
    }

    public Integer getIsSales() {
        return this.isSales;
    }

    public void setIsSales(Integer isSales) {
        this.isSales = isSales;
    }

    public String getShowView() {
        return this.showView;
    }

    public void setShowView(String showView) {
        this.showView = showView;
    }

    public String getPropImg() {
        return this.propImg;
    }

    public void setPropImg(String propImg) {
        this.propImg = propImg;
    }

    public String getPropCode() {
        return this.propCode;
    }

    public void setPropCode(String propCode) {
        this.propCode = propCode;
    }

    public Integer getIsShow() {
        return this.isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public String getLastalternan() {
        return this.lastalternan;
    }

    public void setLastalternan(String lastalternan) {
        this.lastalternan = lastalternan;
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