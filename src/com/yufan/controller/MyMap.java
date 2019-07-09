package com.yufan.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @功能名称 排序map
 * @作者 lirongfan
 * @时间 2016年5月31日 上午10:22:52
 */
public class MyMap {

    private HashMap map = new HashMap();
    private Set keySet = map.keySet();

    public Object get(String key) {
        return map.get(key);
    }

    @SuppressWarnings("unchecked")
    public void put(String key, Object value) {
        map.put(key, value);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void sort() {
        List list = new ArrayList(map.keySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object a, Object b) {
                return a.toString().toLowerCase().compareTo(b.toString().toLowerCase());
            }
        });

        this.keySet = new TreeSet(list);
    }

    public Set keySet() {
        return this.keySet;
    }

    public static void main(String[] args) {
        MyMap map = new MyMap();
        map.put("1", "yi");
        map.put("8", "ba");
        map.put("9", "jiu");
        map.put("7", "qi");
        map.put("5", "wu");
        map.put("6", "liu");
        map.put("4", "si");
        map.put("3", "san");
        map.put("2", "er");

//		for (Iterator it = map.keySet().iterator(); it.hasNext();) {
//			String key = (String) it.next();
//		}

        map.sort();
        for (Iterator it = map.keySet().iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            System.out.println("key[ " + key + "],   value[ " + map.get(key)
                    + "] ");
        }
    }
}
