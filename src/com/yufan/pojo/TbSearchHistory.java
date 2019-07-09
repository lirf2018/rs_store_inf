package com.yufan.pojo;

import java.util.Date;

/**
 * TbSearchHistory entity. @author MyEclipse Persistence Tools
 */

public class TbSearchHistory implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer type;
    private Integer userId;
    private String typeWord;
    private Integer status;
    private Date createtime;

    // Constructors

    /**
     * default constructor
     */
    public TbSearchHistory() {
    }

    /**
     * full constructor
     */
    public TbSearchHistory(Integer type, Integer userId, String typeWord,
                           Integer status, Date createtime) {
        this.type = type;
        this.userId = userId;
        this.typeWord = typeWord;
        this.status = status;
        this.createtime = createtime;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTypeWord() {
        return this.typeWord;
    }

    public void setTypeWord(String typeWord) {
        this.typeWord = typeWord;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

}