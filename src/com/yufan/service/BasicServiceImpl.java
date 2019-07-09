package com.yufan.service;

import com.yufan.common.dao.GeneralDao;
import com.yufan.pojo.TbInfAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-14 18:22
 * 功能介绍:
 */
@Service("basicService")
@Transactional
public class BasicServiceImpl implements BasicService {

    @Autowired
    private GeneralDao generalDao;

    /**
     * 查询接口账号信息
     *
     * @param account
     * @return
     */
    public TbInfAccount loadTbInfAccount(String account) {
        String hql = " from TbInfAccount where sid=? and status=? ";
        return generalDao.queryUniqueByHql(hql, account, 1);
    }
}
