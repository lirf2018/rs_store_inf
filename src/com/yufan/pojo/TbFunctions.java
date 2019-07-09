package com.yufan.pojo;

import java.util.Date;

/**
 * TbFunctions entity. @author MyEclipse Persistence Tools
 */

public class TbFunctions implements java.io.Serializable {

    // Fields

    private Integer functionId;
    private String functionCode;
    private String functionName;
    private Integer functionParentid;
    private Integer functionType;
    private Integer sort;
    private String functionAction;
    private String createman;
    private Date createtime;
    private Date lastaltertime;
    private String lastalterman;
    private Integer status;
    private String remark;

    // Constructors

    /**
     * default constructor
     */
    public TbFunctions() {
    }

    /**
     * full constructor
     */
    public TbFunctions(String functionCode, String functionName,
                       Integer functionParentid, Integer functionType, Integer sort,
                       String functionAction, String createman, Date createtime,
                       Date lastaltertime, String lastalterman, Integer status,
                       String remark) {
        this.functionCode = functionCode;
        this.functionName = functionName;
        this.functionParentid = functionParentid;
        this.functionType = functionType;
        this.sort = sort;
        this.functionAction = functionAction;
        this.createman = createman;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalterman = lastalterman;
        this.status = status;
        this.remark = remark;
    }

    // Property accessors

    public Integer getFunctionId() {
        return this.functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }

    public String getFunctionCode() {
        return this.functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getFunctionName() {
        return this.functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Integer getFunctionParentid() {
        return this.functionParentid;
    }

    public void setFunctionParentid(Integer functionParentid) {
        this.functionParentid = functionParentid;
    }

    public Integer getFunctionType() {
        return this.functionType;
    }

    public void setFunctionType(Integer functionType) {
        this.functionType = functionType;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getFunctionAction() {
        return this.functionAction;
    }

    public void setFunctionAction(String functionAction) {
        this.functionAction = functionAction;
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

}