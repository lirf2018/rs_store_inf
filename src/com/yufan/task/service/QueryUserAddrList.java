package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbDistributionAddr;
import com.yufan.pojo.TbRegion;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.CacheData;
import com.yufan.util.FlushCacheData;
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
 * 创建时间:  2018/8/17 9:14
 * 功能介绍: 查询用户收货地址(全国)
 */
@Service("query_user_addr_list")
public class QueryUserAddrList implements ResultOut {

    private Logger LOG = Logger.getLogger(QueryUserAddrList.class);
    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer userId = bean.getInteger("user_id");
            Integer isDefault = bean.getInteger("is_default");
            if (null == userId) {
                LOG.info("-------->缺少必要参数");
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            List<Map<String, Object>> outUserAddrsList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> userAddrsList = findInfoDao.queryUserAddrListMap(userId, isDefault, 1);
            //处理运费 freight
            for (int i = 0; i < userAddrsList.size(); i++) {
                Map<String, Object> map = userAddrsList.get(i);
                String areaIds = String.valueOf(map.get("area_ids"));
                areaIds = areaIds.replace("-", "','");
                List<Map<String, Object>> listUserAddrs = findInfoDao.queryUserAddrListMapByRegionCodes(areaIds);
                if (null == listUserAddrs || listUserAddrs.size() == 0) {
                    map.put("freight", 50.00);
                } else {
                    map.put("freight", 0);
                    for (int j = 0; j < listUserAddrs.size(); j++) {
                        String freight = listUserAddrs.get(j).get("freight").toString();
                        if (new BigDecimal(freight).compareTo(new BigDecimal("0")) > 0) {
                            map.put("freight", new BigDecimal(freight));
                            break;
                        }
                    }
                }
                outUserAddrsList.add(map);
            }
            Integer addrType = CacheData.sysCodeUserAddrType;//地址类型1全国地址2平台配送或者自己取地址
            dataJson.put("addr_type", addrType);
            dataJson.put("list_user_addr", outUserAddrsList);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }

    public static void main(String[] args) {
        String areaIds = "150000000000-150500000000-150523000000-150523103000";
        String[] reginCodesArray = areaIds.split("-");
        for (int j = reginCodesArray.length - 1; j >= 0; j--) {
            String reginCode = reginCodesArray[j];
            System.out.println(reginCode);
        }
    }
}