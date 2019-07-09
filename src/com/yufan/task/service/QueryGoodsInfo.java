package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.common.util.RsConstants;
import com.yufan.pojo.*;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.CacheData;
import com.yufan.util.CommonMethod;
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
 * 创建时间:  2017-11-22 10:17
 * 功能介绍: 查询商品详情
 */
@Service("qurey_goods_info")
public class QueryGoodsInfo implements ResultOut {

    private Logger LOG = Logger.getLogger(QueryGoodsInfo.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer goodsId = bean.getInteger("goods_id");
            Integer timeGoodsId = bean.getInteger("time_goods_id") == null ? 0 : bean.getInteger("time_goods_id");//抢购商品标识 不是抢购则为0
            Integer userId = bean.getInteger("user_id");
            if (null == goodsId || goodsId.intValue() == 0) {
                LOG.info("------>商品标识不能为空");
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), new JSONObject());
            }
            TbGoods goods = CacheData.goodsMap.get(goodsId);
            if (null == goods) {
                LOG.info("------>有效商品信息不存在goodsId=" + goodsId);
                return packagMsg(ResultCode.GOODS_NOT_EXIST.getResp_code(), new JSONObject());
            }

            boolean flag = CommonMethod.getInstance().checkGoodsInfoEff(goods);
            if (!flag) {
                LOG.info("---flag--->有效商品信息不存在goodsId=" + goodsId);
                return packagMsg(ResultCode.GOODS_NOT_EXIST.getResp_code(), new JSONObject());
            }

            Map<Integer, String> limitWay = new HashMap<Integer, String>();
            limitWay.put(1, "每天一次");
            limitWay.put(2, "每月一次");
            limitWay.put(3, "每年一次");
            limitWay.put(4, "不限购");
            limitWay.put(5, "只允许购买一次");


            String goodsName = goods.getGoodsName();
            String goodsImg = StringUtils.nonEmptyString(goods.getGoodsImg()) ? (RsConstants.PHONE_URL + goods.getGoodsImg()) : (RsConstants.PHONE_URL + "null.jpg");
            String goodsIntro = goods.getIntro();
            BigDecimal nowMoney = goods.getNowMoney();
            BigDecimal trueMoney = goods.getTrueMoney();
            BigDecimal advancePrice = goods.getAdvancePrice();
            BigDecimal depositMoney = goods.getDepositMoney();
            int goodsNum = goods.getGoodsNum();//商品库存
            String goodsUnit = goods.getGoodsUnit();//商品单位
            int categoryId = goods.getClassifyId();//类目标识
            int isSingle = goods.getIsSingle();
            int getWay = goods.getGetWay();
            int isPayOnline = goods.getIsPayOnline();
            int property = goods.getProperty();
            int goodsType = goods.getGoodsType();
            int partnersId = goods.getPartnersId();
            TbPartners partners = CacheData.partnersMap.get(partnersId);
            String partnersName = partners.getPartnersName();
            Integer ticketId = goods.getTicketId();//是否是商品券
            Integer isTimeGoods = goods.getIsTimeGoods();//
            //商品限购
            int goodsLimitNum = goods.getLimitNum();
            int goodsLimitWay = goods.getLimitWay();
            dataJson.put("goods_limit_num", goodsLimitNum);
            dataJson.put("goods_limit_way", goodsLimitWay);
            dataJson.put("goods_limit_way_name", limitWay.get(goodsLimitWay));


            List<Map<String, Object>> goodsBannel = new ArrayList<>();
            List<Map<String, Object>> goodsImginfo = new ArrayList<>();

            List<Map<String, Object>> goodsImgListMap = findInfoDao.loadImgListMap(0, goodsId);
            for (int i = 0; i < goodsImgListMap.size(); i++) {
                int imgType = Integer.parseInt(goodsImgListMap.get(i).get("img_type").toString());
                //商品bannel 4张
                if (imgType == 1 && goodsBannel.size() <= 4) {
                    goodsBannel.add(goodsImgListMap.get(i));
                }
                //商品图片介绍 4张
                if (imgType == 2 && goodsImginfo.size() <= 4) {
                    goodsImginfo.add(goodsImgListMap.get(i));
                }
            }

            int cardCount = 0;//查询购物车商品数
            boolean isAttention = false;//用户是否已收藏(关注)
            if (null != userId && 0 != userId.intValue()) {
                isAttention = findInfoDao.isAttentiond(2, userId, goodsId);
                cardCount = findInfoDao.loadOrderCartGoodsCount(userId);
            }
            dataJson.put("is_attention", 0);//是否已收藏或关注
            dataJson.put("time_goods_id", 0);
            dataJson.put("time_price", 0);
            dataJson.put("time_goods_num", 0);
            //查询抢购商品信息
            if (0 != timeGoodsId.intValue()) {
                TbTimeGoods timeGoods = findInfoDao.loadTbTimeGoodsInfo(timeGoodsId, goodsId);
                if (null == timeGoods || timeGoods.getIsMakeSure() == 0) {
                    LOG.info("------>查询有效抢购商品不存在");
                    return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
                }
                dataJson.put("time_price", timeGoods.getTimePrice());//抢购价格
                dataJson.put("time_goods_num", timeGoods.getGoodsStore());//抢购库存
                dataJson.put("time_goods_id", timeGoodsId);
                dataJson.put("limit_num", timeGoods.getLimitNum());
                dataJson.put("time_way", timeGoods.getTimeWay());
                dataJson.put("time_way_name", limitWay.get(timeGoods.getTimeWay()));

                //`time_way` int(11) DEFAULT NULL COMMENT '限购方式: 1.每天一次2每月一次3.每年一次4不限购5只允许购买一次',
            }

            //商品sku包含有的属性值id
            Map<Integer, Integer> mapValueId = new HashMap<Integer, Integer>();

            if (isSingle == 0) {
                //查询商品sku
                List<Map<String, Object>> listGoodsSku = CacheData.goodsSkuListMap.get(goodsId);
                dataJson.put("goods_sku", listGoodsSku);
                if (null != listGoodsSku && listGoodsSku.size() > 0) {
                    //从sku中得到现价最高和最低
                    int size = listGoodsSku.size();
                    Object price = listGoodsSku.get(0).get("now_money_");
                    if (1 == size) {
                        dataJson.put("sku_price_interval", price + "-" + price);//sku价格区间
                    } else {
                        Object price1 = listGoodsSku.get(size - 1).get("now_money_");
                        dataJson.put("sku_price_interval", price + "-" + price1);//sku价格区间
                    }
                    //计算sku库存
                    int skuNum = 0;
                    for (int i = 0; i < listGoodsSku.size(); i++) {
                        int num = Integer.parseInt(listGoodsSku.get(i).get("sku_num").toString());
                        skuNum = skuNum + num;
                        String valueIdCode = listGoodsSku.get(i).get("prop_code").toString();
                        String[] valueIdArry = valueIdCode.split(";");
                        for (int j = 0; j < valueIdArry.length; j++) {
                            int valueId = Integer.parseInt(valueIdArry[j]);
                            mapValueId.put(valueId, valueId);
                        }
                    }
                    dataJson.put("goods_sku_num", skuNum);
                }
            }

            List<Map<String, Object>> listItem = findInfoDao.loadTbItempropsListMap(categoryId);
            //查询属性值
            List<TbPropsValue> listPropsValue = findInfoDao.loadTbPropsValueList(categoryId);

            List<Map<String, Object>> outItemList = new ArrayList<Map<String, Object>>();//输出属性列表(销售属性)
            List<Map<String, Object>> outItemListNotSale = new ArrayList<Map<String, Object>>();//输出属性列表(非销售属性)

            for (int i = 0; i < listItem.size(); i++) {
                Map<String, Object> itemMap = listItem.get(i);
                int itemId = Integer.parseInt(String.valueOf(itemMap.get("prop_id")));
                int isSales = Integer.parseInt(String.valueOf(itemMap.get("is_sales")));//0不是销售属性1是销售属性

                List<Map<String, Object>> outItemValueList = new ArrayList<Map<String, Object>>();//输出属性列表
                for (int j = 0; j < listPropsValue.size(); j++) {
                    TbPropsValue value = listPropsValue.get(j);
                    int valuePropId = value.getPropId();
                    if (valuePropId == itemId && null != mapValueId.get(value.getValueId())) {
                        Map<String, Object> valueMap = new HashMap<String, Object>();
                        valueMap.put("value_id", value.getValueId());
                        valueMap.put("prop_id", value.getPropId());
                        valueMap.put("value_name", value.getValueName());
                        valueMap.put("oute_id", value.getOuteId());
                        valueMap.put("value_data", value.getValue());
                        outItemValueList.add(valueMap);
                    }
                }
                itemMap.put("value_list", outItemValueList);
                if (isSales == 1) {
                    outItemList.add(itemMap);
                } else {
                    outItemListNotSale.add(itemMap);
                }
            }
            //查询商品销售数量
            List<Map<String, Object>> listSaleCount = findInfoDao.queryGoodsSaleCount(String.valueOf(goodsId));
            int shellCount = 0;
            if (null != listSaleCount && listSaleCount.size() > 0) {
                for (int i = 0; i < listSaleCount.size(); i++) {
                    shellCount = Integer.parseInt(listSaleCount.get(i).get("sale_count").toString());
                }
            }
            //输出
            dataJson.put("card_count", cardCount);//查询购物车商品数
            dataJson.put("is_attention", isAttention);//是否已收藏或关注
            dataJson.put("goods_id", goodsId);
            dataJson.put("goods_img", goodsImg);
            dataJson.put("goods_name", goodsName);
            dataJson.put("goods_num", goodsNum);//商品库存(如果是sku则为sku库存总和,如果是抢购,则为抢购库存)
            dataJson.put("goods_intro", goodsIntro);
            dataJson.put("now_money", nowMoney);//如果单品则有效
            dataJson.put("goods_advance_price", advancePrice);//商品预付款
            dataJson.put("true_money", trueMoney);//如果单品则有效
            dataJson.put("shell_count", shellCount);//销售数
            dataJson.put("goods_unit", goodsUnit);//单位
            dataJson.put("goods_bannel", goodsBannel);
            dataJson.put("goods_img_info", goodsImginfo);
            dataJson.put("get_way", getWay);//取货方式 1邮寄 4.自取 5配送
            dataJson.put("is_pay_online", isPayOnline);//是否支持货到付款:0不支持货到付款1支持货到付款
            dataJson.put("property", property);//属性 0虚拟商品1实体商品
            dataJson.put("goods_type", goodsType);//商品类型:0:实体商品1商品券2话费商品
            dataJson.put("is_single", isSingle);//是否单品0不是单品1是单品
            dataJson.put("goods_item_sales", outItemList);//销售属性
            dataJson.put("goods_item_not_sales", outItemListNotSale);//非销售属性
            dataJson.put("advance_price", 0);
            dataJson.put("is_time_goods", isTimeGoods);
            dataJson.put("deposit_money", depositMoney);
            dataJson.put("partners_id", partnersId);
            dataJson.put("partners_name", partnersName);
            dataJson.put("ticket_id", ticketId);
            dataJson.put("addr_type", CacheData.sysCodeUserAddrType);
            //查询预付款(系统配置参数不检验数据状态性,不需要确认,只要数据存在,就是有效的)
            String sysAdvancePriceStr = CacheData.paramMap.get("sys_code-order_advance_price");
            if (StringUtils.nonEmptyString(sysAdvancePriceStr)) {
                BigDecimal sysAdvancePrice = new BigDecimal(sysAdvancePriceStr.trim());
                dataJson.put("advance_price", sysAdvancePrice);
            }

            //是否不允许加入购物车
            boolean flagSingleGoodsOrder = CommonMethod.getInstance().checkGoodsOrderSingle(goods);
            dataJson.put("add_to_shopcard", 0);//0不允许1允许
            if (!flagSingleGoodsOrder) {
                dataJson.put("add_to_shopcard", 1);
            }
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
