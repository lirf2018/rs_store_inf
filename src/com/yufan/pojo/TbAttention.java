package com.yufan.pojo;

import java.util.Date;

/**
 * TbAttention entity. @author MyEclipse Persistence Tools
 */

public class TbAttention implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer userId;
    private Integer attentionId;
    private Integer typeId;
    private Integer status;
    private Date createtime;
    private Date lastaltertime;

    // Constructors

    /**
     * default constructor
     */
    public TbAttention() {
    }

    /**
     * full constructor
     */
    public TbAttention(Integer userId, Integer attentionId, Integer typeId,
                       Integer status, Date createtime, Date lastaltertime) {
        this.userId = userId;
        this.attentionId = attentionId;
        this.typeId = typeId;
        this.status = status;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
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

    public Integer getAttentionId() {
        return this.attentionId;
    }

    public void setAttentionId(Integer attentionId) {
        this.attentionId = attentionId;
    }

    public Integer getTypeId() {
        return this.typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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

    public Date getLastaltertime() {
        return this.lastaltertime;
    }

    public void setLastaltertime(Date lastaltertime) {
        this.lastaltertime = lastaltertime;
    }

}