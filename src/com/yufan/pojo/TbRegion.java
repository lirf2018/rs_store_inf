package com.yufan.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 创建人: lirf
 * 创建时间:  2018/8/14 9:13
 * 功能介绍:
 */
public class TbRegion {
    private Integer regionId;
    private String regionCode;
    private String regionName;
    private String regionShortname;
    private String parentId;
    private Integer regionLevel;
    private Integer regionOrder;
    private String regionNameEn;
    private String regionShortnameEn;
    private Integer regionType;
    private String createman;
    private Timestamp createtime;
    private Timestamp lastaltertime;
    private String lastalterman;
    private Integer status;
    private String remark;
    private BigDecimal freight;

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionShortname() {
        return regionShortname;
    }

    public void setRegionShortname(String regionShortname) {
        this.regionShortname = regionShortname;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getRegionLevel() {
        return regionLevel;
    }

    public void setRegionLevel(Integer regionLevel) {
        this.regionLevel = regionLevel;
    }

    public Integer getRegionOrder() {
        return regionOrder;
    }

    public void setRegionOrder(Integer regionOrder) {
        this.regionOrder = regionOrder;
    }

    public String getRegionNameEn() {
        return regionNameEn;
    }

    public void setRegionNameEn(String regionNameEn) {
        this.regionNameEn = regionNameEn;
    }

    public String getRegionShortnameEn() {
        return regionShortnameEn;
    }

    public void setRegionShortnameEn(String regionShortnameEn) {
        this.regionShortnameEn = regionShortnameEn;
    }

    public Integer getRegionType() {
        return regionType;
    }

    public void setRegionType(Integer regionType) {
        this.regionType = regionType;
    }

    public String getCreateman() {
        return createman;
    }

    public void setCreateman(String createman) {
        this.createman = createman;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Timestamp getLastaltertime() {
        return lastaltertime;
    }

    public void setLastaltertime(Timestamp lastaltertime) {
        this.lastaltertime = lastaltertime;
    }

    public String getLastalterman() {
        return lastalterman;
    }

    public void setLastalterman(String lastalterman) {
        this.lastalterman = lastalterman;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbRegion region = (TbRegion) o;
        return Objects.equals(regionId, region.regionId) &&
                Objects.equals(regionCode, region.regionCode) &&
                Objects.equals(regionName, region.regionName) &&
                Objects.equals(regionShortname, region.regionShortname) &&
                Objects.equals(parentId, region.parentId) &&
                Objects.equals(regionLevel, region.regionLevel) &&
                Objects.equals(regionOrder, region.regionOrder) &&
                Objects.equals(regionNameEn, region.regionNameEn) &&
                Objects.equals(regionShortnameEn, region.regionShortnameEn) &&
                Objects.equals(regionType, region.regionType) &&
                Objects.equals(createman, region.createman) &&
                Objects.equals(createtime, region.createtime) &&
                Objects.equals(lastaltertime, region.lastaltertime) &&
                Objects.equals(lastalterman, region.lastalterman) &&
                Objects.equals(status, region.status) &&
                Objects.equals(remark, region.remark) &&
                Objects.equals(freight, region.freight);
    }

    @Override
    public int hashCode() {

        return Objects.hash(regionId, regionCode, regionName, regionShortname, parentId, regionLevel, regionOrder, regionNameEn, regionShortnameEn, regionType, createman, createtime, lastaltertime, lastalterman, status, remark, freight);
    }
}
