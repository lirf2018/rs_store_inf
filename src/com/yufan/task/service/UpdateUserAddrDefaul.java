package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.util.CacheData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2018/8/23 10:25
 * 功能介绍:  更新用户收货地址为默认
 */
@Service("update_user_addr_default")
public class UpdateUserAddrDefaul implements ResultOut {

    private Logger LOG = Logger.getLogger(UpdateUserAddrDefaul.class);

    @Autowired
    private SaveInfoService saveInfoService;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer userAddrId = bean.getInteger("user_addr_id");//
            Integer userId = bean.getInteger("user_id");//
            if (userAddrId == null || null == userId) {
                LOG.info("-------->缺少必要参数");
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            int addrType = CacheData.sysCodeUserAddrType;
            //设置其它的为非默认
            saveInfoService.updateUserAddrIsDefault(userId, 0, null, addrType);
            //设置当前的为默认
            saveInfoService.updateUserAddrIsDefault(userId, 1, userAddrId, addrType);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }

}