package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbUserAddr;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.dao.FindInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2018/8/16 18:25
 * 功能介绍: 删除用户收货地址
 */
@Service("delete_user_addr")
public class DeleteUserAddr implements ResultOut {

    private Logger LOG = Logger.getLogger(DeleteUserAddr.class);

    @Autowired
    private SaveInfoService saveInfoService;

    @Autowired
    private FindInfoDao findInfoDao;


    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer userAddrId = bean.getInteger("user_addr_id");//
            Integer userId = bean.getInteger("user_id");//

            if (null == userAddrId || userId == null) {
                LOG.info("-------->缺少必要参数");
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            //查询要删除的用户地址
            TbUserAddr userAddr = findInfoDao.loadTbUserAddr(userId, userAddrId, 1);
            saveInfoService.updateUserAddrStatus(userId, 0, userAddrId);
            if (null != userAddr) {
                //如果删除的收货地址为默认地址,则修改其它地址为非默认
                if (userAddr.getIsDefault() == 1) {
                    LOG.info("-------->删除默认地址后,修改最后一条收货地址为默认");
                    List<TbUserAddr> list = findInfoDao.loadTbUserInfoList(userId);
                    if (list != null && list.size() > 0) {
                        TbUserAddr u = list.get(0);
                        u.setIsDefault(1);
                        saveInfoService.saveUpdateEntity(u);
                    }
                }
            }
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.SYS_ERROR.getResp_code(), new JSONObject());
    }
}