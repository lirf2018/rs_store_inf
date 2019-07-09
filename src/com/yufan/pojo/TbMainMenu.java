package com.yufan.pojo;

import java.util.Date;

/**
 * TbMainMenu entity. @author MyEclipse Persistence Tools
 */

public class TbMainMenu implements java.io.Serializable {

    // Fields

    private Integer id;
    private String menuName;
    private String menuImg;
    private Integer menuSort;
    private String menuUrl;
    private Integer status;
    private String leve1Ids;
    private String categoryIds;
    private Date createtime;

    // Constructors

    /**
     * default constructor
     */
    public TbMainMenu() {
    }

    /**
     * full constructor
     */
    public TbMainMenu(String menuName, String menuImg, Integer menuSort,
                      String menuUrl, Integer status, String leve1Ids,
                      String categoryIds, Date createtime) {
        this.menuName = menuName;
        this.menuImg = menuImg;
        this.menuSort = menuSort;
        this.menuUrl = menuUrl;
        this.status = status;
        this.leve1Ids = leve1Ids;
        this.categoryIds = categoryIds;
        this.createtime = createtime;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuImg() {
        return this.menuImg;
    }

    public void setMenuImg(String menuImg) {
        this.menuImg = menuImg;
    }

    public Integer getMenuSort() {
        return this.menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public String getMenuUrl() {
        return this.menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLeve1Ids() {
        return this.leve1Ids;
    }

    public void setLeve1Ids(String leve1Ids) {
        this.leve1Ids = leve1Ids;
    }

    public String getCategoryIds() {
        return this.categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

}