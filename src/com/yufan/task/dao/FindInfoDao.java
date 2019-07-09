package com.yufan.task.dao;

import com.yufan.common.bean.PageInfo;
import com.yufan.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-14 18:26
 * 功能介绍:
 */
public interface FindInfoDao {

    /**
     * 查询一级分类列表
     *
     * @return
     */
    public List<TbCatogryLevel1> loadTbCatogryLevel1List();

    public List<TbCatogryLevel1> loadTbCatogryLevel1List(String classifyCode);


    /**
     * 查询类目列表
     *
     * @return
     */
    public List<Map<String, Object>> loadTbCategoryListMap();

    public List<TbCategory> loadTbCategoryList();

    public List<TbCategory> loadTbCategoryList(String categoryCode);

    /**
     * 查询类目属性
     */
    public List<TbItemprops> loadTbItempropsList();

    public List<Map<String, Object>> loadTbItempropsListMap(int categoryId);

    /**
     * 查询属性值
     */
    public List<TbPropsValue> loadTbPropsValueList();

    public List<TbPropsValue> loadTbPropsValueList(int categoryId);

    public List<Map<String, Object>> loadTbPropsValueListMap(String valueIds);

    /**
     * 查询分类属性列表
     */
    public List<Map<String, Object>> loadCategoryItempropsListMap();

    public List<Map<String, Object>> loadCategoryItempropsListMap(String categoryCode);

    /**
     * 查询属性属性值列表
     */
    public List<Map<String, Object>> loadItempropsPropsValueListMap();

    public List<Map<String, Object>> loadItempropsPropsValueListMap(String itemCode);

    /**
     * 查询bannel图片列表 默认6张
     */
    public List<Map<String, Object>> loadBannelListMap(Integer bannelSize);

    public List<Map<String, Object>> loadBannelListMap(int bannelId);

    /**
     * 查询activity图片列表 默认4张
     */
    public List<Map<String, Object>> loadActivityListMap(Integer activitiySize);

    public List<Map<String, Object>> loadActivityListMap(int activityId);

    /**
     * 查询配送自取地址
     */
    public List<Map<String, Object>> loadSendAddrListMap();

    public List<Map<String, Object>> loadSendAddrListMap(int addrType);

    /**
     * 查询地址字母
     */
    public List<Map<String, Object>> loadSendAddrChar();

    public List<Map<String, Object>> loadSendAddrChar(int addrType);

    /**
     * 查询商家
     */
    public List<Map<String, Object>> loadPartnersListMap();

    public List<Map<String, Object>> loadPartnersListMap(int partnersId);

    /**
     * 查询商家字母
     */
    public List<Map<String, Object>> loadPartnersCharListMap();

    public List<Map<String, Object>> loadPartnersCharListMap(int partnersId);

    /**
     * 查询抢购商品
     */
    public List<Map<String, Object>> loadTimeGoodsListMap(Integer timeGoodsSize);

    public TbTimeGoods loadTbTimeGoodsInfo(int timeGoodsId, int goodsId);

    public TbTimeGoods loadTbTimeGoodsInfoByGoodsId(int goodsId);

    public PageInfo loadTimeGoodsPage(int current);

    public PageInfo loadTimeGoodsPage(int current, String goodsName, String categoryIds, String leve1Ids);

    /**
     * 查询热卖商品
     */
    public List<Map<String, Object>> loadHotGoodsListMap(Integer hotGoodsSize);

    public PageInfo loadHotGoodsPage(int current);

    public PageInfo loadHotGoodsPage(int current, String goodsName);

    public PageInfo loadHotGoodsCPage(int current, String categoryIds);

    public PageInfo loadHotGoodsPage(int current, String goodsName, String categoryIds);

    public PageInfo loadHotGoodsPage(int current, String goodsName, String categoryIds, String leve1Ids);


    /**
     * 查询推荐(按sort排序)商品
     */
    public List<Map<String, Object>> loadSortGoodsListMap(Integer sortGoodsSize);

    public List<Map<String, Object>> loadSortGoodsListMap(int current);

    public List<Map<String, Object>> loadSortGoodsListMap(int current, String goodsName);

    public List<Map<String, Object>> loadSortGoodsCListMap(int current, String categoryIds);

    public List<Map<String, Object>> loadSortGoodsListMap(int current, String goodsName, String categoryIds);

    public PageInfo loadSortGoodsPage(int current);

    public PageInfo loadSortGoodsPage(int current, String goodsName);

    public PageInfo loadSortGoodsCPage(int current, String categoryIds);

    public PageInfo loadSortGoodsPage(int current, String goodsName, String categoryIds);

    public PageInfo loadSortGoodsPage(int current, String goodsName, String categoryIds, String leve1Ids);

    /**
     * 查询最新商品
     */
    public List<Map<String, Object>> loadNewGoodsListMap(Integer newGoodsSize);

    public List<Map<String, Object>> loadNewGoodsListMap(int current);

    public List<Map<String, Object>> loadNewGoodsListMap(int current, String goodsName);

    public PageInfo loadNewGoodsPage(int current);

    public PageInfo loadNewGoodsPage(int current, String goodsName);

    public PageInfo loadNewGoodsCPage(int current, String categoryIds);

    public PageInfo loadNewGoodsPage(int current, String goodsName, String categoryIds);

    public PageInfo loadNewGoodsPage(int current, String goodsName, String categoryIds, String leve1Ids);

    /**
     * 商品搜索页面
     */
    public List<Map<String, Object>> queryHotGoodsWordSearchListMap();//热门商品搜索

    public List<Map<String, Object>> queryUserGoodsWordSearchListMap(int userId);//个人历史商品搜索

    /**
     * 查询商品详情
     */
    public List<Map<String, Object>> loadGoodsInfoListMap(int goodsId);//商品详情(有效和没有效的)

    public List<Map<String, Object>> loadGoodsInfoEffListMap(int goodsId);//查询有效的商品详情

    public TbGoods loadTbGoods(int goodsId);

    /**
     * 查询是否已关注
     * typeId 关注类型:1店铺2商品
     * userId
     * attentionId 关注id:商家id或者商品id
     */
    public boolean isAttentiond(int typeId, int userId, int attentionId);

    public TbAttention loadTbAttentionByParam(int userId, int attentionId, int typeId);

    /**
     * 查询图片
     * imgType 0主图1商品bannel2商品图片介绍
     * imgClassyfi 0.商品图片1.卡券图片2.店铺图片
     * relateId 关联标识
     */
    public List<Map<String, Object>> loadImgListMap(int imgType, int imgClassyfi, int relateId);

    public List<Map<String, Object>> loadImgListMap(int imgClassyfi, int relateId);

    /**
     * 查询商品sku
     */
    public List<Map<String, Object>> loadGoodsSkuByGoodsId(int goodsId);

    public List<Map<String, Object>> loadGoodsSku();

    public List<TbGoodsSku> loadTbGoodsSkuList(int goodsId);

    public TbGoodsSku loadTbGoodsSkuInfo(int goodsId, String spec);

    /**
     * 查询用户信息中心页面
     */
    public List<Map<String, Object>> loadUserCenterListMap(int userId);

    public TbUserInfo laodTbUserInfo(String loginName);

    public TbUserInfo laodTbUserInfoBuyPhone(String userMobie);

    public TbUserInfo laodTbUserInfoBuyUserId(int userId);

    /**
     * 查询用户投诉建议列表
     */
    public List<Map<String, Object>> loadUserComplainListMap(int userId);

    public List<Map<String, Object>> loadUserComplainListMap(int userId, Integer complainId);

    public PageInfo loadUserComplainPage(int currentPage, int userId);

    public TbComplain loadTbComplainById(int userId, int complainId);

    /**
     * 查询用户消息列表
     */
    public List<Map<String, Object>> loadUserNewsListMap(int userId);

    public PageInfo loadTBNewsPage(int current, int userId);

    public List<Map<String, Object>> loadTBNewsInfoListMap(int userId, int newsId);

    /**
     * 查询用户积分列表
     */
    public List<Map<String, Object>> loadUserJifenListMap(int userId);

    public PageInfo loadUserJifenPage(int current, int userId);

    public List<Map<String, Object>> loadUserJifenListMap(int userId, Integer jifenId);

    /**
     * 查询购物车商家
     */
    public List<Map<String, Object>> loadOrderCartPartnersListMap(int userId);

    public List<Map<String, Object>> loadOrderCartGoodsListMap(int userId);

    public int loadOrderCartGoodsCount(int userId);

    public int loadOrderCartGoodsCountEff(int userId);

    public TbOrderCart loadTbOrderCart(int userId, int goodsId, String spaceCode);

    public List<Map<String, Object>> loadOrderCartCheckGoodsListMap(String cardIds, int userId);

    /**
     * 查询订单列表
     */
    public PageInfo loadOrderPage(int current, int userId);

    public PageInfo loadOrderPage(int current, int userId, int status);

    public List<Map<String, Object>> loadOrderDetailListMap(String orderIds);

    public TbOrder loadTbOrderInfo(String orderNo);

    public List<TbOrderDetail> loadTbOrderDetailList(int orderId);

    /**
     * 查询参数列表
     */
    public List<TbParam> loadTbParamList(int status, int isMakeSure);

    public List<TbParam> loadTbParamList(Integer status);

    public List<TbParam> loadTbParamByCodeList(String paramCode, Integer status);

    public List<TbParam> loadTbParamByCodeList(String paramCode, String paramKey, Integer status);

    /**
     * 检验验证码
     */
    public TbVerification loadTbVerificationInfo(int validType, String validParam, String validCode);//validParam(验证标识参数 如：手机号,邮箱)validCode(验证码)

    /**
     * 查询用户绑定的信息
     */
    public TbUserSns loadTbUserSnsInfoByUID(String uid, int snsType);

    public TbUserSns loadTbUserSnsInfoByUserId(int userId, int snsType);

    public List<Map<String, Object>> loadTbUserSnsListMap(int userId);

    /**
     * 查询当天发送成功的验证码
     */
    public List<Map<String, Object>> loadTbVerificationListMap(String validParam, int validType);

    public List<Map<String, Object>> loadTbVerificationListMap(String date, String validParam);

    /**
     * 商品限购规则订单详情查询
     */
    public List<Map<String, Object>> loadGoodsOrderDetailLimitRuleListMap(int userId, int goodsId);//

    public List<Map<String, Object>> loadGoodsOrderDetailLimitRuleByYearListMap(int userId, int goodsId, String year);//

    public List<Map<String, Object>> loadGoodsOrderDetailLimitRuleByMonthListMap(int userId, int goodsId, String month);//

    public List<Map<String, Object>> loadGoodsOrderDetailLimitRuleByDateListMap(int userId, int goodsId, String date);//

    public List<Map<String, Object>> loadTimeGoodsOrderDetailLimitRuleListMap(int userId, int goodsId);//

    public List<Map<String, Object>> loadTimeGoodsOrderDetailLimitRuleByYearListMap(int userId, int timeGoodsId, String year);//

    public List<Map<String, Object>> loadTimeGoodsOrderDetailLimitRuleByMonthListMap(int userId, int timeGoodsId, String month);//

    public List<Map<String, Object>> loadTimeGoodsOrderDetailLimitRuleByDateListMap(int userId, int timeGoodsId, String date);//

    /**
     * 查询运费
     */
    public TbDistributionAddr loadTbDistributionAddr(int id);

    public TbDistributionAddr loadTbDistributionAddr(int id, Integer status);

    /**
     * 查询卡券
     */
    public TbTicket loadTbTicketById(int ticketId);

    public List<Map<String, Object>> loadEffeTicketListMap(int ticketId);//查询有效的卡券

    /**
     * 查询qr
     */
    public TbTicketDownQr loadTbTicketDownQrInfoById(int qrId);

    /**
     * 查询限购
     *
     * @param userId
     * @param ticketId
     * @return
     */
    public List<Map<String, Object>> loadTbTicketDownQr(int userId, int ticketId);

    public List<Map<String, Object>> loadTbTicketDownQrByDay(int userId, int ticketId, String day);

    public List<Map<String, Object>> loadTbTicketDownQrByMonth(int userId, int ticketId, String month);

    public List<Map<String, Object>> loadTbTicketDownQrByYear(int userId, int ticketId, String year);

    /**
     * 检验qr是否存在
     */
    public boolean isCodeExist(String changeCode, String checkCode);

    /**
     * 用户注册
     *
     * @return
     */
    public TbUserInfo loadTbUserInfoById(int userId);

    public TbUserInfo loadTbUserInfoByPhone(String userMobile);

    public TbUserInfo loadTbUserInfoByLoginName(String loginName);

    /**
     * 查询main页菜单
     *
     * @return
     */
    public List<Map<String, Object>> loadMainMenu(String limit);

    /**
     * 查询用户密保问题3条
     *
     * @return
     */
    public List<Map<String, Object>> queryUserQuestionListMap(int userId);


    /**
     * 查询全球地址
     *
     * @param parentId
     * @return
     */
    public List<Map<String, Object>> queryGlobalAddrListMap(String parentId);

    public List<Map<String, Object>> queryGlobalAddrListMapMark(String parentId);

    /**
     * 查询用户收货地址
     *
     * @param userId
     * @return
     */
    public List<Map<String, Object>> queryUserAddrListMap(int userId, Integer isDefault, Integer addrType);

    public List<Map<String, Object>> queryUserAddrListMapByRegionCodes(String regionCodes);

    public List<TbUserAddr> loadTbUserInfoList(int userId);

    public TbUserAddr loadTbUserAddr(int userId, int userAddrId, Integer status);

    public TbDistributionAddr loadTbDistributionAddrById(int id);

    public TbRegion loadTbRegionByRegionCode(String regionCode);

    /**
     * 查询缓存必须数据
     *
     * @return
     */
    public List<TbDistributionAddr> loadTbDistributionAddrList();

    public List<TbGoods> loadTbGoodsList();

    public List<TbGoodsSku> loadTbGoodsSku();

    public TbParam loadTbParamInfo(int paramId);

    public List<TbShop> loadTbShopList();

    public List<TbPartners> loadTbPartnersList();

    public TbShop loadTbshopInfo(int shopId);

    public TbPartners loadTbPartnersInfo(int partnersId);


    /**
     * 查询商品销售数
     */
    public List<Map<String, Object>> queryGoodsSaleCount(String goodsIds);
}
