package com.yufan.pojo;

import java.util.Date;

/**
 * TbPropsValue entity. @author MyEclipse Persistence Tools
 */

public class TbPropsValue implements java.io.Serializable {

    // Fields

    private Integer valueId;
    private Integer propId;
    private String valueName;
    private Integer categoryId;
    private String outeId;
    private String value;
    private Integer short_;
    private String createman;
    private Date createtime;
    private Date lastaltertime;
    private String lastalterman;
    private Integer status;
    private String remark;
    private String valueImg;

    // Constructors

    /**
     * default constructor
     */
    public TbPropsValue() {
    }

    /**
     * full constructor
     */
    public TbPropsValue(Integer propId, String valueName, Integer categoryId,
                        String outeId, String value, Integer short_, String createman,
                        Date createtime, Date lastaltertime, String lastalterman,
                        Integer status, String remark, String valueImg) {
        this.propId = propId;
        this.valueName = valueName;
        this.categoryId = categoryId;
        this.outeId = outeId;
        this.value = value;
        this.short_ = short_;
        this.createman = createman;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalterman = lastalterman;
        this.status = status;
        this.remark = remark;
        this.valueImg = valueImg;
    }

    // Property accessors

    public Integer getValueId() {
        return this.valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    public Integer getPropId() {
        return this.propId;
    }

    public void setPropId(Integer propId) {
        this.propId = propId;
    }

    public String getValueName() {
        return this.valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
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

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getShort_() {
        return this.short_;
    }

    public void setShort_(Integer short_) {
        this.short_ = short_;
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

    public String getValueImg() {
        return this.valueImg;
    }

    public void setValueImg(String valueImg) {
        this.valueImg = valueImg;
    }

}