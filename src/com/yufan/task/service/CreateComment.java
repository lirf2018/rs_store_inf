package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbComment;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.bean.CommentBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-27 10:30
 * 功能介绍: 创建评论
 */
@Service("create_comment")
public class CreateComment implements ResultOut {
    private Logger LOG = Logger.getLogger(CreateComment.class);

    @Autowired
    private SaveInfoService saveInfoService;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            CommentBean commentBean = JSONObject.toJavaObject(bean, CommentBean.class);
            if (!checkData(commentBean)) {
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }

            TbComment comment = new TbComment();
            comment.setUserId(commentBean.getUserId());
            comment.setPhone(commentBean.getPhone());
            comment.setGoodsId(commentBean.getGoodsId());
            comment.setContent(commentBean.getContent());
            comment.setScore(commentBean.getScore());
            comment.setImg1(commentBean.getImg1());
            comment.setImg2(commentBean.getImg2());
            comment.setImg3(commentBean.getImg3());
            comment.setImg4(commentBean.getImg4());
            comment.setImg5(commentBean.getImg5());
            comment.setImg6(commentBean.getImg6());

            comment.setIsShow(1);//显示状态0不显示1显示
            comment.setCreatetime(new Date());
            comment.setStatus(2);

            int id = saveInfoService.saveEntity(comment);
            LOG.info("-------->创建评论id=" + id);
            dataJson.put("id", id);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
    }

    private boolean checkData(CommentBean commentBean) {
        if (null == commentBean || commentBean.getUserId() == null || commentBean.getGoodsId() == null || !StringUtils.nonEmptyString(commentBean.getContent())
                || !StringUtils.nonEmptyString(commentBean.getPhone()) || null == commentBean.getScore()) {
            LOG.info("---------->缺少必要参数");
            return false;
        }
        return true;
    }
}
