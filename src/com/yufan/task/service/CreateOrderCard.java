package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.*;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.bean.OrderCardBean;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.task.dao.SaveInfoDao;
import com.yufan.util.CacheData;
import com.yufan.util.CommonMethod;
import com.yufan.util.DatetimeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-27 16:10
 * 功能介绍: 增加购物车
 */
@Service("create_ordercard")
public class CreateOrderCard implements ResultOut {

    private Logger LOG = Logger.getLogger(CreateOrderCard.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Autowired
    private SaveInfoDao saveInfoDao;


    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {

            OrderCardBean orderCardBean = JSONObject.toJavaObject(bean, OrderCardBean.class);

            if (!checkData(orderCardBean)) {
                LOG.info("----------->缺少必要参数");
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), new JSONObject());
            }
            int goodsIdParam = orderCardBean.getGoodsId();
            int userId = orderCardBean.getUserId();
            String goodsSpecParam = orderCardBean.getGoodsSpec();
            int goodsCountParam = orderCardBean.getBuyCount();

            //检验独立订单
            if (!checkGoodsSingleOrder(goodsIdParam)) {
                LOG.info("-------->购物车容量不足");
                return packagMsg(ResultCode.FULL_ORDER_CARD.getResp_code(), new JSONObject());
            }

            //购物车容量  最多30个商品(包括有效的和无校的)
            int cardCount = findInfoDao.loadOrderCartGoodsCount(orderCardBean.getUserId());
            if ((cardCount + goodsCountParam) > 30) {
                LOG.info("-------->购物车容量不足");
                return packagMsg(ResultCode.FULL_ORDER_CARD.getResp_code(), new JSONObject());
            }

            //查询有效商品
            TbGoods goods = CacheData.goodsMap.get(goodsIdParam);
            if (null == goods) {
                LOG.info("------>有效商品信息不存在goodsId=" + goodsIdParam);
                return packagMsg(ResultCode.GOODS_NOT_EXIST.getResp_code(), new JSONObject());
            }
            boolean flag = CommonMethod.getInstance().checkGoodsInfoEff(goods);
            if (!flag) {
                LOG.info("---flag--->有效商品信息不存在goodsId=" + goodsIdParam);
                return packagMsg(ResultCode.GOODS_NOT_EXIST.getResp_code(), new JSONObject());
            }

            //检查商品限购规则
            int limitGoodsNum = goods.getLimitNum();//商品限购数
            int limitGoodsWay = goods.getLimitWay();//限购方式: 1.每天一次2每月一次3.每年一次4不限购5只允许购买一次
            int isSingleG = goods.getIsSingle();//是否单品 0不是单品1是单品
            int goodsStore = goods.getGoodsNum();//商品库存
            int shopId = goods.getShopId();
            TbShop shop = CacheData.shopMap.get(shopId);
            String shopName = shop.getShopName();
            int partnersId = goods.getPartnersId();
            TbPartners partners = CacheData.partnersMap.get(partnersId);
            String partnersName = partners.getPartnersName();
            String goodsName = goods.getGoodsName();
            String goodsImg = goods.getGoodsImg();

            //查询购物车是否存在相同的或者相同规格的商品
            TbOrderCart orderCart_ = findInfoDao.loadTbOrderCart(userId, goodsIdParam, orderCardBean.getGoodsSpec());
            if (null != orderCart_) {
                int buyCount = orderCart_.getGoodsCount();
                goodsCountParam = goodsCountParam + buyCount;
            }


            BigDecimal nowNoney = goods.getNowMoney();//销售价格
            BigDecimal trueMoney = goods.getTrueMoney();//原价格
            String goodsSpecName = "";
            String goodsSpecNameStr = "";
            if (0 != limitGoodsNum && limitGoodsWay != 4) {
                //判断商品限购数
                if (goodsCountParam > limitGoodsNum) {
                    LOG.info("-------->超出限购数goodsId=" + goodsIdParam + "  购买数=" + goodsCountParam + "  商品限购数=" + limitGoodsNum);
                    return packagMsg(ResultCode.FAIL_ADD_CARD_LIMIT_GOODS.getResp_code(), new JSONObject());
                }
                //商品限购规则(限购时间开始之后的数据)
                if (1 == limitGoodsWay) {
                    //1.每天一次
                    String now = DatetimeUtil.getNow("yyyy-MM-dd");
                    List<Map<String, Object>> list = findInfoDao.loadGoodsOrderDetailLimitRuleByDateListMap(userId, goodsIdParam, now);
                    if (list.size() > 0) {
                        int hasBuyCount = Integer.parseInt(String.valueOf(list.get(0).get("goods_count")));//已购买数
                        if (hasBuyCount >= limitGoodsNum) {
                            LOG.info("商品限购---每天一次---->已达到限购数--->已购买数=" + hasBuyCount + "  限购数=" + limitGoodsNum);
                            return packagMsg(ResultCode.FAIL_ADD_CARD_LIMIT_GOODS.getResp_code(), new JSONObject());
                        }
                    }
                } else if (2 == limitGoodsWay) {
                    //2每月一次
                    String month = DatetimeUtil.getNow("yyyy-MM");
                    List<Map<String, Object>> list = findInfoDao.loadGoodsOrderDetailLimitRuleByMonthListMap(userId, goodsIdParam, month);
                    if (list.size() > 0) {
                        int hasBuyCount = Integer.parseInt(String.valueOf(list.get(0).get("goods_count")));//已购买数
                        if (hasBuyCount >= limitGoodsNum) {
                            LOG.info("商品限购---每月一次---->已达到限购数--->已购买数=" + hasBuyCount + "  限购数=" + limitGoodsNum);
                            return packagMsg(ResultCode.FAIL_ADD_CARD_LIMIT_GOODS.getResp_code(), new JSONObject());
                        }
                    }
                } else if (3 == limitGoodsWay) {
                    //每年一次
                    String year = DatetimeUtil.getNow("yyyy");
                    List<Map<String, Object>> list = findInfoDao.loadGoodsOrderDetailLimitRuleByYearListMap(userId, goodsIdParam, year);
                    if (list.size() > 0) {
                        int hasBuyCount = Integer.parseInt(String.valueOf(list.get(0).get("goods_count")));//已购买数
                        if (hasBuyCount >= limitGoodsNum) {
                            LOG.info("商品限购---每年一次---->已达到限购数--->已购买数=" + hasBuyCount + "  限购数=" + limitGoodsNum);
                            return packagMsg(ResultCode.FAIL_ADD_CARD_LIMIT_GOODS.getResp_code(), new JSONObject());
                        }
                    }

                } else if (5 == limitGoodsWay) {
                    //只允许购买一次
                    List<Map<String, Object>> list = findInfoDao.loadGoodsOrderDetailLimitRuleListMap(userId, goodsIdParam);
                    if (list.size() > 0) {
                        LOG.info("商品限购---只允许购买一次---->已达到限购数");
                        return packagMsg(ResultCode.FAIL_ADD_CARD_LIMIT_GOODS.getResp_code(), new JSONObject());
                    }
                }
            }

            //检测库存
            if (isSingleG == 1) {
                //是单品
                //判断商品库存
                if (goodsCountParam > goodsStore) {
                    LOG.info("----购买数量大于商品库存------->商品库存goodsId=" + goodsIdParam);
                    return packagMsg(ResultCode.STORE_EMPTY.getResp_code(), new JSONObject());
                }
            } else {
                //sku商品
                TbGoodsSku sku = CacheData.skuMap.get(goodsIdParam + "-" + goodsSpecParam);
                if (null == sku) {
                    LOG.info("----------->sku不存在goodsId=" + goodsIdParam + "  goodsSpec=" + goodsSpecParam);
                    return packagMsg(ResultCode.GOODS_NOT_EXIST.getResp_code(), new JSONObject());
                }
                int skuStore = sku.getSkuNum();
                if (goodsCountParam > skuStore) {
                    LOG.info("----------->sku库存不足goodsId=" + goodsIdParam + "  goodsSpec=" + goodsSpecParam + "  skuId=" + sku.getSkuId());
                    return packagMsg(ResultCode.STORE_EMPTY.getResp_code(), new JSONObject());
                }
                nowNoney = sku.getNowMoney();
                trueMoney = sku.getTrueMoney();
                String skuImg = sku.getSkuImg();
                if (StringUtils.nonEmptyString(skuImg) && !"null".equals(sku.getSkuImg()) && !"".equals(sku.getSkuImg())) {
                    goodsImg = sku.getSkuImg();
                }
                goodsSpecName = sku.getSkuName();
                //goodsSpecNameStr
                //查询属性值
                String valueIds = goodsSpecParam.replace(";", ",");
                valueIds = valueIds.substring(0, valueIds.length() - 1);
                LOG.info("----->valueIds=" + valueIds);
                List<Map<String, Object>> valueMapList = findInfoDao.loadTbPropsValueListMap(valueIds);
                if (null == valueMapList || valueMapList.size() == 0) {
                    LOG.info("----------->查询属性值不存在" + goodsIdParam + "  goodsSpec=" + goodsSpecParam);
                    return packagMsg(ResultCode.GOODS_NOT_EXIST.getResp_code(), new JSONObject());
                }
                Map<Integer, String> sMap = new HashMap<Integer, String>();
                for (int i = 0; i < valueMapList.size(); i++) {
                    int valueId = Integer.parseInt(String.valueOf(valueMapList.get(i).get("value_id")));
                    String spaceNameStr = String.valueOf(valueMapList.get(i).get("goods_space_name_str"));
                    sMap.put(valueId, spaceNameStr);
                }
                String spaceCodeArr[] = goodsSpecParam.split(";");
                for (int i = 0; i < spaceCodeArr.length; i++) {
                    int valueId_ = Integer.parseInt(spaceCodeArr[i]);
                    goodsSpecNameStr = goodsSpecNameStr + sMap.get(valueId_);
                }
                goodsSpecNameStr = goodsSpecNameStr.trim();
            }

            if (null == orderCart_) {
                TbOrderCart orderCart = new TbOrderCart();
                orderCart.setUserId(orderCardBean.getUserId());
                orderCart.setGoodsId(orderCardBean.getGoodsId());
                orderCart.setGoodsName(goodsName);
                orderCart.setGoodsImg(goodsImg);
                orderCart.setGoodsSpec(goodsSpecParam);
                orderCart.setGoodsSpecName(goodsSpecName);
                orderCart.setGoodsCount(goodsCountParam);
                orderCart.setGoodsPrice(nowNoney.doubleValue());
                orderCart.setTrueMoney(trueMoney.doubleValue());
                orderCart.setShopId(shopId);
                orderCart.setShopName(shopName);
                orderCart.setPartnersId(partnersId);
                orderCart.setPartnersName(partnersName);
                orderCart.setCreatetime(new Date());
                orderCart.setStatus(1);
                orderCart.setGoodsSpecNameStr(goodsSpecNameStr);
                int id = saveInfoDao.saveEntity(orderCart);
                LOG.info("-------->增加购物车id=" + id);
                dataJson.put("id", id);
            } else {
                saveInfoDao.updateOrderCardNum(userId, orderCart_.getCartId(), goodsCountParam);
                LOG.info("-------->更新购物车id=" + orderCart_.getCartId());
                dataJson.put("id", orderCart_.getCartId());
            }
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }

    private boolean checkData(OrderCardBean orderCardBean) {
        try {
            if (null != orderCardBean && null != orderCardBean.getUserId() && 0 != orderCardBean.getUserId()
                    && null != orderCardBean.getGoodsId() && orderCardBean.getBuyCount() != null && 0 != orderCardBean.getBuyCount().intValue()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            LOG.info("检验异常--->" + e);
        }
        return false;
    }


    /**
     * 检验是否允许增加到购物车
     *
     * @return
     */
    private boolean checkGoodsSingleOrder(int goodsId) {
        try {
            //-----------------------------------------------------独立生成订单校验规则------------------------------------------
            //商品券(单个生成订单)
            //带押金的商品独立为一个订单
            //抢购商品独立一个订单
            //虚拟商品,独立一个订单
            //限购商品,独立一个订单
            //租赁商品,话费商品(单个生成订单)
            //-----------------------------------------------------独立生成订单校验规则------------------------------------------
            TbGoods goods = CacheData.goodsMap.get(goodsId);
            boolean goodsOrderSingle = CommonMethod.getInstance().checkGoodsOrderSingle(goods);
            if (goodsOrderSingle) {
                return false;
            }
            return true;
        } catch (Exception e) {
            LOG.info("检验是否允许增加到购物车异常", e);
        }
        return false;
    }

}
