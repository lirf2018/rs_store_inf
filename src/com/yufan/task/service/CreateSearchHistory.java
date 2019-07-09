package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbJifen;
import com.yufan.pojo.TbSearchHistory;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.dao.SaveInfoDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-24 18:23
 * 功能介绍: 保存搜索历史
 */
@Service("create_search_history")
public class CreateSearchHistory implements ResultOut {

    private Logger LOG = Logger.getLogger(CreateSearchHistory.class);

    @Autowired
    private SaveInfoService saveInfoService;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer type = bean.getInteger("type");//搜索类型:1商品(必需)
            String typeWord = bean.getString("type_word");//搜索关键词(必需
            Integer userId = bean.getInteger("user_id");
            if (type == null || userId == null || !StringUtils.nonEmptyString(typeWord)) {
                LOG.info("-------->缺少必要参数");
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            TbSearchHistory searchHistory = new TbSearchHistory();
            searchHistory.setUserId(userId);
            searchHistory.setType(type);
            searchHistory.setTypeWord(typeWord.trim());

            searchHistory.setStatus(1);
            searchHistory.setCreatetime(new Date());
            int id = saveInfoService.saveEntity(searchHistory);
            LOG.info("-------->新增保存搜索历史=" + id);
            dataJson.put("id", id);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }

}
