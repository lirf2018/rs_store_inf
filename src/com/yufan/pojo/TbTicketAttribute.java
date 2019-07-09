package com.yufan.pojo;

import java.util.Date;

/**
 * TbTicketAttribute entity. @author MyEclipse Persistence Tools
 */

public class TbTicketAttribute implements java.io.Serializable {

    // Fields

    private Integer attrId;
    private Integer ticketId;
    private Integer propId;
    private Integer valueId;
    private Date createtime;

    // Constructors

    /**
     * default constructor
     */
    public TbTicketAttribute() {
    }

    /**
     * full constructor
     */
    public TbTicketAttribute(Integer ticketId, Integer propId, Integer valueId,
                             Date createtime) {
        this.ticketId = ticketId;
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

    public Integer getTicketId() {
        return this.ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
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