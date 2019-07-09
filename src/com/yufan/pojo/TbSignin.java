package com.yufan.pojo;

import java.util.Date;

/**
 * TbSignin entity. @author MyEclipse Persistence Tools
 */

public class TbSignin implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer userId;
    private Date createtime;

    // Constructors

    /**
     * default constructor
     */
    public TbSignin() {
    }

    /**
     * full constructor
     */
    public TbSignin(Integer userId, Date createtime) {
        this.userId = userId;
        this.createtime = createtime;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

}