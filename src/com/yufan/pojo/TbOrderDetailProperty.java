package com.yufan.pojo;

import java.util.Date;

/**
 * TbOrderDetailProperty entity. @author MyEclipse Persistence Tools
 */

public class TbOrderDetailProperty implements java.io.Serializable {

    // Fields

    private Integer propertyId;
    private Integer orderId;
    private Integer detailId;
    private String propertyKey;
    private String propertyValue;
    private Date createtime;
    private Date edittime;
    private String remark;

    // Constructors

    /**
     * default constructor
     */
    public TbOrderDetailProperty() {
    }

    /**
     * full constructor
     */
    public TbOrderDetailProperty(Integer orderId, Integer detailId,
                                 String propertyKey, String propertyValue, Date createtime,
                                 Date edittime, String remark) {
        this.orderId = orderId;
        this.detailId = detailId;
        this.propertyKey = propertyKey;
        this.propertyValue = propertyValue;
        this.createtime = createtime;
        this.edittime = edittime;
        this.remark = remark;
    }

    // Property accessors

    public Integer getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDetailId() {
        return this.detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public String getPropertyKey() {
        return this.propertyKey;
    }

    public void setPropertyKey(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    public String getPropertyValue() {
        return this.propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getEdittime() {
        return this.edittime;
    }

    public void setEdittime(Date edittime) {
        this.edittime = edittime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}