package com.yufan.pojo;

import java.util.Date;

/**
 * TbInfAccount entity. @author MyEclipse Persistence Tools
 */

public class TbInfAccount implements java.io.Serializable {

    // Fields

    private Integer id;
    private String sid;
    private String secretKey;
    private Integer status;
    private Date createDate;

    // Constructors

    /**
     * default constructor
     */
    public TbInfAccount() {
    }

    /**
     * full constructor
     */
    public TbInfAccount(String sid, String secretKey, Integer status,
                        Date createDate) {
        this.sid = sid;
        this.secretKey = secretKey;
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

    public String getSid() {
        return this.sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
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