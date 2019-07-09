package com.yufan.pojo;

import java.util.Date;

/**
 * TbParam entity. @author MyEclipse Persistence Tools
 */

public class TbParam implements java.io.Serializable {

    // Fields

    private Integer paramId;
    private String paramName;
    private String paramType;
    private String paramCode;
    private String paramKey;
    private String paramValue;
    private String paramValue1;
    private String paramValue2;
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
    public TbParam() {
    }

    /**
     * full constructor
     */
    public TbParam(String paramName, String paramType, String paramCode,
                   String paramKey, String paramValue, String paramValue1,
                   String paramValue2, String createman, Date createtime,
                   Date lastaltertime, String lastalterman, Integer status,
                   String remark, Integer isMakeSure) {
        this.paramName = paramName;
        this.paramType = paramType;
        this.paramCode = paramCode;
        this.paramKey = paramKey;
        this.paramValue = paramValue;
        this.paramValue1 = paramValue1;
        this.paramValue2 = paramValue2;
        this.createman = createman;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalterman = lastalterman;
        this.status = status;
        this.remark = remark;
        this.isMakeSure = isMakeSure;
    }

    // Property accessors

    public Integer getParamId() {
        return this.paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public String getParamName() {
        return this.paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamType() {
        return this.paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getParamCode() {
        return this.paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamKey() {
        return this.paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamValue() {
        return this.paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamValue1() {
        return this.paramValue1;
    }

    public void setParamValue1(String paramValue1) {
        this.paramValue1 = paramValue1;
    }

    public String getParamValue2() {
        return this.paramValue2;
    }

    public void setParamValue2(String paramValue2) {
        this.paramValue2 = paramValue2;
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