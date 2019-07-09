package com.yufan.pojo;

import java.util.Date;

/**
 * TbWeixinMenu entity. @author MyEclipse Persistence Tools
 */

public class TbWeixinMenu implements java.io.Serializable {

    // Fields

    private Integer menuId;
    private String menuName;
    private Integer menuType;
    private Integer menuParent;
    private Integer menuIndex;
    private String menuFoward;
    private String keyCode;
    private String buttonType;
    private String menuKey;
    private Integer createman;
    private Date createtime;
    private Date lastaltertime;
    private Integer lastalterman;
    private Integer status;
    private String remark;

    // Constructors

    /**
     * default constructor
     */
    public TbWeixinMenu() {
    }

    /**
     * full constructor
     */
    public TbWeixinMenu(String menuName, Integer menuType, Integer menuParent,
                        Integer menuIndex, String menuFoward, String keyCode,
                        String buttonType, String menuKey, Integer createman,
                        Date createtime, Date lastaltertime, Integer lastalterman,
                        Integer status, String remark) {
        this.menuName = menuName;
        this.menuType = menuType;
        this.menuParent = menuParent;
        this.menuIndex = menuIndex;
        this.menuFoward = menuFoward;
        this.keyCode = keyCode;
        this.buttonType = buttonType;
        this.menuKey = menuKey;
        this.createman = createman;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalterman = lastalterman;
        this.status = status;
        this.remark = remark;
    }

    // Property accessors

    public Integer getMenuId() {
        return this.menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getMenuType() {
        return this.menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public Integer getMenuParent() {
        return this.menuParent;
    }

    public void setMenuParent(Integer menuParent) {
        this.menuParent = menuParent;
    }

    public Integer getMenuIndex() {
        return this.menuIndex;
    }

    public void setMenuIndex(Integer menuIndex) {
        this.menuIndex = menuIndex;
    }

    public String getMenuFoward() {
        return this.menuFoward;
    }

    public void setMenuFoward(String menuFoward) {
        this.menuFoward = menuFoward;
    }

    public String getKeyCode() {
        return this.keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public String getButtonType() {
        return this.buttonType;
    }

    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }

    public String getMenuKey() {
        return this.menuKey;
    }

    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey;
    }

    public Integer getCreateman() {
        return this.createman;
    }

    public void setCreateman(Integer createman) {
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

    public Integer getLastalterman() {
        return this.lastalterman;
    }

    public void setLastalterman(Integer lastalterman) {
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