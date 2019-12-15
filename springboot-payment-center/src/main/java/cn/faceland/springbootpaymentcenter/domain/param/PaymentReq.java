package cn.faceland.springbootpaymentcenter.domain.param;

import cn.faceland.springbootpaymentcenter.util.AES;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author watermelon
 * @Date 2019-12-14
 * @Description
 */
@Data
@Accessors(chain = true)
public class PaymentReq {
    /** 服务名称 */
    private String server;
    /** 请求参数，非对称加密密文 */
    /**
     * 原文：{"money":100.0,"payType":1,"rebackParam":{"method":"get","type":"http","reback":{"orderNumber":"123456"}},"tradeType":1}
     * 密文：
     */
    private String param;

    public static void main(String[] args) {
//        PaymentReqItem paymentReqItem = new PaymentReqItem();
//        paymentReqItem.setServer("bian");
//        paymentReqItem.setMoney(100.0);
//        paymentReqItem.setPayType(1);
//        paymentReqItem.setTradeType(1);
//
//        JSONObject rebackParam = new JSONObject();
//        rebackParam.put("type","http");
//        rebackParam.put("method","get");
//        JSONObject reback = new JSONObject();
//        reback.put("orderNumber","123456");
//        rebackParam.put("reback",reback);
//        paymentReqItem.setRebackParam(rebackParam);
//
//        System.out.println(JSONObject.toJSONString(paymentReqItem));

        //对称秘钥
        String password = "f1a2c3e4l5a6n7d";

        String paramStr = "{\"money\":100.0,\"payType\":1,\"rebackParam\":{\"method\":\"get\",\"type\":\"http\",\"reback\":{\"orderNumber\":\"123456\"}},\"server\":\"bian\",\"tradeType\":1}";
        String cipher = "";
        long start = System.currentTimeMillis();
        try {
            //服务传过来的参数
            cipher = AES.encrypt(paramStr, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("加密耗时：" + (end - start));

        start = System.currentTimeMillis();
        String enCipher = "";
        try {
            //解析服务的参数
            enCipher = AES.decrypt(cipher,password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        end = System.currentTimeMillis();
        System.out.println("解密耗时：" + (end - start));

        PaymentReqItem item = JSONObject.parseObject(enCipher, PaymentReqItem.class);
        System.out.println(item);
    }
}
