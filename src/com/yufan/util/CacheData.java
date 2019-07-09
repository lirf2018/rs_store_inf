package com.yufan.util;

import com.yufan.pojo.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建人: lirf
 * 创建时间:  2018/11/5 11:10
 * 功能介绍:  缓存数据
 */
public class CacheData {

    /**
     * 系统参数
     */
    //设置邮寄地址方式:1全国地址 2平台配送或者取地址
    public static int sysCodeUserAddrType = 2;
    //查询订单预付款
    public static BigDecimal orderAdvancePrice = new BigDecimal("100000");
    //抢购结束时间
    public static String timeGoodsOut = null;

    public static Map<String, String> paramMap = new HashMap<>();//key=参数编码-参数键, value=参数值

    public static List<TbParam> paramList = new ArrayList<>();


    /**
     * 平台地址 key=id   value=TbDistributionAddr
     */
    public static Map<Integer, TbDistributionAddr> platformMap = new HashMap<>();

    /**
     * 商品信息 key=id   value=TbGoods
     */
    public static Map<Integer, TbGoods> goodsMap = new HashMap<>();

    /**
     * 商家信息 key=id   value=TbPartners
     */
    public static Map<Integer, TbPartners> partnersMap = new HashMap<>();

    /**
     * 店铺信息 key=id   value=TbShop
     */
    public static Map<Integer, TbShop> shopMap = new HashMap<>();

    /**
     * 商品sku key=goodsId   value=List<Map<String, Object>>  (sku)
     */
    public static Map<Integer, List<Map<String, Object>>> goodsSkuListMap = new HashMap<>();

    /**
     * 商品sku key=goodsId-propCode   value=TbGoodsSku
     */
    public static Map<String, TbGoodsSku> skuMap = new HashMap<>();
}
