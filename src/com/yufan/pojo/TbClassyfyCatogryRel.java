package com.yufan.pojo;

/**
 * TbClassyfyCatogryRel entity. @author MyEclipse Persistence Tools
 */

public class TbClassyfyCatogryRel implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer levelId;
    private Integer categoryId;

    // Constructors

    /**
     * default constructor
     */
    public TbClassyfyCatogryRel() {
    }

    /**
     * full constructor
     */
    public TbClassyfyCatogryRel(Integer levelId, Integer categoryId) {
        this.levelId = levelId;
        this.categoryId = categoryId;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevelId() {
        return this.levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

}