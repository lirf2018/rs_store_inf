package com.yufan.task.service;

import com.alibaba.fastjson.JSONObject;
import com.yufan.bean.ReceiveJsonBean;
import com.yufan.bean.ResultCode;
import com.yufan.pojo.TbTicketDownQr;
import com.yufan.service.ResultOut;
import com.yufan.task.SaveInfoService;
import com.yufan.task.dao.FindInfoDao;
import com.yufan.util.DatetimeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.yufan.common.util.ResponeUtil.packagMsg;

/**
 * 创建人: lirf
 * 创建时间:  2017-11-24 18:23
 * 功能介绍: 保存二维码
 */
@Service("create_ticket_qr")
public class CreateTicketQR implements ResultOut {

    private Logger LOG = Logger.getLogger(CreateTicketQR.class);

    @Autowired
    private SaveInfoService saveInfoService;

    @Autowired
    private FindInfoDao findInfoDao;

    @Override
    public synchronized String getResult(ReceiveJsonBean receiveJsonBean) {
        JSONObject dataJson = new JSONObject();
        JSONObject bean = receiveJsonBean.getData();
        try {
            Integer userId = bean.getInteger("user_id");//
            Integer ticketId = bean.getInteger("ticket_id");//

            if (userId == null || ticketId == null) {
                return packagMsg(ResultCode.NEED_PARAM_ERROR.getResp_code(), dataJson);
            }
            //输出
            dataJson.put("result", false);
            dataJson.put("msg", "领取失败,请稍后重试");
            dataJson.put("ticket_id", ticketId);
//            dataJson.put("qr_id",)//成功才会有qr_id


            //查询有效卡券
            List<Map<String, Object>> ticketListMap = findInfoDao.loadEffeTicketListMap(ticketId);
            if (null != ticketListMap && ticketListMap.size() == 1) {
                LOG.info("-------->有效卡券存在");
                int shopId = Integer.parseInt(String.valueOf(ticketListMap.get(0).get("shop_id")));//
                int ticketType = Integer.parseInt(String.valueOf(ticketListMap.get(0).get("ticket_type")));//卡券类型 1:代金券2优惠券
                BigDecimal ticketValue = new BigDecimal(String.valueOf(ticketListMap.get(0).get("ticket_value")));//卡券类型值(只有当为代金券时该字段必填)

                int ticketNum = Integer.parseInt(String.valueOf(ticketListMap.get(0).get("ticket_num")));//卡券数量
                //
                if (ticketNum < 1) {
                    LOG.info("-------->卡券库存不足");
                    return packagMsg(ResultCode.STORE_EMPTY.getResp_code(), dataJson);
                }

                int limitWay = Integer.parseInt(String.valueOf(ticketListMap.get(0).get("limit_way")));//限购方式: 1.每天一次2每月一次3.每年一次4不限购5只允许购买一次
                int limitNum = Integer.parseInt(String.valueOf(ticketListMap.get(0).get("limit_num")));//卡券限购数量

                if (4 != limitWay) {
                    LOG.info("------>开启限购检验");
                    List<Map<String, Object>> listQr = null;
                    if (limitWay == 1) {
                        String nowDate = DatetimeUtil.getNow("yyyy-MM-dd");
                        listQr = findInfoDao.loadTbTicketDownQrByDay(userId, ticketId, nowDate);
                        if (listQr.size() > 0) {
                            int hasGetCount = Integer.parseInt(String.valueOf(listQr.get(0).get("ticket_count")));
                            if (hasGetCount >= limitNum) {
                                LOG.info("------>领取已达上限limitWay=1--每天一次");
                                dataJson.put("msg", "领取失败,请查看卡券领取限制规则");
                                return packagMsg(ResultCode.FAIL_GET_TICKET.getResp_code(), dataJson);
                            }
                        }
                    } else if (limitWay == 2) {
                        String nowDate = DatetimeUtil.getNow("yyyy-MM");
                        listQr = findInfoDao.loadTbTicketDownQrByMonth(userId, ticketId, nowDate);
                        if (listQr.size() > 0) {
                            int hasGetCount = Integer.parseInt(String.valueOf(listQr.get(0).get("ticket_count")));
                            if (hasGetCount >= limitNum) {
                                LOG.info("------>领取已达上限limitWay=2--每月一次");
                                dataJson.put("msg", "领取失败,请查看卡券领取限制规则");
                                return packagMsg(ResultCode.FAIL_GET_TICKET.getResp_code(), dataJson);
                            }
                        }
                    } else if (limitWay == 3) {
                        String nowDate = DatetimeUtil.getNow("yyyy");
                        listQr = findInfoDao.loadTbTicketDownQrByYear(userId, ticketId, nowDate);
                        if (listQr.size() > 0) {
                            int hasGetCount = Integer.parseInt(String.valueOf(listQr.get(0).get("ticket_count")));
                            if (hasGetCount >= limitNum) {
                                LOG.info("------>领取已达上限limitWay=3--每年一次");
                                dataJson.put("msg", "领取失败,请查看卡券领取限制规则");
                                return packagMsg(ResultCode.FAIL_GET_TICKET.getResp_code(), dataJson);
                            }
                        }
                    } else if (limitWay == 5) {
                        listQr = findInfoDao.loadTbTicketDownQr(userId, ticketId);
                        if (listQr.size() > 0) {
                            int hasGetCount = Integer.parseInt(String.valueOf(listQr.get(0).get("ticket_count")));
                            if (hasGetCount >= limitNum) {
                                LOG.info("------>领取已达上限limitWay=5--只允许购买一次");
                                dataJson.put("msg", "领取失败,请查看卡券领取限制规则");
                                return packagMsg(ResultCode.FAIL_GET_TICKET.getResp_code(), dataJson);
                            }
                        }
                    }
                }
                //根据规则计算生成的卡券过期时间
                int appointType = Integer.parseInt(String.valueOf(ticketListMap.get(0).get("appoint_type")));//是否指定使用方式：0按兑换过期天计算过期时间  1指定过期时间  2指定使用时间
                String appointDate = String.valueOf(ticketListMap.get(0).get("appoint_date"));//指定时间
                String dateFormat = "yyyy-MM-dd";
                String qrBenginDate = DatetimeUtil.getNow(dateFormat);
                String qrPassDate = "";
                if (appointType == 1) {
                    //1指定过期时间
                    qrPassDate = appointDate;
                } else if (appointType == 2) {
                    //2指定使用时间
                    qrBenginDate = appointDate;
                    qrPassDate = appointDate;
                } else {
                    int outDate = Integer.parseInt(String.valueOf(ticketListMap.get(0).get("out_date")));//有效过期天
                    String nowDate = DatetimeUtil.getNow(dateFormat);
                    qrPassDate = DatetimeUtil.getDateLastOrNext(dateFormat, nowDate, outDate);
                }
                String path = receiveJsonBean.getRequest().getSession().getServletContext().getRealPath("/");
                String p[] = path.split("webapps");
                //图片根文件夹
                String qrImgRoot = p[0] + "webapps/image/";
                LOG.info("----------QR有效期qrBenginDate=" + qrBenginDate + " qrPassDate=" + qrPassDate + "  qrImgRoot=" + qrImgRoot);
                return generateQR(dataJson, ticketId, userId, qrBenginDate, qrPassDate, ticketType, ticketValue, shopId, qrImgRoot);
            }
            LOG.info("-------->有效卡券不存在");
            return packagMsg(ResultCode.FAIL.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("异常", e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }

    /**
     * 生成二维码
     *
     * @return
     */
    private String generateQR(JSONObject dataJson, int ticketId, int userId, String sTime, String eTime, int ticketType, BigDecimal ticketValue, int shopId, String qrImgRoot) {
        try {
            TbTicketDownQr downQr = new TbTicketDownQr();
            downQr.setTicketId(ticketId);
            downQr.setUserId(userId);
            downQr.setStartTime(DatetimeUtil.convertObjectToDate(sTime));
            downQr.setEndTime(DatetimeUtil.convertObjectToDate(eTime));
            downQr.setRecodeState(3);
            downQr.setCreatetime(new Date());
            downQr.setStatus(1);
            downQr.setTicketType(ticketType);
            downQr.setTicketValue(ticketValue.doubleValue());
            downQr.setShopId(shopId);

            //生成检验码和验证码
            String changeCode = "";
            String checkCode = "";
            //循环20次,如果10次还生成不了唯一的码,那就是出现问题了
            for (int i = 0; i < 10; i++) {
                checkCode = generateCode6();
                changeCode = generateCode8();
                //检验是否存在数据库中
                boolean isExist = findInfoDao.isCodeExist(changeCode, checkCode);
                if (!isExist) {
                    break;
                }
                if (i == 9) {
                    LOG.info("-------->生成检验码和验证码异常");
                    return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
                }
            }
            downQr.setChangeCode(changeCode);
            downQr.setCheckCode(checkCode);

            int id = saveInfoService.saveGenerateQR(downQr, qrImgRoot);
            LOG.info("-------->新增保存二维码=" + id);
            dataJson.put("qr_id", id);
            dataJson.put("msg", "成功");
            dataJson.put("result", true);
            return packagMsg(ResultCode.OK.getResp_code(), dataJson);
        } catch (Exception e) {
            LOG.info("generateQR" + e);
        }
        return packagMsg(ResultCode.FAIL.getResp_code(), new JSONObject());
    }

    /**
     * 生成验证码 4位
     *
     * @return
     */
    private String generateCode6() {
        String validCode = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        return validCode;
    }

    /**
     * 生成兑换码 8位
     *
     * @return
     */
    private String generateCode8() {
        String validCode = String.valueOf((int) ((Math.random() * 9 + 1) * 10000000));
        return validCode;
    }

    public static void main(String[] args) {
        String validCode = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        System.out.println(validCode);
    }

}
