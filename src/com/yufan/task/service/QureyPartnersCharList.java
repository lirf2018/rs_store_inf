package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-16 15:33
 * 功能介绍: 按字母查询配送地址列表(qurey_sendaddr_char_list)
 */
@Service("qurey_sendaddr_char_list")
public class QureyPartnersCharList implements ResultOut {

    private Logger LOG = Logger.getLogger(QureyPartnersCharList.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {

            Integer partnersId = bean.getInteger("partners_id");
            List<Map<String, Object>> partnersrChar = null;
            List<Map<String, Object>> partnersList = null;
            if (null != partnersId && 0 != partnersId) {
                partnersrChar = findInfoDao.loadPartnersCharListMap(partnersId);
                partnersList = findInfoDao.loadPartnersListMap(partnersId);
            } else {
                partnersrChar = findInfoDao.loadPartnersCharListMap();
                partnersList = findInfoDao.loadPartnersListMap();
            }
            //输出数据
            List<Map<String, Object>> outChar = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < partnersrChar.size(); i++) {
                Map<String, Object> oMap = new HashMap<String, Object>();
                String charWord = String.valueOf(partnersrChar.get(i).get("first_name_code"));
                oMap.put("char_word", charWord);
                List<Map<String, Object>> addrData = new ArrayList<Map<String, Object>>();
                for (int j = 0; j < partnersList.size(); j++) {
                    String charWord_ = String.valueOf(partnersList.get(j).get("first_name_code"));
                    if (charWord.equals(charWord_)) {
                        addrData.add(partnersList.get(j));
                    }
                }
                oMap.put("partners_detail", addrData);
                outChar.add(oMap);
            }
            dataJson.put("list_partners", outChar);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
