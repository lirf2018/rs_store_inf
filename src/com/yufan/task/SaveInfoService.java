package com.yufan.task;

import com.alibaba.fastjson.JSONObject;
import com.yufan.pojo.TbTicketDownQr;
import com.yufan.pojo.TbUserAddr;
import com.yufan.pojo.TbUserInfo;
import com.yufan.task.bean.OrderBean;

/**
 * 创建人: lirf
 * 创建时间:  2017-12-07 18:14
 * 功能介绍:
 */
public interface SaveInfoService {

    /**
     * 保存对象
     */
    public int saveEntity(Object object);

    public void saveUpdateEntity(Object object);

    /**
     * 删除用户关注的
     */
    public void deleteUserAttention(int userId, int attentionId, int typeId);

    /**
     * 更新用户购物车临时标识
     */
    public void updateOrderCardTemp(String userMarkTemp, int userId);

    /**
     * 更新用户最后登录时间
     */
    public void updateUserLoginTime(int userId);

    /**
     * 更新商品库存
     */
    public void updateGoodsStoreById(int goodsId, int buyCount);

    /**
     * 更新商品sku库存
     */
    public void updateGoodsSkuStoreById(int goodsId, String propCode, int buyCount);

    /**
     * 更新抢购商品库存
     */
    public void updateTimeGoodsStoreById(int goodsId, int buyCount);

    /**
     * 保存订单
     */
    public boolean saveOrder(OrderBean orderBean, String orderNo);

    /**
     * 保存二维码
     *
     * @param downQr
     * @param qrImgRoot qr图片保存的根目录
     * @return
     */
    public int saveGenerateQR(TbTicketDownQr downQr, String qrImgRoot);


    /**
     * 用户注册
     */
    public int saveUserInfo(String userMobile, String passwd, String uid);

    /**
     * 更新验证码
     *
     * @param status
     * @param validParam
     * @param validType
     */
    public void updateTbVerificationStatus(int status, String validParam, int validType);

    /**
     * 清空用户搜索记录
     *
     * @param userId
     */
    public void updateUserSearchHistory(int userId);

    /**
     * 重置密码
     *
     * @param phone
     * @param passwd
     */
    public void updateUserPasswd(String phone, String passwd);

    public void updateUserPasswdByUserId(int userId, String passwd);

    /**
     * 新增用户
     */
    public int saveNewUser(TbUserInfo userInfo);

    /**
     * 删除密保问题
     */
    public void deletePasswdQuestion(int userId);

    /**
     * 微信注册和绑定
     */
    public TbUserInfo registerWeixin(JSONObject registerData);

    /**
     * 修改用户收货地址是否为默认
     *
     * @param userId
     * @param isDefault
     */
    public void updateUserAddrIsDefault(int userId, int isDefault, Integer id, int addrType);

    public void updateUserAddrStatus(int userId, int status, Integer id);

    public void deleteUserAddrStatus(int userId, int addrtype);

    /**
     * 绑定手机号码
     */
    public boolean boundUserPhone(TbUserInfo userInfo);

    /**
     * 申请还货更新订单信息
     *
     * @param orderId
     * @param goodsBackDate
     * @param backAddrName
     * @param backAddrId
     * @return
     */
    public boolean updateOrderStatus(int orderId, String goodsBackDate, String backAddrName, int backAddrId, String serviceRemark);

    /**
     * 更新订单状态
     *
     * @param orderId
     * @param status
     */
    public void updateOrderStatus(int orderId, int status, String serviceRemark);

}
