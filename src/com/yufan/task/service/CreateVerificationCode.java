package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.lang.StringUtils;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbUserInfo;
import com.yufan.pojo.TbUserSns;
import com.yufan.pojo.TbVerification;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.DatetimeUtil;
import com.yufan.util.sms.aliyun.AliyunSmsUtil;
import com.yufan.util.sms.netease.NeteaseUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-24 18:23
 * 功能介绍: 生成验证码
 */
@Service("create_verification")
public class CreateVerificationCode implements ResultOut {

    private Logger LOG = Logger.getLogger(CreateVerificationCode.class);

    @Autowired
    private SaveInfoService saveInfoService;

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer validType = bean.getInteger("valid_type");//验证码类型:1手机绑定2修改密码3重置密码4手机解绑5手机注册
            String validParam = bean.getString("valid_param");//验证标识参数 如：手机号,邮箱
            String validDesc = bean.getString("valid_desc");//验证类型说明
            TbVerification verification = new TbVerification();
            verification.setValidType(validType);
            verification.setValidParam(validParam);
            verification.setValidDesc(validDesc);

            if (!checkCode(bean)) {
                LOG.info("-------->参数解析失败或者参数检验失败");
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            String mobile = validParam.trim();
            //
            if (validType.intValue() == 5 || validType.intValue() == 1) {
                //注册(检验手机号码是否已注册绑定)
                TbUserInfo userInfo = findInfoDao.loadTbUserInfoByPhone(mobile);
                if (null != userInfo) {
                    LOG.info("------->手机是已经被注册绑定");
                    return packagMsg(ResultCode.PHONE_HAS_USED.getResp_code(), dataJson);
                }
            } else if (validType.intValue() == 3 || validType.intValue() == 4) {
                //重置密码（查询手机是否存在）
                TbUserInfo userInfo = findInfoDao.loadTbUserInfoByPhone(mobile);
                if (null == userInfo) {
                    LOG.info("------->手机绑定,不存在");
                    return packagMsg(ResultCode.FAIL_USER_INVALIDATE.getResp_code(), dataJson);
                }
            } else {
                LOG.info("---->验证码类型未开发");
                return packagMsg(ResultCode.BUSINESS_NOT_ERROR.getResp_code(), dataJson);
            }

            String now = DatetimeUtil.getNow("yyyy-MM-dd");
            String nowTime = DatetimeUtil.getNow();//yyyy-MM-dd HH:mm:ss
            //查询手机当天发送成功短信的记录数(每个手机号每天只能成功发送3条)
            List<Map<String, Object>> sucessSendList = findInfoDao.loadTbVerificationListMap(now, validParam.trim());
            if (sucessSendList.size() > 2) {
                //检验是否存在有效的发送成功且没有使用的短信
                for (int i = 0; i < sucessSendList.size(); i++) {
                    int sucessSendListValidType = Integer.parseInt(String.valueOf(sucessSendList.get(i).get("valid_type")));
                    int status = Integer.parseInt(String.valueOf(sucessSendList.get(i).get("status")));
                    int id = Integer.parseInt(String.valueOf(sucessSendList.get(i).get("id")));
                    String passTime = String.valueOf(sucessSendList.get(i).get("pass_time"));
                    if (sucessSendListValidType == validType.intValue() && status == 1 && DatetimeUtil.compareDateTime(passTime, nowTime) > -1) {
                        //同一类型的
                        LOG.info("------>存在在效的验证码send code  id=" + id);
                        return packagMsg(ResultCode.OK.getResp_code(), dataJson);
                    }
                }
                LOG.info("--------->短信发送达上限");
                return packagMsg(ResultCode.ERROR_SMS.getResp_code(), dataJson);
            }
            //查询是否存在有效的验证吗
            List<Map<String, Object>> list = findInfoDao.loadTbVerificationListMap(validParam, validType);
            if (null == list) {
                LOG.info("-------->查询是否存在有效的验证码异常");
                return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
            }
            if (list.size() > 0) {
                LOG.info("-------->存在有效的验证码");
                //验证码类型:
                String passTime = String.valueOf(list.get(0).get("pass_time"));
                //判断是否过期
                int comp = DatetimeUtil.compareDateTime(passTime, nowTime);
                if (comp > -1) {
                    //验证码有效
                    return packagMsg(ResultCode.OK.getResp_code(), dataJson);
                }
            }

            String validCode = String.valueOf((int) ((Math.random() * 9 + 1) * 1000));
            LOG.info("--------->手机号-->" + validParam + " 生成的验证码-->" + validCode);
            verification.setValidCode(validCode);
            verification.setStatus(1);//状态0无效1有效2已使用
            verification.setPassTime(new Timestamp(DatetimeUtil.addMinutes(new Date(), 30).getTime()));//30分钟有效
            verification.setCreatetime(new Timestamp(new Date().getTime()));
            verification.setSendStatus(0);//发送情况:0未知1发送失败2发送成功
            int id = saveInfoService.saveEntity(verification);
            LOG.info("-------->新增保存验证码=" + id);
            JSONObject result = new JSONObject();
            if (isMobileNO(validParam.trim())) {
                //调用接口发送验证码 调用云片平台发送短信
//                JSONObject inObj = new JSONObject();
//                inObj.put("mobile", verification.getValidParam().trim());
//                inObj.put("authCode", validCode);
//                result = NeteaseUtils.sendcode(inObj);//调用云片平台发送短信

                //阿里短信发送
                JSONObject c = new JSONObject();
                c.put("code", validCode);
                String contends = c.toString();
                String smsNumber = "rs" + id;
                String sign = "李融凡";//签名
                String smsModel = "SMS_140736998";//模板标识
                result = AliyunSmsUtil.getInstence().sendPhoneSms(validParam, contends, smsNumber, sign, smsModel);
                if (null != result) {
                    verification.setSendMsg(result.toString());
                }
                if (null != result && 1 == result.getIntValue("result")) {
                    //更新验证码发送状态
                    verification.setSendStatus(2);//发送情况:0未知1发送失败2发送成功
                    saveInfoService.saveUpdateEntity(verification);
                    return packagMsg(ResultCode.OK.getResp_code(), dataJson);
                }
            }
            verification.setSendStatus(1);//发送情况:0未知1发送失败2发送成功
            saveInfoService.saveUpdateEntity(verification);
            LOG.info("--------->发送失败");
            return packagMsg(ResultCode.SMS_SEND_FAIL.getResp_code(), dataJson);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }

    /**
     * 检验入参
     *
     * @return
     */
    public boolean checkCode(JSONObject bean) {
        try {
            Integer validType = bean.getInteger("valid_type");//
            String validParam = bean.getString("valid_param");//验证标识参数 如：手机号,邮箱
            String validDesc = bean.getString("valid_desc");//验证类型说明
            if (null == validType || !StringUtils.nonEmptyString(validParam) || !StringUtils.nonEmptyString(validDesc)) {
                LOG.info("----->参数不全");
                return false;
            }
            return true;
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return false;
    }

    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
