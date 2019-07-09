package com.yufan.task.dao;

import com.mchange.v2.lang.StringUtils;
import com.yufan.common.bean.PageInfo;
import com.yufan.common.dao.GeneralDao;
import com.yufan.common.util.RsConstants;
import com.yufan.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-14 18:27
 * 功能介绍:
 */
@Repository
@Transactional
public class FindInfoDaoImpl implements FindInfoDao {

    @Autowired
    private GeneralDao generalDao;

    /**
     * 查询一级分类列表
     *
     * @return
     */
    @Override
    public List<TbCatogryLevel1> loadTbCatogryLevel1List() {
        String sql = " SELECT level_id ,level_code ,level_name,level_img,level_sort,createman,createtime,lastaltertime,lastalterman,status,remark from tb_catogry_level1 where `status`=1 order by level_sort desc ";
        return generalDao.queryListBySql(sql, TbCatogryLevel1.class);
    }

    @Override
    public List<TbCatogryLevel1> loadTbCatogryLevel1List(String classifyCode) {
        String sql = " SELECT level_id ,level_code,level_name ,level_img,level_sort,createman,createtime,lastaltertime,lastalterman,status,remark from tb_catogry_level1 where `status`=1 and level_code=? order by level_sort desc ";
        return generalDao.queryListBySql(sql, TbCatogryLevel1.class, classifyCode);
    }

    /**
     * 查询类目列表
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> loadTbCategoryListMap() {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ca.category_id,ca.category_name,ca.category_code,ca.category_img,ca.oute_id,rel.level_id  ");
        sql.append(" from tb_category ca LEFT JOIN tb_classyfy_catogry_rel rel on rel.category_id=ca.category_id ");
        sql.append(" where ca.status=1 and ca.is_show=1 ORDER BY ca.short DESC,ca.createtime DESC ");
        return generalDao.getBySql2(sql.toString());
    }

    @Override
    public List<TbCategory> loadTbCategoryList() {
        String sql = " SELECT category_id,category_name,parent_id,is_parent,short,oute_id,category_img,category_code,is_show,createman," +
                " createtime,lastaltertime,lastalterman,status,remark from tb_category where `status`=1 and is_show=1 ORDER BY short DESC,createtime DESC ";
        return generalDao.queryListBySql(sql, TbCategory.class);
    }

    @Override
    public List<TbCategory> loadTbCategoryList(String categoryCode) {
        String sql = " SELECT category_id,category_name,parent_id,is_parent,short,oute_id,category_img,category_code,is_show,createman," +
                " createtime,lastaltertime,lastalterman,status,remark from tb_category where `status`=1 and is_show=1 and category_code=? ORDER BY short DESC,createtime DESC ";
        return generalDao.queryListBySql(sql, TbCategory.class, categoryCode);
    }

    /**
     * 查询类目属性
     */
    @Override
    public List<TbItemprops> loadTbItempropsList() {
        String sql = " SELECT prop_id,prop_name,category_id,oute_id,is_sales,show_view,prop_img,prop_code,is_show,sort,createman,createtime,lastaltertime," +
                " lastalternan,status,remark from tb_itemprops where is_show=1 and status=1 ORDER BY sort desc,createtime desc ";
        return generalDao.queryListBySql(sql, TbItemprops.class);
    }

    @Override
    public List<Map<String, Object>> loadTbItempropsListMap(int categoryId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT prop_id,prop_name,oute_id,is_sales,prop_code,is_show, ");
        sql.append(" if(prop_img is null or prop_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',prop_img)) as prop_img ");
        sql.append(" from tb_itemprops where `status`=1 ");
        sql.append(" and category_id=? ");
        sql.append(" ORDER BY sort desc,createtime desc ");
        return generalDao.getBySql2(sql.toString(), categoryId);
    }

    /**
     * 查询属性值
     */
    @Override
    public List<TbPropsValue> loadTbPropsValueList() {
        String sql = " SELECT value_id,prop_id,value_name,category_id,oute_id,value,short,createman,createtime,lastaltertime,lastalterman,status," +
                " remark,value_img from tb_props_value where status=1  ORDER BY short desc,createtime desc ";
        return generalDao.queryListBySql(sql, TbPropsValue.class);
    }

    @Override
    public List<TbPropsValue> loadTbPropsValueList(int categoryId) {
        String sql = " SELECT value_id,prop_id,value_name,category_id,oute_id,value,short,createman,createtime,lastaltertime,lastalterman,status," +
                " remark,value_img from tb_props_value where status=1 and category_id=?  ORDER BY short desc,createtime desc ";
        return generalDao.queryListBySql(sql, TbPropsValue.class, categoryId);
    }

    @Override
    public List<Map<String, Object>> loadTbPropsValueListMap(String valueIds) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT pv.value_id,pv.value_name,it.prop_name,CONCAT(it.prop_name,'：',pv.value_name,' ') as goods_space_name_str ");
        sql.append(" from tb_props_value pv JOIN tb_itemprops it on it.prop_id=pv.prop_id where value_id in(" + valueIds + ") ");
        return generalDao.getBySql2(sql.toString());
    }

    /**
     * 查询分类属性列表
     */
    @Override
    public List<Map<String, Object>> loadCategoryItempropsListMap() {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ca.category_id,ca.category_name,ca.oute_id as ca_oute_id,ca.category_img,ca.category_code, ");
        sql.append(" item.prop_id,item.prop_name,item.oute_id as item_oute_id,item.is_sales,item.show_view,item.prop_img,item.prop_code ");
        sql.append(" from tb_category ca LEFT JOIN tb_itemprops item on ca.category_id=item.category_id and item.`status`=1 and item.is_show=1 ");
        sql.append(" where ca.`status`=1 and ca.is_show=1 ");
        sql.append(" ORDER BY ca.short desc,item.sort desc,ca.createtime desc,item.createtime desc ");
        return generalDao.getBySql2(sql.toString());
    }

    @Override
    public List<Map<String, Object>> loadCategoryItempropsListMap(String categoryCode) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ca.category_id,ca.category_name,ca.oute_id as ca_oute_id,ca.category_img,ca.category_code, ");
        sql.append(" item.prop_id,item.prop_name,item.oute_id as item_oute_id,item.is_sales,item.show_view,item.prop_img,item.prop_code ");
        sql.append(" from tb_category ca LEFT JOIN tb_itemprops item on ca.category_id=item.category_id and item.`status`=1 and item.is_show=1 ");
        sql.append(" where ca.`status`=1 and ca.is_show=1 and ca.category_code=? ");
        sql.append(" ORDER BY ca.short desc,item.sort desc,ca.createtime desc,item.createtime desc ");
        return generalDao.getBySql2(sql.toString(), categoryCode);
    }

    /**
     * 查询属性属性值列表
     */
    @Override
    public List<Map<String, Object>> loadItempropsPropsValueListMap() {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT item.prop_id,item.prop_name,item.category_id,item.oute_id as item_out_id,item.is_sales,item.show_view,item.prop_img,item.prop_code, ");
        sql.append(" pv.value_id,pv.value_name,pv.oute_id as pv_oute_id,pv.value,pv.value_img ");
        sql.append(" from tb_itemprops item LEFT JOIN tb_props_value pv on pv.prop_id=item.prop_id and pv.`status`=1 ");
        sql.append(" where item.`status`=1 and item.is_show=1 ");
        sql.append(" ORDER BY item.sort desc,pv.short desc,item.createtime desc,pv.createtime desc ");
        return generalDao.getBySql2(sql.toString());
    }

    @Override
    public List<Map<String, Object>> loadItempropsPropsValueListMap(String itemCode) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT item.prop_id,item.prop_name,item.category_id,item.oute_id as item_out_id,item.is_sales,item.show_view,item.prop_img,item.prop_code, ");
        sql.append(" pv.value_id,pv.value_name,pv.oute_id as pv_oute_id,pv.value,pv.value_img ");
        sql.append(" from tb_itemprops item LEFT JOIN tb_props_value pv on pv.prop_id=item.prop_id and pv.`status`=1 ");
        sql.append(" where item.`status`=1 and item.is_show=1 and item.prop_code=? ");
        sql.append(" ORDER BY item.sort desc,pv.short desc,item.createtime desc,pv.createtime desc ");
        return generalDao.getBySql2(sql.toString(), itemCode);
    }

    /**
     * 查询bannel图片列表 默认6张
     */
    @Override
    public List<Map<String, Object>> loadBannelListMap(Integer bannelSize) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT b.bannel_id,b.bannel_title,b.bannel_name,b.bannel_link,b.partners_id,p.partners_name, ");
        sql.append(" if(b.bannel_img is null or b.bannel_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',b.bannel_img)) as bannel_img ");
        sql.append(" from tb_bannel b JOIN tb_partners p on p.id=b.partners_id ");
        sql.append(" where (b.`status`=1 and b.valid_date=1  and DATE_FORMAT(NOW(),'%Y-%m-%d %T')>=DATE_FORMAT(b.start_time,'%Y-%m-%d %T') ) ");
        sql.append(" or (b.`status`=1 and DATE_FORMAT(NOW(),'%Y-%m-%d %T')>=DATE_FORMAT(b.start_time,'%Y-%m-%d %T') and DATE_FORMAT(NOW(),'%Y-%m-%d %T')<=DATE_FORMAT(b.end_time,'%Y-%m-%d %T')) ");
        sql.append(" ORDER BY b.weight desc,b.createtime desc LIMIT 0,"+bannelSize+" ");
        return generalDao.getBySql2(sql.toString());
    }

    @Override
    public List<Map<String, Object>> loadBannelListMap(int bannelId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT b.bannel_id,b.bannel_title,b.bannel_name,b.bannel_link,b.partners_id,p.partners_name, ");
        sql.append(" if(b.bannel_img is null or b.bannel_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',b.bannel_img)) as bannel_img ");
        sql.append(" from tb_bannel b JOIN tb_partners p on p.id=b.partners_id ");
        sql.append(" where (b.`status`=1 and b.valid_date=1 and b.bannel_id=? and b.bannel_id=1 and DATE_FORMAT(NOW(),'%Y-%m-%d %T')>=DATE_FORMAT(b.start_time,'%Y-%m-%d %T') ) ");
        sql.append(" or (b.`status`=1 and b.bannel_id=? and DATE_FORMAT(NOW(),'%Y-%m-%d %T')>=DATE_FORMAT(b.start_time,'%Y-%m-%d %T') and DATE_FORMAT(NOW(),'%Y-%m-%d %T')<=DATE_FORMAT(b.end_time,'%Y-%m-%d %T')) ");
        return generalDao.getBySql2(sql.toString(), bannelId, bannelId);
    }

    /**
     * 查询activity图片列表 默认4张
     */
    @Override
    public List<Map<String, Object>> loadActivityListMap(Integer activitiySize) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT a.activity_id,a.activity_title,a.activity_name,a.activity_link,a.activity_intro,a.partners_id,p.partners_name, ");
        sql.append(" if(a.activity_img is null or a.activity_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',a.activity_img)) as activity_img ");
        sql.append(" from tb_activity a LEFT JOIN tb_partners p on p.id=a.partners_id ");
        sql.append(" where (a.`status`=1 and a.valid_date=1 and DATE_FORMAT(NOW(),'%Y-%m-%d %T')>=DATE_FORMAT(a.start_time,'%Y-%m-%d %T') ) ");
        sql.append(" or (a.`status`=1 and DATE_FORMAT(NOW(),'%Y-%m-%d %T')>=DATE_FORMAT(a.start_time,'%Y-%m-%d %T') and DATE_FORMAT(NOW(),'%Y-%m-%d %T')<=DATE_FORMAT(a.end_time,'%Y-%m-%d %T')) ");
        sql.append(" ORDER BY a.weight desc,a.createtime desc LIMIT 0,"+activitiySize+" ");
        return generalDao.getBySql2(sql.toString());
    }

    @Override
    public List<Map<String, Object>> loadActivityListMap(int activityId) {

        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT a.activity_id,a.activity_title,a.activity_name,a.activity_link,a.activity_intro,a.partners_id,p.partners_name, ");
        sql.append(" if(a.activity_img is null or a.activity_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',a.activity_img)) as activity_img ");
        sql.append(" from tb_activity a LEFT JOIN tb_partners p on p.id=a.partners_id ");
        sql.append(" where (a.`status`=1 and a.valid_date=1  and a.activity_id=? and DATE_FORMAT(NOW(),'%Y-%m-%d %T')>=DATE_FORMAT(a.start_time,'%Y-%m-%d %T') ) ");
        sql.append(" or (a.`status`=1  and a.activity_id=? and DATE_FORMAT(NOW(),'%Y-%m-%d %T')>=DATE_FORMAT(a.start_time,'%Y-%m-%d %T') and DATE_FORMAT(NOW(),'%Y-%m-%d %T')<=DATE_FORMAT(a.end_time,'%Y-%m-%d %T')) ");
        return generalDao.getBySql2(sql.toString(), activityId, activityId);
    }

    /**
     * 查询配送自取地址
     */
    @Override
    public List<Map<String, Object>> loadSendAddrListMap() {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT id,detail_addr,responsible_man,responsible_phone,CONCAT(freight,'') as freight,sort_char,addr_prefix,addr_desc,addr_name,addr_type, ");
        sql.append(" if(addr_type=1,'配送地址','自取地址') as addr_type_name ");
        sql.append(" from tb_distribution_addr ");
        sql.append(" where `status`=1 order by sort_char,addr_short desc ");
        return generalDao.getBySql2(sql.toString());
    }

    @Override
    public List<Map<String, Object>> loadSendAddrListMap(int addrType) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT id,detail_addr,responsible_man,responsible_phone,CONCAT(freight,'') as freight,sort_char,addr_prefix,addr_desc,addr_name,addr_type, ");
        sql.append(" if(addr_type=1,'配送地址','自取地址') as addr_type_name ");
        sql.append(" from tb_distribution_addr ");
        sql.append(" where `status`=1 and addr_type=? order by sort_char,addr_short desc ");
        return generalDao.getBySql2(sql.toString(), addrType);
    }

    /**
     * 查询地址字母
     */
    @Override
    public List<Map<String, Object>> loadSendAddrChar() {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT sort_char from tb_distribution_addr where `status`=1 GROUP BY sort_char order by sort_char  ");
        return generalDao.getBySql2(sql.toString());
    }

    @Override
    public List<Map<String, Object>> loadSendAddrChar(int addrType) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT sort_char from tb_distribution_addr where `status`=1 and addr_type=? GROUP BY sort_char order by sort_char  ");
        return generalDao.getBySql2(sql.toString(), addrType);
    }

    /**
     * 查询商家
     */
    @Override
    public List<Map<String, Object>> loadPartnersListMap() {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT p.id as partners_id,p.partners_code,p.partners_name,p.shop_id,p.partners_sort,p.first_name_code,p.account,p.is_recommend, ");
        sql.append(" if(p.partners_img is null or p.partners_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',p.partners_img)) as partners_img ");
        sql.append(" from tb_partners p where p.`status`=1 ");
        sql.append(" ORDER BY p.is_recommend desc,p.partners_sort desc ");
        return generalDao.getBySql2(sql.toString());
    }

    @Override
    public List<Map<String, Object>> loadPartnersListMap(int partnersId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT p.id as partners_id,p.partners_code,p.partners_name,p.shop_id,p.partners_sort,p.first_name_code,p.account,p.is_recommend, ");
        sql.append(" if(p.partners_img is null or p.partners_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',p.partners_img)) as partners_img ");
        sql.append(" from tb_partners p where p.`status`=1 and p.id=? ");
        return generalDao.getBySql2(sql.toString(), partnersId);
    }

    /**
     * 查询商家字母
     */
    @Override
    public List<Map<String, Object>> loadPartnersCharListMap() {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT first_name_code from tb_partners where `status`=1  GROUP BY first_name_code  ORDER BY first_name_code  ");
        return generalDao.getBySql2(sql.toString());


    }

    @Override
    public List<Map<String, Object>> loadPartnersCharListMap(int partnersId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT first_name_code from tb_partners where `status`=1 and id=?  GROUP BY first_name_code  ORDER BY first_name_code  ");
        return generalDao.getBySql2(sql.toString(), partnersId);
    }

    /**
     * 查询抢购商品
     */
    @Override
    public List<Map<String, Object>> loadTimeGoodsListMap(Integer timeGoodsSize) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT tg.id,tg.goods_id,CONCAT(tg.time_price,'') as time_price,CONCAT(g.now_money,'') as now_money,tg.goods_store,tg.limit_num,tg.time_way,");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img,g.goods_name,g.is_single,g.goods_num  ");
        sql.append(" from tb_time_goods tg JOIN tb_goods g on g.goods_id=tg.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" where 1=1 and tg.`status`=1 and tg.is_make_sure=1 ");
        sql.append(" and DATE_FORMAT(tg.begin_time,'%Y-%m-%d %T')<=DATE_FORMAT(NOW(),'%Y-%m-%d %T') and DATE_FORMAT(tg.end_time,'%Y-%m-%d %T')>=DATE_FORMAT(NOW(),'%Y-%m-%d %T') ");
        sql.append(" and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and ( (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d')) ");
        sql.append(" ) ORDER BY tg.weight DESC,g.weight DESC LIMIT 0,"+timeGoodsSize+" ");
        return generalDao.getBySql2(sql.toString());
    }

    @Override
    public TbTimeGoods loadTbTimeGoodsInfo(int timeGoodsId, int goodsId) {
        String hql = "from TbTimeGoods where id=? and goodsId=? and status=? ";
        return generalDao.queryUniqueByHql(hql, timeGoodsId, goodsId, 1);
    }

    @Override
    public TbTimeGoods loadTbTimeGoodsInfoByGoodsId(int goodsId) {
        String hql = "from TbTimeGoods where  goodsId=? and status=? ";
        return generalDao.queryUniqueByHql(hql, goodsId, 1);
    }

    public PageInfo loadTimeGoodsPage(int current) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT tg.id as time_goods_id,tg.goods_id,CONCAT(tg.time_price,'') as now_money,CONCAT(g.now_money,'') as goods_now_money,CONCAT(g.true_money,'') as true_money,tg.goods_store,tg.limit_num,tg.time_way, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img,g.goods_name,g.is_single,g.goods_num ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts ");
        sql.append(" from tb_time_goods tg JOIN tb_goods g on g.goods_id=tg.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and tg.`status`=1 and tg.is_make_sure=1 ");
        sql.append(" and DATE_FORMAT(tg.begin_time,'%Y-%m-%d %T')<=DATE_FORMAT(NOW(),'%Y-%m-%d %T') and DATE_FORMAT(tg.end_time,'%Y-%m-%d %T')>=DATE_FORMAT(NOW(),'%Y-%m-%d %T') ");
        sql.append(" and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and ( (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))  ");
        sql.append(" ) ORDER BY tg.weight DESC,g.weight DESC ");
        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(current);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }

    public PageInfo loadTimeGoodsPage(int current, String goodsName, String categoryIds, String leve1Ids) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT tg.id as time_goods_id,tg.goods_id,CONCAT(tg.time_price,'') as now_money,CONCAT(g.now_money,'') as goods_now_money,CONCAT(g.true_money,'') as true_money,tg.goods_store,tg.limit_num,tg.time_way, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img,g.goods_name,g.is_single,g.goods_num ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts ");
        sql.append(" from tb_time_goods tg JOIN tb_goods g on g.goods_id=tg.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and tg.`status`=1 and tg.is_make_sure=1 ");
        sql.append(" and DATE_FORMAT(tg.begin_time,'%Y-%m-%d %T')<=DATE_FORMAT(NOW(),'%Y-%m-%d %T') and DATE_FORMAT(tg.end_time,'%Y-%m-%d %T')>=DATE_FORMAT(NOW(),'%Y-%m-%d %T') ");
        sql.append(" and g.`status`=1 and g.is_putaway=2 ");
        if (StringUtils.nonEmptyString(goodsName)) {
            sql.append(" and g.goods_name like '%" + goodsName + "%' ");
        }
        if (StringUtils.nonEmptyString(categoryIds)) {
            sql.append("  and g.classify_id in (" + categoryIds + ") ");
        }
        if (StringUtils.nonEmptyString(leve1Ids)) {
            sql.append("  and g.leve1_id in (" + leve1Ids + ") ");
        }
        sql.append(" and ( (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))  ");
        sql.append(" ) ORDER BY tg.weight DESC,g.weight DESC ");
        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(current);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }


    /**
     * 查询热卖商品
     */
    @Override
    public List<Map<String, Object>> loadHotGoodsListMap(Integer hotGoodsSize) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img  ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY tbOrder.buy_counts DESC,g.weight DESC,g.createtime DESC LIMIT 0,"+hotGoodsSize+" ");
        return generalDao.getBySql2(sql.toString());
    }

    @Override
    public PageInfo loadHotGoodsPage(int current) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img  ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY tbOrder.buy_counts DESC,g.weight DESC,g.createtime DESC ");
        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(current);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }

    @Override
    public PageInfo loadHotGoodsPage(int current, String goodsName) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img  ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and g.goods_name like '%" + goodsName.trim() + "%'  ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY tbOrder.buy_counts DESC,g.weight DESC,g.createtime DESC ");
        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(current);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }

    @Override
    public PageInfo loadHotGoodsCPage(int current, String categoryIds) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img  ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and g.classify_id in (" + categoryIds + ")  ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY tbOrder.buy_counts DESC,g.weight DESC,g.createtime DESC ");
        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(current);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }

    @Override
    public PageInfo loadHotGoodsPage(int current, String goodsName, String categoryIds) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img  ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and g.goods_name like '%" + goodsName.trim() + "%'  ");
        sql.append(" and g.classify_id in (" + categoryIds + ")  ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY tbOrder.buy_counts DESC,g.weight DESC,g.createtime DESC ");
        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(current);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }

    @Override
    public PageInfo loadHotGoodsPage(int current, String goodsName, String categoryIds, String leve1Ids) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img  ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3  GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        if (null != goodsName && StringUtils.nonEmptyString(goodsName.trim())) {
            sql.append(" and g.goods_name like '%" + goodsName.trim() + "%'  ");
        }
        if (StringUtils.nonEmptyString(categoryIds)) {
            sql.append(" and g.classify_id in (" + categoryIds + ")  ");
        }
        if (StringUtils.nonEmptyString(leve1Ids)) {
            sql.append(" and g.leve1_id in (" + leve1Ids + ")  ");
        }

        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY tbOrder.buy_counts DESC,g.weight DESC,g.createtime DESC ");
        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(current);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }


    /**
     * 查询推荐(按sort排序)商品
     */
    @Override
    public List<Map<String, Object>> loadSortGoodsListMap(Integer sortGoodsSize) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY g.weight DESC,g.createtime DESC LIMIT 0,"+sortGoodsSize+" ");
        return generalDao.getBySql2(sql.toString());
    }

    @Override
    public List<Map<String, Object>> loadSortGoodsListMap(int current) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY g.weight DESC,g.createtime DESC LIMIT ?,10 ");
        return generalDao.getBySql2(sql.toString(), current);
    }

    @Override
    public List<Map<String, Object>> loadSortGoodsListMap(int current, String goodsName) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and g.goods_name like '%?%' ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY g.weight DESC,g.createtime DESC LIMIT ?,10 ");
        return generalDao.getBySql2(sql.toString(), goodsName, current);
    }

    @Override
    public List<Map<String, Object>> loadSortGoodsCListMap(int current, String categoryIds) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and g.classify_id in (" + categoryIds + ") ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY g.weight DESC,g.createtime DESC LIMIT ?,10 ");
        return generalDao.getBySql2(sql.toString(), categoryIds, current);
    }

    @Override
    public List<Map<String, Object>> loadSortGoodsListMap(int current, String goodsName, String categoryIds) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and g.goods_name like '%?%' ");
        sql.append(" and g.classify_id in (" + categoryIds + ") ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY g.weight DESC,g.createtime DESC LIMIT ?,10 ");
        return generalDao.getBySql2(sql.toString(), goodsName, categoryIds, current);
    }

    @Override
    public PageInfo loadSortGoodsPage(int current) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY g.weight DESC,g.createtime DESC ");
        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(current);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }

    @Override
    public PageInfo loadSortGoodsPage(int current, String goodsName) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and g.goods_name like '%" + goodsName.trim() + "%' ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d')) ) ");
        sql.append(" ORDER BY g.weight DESC,g.createtime DESC ");
        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(current);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }

    @Override
    public PageInfo loadSortGoodsCPage(int current, String categoryIds) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and g.classify_id in (" + categoryIds + ") ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY g.weight DESC,g.createtime DESC  ");
        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(current);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }

    @Override
    public PageInfo loadSortGoodsPage(int current, String goodsName, String categoryIds) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and g.goods_name like '%" + goodsName.trim() + "%' ");
        sql.append(" and g.classify_id in (" + categoryIds + ") ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY g.weight DESC,g.createtime DESC ");
        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(current);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }

    @Override
    public PageInfo loadSortGoodsPage(int current, String goodsName, String categoryIds, String leve1Ids) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        if (null != goodsName && StringUtils.nonEmptyString(goodsName.trim())) {
            sql.append(" and g.goods_name like '%" + goodsName.trim() + "%' ");
        }
        if (StringUtils.nonEmptyString(categoryIds)) {
            sql.append(" and g.classify_id in (" + categoryIds + ") ");
        }
        if (StringUtils.nonEmptyString(leve1Ids)) {
            sql.append(" and g.leve1_id in (" + leve1Ids + ") ");
        }
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY g.weight DESC,g.createtime DESC ");
        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(current);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }

    /**
     * 查询最新商品
     */
    @Override
    public List<Map<String, Object>> loadNewGoodsListMap(Integer newGoodsSize) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY g.createtime DESC,g.weight DESC LIMIT 0,"+newGoodsSize+" ");
        return generalDao.getBySql2(sql.toString());
    }

    @Override
    public List<Map<String, Object>> loadNewGoodsListMap(int current) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY g.createtime DESC,g.weight DESC LIMIT ?,10 ");
        return generalDao.getBySql2(sql.toString(), current);
    }

    @Override
    public List<Map<String, Object>> loadNewGoodsListMap(int current, String goodsName) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and g.goods_name like '%?%' ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY g.createtime DESC,g.weight DESC LIMIT ?,10 ");
        return generalDao.getBySql2(sql.toString(), goodsName, current);
    }

    @Override
    public PageInfo loadNewGoodsPage(int current) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts ,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY g.createtime DESC,g.weight DESC ");
        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(current);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }

    @Override
    public PageInfo loadNewGoodsPage(int current, String goodsName) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and g.goods_name like '%" + goodsName.trim() + "%' ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY g.createtime DESC,g.weight DESC  ");
        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(current);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }

    @Override
    public PageInfo loadNewGoodsCPage(int current, String categoryIds) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and g.classify_id in (" + categoryIds + ") ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY g.createtime DESC,g.weight DESC  ");
        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(current);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }

    @Override
    public PageInfo loadNewGoodsPage(int current, String goodsName, String categoryIds) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and g.goods_name like '%" + goodsName.trim() + "%' ");
        sql.append(" and g.classify_id in (" + categoryIds + ") ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY g.createtime DESC,g.weight DESC  ");
        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(current);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }

    @Override
    public PageInfo loadNewGoodsPage(int current, String goodsName, String categoryIds, String leve1Ids) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.is_single,goodsSku.now_money AS sku_now_money,CONCAT(g.now_money,'') as now_money,CONCAT(g.true_money,'') as true_money, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img ");
        sql.append(" ,IFNULL(tbOrder.buy_counts,0) as shell_counts,g.goods_num ");
        sql.append(" from tb_goods g LEFT JOIN (SELECT goods_id,GROUP_CONCAT(now_money ORDER BY now_money ) as now_money  from tb_goods_sku GROUP BY goods_id) goodsSku on goodsSku.goods_id=g.goods_id ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`=1 JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.`status`=1 and g.is_putaway=2 ");
        if (null != goodsName && StringUtils.nonEmptyString(goodsName.trim())) {
            sql.append(" and g.goods_name like '%" + goodsName.trim() + "%' ");
        }
        if (StringUtils.nonEmptyString(categoryIds)) {
            sql.append(" and g.classify_id in (" + categoryIds + ") ");
        }
        if (StringUtils.nonEmptyString(leve1Ids)) {
            sql.append(" and g.leve1_id in (" + leve1Ids + ") ");
        }
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        sql.append(" ORDER BY g.createtime DESC,g.weight DESC  ");
        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(current);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }

    /**
     * 商品搜索页面
     */
    @Override
    public List<Map<String, Object>> queryHotGoodsWordSearchListMap() {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT tab.type_word from (SELECT COUNT(type_word) as c,type_word  from tb_search_history GROUP BY type_word) tab ORDER BY tab.c desc  LIMIT 0,9 ");
        return generalDao.getBySql2(sql.toString());
    }

    @Override
    public List<Map<String, Object>> queryUserGoodsWordSearchListMap(int userId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT  DISTINCT type_word from tb_search_history  where user_id=? and `status`=1   ORDER BY id desc  LIMIT 0,9 ");
        return generalDao.getBySql2(sql.toString(), userId);
    }

    /**
     * 查询商品详情
     */
    @Override
    public List<Map<String, Object>> loadGoodsInfoListMap(int goodsId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.title,CONCAT(g.true_money,'') as true_money,CONCAT(g.now_money,'') as now_money,g.intro,g.shop_id, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img , ");
        sql.append(" g.is_yuding,if(g.is_yuding=1,'接受预定','不接受预定') as yuding,g.get_way,g.is_invoice,if(g.is_invoice=1,'开发票','不开发票') as invoice, ");
        sql.append(" g.is_putaway,if(g.is_putaway=1,'上架','下架') as putaway,g.property,if(g.property=1,'实体商品','虚拟商品') as property_name, ");
        sql.append(" g.weight,g.classify_id,g.area_id,g.valid_date,if(g.valid_date=1,'长期有效','不是长期有效') as valid_date_name, ");
        sql.append(" DATE_FORMAT(g.start_time,'%Y-%m-%d') start_time,DATE_FORMAT(g.end_time,'%Y-%m-%d') end_time, ");
        sql.append(" g.is_single,if(g.is_single=1,'是单品','不是单品') single,g.is_return,if(g.is_return=1,'可退款','不可退款') sreturn, ");
        sql.append(" g.goods_code,g.goods_unit,g.goods_num,g.ticket_id,g.goods_type,s.shop_name,p.param_value as goods_type_name, ");
        sql.append(" DATE_FORMAT(g.createtime,'%Y-%m-%d %T') createtime,g.`status`, ");
        sql.append(" if(DATE_FORMAT(NOW(),'%Y-%m-%d')>DATE_FORMAT(g.end_time,'%Y-%m-%d'),'0','1') is_end ");
        sql.append(" if(DATE_FORMAT(NOW(),'%Y-%m-%d')<DATE_FORMAT(g.start_time,'%Y-%m-%d'),'0','1') is_begin,pa.partners_name, ");
        sql.append(" ca.category_name,leve.level_name,CONCAT(g.purchase_price) as purchase_price, ");
        sql.append(" g.is_time_goods,g.leve1_id,g.limit_way,g.limit_num,CONCAT(g.deposit_money,'') as deposit_money,g.peisong_zc_desc,g.peisong_pei_desc,IFNULL(tbOrder.buy_counts,0) as shell_counts, ");
        sql.append(" g.partners_id,g.limit_begin_time,CONCAT(g.advance_price,'') as advance_price,g.is_pay_online ");
        sql.append(" from tb_goods g ");
        sql.append(" JOIN tb_shop s on s.shop_id=g.shop_id   ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id   ");
        sql.append(" LEFT JOIN tb_param p on p.param_key=g.goods_type and p.param_code='goods_type' and p.is_make_sure=1 ");
        sql.append(" JOIN tb_category ca on ca.category_id=g.classify_id ");
        sql.append(" JOIN tb_classyfy_catogry_rel rel on rel.category_id=g.classify_id LEFT JOIN tb_catogry_level1 leve on leve.level_id=rel.level_id ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.goods_id=? ");
        return generalDao.getBySql2(sql.toString(), goodsId);
    }

    @Override
    public List<Map<String, Object>> loadGoodsInfoEffListMap(int goodsId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT g.goods_id,g.goods_name,g.title,CONCAT(g.true_money,'') as true_money,CONCAT(g.now_money,'') as now_money,g.intro,g.shop_id, ");
        sql.append(" if(g.goods_img is null or g.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',g.goods_img)) as goods_img , ");
        sql.append(" g.is_yuding,if(g.is_yuding=1,'接受预定','不接受预定') as yuding,g.get_way,g.is_invoice,if(g.is_invoice=1,'开发票','不开发票') as invoice, ");
        sql.append(" g.is_putaway,if(g.is_putaway=1,'上架','下架') as putaway,g.property,if(g.property=1,'实体商品','虚拟商品') as property_name, ");
        sql.append(" g.weight,g.classify_id,g.area_id,g.valid_date,if(g.valid_date=1,'长期有效','不是长期有效') as valid_date_name, ");
        sql.append(" DATE_FORMAT(g.start_time,'%Y-%m-%d') start_time,DATE_FORMAT(g.end_time,'%Y-%m-%d') end_time, ");
        sql.append(" g.is_single,if(g.is_single=1,'是单品','不是单品') single,g.is_return,if(g.is_return=1,'可退款','不可退款') sreturn, ");
        sql.append(" g.goods_code,g.goods_unit,g.goods_num,g.ticket_id,g.goods_type,s.shop_name,p.param_value as goods_type_name, ");
        sql.append(" DATE_FORMAT(g.createtime,'%Y-%m-%d %T') createtime,g.`status`, ");
        sql.append(" if(DATE_FORMAT(NOW(),'%Y-%m-%d')>DATE_FORMAT(g.end_time,'%Y-%m-%d'),'0','1') is_end, ");
        sql.append(" if(DATE_FORMAT(NOW(),'%Y-%m-%d')<DATE_FORMAT(g.start_time,'%Y-%m-%d'),'0','1') is_begin,pa.partners_name, ");
        sql.append(" ca.category_name,leve.level_name,CONCAT(g.purchase_price) as purchase_price, ");
        sql.append(" g.is_time_goods,g.leve1_id,g.limit_way,g.limit_num,CONCAT(g.deposit_money,'') as deposit_money,g.peisong_zc_desc,g.peisong_pei_desc,IFNULL(tbOrder.buy_counts,0) as shell_counts, ");
        sql.append(" g.partners_id,g.limit_begin_time,g.goods_img as goods_img_,CONCAT(g.advance_price,'') as advance_price,g.is_pay_online ");
        sql.append(" from tb_goods g ");
        sql.append(" JOIN tb_shop s on s.shop_id=g.shop_id and s.`status`=1 ");
        sql.append(" JOIN tb_partners pa on pa.id=g.partners_id and pa.`status`= 1 ");
        sql.append(" LEFT JOIN tb_param p on p.param_key=p.param_key=CONCAT(g.goods_type,'') and p.param_code='goods_type' and p.is_make_sure=1 ");
        sql.append(" JOIN tb_category ca on ca.category_id=g.classify_id ");
        sql.append(" JOIN tb_classyfy_catogry_rel rel on rel.category_id=g.classify_id LEFT JOIN tb_catogry_level1 leve on leve.level_id=rel.level_id ");
        sql.append(" LEFT JOIN (SELECT d.goods_id,SUM(d.goods_count) as buy_counts from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id where o.order_status !=7 and o.order_status !=10 and o.order_status !=3 GROUP BY goods_id) tbOrder on tbOrder.goods_id=g.goods_id ");
        sql.append(" where 1=1 and g.goods_id=? ");
        sql.append(" and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");
        return generalDao.getBySql2(sql.toString(), goodsId);
    }

    @Override
    public TbGoods loadTbGoods(int goodsId) {
        String hql = " from TbGoods where goodsId=? ";
        return generalDao.queryUniqueByHql(hql, goodsId);
    }

    /**
     * 查询是否已关注
     */
    @Override
    public boolean isAttentiond(int typeId, int userId, int attentionId) {
        String sql = " SELECT id,attention_id from tb_attention where type_id=? and `status`=1 and user_id=? and attention_id=? ";
        List list = generalDao.getBySql2(sql, typeId, userId, attentionId);
        if (null != list && list.size() > 0) {
            return true;
        }
        return false;
    }

    public TbAttention loadTbAttentionByParam(int userId, int attentionId, int typeId) {
        String hql = " from TbAttention where userId=? and attentionId=? and typeId=? and status=? ";
        return generalDao.queryUniqueByHql(hql, userId, attentionId, typeId, 1);
    }

    /**
     * 查询图片
     * imgType 0主图1商品bannel2商品图片介绍
     * imgClassyfi 0.商品图片1.卡券图片2.店铺图片
     * relateId 关联标识
     */
    @Override
    public List<Map<String, Object>> loadImgListMap(int imgType, int imgClassyfi, int relateId) {
        String sql = " SELECT if(img_url is null or img_url='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',img_url)) as img_url from tb_img where img_type=? and img_classyfi=? and relate_id=? ORDER BY img_sort desc LIMIT 0,4 ";
        return generalDao.getBySql2(sql, imgType, imgClassyfi, relateId);
    }

    public List<Map<String, Object>> loadImgListMap(int imgClassyfi, int relateId) {
        String sql = " SELECT if(img_url is null or img_url='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',img_url)) as img_url,img_type from tb_img where img_classyfi=? and relate_id=? ORDER BY img_sort desc,img_id desc ";
        return generalDao.getBySql2(sql, imgClassyfi, relateId);
    }

    /**
     * 查询商品sku
     */
    @Override
    public List<Map<String, Object>> loadGoodsSkuByGoodsId(int goodsId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT sku_id,goods_id,sku_name,CONCAT(true_money,'') as true_money,CONCAT(now_money,'') as now_money_,sku_code,prop_code,sku_num, ");
        sql.append(" if(sku_img is null or sku_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',sku_img)) as sku_img,CONCAT(purchase_price,'') as purchase_price ");
        sql.append(" from tb_goods_sku where goods_id=? ORDER BY now_money ");
        return generalDao.getBySql2(sql.toString(), goodsId);
    }

    public List<Map<String, Object>> loadGoodsSku() {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT sku_id,goods_id,sku_name,CONCAT(true_money,'') as true_money,CONCAT(now_money,'') as now_money_,sku_code,prop_code,sku_num, ");
        sql.append(" if(sku_img is null or sku_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',sku_img)) as sku_img,CONCAT(purchase_price,'') as purchase_price ");
        sql.append(" from tb_goods_sku  ORDER BY now_money ");
        return generalDao.getBySql2(sql.toString());
    }

    @Override
    public List<TbGoodsSku> loadTbGoodsSkuList(int goodsId) {
        String hql = "from TbGoodsSku where goodsId=? ";
        return generalDao.queryListByHql(hql, goodsId);
    }

    @Override
    public TbGoodsSku loadTbGoodsSkuInfo(int goodsId, String spec) {
        String hql = "from TbGoodsSku where goodsId=? and propCode=? ";
        return generalDao.queryUniqueByHql(hql, goodsId, spec);
    }

    /**
     * 查询用户信息中心页面
     */
    @Override
    public List<Map<String, Object>> loadUserCenterListMap(int userId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT u.user_id,u.login_name,u.nick_name,u.user_mobile, ");
        sql.append(" if(u.user_img is null or u.user_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',u.user_img)) as user_img, ");
        sql.append(" ifnull(tbOrder0.order_count,0) as wait_payorder,ifnull(tbOrder2.order_count,0) as sure_order,ifnull(tbOrder1.order_count,0) as ispay_order, ");
        sql.append(" ifnull(tbOrder5.order_count,0) as wait_getorder,ifnull(tbOrder3.order_count,0) as fail_order, ");
        sql.append(" ifnull(tbOrder6.order_count,0) as is_finish, ");//6
        sql.append(" ifnull(tbOrder7.order_count,0) as is_cancel, ");//7
        sql.append(" ifnull(tbOrder9.order_count,0) as watting_tuikuang, ");//9
        sql.append(" ifnull(tbOrder10.order_count,0) as is_tuikuang, ");//10
        sql.append(" ifnull(tbOrder11.order_count,0) as is_doing, ");//11

        sql.append(" ifnull(tbTickets.ticket_counts,0) as ticket_counts ");


        sql.append(" from tb_user_info u ");
        sql.append(" LEFT JOIN (SELECT o.user_id,COUNT(o.user_id) as order_count  from tb_order o where o.order_status=0 and user_id=" + userId + ") tbOrder0 on tbOrder0.user_id=u.user_id ");
        sql.append(" LEFT JOIN (SELECT o.user_id,COUNT(o.user_id) as order_count  from tb_order o where o.order_status=1 and user_id=" + userId + ") tbOrder1 on tbOrder1.user_id=u.user_id ");
        sql.append(" LEFT JOIN (SELECT o.user_id,COUNT(o.user_id) as order_count  from tb_order o where o.order_status=2 and user_id=" + userId + ") tbOrder2 on tbOrder2.user_id=u.user_id ");
        sql.append(" LEFT JOIN (SELECT o.user_id,COUNT(o.user_id) as order_count  from tb_order o where o.order_status=3 and user_id=" + userId + ") tbOrder3 on tbOrder3.user_id=u.user_id ");
        sql.append(" LEFT JOIN (SELECT o.user_id,COUNT(o.user_id) as order_count  from tb_order o where o.order_status=5 and user_id=" + userId + ") tbOrder5 on tbOrder5.user_id=u.user_id ");
        sql.append(" LEFT JOIN (SELECT o.user_id,COUNT(o.user_id) as order_count  from tb_order o where o.order_status=6 and user_id=" + userId + ") tbOrder6 on tbOrder6.user_id=u.user_id ");
        sql.append(" LEFT JOIN (SELECT o.user_id,COUNT(o.user_id) as order_count  from tb_order o where o.order_status=7 and user_id=" + userId + ") tbOrder7 on tbOrder7.user_id=u.user_id ");
        sql.append(" LEFT JOIN (SELECT o.user_id,COUNT(o.user_id) as order_count  from tb_order o where o.order_status=9 and user_id=" + userId + ") tbOrder9 on tbOrder9.user_id=u.user_id ");
        sql.append(" LEFT JOIN (SELECT o.user_id,COUNT(o.user_id) as order_count  from tb_order o where o.order_status=10 and user_id=" + userId + ") tbOrder10 on tbOrder10.user_id=u.user_id ");
        sql.append(" LEFT JOIN (SELECT o.user_id,COUNT(o.user_id) as order_count  from tb_order o where o.order_status=11 and user_id=" + userId + ") tbOrder11 on tbOrder11.user_id=u.user_id ");
        sql.append(" LEFT JOIN (SELECT user_id,COUNT(user_id) as ticket_counts from tb_ticket_down_qr qr where user_id=" + userId + ") tbTickets on tbTickets.user_id=u.user_id ");
        sql.append(" where u.user_id=" + userId + "  and u.user_state=1 ");
        return generalDao.getBySql2(sql.toString());
    }

    @Override
    public TbUserInfo laodTbUserInfo(String loginName) {
        String hql = " from TbUserInfo where loginName=? or userMobile=? and userState !=3 ";
        return generalDao.queryUniqueByHql(hql, loginName, loginName);
    }

    @Override
    public TbUserInfo laodTbUserInfoBuyPhone(String userMobie) {
        String hql = " from TbUserInfo where userMobile=? and userState !=3 ";
        return generalDao.queryUniqueByHql(hql, userMobie);
    }

    @Override
    public TbUserInfo laodTbUserInfoBuyUserId(int userId) {
        String hql = " from TbUserInfo where userId=? and userState !=3 ";
        return generalDao.queryUniqueByHql(hql, userId);
    }

    /**
     * 查询用户投诉建议表
     */
    @Override
    public List<Map<String, Object>> loadUserComplainListMap(int userId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT id,user_id as userId,information,contents,`status`,is_read as isRead,DATE_FORMAT(createtime,'%Y-%m-%d %T') as createtime, ");
        sql.append(" DATE_FORMAT(lastaltertime,'%Y-%m-%d %T') as lastaltertime,lastalterman ");
        sql.append(" from tb_complain where user_id=?   ");
        sql.append(" ORDER BY createtime desc ");
        return generalDao.getBySql2(sql.toString(), userId);
    }

    @Override
    public List<Map<String, Object>> loadUserComplainListMap(int userId, Integer complainId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT id,user_id,information,contents,`status`,is_read,DATE_FORMAT(createtime,'%Y-%m-%d %T') as createtime, ");
        sql.append(" DATE_FORMAT(lastaltertime,'%Y-%m-%d %T') as lastaltertime,lastalterman ");
        sql.append(" from tb_complain where user_id=?  and id=? ");
        return generalDao.getBySql2(sql.toString(), userId, complainId);
    }

    @Override
    public PageInfo loadUserComplainPage(int currentPage, int userId) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT id,user_id,information,contents,`status`,is_read,DATE_FORMAT(createtime,'%Y-%m-%d %T') as createtime, ");
        sql.append(" DATE_FORMAT(lastaltertime,'%Y-%m-%d %T') as lastaltertime,lastalterman ");
        sql.append(" from tb_complain where user_id=" + userId + "  ");
        sql.append(" ORDER BY createtime desc ");
        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(currentPage);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }

    @Override
    public TbComplain loadTbComplainById(int userId, int complainId) {
        String hql = " from TbComplain where userId=? and  id=? ";
        return generalDao.queryUniqueByHql(hql, userId, complainId);
    }

    /**
     * 查询用户消息列表
     */
    @Override
    public List<Map<String, Object>> loadUserNewsListMap(int userId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT news_id,news_title,contents,`status`,is_read,createman,DATE_FORMAT(createtime,'%Y-%m-%d %T') as createtime , ");
        sql.append(" DATE_FORMAT(lastaltertime,'%Y-%m-%d %T') as lastaltertime from tb_news ");
        sql.append(" where user_id=? ");
        return generalDao.getBySql2(sql.toString(), userId);
    }


    @Override
    public List<Map<String, Object>> loadTBNewsInfoListMap(int userId, int newsId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT news_id,news_title,contents,status,is_read,DATE_FORMAT(createtime,'%Y-%m-%d %T') as createtime, ");
        sql.append(" createman,DATE_FORMAT(lastaltertime,'%Y-%m-%d %T') as lastaltertime,user_id from tb_news ");
        sql.append(" where news_id=" + newsId + " and (user_id=" + userId + " or user_id=0 ) ");
        return generalDao.getBySql2(sql.toString());
    }


    @Override
    public PageInfo loadTBNewsPage(int current, int userId) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT news_id,news_title,contents,status,is_read,DATE_FORMAT(createtime,'%Y-%m-%d %T') as createtime, ");
        sql.append(" createman,DATE_FORMAT(lastaltertime,'%Y-%m-%d %T') as lastaltertime,user_id from tb_news ");
        sql.append(" where user_id =0 or user_id=" + userId + " ");
        sql.append(" ORDER BY createman desc ");

        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(current);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }

    /**
     * 查询用户积分列表
     */
    @Override
    public List<Map<String, Object>> loadUserJifenListMap(int userId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT j.id,j.task_id,j.is_inout,j.jifen,j.items,DATE_FORMAT(j.createtime,'%Y-%m-%d %T') as createtime,t.task_name,CONCAT(if(j.is_inout=1,'+','-'),j.jifen) as jifen_ ");
        sql.append(" from tb_jifen j LEFT JOIN tb_task t on t.task_id=j.task_id ");
        sql.append(" where j.user_id=? and j.status=2   ");
        return generalDao.getBySql2(sql.toString(), userId);
    }

    public PageInfo loadUserJifenPage(int current, int userId) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT j.id,j.task_id,j.is_inout,j.jifen,j.items,DATE_FORMAT(j.createtime,'%Y-%m-%d %T') as createtime,t.task_name,CONCAT(if(j.is_inout=1,'+','-'),j.jifen) as jifen_ ");
        sql.append(" from tb_jifen j LEFT JOIN tb_task t on t.task_id=j.task_id ");
        sql.append(" where j.user_id=" + userId + " and j.status=2 ");
        sql.append(" ORDER BY j.lastaltertime desc  ");
        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(current);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }

    @Override
    public List<Map<String, Object>> loadUserJifenListMap(int userId, Integer jifenId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT j.id,j.task_id,j.is_inout,j.jifen,j.items,DATE_FORMAT(j.createtime,'%Y-%m-%d %T') as createtime,t.task_name,CONCAT(if(j.is_inout=1,'+','-'),j.jifen) as jifen_ ");
        sql.append(" from tb_jifen j LEFT JOIN tb_task t on t.task_id=j.task_id ");
        sql.append(" where j.user_id=? and j.id=? and j.status=2  ");
        return generalDao.getBySql2(sql.toString(), userId, jifenId);
    }

    /**
     * 查询购物车商家
     */
    @Override
    public List<Map<String, Object>> loadOrderCartPartnersListMap(int userId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT DISTINCT cart.partners_id,cart.partners_name,cart.partners_img from ");
        sql.append(" (SELECT c.cart_id,c.partners_id,p.partners_name,d.detail_id, ");
        sql.append(" if(p.partners_img is null or p.partners_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',p.partners_img)) as partners_img ");
        sql.append(" from tb_order_cart c LEFT JOIN tb_order_detail d on d.cart_id=c.cart_id ");
        sql.append(" JOIN tb_partners p on p.id=c.partners_id and p.`status`=1 and c.`status`!=0 and c.user_id=? ORDER BY c.createtime DESC ) cart where cart.detail_id is null ");
        return generalDao.getBySql2(sql.toString(), userId);
    }


    @Override
    public List<Map<String, Object>> loadOrderCartGoodsListMap(int userId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT cart.goods_id,cart.cart_id,cart.partners_id,cart.partners_name,cart.goods_name,cart.goods_count,cart.goods_price,cart.goods_img,cart.status,cart.true_money, ");
        sql.append(" cart.goods_spec_name,DATE_FORMAT(cart.createtime,'%Y-%m-%d %T') as createtime,cart.pay_type,cart.goods_spec,cart.goods_spec_name_str,cart.is_single, ");
        sql.append(" cart.property,cart.get_way,cart.goods_type,cart.deposit_money,cart.goods_advance_price,cart.is_pay_online ");
        sql.append("  from  (SELECT c.cart_id,c.partners_id,p.partners_name,d.detail_id,c.goods_name,SUM(c.goods_count) as goods_count,CONCAT(c.goods_price,'') as  goods_price,CONCAT(c.true_money,'') as  true_money, ");
        sql.append(" if(c.goods_img is null or c.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',c.goods_img)) as goods_img, ");
        sql.append(" c.goods_spec_name,c.goods_spec_name_str,c.status,c.createtime,c.pay_type,c.goods_spec,g.is_single,g.goods_id, ");
        sql.append(" g.property,g.get_way,g.goods_type,g.deposit_money,g.advance_price as goods_advance_price,g.is_pay_online ");
        sql.append(" from tb_order_cart c LEFT JOIN tb_order_detail d on d.cart_id=c.cart_id join tb_goods g on g.goods_id=c.goods_id ");
        sql.append(" JOIN tb_partners p on p.id=c.partners_id and p.`status`=1 and c.status in(1,2) and c.user_id=" + userId + " GROUP BY c.goods_id,c.`status`,c.goods_spec ) cart where cart.detail_id is null   ORDER BY cart.createtime DESC ");
        return generalDao.getBySql2(sql.toString());
    }

    public int loadOrderCartGoodsCount(int userId) {
        String sql = " SELECT cart_id from tb_order_cart where user_id=? and status=1 ";
        List<Map<String, Object>> list = generalDao.getBySql2(sql.toString(), userId);
        return list.size();
    }

    public int loadOrderCartGoodsCountEff(int userId) {
        String sql = " SELECT cart_id from tb_order_cart where user_id=? and status=1 ";
        List<Map<String, Object>> list = generalDao.getBySql2(sql.toString(), userId);
        return list.size();
    }

    public TbOrderCart loadTbOrderCart(int userId, int goodsId, String spaceCode) {
        String hql = " from TbOrderCart where userId=? and  goodsId=? and status=1 ";
        if (StringUtils.nonEmptyString(spaceCode)) {
            hql = hql + " and  goodsSpec=? ";
            return generalDao.queryUniqueByHql(hql, userId, goodsId, spaceCode);
        } else {
            return generalDao.queryUniqueByHql(hql, userId, goodsId);
        }
    }

    public List<Map<String, Object>> loadOrderCartCheckGoodsListMap(String cardIds, int userId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT cart.cart_id,cart.goods_id,cart.goods_spec, cart.goods_price as sale_price,cart.goods_count, ");
        sql.append(" g.is_single,g.get_way,g.goods_type,g.property,g.now_money,g.goods_num  ");
        sql.append(" from tb_order_cart cart JOIN tb_goods g on g.goods_id=cart.goods_id ");
        sql.append(" where cart.status=1 and cart.user_id=" + userId + " and cart.cart_id in (" + cardIds + ") ");
        sql.append(" and g.`status`=1 and g.is_putaway=2 ");
        sql.append(" and cart.createtime>g.lastaltertime ");
//        sql.append(" and cart.goods_count<=g.goods_num ");
        sql.append(" and g.limit_way=4 ");
        sql.append(" and g.goods_type=0 ");// -- 商品类型:0:实体商品1商品券2话费商品3租赁商品4服务商品
        sql.append(" and g.deposit_money=0 ");// -- 押金
        sql.append(" and g.property=1 ");// -- 0虚拟商品1实体商品
        sql.append(" and g.ticket_id=0 ");// -- 0则代表不是商品券
        sql.append(" and g.is_time_goods=0 ");// -- 0不是抢购商品
        sql.append(" and ((DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and g.valid_date=1) OR ");
        sql.append(" (DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(g.start_time,'%Y-%m-%d') and DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(g.end_time,'%Y-%m-%d'))) ");

        return generalDao.getBySql2(sql.toString());
    }

    /**
     * 查询订单列表
     */
    @Override
    public PageInfo loadOrderPage(int current, int userId) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT o.order_id,o.order_no,DATE_FORMAT(o.createtime,'%Y-%m-%d %T') as createtime,CONCAT(o.real_price,'') as real_price,CONCAT(o.order_price,'') as order_price,o.order_status,o.goods_count, ");
        sql.append(" o.partners_id,o.partners_name,p.param_value as order_status_name from tb_order o ");
        sql.append(" JOIN tb_param p on p.param_code='order_status' and CONCAT(p.param_key,'')=o.order_status ");
        sql.append(" where o.user_id=" + userId + " ORDER BY o.createtime desc ");
        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(current);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }

    @Override
    public PageInfo loadOrderPage(int current, int userId, int status) {
        PageInfo pageInfo = new PageInfo();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT o.order_id,o.order_no,DATE_FORMAT(o.createtime,'%Y-%m-%d %T') as createtime,CONCAT(o.real_price,'') as real_price,CONCAT(o.order_price,'') as order_price,o.order_status,o.goods_count, ");
        sql.append(" o.partners_id,o.partners_name,p.param_value as order_status_name from tb_order o ");
        sql.append(" JOIN tb_param p on p.param_code='order_status' and CONCAT(p.param_key,'')=o.order_status ");
        sql.append(" where o.user_id=" + userId + " and  o.order_status=" + status + "  ORDER BY o.createtime desc ");
        pageInfo.setSql(sql.toString());
        pageInfo.setCurrePage(current);
        pageInfo.setPageSize(RsConstants.PAGE_SIZE);
        return generalDao.sqlPageMapInfo(pageInfo);
    }

    @Override
    public List<Map<String, Object>> loadOrderDetailListMap(String orderIds) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT d.order_id,d.goods_id,d.goods_name,CONCAT(d.sale_money,'') as sale_money,d.goods_count,d.goods_spec_name,d.goods_spec_name_str, ");
        sql.append(" if(d.goods_img is null or d.goods_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',d.goods_img)) as goods_img ");
        sql.append(" from tb_order_detail d where d.order_id in (" + orderIds + ") ");
        return generalDao.getBySql2(sql.toString());
    }

    @Override
    public TbOrder loadTbOrderInfo(String orderNo) {
        String hql = " from TbOrder where orderNo=? ";
        return generalDao.queryUniqueByHql(hql, orderNo);
    }

    @Override
    public List<TbOrderDetail> loadTbOrderDetailList(int orderId) {
        String hql = " from TbOrderDetail where orderId=? ";
        return generalDao.queryListByHql(hql, orderId);
    }

    /**
     * 查询参数列表
     */
    @Override
    public List<TbParam> loadTbParamList(int status, int isMakeSure) {
        StringBuffer hql = new StringBuffer(" from TbParam where status=? and isMakeSure=? ");
        return generalDao.queryListByHql(hql.toString(), status, isMakeSure);
    }

    @Override
    public List<TbParam> loadTbParamList(Integer status) {
        StringBuffer hql = new StringBuffer(" from TbParam ");
        if (null != status) {
            hql.append(" where status=" + status + " ");
        }
        return generalDao.queryListByHql(hql.toString());
    }

    @Override
    public List<TbParam> loadTbParamByCodeList(String paramCode, Integer status) {
        StringBuffer hql = new StringBuffer(" from TbParam where paramCode=? ");
        if (null != status) {
            hql.append(" and status=" + status + " ");
        }
        return generalDao.queryListByHql(hql.toString(), paramCode);
    }

    @Override
    public List<TbParam> loadTbParamByCodeList(String paramCode, String paramKey, Integer status) {
        StringBuffer hql = new StringBuffer(" from TbParam where paramCode=? and paramKey=? ");
        if (null != status) {
            hql.append(" and status=" + status + " ");
        }
        return generalDao.queryListByHql(hql.toString(), paramCode, paramKey);
    }

    /**
     * 检验验证码
     */
    @Override
    public TbVerification loadTbVerificationInfo(int validType, String validParam, String validCode) {
        String hql = " from TbVerification where validParam=? and validCode=? and status=? and validType=? and  sendStatus=2 ";
        return generalDao.queryUniqueByHql(hql, validParam, validCode, 1, validType);
    }

    /**
     * 查询用户绑定的信息
     */
    @Override
    public TbUserSns loadTbUserSnsInfoByUID(String uid, int snstype) {
        String hql = " from TbUserSns where uid=? and status=? and snsType=? ";
        return generalDao.queryUniqueByHql(hql, uid, 1, snstype);
    }

    @Override
    public TbUserSns loadTbUserSnsInfoByUserId(int userId, int snsType) {
        String hql = " from TbUserSns where userId=? and status=? and snsType=? ";
        return generalDao.queryUniqueByHql(hql, userId, 1, snsType);
    }

    public List<Map<String, Object>> loadTbUserSnsListMap(int userId) {
        String sql = " SELECT sns_id,user_id,sns_type,uid,openkey,sns_name,sns_account,sns_img,is_use_img,DATE_FORMAT(createtime,'%Y-%m-%d %T') as createtime from tb_user_sns where `status`=1 and user_id=? ";
        return generalDao.getBySql2(sql, userId);
    }

    /**
     * 查询当天发送成功的验证码
     */
    @Override
    public List<Map<String, Object>> loadTbVerificationListMap(String validParam, int validType) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT  id,valid_type,valid_code,`status`,send_status,DATE_FORMAT(pass_time,'%Y-%m-%d %T') as pass_time ");
        sql.append(" from tb_verification where  valid_param=? and valid_type=? and send_status=2 and `status` =1 ORDER BY id DESC ");
        return generalDao.getBySql2(sql.toString(), validParam, validType);
    }

    @Override
    public List<Map<String, Object>> loadTbVerificationListMap(String date, String validParam) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT  id,valid_type,valid_code,`status`,send_status,DATE_FORMAT(pass_time,'%Y-%m-%d %T') as pass_time ");
        sql.append(" from tb_verification where DATE_FORMAT(createtime,'%Y-%m-%d')=? and valid_param=?  and send_status=2  ORDER BY id DESC ");
        return generalDao.getBySql2(sql.toString(), date, validParam);
    }

    /**
     * 商品限购规则订单详情查询
     */
    @Override
    public List<Map<String, Object>> loadGoodsOrderDetailLimitRuleListMap(int userId, int goodsId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT d.goods_id,ifnull(SUM(d.goods_count),0) as goods_count from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id ");
        sql.append(" JOIN tb_goods g on g.goods_id=d.goods_id ");
        sql.append(" where 1=1 and DATE_FORMAT(o.createtime,'%Y-%m-%d')>=DATE_FORMAT(g.limit_begin_time,'%Y-%m-%d') and o.order_status !=7  and o.order_status !=3  and o.order_status !=10  ");
        sql.append(" and o.user_id=? and d.goods_id=? ");
        sql.append(" GROUP BY d.goods_id ");
        return generalDao.getBySql2(sql.toString(), userId, goodsId);
    }

    @Override
    public List<Map<String, Object>> loadGoodsOrderDetailLimitRuleByYearListMap(int userId, int goodsId, String year) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT d.goods_id,ifnull(SUM(d.goods_count),0) as goods_count from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id ");
        sql.append(" JOIN tb_goods g on g.goods_id=d.goods_id ");
        sql.append(" where 1=1 and DATE_FORMAT(o.createtime,'%Y-%m-%d')>=DATE_FORMAT(g.limit_begin_time,'%Y-%m-%d') and o.order_status !=7  and o.order_status !=3  and o.order_status !=10 ");
        sql.append(" and o.user_id=? and d.goods_id=? ");
        sql.append(" and DATE_FORMAT(o.createtime,'%Y')=? ");
        sql.append(" GROUP BY d.goods_id ");
        return generalDao.getBySql2(sql.toString(), userId, goodsId, year);
    }

    @Override
    public List<Map<String, Object>> loadGoodsOrderDetailLimitRuleByMonthListMap(int userId, int goodsId, String month) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT d.goods_id,ifnull(SUM(d.goods_count),0) as goods_count from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id ");
        sql.append(" JOIN tb_goods g on g.goods_id=d.goods_id ");
        sql.append(" where 1=1 and DATE_FORMAT(o.createtime,'%Y-%m-%d')>=DATE_FORMAT(g.limit_begin_time,'%Y-%m-%d') and o.order_status !=7 and o.order_status !=3  and o.order_status !=10  ");
        sql.append(" and o.user_id=? and d.goods_id=? ");
        sql.append(" and DATE_FORMAT(o.createtime,'%Y-%m')=? ");
        sql.append(" GROUP BY d.goods_id ");
        return generalDao.getBySql2(sql.toString(), userId, goodsId, month);
    }

    @Override
    public List<Map<String, Object>> loadGoodsOrderDetailLimitRuleByDateListMap(int userId, int goodsId, String date) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT d.goods_id,ifnull(SUM(d.goods_count),0) as goods_count from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id ");
        sql.append(" JOIN tb_goods g on g.goods_id=d.goods_id ");
        sql.append(" where 1=1 and DATE_FORMAT(o.createtime,'%Y-%m-%d')>=DATE_FORMAT(g.limit_begin_time,'%Y-%m-%d') and o.order_status !=7 and o.order_status !=3  and o.order_status !=10  ");
        sql.append(" and o.user_id=? and d.goods_id=? ");
        sql.append(" and DATE_FORMAT(o.createtime,'%Y-%m-%d')=? ");
        sql.append(" GROUP BY d.goods_id ");
        return generalDao.getBySql2(sql.toString(), userId, goodsId, date);
    }

    @Override
    public List<Map<String, Object>> loadTimeGoodsOrderDetailLimitRuleListMap(int userId, int timeGoodsId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT d.time_goods_id,ifnull(SUM(d.time_goods_id),0) as goods_count from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id ");
        sql.append(" JOIN tb_time_goods tg on tg.id=d.time_goods_id ");
        sql.append(" where 1=1 and DATE_FORMAT(o.createtime,'%Y-%m-%d')>=DATE_FORMAT(tg.limit_begin_time,'%Y-%m-%d') and o.order_status !=7 and o.order_status !=3  and o.order_status !=10  ");
        sql.append(" and o.user_id=? and d.time_goods_id=? ");
        sql.append(" GROUP BY d.time_goods_id ");
        return generalDao.getBySql2(sql.toString(), userId, timeGoodsId);
    }

    @Override
    public List<Map<String, Object>> loadTimeGoodsOrderDetailLimitRuleByYearListMap(int userId, int timeGoodsId, String year) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT d.time_goods_id,ifnull(SUM(d.time_goods_id),0) as goods_count from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id ");
        sql.append(" JOIN tb_time_goods tg on tg.id=d.time_goods_id ");
        sql.append(" where 1=1 and DATE_FORMAT(o.createtime,'%Y-%m-%d')>=DATE_FORMAT(tg.limit_begin_time,'%Y-%m-%d') and o.order_status !=7 and o.order_status !=3  and o.order_status !=10  ");
        sql.append(" and o.user_id=? and d.time_goods_id=? ");
        sql.append(" and DATE_FORMAT(o.createtime,'%Y')=? ");
        sql.append(" GROUP BY d.time_goods_id ");
        return generalDao.getBySql2(sql.toString(), userId, timeGoodsId, year);
    }

    @Override
    public List<Map<String, Object>> loadTimeGoodsOrderDetailLimitRuleByMonthListMap(int userId, int timeGoodsId, String month) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT d.time_goods_id,ifnull(SUM(d.time_goods_id),0) as goods_count from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id ");
        sql.append(" JOIN tb_time_goods tg on tg.id=d.time_goods_id ");
        sql.append(" where 1=1 and DATE_FORMAT(o.createtime,'%Y-%m-%d')>=DATE_FORMAT(tg.limit_begin_time,'%Y-%m-%d') and o.order_status !=7 and o.order_status !=3  and o.order_status !=10  ");
        sql.append(" and o.user_id=? and d.time_goods_id=? ");
        sql.append(" and DATE_FORMAT(o.createtime,'%Y-%m')=? ");
        sql.append(" GROUP BY d.time_goods_id ");
        return generalDao.getBySql2(sql.toString(), userId, timeGoodsId, month);
    }

    @Override
    public List<Map<String, Object>> loadTimeGoodsOrderDetailLimitRuleByDateListMap(int userId, int timeGoodsId, String date) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT d.time_goods_id,ifnull(SUM(d.time_goods_id),0) as goods_count from tb_order_detail d JOIN tb_order o on o.order_id=d.order_id ");
        sql.append(" JOIN tb_time_goods tg on tg.id=d.time_goods_id ");
        sql.append(" where 1=1 and DATE_FORMAT(o.createtime,'%Y-%m-%d')>=DATE_FORMAT(tg.limit_begin_time,'%Y-%m-%d') and o.order_status !=7 and o.order_status !=3  and o.order_status !=10  ");
        sql.append(" and o.user_id=? and d.time_goods_id=? ");
        sql.append(" and DATE_FORMAT(o.createtime,'%Y-%m-%d')=? ");
        sql.append(" GROUP BY d.time_goods_id ");
        return generalDao.getBySql2(sql.toString(), userId, timeGoodsId, date);
    }

    /**
     * 查询运费
     */
    @Override
    public TbDistributionAddr loadTbDistributionAddr(int id) {
        String hql = " from  TbDistributionAddr where id=? and status=? ";
        return generalDao.queryUniqueByHql(hql, id, 1);
    }

    public TbDistributionAddr loadTbDistributionAddr(int id, Integer status) {
        if (null == status) {
            String hql = " from  TbDistributionAddr where id=?  ";
            return generalDao.queryUniqueByHql(hql, id);
        } else {
            String hql = " from  TbDistributionAddr where id=? and status=? ";
            return generalDao.queryUniqueByHql(hql, id, status);
        }
    }

    /**
     * 查询卡券
     */
    @Override
    public TbTicket loadTbTicketById(int ticketId) {
        String hql = " from  TbTicket where tikcetId=?  ";
        return generalDao.queryUniqueByHql(hql, ticketId);
    }

    @Override
    public List<Map<String, Object>> loadEffeTicketListMap(int ticketId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT t.tikcet_id,t.tikcet_name,t.title, ");
        sql.append(" if(t.ticket_img is null or t.ticket_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',t.ticket_img)) as ticket_img , ");
        sql.append(" t.intro,t.shop_id,t.weight,t.classify_id,t.area_id,t.is_show,t.ticket_type,CONCAT(t.ticket_value,'') as ticket_value, ");
        sql.append(" DATE_FORMAT(t.start_time,'%Y-%m-%d') as start_time,DATE_FORMAT(t.end_time,'%Y-%m-%d') as end_time,t.valid_date,t.out_date, ");
        sql.append(" DATE_FORMAT(t.createtime,'%Y-%m-%d %T') as createtime,t.status,t.remark,t.need_know,t.ticket_num,t.partners_id, ");
        sql.append(" t.is_putaway,t.limit_num,t.limit_way,t.leve1_id,t.appoint_type,t.appoint_date,DATE_FORMAT(t.limit_begin_time,'%Y-%m-%d') as limit_begin_time ");
        sql.append(" FROM tb_ticket t ");
        sql.append(" JOIN tb_partners p on p.id=t.partners_id ");
        sql.append(" JOIN tb_shop s on s.shop_id=t.shop_id ");
        sql.append(" where 1=1 ");
        sql.append(" and t.tikcet_id=" + ticketId + " ");
        sql.append(" and p.`status`=1 and t.`status`=1 and s.`status`=1 and t.is_putaway=2 ");
        sql.append(" and DATE_FORMAT(NOW(),'%Y-%m-%d')>=DATE_FORMAT(t.start_time,'%Y-%m-%d') ");
        sql.append(" and (DATE_FORMAT(NOW(),'%Y-%m-%d')<=DATE_FORMAT(t.end_time,'%Y-%m-%d') or t.valid_date=1) ");
        sql.append(" ORDER BY t.weight desc,t.createtime desc ");
        return generalDao.getBySql2(sql.toString());
    }


    /**
     * 查询qr
     */
    @Override
    public TbTicketDownQr loadTbTicketDownQrInfoById(int qrId) {
        String hql = " from  TbTicketDownQr where id=?  ";
        return generalDao.queryUniqueByHql(hql, qrId);
    }

    /**
     * 查询限购
     *
     * @param userId
     * @param ticketId
     * @return
     */
    @Override
    public List<Map<String, Object>> loadTbTicketDownQr(int userId, int ticketId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT qr.user_id,qr.ticket_id,COUNT(qr.ticket_id)  as ticket_count ");
        sql.append(" FROM tb_ticket_down_qr qr JOIN tb_ticket t on t.tikcet_id=qr.ticket_id where qr.status=1 ");
        sql.append(" and  qr.user_id=? and qr.ticket_id=? and (qr.recode_state=1 or qr.recode_state=2) ");
        sql.append(" and DATE_FORMAT(qr.lastaltertime,'%Y-%m-%d')>=DATE_FORMAT(t.limit_begin_time,'%Y-%m-%d') ");
        sql.append("GROUP BY qr.user_id,qr.ticket_id ");
        return generalDao.getBySql2(sql.toString(), userId, ticketId);
    }

    @Override
    public List<Map<String, Object>> loadTbTicketDownQrByDay(int userId, int ticketId, String day) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT user_id,ticket_id,COUNT(ticket_id)  as ticket_count ");
        sql.append("from tb_ticket_down_qr where `status`=1 ");
        sql.append("and  user_id=? and ticket_id=? and (recode_state=1 or recode_state=2) ");
        sql.append(" and DATE_FORMAT(qr.lastaltertime,'%Y-%m-%d')>=DATE_FORMAT(t.limit_begin_time,'%Y-%m-%d') ");
        sql.append("and DATE_FORMAT(lastaltertime,'%Y-%m-%d')=? ");
        sql.append("GROUP BY user_id,ticket_id ");
        return generalDao.getBySql2(sql.toString(), userId, ticketId, day);
    }

    @Override
    public List<Map<String, Object>> loadTbTicketDownQrByMonth(int userId, int ticketId, String month) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT user_id,ticket_id,COUNT(ticket_id)  as ticket_count ");
        sql.append("from tb_ticket_down_qr where `status`=1 ");
        sql.append("and  user_id=? and ticket_id=? and (recode_state=1 or recode_state=2) ");
        sql.append(" and DATE_FORMAT(qr.lastaltertime,'%Y-%m-%d')>=DATE_FORMAT(t.limit_begin_time,'%Y-%m-%d') ");
        sql.append("and DATE_FORMAT(lastaltertime,'%Y-%m')=? ");
        sql.append("GROUP BY user_id,ticket_id ");
        return generalDao.getBySql2(sql.toString(), userId, ticketId, month);
    }

    @Override
    public List<Map<String, Object>> loadTbTicketDownQrByYear(int userId, int ticketId, String year) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT user_id,ticket_id,COUNT(ticket_id)  as ticket_count ");
        sql.append("from tb_ticket_down_qr where `status`=1 ");
        sql.append("and  user_id=? and ticket_id=? and (recode_state=1 or recode_state=2) ");
        sql.append(" and DATE_FORMAT(qr.lastaltertime,'%Y-%m-%d')>=DATE_FORMAT(t.limit_begin_time,'%Y-%m-%d') ");
        sql.append("and DATE_FORMAT(lastaltertime,'%Y')=? ");
        sql.append("GROUP BY user_id,ticket_id ");
        return generalDao.getBySql2(sql.toString(), userId, ticketId, year);
    }

    /**
     * 检验qr是否存在
     */
    @Override
    public boolean isCodeExist(String changeCode, String checkCode) {
        try {
            String sql = " SELECT id from tb_ticket_down_qr where change_code='" + changeCode + "' and check_code='" + checkCode + "' and recode_state!=2 ";
            List<Map<String, Object>> list = generalDao.getBySql2(sql);
            if (null != list && list.size() == 0) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 用户注册
     *
     * @param userMobile
     * @return
     */
    @Override
    public TbUserInfo loadTbUserInfoByPhone(String userMobile) {
        String hql = " from TbUserInfo where userMobile=?  and  userState!=3 ";
        return generalDao.queryUniqueByHql(hql, userMobile);
    }

    @Override
    public TbUserInfo loadTbUserInfoById(int userId) {
        String hql = " from TbUserInfo where userId=?  ";
        return generalDao.queryUniqueByHql(hql, userId);
    }

    @Override
    public TbUserInfo loadTbUserInfoByLoginName(String loginName) {
        String hql = " from TbUserInfo where loginName=? and  userState!=3 ";
        return generalDao.queryUniqueByHql(hql, loginName);
    }

    /**
     * 查询main页菜单
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> loadMainMenu(String limit) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT id,menu_name,menu_sort,menu_url,`status`,leve1_ids,category_ids,DATE_FORMAT(createtime,'%Y-%m-%d %T') as createtime, ");
        sql.append(" if(menu_img is null or menu_img='',CONCAT('" + RsConstants.PHONE_URL + "','null.jpg'),CONCAT('" + RsConstants.PHONE_URL + "',menu_img)) as menu_img  ");
        sql.append(" from tb_main_menu where `status`=1 ORDER BY menu_sort desc ");
        sql.append("  LIMIT " + limit + " ");
        return generalDao.getBySql2(sql.toString());
    }

    /**
     * 查询用户密保问题3条
     *
     * @return
     */
    public List<Map<String, Object>> queryUserQuestionListMap(int userId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT `code`,qustion,answer from tb_passwd_reset_question where status=1 and user_id=" + userId + " order by id desc limit 3  ");
        return generalDao.getBySql2(sql.toString());
    }

    /**
     * 查询全球地址
     *
     * @param parentId
     * @return
     */
    public List<Map<String, Object>> queryGlobalAddrListMap(String parentId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT region_id,region_code,region_name,region_shortname,parent_id,region_level,region_name_en,region_shortname_en,region_type,freight from tb_region where `status`=1 ");
        if (!StringUtils.nonEmptyString(parentId)) {
            sql.append(" and region_level=1 ");
        } else {
            sql.append(" and parent_id='" + parentId + "' ");
        }
        sql.append(" ORDER BY region_order desc,region_code ");
        return generalDao.getBySql2(sql.toString());
    }

    public List<Map<String, Object>> queryGlobalAddrListMapMark(String parentId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT region_code,region_name from tb_region where `status`=1 ");
        if (!StringUtils.nonEmptyString(parentId)) {
            sql.append(" and region_level=1 ");
        } else {
            sql.append(" and parent_id='" + parentId + "' ");
        }
        sql.append(" ORDER BY region_order desc,region_code ");
        return generalDao.getBySql2(sql.toString());
    }

    /**
     * 查询用户收货地址
     *
     * @param userId
     * @return
     */
    public List<Map<String, Object>> queryUserAddrListMap(int userId, Integer isDefault, Integer addrType) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT id,area_ids,area_name,user_id,user_phone,addr_detail,is_default,addr_name,addr_type,user_name ");
        sql.append(" from tb_user_addr where `status`=1 and user_id=" + userId + "  ");
        if (null != isDefault) {
            sql.append(" and is_default=" + isDefault + " ");
        }
        if (null != addrType) {
            sql.append(" and addr_type=" + addrType + " ");
        }
        sql.append(" ORDER BY is_default desc, createtime desc  ");
        return generalDao.getBySql2(sql.toString());
    }

    public List<Map<String, Object>> queryUserAddrListMapByRegionCodes(String regionCodes) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT region_id,region_code,freight from tb_region where `status`=1 and region_code in ('" + regionCodes + "') ORDER BY region_level  desc ");
        return generalDao.getBySql2(sql.toString());
    }


    @Override
    public List<TbUserAddr> loadTbUserInfoList(int userId) {
        String hql = "from TbUserAddr where userId=? and status=? order by createtime desc ";
        return generalDao.queryListByHql(hql, userId, 1);
    }

    public TbUserAddr loadTbUserAddr(int userId, int userAddrId, Integer status) {
        if (null == status) {
            String hql = " from TbUserAddr where id=?  and userId=? ";
            return generalDao.queryUniqueByHql(hql, userAddrId, userId);
        } else {
            String hql = " from TbUserAddr where id=?  and userId=? and status=? ";
            return generalDao.queryUniqueByHql(hql, userAddrId, userId, status);
        }
    }

    public TbDistributionAddr loadTbDistributionAddrById(int id) {
        String hql = " from TbDistributionAddr where id=?  ";
        return generalDao.queryUniqueByHql(hql, id);
    }

    public TbRegion loadTbRegionByRegionCode(String regionCode) {
        String hql = " from TbRegion where regionCode=?  ";
        return generalDao.queryUniqueByHql(hql, regionCode);
    }

    public List<TbDistributionAddr> loadTbDistributionAddrList() {
        String hql = "from TbDistributionAddr  ";
        return generalDao.queryListByHql(hql);
    }

    public List<TbGoods> loadTbGoodsList() {
        String hql = "from TbGoods ";
        return generalDao.queryListByHql(hql);
    }

    public List<TbGoodsSku> loadTbGoodsSku() {
        String hql = "from TbGoodsSku ";
        return generalDao.queryListByHql(hql);
    }

    public TbParam loadTbParamInfo(int paramId) {
        String hql = "from TbParam where paramId=? ";
        return generalDao.queryUniqueByHql(hql, paramId);
    }

    @Override
    public List<TbShop> loadTbShopList() {
        String hql = "from TbShop ";
        return generalDao.queryListByHql(hql);
    }

    @Override
    public List<TbPartners> loadTbPartnersList() {
        String hql = "from TbPartners ";
        return generalDao.queryListByHql(hql);
    }

    public TbShop loadTbshopInfo(int shopId) {
        String hql = "from TbShop where shopId=? ";
        return generalDao.queryUniqueByHql(hql, shopId);
    }

    public TbPartners loadTbPartnersInfo(int partnersId) {
        String hql = "from TbPartners where id=? ";
        return generalDao.queryUniqueByHql(hql, partnersId);
    }

    @Override
    public List<Map<String, Object>> queryGoodsSaleCount(String goodsIds) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT SUM(d.goods_count) as sale_count,d.goods_id from tb_order_detail d JOIN tb_order o on  o.order_id=d.order_id where ");
        sql.append(" d.goods_id in (").append(goodsIds).append(") ");
        sql.append(" and o.order_status !=7 and o.order_status !=10 and o.order_status !=3 ");
        sql.append(" GROUP BY d.goods_id ");
        return generalDao.getBySql2(sql.toString());
    }
}
