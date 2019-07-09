package com.yufan.pojo;

import java.util.Date;

/**
 * TbRole entity. @author MyEclipse Persistence Tools
 */

public class TbRole implements java.io.Serializable {

    // Fields

    private Integer roleId;
    private String roleCode;
    private String roleName;
    private Integer roleParentid;
    private Integer sort;
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
    public TbRole() {
    }

    /**
     * full constructor
     */
    public TbRole(String roleCode, String roleName, Integer roleParentid,
                  Integer sort, String createman, Date createtime,
                  Date lastaltertime, String lastalterman, Integer status,
                  String remark) {
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.roleParentid = roleParentid;
        this.sort = sort;
        this.createman = createman;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalterman = lastalterman;
        this.status = status;
        this.remark = remark;
    }

    // Property accessors

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return this.roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleParentid() {
        return this.roleParentid;
    }

    public void setRoleParentid(Integer roleParentid) {
        this.roleParentid = roleParentid;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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