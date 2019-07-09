package com.yufan.initcache.service;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbDistributionAddr;
import com.yufan.pojo.TbGoods;
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
 * 创建时间:  2019/3/13 17:30
 * 功能介绍: 更新平台地址缓存
 */
@Service("refresh_platform")
public class InitPlatform implements ResultOut {

    private Logger LOG = Logger.getLogger(InitPlatform.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer addrId = bean.getInteger("addr_id");
            if (null == addrId || addrId == 0) {
                LOG.info("----->更新全部缓存");
                FlushCacheData.getInstence().initPlatform(findInfoDao);
                return packagMsg(ResultCode.OK.getResp_code(), dataJson);
            }
            TbDistributionAddr distributionAddrInfo = findInfoDao.loadTbDistributionAddr(addrId, null);
            if (distributionAddrInfo == null) {
                return packagMsg(ResultCode.GOODS_NOT_EXIST.getResp_code(), dataJson);
            }
            LOG.info("----->更新商品缓存addrId=" + addrId);
            CacheData.platformMap.put(addrId, distributionAddrInfo);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
    }
}