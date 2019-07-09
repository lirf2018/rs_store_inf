package com.yufan.pojo;

import java.util.Date;

/**
 * TbTicketDownQr entity. @author MyEclipse Persistence Tools
 */

public class TbTicketDownQr implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer ticketId;
    private Integer userId;
    private Integer channelId;
    private String qrImg;
    private String changeCode;
    private String checkCode;
    private String content;
    private Date startTime;
    private Date endTime;
    private Integer recodeState;
    private Date changeDate;
    private Integer changeUserId;
    private Integer changePartnersId;
    private String createman;
    private Date createtime;
    private Date lastaltertime;
    private String lastalterman;
    private Integer status;
    private String remark;
    private Integer ticketType;
    private double ticketValue;
    private Integer shopId;
    private String qrDesc;

    // Constructors

    /**
     * default constructor
     */
    public TbTicketDownQr() {
    }

    /**
     * full constructor
     */
    public TbTicketDownQr(Integer ticketId, Integer userId, Integer channelId,
                          String qrImg, String changeCode, String checkCode, String content,
                          Date startTime, Date endTime, Integer recodeState, Date changeDate,
                          Integer changeUserId, Integer changePartnersId, String createman,
                          Date createtime, Date lastaltertime, String lastalterman,
                          Integer status, String remark, Integer ticketType,
                          double ticketValue, Integer shopId, String qrDesc) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.channelId = channelId;
        this.qrImg = qrImg;
        this.changeCode = changeCode;
        this.checkCode = checkCode;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
        this.recodeState = recodeState;
        this.changeDate = changeDate;
        this.changeUserId = changeUserId;
        this.changePartnersId = changePartnersId;
        this.createman = createman;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalterman = lastalterman;
        this.status = status;
        this.remark = remark;
        this.ticketType = ticketType;
        this.ticketValue = ticketValue;
        this.shopId = shopId;
        this.qrDesc = qrDesc;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTicketId() {
        return this.ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getChannelId() {
        return this.channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getQrImg() {
        return this.qrImg;
    }

    public void setQrImg(String qrImg) {
        this.qrImg = qrImg;
    }

    public String getChangeCode() {
        return this.changeCode;
    }

    public void setChangeCode(String changeCode) {
        this.changeCode = changeCode;
    }

    public String getCheckCode() {
        return this.checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getRecodeState() {
        return this.recodeState;
    }

    public void setRecodeState(Integer recodeState) {
        this.recodeState = recodeState;
    }

    public Date getChangeDate() {
        return this.changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public Integer getChangeUserId() {
        return this.changeUserId;
    }

    public void setChangeUserId(Integer changeUserId) {
        this.changeUserId = changeUserId;
    }

    public Integer getChangePartnersId() {
        return this.changePartnersId;
    }

    public void setChangePartnersId(Integer changePartnersId) {
        this.changePartnersId = changePartnersId;
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

    public Integer getShopId() {
        return this.shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getQrDesc() {
        return this.qrDesc;
    }

    public void setQrDesc(String qrDesc) {
        this.qrDesc = qrDesc;
    }

}