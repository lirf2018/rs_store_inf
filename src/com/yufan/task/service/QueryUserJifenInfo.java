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
import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-12-15 10:46
 * 功能介绍: 查询积分详情
 */
@Service("qurey_user_jifen_info")
public class QueryUserJifenInfo implements ResultOut {

    private Logger LOG = Logger.getLogger(QueryUserJifenList.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {

            Integer userId = bean.getInteger("user_id");
            Integer jifenId = bean.getInteger("jifen_id");
            if (null == userId || jifenId == null) {
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            List<Map<String, Object>> jifenList = new ArrayList<Map<String, Object>>();
            jifenList = findInfoDao.loadUserJifenListMap(userId, jifenId);
            if (jifenList.size() > 0) {
                dataJson.put("user_jifen_info", jifenList.get(0));
            }
            //计算积分
            List<Map<String, Object>> jifenListAll = findInfoDao.loadUserJifenListMap(userId);
            int jifen = 0;
            for (int i = 0; i < jifenListAll.size(); i++) {
                int jifen_ = Integer.parseInt(String.valueOf(jifenListAll.get(i).get("jifen_")));//带符号的积分大小
                jifen = jifen + jifen_;
            }
            dataJson.put("user_jifen", jifen);

            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
