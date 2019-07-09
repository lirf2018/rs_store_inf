package com.yufan.pojo;

import java.util.Date;

/**
 * TbComplain entity. @author MyEclipse Persistence Tools
 */

public class TbComplain implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer userId;
    private String information;
    private String contents;
    private Integer status;
    private Integer isRead;
    private Date createtime;
    private Date lastaltertime;
    private String lastalterman;

    // Constructors

    /**
     * default constructor
     */
    public TbComplain() {
    }

    /**
     * full constructor
     */
    public TbComplain(Integer userId, String information, String contents,
                      Integer status, Integer isRead, Date createtime,
                      Date lastaltertime, String lastalterman) {
        this.userId = userId;
        this.information = information;
        this.contents = contents;
        this.status = status;
        this.isRead = isRead;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalterman = lastalterman;
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

    public String getInformation() {
        return this.information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getContents() {
        return this.contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsRead() {
        return this.isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
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

}