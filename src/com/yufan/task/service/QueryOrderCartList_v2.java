package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbParam;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.CacheData;
import com.yufan.util.FlushCacheData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-22 23:13
 * 功能介绍:  查询购物车列表  购物车按商家分组展现
 */
@Service("qurey_order_cart_list_v2")
public class QueryOrderCartList_v2 implements ResultOut {
    private Logger LOG = Logger.getLogger(QueryOrderCartList_v2.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer userId = bean.getInteger("user_id");
            if (null == userId || userId.intValue() == 0) {
                LOG.info("--------->用户标识不能为空");
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            //取货方式:1邮寄4自取5配送
            Integer getWay = bean.getInteger("get_way");

            //查询用户购物车商品
            List<Map<String, Object>> listCartGoods = findInfoDao.loadOrderCartGoodsListMap(userId);
            //商家
            Map<Integer, String> partnersMap = new HashMap<Integer, String>();//商家map
            List<Integer> listPartners = new ArrayList<Integer>();//商家list
            for (int i = 0; i < listCartGoods.size(); i++) {
                Map<String, Object> pMap = listCartGoods.get(i);
                int partnersId = Integer.parseInt(String.valueOf(pMap.get("partners_id")));
                String partnersName = String.valueOf(pMap.get("partners_name"));
                if (null == partnersMap.get(partnersId)) {
                    partnersMap.put(partnersId, partnersName);
                    listPartners.add(partnersId);
                }
            }
            List<Map<String, Object>> outListEffective = new ArrayList<Map<String, Object>>();//有效的购物车商品列表
            List<Map<String, Object>> outListUneffective = new ArrayList<Map<String, Object>>();//无效的购物车商品列表

            for (int i = 0; i < listPartners.size(); i++) {
                int partnersId = listPartners.get(i);
                Map<String, Object> peffMap = new HashMap<String, Object>();//有效商品商家
                //循环购物车商品
                List<Map<String, Object>> effective = new ArrayList<Map<String, Object>>();//有效的购物车商品列表
                boolean flagEff = false;

                for (int j = 0; j < listCartGoods.size(); j++) {
                    Map<String, Object> sMap = listCartGoods.get(j);
                    int partnersId_ = Integer.parseInt(String.valueOf(sMap.get("partners_id")));
                    if (partnersId == partnersId_) {//属于一个商家
                        //判断状态有效的还有无效的
                        int status = Integer.parseInt(String.valueOf(sMap.get("status")));//状态0无效1有效2商品被编辑无效3已下单4已删除
                        if (status == 1) {
                            int goodsGetWay = Integer.parseInt(String.valueOf(sMap.get("get_way")));
                            if (null != getWay && goodsGetWay != getWay) {
                                continue;
                            }
                            effective.add(sMap);
                            flagEff = true;
                        } else if (outListUneffective.size() < 11) {
                            outListUneffective.add(sMap);
                        }
                    }
                }
                if (flagEff) {
                    peffMap.put("partners_id", partnersId);
                    peffMap.put("partners_name", partnersMap.get(partnersId));
                    peffMap.put("effective", effective);
                    outListEffective.add(peffMap);
                }
            }
            dataJson.put("advance_price", CacheData.orderAdvancePrice); //查询订单预付款
            dataJson.put("list_effective", outListEffective);
            dataJson.put("list_uneffective", outListUneffective);
            dataJson.put("addr_type", CacheData.sysCodeUserAddrType);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
