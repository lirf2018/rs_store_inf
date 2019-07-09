package com.yufan.pojo;

import java.util.Date;

/**
 * TbUserSns entity. @author MyEclipse Persistence Tools
 */

public class TbUserSns implements java.io.Serializable {

    // Fields

    private Integer snsId;
    private Integer userId;
    private Integer snsType;
    private String uid;
    private String openkey;
    private String snsName;
    private String snsAccount;
    private String snsImg;
    private Integer isUseImg;
    private Date createtime;
    private Integer status;

    // Constructors

    /**
     * default constructor
     */
    public TbUserSns() {
    }

    /**
     * full constructor
     */
    public TbUserSns(Integer userId, Integer snsType, String uid,
                     String openkey, String snsName, String snsAccount, String snsImg,
                     Integer isUseImg, Date createtime, Integer status) {
        this.userId = userId;
        this.snsType = snsType;
        this.uid = uid;
        this.openkey = openkey;
        this.snsName = snsName;
        this.snsAccount = snsAccount;
        this.snsImg = snsImg;
        this.isUseImg = isUseImg;
        this.createtime = createtime;
        this.status = status;
    }

    // Property accessors

    public Integer getSnsId() {
        return this.snsId;
    }

    public void setSnsId(Integer snsId) {
        this.snsId = snsId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSnsType() {
        return this.snsType;
    }

    public void setSnsType(Integer snsType) {
        this.snsType = snsType;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOpenkey() {
        return this.openkey;
    }

    public void setOpenkey(String openkey) {
        this.openkey = openkey;
    }

    public String getSnsName() {
        return this.snsName;
    }

    public void setSnsName(String snsName) {
        this.snsName = snsName;
    }

    public String getSnsAccount() {
        return this.snsAccount;
    }

    public void setSnsAccount(String snsAccount) {
        this.snsAccount = snsAccount;
    }

    public String getSnsImg() {
        return this.snsImg;
    }

    public void setSnsImg(String snsImg) {
        this.snsImg = snsImg;
    }

    public Integer getIsUseImg() {
        return this.isUseImg;
    }

    public void setIsUseImg(Integer isUseImg) {
        this.isUseImg = isUseImg;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}