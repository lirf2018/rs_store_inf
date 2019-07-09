package com.yufan.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 获取配置文件属性值信息
 *
 * @author lirongfan
 */
public class ConfigProperty {

    private static final Logger log = Logger.getLogger(ConfigProperty.class);
    private static Properties property = new Properties();

    static {
        InputStream input = null;
        try {
            input = ConfigProperty.class.getResourceAsStream("/config.properties");
            property.load(input);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取配置文件config.properties信息出错:" + e.getMessage());
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getConfigValue(String key) {
        return property.getProperty(key);
    }

    public static void main(String[] args) {
        System.out.println(new ConfigProperty().getConfigValue("fw_path"));
    }

}
