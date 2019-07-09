package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.common.util.RsConstants;
import com.yufan.pojo.TbCategory;
import com.yufan.pojo.TbItemprops;
import com.yufan.service.ResultOut;
import com.yufan.task.dao.FindInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-15 15:21
 * 功能介绍: 查询类目属性列表(qurey_category_itemprops)
 */
@Service("qurey_category_itemprops")
public class QureyCategoryItempropsList implements ResultOut {

    private Logger LOG = Logger.getLogger(QureyCategoryItempropsList.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            //输出
            List<Map<String, Object>> out = new ArrayList<Map<String, Object>>();
            String categoryCode = bean.getString("category_code");
            //查询类目列表
            List<TbCategory> categoryList = null;

            if (null != categoryCode && !"".equals(categoryCode.trim())) {
                categoryList = findInfoDao.loadTbCategoryList(categoryCode.trim());
            } else {
                categoryList = findInfoDao.loadTbCategoryList();
            }
            if (null != categoryList && categoryList.size() > 0) {
                String path = RsConstants.PHONE_URL;
                //查询属性列表
                List<TbItemprops> itempropsList = findInfoDao.loadTbItempropsList();
                for (int i = 0; i < categoryList.size(); i++) {
                    TbCategory category = categoryList.get(i);
                    Integer categoryId = category.getCategoryId();
                    String categoryName = category.getCategoryName();
                    String outeId = category.getOuteId();
                    String categoryImg = category.getCategoryImg();
                    String categoryCode_ = category.getCategoryCode();
                    Map<String, Object> categoryObj = new HashMap<String, Object>();
                    categoryObj.put("category_id", categoryId);
                    categoryObj.put("category_name", categoryName);
                    categoryObj.put("oute_id", outeId);
                    categoryObj.put("category_img", path + categoryImg);
                    categoryObj.put("category_code", categoryCode_);
                    List<Map<String, Object>> itemOutList = new ArrayList<Map<String, Object>>();
                    for (int j = 0; j < itempropsList.size(); j++) {
                        TbItemprops itemprops = itempropsList.get(j);
                        Integer propId = itemprops.getPropId();
                        String propName = itemprops.getPropName();
                        int categoryId_ = itemprops.getCategoryId();
                        String outeId_ = itemprops.getOuteId();
                        String propImg = itemprops.getPropImg();
                        String propCode = itemprops.getPropCode();
                        if (categoryId_ == categoryId) {
                            Map<String, Object> itempropsMap = new HashMap<String, Object>();
                            itempropsMap.put("prop_id", propId);
                            itempropsMap.put("prop_name", propName);
                            itempropsMap.put("oute_id", outeId_);
                            itempropsMap.put("prop_img", propImg);
                            itempropsMap.put("prop_code", propCode);
                            itemOutList.add(itempropsMap);
                        }
                    }
                    categoryObj.put("itemprops_list", itemOutList);
                    out.add(categoryObj);
                }
            }
            dataJson.put("category_list", out);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
