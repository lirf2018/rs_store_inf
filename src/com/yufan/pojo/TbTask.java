package com.yufan.pojo;

import java.util.Date;

/**
 * TbTask entity. @author MyEclipse Persistence Tools
 */

public class TbTask implements java.io.Serializable {

    // Fields

    private Integer taskId;
    private String taskCode;
    private String taskName;
    private Date startTime;
    private Date endTime;
    private Date validDate;
    private String status;
    private String createman;
    private Date createtime;

    // Constructors

    /**
     * default constructor
     */
    public TbTask() {
    }

    /**
     * full constructor
     */
    public TbTask(String taskCode, String taskName, Date startTime,
                  Date endTime, Date validDate, String status, String createman,
                  Date createtime) {
        this.taskCode = taskCode;
        this.taskName = taskName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.validDate = validDate;
        this.status = status;
        this.createman = createman;
        this.createtime = createtime;
    }

    // Property accessors

    public Integer getTaskId() {
        return this.taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskCode() {
        return this.taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    public Date getValidDate() {
        return this.validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
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