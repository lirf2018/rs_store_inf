package com.yufan.pojo;

import java.util.Date;

/**
 * TbGoodsAttribute entity. @author MyEclipse Persistence Tools
 */

public class TbGoodsAttribute implements java.io.Serializable {

    // Fields

    private Integer attrId;
    private Integer goodsId;
    private Integer propId;
    private Integer valueId;
    private Date createtime;

    // Constructors

    /**
     * default constructor
     */
    public TbGoodsAttribute() {
    }

    /**
     * full constructor
     */
    public TbGoodsAttribute(Integer goodsId, Integer propId, Integer valueId,
                            Date createtime) {
        this.goodsId = goodsId;
        this.propId = propId;
        this.valueId = valueId;
        this.createtime = createtime;
    }

    // Property accessors

    public Integer getAttrId() {
        return this.attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public Integer getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getPropId() {
        return this.propId;
    }

    public void setPropId(Integer propId) {
        this.propId = propId;
    }

    public Integer getValueId() {
        return this.valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

}