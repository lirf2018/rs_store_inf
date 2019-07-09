package com.yufan.util;


public class ConfigPath {


    /**
     * 获取文件目录
     *
     * @return
     * @throws
     */
    public String getWebRoot() {
        String path = getClass().getProtectionDomain().getCodeSource()
                .getLocation().getPath();
        System.out.println(path);
        if (path.indexOf("webapps") > 0) {
            path = path.substring(0, path.indexOf("webapps"));
        }
        return path;
    }

    public static void main(String[] args) {
        System.out.println(new ConfigPath().getWebRoot());
    }
}
