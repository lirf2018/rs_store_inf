package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.common.bean.PageInfo;
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
 * 创建时间:  2017-11-22 18:04
 * 功能介绍: 查询用户投诉或者建议列表
 */
@Service("qurey_user_complain_list")
public class QueryUserComplainList implements ResultOut {

    private Logger LOG = Logger.getLogger(QueryUserComplainList.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {

            Integer userId = bean.getInteger("user_id");
            Integer currentPage = bean.getInteger("current");//(查询页)
            List<Map<String, Object>> complainList = new ArrayList<Map<String, Object>>();
            dataJson.put("user_complain_list", complainList);
            dataJson.put("hasNext", false);
            if (null == userId || currentPage == null) {
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            //查询数据
            PageInfo page = new PageInfo();
            page = findInfoDao.loadUserComplainPage(currentPage, userId);
            dataJson.put("user_complain_list", page.getResultListMap());
            dataJson.put("hasNext", page.isHasNext());
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
