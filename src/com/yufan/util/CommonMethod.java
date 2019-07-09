package com.yufan.util;

import com.yufan.pojo.TbGoods;
import com.yufan.task.bean.OrderBean;
import com.yufan.task.bean.OrderDetailBean;
import org.apache.log4j.Logger;

import java.math.BigDecimal;

/**
 * 公共类
 */
public class CommonMethod {

    private Logger LOG = Logger.getLogger(CommonMethod.class);
    private volatile static CommonMethod commonMethod = null;

    public static CommonMethod getInstance() {
        if (null == commonMethod) {
            synchronized (CommonMethod.class) {
                if (null == commonMethod) {
                    commonMethod = new CommonMethod();
                }
            }
        }
        return commonMethod;
    }

    /**
     * 订单参数校验
     *
     * @param orderBean
     * @return
     */
    public boolean checkOrderParam(OrderBean orderBean) {
        try {
            Integer userId = orderBean.getUserId();
            if (null == userId || 0 == userId) {
                LOG.info("---------->空userId");
                return false;
            }
            Integer goodsCount = orderBean.getGoodsCount();
            if (null == goodsCount || 0 == goodsCount) {
                LOG.info("---------->空goodsCount");
                return false;
            }
            BigDecimal orderPrice = orderBean.getOrderPrice();//订单支付总价
            if (null == orderPrice) {
                LOG.info("---------->空orderPrice");
                return false;
            }
            BigDecimal realPrice = orderBean.getRealPrice();//订单实际支付价格
            if (null == realPrice) {
                LOG.info("---------->空realPrice");
                return false;
            }
            BigDecimal advancePrice = orderBean.getAdvancePrice();//订单预付款
            if (null == advancePrice) {
                LOG.info("---------->空advancePrice");
                return false;
            }
            BigDecimal needpayPrice = orderBean.getNeedpayPrice();//订单待付款
            if (null == needpayPrice) {
                LOG.info("---------->空needpayPrice");
                return false;
            }
            BigDecimal depositPriceAll = orderBean.getDepositPriceAll();//订单押金总额
            if (null == depositPriceAll) {
                LOG.info("---------->空depositPriceAll");
                return false;
            }
            BigDecimal postPrice = orderBean.getPostPrice();//邮费
            if (null == postPrice) {
                LOG.info("---------->空postPrice");
                return false;
            }

            Integer businessType = orderBean.getBusinessType();//业务类型 1.正常下单2.抢购3.预定
            if (null == businessType || (1 != businessType && 2 != businessType && 3 != businessType)) {
                LOG.info("---------->空businessType业务类型 1.正常下单2.抢购3.预定");
                return false;
            }
            BigDecimal discountsPrice = orderBean.getDiscountsPrice();//优惠价格
            if (null == discountsPrice) {
                LOG.info("---------->空discountsPrice");
                return false;
            }
            Integer discountsId = orderBean.getDiscountsId();//优惠券标识
            if (null == discountsId || (discountsPrice.compareTo(new BigDecimal(0)) > 0 && discountsId == 0)) {
                LOG.info("---------->优惠券标识不能为空,优惠金额>0必须discountsId卡券标识要存在");
                return false;
            }

            Integer userAddrId = orderBean.getUserAddrId();
            if (null == userAddrId || userAddrId == 0) {
                LOG.info("---------->收货地址标识为空userAddrId为空");
                return false;
            }
            return true;
        } catch (Exception e) {
            LOG.info("checkOrderParam----->" + e);
        }
        return false;
    }

    /**
     * 详情必填参数
     *
     * @param orderDetailBean
     * @return
     */
    public boolean checkOrderDetailParam(OrderDetailBean orderDetailBean) {
        try {
            if (orderDetailBean == null || null == orderDetailBean.getGoodsCount() || null == orderDetailBean.getGoodsId() || orderDetailBean.getTimeGoodsId() == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            LOG.info("-------checkOrderDetailParam------->异常");
        }
        return false;
    }


    /**
     * 检验是否只能单独生成订单(不能加到购物车)
     *
     * @return
     */
    public boolean checkGoodsOrderSingle(TbGoods goods) {
        //商品券(单个生成订单)
        if (goods.getTicketId() > 0) {
            LOG.info("---------->商品券(单个生成订单)");
            return true;
        }
        //带押金的商品独立为一个订单
        if (goods.getDepositMoney().compareTo(new BigDecimal(0)) > 0) {
            LOG.info("---------->带押金的商品独立为一个订单");
            return true;
        }
        //抢购商品独立一个订单
        if (goods.getIsTimeGoods() == 1) {
            LOG.info("--------------->如果是抢购商品,独立一个订单");
            return true;
        }

        //虚拟商品,独立一个订单
        if (goods.getProperty() == 0) {
            LOG.info("--------------->如果是虚拟商品,独立一个订单");
            return true;
        }
        //限购商品,独立一个订单
        if (goods.getLimitWay() != 4) {
            LOG.info("---------->限购商品,独立一个订单");
            return true;
        }
        //预定商品,独立一个订单
        if (goods.getIsYuding() == 1) {
            LOG.info("---------->预定商品,独立一个订单");
            return true;
        }
        //租赁商品,话费商品(单个生成订单)  商品类型:0:实体商品1商品券2话费商品3租赁商品4服务商品
        if (goods.getGoodsType() != 0) {
            LOG.info("---------->租赁商品,话费商品,独立一个订单");
            return true;
        }
        return false;
    }


    /**
     * 校验是否为有效商品
     *
     * @param goods
     * @return
     */
    public boolean checkGoodsInfoEff(TbGoods goods) {
        try {
            //状态和上架状态
            if (goods.getStatus() != 1 || goods.getIsPutaway() != 2) {
                LOG.info("---->状态和上架状态无效");
                return false;
            }

            //销售时间
            String format = "yyyy-MM-dd";
            String nowDate = DatetimeUtil.getNow(format);
            //判断是否开始销售
            String startDate = DatetimeUtil.TimeStamp2Date(String.valueOf(goods.getStartTime().getTime())).split(" ")[0];//yyyy-MM-dd
            if (DatetimeUtil.compareDate(nowDate, startDate, format) < 0) {
                LOG.info("---->未开始销售");
                return false;
            }


            //判断是否长期销售 0:不是长期有效1长期有效
            if (goods.getValidDate() == 0) {
                //判断结束时间
                String endDate = DatetimeUtil.TimeStamp2Date(String.valueOf(goods.getEndTime().getTime())).split(" ")[0];//yyyy-MM-dd
                if (DatetimeUtil.compareDate(nowDate, endDate, format) > 0) {
                    LOG.info("---->已结束销售");
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            LOG.info("---------->校验是否为有效商品异常", e);
        }
        return false;
    }

}
