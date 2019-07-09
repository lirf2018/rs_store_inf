package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.*;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.bean.OrderBean;
import com.yufan.task.bean.OrderDetailBean;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.Base64Coder;
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
 * 创建时间:  2017-11-27 16:10
 * 功能介绍: 创建订单(本次改版支持全国地址)
 */
@Service("create_order_v2")
public class CreateOrder_v2 implements ResultOut {

    private Logger LOG = Logger.getLogger(CreateOrder_v2.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Autowired
    private SaveInfoService saveInfoService;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();//base64
        try {

            String base64OrderData = bean.getString("base64_order_data");
            if (null == base64OrderData) {
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            String base64OrderDataDecode = Base64Coder.decodeString(base64OrderData);
            JSONObject orderData = JSONObject.parseObject(base64OrderDataDecode);
            OrderBean orderBean = JSONObject.toJavaObject(orderData, OrderBean.class);
            String orderNo = DatetimeUtil.getNow("YYYYMMDD" + System.currentTimeMillis());
            if (null == orderBean || !saveOrderData(orderBean, orderNo)) {
                LOG.info("----->订单信息检验失败");
                return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
            }
            dataJson.put("order_no", orderNo);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("getResult----->" + e);
        }
        return packagMsg(ResultCode.NET_ERROR.getResp_code(), dataJson);
    }

    /**
     * 检验订单信息
     *
     * @return
     */
    public synchronized boolean saveOrderData(OrderBean orderBean, String orderNo) {
        try {
            //验证订单商品
            List<OrderDetailBean> orderDetailBeansList = orderBean.getOrderDetailList();
            //每个订单最多只能有10个不同类别的商品同一个(商品id+销售价格)为一个分类商品
            if (null == orderDetailBeansList || orderDetailBeansList.size() == 0 || orderDetailBeansList.size() > 11) {
                LOG.info("----->订单商品详情不存在或者10个不同类别的商品同一个(商品id+销售价格)为一个分类商品>10个===orderDetailBeansList.size()");
                return false;
            }
            boolean orderParam = CommonMethod.getInstance().checkOrderParam(orderBean);
            if (!orderParam) {
                LOG.info("---->参数非空检验不通过");
                return false;
            }

            //订单参数
            int userId = orderBean.getUserId();
            Integer userAddrId = orderBean.getUserAddrId();//收货地址地址标识
            int orderGoodsCountParam = orderBean.getGoodsCount();//订单数量
            BigDecimal postPriceParam = orderBean.getPostPrice();//

            /**
             * 配送费
             */
            BigDecimal postPrice = new BigDecimal(0);
            int addrType = CacheData.sysCodeUserAddrType;
            //地址类型1全国地址2平台配送或者自取地址
            if (addrType == 2) {
                LOG.info("---->平台配送或者自取地址");
                if (!StringUtils.nonEmptyString(orderBean.getUserPhone()) || !StringUtils.nonEmptyString(orderBean.getUserName())) {
                    LOG.info("----->订单参数缺少用户名称或电话 ");
                    return false;
                }
                TbDistributionAddr distributionAddr = CacheData.platformMap.get(userAddrId);
                if (null == distributionAddr) {
                    LOG.info("----->平台配送或者自取地址不存在,标识:  " + orderBean.getUserAddrId());
                    return false;
                }
                postPrice = new BigDecimal(distributionAddr.getFreight());//配置的运费
                orderBean.setUserAddr(distributionAddr.getDetailAddr());
                //快递人员
                orderBean.setPostMan(distributionAddr.getResponsibleMan());
                orderBean.setPostPhone(distributionAddr.getResponsiblePhone());
            } else {
                LOG.info("---->全国地址");
                //查询用户收货地址信息 判断地址类型
                TbUserAddr userAddr = findInfoDao.loadTbUserAddr(userId, userAddrId, 1);
                if (null == userAddr) {
                    LOG.info("----->查询用户收货地址信息不存在标识:  " + userAddrId);
                    return false;
                }

                String areaIds = userAddr.getAreaIds();
                areaIds = areaIds.replace("-", "','");
                List<Map<String, Object>> listUserAddrs = findInfoDao.queryUserAddrListMapByRegionCodes(areaIds);
                if (null == listUserAddrs || listUserAddrs.size() == 0) {
                    LOG.info("----->(全国地址表对应数据不存在)收货地址不存在,标识:  " + areaIds);
                    return false;
                }
                for (int i = 0; i < listUserAddrs.size(); i++) {
                    String freight = listUserAddrs.get(i).get("freight").toString();
                    if (postPrice.compareTo(new BigDecimal(freight)) < 0) {
                        postPrice = new BigDecimal(freight);
                        break;
                    }
                }
                orderBean.setUserAddr(userAddr.getAddrName());
                orderBean.setUserPhone(userAddr.getUserPhone());
                orderBean.setUserName(userAddr.getUserName());
            }
            orderBean.setPostPrice(postPrice);

            //判断邮费
            if (postPriceParam.compareTo(postPrice) != 0) {
                LOG.info("------->订单参数配送费总额=" + postPriceParam + "  实际配送费总额=" + postPrice);
                return false;
            }

            //数据库中得到的  商品总价  和  押金总价格  和  商品预付款总额
            BigDecimal goodsPriceAllStore = new BigDecimal(0);//商品总价格 = 商品销售价格 X 数量
            BigDecimal goodsDepositPriceStore = new BigDecimal(0);//押金总价格 = 商品押金 X 数量
            BigDecimal goodsAdvancePriceStore = new BigDecimal(0);//商品预付款总额 = 商品预付款 X 数量
            BigDecimal goodsPayOnlinePriceStore = new BigDecimal(0);//在线支付总价格 = 商品销售价格 X 数量（不支付货到付款总金额）

            int goodsCount = 0;//商品数量
            int goodsGetWay = -1;//用于比较商品取货方式

            //校验商品详情(循环)
            for (int i = 0; i < orderDetailBeansList.size(); i++) {
                OrderDetailBean orderDetailBean = orderDetailBeansList.get(i);//接收的商品详情
                int goodsIdParam = orderDetailBean.getGoodsId();
                String goodsSpecParam = orderDetailBean.getGoodsSpec();//商品规格
                int goodsCountParam = orderDetailBean.getGoodsCount();//实际购买数量
                int timeGoodsIdParam = orderDetailBean.getTimeGoodsId();//抢购商品标识

                //详情必填参数
                boolean detailParam = CommonMethod.getInstance().checkOrderDetailParam(orderDetailBean);
                if (!detailParam) {
                    LOG.info("--------详情缺少必要参数");
                    return false;
                }
                //查询有效商品
                TbGoods goods = CacheData.goodsMap.get(goodsIdParam);
                if (null == goods) {
                    LOG.info("------>有效商品信息不存在goodsId=" + goodsIdParam);
                    return false;
                }
                boolean flag = CommonMethod.getInstance().checkGoodsInfoEff(goods);
                if (!flag) {
                    LOG.info("---flag--->有效商品信息不存在goodsId=" + goodsIdParam);
                    return false;
                }
                //----------------------------------实时查询有效商品数据-------------------------------
                BigDecimal depositMoney = goods.getDepositMoney();//商品押金
                int isSingleG = goods.getIsSingle();//是否单品 0不是单品1是单品
                int goodsStore = goods.getGoodsNum();//商品库存
                BigDecimal nowPrice = goods.getNowMoney();//---------实时现价(商品销售价格,可能会根据商品类型变化)
                BigDecimal trueMoney = goods.getTrueMoney();//---------实时现原价
                BigDecimal purchasePrice = goods.getPurchasePrice();//---------实时进货价
                BigDecimal advancePrice = goods.getAdvancePrice();//---------商品预付款
                String goodsImg = goods.getGoodsImg();//不包括访问地址,只包括路径
                int shopId = goods.getShopId();
                TbShop shop = CacheData.shopMap.get(shopId);
                String shopName = shop.getShopName();
                String goodsName = goods.getGoodsName();
                int partnersId = goods.getPartnersId();
                TbPartners partners = CacheData.partnersMap.get(partnersId);
                String partnersName = partners.getPartnersName();
                int limitGoodsNum = goods.getLimitNum();//商品限购数
                int limitGoodsWay = goods.getLimitWay();//限购方式: 1.每天一次2每月一次3.每年一次4不限购5只允许购买一次
                int getWay = goods.getGetWay();//1邮寄 4.自取 5配送
                int ticketId = goods.getTicketId();//是否是商品券
                int isPayOnline = goods.getIsPayOnline();//是否线上支付 0可以不用线上支付 1只能线上支付
                int isYuding = goods.getIsYuding();//0非预定商品1预定商品
                int goodsType = goods.getGoodsType();//商品类型:0:实体商品1商品券2话费商品3租赁商品4服务商品
                orderBean.setPostWay(getWay);//配送方式 1.快递邮寄4.用户自取 5商家配送


                //订单参数
                if (timeGoodsIdParam > 0) {
                    //租赁>预定>抢购>正常下单
                    orderBean.setBusinessType(2);//业务类型 1.正常下单2.抢购3.预定 4 租赁
                }
                if (isYuding == 1) {
                    orderBean.setBusinessType(3);//业务类型 1.正常下单2.抢购3.预定 4 租赁
                }
                if (goodsType == 3) {
                    orderBean.setBusinessType(4);//业务类型 1.正常下单2.抢购3.预定 4 租赁
                }

                //--------------------------------------------------价格计算
                //商品押金
                goodsDepositPriceStore = goodsDepositPriceStore.add(depositMoney.multiply(new BigDecimal(goodsCountParam)));
                if (isPayOnline == 0) {// 0可以不用线上支付
                    goodsAdvancePriceStore = goodsAdvancePriceStore.add(advancePrice.multiply(new BigDecimal(goodsCountParam)));
                }

                //规则  1.如果商品为支持邮寄(则必须与系统配置一致)
                if ((addrType == 1 && getWay != 1) || (addrType != 1 && getWay == 1)) {
                    LOG.info("-->商品取货方式与系统配置方式不一致goodsId=" + goodsIdParam + "   -->addrTypeSys=" + addrType + "(1全国地址2平台地址)");
                    return false;
                }
                //规则同一次订单数据取货方式必须一至
                if (goodsGetWay == -1) {
                    goodsGetWay = getWay;
                }
                if (goodsGetWay != getWay) {
                    LOG.info("---->商品取货方式不一致");
                    return false;
                }
                //-----------------------------------------------------独立生成订单校验规则-------begin-----------------------------------
                //商品券(单个生成订单)
                //带押金的商品独立为一个订单
                //抢购商品独立一个订单
                //虚拟商品,独立一个订单
                //限购商品,独立一个订单
                //租赁商品,话费商品(单个生成订单)
                boolean goodsOrderSingle = CommonMethod.getInstance().checkGoodsOrderSingle(goods);

                //虚拟商品,独立一个订单
                if (goodsOrderSingle && orderDetailBeansList.size() > 1) {
                    LOG.info("--------------->如果是虚拟商品,独立一个订单");
                    return false;
                }
                //商品券(单个生成订单)
                if (goodsOrderSingle && (orderDetailBeansList.size() > 1)) {
                    LOG.info("---------->商品券(单个生成订单)且不能使用优惠券");
                    return false;
                }
                //带押金的商品独立为一个订单
                if (goodsOrderSingle && orderDetailBeansList.size() > 1) {
                    LOG.info("---------->带押金的商品独立为一个订单");
                    return false;
                }
                //根据请求的商品信息来确定是否是抢购商品还是原商品
                if (goodsOrderSingle && orderDetailBeansList.size() > 1) {
                    LOG.info("--------------->如果是抢购商品,独立一个订单");
                    return false;
                }

                //限购商品,独立一个订单
                if (goodsOrderSingle && orderDetailBeansList.size() > 1) {
                    LOG.info("---------->限购商品,独立一个订单");
                    return false;
                }
                //预定商品,独立一个订单
                if (goodsOrderSingle && orderDetailBeansList.size() > 1) {
                    LOG.info("---------->预定商品,独立一个订单");
                    return false;
                }
                //租赁商品(单个生成订单)
                if (goodsOrderSingle && orderDetailBeansList.size() > 1) {
                    LOG.info("---------->租赁商品,独立一个订单");
                    return false;
                }
                //-----------------------------------------------------独立生成订单校验规则-------end-----------------------------------

                //商品统一判断--------------------
                //检查商品限购规则
                if (0 != limitGoodsNum && limitGoodsWay != 4) {
                    //判断商品限购数
                    if (goodsCountParam > limitGoodsNum) {
                        LOG.info("-------->超出限购数goodsId=" + goodsIdParam + "  购买数=" + goodsCountParam + "  商品限购数=" + limitGoodsNum);
                        return false;
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
                                return false;
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
                                return false;
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
                                return false;
                            }
                        }
                    } else if (5 == limitGoodsWay) {
                        //只允许购买一次
                        List<Map<String, Object>> list = findInfoDao.loadGoodsOrderDetailLimitRuleListMap(userId, goodsIdParam);
                        if (list.size() > 0) {
                            LOG.info("商品限购---只允许购买一次---->已达到限购数");
                            return false;
                        }
                    }
                }
                if (isSingleG == 1) {
                    //是单品
                    //判断商品库存
                    if (goodsCountParam > goodsStore) {
                        LOG.info("----购买数量大于商品库存------->商品库存goodsId=" + goodsIdParam);
                        return false;
                    }
                    //判断是否是抢购商品
                    if (timeGoodsIdParam > 0) {
                        LOG.info("----------->抢购商品");
                        //是抢购商品 判断抢购是否结束
                        if (StringUtils.nonEmptyString(CacheData.timeGoodsOut)) {
                            String passTime = CacheData.timeGoodsOut;
                            String nowTime = DatetimeUtil.getNow();
                            if (null == passTime || "".equals(passTime) || DatetimeUtil.compareDateTime(nowTime, passTime) == 1) {
                                LOG.info("---->查询抢购商品--抢购时间过期-->");
                                return false;
                            }
                        }

                        TbTimeGoods timeGoods = findInfoDao.loadTbTimeGoodsInfo(timeGoodsIdParam, goodsIdParam);
                        if (null == timeGoods) {
                            LOG.info("---->查询抢购商品异常goodsId=" + goodsIdParam + "  timeGoodsIdParam=" + timeGoodsIdParam);
                            return false;
                        }
                        //检验抢购规则
                        int timeGoodsStore = timeGoods.getGoodsStore();//抢购商品库存
                        int limitTimeGoodsNum = timeGoods.getLimitNum();//限购数
                        int limitTimeGoodsWay = timeGoods.getTimeWay();//限购方式: 1.每天一次2每月一次3.每年一次4不限购5只允许购买一次
                        //判断抢购商品库存
                        if (goodsCountParam > timeGoodsStore) {
                            LOG.info("---->抢购商品设置的库存不足goodsId=" + goodsIdParam + "  timeGoodsId=" + timeGoods.getId());
                            return false;
                        }
                        //有商品限购规则
                        if (0 != limitTimeGoodsNum && limitTimeGoodsWay != 4) {
                            //判断限购数
                            if (goodsCountParam > limitTimeGoodsNum) {
                                LOG.info("-------->抢购商品超出限购数goodsId=" + goodsIdParam + "  timeGoodsId=" + timeGoods.getId() + "  购买数=" + goodsCountParam + "  抢购限购数=" + limitTimeGoodsNum);
                                return false;
                            }
                            //商品限购规则(限购时间开始之后的数据)
                            if (1 == limitTimeGoodsWay) {
                                //1.每天一次
                                String now = DatetimeUtil.getNow("yyyy-MM-dd");
                                List<Map<String, Object>> list = findInfoDao.loadTimeGoodsOrderDetailLimitRuleByDateListMap(userId, timeGoodsIdParam, now);
                                if (list.size() > 0) {
                                    int hasBuyCount = Integer.parseInt(String.valueOf(list.get(0).get("goods_count")));//已购买数
                                    if (hasBuyCount >= limitTimeGoodsNum) {
                                        LOG.info("抢购商品--每天一次---->已达到限购数--->已购买数=" + hasBuyCount + "  限购数=" + limitTimeGoodsNum);
                                        return false;
                                    }
                                }
                            } else if (2 == limitTimeGoodsWay) {
                                //2每月一次
                                String month = DatetimeUtil.getNow("yyyy-MM");
                                List<Map<String, Object>> list = findInfoDao.loadTimeGoodsOrderDetailLimitRuleByMonthListMap(userId, timeGoodsIdParam, month);
                                if (list.size() > 0) {
                                    int hasBuyCount = Integer.parseInt(String.valueOf(list.get(0).get("goods_count")));//已购买数
                                    if (hasBuyCount >= limitTimeGoodsNum) {
                                        LOG.info("抢购商品--每月一次---->已达到限购数--->已购买数=" + hasBuyCount + "  限购数=" + limitTimeGoodsNum);
                                        return false;
                                    }
                                }
                            } else if (3 == limitTimeGoodsWay) {
                                //每年一次
                                String year = DatetimeUtil.getNow("yyyy");
                                List<Map<String, Object>> list = findInfoDao.loadTimeGoodsOrderDetailLimitRuleByYearListMap(userId, timeGoodsIdParam, year);
                                if (list.size() > 0) {
                                    int hasBuyCount = Integer.parseInt(String.valueOf(list.get(0).get("goods_count")));//已购买数
                                    if (hasBuyCount >= limitTimeGoodsNum) {
                                        LOG.info("抢购商品--每年一次---->已达到限购数--->已购买数=" + hasBuyCount + "  限购数=" + limitTimeGoodsNum);
                                        return false;
                                    }
                                }
                            } else if (5 == limitTimeGoodsWay) {
                                //只允许购买一次
                                List<Map<String, Object>> list = findInfoDao.loadTimeGoodsOrderDetailLimitRuleListMap(userId, timeGoodsIdParam);
                                if (list.size() > 0) {
                                    LOG.info("抢购商品--只允许购买一次---->已达到限购数");
                                    return false;
                                }
                            }
                        }
                        //数据库中实时抢购价格
                        BigDecimal timePriceG = new BigDecimal(timeGoods.getTimePrice());
                        goodsPriceAllStore = goodsPriceAllStore.add(timePriceG.multiply(new BigDecimal(goodsCountParam)));
                        nowPrice = timePriceG;
                        if (isPayOnline == 1) {//1只能线上支付
                            goodsPayOnlinePriceStore = goodsPayOnlinePriceStore.add(timePriceG.multiply(new BigDecimal(goodsCountParam)));
                        }
                    } else {
                        //不是抢购商品
                        goodsPriceAllStore = goodsPriceAllStore.add(nowPrice.multiply(new BigDecimal(goodsCountParam)));
                        if (isPayOnline == 1) {//1只能线上支付
                            goodsPayOnlinePriceStore = goodsPayOnlinePriceStore.add(nowPrice.multiply(new BigDecimal(goodsCountParam)));
                        }
                    }
                } else {
                    //sku商品
                    TbGoodsSku sku = CacheData.skuMap.get(goodsIdParam + "-" + goodsSpecParam);
                    if (null == sku) {
                        LOG.info("----------->sku不存在goodsId=" + goodsIdParam + "  goodsSpec=" + goodsSpecParam);
                        return false;
                    }
                    String goodsSkuImg = sku.getSkuImg();
                    if (StringUtils.nonEmptyString(goodsSkuImg) && goodsSkuImg.indexOf("null") < 0) {
                        goodsImg = goodsSkuImg;
                    }
                    int skuStore = sku.getSkuNum();
                    BigDecimal skuNowMoney = sku.getNowMoney();
                    if (goodsCountParam > skuStore) {
                        LOG.info("----------->sku库存不足goodsId=" + goodsIdParam + "  goodsSpec=" + goodsSpecParam + "  skuId=" + sku.getSkuId());
                        return false;
                    }
                    goodsPriceAllStore = goodsPriceAllStore.add(skuNowMoney.multiply(new BigDecimal(goodsCountParam)));
                    nowPrice = skuNowMoney;
                    trueMoney = sku.getTrueMoney();
                    purchasePrice = sku.getPurchasePrice();
                    if (isPayOnline == 1) {//只能线上支付
                        goodsPayOnlinePriceStore = goodsPayOnlinePriceStore.add(skuNowMoney.multiply(new BigDecimal(goodsCountParam)));
                    }
                }
                //---------------------计算-------------------------------------------------------------------------------------
                goodsCount = goodsCount + goodsCountParam;

                //订单设置必要值
                orderBean.setPartnersId(partnersId);
                orderBean.setPartnersName(partnersName);

                //订单详情设置必要值
                orderDetailBean.setShopId(shopId);
                orderDetailBean.setShopName(shopName);
                orderDetailBean.setGoodsName(goodsName);
                orderDetailBean.setPartnersId(partnersId);
                orderDetailBean.setPartnersName(partnersName);
                orderDetailBean.setPurchasePrice(purchasePrice);//进货价格
                orderDetailBean.setTrueMoney(trueMoney);//原价
                orderDetailBean.setSalePrice(nowPrice);//
                orderDetailBean.setDepositPrice(goodsDepositPriceStore);
                if (timeGoodsIdParam > 0) {
                    orderDetailBean.setTimePrice(nowPrice);
                } else {
                    orderDetailBean.setTimePrice(new BigDecimal("0"));
                }
                orderDetailBean.setGoodsImg(goodsImg);
                if (ticketId > 0) {
                    TbTicket ticket = findInfoDao.loadTbTicketById(ticketId);
                    String ticketJson = JSONObject.toJSONString(ticket);
                    orderDetailBean.setIsTicket(1);
                    orderDetailBean.setTicketJson(ticketJson);
                } else {
                    orderDetailBean.setIsTicket(0);
                }
                if (timeGoodsIdParam > 0) {
                    orderDetailBean.setIsTimeGoods(1);
                } else {
                    orderDetailBean.setIsTimeGoods(0);
                }
                orderDetailBean.setIsSingle(isSingleG);
            }
            //----------------------------订单参数检验-----
            //判断数量
            if (orderGoodsCountParam != goodsCount) {
                LOG.info("---------->订单数量不相等orderGoodsCountParam=" + orderGoodsCountParam + "  goodsCount=" + goodsCount);
                return false;
            }

            //数据库价格校验------------------------开始------------------------------------------------------
            //----------------------------订单参数
            BigDecimal orderPriceParam = orderBean.getOrderPrice();//
            BigDecimal orderRealPriceParam = orderBean.getRealPrice();//
            BigDecimal advancePriceParam = orderBean.getAdvancePrice();//
            BigDecimal needpayPriceParam = orderBean.getNeedpayPrice();//
            BigDecimal depositPriceAllParam = orderBean.getDepositPriceAll();//
            BigDecimal discountsPriceParam = orderBean.getDiscountsPrice();//


            /**
             * 押金总额 = 商品押金 X 商品数量
             */
            if (depositPriceAllParam.compareTo(goodsDepositPriceStore) != 0) {
                LOG.info("------->订单参数押金总额=" + depositPriceAllParam + "  实际押金总额=" + goodsDepositPriceStore);
                return false;
            }


            /**
             * 订单实际金额 = 商品总金额 + 邮费 + 总押金
             */
            BigDecimal orderRealyPrice = goodsPriceAllStore.add(postPrice).add(goodsDepositPriceStore);
            if (orderRealPriceParam.compareTo(orderRealyPrice) != 0) {
                LOG.info("------->订单参数订单实际金额总额=" + orderRealPriceParam + "  订单实际金额总额(商品总金额 + 邮费 + 押金)=" + orderRealyPrice);
                return false;
            }

            //查询订单预付款(系统配置参数不检验数据状态性,不需要确认,只要数据存在,就是有效的)
            BigDecimal sysAdvancePrice = CacheData.orderAdvancePrice;
            /**
             * 优惠金额 = 优惠券金额
             */
            int discountsIdParam = orderBean.getDiscountsId();//优惠券标识qr
            if (discountsIdParam > 0) {
                //--------------------------------------------------------------------有优惠金额----------------------------------------------------------------------------------
                /**
                 * 查询优惠金额
                 */
                BigDecimal discountsPriceB = new BigDecimal(0);//优惠价格=卡券抵消的价格
                TbTicketDownQr ticketDownQr = findInfoDao.loadTbTicketDownQrInfoById(discountsIdParam);
                if (ticketDownQr.getUserId().intValue() != userId) {
                    LOG.info("-----------有优惠金额----1------>查询qr优惠券异常,用户无此优惠券discountsId=" + discountsIdParam);
                    return false;
                }
                if (null != ticketDownQr) {
                    discountsPriceB = new BigDecimal(ticketDownQr.getTicketValue());//--------优惠价格
                    //订单设置必要值
                    orderBean.setTicketJson(JSONObject.toJSONString(ticketDownQr));
                } else {
                    LOG.info("-----------有优惠金额----2------>查询qr优惠券异常discountsId=" + discountsIdParam);
                    return false;
                }
                BigDecimal orderRealyPrice2 = new BigDecimal("0");//减去优惠券后的金额
                if (discountsPriceB.compareTo(goodsPriceAllStore) > -1) {
                    //(商品免费)
                    //优惠金额校验
                    if (discountsPriceParam.compareTo(goodsPriceAllStore) != 0) {
                        LOG.info("------------有优惠金额----3----->优惠金额有误");
                        return false;
                    }
                    /**
                     *  实际订单价格2 = 邮费 + 押金总额
                     */
                    orderRealyPrice2 = postPrice.add(goodsDepositPriceStore);

                } else {
                    //优惠金额校验
                    if (discountsPriceParam.compareTo(discountsPriceB) != 0) {
                        LOG.info("------------有优惠金额----4----->优惠金额有误");
                        return false;
                    }
                    /**
                     *  实际订单价格2 = 邮费 + 押金总额 + 商品总额 - 优惠金额
                     */
                    orderRealyPrice2 = postPrice.add(goodsDepositPriceStore).add(goodsPriceAllStore).subtract(discountsPriceB);
                }

                /**
                 * 预付款 = 邮费 + 订单预付款
                 */
                BigDecimal advancePrice = postPrice.add(sysAdvancePrice);
                if (advancePrice.compareTo(orderRealyPrice2) > -1) {
                    if (advancePriceParam.compareTo(orderRealyPrice2) != 0) {
                        LOG.info("------------有优惠金额----5----->预付款 = 邮费 + 订单预付款");
                        return false;
                    }
                    //待付款=0
                    if (needpayPriceParam.compareTo(new BigDecimal("0")) != 0) {
                        LOG.info("-------------有优惠金额----6---->待付款!=0");
                        return false;
                    }
                } else {
                    //支付方式为现金,则预付款=订单支付金额
                    if (orderBean.getAdvancePayWay() == 0 && advancePriceParam.compareTo(orderRealyPrice2) != 0) {
                        LOG.info("--------有优惠金额---------10-----支付方式为现金,则预付款=订单支付金额");
                        return false;
                    } else {
                        if (advancePriceParam.compareTo(advancePrice) != 0) {
                            LOG.info("--------------有优惠金额----7--->预付款 = 邮费 + 订单预付款");
                            return false;
                        }
                        //待付款=0
                        if (needpayPriceParam.compareTo(orderRealyPrice2.subtract(advancePrice)) != 0) {
                            LOG.info("-------------有优惠金额----8---->待付款!=0");
                            return false;
                        }
                    }
                }
                //订单金额=实际订单价格2
                if (orderPriceParam.compareTo(orderRealyPrice2) != 0) {
                    LOG.info("------------有优惠金额----9----->订单金额!=实际订单价格2 ");
                    return false;
                }
            } else {
                //--------------------------------------------------------------------无优惠金额----------------------------------------------------------------------------------
                /**
                 * 预付款 = 邮费 + 订单预付款 + 商品预付款总额(支持线下付款类商品) + 只能线上支付的金额
                 */
                //支付方式为现金,则预付款=订单支付金额==订单实际总额
                if (orderBean.getAdvancePayWay() == 0) {
                    if (advancePriceParam.compareTo(orderRealyPrice) != 0 || needpayPriceParam.compareTo(new BigDecimal("0")) != 0) {
                        LOG.info("--------无优惠金额---------10-----支付方式为现金,则预付款=订单支付金额");
                        return false;
                    }
                } else {
                    BigDecimal advancePrice = postPrice.add(sysAdvancePrice).add(goodsAdvancePriceStore).add(goodsPayOnlinePriceStore);
                    if (advancePrice.compareTo(orderRealyPrice) > -1) {
                        if (advancePriceParam.compareTo(orderRealyPrice) != 0) {
                            LOG.info("--------无优惠金额-------1---->如果无优惠券则 预付款 = 邮费+订单预付款+商品预付款总额---->订单参数预付款=" + advancePriceParam + "  计算预付款=" + advancePrice);
                            return false;
                        }
                        if (needpayPriceParam.compareTo(new BigDecimal("0")) != 0) {
                            LOG.info("--------无优惠金额-------2---->如果无优惠券则 待付款 = 商品押金总额 + 商品总额---->订单参数待付款=" + needpayPriceParam + "  计算预付款=" + goodsDepositPriceStore.add(goodsPriceAllStore));
                            return false;
                        }
                    } else {
                        if (advancePriceParam.compareTo(advancePrice) != 0) {
                            LOG.info("--------无优惠金额-------3---->如果无优惠券则 预付款 = 邮费+订单预付款+商品预付款总额---->订单参数预付款=" + advancePriceParam + "  计算预付款=" + advancePrice);
                            return false;
                        }
                        /**
                         * 待付款 = 订单实际总额 - 预付款
                         */
                        if (needpayPriceParam.compareTo(orderRealyPrice.subtract(advancePrice)) != 0) {
                            LOG.info("---------无优惠金额-------4--->如果无优惠券则 待付款 = 商品押金总额 + 商品总额---->订单参数待付款=" + needpayPriceParam + "  计算预付款=" + goodsDepositPriceStore.add(goodsPriceAllStore));
                            return false;
                        }
                    }
                }
                /**
                 *  订单的支付金额 = 订单实际金额
                 */
                if (discountsPriceParam.compareTo(new BigDecimal("0")) != 0) {
                    LOG.info("--------无优惠金额-------5---->订单参数优惠金额总额=" + discountsPriceParam + "  实际优惠金额总额=0");
                    return false;
                }

                if (orderPriceParam.compareTo(orderRealyPrice) != 0) {
                    LOG.info("---------无优惠金额-------6--->订单参数订单的支付金额=" + orderPriceParam + "  实际订单的支付金额=" + orderRealyPrice);
                    return false;
                }
            }
            //数据库价格校验------------------------结束------------------------------------------------------
            //-----------------------------------------------------独立生成订单校验
            //创建订单
            LOG.info("----->开始创建订单---");
            return saveOrder(orderBean, orderNo);

        } catch (Exception e) {
            LOG.info("saveOrderData------->" + e);
        }
        return false;
    }

    /**
     * 保存订单
     *
     * @return
     */
    public boolean saveOrder(OrderBean orderBean, String orderNo) {
        try {
            saveInfoService.saveOrder(orderBean, orderNo);
            return true;
        } catch (Exception e) {
            LOG.info("---------->保存订单异常");
        }
        return false;
    }

}
