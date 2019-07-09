package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.common.bean.PageInfo;
import com.yufan.pojo.TbSearchHistory;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.dao.FindInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2018-02-07 10:42
 * 功能介绍: 商城首页菜单
 */
@Service("qurey_main_menu")
public class QueryMainMenuList implements ResultOut {

    private Logger LOG = Logger.getLogger(QueryMainMenuList.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {

            //主页菜单最多32=4x8个,每页8个
            List<Map<String, Object>> list1 = findInfoDao.loadMainMenu("0,8");
            List<Map<String, Object>> list2 = findInfoDao.loadMainMenu("8,8");
            List<Map<String, Object>> list3 = findInfoDao.loadMainMenu("16,8");
            List<Map<String, Object>> list4 = findInfoDao.loadMainMenu("24,8");
            dataJson.put("menu01", list1);
            dataJson.put("menu02", list2);
            dataJson.put("menu03", list3);
            dataJson.put("menu04", list4);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
