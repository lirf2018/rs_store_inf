package com.yufan.pojo;

import java.util.Date;

/**
 * TbCatogryLevel1 entity. @author MyEclipse Persistence Tools
 */

public class TbCatogryLevel1 implements java.io.Serializable {

    // Fields

    private Integer levelId;
    private String levelCode;
    private String levelName;
    private String levelImg;
    private Integer levelSort;
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
    public TbCatogryLevel1() {
    }

    /**
     * full constructor
     */
    public TbCatogryLevel1(String levelCode, String levelName, String levelImg,
                           Integer levelSort, String createman, Date createtime,
                           Date lastaltertime, String lastalterman, Integer status,
                           String remark) {
        this.levelCode = levelCode;
        this.levelName = levelName;
        this.levelImg = levelImg;
        this.levelSort = levelSort;
        this.createman = createman;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalterman = lastalterman;
        this.status = status;
        this.remark = remark;
    }

    // Property accessors

    public Integer getLevelId() {
        return this.levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelCode() {
        return this.levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public String getLevelName() {
        return this.levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelImg() {
        return this.levelImg;
    }

    public void setLevelImg(String levelImg) {
        this.levelImg = levelImg;
    }

    public Integer getLevelSort() {
        return this.levelSort;
    }

    public void setLevelSort(Integer levelSort) {
        this.levelSort = levelSort;
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