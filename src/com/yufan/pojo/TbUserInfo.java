package com.yufan.pojo;

import java.util.Date;

/**
 * TbUserInfo entity. @author MyEclipse Persistence Tools
 */

public class TbUserInfo implements java.io.Serializable {

    // Fields

    private Integer userId;
    private String loginName;
    private String loginPass;
    private String loginPassShow;
    private String nickName;
    private String userEmail;
    private Integer emailValite;
    private String userMobile;
    private Integer mobileValite;
    private Integer logCount;
    private Integer userState;
    private Date createtime;
    private Date lastlogintime;
    private Date lastaltertime;
    private String userImg;

    // Constructors

    /**
     * default constructor
     */
    public TbUserInfo() {
    }

    /**
     * full constructor
     */
    public TbUserInfo(String loginName, String loginPass, String loginPassShow,
                      String nickName, String userEmail, Integer emailValite,
                      String userMobile, Integer mobileValite, Integer logCount,
                      Integer userState, Date createtime, Date lastlogintime,
                      Date lastaltertime, String userImg) {
        this.loginName = loginName;
        this.loginPass = loginPass;
        this.loginPassShow = loginPassShow;
        this.nickName = nickName;
        this.userEmail = userEmail;
        this.emailValite = emailValite;
        this.userMobile = userMobile;
        this.mobileValite = mobileValite;
        this.logCount = logCount;
        this.userState = userState;
        this.createtime = createtime;
        this.lastlogintime = lastlogintime;
        this.lastaltertime = lastaltertime;
        this.userImg = userImg;
    }

    // Property accessors

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPass() {
        return this.loginPass;
    }

    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass;
    }

    public String getLoginPassShow() {
        return this.loginPassShow;
    }

    public void setLoginPassShow(String loginPassShow) {
        this.loginPassShow = loginPassShow;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getEmailValite() {
        return this.emailValite;
    }

    public void setEmailValite(Integer emailValite) {
        this.emailValite = emailValite;
    }

    public String getUserMobile() {
        return this.userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Integer getMobileValite() {
        return this.mobileValite;
    }

    public void setMobileValite(Integer mobileValite) {
        this.mobileValite = mobileValite;
    }

    public Integer getLogCount() {
        return this.logCount;
    }

    public void setLogCount(Integer logCount) {
        this.logCount = logCount;
    }

    public Integer getUserState() {
        return this.userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastlogintime() {
        return this.lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public Date getLastaltertime() {
        return this.lastaltertime;
    }

    public void setLastaltertime(Date lastaltertime) {
        this.lastaltertime = lastaltertime;
    }

    public String getUserImg() {
        return this.userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

}