package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-16 13:56
 * 功能介绍: 查询商家列表(qurey_partners_list)
 */
@Service("qurey_partners_list")
public class QureyPartnersList implements ResultOut {

    private Logger LOG = Logger.getLogger(QureyPartnersList.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer partnersId = bean.getInteger("partners_id");
            List<Map<String, Object>> listPartners = null;
            if (null != partnersId && 0 != partnersId) {
                listPartners = findInfoDao.loadPartnersListMap(partnersId);
            } else {
                listPartners = findInfoDao.loadPartnersListMap();
            }
            dataJson.put("list_partners", listPartners);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
