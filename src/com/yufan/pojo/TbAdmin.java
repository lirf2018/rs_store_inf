package com.yufan.pojo;

import java.util.Date;

/**
 * TbAdmin entity. @author MyEclipse Persistence Tools
 */

public class TbAdmin implements java.io.Serializable {

    // Fields

    private Integer adminId;
    private String loginName;
    private String loginPassword;
    private String userName;
    private Date birthday;
    private String sex;
    private String photo;
    private String nickName;
    private String idcard;
    private String phone;
    private String email;
    private String qq;
    private Date memberBegintime;
    private Date memberEndtime;
    private Integer shopId;
    private Integer shopMenberType;
    private String passwordHide;
    private String createman;
    private Date createtime;
    private Date lastaltertime;
    private String lastalterman;
    private Date lastlogintime;
    private Integer status;
    private String remark;
    private String wxOpenid;
    private Integer isMakeSure;

    // Constructors

    /**
     * default constructor
     */
    public TbAdmin() {
    }

    /**
     * full constructor
     */
    public TbAdmin(String loginName, String loginPassword, String userName,
                   Date birthday, String sex, String photo, String nickName,
                   String idcard, String phone, String email, String qq,
                   Date memberBegintime, Date memberEndtime, Integer shopId,
                   Integer shopMenberType, String passwordHide, String createman,
                   Date createtime, Date lastaltertime, String lastalterman,
                   Date lastlogintime, Integer status, String remark, String wxOpenid,
                   Integer isMakeSure) {
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.userName = userName;
        this.birthday = birthday;
        this.sex = sex;
        this.photo = photo;
        this.nickName = nickName;
        this.idcard = idcard;
        this.phone = phone;
        this.email = email;
        this.qq = qq;
        this.memberBegintime = memberBegintime;
        this.memberEndtime = memberEndtime;
        this.shopId = shopId;
        this.shopMenberType = shopMenberType;
        this.passwordHide = passwordHide;
        this.createman = createman;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalterman = lastalterman;
        this.lastlogintime = lastlogintime;
        this.status = status;
        this.remark = remark;
        this.wxOpenid = wxOpenid;
        this.isMakeSure = isMakeSure;
    }

    // Property accessors

    public Integer getAdminId() {
        return this.adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return this.loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIdcard() {
        return this.idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return this.qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Date getMemberBegintime() {
        return this.memberBegintime;
    }

    public void setMemberBegintime(Date memberBegintime) {
        this.memberBegintime = memberBegintime;
    }

    public Date getMemberEndtime() {
        return this.memberEndtime;
    }

    public void setMemberEndtime(Date memberEndtime) {
        this.memberEndtime = memberEndtime;
    }

    public Integer getShopId() {
        return this.shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getShopMenberType() {
        return this.shopMenberType;
    }

    public void setShopMenberType(Integer shopMenberType) {
        this.shopMenberType = shopMenberType;
    }

    public String getPasswordHide() {
        return this.passwordHide;
    }

    public void setPasswordHide(String passwordHide) {
        this.passwordHide = passwordHide;
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

    public Date getLastlogintime() {
        return this.lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
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

    public String getWxOpenid() {
        return this.wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public Integer getIsMakeSure() {
        return this.isMakeSure;
    }

    public void setIsMakeSure(Integer isMakeSure) {
        this.isMakeSure = isMakeSure;
    }

}