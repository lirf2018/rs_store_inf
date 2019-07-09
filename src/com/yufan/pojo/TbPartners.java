package com.yufan.pojo;

import java.util.Date;

/**
 * TbPartners entity. @author MyEclipse Persistence Tools
 */

public class TbPartners implements java.io.Serializable {

    // Fields

    private Integer id;
    private String partnersCode;
    private String partnersName;
    private Integer shopId;
    private String createman;
    private Date createtime;
    private Date lastaltertime;
    private String lastalterman;
    private Integer status;
    private String remark;
    private Integer partnersSort;
    private String firstNameCode;
    private String account;
    private String passwd;
    private String secretKey;
    private String partnersImg;
    private String passwdShow;
    private Integer isRecommend;

    // Constructors

    /**
     * default constructor
     */
    public TbPartners() {
    }

    /**
     * full constructor
     */
    public TbPartners(String partnersCode, String partnersName, Integer shopId,
                      String createman, Date createtime, Date lastaltertime,
                      String lastalterman, Integer status, String remark,
                      Integer partnersSort, String firstNameCode, String account,
                      String passwd, String secretKey, String partnersImg,
                      String passwdShow, Integer isRecommend) {
        this.partnersCode = partnersCode;
        this.partnersName = partnersName;
        this.shopId = shopId;
        this.createman = createman;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalterman = lastalterman;
        this.status = status;
        this.remark = remark;
        this.partnersSort = partnersSort;
        this.firstNameCode = firstNameCode;
        this.account = account;
        this.passwd = passwd;
        this.secretKey = secretKey;
        this.partnersImg = partnersImg;
        this.passwdShow = passwdShow;
        this.isRecommend = isRecommend;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartnersCode() {
        return this.partnersCode;
    }

    public void setPartnersCode(String partnersCode) {
        this.partnersCode = partnersCode;
    }

    public String getPartnersName() {
        return this.partnersName;
    }

    public void setPartnersName(String partnersName) {
        this.partnersName = partnersName;
    }

    public Integer getShopId() {
        return this.shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
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

    public Integer getPartnersSort() {
        return this.partnersSort;
    }

    public void setPartnersSort(Integer partnersSort) {
        this.partnersSort = partnersSort;
    }

    public String getFirstNameCode() {
        return this.firstNameCode;
    }

    public void setFirstNameCode(String firstNameCode) {
        this.firstNameCode = firstNameCode;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPasswd() {
        return this.passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getPartnersImg() {
        return this.partnersImg;
    }

    public void setPartnersImg(String partnersImg) {
        this.partnersImg = partnersImg;
    }

    public String getPasswdShow() {
        return this.passwdShow;
    }

    public void setPasswdShow(String passwdShow) {
        this.passwdShow = passwdShow;
    }

    public Integer getIsRecommend() {
        return this.isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

}