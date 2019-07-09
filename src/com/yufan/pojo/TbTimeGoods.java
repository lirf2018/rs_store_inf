package com.yufan.pojo;

import java.util.Date;

/**
 * TbTimeGoods entity. @author MyEclipse Persistence Tools
 */

public class TbTimeGoods implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer goodsId;
    private Integer goodsskuId;
    private Date beginTime;
    private Date endTime;
    private double timePrice;
    private Integer goodsStore;
    private Integer limitNum;
    private Integer timeWay;
    private Integer weight;
    private Integer isMakeSure;
    private String createman;
    private Date createtime;
    private Date lastaltertime;
    private String lastalterman;
    private Integer status;
    private String remark;
    private Date limitBeginTime;

    // Constructors

    /**
     * default constructor
     */
    public TbTimeGoods() {
    }

    /**
     * full constructor
     */
    public TbTimeGoods(Integer goodsId, Integer goodsskuId, Date beginTime,
                       Date endTime, double timePrice, Integer goodsStore,
                       Integer limitNum, Integer timeWay, Integer weight,
                       Integer isMakeSure, String createman, Date createtime,
                       Date lastaltertime, String lastalterman, Integer status,
                       String remark, Date limitBeginTime) {
        this.goodsId = goodsId;
        this.goodsskuId = goodsskuId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.timePrice = timePrice;
        this.goodsStore = goodsStore;
        this.limitNum = limitNum;
        this.timeWay = timeWay;
        this.weight = weight;
        this.isMakeSure = isMakeSure;
        this.createman = createman;
        this.createtime = createtime;
        this.lastaltertime = lastaltertime;
        this.lastalterman = lastalterman;
        this.status = status;
        this.remark = remark;
        this.limitBeginTime = limitBeginTime;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsskuId() {
        return this.goodsskuId;
    }

    public void setGoodsskuId(Integer goodsskuId) {
        this.goodsskuId = goodsskuId;
    }

    public Date getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getTimePrice() {
        return this.timePrice;
    }

    public void setTimePrice(double timePrice) {
        this.timePrice = timePrice;
    }

    public Integer getGoodsStore() {
        return this.goodsStore;
    }

    public void setGoodsStore(Integer goodsStore) {
        this.goodsStore = goodsStore;
    }

    public Integer getLimitNum() {
        return this.limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public Integer getTimeWay() {
        return this.timeWay;
    }

    public void setTimeWay(Integer timeWay) {
        this.timeWay = timeWay;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getIsMakeSure() {
        return this.isMakeSure;
    }

    public void setIsMakeSure(Integer isMakeSure) {
        this.isMakeSure = isMakeSure;
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

    public Date getLimitBeginTime() {
        return this.limitBeginTime;
    }

    public void setLimitBeginTime(Date limitBeginTime) {
        this.limitBeginTime = limitBeginTime;
    }

}