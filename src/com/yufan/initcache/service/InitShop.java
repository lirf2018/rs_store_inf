package com.yufan.initcache.service;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbGoods;
import com.yufan.pojo.TbShop;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.CacheData;
import com.yufan.util.FlushCacheData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2019/3/14 10:54
 * 功能介绍:
 */
@Service("refresh_shop")
public class InitShop implements ResultOut {

    private Logger LOG = Logger.getLogger(InitShop.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer shopId = bean.getInteger("shop_id");
            if (null == shopId || shopId == 0) {
                LOG.info("----->更新全部缓存");
                FlushCacheData.getInstence().initShop(findInfoDao);
                return packagMsg(ResultCode.OK.getResp_code(), dataJson);
            }
            TbShop shopInfo = findInfoDao.loadTbshopInfo(shopId);
            if (shopInfo == null) {
                return packagMsg(ResultCode.GOODS_NOT_EXIST.getResp_code(), dataJson);
            }
            LOG.info("----->更新商品缓存shopId=" + shopId);
            CacheData.shopMap.put(shopId, shopInfo);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
    }
}
