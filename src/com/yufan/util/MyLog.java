package com.yufan.util;

import org.apache.log4j.Logger;

/**
 * @功能名称 自定义日志
 * @作者 lirongfan
 * @时间 2016年11月22日 下午3:38:10
 */
public class MyLog {
    private static final Logger logger = Logger.getLogger(MyLog.class);

    public static Logger getLogger() {
        return logger;
    }
}
