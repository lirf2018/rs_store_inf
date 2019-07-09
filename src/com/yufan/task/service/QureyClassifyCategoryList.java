package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.common.util.RsConstants;
import com.yufan.pojo.TbCatogryLevel1;
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
 * 创建时间:  2017-11-15 10:44
 * 功能介绍: 查询一级分类目列表(qurey_classify_category)
 */
@Service("qurey_classify_category")
public class QureyClassifyCategoryList implements ResultOut {

    private Logger LOG = Logger.getLogger(QureyClassifyCategoryList.class);

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            //输出
            List<Map<String, Object>> out = new ArrayList<Map<String, Object>>();
            String classifyCode = bean.getString("classify_code");
            List<TbCatogryLevel1> catogryLevel1List = null;
            //查询一级分类
            if (null != classifyCode && !"".equals(classifyCode.trim())) {
                catogryLevel1List = findInfoDao.loadTbCatogryLevel1List(classifyCode.trim());
            } else {
                catogryLevel1List = findInfoDao.loadTbCatogryLevel1List();
            }
            if (null != catogryLevel1List && catogryLevel1List.size() > 0) {
                String path = RsConstants.PHONE_URL;
                //查询类目
                List<Map<String, Object>> catogreyList = findInfoDao.loadTbCategoryListMap();
                for (int i = 0; i < catogryLevel1List.size(); i++) {
                    TbCatogryLevel1 level1 = catogryLevel1List.get(i);
                    Map<String, Object> leve1Map = new HashMap<String, Object>();
                    int leve1Id = level1.getLevelId();
                    leve1Map.put("level_id", leve1Id);
                    leve1Map.put("level_code", level1.getLevelCode());
                    leve1Map.put("level_name", level1.getLevelName());
                    leve1Map.put("level_img", path + level1.getLevelImg());
                    List<Map<String, Object>> caList = new ArrayList<Map<String, Object>>();
                    for (int j = 0; j < catogreyList.size(); j++) {
                        Map<String, Object> map = catogreyList.get(j);
                        int caLeve1Id = Integer.parseInt(String.valueOf(map.get("level_id") == null || "".equals(map.get("level_id").toString()) ? "0" : map.get("level_id")));
                        if (caLeve1Id == leve1Id) {
                            map.remove("level_id");
                            caList.add(map);
                        }
                    }
                    leve1Map.put("category_list", caList);
                    out.add(leve1Map);
                }
            }
            dataJson.put("list_leve1", out);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }
}
