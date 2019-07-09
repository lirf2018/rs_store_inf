package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbGoods;
import com.yufan.pojo.TbGoodsSku;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.task.dao.SaveInfoDao;
import com.yufan.util.CacheData;
import com.yufan.util.CommonMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 购物车去结算商品检验(本次改版支持全国地址,不在考虑配送方式)
 */
@Service("check_cart_order_v2")
public class CheckCardOrder_v2 implements ResultOut {

    private Logger LOG = Logger.getLogger(CheckCardOrder_v2.class);

    @Autowired
    private FindInfoDao findInfoDao;
    @Autowired
    private SaveInfoDao saveInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            //String
            Integer userId = bean.getInteger("user_id");
            String cartIds = bean.getString("cart_ids");
            if (!StringUtils.nonEmptyString(cartIds) || null == userId || 0 == userId) {
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), new JSONObject());
            }

            //查询用户购物车信息
            List<Map<String, Object>> listCartGoods = findInfoDao.loadOrderCartCheckGoodsListMap(cartIds, userId);
            if (null == listCartGoods) {
                LOG.info("------------>查询购物车信息为空null异常");
                //更新为失效商品
                saveInfoDao.updateOrderCardStatus(cartIds, 2);
                return packagMsg(ResultCode.GOODS_OUTTIME_ERROR.getResp_code(), new JSONObject());
            }

            boolean flag = false;
            boolean flagGoodsNum = false;
            String outTimeCartGoodsStr = "";//失效的购物车商品
            int getWay = -1;//用于判断取货方式是否一致
            //当前查询的购物车标识
            String cartIdArray[] = cartIds.split(",");
            for (int i = 0; i < cartIdArray.length; i++) {
                if (StringUtils.nonEmptyString(cartIdArray[i])) {
                    boolean isExsit = false;
                    int cartId = Integer.parseInt(cartIdArray[i]);
                    for (int j = 0; j < listCartGoods.size(); j++) {
                        int cartId_ = Integer.parseInt(listCartGoods.get(j).get("cart_id").toString());
                        if (cartId_ == cartId) {
                            isExsit = true;
                            int goodsId = Integer.parseInt(listCartGoods.get(j).get("goods_id").toString());
                            String goodsSpec = listCartGoods.get(j).get("goods_spec") == null ? "" : listCartGoods.get(j).get("goods_spec").toString();
                            BigDecimal salePrice = new BigDecimal(listCartGoods.get(j).get("sale_price").toString());
                            BigDecimal nowMoney = new BigDecimal(listCartGoods.get(j).get("now_money").toString());
                            int buyCount = Integer.parseInt(listCartGoods.get(j).get("goods_count").toString());
                            int goodsNum = Integer.parseInt(listCartGoods.get(j).get("goods_num").toString());
                            int isSingle = Integer.parseInt(listCartGoods.get(j).get("is_single").toString());
                            int goodsGetWay = Integer.parseInt(listCartGoods.get(j).get("get_way").toString());//1邮寄 4.自取 5配送
                            //当前系统配置的取货方式
                            int addrType = CacheData.sysCodeUserAddrType;//1全国地址2平台地址(自取/配送)
                            if ((addrType == 1 && goodsGetWay != 1) || (addrType != 1 && goodsGetWay == 1)) {
                                LOG.info("-->商品取货方式与系统配置方式不一致goodsId=" + goodsId + "   -->addrTypeSys=" + addrType + "(1全国地址2平台地址)");
                                return packagMsg(ResultCode.ORDER_NOT_POST.getResp_code(), new JSONObject());
                            }
                            //商品取货方式是否一致
                            if (-1 == getWay) {
                                getWay = goodsGetWay;
                            }
                            if (getWay != goodsGetWay) {
                                LOG.info("--->商品取货方式不一致");
                                return packagMsg(ResultCode.ORDER_NOT_POST.getResp_code(), new JSONObject());
                            }
                            //-----------------------------------------------------独立生成订单校验规则------------------------------------------
                            //商品券(单个生成订单)(sql已加该条件)
                            //带押金的商品独立为一个订单(sql已加该条件)
                            //虚拟商品,独立一个订单(sql已加该条件)
                            //限购商品,独立一个订单(sql已加该条件)
                            //租赁商品(单个生成订单)(sql已加该条件)
                            TbGoods goods = CacheData.goodsMap.get(goodsId);
                            boolean goodsOrderSingle = CommonMethod.getInstance().checkGoodsOrderSingle(goods);
                            if (goodsOrderSingle) {
                                flag = true;
                                outTimeCartGoodsStr = outTimeCartGoodsStr + cartId + ",";
                            }
                            //-----------------------------------------------------独立生成订单校验规则------------------------------------------
                            if (isSingle == 0) {
                                //查询sku商品
                                TbGoodsSku goodsSku = CacheData.skuMap.get(goodsId + "-" + goodsSpec);
                                if (null == goodsSku) {
                                    LOG.info("---->sku商品不存在" + cartId);
                                    flag = true;
                                    outTimeCartGoodsStr = outTimeCartGoodsStr + cartId + ",";
                                }

                                BigDecimal skuGoodsPrice = goodsSku.getNowMoney();
                                if (skuGoodsPrice.compareTo(salePrice) != 0) {
                                    LOG.info("---->销售价格与商品sku价格不符合" + cartId);
                                    flag = true;
                                    outTimeCartGoodsStr = outTimeCartGoodsStr + cartId + ",";
                                }

                                int skuNum = goodsSku.getSkuNum();
                                if (buyCount > skuNum) {
                                    flagGoodsNum = true;
                                    flag = true;
                                    LOG.info("---->购物车数量>商品sku库存" + cartId);
                                }
                            } else {
                                //--------------------------单品------------
                                if (salePrice.compareTo(nowMoney) != 0) {
                                    LOG.info("---->销售价格与商品价格不符合" + cartId);
                                    flag = true;
                                    outTimeCartGoodsStr = outTimeCartGoodsStr + cartId + ",";
                                }
                                if (buyCount > goodsNum) {
                                    flagGoodsNum = true;
                                    flag = true;
                                    LOG.info("---->购物车数量>商品库存" + cartId);
                                }
                            }
                        }
                    }
                    if (!isExsit) {
                        //商品失效
                        LOG.info("---商品失效（商品不满足条件）-->" + cartId);
                        flag = true;
                        outTimeCartGoodsStr = outTimeCartGoodsStr + cartId + ",";
                    }
                }
            }

            if (flagGoodsNum) {
                LOG.info("---商品库存不足--");
                return packagMsg(ResultCode.PART_GOODS_STORE_NOENOUGH.getResp_code(), new JSONObject());
            }
            if (flag) {
                LOG.info("---------->商品已失效->" + outTimeCartGoodsStr);
                //更新失效商品
                outTimeCartGoodsStr = outTimeCartGoodsStr.substring(0, outTimeCartGoodsStr.length() - 1);
                saveInfoDao.updateOrderCardStatus(outTimeCartGoodsStr, 2);
                return packagMsg(ResultCode.GOODS_OUTTIME_ERROR.getResp_code(), new JSONObject());
            }
            return packagMsg(ResultCode.OK.getResp_code(), new JSONObject());
        } catch (Exception e) {

        }
        return packagMsg(ResultCode.OUT_OF_TIME.getResp_code(), dataJson);
    }
}
