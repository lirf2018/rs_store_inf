package com.yufan.task;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.pojo.*;
import com.yufan.task.bean.OrderBean;
import com.yufan.task.bean.OrderDetailBean;
import com.yufan.task.bean.OrderDetailProptyBean;
import com.yufan.task.dao.SaveInfoDao;
import com.yufan.util.Base64Coder;
import com.yufan.util.DatetimeUtil;
import com.yufan.util.MD5;
import com.yufan.util.TwoDimensionCode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 创建人: lirf
 * 创建时间:  2017-12-07 18:15
 * 功能介绍:
 */
@Service("saveInfoService")
@Transactional
public class SaveInfoServiceImpl implements SaveInfoService {

    private static Logger LOG = Logger.getLogger(SaveInfoServiceImpl.class);

    @Autowired
    private SaveInfoDao saveInfoDao;

    @Override
    public int saveEntity(Object object) {
        return saveInfoDao.saveEntity(object);
    }

    @Override
    public void saveUpdateEntity(Object object) {
        saveInfoDao.saveUpdateEntity(object);
    }

    @Override
    public void deleteUserAttention(int userId, int attentionId, int typeId) {
        saveInfoDao.deleteUserAttention(userId, attentionId, typeId);
    }

    @Override
    public void updateOrderCardTemp(String userMarkTemp, int userId) {
        saveInfoDao.updateOrderCardTemp(userMarkTemp, userId);
    }

    @Override
    public void updateUserLoginTime(int userId) {
        saveInfoDao.updateUserLoginTime(userId);
    }

    @Override
    public void updateGoodsStoreById(int goodsId, int buyCount) {
        saveInfoDao.updateTimeGoodsStoreById(goodsId, buyCount);
    }

    @Override
    public void updateGoodsSkuStoreById(int goodsId, String propCode, int buyCount) {
        saveInfoDao.updateGoodsSkuStoreById(goodsId, propCode, buyCount);
    }

    @Override
    public void updateTimeGoodsStoreById(int goodsId, int buyCount) {
        saveInfoDao.updateTimeGoodsStoreById(goodsId, buyCount);
    }

    /**
     * 保存订单
     */
    @Transactional
    public boolean saveOrder(OrderBean orderBean, String orderNo) {
        try {
            TbOrder order = new TbOrder();
            order.setOrderNo(orderNo);
            Integer userId = orderBean.getUserId();
            order.setUserId(userId);
            Integer goodsCount = orderBean.getGoodsCount();
            order.setGoodsCount(goodsCount);
            BigDecimal orderPrice = orderBean.getOrderPrice();//订单实际支付价格
            order.setOrderPrice(orderPrice.doubleValue());
            BigDecimal realPrice = orderBean.getRealPrice();//订单支付总价
            order.setRealPrice(realPrice.doubleValue());
            BigDecimal advancePrice = orderBean.getAdvancePrice();//订单预付款
            order.setAdvancePrice(advancePrice.doubleValue());
            BigDecimal needpayPrice = orderBean.getNeedpayPrice();//订单待付款
            order.setNeedpayPrice(needpayPrice.doubleValue());
            String userName = orderBean.getUserName();
            order.setUserName(userName.trim());
            String userPhone = orderBean.getUserPhone();
            order.setUserPhone(userPhone.trim());
            String userAddr = orderBean.getUserAddr();
            order.setUserAddr(userAddr.trim());
            BigDecimal postPrice = orderBean.getPostPrice();
            order.setPostPrice(postPrice.doubleValue());
            Integer postWay = orderBean.getPostWay();
            order.setPostWay(postWay);
            String userRemark = orderBean.getUserRemark();
            order.setUserRemark(userRemark == null ? "" : userRemark.trim());
            order.setOrderStatus(2);//0待付款1已付款2确认中3已失败4待发货5待收货6已完成7已取消8已删除9退款中10已退款
//            if (orderBean.getAdvancePrice().compareTo(new BigDecimal(0)) == 0) {
//                order.setOrderStatus(2);//0待付款1已付款2确认中3已失败4待发货5待收货6已完成7已取消8已删除9退款中10已退款
//            }

            order.setOrderTime(new Timestamp(new Date().getTime()));
            Integer businessType = orderBean.getBusinessType();//业务类型 1.正常下单2.抢购3.预定
            order.setBusinessType(businessType);
            BigDecimal discountsPrice = orderBean.getDiscountsPrice();//优惠价格
            order.setDiscountsPrice(discountsPrice.doubleValue());
            String discountsRemark = orderBean.getDiscountsRemark();//优惠说明
            order.setDiscountsRemark(discountsRemark == null ? "" : discountsRemark.trim());
            order.setCreatetime(new Timestamp(new Date().getTime()));
            String remark = orderBean.getRemark();
            order.setRemark(remark);
            Integer partnersId = orderBean.getPartnersId();
            order.setPartnersId(partnersId);
            String partnersName = orderBean.getPartnersName();
            order.setPartnersName(partnersName);
            Integer discountsId = orderBean.getDiscountsId() == null ? 0 : orderBean.getDiscountsId();//优惠券标识
            order.setDiscountsId(discountsId);
            Integer userAddrId = orderBean.getUserAddrId();//自取或者配送地址标识
            order.setUserAddrId(userAddrId);
            order.setTicketJson(orderBean.getTicketJson());//优惠券二维码信息
            order.setStatusOpration(0);
            if (orderBean.getAdvancePrice().compareTo(new BigDecimal("0")) > 0) {
                order.setAdvancePayWay(orderBean.getAdvancePayWay());
            } else {
                order.setAdvancePayWay(4);
            }
            order.setPostName(orderBean.getPostMan());
            order.setPostPhone(orderBean.getPostPhone());

            int orderId = saveInfoDao.saveEntity(order);
            LOG.info("---->订单保存成功orderId=" + orderId);
            //订单商品
            String cardIds = "";
            List<OrderDetailBean> orderDetailBeansList = orderBean.getOrderDetailList();
            for (int i = 0; i < orderDetailBeansList.size(); i++) {
                OrderDetailBean detailBean = orderDetailBeansList.get(i);
                //购物车标识
                if (0 != detailBean.getCartId()) {
                    cardIds = cardIds + detailBean.getCartId() + ",";
                }
                TbOrderDetail detail = new TbOrderDetail();
                detail.setOrderId(orderId);
                detail.setGoodsId(detailBean.getGoodsId());
                detail.setGoodsName(detailBean.getGoodsName());
                detail.setGoodsSpec(detailBean.getGoodsSpec());
                detail.setGoodsSpecName(detailBean.getGoodsSpecName());
                detail.setGoodsSpecNameStr(detailBean.getGoodsSpecNameStr());
                detail.setGoodsCount(detailBean.getGoodsCount());
                detail.setSaleMoney(detailBean.getSalePrice());//销售价格//不传-----------
                detail.setGoodsTrueMoney(detailBean.getTrueMoney());//不传-----------
                detail.setGoodsPurchasePrice(detailBean.getPurchasePrice());//不传-----------
                detail.setTimePrice(detailBean.getTimePrice());//不传-----------
                detail.setDepositPrice(detailBean.getDepositPrice());//不传-----------
                detail.setShopId(detailBean.getShopId());//不传-----------
                detail.setShopName(detailBean.getShopName());//不传-----------
                detail.setPartnersId(orderBean.getPartnersId());//不传-----------
                detail.setPartnersName(orderBean.getPartnersName());//不传-----------
                detail.setGetAddrId(detailBean.getGetAddrId() == null ? 0 : detailBean.getGetAddrId());
                detail.setGetAddrName(detailBean.getGetAddrName());
                if (StringUtils.nonEmptyString(detailBean.getGetTime())) {
                    detail.setGetTime(new Timestamp(DatetimeUtil.convertObjectToDate(detailBean.getGetTime()).getTime()));
                }
                detail.setBackAddrId(detailBean.getBackAddrId() == null ? 0 : detailBean.getBackAddrId());
                detail.setBackAddrName(detailBean.getBackAddrName());
                if (StringUtils.nonEmptyString(detailBean.getBackTime())) {
                    detail.setBackTime(new Timestamp(DatetimeUtil.convertObjectToDate(detailBean.getBackTime()).getTime()));
                }
                detail.setDetailStatus(0);//商品详情状态 0未取货1已取货2已取消3已完成
                detail.setCreatetime(new Timestamp(new Date().getTime()));
                detail.setStatus(1);
                detail.setRemark(detailBean.getRemark());
                detail.setGoodsImg(detailBean.getGoodsImg());
                detail.setCartId(detailBean.getCartId() == null ? 0 : detailBean.getCartId());
                if (StringUtils.nonEmptyString(detailBean.getGetGoodsDate())) {
                    detail.setGetGoodsDate(new Timestamp(DatetimeUtil.convertObjectToDate(detailBean.getGetGoodsDate()).getTime()));
                }
                detail.setIsTicket(detailBean.getIsTicket() == null ? 0 : detailBean.getIsTicket());//--------------------------------------------------------------
                detail.setTimeGoodsId(detailBean.getTimeGoodsId() == null ? 0 : detailBean.getTimeGoodsId());
                detail.setTicketJson(detailBean.getTicketJson());//不传-----------商品券卡券信息
                detail.setPartnersId(detailBean.getPartnersId());
                detail.setPartnersName(detailBean.getPartnersName());
                int detailId = saveInfoDao.saveEntity(detail);
                //更新商品库存  是否单品
                if (detailBean.getIsSingle() == 1) {
                    //单品
                    saveInfoDao.updateGoodsStoreById(detail.getGoodsId(), detail.getGoodsCount());
                    //是否抢购商品(更新)
                    if (detail.getTimeGoodsId() > 0) {
                        saveInfoDao.updateTimeGoodsStoreById(detail.getGoodsId(), detail.getGoodsCount());
                    }
                } else {
                    // sku商品
                    saveInfoDao.updateGoodsSkuStoreById(detail.getGoodsId(), detailBean.getGoodsSpec(), detail.getGoodsCount());
                    saveInfoDao.updateGoodsStoreBySku(detail.getGoodsId());
                }
                LOG.info("---->订单详情保存成功orderId=" + orderId + "   detailId=" + detailId);
                List<OrderDetailProptyBean> proptyBeans = detailBean.getOrderDetailProptyList();
                if (null != proptyBeans) {
                    for (int j = 0; j < proptyBeans.size(); j++) {
                        OrderDetailProptyBean proptyBean = proptyBeans.get(j);
                        TbOrderDetailProperty tbOrderDetailProperty = new TbOrderDetailProperty();
                        tbOrderDetailProperty.setOrderId(orderId);
                        tbOrderDetailProperty.setDetailId(detailId);
                        tbOrderDetailProperty.setPropertyKey(proptyBean.getPropertyKey());
                        tbOrderDetailProperty.setPropertyValue(proptyBean.getPropertyValue());
                        tbOrderDetailProperty.setCreatetime(new Date());
                        int propertyId = saveInfoDao.saveEntity(tbOrderDetailProperty);
                        LOG.info("---->订单详情属性保存成功orderId=" + orderId + "   detailId=" + detailId + " propertyId=" + propertyId);
                    }
                }
            }
            //修改购物车商品状态
            if (!"".equals(cardIds)) {
                cardIds = cardIds.substring(0, cardIds.length() - 1);
                //状态0无效1有效2商品被编辑无效3已下单4已删除
                saveInfoDao.updateOrderCardStatus(cardIds, 3);
            }
            return true;
        } catch (Exception e) {
            LOG.info("saveOrder----->异常");
            throw new RuntimeException();
        }
//        return false;
    }

    /**
     * 保存二维码
     *
     * @param downQr
     * @param qrImgRoot qr图片保存的根目录
     * @return
     */
    @Transactional
    public int saveGenerateQR(TbTicketDownQr downQr, String qrImgRoot) {
        try {
            int id = saveInfoDao.saveEntity(downQr);
            //信息内容
            JSONObject info = new JSONObject();
            info.put("ticketId", downQr.getTicketId());
            info.put("qrId", id);
            downQr.setContent(info.toString());

            //生成二维码图片
            String contents = Base64Coder.encodeString(info.toString());
            TwoDimensionCode handler = new TwoDimensionCode();
            String imgName = DatetimeUtil.getNow("yyyyMMdd") + System.currentTimeMillis() + ".png";

            String prefYear = DatetimeUtil.getNow("yyyy");
            String prefMonth = DatetimeUtil.getNow("MM");

            String filePath = qrImgRoot + "/" + "qrImg" + "/" + prefYear + "/" + prefMonth;
            File file = new File(filePath);
            if (!file.exists() && !file.isDirectory()) {
                file.mkdirs();
            }
            String allImgPath = qrImgRoot + "/" + "qrImg" + "/" + prefYear + "/" + prefMonth + "/" + imgName;
            downQr.setQrImg("qrImg" + "/" + prefYear + "/" + prefMonth + "/" + imgName);
            handler.encoderQRCode(contents, allImgPath, "png");

            downQr.setRecodeState(0);
            saveInfoDao.saveUpdateEntity(downQr);
            return id;
        } catch (Exception e) {
            LOG.info("saveGenerateQR-----1>异常" + e);
            throw new RuntimeException();
        } catch (Throwable e) {
            LOG.info("saveGenerateQR-----2>异常" + e);
            throw new RuntimeException();
        }
    }


    /**
     * 用户注册
     */
    @Transactional
    public int saveUserInfo(String userMobile, String passwd, String uid) {
        try {
            TbUserInfo userInfo = new TbUserInfo();
            userInfo.setCreatetime(new Date());
            userInfo.setMobileValite(1);
            userInfo.setUserState(1);
            userInfo.setUserMobile(userMobile);
            userInfo.setLoginPass(passwd);
            userInfo.setEmailValite(0);
            int userId = saveInfoDao.saveEntity(userInfo);
            LOG.info("-------->新增用户注册=" + userId);
            //保存微信绑定
            TbUserSns userSns = new TbUserSns();
            userSns.setUserId(userId);
            userSns.setSnsType(4);
            userSns.setUid(uid);
            userSns.setIsUseImg(0);
            userSns.setCreatetime(new Date());
            userSns.setStatus(1);
            int userSnsId = saveInfoDao.saveEntity(userSns);
            LOG.info("-------->新增用户绑定=" + userSnsId);
            return userId;
        } catch (Exception e) {
            LOG.info("saveUserInfo-----1>异常" + e);
            throw new RuntimeException();
        } catch (Throwable e) {
            LOG.info("saveUserInfo-----2>异常" + e);
            throw new RuntimeException();
        }
    }

    /**
     * 更新验证码
     *
     * @param status
     * @param validParam
     * @param validType
     */
    public void updateTbVerificationStatus(int status, String validParam, int validType) {
        saveInfoDao.updateTbVerificationStatus(status, validParam, validType);
    }

    /**
     * 清空用户搜索记录
     *
     * @param userId
     */
    public void updateUserSearchHistory(int userId) {
        saveInfoDao.updateUserSearchHistory(userId);
    }

    /**
     * 重置密码
     *
     * @param phone
     * @param passwd
     */
    public void updateUserPasswd(String phone, String passwd) {
        saveInfoDao.updateUserPasswd(phone, passwd);
        //更新手机随机码为已使用
        saveInfoDao.updateTbVerificationStatus(2, phone, 3);
    }

    public void updateUserPasswdByUserId(int userId, String passwd) {
        saveInfoDao.updateUserPasswdByUserId(userId, passwd);
    }

    /**
     * 新增用户
     */
    @Transactional
    public int saveNewUser(TbUserInfo userInfo) {
        try {
            //保存用户
            int id = saveInfoDao.saveEntity(userInfo);
            //更新手机随机码为已使用
            saveInfoDao.updateTbVerificationStatus(2, userInfo.getUserMobile(), 5);
            return id;
        } catch (Exception e) {
            LOG.info("saveUserInfo-----1>异常" + e);
            throw new RuntimeException();
        } catch (Throwable e) {
            LOG.info("saveUserInfo-----2>异常" + e);
            throw new RuntimeException();
        }
    }

    @Override
    public void deletePasswdQuestion(int userId) {
        saveInfoDao.deletePasswdQuestion(userId);
    }

    /**
     * 微信注册和绑定
     */
    public TbUserInfo registerWeixin(JSONObject registerData) {
        try {
            TbUserInfo userInfo = new TbUserInfo();
            userInfo.setCreatetime(new Date());
            userInfo.setMobileValite(StringUtils.nonEmptyString(registerData.getString("phone")) ? 1 : 0);
            userInfo.setUserMobile(registerData.getString("phone"));
            userInfo.setEmailValite(0);
            userInfo.setUserState(1);
            userInfo.setLoginName("rs" + System.currentTimeMillis());
            userInfo.setLoginPass(MD5.enCodeStandard(System.currentTimeMillis() + ""));
            userInfo.setNickName(StringUtils.nonEmptyString(registerData.getString("nick_name")) ? registerData.getString("nick_name") : "用户" + System.currentTimeMillis());
            userInfo.setUserImg(registerData.getString("img"));
            userInfo.setLastlogintime(new Date());
            userInfo.setLogCount(1);
            saveInfoDao.saveEntity(userInfo);
            //保存绑定微信
            TbUserSns userSns = new TbUserSns();
            userSns.setUserId(userInfo.getUserId());
            userSns.setSnsType(4);//4微信
            userSns.setUid(registerData.getString("uid"));
            userSns.setOpenkey("");
            userSns.setSnsName(registerData.getString("nick_name"));
            userSns.setSnsAccount("");
            userSns.setSnsImg(registerData.getString("img"));
            userSns.setCreatetime(new Date());
            userSns.setIsUseImg(0);
            userSns.setStatus(1);
            int snsId = saveInfoDao.saveEntity(userSns);
            LOG.info("-------->新增保存绑定=" + snsId);
            return userInfo;
        } catch (Exception e) {
            LOG.info("registerWeixin-----1>异常" + e);
            throw new RuntimeException();
        } catch (Throwable e) {
            LOG.info("registerWeixin-----2>异常" + e);
            throw new RuntimeException();
        }
    }

    /**
     * 修改用户收货地址是否为默认
     *
     * @param userId
     * @param isDefault
     */
    public void updateUserAddrIsDefault(int userId, int isDefault, Integer id, int addrType) {
        saveInfoDao.updateUserAddrIsDefault(userId, isDefault, id, addrType);
    }

    public void updateUserAddrStatus(int userId, int status, Integer id) {
        try {
            saveInfoDao.updateUserAddrStatus(userId, status, id);
        } catch (Exception e) {
            LOG.info("registerWeixin-----1>异常" + e);
            throw new RuntimeException();
        } catch (Throwable e) {
            LOG.info("registerWeixin-----2>异常" + e);
            throw new RuntimeException();
        }
    }

    public void deleteUserAddrStatus(int userId, int addrtype) {
        saveInfoDao.deleteUserAddrStatus(userId, addrtype);
    }

    /**
     * 绑定手机号码
     */
    @Transactional
    public boolean boundUserPhone(TbUserInfo userInfo) {
        try {
            saveInfoDao.saveUpdateEntity(userInfo);
            //更新验证码
            saveInfoDao.updateTbVerificationStatus(2, userInfo.getUserMobile(), 1);//验证码类型:1手机绑定2修改密码3重置密码4手机解绑5手机注册
            //同时生成一个绑定记录
            TbUserSns userSns = new TbUserSns();
            userSns.setUserId(userInfo.getUserId());
            userSns.setSnsType(0);//4微信
            userSns.setUid(userInfo.getUserMobile());
            userSns.setOpenkey("");
            userSns.setSnsName("");
            userSns.setSnsAccount("");
            userSns.setSnsImg("");
            userSns.setCreatetime(new Date());
            userSns.setIsUseImg(0);
            userSns.setStatus(1);
            int snsId = saveInfoDao.saveEntity(userSns);
            LOG.info("-------->新增保存手机绑定=" + snsId);
            return true;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    @Transactional
    public boolean updateOrderStatus(int orderId, String goodsBackDate, String backAddrName, int backAddrId, String serviceRemark) {
        try {
            //更新订单
            saveInfoDao.updateOrderStatus(orderId, 12, serviceRemark);
            //更新订单详情
            saveInfoDao.updateOrderDetail(backAddrId, backAddrName, goodsBackDate, orderId);
            return true;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * 更新订单状态
     *
     * @param orderId
     * @param status
     */
    public void updateOrderStatus(int orderId, int status, String serviceRemark) {
        try {
            saveInfoDao.updateOrderStatus(orderId, status, serviceRemark);
            //更新订单详情状态
            if (status == 6) {
                saveInfoDao.updateOrderDetailStatus(orderId, 1);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}