package com.yufan.pojo;

import java.util.Date;

/**
 * TbExchangeJurisdiction entity. @author MyEclipse Persistence Tools
 */

public class TbExchangeJurisdiction implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer tikcetId;
    private Integer partnersId;
    private Date startTime;
    private Date endTime;
    private Integer validDate;
    private String createman;
    private Date createtime;
    private Integer isMakeSure;

    // Constructors

    /**
     * default constructor
     */
    public TbExchangeJurisdiction() {
    }

    /**
     * full constructor
     */
    public TbExchangeJurisdiction(Integer tikcetId, Integer partnersId,
                                  Date startTime, Date endTime, Integer validDate, String createman,
                                  Date createtime, Integer isMakeSure) {
        this.tikcetId = tikcetId;
        this.partnersId = partnersId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.validDate = validDate;
        this.createman = createman;
        this.createtime = createtime;
        this.isMakeSure = isMakeSure;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTikcetId() {
        return this.tikcetId;
    }

    public void setTikcetId(Integer tikcetId) {
        this.tikcetId = tikcetId;
    }

    public Integer getPartnersId() {
        return this.partnersId;
    }

    public void setPartnersId(Integer partnersId) {
        this.partnersId = partnersId;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getValidDate() {
        return this.validDate;
    }

    public void setValidDate(Integer validDate) {
        this.validDate = validDate;
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

    public Integer getIsMakeSure() {
        return this.isMakeSure;
    }

    public void setIsMakeSure(Integer isMakeSure) {
        this.isMakeSure = isMakeSure;
    }

}