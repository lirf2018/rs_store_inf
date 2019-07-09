package com.yufan.job;

import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.FlushCacheData;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 创建人: lirf
 * 创建时间:  2019/3/13 15:32
 * 功能介绍: 初始化缓存
 */
@Service
public class InitCacheData implements InitializingBean {

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public void afterPropertiesSet() throws Exception {
        FlushCacheData.getInstence().flushCacheAllData(findInfoDao);
    }
}
