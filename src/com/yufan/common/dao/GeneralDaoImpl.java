package com.yufan.common.dao;

import com.yufan.common.bean.PageInfo;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 数据库操作类
 */
@Repository
public class GeneralDaoImpl implements GeneralDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 取得hibernate当前Session对象
     */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T findById(Class<T> type, Serializable id) {
        return (T) sessionFactory.getCurrentSession().get(type, id);
    }

    @Override
    public List<?> list(String hql, Map<String, Object> params) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        if (params != null) {
            for (Entry<String, Object> item : params.entrySet()) {
                query.setParameter(item.getKey(), item.getValue());
            }
        }
        return query.list();
    }

    //	@Override
//	public List<?> queryListByHql(String hql, Object... values) {
//		Session session = sessionFactory.getCurrentSession();
//		Query query = session.createQuery(hql);
//		if(values!=null){
//			for (int i = 0; i < values.length; i++) {
//				query.setParameter(i, values[i]);
//			}
//		}
//		return query.list();
//	}
    public <T> List<T> queryListByHql(String hql, Object... values) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return (List<T>) query.list();
    }

    @Override
    public List<Object[]> queryListBySql(String sql, Object... values) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query.list();
    }

    @Override
    public <T> List<T> queryListBySql(String sql, Class<T> clazz, Object... values) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(clazz);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query.list();
    }

    @Override
    public <T> T queryUniqueByHql(String queryString, Object... values) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return (T) query.uniqueResult();
    }

    @Override
    public <T> T queryUniqueBySql(String sql, Class<T> clazz, Object... values) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(clazz);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return (T) query.uniqueResult();
    }

    @Override
    public int executeUpdate(String hql, Map<String, Object> params) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        if (params != null) {
            for (Entry<String, Object> item : params.entrySet()) {
                query.setParameter(item.getKey(), item.getValue());
            }
        }
        return query.executeUpdate();
    }

    @Override
    public int executeByHql(String hql, Object... values) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query.executeUpdate();
    }

    @Override
    public int executeUpdateForSQL(String sql, Object... values) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql);
        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query.executeUpdate();
    }

    @Override
    public int save(Object entity) {
        Session session = sessionFactory.getCurrentSession();
        return (int) session.save(entity);
    }

    //	@Override
//	public void save(Collection<Object> entities) {
//		for (Object entity : entities) {
//			sessionFactory.getCurrentSession().save(entity);
//		}
//	}
    @Override
    public void saveOrUpdate(Object entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(Object entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public void delete(Collection<Object> entities) {
        Session session = sessionFactory.getCurrentSession();
        for (Object entity : entities) {
            session.delete(entity);
        }
    }

    @Override
    public void deleteById(Class<?> type, Serializable id) {
        if (id == null) {
            return;
        }
        Object entity = findById(type, id);
        if (entity == null) {
            return;
        }
        delete(entity);
    }

    @Override
    public List<Map<String, Object>> getBySql2(String sql, Object... values) {
        Session session = sessionFactory.getCurrentSession();
        try {
            SQLQuery query = session.createSQLQuery(sql);
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    query.setParameter(i, values[i]);
                }
            }
            query.setResultTransformer(new ResultTransformer() {
                // alues=值 columns=列名
                public Object transformTuple(Object[] values, String[] columns) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    for (int i = 0; i < columns.length; i++) {
                        map.put(columns[i], values[i] == null ? "" : values[i]);
                    }
                    return map;
                }

                public List transformList(List columns) {
                    return columns;
                }
            });

            return query.list();
        } catch (HibernateException he) {
            return null;
        } finally {
//			session.flush();
//			session.close();
        }
    }

    public PageInfo sqlPageInfo(PageInfo page) {
        Session session = sessionFactory.getCurrentSession();
        String fromHql = "";
        fromHql = page.getSql();

        if (page.getRecordSum() == 0) {//计算总数
            String countHql = "SELECT COUNT(*) FROM (" + fromHql + ") AS COU";
            Query countQuery = session.createSQLQuery(countHql);
//			Query countQuery = session.createQuery(countHql);
            Object totalRow = countQuery.list().iterator().next();
            page.setRecordSum(totalRow == null ? Integer.parseInt("0") : Integer.parseInt(totalRow.toString()));
        }

        account(page);

        if (page.getRecordSum() > 0) {//查询记录信息
            Query query = session.createSQLQuery(page.getSql());
            query.setFirstResult((page.getCurrePage() - 1) * page.getPageSize());
            query.setMaxResults(page.getPageSize());
            List resultList = query.list();
            page.setResultList(resultList);
            return page;
        }
//		session.close();
        return null;
    }

    public PageInfo sqlPageMapInfo(PageInfo page, Object... values) {
        Session session = sessionFactory.getCurrentSession();
        String fromHql = "";
        fromHql = page.getSql();

        if (page.getRecordSum() == 0) {//计算总数
            String countHql = "SELECT COUNT(*) FROM (" + fromHql + ") AS COU";
            Query countQuery = session.createSQLQuery(countHql);
            Object totalRow = countQuery.list().iterator().next();
            page.setRecordSum(totalRow == null ? Integer.parseInt("0") : Integer.parseInt(totalRow.toString()));
        }

        account(page);

        if (page.getRecordSum() > 0) {//查询记录信息
            Query query = session.createSQLQuery(page.getSql());
            query.setFirstResult((page.getCurrePage() - 1) * page.getPageSize());
            query.setMaxResults(page.getPageSize());
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    query.setParameter(i, values[i]);
                }
            }
            query.setResultTransformer(new ResultTransformer() {
                // alues=值 columns=列名
                public Object transformTuple(Object[] values, String[] columns) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    for (int i = 0; i < columns.length; i++) {
                        map.put(columns[i], values[i] == null ? "" : values[i]);
                    }
                    return map;
                }

                public List transformList(List columns) {
                    return columns;
                }
            });
            page.setResultListMap(query.list());
            return page;
        }
        page.setResultListMap(new ArrayList<Map<String, Object>>());
        return page;
    }

    /**
     * 计算分页数据
     */
    private static void account(PageInfo pi) {
        /**
         * 计算分页导航信息
         */
        //计算总页

        pi.setPageSum((pi.getRecordSum() % pi.getPageSize() == 0) ? pi.getRecordSum() / pi.getPageSize() : pi.getRecordSum() / pi.getPageSize() + 1);
        if (pi.getPageSum() == 0) pi.setPageSum(1);
        //计算当前页码
        if (pi.getCurrePage() < 1) pi.setCurrePage(1);
        else if (pi.getCurrePage() > pi.getPageSum()) pi.setCurrePage(pi.getPageSum());
        //计算当前页的记录数量
        pi.setCurrePageRecord(pi.getRecordSum() - (pi.getPageSize() * (pi.getCurrePage() - 1)));
        if (pi.getCurrePageRecord() > pi.getPageSize()) pi.setCurrePageRecord(pi.getPageSize());
        if (pi.getCurrePageRecord() < 0) pi.setCurrePageRecord(0);
        pi.setFirstRecord(pi.getPageSize() * (pi.getCurrePage() - 1));
        //计算是否有下一页上一页
        pi.setHasNext((pi.getCurrePage() < pi.getPageSum()) ? true : false);
        pi.setHasPrevious((pi.getCurrePage() > 1) ? true : false);
        if (pi.getRecordSum() == 0) {//如果没有记录
            pi.setResultList(new ArrayList());
        }
    }


}

