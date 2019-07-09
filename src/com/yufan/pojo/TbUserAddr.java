package com.yufan.pojo;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * 创建人: lirf
 * 创建时间:  2018/9/25 16:24
 * 功能介绍:
 */
public class TbUserAddr {
    private Integer id;
    private String areaIds;
    private String areaName;
    private Integer userId;
    private String userPhone;
    private String userName;
    private String addrDetail;
    private Integer isDefault;
    private Integer status;
    private Timestamp createtime;
    private String addrName;
    private Integer addrType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(String areaIds) {
        this.areaIds = areaIds;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddrDetail() {
        return addrDetail;
    }

    public void setAddrDetail(String addrDetail) {
        this.addrDetail = addrDetail;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getAddrName() {
        return addrName;
    }

    public void setAddrName(String addrName) {
        this.addrName = addrName;
    }

    public Integer getAddrType() {
        return addrType;
    }

    public void setAddrType(Integer addrType) {
        this.addrType = addrType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbUserAddr userAddr = (TbUserAddr) o;
        return Objects.equals(id, userAddr.id) &&
                Objects.equals(areaIds, userAddr.areaIds) &&
                Objects.equals(areaName, userAddr.areaName) &&
                Objects.equals(userId, userAddr.userId) &&
                Objects.equals(userPhone, userAddr.userPhone) &&
                Objects.equals(userName, userAddr.userName) &&
                Objects.equals(addrDetail, userAddr.addrDetail) &&
                Objects.equals(isDefault, userAddr.isDefault) &&
                Objects.equals(status, userAddr.status) &&
                Objects.equals(createtime, userAddr.createtime) &&
                Objects.equals(addrName, userAddr.addrName) &&
                Objects.equals(addrType, userAddr.addrType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, areaIds, areaName, userId, userPhone, userName, addrDetail, isDefault, status, createtime, addrName, addrType);
    }
}
