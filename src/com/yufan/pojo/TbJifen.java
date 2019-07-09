package com.yufan.pojo;

import java.util.Date;

/**
 * TbJifen entity. @author MyEclipse Persistence Tools
 */

public class TbJifen implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer taskId;
    private Integer isInout;
    private Integer jifen;
    private String items;
    private Integer userId;
    private Date createtime;
    private Integer status;
    private Date lastaltertime;

    // Constructors

    /**
     * default constructor
     */
    public TbJifen() {
    }

    /**
     * full constructor
     */
    public TbJifen(Integer taskId, Integer isInout, Integer jifen,
                   String items, Integer userId, Date createtime, Integer status,
                   Date lastaltertime) {
        this.taskId = taskId;
        this.isInout = isInout;
        this.jifen = jifen;
        this.items = items;
        this.userId = userId;
        this.createtime = createtime;
        this.status = status;
        this.lastaltertime = lastaltertime;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return this.taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getIsInout() {
        return this.isInout;
    }

    public void setIsInout(Integer isInout) {
        this.isInout = isInout;
    }

    public Integer getJifen() {
        return this.jifen;
    }

    public void setJifen(Integer jifen) {
        this.jifen = jifen;
    }

    public String getItems() {
        return this.items;
    }

    public void setItems(String items) {
        this.items = items;
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

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getLastaltertime() {
        return this.lastaltertime;
    }

    public void setLastaltertime(Date lastaltertime) {
        this.lastaltertime = lastaltertime;
    }

}