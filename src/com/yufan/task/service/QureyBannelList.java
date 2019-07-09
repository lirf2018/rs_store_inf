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
 * 功能介绍: 查询bannel图片列表 默认6张
 */
@Service("qurey_bannel_list")
public class QureyBannelList implements ResultOut {

    private Logger LOG = Logger.getLogger(QureyBannelList.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer bannelId = bean.getInteger("bannel_id");
            List<Map<String, Object>> listBannel = null;
            if (null != bannelId && 0 != bannelId) {
                listBannel = findInfoDao.loadBannelListMap(bannelId);
            } else {
                listBannel = findInfoDao.loadBannelListMap();
            }
            dataJson.put("list_bannel", listBannel);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
