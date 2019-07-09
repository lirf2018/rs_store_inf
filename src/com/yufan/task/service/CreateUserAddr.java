package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbSignin;
import com.yufan.pojo.TbUserAddr;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.CacheData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2018/8/16 18:24
 * 功能介绍: 创建用户收货地址(有效地址最多10个)
 */
@Service("create_user_addr")
public class CreateUserAddr implements ResultOut {

    private Logger LOG = Logger.getLogger(CreateUserAddr.class);

    @Autowired
    private SaveInfoService saveInfoService;

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer userId = bean.getInteger("user_id");//
            String areaIds = bean.getString("area_ids");
            String areaName = bean.getString("area_name");
            String userPhone = bean.getString("user_phone");
            String userName = bean.getString("user_name");
            String addrDetail = bean.getString("addr_detail");
            Integer isDefault = bean.getInteger("is_default");

//            Integer addrType = CacheData.sysCodeUserAddrType;//地址类型1全国地址2平台配送或者自己取地址
            if (userId == null || isDefault == null || !StringUtils.nonEmptyString(areaIds) || !StringUtils.nonEmptyString(areaName)
                    || !StringUtils.nonEmptyString(userPhone) || !StringUtils.nonEmptyString(addrDetail) || !StringUtils.nonEmptyString(userName)) {
                LOG.info("-------->缺少必要参数");
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            //查询用户收货地址数量(两种有效地址最多创建10个)
            List<Map<String, Object>> userAddrsList = findInfoDao.queryUserAddrListMap(userId, null, 1);
            if (userAddrsList.size() > 10) {
                LOG.info("-------->用户收货地址数量(有效地址最多创建10个)");
                return packagMsg(ResultCode.USER_ADDR_FULL.getResp_code(), dataJson);
            }

            TbUserAddr userAddr = new TbUserAddr();
            if (areaIds.endsWith("-")) {
                areaIds = areaIds.substring(0, areaIds.length() - 1);
            }
            userAddr.setAreaIds(areaIds);
            userAddr.setAreaName(areaName);
            userAddr.setUserId(userId);
            userAddr.setUserName(userName);
            userAddr.setUserPhone(userPhone);
            userAddr.setAddrDetail(addrDetail);
            userAddr.setIsDefault(isDefault);
            userAddr.setStatus(1);
            userAddr.setCreatetime(new Timestamp(new Date().getTime()));
            userAddr.setAddrName(areaName + addrDetail);//完整地址名称
            userAddr.setAddrType(1);

            //如果新创建的收货地址为默认地址,则修改其它地址为非默认
            if (isDefault.intValue() == 1) {
                saveInfoService.updateUserAddrIsDefault(userId, 0, null, 1);
            }
            if (userAddrsList.size() == 0) {
                userAddr.setIsDefault(1);
            }

            int id = saveInfoService.saveEntity(userAddr);
            LOG.info("-------->新增用户收货地址=" + id);
            dataJson.put("id", id);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }

}