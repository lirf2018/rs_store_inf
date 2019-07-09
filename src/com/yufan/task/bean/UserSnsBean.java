package com.yufan.task.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 创建人: lirf
 * 创建时间:  2017-12-15 16:18
 * 功能介绍:
 */
public class UserSnsBean {
    @JSONField(name = "user_id")
    private Integer userId;
    @JSONField(name = "sns_type")
    private Integer snsType;
    private String uid;
    private String openkey;
    @JSONField(name = "sns_name")
    private String snsName;
    @JSONField(name = "sns_aAccount")
    private String snsAccount;
    @JSONField(name = "sns_img")
    private String snsImg;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSnsType() {
        return snsType;
    }

    public void setSnsType(Integer snsType) {
        this.snsType = snsType;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOpenkey() {
        return openkey;
    }

    public void setOpenkey(String openkey) {
        this.openkey = openkey;
    }

    public String getSnsName() {
        return snsName;
    }

    public void setSnsName(String snsName) {
        this.snsName = snsName;
    }

    public String getSnsAccount() {
        return snsAccount;
    }

    public void setSnsAccount(String snsAccount) {
        this.snsAccount = snsAccount;
    }

    public String getSnsImg() {
        return snsImg;
    }

    public void setSnsImg(String snsImg) {
        this.snsImg = snsImg;
    }
}
