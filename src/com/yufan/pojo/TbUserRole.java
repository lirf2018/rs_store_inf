package com.yufan.pojo;

import java.util.Date;

/**
 * TbUserRole entity. @author MyEclipse Persistence Tools
 */

public class TbUserRole implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer adminId;
    private Integer roleId;
    private String createman;
    private Date createtime;

    // Constructors

    /**
     * default constructor
     */
    public TbUserRole() {
    }

    /**
     * full constructor
     */
    public TbUserRole(Integer adminId, Integer roleId, String createman,
                      Date createtime) {
        this.adminId = adminId;
        this.roleId = roleId;
        this.createman = createman;
        this.createtime = createtime;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminId() {
        return this.adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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