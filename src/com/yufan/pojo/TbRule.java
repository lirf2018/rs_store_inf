package com.yufan.pojo;

import java.util.Date;

/**
 * TbRule entity. @author MyEclipse Persistence Tools
 */

public class TbRule implements java.io.Serializable {

    // Fields

    private Integer ruleId;
    private String ruleName;
    private String ruleCode;
    private Integer ruleType;
    private String ruleValue;
    private Integer ruleBelong;
    private String ruleDesc;
    private String createman;
    private Date createtime;
    private Date lastaltertime;
    private String lastalterman;
    private Integer status;
    private String remark;
    private Integer isMakeSure;

    // Constructors

    /**
     * default constructor
     */
    public TbRule() {
    }

    /**
     * full constructor
     */
    public TbRule(String ruleName, String ruleCode, Integer ruleType,
                  String ruleValue, Integer ruleBelong, String ruleDesc,
                  String createman, Date createtime, Date lastaltertime,
                  String lastalterman, Integer status, String remark,
                  Integer isMakeSure) {
        this.ruleName = ruleName;
        this.ruleCode = ruleCode;
        this.ruleType = ruleType;
        this.ruleValue = ruleValue;
        this.ruleBelong = ruleBelong;
        this.ruleDesc = ruleDesc;
        this.createman = createman;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalterman = lastalterman;
        this.status = status;
        this.remark = remark;
        this.isMakeSure = isMakeSure;
    }

    // Property accessors

    public Integer getRuleId() {
        return this.ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return this.ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleCode() {
        return this.ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public Integer getRuleType() {
        return this.ruleType;
    }

    public void setRuleType(Integer ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleValue() {
        return this.ruleValue;
    }

    public void setRuleValue(String ruleValue) {
        this.ruleValue = ruleValue;
    }

    public Integer getRuleBelong() {
        return this.ruleBelong;
    }

    public void setRuleBelong(Integer ruleBelong) {
        this.ruleBelong = ruleBelong;
    }

    public String getRuleDesc() {
        return this.ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
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

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsMakeSure() {
        return this.isMakeSure;
    }

    public void setIsMakeSure(Integer isMakeSure) {
        this.isMakeSure = isMakeSure;
    }

}