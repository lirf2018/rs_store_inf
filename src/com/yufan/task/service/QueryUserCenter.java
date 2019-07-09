package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.CacheData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-22 16:13
 * 功能介绍: 用户中心页面
 */
@Service("qurey_user_center")
public class QueryUserCenter implements ResultOut {

    private Logger LOG = Logger.getLogger(QueryUserCenter.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer userId = bean.getInteger("user_id");
            if (null == userId || 0 == userId.intValue()) {
                LOG.info("------>user_id不能为空");
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            //用户信息
            List<Map<String, Object>> listUserInfo = findInfoDao.loadUserCenterListMap(userId);
            if (null == listUserInfo || listUserInfo.size() == 0) {
                LOG.info("------>查询用户不存在");
                return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
            }

            String nickName = String.valueOf(listUserInfo.get(0).get("nick_name"));
            String userMobile = String.valueOf(listUserInfo.get(0).get("user_mobile"));
            String userImg = String.valueOf(listUserInfo.get(0).get("user_img"));
            int waitPayorder = Integer.parseInt(String.valueOf(listUserInfo.get(0).get("wait_payorder") == null ? 0 : listUserInfo.get(0).get("wait_payorder")));
            int sureOrder = Integer.parseInt(String.valueOf(listUserInfo.get(0).get("sure_order") == null ? 0 : listUserInfo.get(0).get("sure_order")));
            int ispayOrder = Integer.parseInt(String.valueOf(listUserInfo.get(0).get("ispay_order") == null ? 0 : listUserInfo.get(0).get("ispay_order")));
            int waitGetorder = Integer.parseInt(String.valueOf(listUserInfo.get(0).get("wait_getorder") == null ? 0 : listUserInfo.get(0).get("wait_getorder")));
            int failOrder = Integer.parseInt(String.valueOf(listUserInfo.get(0).get("fail_order") == null ? 0 : listUserInfo.get(0).get("fail_order")));
            int ticketCounts = Integer.parseInt(String.valueOf(listUserInfo.get(0).get("ticket_counts") == null ? 0 : listUserInfo.get(0).get("ticket_counts")));

            int isFinish = Integer.parseInt(String.valueOf(listUserInfo.get(0).get("is_finish") == null ? 0 : listUserInfo.get(0).get("is_finish")));
            int isCancel = Integer.parseInt(String.valueOf(listUserInfo.get(0).get("is_cancel") == null ? 0 : listUserInfo.get(0).get("is_cancel")));
            int wattingTuikuang = Integer.parseInt(String.valueOf(listUserInfo.get(0).get("watting_tuikuang") == null ? 0 : listUserInfo.get(0).get("watting_tuikuang")));
            int isTuikuang = Integer.parseInt(String.valueOf(listUserInfo.get(0).get("is_tuikuang") == null ? 0 : listUserInfo.get(0).get("is_tuikuang")));
            int isDoing = Integer.parseInt(String.valueOf(listUserInfo.get(0).get("is_doing") == null ? 0 : listUserInfo.get(0).get("is_doing")));

            dataJson.put("nick_name", nickName);
            dataJson.put("user_mobile", userMobile);
            dataJson.put("user_img", userImg);
            dataJson.put("wait_payorder", waitPayorder);//待付款订单数
            dataJson.put("ispay_order", ispayOrder);//已付款订单数
            dataJson.put("sure_order", sureOrder);//待确认订单数
            dataJson.put("wait_getorder", waitGetorder);//待收货订单数
            dataJson.put("fail_order", failOrder);//已失败订单数
            dataJson.put("ticket_counts", ticketCounts);//优惠券数

            dataJson.put("is_finish", isFinish);//已完成
            dataJson.put("is_cancel", isCancel);//已取消
            dataJson.put("watting_tuikuang", wattingTuikuang);//退款中
            dataJson.put("is_tuikuang", isTuikuang);//已退款
            dataJson.put("is_doing", isDoing);//处理中

            //查询积分列表
            List<Map<String, Object>> jifenList = findInfoDao.loadUserJifenListMap(userId);
            //计算积分
            int jifen = 0;
            for (int i = 0; i < jifenList.size(); i++) {
                int jifen_ = Integer.parseInt(String.valueOf(jifenList.get(i).get("jifen_")));//带符号的积分大小
                jifen = jifen + jifen_;
            }
            dataJson.put("user_jifen", jifen);

            //查询购物车
            int orderCardCount = findInfoDao.loadOrderCartGoodsCount(userId);
            dataJson.put("card_count", orderCardCount > 99 ? "99+" : orderCardCount);

            dataJson.put("addr_type", CacheData.sysCodeUserAddrType);//当前平台设置的取货方式

            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
