package com.yufan.pojo;

import java.util.Date;

/**
 * TbChannel entity. @author MyEclipse Persistence Tools
 */

public class TbChannel implements java.io.Serializable {

    // Fields

    private Integer channelId;
    private String channelCode;
    private String channelName;
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
    public TbChannel() {
    }

    /**
     * full constructor
     */
    public TbChannel(String channelCode, String channelName, String createman,
                     Date createtime, Date lastaltertime, String lastalterman,
                     Integer status, String remark) {
        this.channelCode = channelCode;
        this.channelName = channelName;
        this.createman = createman;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalterman = lastalterman;
        this.status = status;
        this.remark = remark;
    }

    // Property accessors

    public Integer getChannelId() {
        return this.channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getChannelCode() {
        return this.channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
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