package com.yufan.util;

import java.util.UUID;

/**
 * 功能名称: uuid生成
 * 开发人: lirf
 * 开发时间: 2017下午11:20:17
 * 其它说明：
 */
public class UUIDGenerator {

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13)
                + str.substring(14, 18) + str.substring(19, 23)
                + str.substring(24);
        return temp;
    }

    // 获得指定数量的UUID
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }

    public static void main(String[] args) {
//		String[] ss = getUUID(1);
//		for (int i = 0; i < ss.length; i++) {
//			System.out.println("ss[" + i + "]=====" + ss[i]);
//		}

        System.out.println(getUUID());
//		String str="b4fc86a774f74667b6bff4ab7a4b693d";
//		System.out.println(str.length());
    }
}
