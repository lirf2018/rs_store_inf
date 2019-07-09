package com.yufan.pojo;

import java.util.Date;

/**
 * TbNews entity. @author MyEclipse Persistence Tools
 */

public class TbNews implements java.io.Serializable {

    // Fields

    private Integer newsId;
    private String newsTitle;
    private String contents;
    private Integer status;
    private Integer isRead;
    private Date createtime;
    private Date lastaltertime;
    private String createman;
    private Integer userId;

    // Constructors

    /**
     * default constructor
     */
    public TbNews() {
    }

    /**
     * full constructor
     */
    public TbNews(String newsTitle, String contents, Integer status,
                  Integer isRead, Date createtime, Date lastaltertime,
                  String createman, Integer userId) {
        this.newsTitle = newsTitle;
        this.contents = contents;
        this.status = status;
        this.isRead = isRead;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.createman = createman;
        this.userId = userId;
    }

    // Property accessors

    public Integer getNewsId() {
        return this.newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return this.newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getContents() {
        return this.contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsRead() {
        return this.isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
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

    public String getCreateman() {
        return this.createman;
    }

    public void setCreateman(String createman) {
        this.createman = createman;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}