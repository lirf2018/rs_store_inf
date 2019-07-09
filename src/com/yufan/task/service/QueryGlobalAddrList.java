package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbComplain;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.dao.FindInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2018/8/3 10:09
 * 功能介绍:  查询全球地址
 */
@Service("query_global_addr_list")
public class QueryGlobalAddrList implements ResultOut {

    private Logger LOG = Logger.getLogger(QueryGlobalAddrList.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            String parentId = bean.getString("parent_id");//parentId
            String mark = bean.getString("mark");//mark
            List<Map<String, Object>> list = null;
            if ("1".equals(mark)) {
                list = findInfoDao.queryGlobalAddrListMapMark(parentId);//
            } else {
                list = findInfoDao.queryGlobalAddrListMap(parentId);
            }
            dataJson.put("addr_list", list);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
    }

}
