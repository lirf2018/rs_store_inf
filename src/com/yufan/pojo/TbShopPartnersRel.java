package com.yufan.pojo;

import java.util.Date;

/**
 * TbShopPartnersRel entity. @author MyEclipse Persistence Tools
 */

public class TbShopPartnersRel implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer shopId;
    private Integer partnersId;
    private String createman;
    private Date createtime;

    // Constructors

    /**
     * default constructor
     */
    public TbShopPartnersRel() {
    }

    /**
     * full constructor
     */
    public TbShopPartnersRel(Integer shopId, Integer partnersId,
                             String createman, Date createtime) {
        this.shopId = shopId;
        this.partnersId = partnersId;
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

    public Integer getShopId() {
        return this.shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getPartnersId() {
        return this.partnersId;
    }

    public void setPartnersId(Integer partnersId) {
        this.partnersId = partnersId;
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