package com.yufan.task.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 创建人: lirf
 * 创建时间:  2017-12-19 9:44
 * 功能介绍:
 */
public class OrderCardBean {

    @JSONField(name = "user_id")
    private Integer userId;

    @JSONField(name = "goods_id")
    private Integer goodsId;

    @JSONField(name = "buy_count")
    private Integer buyCount;

    @JSONField(name = "goods_spec")
    private String goodsSpec;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }
}
