package com.yufan.pojo;

import java.util.Date;

/**
 * TbSecretkey entity. @author MyEclipse Persistence Tools
 */

public class TbSecretkey implements java.io.Serializable {

    // Fields

    private Integer id;
    private String openId;
    private String updateKey;
    private Date passTime;
    private Date createTime;
    private Integer status;

    // Constructors

    /**
     * default constructor
     */
    public TbSecretkey() {
    }

    /**
     * full constructor
     */
    public TbSecretkey(String openId, String updateKey, Date passTime,
                       Date createTime, Integer status) {
        this.openId = openId;
        this.updateKey = updateKey;
        this.passTime = passTime;
        this.createTime = createTime;
        this.status = status;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUpdateKey() {
        return this.updateKey;
    }

    public void setUpdateKey(String updateKey) {
        this.updateKey = updateKey;
    }

    public Date getPassTime() {
        return this.passTime;
    }

    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}