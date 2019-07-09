package com.yufan.pojo;

import java.util.Date;

/**
 * TbVerification entity. @author MyEclipse Persistence Tools
 */

public class TbVerification implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer validType;
    private String validParam;
    private String validCode;
    private String validDesc;
    private Integer status;
    private Date passTime;
    private Date createtime;
    private Date lastaltertime;
    private String userSnsUid;
    private Integer sendStatus;
    private String sendMsg;

    // Constructors

    /**
     * default constructor
     */
    public TbVerification() {
    }

    /**
     * full constructor
     */
    public TbVerification(Integer validType, String validParam,
                          String validCode, String validDesc, Integer status, Date passTime,
                          Date createtime, Date lastaltertime, String userSnsUid,
                          Integer sendStatus, String sendMsg) {
        this.validType = validType;
        this.validParam = validParam;
        this.validCode = validCode;
        this.validDesc = validDesc;
        this.status = status;
        this.passTime = passTime;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.userSnsUid = userSnsUid;
        this.sendStatus = sendStatus;
        this.sendMsg = sendMsg;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValidType() {
        return this.validType;
    }

    public void setValidType(Integer validType) {
        this.validType = validType;
    }

    public String getValidParam() {
        return this.validParam;
    }

    public void setValidParam(String validParam) {
        this.validParam = validParam;
    }

    public String getValidCode() {
        return this.validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public String getValidDesc() {
        return this.validDesc;
    }

    public void setValidDesc(String validDesc) {
        this.validDesc = validDesc;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPassTime() {
        return this.passTime;
    }

    public void setPassTime(Date passTime) {
        this.passTime = passTime;
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

    public String getUserSnsUid() {
        return this.userSnsUid;
    }

    public void setUserSnsUid(String userSnsUid) {
        this.userSnsUid = userSnsUid;
    }

    public Integer getSendStatus() {
        return this.sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getSendMsg() {
        return this.sendMsg;
    }

    public void setSendMsg(String sendMsg) {
        this.sendMsg = sendMsg;
    }

}