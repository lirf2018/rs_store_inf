package com.yufan.util;

import com.alibaba.fastjson.JSONObject;
import com.yufan.pojo.*;
import com.yufan.task.dao.FindInfoDao;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 创建人: lirf
 * 创建时间:  2018/11/5 11:47
 * 功能介绍:  更新缓存
 */
public class FlushCacheData {

    private static Logger LOG = Logger.getLogger(FlushCacheData.class);

    private static volatile FlushCacheData flushCacheData;

    public static FlushCacheData getInstence() {
        if (flushCacheData == null) {
            synchronized (FlushCacheData.class) {
                if (flushCacheData == null) {
                    flushCacheData = new FlushCacheData();
                }
            }
        }
        return flushCacheData;
    }

    public void flushCacheAllData(FindInfoDao findInfoDao) {
        initParam(findInfoDao);
        initPlatform(findInfoDao);
        initGoods(findInfoDao);
        initShop(findInfoDao);
        initPartners(findInfoDao);
    }

    /**
     * 更新参数
     */
    public void initParam(FindInfoDao findInfoDao) {
        try {
            LOG.info("----------开始更新参数缓存----------");
            int status = 1;
            int isMakeSure = 1;
            List<TbParam> paramList = findInfoDao.loadTbParamList(status, isMakeSure);
            CacheData.paramList = paramList;
            for (int i = 0; i < paramList.size(); i++) {
                TbParam param = paramList.get(i);
                String paramCode = param.getParamCode();
                String paramKey = param.getParamKey();
                String paramValue = param.getParamValue();
                CacheData.paramMap.put(paramCode + "-" + paramKey, paramValue);
                //系统参数
                if ("sys_code".endsWith(paramCode)) {
                    if ("use_addr_type".equals(paramKey)) {
                        //设置邮寄地址方式:1全国地址2商家自定义地址
                        CacheData.sysCodeUserAddrType = Integer.parseInt(paramValue);
                    } else if ("order_advance_price".equals(paramKey)) {
                        CacheData.orderAdvancePrice = new BigDecimal(paramValue);
                    }
                } else if ("time_goods_out".equals(paramCode)) {
                    CacheData.timeGoodsOut = paramValue;
                }
            }
            LOG.info("----------结束更新参数缓存----------");
        } catch (Exception e) {
            LOG.info("更新参数缓存异常", e);
        }
    }

    /**
     * 更新平台地址
     */
    public void initPlatform(FindInfoDao findInfoDao) {
        try {
            LOG.info("----------开始更新平台地址----------");
            List<TbDistributionAddr> distributionAddrList = findInfoDao.loadTbDistributionAddrList();
            for (int i = 0; i < distributionAddrList.size(); i++) {
                CacheData.platformMap.put(distributionAddrList.get(i).getId(), distributionAddrList.get(i));
            }
            LOG.info("----------结束更新平台地址----------");
        } catch (Exception e) {
            LOG.info("更新平台地址异常", e);
        }
    }

    /**
     * 更新商品,商品sku
     */
    public void initGoods(FindInfoDao findInfoDao) {
        try {
            LOG.info("----------开始更新商品----------");
            List<Integer> goodsIdsList = new ArrayList<>();
            List<TbGoods> goodsList = findInfoDao.loadTbGoodsList();
            for (int i = 0; i < goodsList.size(); i++) {
                if (goodsList.get(i).getIsSingle() == 0) {
                    goodsIdsList.add(goodsList.get(i).getGoodsId());
                }
                CacheData.goodsMap.put(goodsList.get(i).getGoodsId(), goodsList.get(i));
            }
            LOG.info("----------结束更新商品----------");
            if (goodsIdsList.size() > 0) {
                List<Map<String, Object>> skuListMap = findInfoDao.loadGoodsSku();
                LOG.info("----------开始更新商品sku----------");
                for (int i = 0; i < goodsIdsList.size(); i++) {
                    int goodsId = goodsIdsList.get(i);
                    List<Map<String, Object>> l = new ArrayList<>();
                    for (int j = 0; j < skuListMap.size(); j++) {
                        int skuGoodsId = Integer.parseInt(skuListMap.get(j).get("goods_id").toString());
                        if (goodsId == skuGoodsId) {
                            Integer skuId = Integer.parseInt(skuListMap.get(j).get("sku_id").toString());
                            String skuName = skuListMap.get(j).get("sku_name").toString();
                            BigDecimal trueMoney = new BigDecimal(skuListMap.get(j).get("true_money").toString());
                            BigDecimal nowMoney = new BigDecimal(skuListMap.get(j).get("now_money_").toString());
                            String skuCode = skuListMap.get(j).get("sku_code").toString();
                            String propCode = skuListMap.get(j).get("prop_code").toString();
                            Integer skuNum = Integer.parseInt(skuListMap.get(j).get("sku_num").toString());
                            String skuImg = skuListMap.get(j).get("sku_img").toString();
                            BigDecimal purchasePrice = new BigDecimal(skuListMap.get(j).get("purchase_price").toString());
                            l.add(skuListMap.get(j));

                            TbGoodsSku goodsSkuInfo = new TbGoodsSku();
                            goodsSkuInfo.setSkuId(skuId);
                            goodsSkuInfo.setGoodsId(goodsId);
                            goodsSkuInfo.setSkuName(skuName);
                            goodsSkuInfo.setTrueMoney(trueMoney);
                            goodsSkuInfo.setNowMoney(nowMoney);
                            goodsSkuInfo.setSkuCode(skuCode);
                            goodsSkuInfo.setPropCode(propCode);
                            goodsSkuInfo.setSkuNum(skuNum);
                            goodsSkuInfo.setSkuImg(skuImg);
                            goodsSkuInfo.setPurchasePrice(purchasePrice);
                            CacheData.skuMap.put(goodsId + "-" + propCode, goodsSkuInfo);
                        }
                    }
                    CacheData.goodsSkuListMap.put(goodsId, l);
                }
                LOG.info("----------结束更新商品sku----------");

            }
        } catch (Exception e) {
            LOG.info("更新商品异常", e);
        }
    }

    /**
     * 更新店铺
     */
    public void initShop(FindInfoDao findInfoDao) {
        try {
            LOG.info("----------开始更新店铺----------");
            List<TbShop> shopList = findInfoDao.loadTbShopList();
            for (int i = 0; i < shopList.size(); i++) {
                CacheData.shopMap.put(shopList.get(i).getShopId(), shopList.get(i));
            }
            LOG.info("----------结束更新店铺----------");
        } catch (Exception e) {
            LOG.info("更新店铺异常", e);
        }
    }

    /**
     * 更新商家
     */
    public void initPartners(FindInfoDao findInfoDao) {
        try {
            LOG.info("----------开始更新商家----------");
            List<TbPartners> partnersList = findInfoDao.loadTbPartnersList();
            for (int i = 0; i < partnersList.size(); i++) {
                CacheData.partnersMap.put(partnersList.get(i).getId(), partnersList.get(i));
            }
            LOG.info("----------结束更新商家----------");
        } catch (Exception e) {
            LOG.info("更新更新商家", e);
        }
    }
}
