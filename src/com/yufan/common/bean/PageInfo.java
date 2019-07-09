/**
 * 用于纯jsp分页
 */
package com.yufan.common.bean;

import java.util.List;
import java.util.Map;

public class PageInfo {
    private int recordSum;            //总记录数,取第二页时不用再统计
    private int currePage;            //当前页

    private int pageSize = 20;        //每页显示数据
    private int pageSum;            //总页数

    private int currePageRecord;    //当前页记录数量

    private int firstRecord;        //当前页开始的记录数

    //是否有下一页,和上一页

    private boolean hasNext;
    private boolean hasPrevious;
    private List<Object> resultList;//查询出来的数据集合
    private List<Map<String, Object>> resultListMap;//查询出来的数据集合

    private String sql;                 //hql语句
    private String orderby = null;     //排序
    private Boolean desc;

    public int getRecordSum() {
        return recordSum;
    }

    public void setRecordSum(int recordSum) {
        this.recordSum = recordSum;
    }

    public int getCurrePage() {
        return currePage;
    }

    public void setCurrePage(int currePage) {
        this.currePage = currePage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSum() {
        return pageSum;
    }

    public void setPageSum(int pageSum) {
        this.pageSum = pageSum;
    }

    public int getCurrePageRecord() {
        return currePageRecord;
    }

    public void setCurrePageRecord(int currePageRecord) {
        this.currePageRecord = currePageRecord;
    }

    public int getFirstRecord() {
        return firstRecord;
    }

    public void setFirstRecord(int firstRecord) {
        this.firstRecord = firstRecord;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public List<Object> getResultList() {
        return resultList;
    }

    public void setResultList(List<Object> resultList) {
        this.resultList = resultList;
    }

    public List<Map<String, Object>> getResultListMap() {
        return resultListMap;
    }

    public void setResultListMap(List<Map<String, Object>> resultListMap) {
        this.resultListMap = resultListMap;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public Boolean isDesc() {
        return desc;
    }

    public void setDesc(Boolean desc) {
        this.desc = desc;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
