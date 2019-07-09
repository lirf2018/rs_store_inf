package com.yufan.service;

import com.yufan.pojo.TbInfAccount;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-14 18:22
 * 功能介绍:
 */
public interface BasicService {

    /**
     * 查询接口账号信息
     *
     * @param account
     * @return
     */
    public TbInfAccount loadTbInfAccount(String account);
}
