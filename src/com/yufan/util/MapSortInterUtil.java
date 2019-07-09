package com.yufan.util;

import java.util.*;

/**
 * 创建人: lirf
 * 创建时间:  2018/11/28 14:58
 * 功能介绍: Map 按Key排序和按Value排序
 */
public class MapSortInterUtil {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

        map.put(3, 6);
        map.put(4, 9);
        map.put(8, 7);
        map.put(5, 3);
        map.put(1, 3);
        map.put(6, 4);

        Map<Integer, Integer> resultMap = sortMapByValue(map);    //按Key进行排序

        for (Map.Entry<Integer, Integer> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    /**
     * 使用 Map按key进行排序(降序)
     *
     * @param map
     * @return
     */
    public static Map<Integer, Integer> sortMapByKey(Map<Integer, Integer> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<Integer, Integer> sortMap = new TreeMap<Integer, Integer>(new MapKeyComparator());
        sortMap.putAll(map);

        return sortMap;
    }

    /**
     * 使用 Map按value进行排序(降序)
     *
     * @param oriMap
     * @return
     */
    public static Map<Integer, Integer> sortMapByValue(Map<Integer, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<Map.Entry<Integer, Integer>>(
                oriMap.entrySet());
        Collections.sort(entryList, new MapValueComparator());

        Iterator<Map.Entry<Integer, Integer>> iter = entryList.iterator();
        Map.Entry<Integer, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

}

class MapKeyComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }
}

class MapValueComparator implements Comparator<Map.Entry<Integer, Integer>> {

    @Override
    public int compare(Map.Entry<Integer, Integer> me1, Map.Entry<Integer, Integer> me2) {

        return me2.getValue() - me1.getValue();
    }
}