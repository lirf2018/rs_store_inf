package com.yufan.pojo;

import java.util.Date;

/**
 * TbCategory entity. @author MyEclipse Persistence Tools
 */

public class TbCategory implements java.io.Serializable {

    // Fields

    private Integer categoryId;
    private String categoryName;
    private Integer parentId;
    private Integer isParent;
    private Integer short_;
    private String outeId;
    private String categoryImg;
    private String categoryCode;
    private Integer isShow;
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
    public TbCategory() {
    }

    /**
     * full constructor
     */
    public TbCategory(String categoryName, Integer parentId, Integer isParent,
                      Integer short_, String outeId, String categoryImg,
                      String categoryCode, Integer isShow, String createman,
                      Date createtime, Date lastaltertime, String lastalterman,
                      Integer status, String remark) {
        this.categoryName = categoryName;
        this.parentId = parentId;
        this.isParent = isParent;
        this.short_ = short_;
        this.outeId = outeId;
        this.categoryImg = categoryImg;
        this.categoryCode = categoryCode;
        this.isShow = isShow;
        this.createman = createman;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalterman = lastalterman;
        this.status = status;
        this.remark = remark;
    }

    // Property accessors

    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getIsParent() {
        return this.isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    public Integer getShort_() {
        return this.short_;
    }

    public void setShort_(Integer short_) {
        this.short_ = short_;
    }

    public String getOuteId() {
        return this.outeId;
    }

    public void setOuteId(String outeId) {
        this.outeId = outeId;
    }

    public String getCategoryImg() {
        return this.categoryImg;
    }

    public void setCategoryImg(String categoryImg) {
        this.categoryImg = categoryImg;
    }

    public String getCategoryCode() {
        return this.categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Integer getIsShow() {
        return this.isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
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