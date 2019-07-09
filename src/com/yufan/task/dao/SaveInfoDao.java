package com.yufan.task.dao;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-14 18:27
 * 功能介绍:
 */
public interface SaveInfoDao {

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

    public void updateGoodsStoreBySku(int goodsId);

    /**
     * 更新商品sku库存
     */
    public void updateGoodsSkuStoreById(int goodsId, String propCode, int buyCount);

    /**
     * 更新抢购商品库存
     */
    public void updateTimeGoodsStoreById(int goodsId, int buyCount);

    /**
     * 更新验证码
     *
     * @param status
     * @param validParam
     * @param validType
     */
    public void updateTbVerificationStatus(int status, String validParam, int validType);

    /**
     * 更新购物车状态
     */
    public void updateOrderCardStatus(String cardIds, int status);

    public void updateOrderCardStatus(String cardIds, int status, int userId);

    public void updateOrderCardNum(int userId, int cardId, int goodsCount);

    public void updateOrderCardStatus(int cardId, int status);

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
     * 取消绑定
     *
     * @param userId
     * @param snsType
     */
    public void updateUserBind(int userId, int snsType);

    /**
     * 更新订单状态
     *
     * @param orderId
     * @param status
     */
    public void updateOrderStatus(int orderId, int status, String serviceRemark);

    /**
     * 删除密保问题
     */
    public void deletePasswdQuestion(int userId);

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
     * 更新订单详情状态
     */
    public void updateOrderDetailStatus(int orderId, int detailStatus);

    /**
     * 申请还货
     */
    public void updateOrderDetail(int backAddrId, String backAddrName, String backDate, int orderId);

    public void updateOrderStatus(int orderId, int status);
}
