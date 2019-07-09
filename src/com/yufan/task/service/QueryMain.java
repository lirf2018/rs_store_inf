package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbParam;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.CacheData;
import com.yufan.util.DatetimeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-21 14:09
 * 功能介绍: mian页
 */
@Service("qurey_main")
public class QueryMain implements ResultOut {

    private Logger LOG = Logger.getLogger(QueryMain.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer bannelSize = bean.getInteger("bannel_size");
            Integer activitiySize = bean.getInteger("activitiy_size");
            Integer timeGoodsSize = bean.getInteger("time_goods_size");
            Integer hotGoodsSize = bean.getInteger("hot_goods_size");
            Integer sortGoodsSize = bean.getInteger("sort_goods_size");
            Integer newGoodsSize = bean.getInteger("new_goods_size");


            //查询bannel 6个
            List<Map<String, Object>> bannelList = findInfoDao.loadBannelListMap(bannelSize == null ? 6 : bannelSize);
            //查询活动 4 个
            List<Map<String, Object>> activityList = findInfoDao.loadActivityListMap(activitiySize == null ? 4 : activitiySize);
            //查询抢购商品 10个
            List<Map<String, Object>> timeGoodsList = new ArrayList<Map<String, Object>>();
            //查询抢购时间
            dataJson.put("out_time_goods", "");
            String timeGoodsOutStr = CacheData.paramMap.get("time_goods_out-time_goods_out");
            if (StringUtils.nonEmptyString(timeGoodsOutStr)) {
                //判断抢购时间是否过期
                String passTime = timeGoodsOutStr;
                String nowTime = DatetimeUtil.getNow();
                if (null != passTime && !"".equals(passTime) && DatetimeUtil.compareDateTime(nowTime, passTime) == -1) {
                    dataJson.put("out_time_goods", passTime);
                    timeGoodsList = findInfoDao.loadTimeGoodsListMap(timeGoodsSize == null ? 15 : timeGoodsSize);
                }
            }
            //查询热卖商品 10个
            List<Map<String, Object>> hotGoodsListOut = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> hotGoodsList = findInfoDao.loadHotGoodsListMap(hotGoodsSize == null ? 10 : hotGoodsSize);
            for (int i = 0; i < hotGoodsList.size(); i++) {
                Map<String, Object> map = hotGoodsList.get(i);
                //处理sku价格
                String skuPrice = String.valueOf(map.get("sku_now_money"));
                String isSingle = String.valueOf(map.get("is_single"));
                String[] strArrs = skuPrice.split(",");
                if ("0".equals(isSingle)) {
                    map.put("low_money", strArrs[0]);
                    if (strArrs.length == 1) {
                        map.put("sku_now_money", strArrs[0]);
                        hotGoodsListOut.add(map);
                    } else if (strArrs.length > 2) {
                        String skuprice = strArrs[0] + "-" + strArrs[strArrs.length - 1];
                        map.put("sku_now_money", skuprice);
                        hotGoodsListOut.add(map);
                    } else {
                        LOG.info("------->热卖商品sku价格处理异常");
                    }
                } else {
                    map.put("low_money", String.valueOf(map.get("now_money")));
                    hotGoodsListOut.add(map);
                }
            }

            //查询推荐(按sort排序)商品 10个
            List<Map<String, Object>> sortGoodsListOut = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> sortGoodsList = findInfoDao.loadSortGoodsListMap(sortGoodsSize == null ? 10 : sortGoodsSize);
            for (int i = 0; i < sortGoodsList.size(); i++) {
                Map<String, Object> map = sortGoodsList.get(i);
                //处理sku价格
                String skuPrice = String.valueOf(map.get("sku_now_money"));
                String isSingle = String.valueOf(map.get("is_single"));
                String[] strArrs = skuPrice.split(",");
                if ("0".equals(isSingle)) {
                    map.put("low_money", strArrs[0]);
                    if (strArrs.length == 1) {
                        map.put("sku_now_money", strArrs[0]);
                        sortGoodsListOut.add(map);
                    } else if (strArrs.length > 2) {
                        String skuprice = strArrs[0] + "-" + strArrs[strArrs.length - 1];
                        map.put("sku_now_money", skuprice);
                        sortGoodsListOut.add(map);
                    } else {
                        LOG.info("------->热卖商品sku价格处理异常");
                    }
                } else {
                    map.put("low_money", String.valueOf(map.get("now_money")));
                    sortGoodsListOut.add(map);
                }
            }

            //查询最新商品  10个
            List<Map<String, Object>> newGoodsListOut = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> newGoodsList = findInfoDao.loadNewGoodsListMap(newGoodsSize == null ? 10 : newGoodsSize);
            for (int i = 0; i < newGoodsList.size(); i++) {
                Map<String, Object> map = newGoodsList.get(i);
                //处理sku价格
                String skuPrice = String.valueOf(map.get("sku_now_money"));
                String isSingle = String.valueOf(map.get("is_single"));
                String[] strArrs = skuPrice.split(",");
                if ("0".equals(isSingle)) {
                    map.put("low_money", strArrs[0]);
                    if (strArrs.length == 1) {
                        map.put("sku_now_money", strArrs[0]);
                        newGoodsListOut.add(map);
                    } else if (strArrs.length > 2) {
                        String skuprice = strArrs[0] + "-" + strArrs[strArrs.length - 1];
                        map.put("sku_now_money", skuprice);
                        newGoodsListOut.add(map);
                    } else {
                        LOG.info("------->热卖商品sku价格处理异常");
                    }
                } else {
                    map.put("low_money", String.valueOf(map.get("now_money")));
                    newGoodsListOut.add(map);
                }
            }


            dataJson.put("bannel_list", bannelList);
            dataJson.put("activity_list", activityList);
            dataJson.put("time_goods_list", timeGoodsList);
            dataJson.put("hot_goods_list", hotGoodsListOut);
            dataJson.put("sort_goods_list", sortGoodsListOut);
            dataJson.put("new_goods_list", newGoodsListOut);

            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
