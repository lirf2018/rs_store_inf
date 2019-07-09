package com.yufan.task.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

public class CheckOrderCartBean {

    @JSONField(name = "user_id")
    private int userId;

    @JSONField(name = "goods_ids")
    private String goodsIds;

    @JSONField(name = "order_counts")
    private String orderCounts;

    @JSONField(name = "goods_spaces")
    private String goodsSpaces;

    @JSONField(name = "shop_cart_id")
    private String shopCartId;

    @JSONField(name = "sale_price")
    private BigDecimal salePrice;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(String goodsIds) {
        this.goodsIds = goodsIds;
    }

    public String getOrderCounts() {
        return orderCounts;
    }

    public void setOrderCounts(String orderCounts) {
        this.orderCounts = orderCounts;
    }

    public String getGoodsSpaces() {
        return goodsSpaces;
    }

    public void setGoodsSpaces(String goodsSpaces) {
        this.goodsSpaces = goodsSpaces;
    }

    public String getShopCartId() {
        return shopCartId;
    }

    public void setShopCartId(String shopCartId) {
        this.shopCartId = shopCartId;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
}
