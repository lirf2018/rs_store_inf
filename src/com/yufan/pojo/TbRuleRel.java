package com.yufan.pojo;

import java.util.Date;

/**
 * TbRuleRel entity. @author MyEclipse Persistence Tools
 */

public class TbRuleRel implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer relId;
    private Integer ruleId;
    private Date startTime;
    private Date endTime;
    private Integer validDate;
    private Integer relIdType;
    private Date createtime;
    private String createman;
    private Integer isMakeSure;

    // Constructors

    /**
     * default constructor
     */
    public TbRuleRel() {
    }

    /**
     * full constructor
     */
    public TbRuleRel(Integer relId, Integer ruleId, Date startTime,
                     Date endTime, Integer validDate, Integer relIdType,
                     Date createtime, String createman, Integer isMakeSure) {
        this.relId = relId;
        this.ruleId = ruleId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.validDate = validDate;
        this.relIdType = relIdType;
        this.createtime = createtime;
        this.createman = createman;
        this.isMakeSure = isMakeSure;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRelId() {
        return this.relId;
    }

    public void setRelId(Integer relId) {
        this.relId = relId;
    }

    public Integer getRuleId() {
        return this.ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
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

    public Integer getRelIdType() {
        return this.relIdType;
    }

    public void setRelIdType(Integer relIdType) {
        this.relIdType = relIdType;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateman() {
        return this.createman;
    }

    public void setCreateman(String createman) {
        this.createman = createman;
    }

    public Integer getIsMakeSure() {
        return this.isMakeSure;
    }

    public void setIsMakeSure(Integer isMakeSure) {
        this.isMakeSure = isMakeSure;
    }

}