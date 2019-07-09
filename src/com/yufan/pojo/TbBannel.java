package com.yufan.pojo;

import java.util.Date;

/**
 * TbBannel entity. @author MyEclipse Persistence Tools
 */

public class TbBannel implements java.io.Serializable {

    // Fields

    private Integer bannelId;
    private String bannelTitle;
    private String bannelName;
    private String bannelLink;
    private String bannelImg;
    private Integer weight;
    private Integer channelId;
    private Integer partnersId;
    private Date startTime;
    private Date endTime;
    private Integer validDate;
    private Integer areaId;
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
    public TbBannel() {
    }

    /**
     * full constructor
     */
    public TbBannel(String bannelTitle, String bannelName, String bannelLink,
                    String bannelImg, Integer weight, Integer channelId,
                    Integer partnersId, Date startTime, Date endTime,
                    Integer validDate, Integer areaId, String createman,
                    Date createtime, Date lastaltertime, String lastalterman,
                    Integer status, String remark) {
        this.bannelTitle = bannelTitle;
        this.bannelName = bannelName;
        this.bannelLink = bannelLink;
        this.bannelImg = bannelImg;
        this.weight = weight;
        this.channelId = channelId;
        this.partnersId = partnersId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.validDate = validDate;
        this.areaId = areaId;
        this.createman = createman;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalterman = lastalterman;
        this.status = status;
        this.remark = remark;
    }

    // Property accessors

    public Integer getBannelId() {
        return this.bannelId;
    }

    public void setBannelId(Integer bannelId) {
        this.bannelId = bannelId;
    }

    public String getBannelTitle() {
        return this.bannelTitle;
    }

    public void setBannelTitle(String bannelTitle) {
        this.bannelTitle = bannelTitle;
    }

    public String getBannelName() {
        return this.bannelName;
    }

    public void setBannelName(String bannelName) {
        this.bannelName = bannelName;
    }

    public String getBannelLink() {
        return this.bannelLink;
    }

    public void setBannelLink(String bannelLink) {
        this.bannelLink = bannelLink;
    }

    public String getBannelImg() {
        return this.bannelImg;
    }

    public void setBannelImg(String bannelImg) {
        this.bannelImg = bannelImg;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getChannelId() {
        return this.channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getPartnersId() {
        return this.partnersId;
    }

    public void setPartnersId(Integer partnersId) {
        this.partnersId = partnersId;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getValidDate() {
        return this.validDate;
    }

    public void setValidDate(Integer validDate) {
        this.validDate = validDate;
    }

    public Integer getAreaId() {
        return this.areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
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