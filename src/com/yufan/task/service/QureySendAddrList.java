package com.yufan.task.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.CacheData;
import com.yufan.util.DatetimeUtil;
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
 * 功能介绍: 查询配送地址列表(qurey_sendaddr_list)
 */
@Service("qurey_sendaddr_list")
public class QureySendAddrList implements ResultOut {

    private Logger LOG = Logger.getLogger(QureySendAddrList.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer addrType = bean.getInteger("addr_type");
            List<Map<String, Object>> addrChar = null;
            List<Map<String, Object>> addrList = null;
            if (null != addrType) {
                addrChar = findInfoDao.loadSendAddrChar(addrType);
                addrList = findInfoDao.loadSendAddrListMap(addrType);
            } else {
                addrChar = findInfoDao.loadSendAddrChar();
                addrList = findInfoDao.loadSendAddrListMap();
            }
            //输出数据
            List<Map<String, Object>> outChar = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < addrChar.size(); i++) {
                Map<String, Object> oMap = new HashMap<String, Object>();
                String charWord = String.valueOf(addrChar.get(i).get("sort_char"));
                oMap.put("char_word", charWord);
                oMap.put("is_zq", false);
                oMap.put("is_ps", false);

                List<Map<String, Object>> addrData = new ArrayList<Map<String, Object>>();
                for (int j = 0; j < addrList.size(); j++) {
                    String charWord_ = String.valueOf(addrList.get(j).get("sort_char"));
                    if (charWord.equals(charWord_)) {
                        int addrType_ = Integer.parseInt(String.valueOf(addrList.get(j).get("addr_type")));
                        if (addrType_ == 4) {//地址类型4.自取 5配送
                            oMap.put("is_ps", true);
                        } else if (addrType_ == 5) {
                            oMap.put("is_zq", true);
                        }
                        addrData.add(addrList.get(j));
                    }
                }
                oMap.put("addr_detail", addrData);
                outChar.add(oMap);
            }
            dataJson.put("list_addr", outChar);
            //指定取货日期
            JSONArray arrayDate = new JSONArray();
            if (null != CacheData.paramMap.get("sys_code-get_day_max")) {
                String nowDate = DatetimeUtil.getNow("yyyy-MM-dd");
                int getDateMax = Integer.parseInt(CacheData.paramMap.get("sys_code-get_day_max"));
                for (int i = 0; i < getDateMax; i++) {
                    String d = DatetimeUtil.getDateLastOrNext("yyyy-MM-dd", nowDate, i + 1);
                    arrayDate.add(d);
                }
            }
            dataJson.put("list_get_date", arrayDate);

            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
