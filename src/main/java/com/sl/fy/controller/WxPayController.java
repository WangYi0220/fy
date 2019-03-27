package com.sl.fy.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.sl.fy.pojo.Order;
import com.sl.fy.service.OrderService;
import com.sl.fy.service.StudentService;
import io.swagger.annotations.Api;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

@RestController
@Api(tags = "微信支付")
public class WxPayController {

    @Autowired
    private WxPayService wxPayService;
    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private OrderService orderService;

    private final String NOTIFY_URL = ""; // 微信回调地址，支付成功后微信把支付信息发送到这个地址

    @PostMapping("/createOrder")
    public String createOrder(@RequestBody JSONObject requestObject, HttpServletRequest req) throws Exception {

        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
        request.setBody("赋羽青年-成为会员");
        request.setOutTradeNo(UUID.randomUUID().toString().replaceAll("-", "")); // 数据库订单号
        request.setNotifyUrl(NOTIFY_URL); // 微信通知地址
        request.setTotalFee(1); // 付款金额 以分为单位 int类型 如付款1块钱 填100
        request.setOpenid(requestObject.getString("openId")); // 付款用户openId
        request.setTradeType("JSAPI");

        String ip = getIP(req);
        if (ip.contains(",")) {
            ip = ip.replaceAll(" ", "");
            ip = ip.split(",")[0];
        }

        request.setSpbillCreateIp(ip);
        request.setProductId("testProductId"); // 商品ID

        WxPayUnifiedOrderResult result = wxPayService.unifiedOrder(request);

        result.setXmlString(null);
        result.setMchId(null);

        // 生成签名
        Map<String, String> map = new TreeMap<>();
        String timeStamp = String.valueOf(new Date().getTime());
        String nonceStr = WXPayUtil.generateNonceStr();
        map.put("appId", result.getAppid());
        map.put("timeStamp", timeStamp);
        map.put("nonceStr", nonceStr);
        map.put("package", "prepay_id=" + result.getPrepayId());
        map.put("signType", "MD5");
        String signature = WXPayUtil.generateSignature(map, wxPayService.getConfig().getMchKey(), WXPayConstants.SignType.MD5);

        // 返回数据 网页唤起微信支付需要用到
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("signature", signature);
        jsonObject.put("package", map.get("package"));
        jsonObject.put("timeStamp", timeStamp);
        jsonObject.put("nonceStr", nonceStr);
        jsonObject.put("appId", result.getAppid());

        return jsonObject.toJSONString();
    }


    @PostMapping("notify")
    public String notify(@RequestBody String xmlData) {
        try {
            WxPayOrderNotifyResult notifyResult = wxPayService.parseOrderNotifyResult(xmlData);
            String transactionId = notifyResult.getTransactionId();
            String openId = notifyResult.getOpenid(); // 付款用户openId
            Integer totalFee = notifyResult.getTotalFee(); // 付款金额
            Order order = new Order().setOutTradeNo(notifyResult.getOutTradeNo())
                    .setTransactionId(transactionId)
                    .setOpenID(openId)
                    .setTotalFee(totalFee)
                    .setCreateTime(new Date());
            orderService.addOrder(order);//添加订单
            try {
                WxMpUser mpUser = wxMpService.getUserService().userInfo(openId);
                // mpUser里面包含付款用户的openId、头像、昵称
                // 收到微信回调支付状态，修改用户付款状态逻辑....
                studentService.afterPayment(mpUser.getOpenId(), mpUser.getHeadImgUrl());//建立学员信息（openID, 头像）
            } catch (Exception e) {
                e.printStackTrace();
            }

            return WxPayNotifyResponse.success("ok");
        } catch (WxPayException e) {
            e.printStackTrace();

            return WxPayNotifyResponse.fail("parse fail");
        }
    }


    private String getIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
