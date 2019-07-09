package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbGoods;
import com.yufan.pojo.TbGoodsSku;
import com.yufan.pojo.TbParam;
import com.yufan.pojo.TbTimeGoods;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.CacheData;
import com.yufan.util.CommonMethod;
import com.yufan.util.DatetimeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2018-01-10 11:47
 * 功能介绍: 单个商品下单前校验
 */
@Service("check_goods_order")
public class CheckGoodsOrder implements ResultOut {

    private Logger LOG = Logger.getLogger(CheckGoodsOrder.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {

            Integer goodsIdParam = bean.getInteger("goods_id");
            Integer timeGoodsIdParam = bean.getInteger("time_goods_id");
            Integer userId = bean.getInteger("user_id");
            String goodsSpaceParam = bean.getString("goods_space");
            Integer goodsCountParam = bean.getInteger("order_count");
            BigDecimal salePrice = bean.getBigDecimal("sale_price");

            if (goodsIdParam == null || null == userId || null == goodsCountParam || null == timeGoodsIdParam || goodsCountParam.intValue() == 0 || null == salePrice) {
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), new JSONObject());
            }

            //查询有效商品
            TbGoods goods = CacheData.goodsMap.get(goodsIdParam);
            if (null == goods) {
                LOG.info("------>有效商品信息不存在goodsId=" + goodsIdParam);
                return packagMsg(ResultCode.GOODS_NOT_EXIST.getResp_code(), dataJson);
            }
            boolean flag = CommonMethod.getInstance().checkGoodsInfoEff(goods);
            if (!flag) {
                LOG.info("---flag--->有效商品信息不存在goodsId=" + goodsIdParam);
                return packagMsg(ResultCode.GOODS_NOT_EXIST.getResp_code(), dataJson);
            }


            //实时查询有效商品数据
            int isSingleD = goods.getIsSingle();//是否单品 0不是单品1是单品
            int goodsStoreD = goods.getGoodsNum();//商品库存
            BigDecimal nowPriceD = goods.getNowMoney();//现价
            int getWay = goods.getGetWay();//
            //检查商品限购规则
            int limitGoodsNumD = goods.getLimitNum();//商品限购数
            int limitGoodsWayD = goods.getLimitWay();//限购方式: 1.每天一次2每月一次3.每年一次4不限购5只允许购买一次

            //当前系统配置的取货方式
            int addrType = CacheData.sysCodeUserAddrType;//1全国地址2平台地址
            if ((addrType == 1 && getWay != 1) || (addrType != 1 && getWay == 1)) {
                LOG.info("-->商品取货方式与系统配置方式不一致goodsId=" + goodsIdParam + "   -->addrTypeSys=" + addrType + "(1全国地址2平台地址)");
                return packagMsg(ResultCode.GOODS_CHECK_NOT_POST.getResp_code(), new JSONObject());
            }

            //商品限购--------------------
            String checkGoodsLimitStr = checkGoodsLimitRule(limitGoodsNumD, limitGoodsWayD, goodsIdParam, goodsCountParam, userId);
            if (null == checkGoodsLimitStr) {
                return packagMsg(ResultCode.INTERFACE_CALL_FAIL.getResp_code(), dataJson);
            } else if (!"".equals(checkGoodsLimitStr)) {
                return checkGoodsLimitStr;
            }
            //--------------------
            if (isSingleD == 1) {
                //是单品 判断商品库存
                if (goodsCountParam > goodsStoreD) {
                    LOG.info("----购买数量大于商品库存------->商品库存goodsId=" + goodsIdParam);
                    return packagMsg(ResultCode.STORE_EMPTY.getResp_code(), new JSONObject());
                }
                //判断是否是抢购商品
                if (null != timeGoodsIdParam && 0 != timeGoodsIdParam) {
                    //是抢购商品 判断抢购是否结束
                    String timeGoodsOutStr = CacheData.paramMap.get("time_goods_out-time_goods_out");
                    if (StringUtils.nonEmptyString(timeGoodsOutStr)) {
                        //判断抢购时间是否过期
                        String passTime = timeGoodsOutStr;
                        String nowTime = DatetimeUtil.getNow();
                        if (null == passTime || "".equals(passTime) || DatetimeUtil.compareDateTime(nowTime, passTime) == 1) {
                            LOG.info("---->查询抢购商品--抢购时间过期-->");
                            return packagMsg(ResultCode.GOODS_NOT_EXIST.getResp_code(), new JSONObject());
                        }
                    }
                    TbTimeGoods timeGoods = findInfoDao.loadTbTimeGoodsInfo(timeGoodsIdParam, goodsIdParam);
                    if (null == timeGoods) {
                        LOG.info("---->查询抢购商品异常goodsId=" + goodsIdParam + "  timeGoodsIdParam=" + timeGoodsIdParam);
                        return packagMsg(ResultCode.GOODS_NOT_EXIST.getResp_code(), new JSONObject());
                    }
                    //检验抢购规则
                    int timeGoodsId = timeGoods.getId();
                    int timeGoodsStore = timeGoods.getGoodsStore();//抢购商品库存
                    int limitTimeGoodsNum = timeGoods.getLimitNum();//限购数
                    int limitTimeGoodsWay = timeGoods.getTimeWay();//限购方式: 1.每天一次2每月一次3.每年一次4不限购5只允许购买一次
                    //判断抢购商品库存
                    if (goodsCountParam > timeGoodsStore) {
                        LOG.info("---->抢购商品设置的库存不足goodsId=" + goodsIdParam + "  timeGoodsId=" + timeGoods.getId());
                        return packagMsg(ResultCode.STORE_EMPTY.getResp_code(), new JSONObject());
                    }
                    //有商品限购规则
                    if (0 != limitTimeGoodsNum && limitTimeGoodsWay != 4) {
                        //判断限购数
                        if (goodsCountParam > limitTimeGoodsNum) {
                            LOG.info("-------->抢购商品超出限购数goodsId=" + goodsIdParam + "  timeGoodsId=" + timeGoods.getId() + "  购买数=" + goodsCountParam + "  抢购限购数=" + limitTimeGoodsNum);
                            return packagMsg(ResultCode.FAIL_ADD_CARD_LIMIT_GOODS.getResp_code(), new JSONObject());
                        }
                        //商品限购规则(限购时间开始之后的数据)
                        if (1 == limitTimeGoodsWay) {
                            //1.每天一次
                            String now = DatetimeUtil.getNow("yyyy-MM-dd");
                            List<Map<String, Object>> list = findInfoDao.loadTimeGoodsOrderDetailLimitRuleByDateListMap(userId, timeGoodsId, now);
                            if (list.size() > 0) {
                                int hasBuyCount = Integer.parseInt(String.valueOf(list.get(0).get("goods_count")));//已购买数
                                if (hasBuyCount >= limitTimeGoodsNum) {
                                    LOG.info("抢购商品--每天一次---->已达到限购数--->已购买数=" + hasBuyCount + "  限购数=" + limitTimeGoodsNum);
                                    return packagMsg(ResultCode.FAIL_ADD_CARD_LIMIT_GOODS.getResp_code(), dataJson);
                                }
                            }
                        } else if (2 == limitTimeGoodsWay) {
                            //2每月一次
                            String month = DatetimeUtil.getNow("yyyy-MM");
                            List<Map<String, Object>> list = findInfoDao.loadTimeGoodsOrderDetailLimitRuleByMonthListMap(userId, timeGoodsId, month);
                            if (list.size() > 0) {
                                int hasBuyCount = Integer.parseInt(String.valueOf(list.get(0).get("goods_count")));//已购买数
                                if (hasBuyCount >= limitTimeGoodsNum) {
                                    LOG.info("抢购商品--每月一次---->已达到限购数--->已购买数=" + hasBuyCount + "  限购数=" + limitTimeGoodsNum);
                                    return packagMsg(ResultCode.FAIL_ADD_CARD_LIMIT_GOODS.getResp_code(), dataJson);
                                }
                            }
                        } else if (3 == limitTimeGoodsWay) {
                            //每年一次
                            String year = DatetimeUtil.getNow("yyyy");
                            List<Map<String, Object>> list = findInfoDao.loadTimeGoodsOrderDetailLimitRuleByYearListMap(userId, timeGoodsId, year);
                            if (list.size() > 0) {
                                int hasBuyCount = Integer.parseInt(String.valueOf(list.get(0).get("goods_count")));//已购买数
                                if (hasBuyCount >= limitTimeGoodsNum) {
                                    LOG.info("抢购商品--每年一次---->已达到限购数--->已购买数=" + hasBuyCount + "  限购数=" + limitTimeGoodsNum);
                                    return packagMsg(ResultCode.FAIL_ADD_CARD_LIMIT_GOODS.getResp_code(), dataJson);
                                }
                            }
                        } else if (5 == limitTimeGoodsWay) {
                            //只允许购买一次
                            List<Map<String, Object>> list = findInfoDao.loadTimeGoodsOrderDetailLimitRuleListMap(userId, timeGoodsId);
                            if (list.size() > 0) {
                                LOG.info("抢购商品--只允许购买一次---->已达到限购数");
                                return packagMsg(ResultCode.FAIL_ADD_CARD_LIMIT_GOODS.getResp_code(), dataJson);
                            }
                        }
                    }
                    //判断商品抢购价格
                    BigDecimal timePriceG = new BigDecimal(timeGoods.getTimePrice());
                    if (timePriceG.compareTo(salePrice) != 0) {
                        LOG.info("----------->商品页面抢购价格有误" + goodsIdParam + " 商品抢购价格" + timePriceG + "  页面销售价=" + salePrice);
                        return packagMsg(ResultCode.ORDWE_PRICE_ERROR.getResp_code(), dataJson);
                    }
                } else {
                    //不是抢购商品
                    if (nowPriceD.compareTo(salePrice) != 0) {
                        LOG.info("----------->商品页面销售价格有误" + goodsIdParam + " 商品现价=" + nowPriceD + "  页面销售价=" + salePrice);
                        return packagMsg(ResultCode.ORDWE_PRICE_ERROR.getResp_code(), dataJson);
                    }
                }
            } else {
                //sku商品
                TbGoodsSku sku = CacheData.skuMap.get(goodsIdParam + "-" + goodsSpaceParam);
                if (null == sku) {
                    LOG.info("----------->sku不存在goodsId=" + goodsIdParam + "  goodsSpaceParam=" + goodsSpaceParam);
                    return packagMsg(ResultCode.GOODS_NOT_EXIST.getResp_code(), dataJson);
                }
                int skuStore = sku.getSkuNum();
                BigDecimal skuNowMoney = sku.getNowMoney();
                if (goodsCountParam > skuStore) {
                    LOG.info("----------->sku库存不足goodsId=" + goodsIdParam + "  goodsSpec=" + goodsSpaceParam + "  skuId=" + sku.getSkuId());
                    return packagMsg(ResultCode.STORE_EMPTY.getResp_code(), dataJson);
                }
                if (skuNowMoney.compareTo(salePrice) != 0) {
                    LOG.info("----------->商品页面销售价格有误" + goodsIdParam + " 商品sku现价=" + skuNowMoney + "  页面销售价=" + salePrice);
                    return packagMsg(ResultCode.ORDWE_PRICE_ERROR.getResp_code(), dataJson);
                }
            }
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
    }

    /**
     * 商品限购规则
     */
    private String checkGoodsLimitRule(int limitGoodsNumD, int limitGoodsWayD, int goodsIdParam, int goodsCountParam, int userId) {
        try {
            if (0 != limitGoodsNumD && limitGoodsWayD != 4) {
                //判断商品限购数
                if (goodsCountParam > limitGoodsNumD) {
                    LOG.info("-------->超出限购数goodsId=" + goodsIdParam + "  购买数=" + goodsCountParam + "  商品限购数=" + limitGoodsNumD);
                    return packagMsg(ResultCode.FAIL_ADD_CARD_LIMIT_GOODS.getResp_code(), new JSONObject());
                }
                //商品限购规则(限购时间开始之后的数据)
                if (1 == limitGoodsWayD) {
                    //1.每天一次
                    String now = DatetimeUtil.getNow("yyyy-MM-dd");
                    List<Map<String, Object>> list = findInfoDao.loadGoodsOrderDetailLimitRuleByDateListMap(userId, goodsIdParam, now);
                    if (list.size() > 0) {
                        int hasBuyCount = Integer.parseInt(String.valueOf(list.get(0).get("goods_count")));//已购买数
                        if (hasBuyCount >= limitGoodsNumD) {
                            LOG.info("商品限购---每天一次---->已达到限购数--->已购买数=" + hasBuyCount + "  限购数=" + limitGoodsNumD);
                            return packagMsg(ResultCode.FAIL_ADD_CARD_LIMIT_GOODS.getResp_code(), new JSONObject());
                        }
                    }
                } else if (2 == limitGoodsWayD) {
                    //2每月一次
                    String month = DatetimeUtil.getNow("yyyy-MM");
                    List<Map<String, Object>> list = findInfoDao.loadGoodsOrderDetailLimitRuleByMonthListMap(userId, goodsIdParam, month);
                    if (list.size() > 0) {
                        int hasBuyCount = Integer.parseInt(String.valueOf(list.get(0).get("goods_count")));//已购买数
                        if (hasBuyCount >= limitGoodsNumD) {
                            LOG.info("商品限购---每月一次---->已达到限购数--->已购买数=" + hasBuyCount + "  限购数=" + limitGoodsNumD);
                            return packagMsg(ResultCode.FAIL_ADD_CARD_LIMIT_GOODS.getResp_code(), new JSONObject());
                        }
                    }
                } else if (3 == limitGoodsWayD) {
                    //每年一次
                    String year = DatetimeUtil.getNow("yyyy");
                    List<Map<String, Object>> list = findInfoDao.loadGoodsOrderDetailLimitRuleByYearListMap(userId, goodsIdParam, year);
                    if (list.size() > 0) {
                        int hasBuyCount = Integer.parseInt(String.valueOf(list.get(0).get("goods_count")));//已购买数
                        if (hasBuyCount >= limitGoodsNumD) {
                            LOG.info("商品限购---每年一次---->已达到限购数--->已购买数=" + hasBuyCount + "  限购数=" + limitGoodsNumD);
                            return packagMsg(ResultCode.FAIL_ADD_CARD_LIMIT_GOODS.getResp_code(), new JSONObject());
                        }
                    }
                } else if (5 == limitGoodsWayD) {
                    //只允许购买一次
                    List<Map<String, Object>> list = findInfoDao.loadGoodsOrderDetailLimitRuleListMap(userId, goodsIdParam);
                    if (list.size() > 0) {
                        LOG.info("商品限购---只允许购买一次---->已达到限购数");
                        return packagMsg(ResultCode.FAIL_ADD_CARD_LIMIT_GOODS.getResp_code(), new JSONObject());
                    }
                }
            }
            return "";
        } catch (Exception e) {
            LOG.info("------商品限购规则异常--->");
        }
        return null;
    }
}
