package com.yufan.task.dao;

import com.yufan.common.dao.GeneralDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-14 18:27
 * 功能介绍:
 */
@Repository
@Transactional
public class SaveInfoDaoImpl implements SaveInfoDao {

    @Autowired
    private GeneralDao generalDao;

    /**
     * 保存对象
     */
    public int saveEntity(Object object) {
        return generalDao.save(object);
    }

    public void saveUpdateEntity(Object object) {
        generalDao.saveOrUpdate(object);
    }

    /**
     * 删除用户关注的
     */
    public void deleteUserAttention(int userId, int attentionId, int typeId) {
        String sql = " update tb_attention set status=0,lastaltertime=now() where user_id=? and attention_id=? and type_id=?  ";
        generalDao.executeUpdateForSQL(sql, userId, attentionId, typeId);
    }

    /**
     * 更新用户购物车临时标识
     */
    public void updateOrderCardTemp(String userMarkTemp, int userId) {
        String sql = " update tb_order_cart set user_id=? where user_mark_temp=? ";
        generalDao.executeUpdateForSQL(sql, userId, userMarkTemp);
    }

    /**
     * 更新用户最后登录时间
     */
    public void updateUserLoginTime(int userId) {
        String sql = " update tb_user_info set lastlogintime=now() where user_id=? ";
        generalDao.executeUpdateForSQL(sql, userId);
    }

    /**
     * 更新商品库存
     */
    public void updateGoodsStoreById(int goodsId, int buyCount) {
        String sql = " UPDATE tb_goods set goods_num=goods_num-? WHERE goods_id=? ";
        generalDao.executeUpdateForSQL(sql, buyCount, goodsId);
    }

    public void updateGoodsStoreBySku(int goodsId) {
        String sql = " update tb_goods set goods_num=(SELECT SUM(sku_num) from tb_goods_sku where goods_id=?) where goods_id=? ";
        generalDao.executeUpdateForSQL(sql, goodsId, goodsId);
    }

    /**
     * 更新商品sku库存
     */
    public void updateGoodsSkuStoreById(int goodsId, String propCode, int buyCount) {
        String sql = " UPDATE tb_goods_sku set sku_num=sku_num-? WHERE goods_id=? and prop_code=? ";
        generalDao.executeUpdateForSQL(sql, buyCount, goodsId, propCode);
    }

    /**
     * 更新抢购商品库存
     */
    public void updateTimeGoodsStoreById(int goodsId, int buyCount) {
        String sql = " UPDATE tb_time_goods set goods_store=goods_store-? where goods_id=? and `status`=1 ";
        generalDao.executeUpdateForSQL(sql, buyCount, goodsId);
    }

    /**
     * 更新验证码
     *
     * @param status
     * @param validParam
     * @param validType
     */
    public void updateTbVerificationStatus(int status, String validParam, int validType) {
        String sql = " update tb_verification set status=" + status + ",lastaltertime=now() where valid_type=" + validType + " and valid_param='" + validParam + "' ";
        generalDao.executeUpdateForSQL(sql);

    }

    /**
     * 更新购物车状态
     */
    public void updateOrderCardStatus(String cardIds, int status) {
        String sql = " update tb_order_cart set `status`=?,lastaltertime=now() where cart_id in (" + cardIds + ")  ";
        generalDao.executeUpdateForSQL(sql, status);
    }

    public void updateOrderCardStatus(String cardIds, int status, int userId) {
        String sql = " update tb_order_cart set `status`=?,lastaltertime=now() where cart_id in (" + cardIds + ") and  user_id=? ";
        generalDao.executeUpdateForSQL(sql, status, userId);
    }

    public void updateOrderCardNum(int userId, int cardId, int goodsCount) {
        String sql = " update tb_order_cart set `goods_count`=?,lastaltertime=now() where cart_id=? and user_id=?  ";
        generalDao.executeUpdateForSQL(sql, goodsCount, cardId, userId);
    }

    public void updateOrderCardStatus(int cardId, int status) {
        String sql = " update tb_order_cart set `status`=?,lastaltertime=now() where cart_id =? ";
        generalDao.executeUpdateForSQL(sql, status, cardId);
    }

    /**
     * 清空用户搜索记录
     *
     * @param userId
     */
    public void updateUserSearchHistory(int userId) {
        String sql = " update tb_search_history set `status`=0 where user_id=? ";
        generalDao.executeUpdateForSQL(sql, userId);
    }

    /**
     * 修改密码
     *
     * @param phone
     * @param passwd
     */
    public void updateUserPasswd(String phone, String passwd) {
        String sql = " update tb_user_info set login_pass=?,lastaltertime=now() where user_mobile=? and user_state=1 ";
        generalDao.executeUpdateForSQL(sql, passwd, phone);
    }

    public void updateUserPasswdByUserId(int userId, String passwd) {
        String sql = " update tb_user_info set login_pass=?,lastaltertime=now() where user_id=? and user_state=1 ";
        generalDao.executeUpdateForSQL(sql, passwd, userId);
    }


    /**
     * 取消绑定
     *
     * @param userId
     * @param snsType
     */
    public void updateUserBind(int userId, int snsType) {
        String sql = " update tb_user_sns set update_time=NOW(),status=2 where user_id=? and sns_type=? ";
        generalDao.executeUpdateForSQL(sql, userId, snsType);
    }

    /**
     * 更新订单状态
     *
     * @param orderId
     * @param status
     */
    public void updateOrderStatus(int orderId, int status, String serviceRemark) {
        String sql = " update tb_order set order_status=?,lastaltertime=now(),service_remark=?,lastalterman='' where order_id=? ";
        generalDao.executeUpdateForSQL(sql, status, serviceRemark, orderId);
    }

    /**
     * 删除密保问题
     */
    public void deletePasswdQuestion(int userId) {
        String sql = " update tb_passwd_reset_question set status=0 where user_id=" + userId + "  ";
        generalDao.executeUpdateForSQL(sql);
    }

    /**
     * 修改用户收货地址是否为默认
     *
     * @param userId
     * @param isDefault
     */
    public void updateUserAddrIsDefault(int userId, int isDefault, Integer id, int addrType) {
        if (null == id) {
            String sql = " update tb_user_addr set is_default=? where user_id=? and addr_type=? ";
            generalDao.executeUpdateForSQL(sql, isDefault, userId, addrType);
        } else {
            String sql = " update tb_user_addr set is_default=? where user_id=? and id=? and addr_type=? ";
            generalDao.executeUpdateForSQL(sql, isDefault, userId, id, addrType);
        }

    }

    public void updateUserAddrStatus(int userId, int status, Integer id) {

        if (null == id) {
            String sql = " update tb_user_addr set status=? where user_id=? ";
            generalDao.executeUpdateForSQL(sql, status, userId);
        } else {
            String sql = " update tb_user_addr set status=? where user_id=? and id=? ";
            generalDao.executeUpdateForSQL(sql, status, userId, id);
        }
    }

    public void deleteUserAddrStatus(int userId, int addrtype) {
        String sql = " update tb_user_addr  set status=0 where addr_type=? and user_id=? ";
        generalDao.executeUpdateForSQL(sql, addrtype, userId);
    }


    @Override
    public void updateOrderDetail(int backAddrId, String backAddrName, String backDate, int orderId) {
        String sql = " update tb_order_detail set back_addr_id=?,back_addr_name=?,back_time=?,lastaltertime=now()  where order_id=?  ";
        generalDao.executeUpdateForSQL(sql, backAddrId, backAddrName, backDate, orderId);
    }

    @Override
    public void updateOrderStatus(int orderId, int status) {
        String sql = " update tb_order set order_status=? ,lastaltertime=NOW() where order_id=? ";
        generalDao.executeUpdateForSQL(sql, status, orderId);
    }

    /**
     * 更新订单详情状态
     */
    public void updateOrderDetailStatus(int orderId, int detailStatus) {
        String sql = " update tb_order_detail set detail_status=? ,lastaltertime=NOW() where order_id=? ";
        generalDao.executeUpdateForSQL(sql, detailStatus, orderId);
    }
}
