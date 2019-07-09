package com.yufan.pojo;

import java.util.Date;

/**
 * TbWeixinAccessToken entity. @author MyEclipse Persistence Tools
 */

public class TbWeixinAccessToken implements java.io.Serializable {

    // Fields

    private Integer id;
    private String combineCode;
    private Date expiryDate;
    private String accessToken;
    private Integer status;
    private Date createDate;

    // Constructors

    /**
     * default constructor
     */
    public TbWeixinAccessToken() {
    }

    /**
     * full constructor
     */
    public TbWeixinAccessToken(String combineCode, Date expiryDate,
                               String accessToken, Integer status, Date createDate) {
        this.combineCode = combineCode;
        this.expiryDate = expiryDate;
        this.accessToken = accessToken;
        this.status = status;
        this.createDate = createDate;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCombineCode() {
        return this.combineCode;
    }

    public void setCombineCode(String combineCode) {
        this.combineCode = combineCode;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}