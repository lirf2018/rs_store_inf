package com.yufan.pojo;

/**
 * TbTelephoneDetail entity. @author MyEclipse Persistence Tools
 */

public class TbTelephoneDetail implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer number;
    private String province;
    private String provinceCode;
    private String city;
    private String cityCode;
    private String operator;
    private String peratorCode;

    // Constructors

    /**
     * default constructor
     */
    public TbTelephoneDetail() {
    }

    /**
     * minimal constructor
     */
    public TbTelephoneDetail(Integer number, String operator) {
        this.number = number;
        this.operator = operator;
    }

    /**
     * full constructor
     */
    public TbTelephoneDetail(Integer number, String province,
                             String provinceCode, String city, String cityCode, String operator,
                             String peratorCode) {
        this.number = number;
        this.province = province;
        this.provinceCode = provinceCode;
        this.city = city;
        this.cityCode = cityCode;
        this.operator = operator;
        this.peratorCode = peratorCode;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceCode() {
        return this.provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getPeratorCode() {
        return this.peratorCode;
    }

    public void setPeratorCode(String peratorCode) {
        this.peratorCode = peratorCode;
    }

}