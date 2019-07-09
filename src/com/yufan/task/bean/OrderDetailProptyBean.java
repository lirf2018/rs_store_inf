package com.yufan.task.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-28 9:15
 * 功能介绍:
 */
public class OrderDetailProptyBean {


    @JSONField(name = "property_key")
    private String propertyKey;

    @JSONField(name = "property_value")
    private String propertyValue;

    @JSONField(name = "remark")
    private String remark;
    private String onlyMark;//唯一标识,同一个详情与同一个详情主属性的关系uuid+时间戳

    public String getPropertyKey() {
        return propertyKey;
    }

    public void setPropertyKey(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
