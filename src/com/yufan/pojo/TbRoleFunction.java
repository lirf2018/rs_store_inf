package com.yufan.pojo;

import java.util.Date;

/**
 * TbRoleFunction entity. @author MyEclipse Persistence Tools
 */

public class TbRoleFunction implements java.io.Serializable {

    // Fields

    private Integer roleFunctionId;
    private Integer roleId;
    private Integer functionId;
    private String createman;
    private Date createtime;

    // Constructors

    /**
     * default constructor
     */
    public TbRoleFunction() {
    }

    /**
     * full constructor
     */
    public TbRoleFunction(Integer roleId, Integer functionId, String createman,
                          Date createtime) {
        this.roleId = roleId;
        this.functionId = functionId;
        this.createman = createman;
        this.createtime = createtime;
    }

    // Property accessors

    public Integer getRoleFunctionId() {
        return this.roleFunctionId;
    }

    public void setRoleFunctionId(Integer roleFunctionId) {
        this.roleFunctionId = roleFunctionId;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getFunctionId() {
        return this.functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
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

}