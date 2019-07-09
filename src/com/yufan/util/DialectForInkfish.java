/**
 * 项目名:hc_task
 * 版权:
 * 文件编号:
 * JDK版本:1.7
 * 命名空间:com.uhuibao.util
 * 版本:1.0.0
 * 创建人:dengkc
 * 创建时间:2014-12-31 下午1:45:16
 * 修改人:
 * 修改时间:
 * 描述:
 */
package com.yufan.util;

import java.sql.Types;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * 创建人： dengkc
 * 描述：写一个类、修改hibernate配置文件。 写一个Dialect的子类，这里我 extends MySQL5Dialect类
 * DialectForInkfish.java
 */
public class DialectForInkfish extends MySQL5Dialect {
    public DialectForInkfish() {
        super();
        registerHibernateType(Types.LONGVARCHAR, 65535, "text");
    }
}
