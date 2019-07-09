package com.yufan.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 获取配置文件属性值信息
 *
 * @author lirf
 */
public class ConfigEntityProperty {

    private static final Logger log = Logger.getLogger(ConfigEntityProperty.class);
    private static Properties property = new Properties();

    public ConfigEntityProperty() {
        InputStream input = null;
        try {
            input = ConfigEntityProperty.class.getResourceAsStream("/entity.properties");
            property.load(input);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取配置文件entity.properties信息出错:" + e.getMessage());
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public String getConfigValue(String key) {
        return property.getProperty(key);
    }

}
