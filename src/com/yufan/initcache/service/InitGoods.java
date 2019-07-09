package com.yufan.initcache.service;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbGoods;
import com.yufan.pojo.TbGoodsSku;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.CacheData;
import com.yufan.util.FlushCacheData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2019/3/13 17:29
 * 功能介绍: 更新商品信息缓存
 */
@Service("refresh_goods")
public class InitGoods implements ResultOut {

    private Logger LOG = Logger.getLogger(InitGoods.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer goodsId = bean.getInteger("goods_id");
            if (null == goodsId || goodsId == 0) {
                LOG.info("----->更新全部缓存");
                FlushCacheData.getInstence().initGoods(findInfoDao);
                return packagMsg(ResultCode.OK.getResp_code(), dataJson);
            }
            TbGoods goodsInfo = findInfoDao.loadTbGoods(goodsId);
            if (goodsInfo == null) {
                return packagMsg(ResultCode.GOODS_NOT_EXIST.getResp_code(), dataJson);
            }
            LOG.info("----->更新商品缓存goodsId=" + goodsId);
            CacheData.goodsMap.put(goodsId, goodsInfo);

            //更新商品sku
            List<Map<String, Object>> skuListMap = findInfoDao.loadGoodsSkuByGoodsId(goodsId);
            LOG.info("----->更新更新商品sku" + skuListMap.size());
            CacheData.goodsSkuListMap.put(goodsId, skuListMap);

            for (int j = 0; j < skuListMap.size(); j++) {
                Integer skuId = Integer.parseInt(skuListMap.get(j).get("sku_id").toString());
                String skuName = skuListMap.get(j).get("sku_name").toString();
                BigDecimal trueMoney = new BigDecimal(skuListMap.get(j).get("true_money").toString());
                BigDecimal nowMoney = new BigDecimal(skuListMap.get(j).get("now_money_").toString());
                String skuCode = skuListMap.get(j).get("sku_code").toString();
                String propCode = skuListMap.get(j).get("prop_code").toString();
                Integer skuNum = Integer.parseInt(skuListMap.get(j).get("sku_num").toString());
                String skuImg = skuListMap.get(j).get("sku_img").toString();
                BigDecimal purchasePrice = new BigDecimal(skuListMap.get(j).get("purchase_price").toString());

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
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
    }
}
