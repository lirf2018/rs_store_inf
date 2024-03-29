package com.yufan.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.yufan.common.bean.PageInfo;

public interface GeneralDao {
    public <T> T findById(Class<T> type, Serializable id);

    public List<?> list(String hql, Map<String, Object> params);

    //	public List<?> queryListByHql(String hql,Object... values);
    public <T> List<T> queryListByHql(String hql, Object... values);

    public List<Object[]> queryListBySql(String sql, Object... values);

    public <T> List<T> queryListBySql(String sql, Class<T> clazz, Object... values);

    public <T> T queryUniqueByHql(String queryString, Object... values);

    public <T> T queryUniqueBySql(String sql, Class<T> clazz, Object... values);

    public int executeUpdate(String hql, Map<String, Object> params);

    public int executeByHql(String hql, Object... values);

    public int executeUpdateForSQL(String sql, Object... values);

    //	public void save(Object entity);
    public int save(Object entity);

//	public void save(Collection<Object> entities);

    public void saveOrUpdate(Object entity);

    public void delete(Object entities);

    public void delete(Collection<Object> entities);

    public void deleteById(Class<?> type, Serializable id);

    public List<Map<String, Object>> getBySql2(String sql, Object... values);

    public PageInfo sqlPageInfo(PageInfo page);

    public PageInfo sqlPageMapInfo(PageInfo page, Object... values);


}
