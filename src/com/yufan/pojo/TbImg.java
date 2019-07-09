package com.yufan.pojo;

import java.util.Date;

/**
 * TbImg entity. @author MyEclipse Persistence Tools
 */

public class TbImg implements java.io.Serializable {

    // Fields

    private Integer imgId;
    private String imgUrl;
    private Integer imgType;
    private Integer imgClassyfi;
    private Date createtime;
    private Integer relateId;
    private Integer imgSort;

    // Constructors

    /**
     * default constructor
     */
    public TbImg() {
    }

    /**
     * full constructor
     */
    public TbImg(String imgUrl, Integer imgType, Integer imgClassyfi,
                 Date createtime, Integer relateId, Integer imgSort) {
        this.imgUrl = imgUrl;
        this.imgType = imgType;
        this.imgClassyfi = imgClassyfi;
        this.createtime = createtime;
        this.relateId = relateId;
        this.imgSort = imgSort;
    }

    // Property accessors

    public Integer getImgId() {
        return this.imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getImgType() {
        return this.imgType;
    }

    public void setImgType(Integer imgType) {
        this.imgType = imgType;
    }

    public Integer getImgClassyfi() {
        return this.imgClassyfi;
    }

    public void setImgClassyfi(Integer imgClassyfi) {
        this.imgClassyfi = imgClassyfi;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getRelateId() {
        return this.relateId;
    }

    public void setRelateId(Integer relateId) {
        this.relateId = relateId;
    }

    public Integer getImgSort() {
        return this.imgSort;
    }

    public void setImgSort(Integer imgSort) {
        this.imgSort = imgSort;
    }

}