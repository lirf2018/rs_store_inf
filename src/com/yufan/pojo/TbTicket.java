package com.yufan.pojo;

import java.util.Date;

/**
 * TbTicket entity. @author MyEclipse Persistence Tools
 */

public class TbTicket implements java.io.Serializable {

    // Fields

    private Integer tikcetId;
    private String tikcetName;
    private String title;
    private String ticketImg;
    private String intro;
    private Integer shopId;
    private Integer weight;
    private Integer classifyId;
    private Integer areaId;
    private Integer isShow;
    private Integer ticketType;
    private double ticketValue;
    private Date startTime;
    private Date endTime;
    private Integer validDate;
    private Integer outDate;
    private String createman;
    private Date createtime;
    private Date lastaltertime;
    private String lastalterman;
    private Integer status;
    private String remark;
    private String needKnow;
    private Integer ticketNum;
    private Integer partnersId;
    private Integer isPutaway;
    private Integer limitNum;
    private Integer limitWay;
    private Integer leve1Id;
    private Integer appointType;
    private Date appointDate;
    private Date limitBeginTime;

    // Constructors

    /**
     * default constructor
     */
    public TbTicket() {
    }

    /**
     * full constructor
     */
    public TbTicket(String tikcetName, String title, String ticketImg,
                    String intro, Integer shopId, Integer weight, Integer classifyId,
                    Integer areaId, Integer isShow, Integer ticketType,
                    double ticketValue, Date startTime, Date endTime,
                    Integer validDate, Integer outDate, String createman,
                    Date createtime, Date lastaltertime, String lastalterman,
                    Integer status, String remark, String needKnow, Integer ticketNum,
                    Integer partnersId, Integer isPutaway, Integer limitNum,
                    Integer limitWay, Integer leve1Id, Integer appointType,
                    Date appointDate, Date limitBeginTime) {
        this.tikcetName = tikcetName;
        this.title = title;
        this.ticketImg = ticketImg;
        this.intro = intro;
        this.shopId = shopId;
        this.weight = weight;
        this.classifyId = classifyId;
        this.areaId = areaId;
        this.isShow = isShow;
        this.ticketType = ticketType;
        this.ticketValue = ticketValue;
        this.startTime = startTime;
        this.endTime = endTime;
        this.validDate = validDate;
        this.outDate = outDate;
        this.createman = createman;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalterman = lastalterman;
        this.status = status;
        this.remark = remark;
        this.needKnow = needKnow;
        this.ticketNum = ticketNum;
        this.partnersId = partnersId;
        this.isPutaway = isPutaway;
        this.limitNum = limitNum;
        this.limitWay = limitWay;
        this.leve1Id = leve1Id;
        this.appointType = appointType;
        this.appointDate = appointDate;
        this.limitBeginTime = limitBeginTime;
    }

    // Property accessors

    public Integer getTikcetId() {
        return this.tikcetId;
    }

    public void setTikcetId(Integer tikcetId) {
        this.tikcetId = tikcetId;
    }

    public String getTikcetName() {
        return this.tikcetName;
    }

    public void setTikcetName(String tikcetName) {
        this.tikcetName = tikcetName;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTicketImg() {
        return this.ticketImg;
    }

    public void setTicketImg(String ticketImg) {
        this.ticketImg = ticketImg;
    }

    public String getIntro() {
        return this.intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getShopId() {
        return this.shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getClassifyId() {
        return this.classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public Integer getAreaId() {
        return this.areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getIsShow() {
        return this.isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getTicketType() {
        return this.ticketType;
    }

    public void setTicketType(Integer ticketType) {
        this.ticketType = ticketType;
    }

    public double getTicketValue() {
        return this.ticketValue;
    }

    public void setTicketValue(double ticketValue) {
        this.ticketValue = ticketValue;
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

    public Integer getOutDate() {
        return this.outDate;
    }

    public void setOutDate(Integer outDate) {
        this.outDate = outDate;
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

    public String getNeedKnow() {
        return this.needKnow;
    }

    public void setNeedKnow(String needKnow) {
        this.needKnow = needKnow;
    }

    public Integer getTicketNum() {
        return this.ticketNum;
    }

    public void setTicketNum(Integer ticketNum) {
        this.ticketNum = ticketNum;
    }

    public Integer getPartnersId() {
        return this.partnersId;
    }

    public void setPartnersId(Integer partnersId) {
        this.partnersId = partnersId;
    }

    public Integer getIsPutaway() {
        return this.isPutaway;
    }

    public void setIsPutaway(Integer isPutaway) {
        this.isPutaway = isPutaway;
    }

    public Integer getLimitNum() {
        return this.limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public Integer getLimitWay() {
        return this.limitWay;
    }

    public void setLimitWay(Integer limitWay) {
        this.limitWay = limitWay;
    }

    public Integer getLeve1Id() {
        return this.leve1Id;
    }

    public void setLeve1Id(Integer leve1Id) {
        this.leve1Id = leve1Id;
    }

    public Integer getAppointType() {
        return this.appointType;
    }

    public void setAppointType(Integer appointType) {
        this.appointType = appointType;
    }

    public Date getAppointDate() {
        return this.appointDate;
    }

    public void setAppointDate(Date appointDate) {
        this.appointDate = appointDate;
    }

    public Date getLimitBeginTime() {
        return this.limitBeginTime;
    }

    public void setLimitBeginTime(Date limitBeginTime) {
        this.limitBeginTime = limitBeginTime;
    }

}