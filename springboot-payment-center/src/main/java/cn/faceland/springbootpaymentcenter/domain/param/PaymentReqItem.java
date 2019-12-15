package cn.faceland.springbootpaymentcenter.domain.param;

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
public class PaymentReqItem {
    /** 金额 */
    private Double money;
    /** APP, H5 */
    private Integer tradeType;
    /** 支付方式 微信支付，支付宝支付 */
    private Integer payType;
    /** 回调封装参数 */
    private JSONObject rebackParam;

    public static void main(String[] args) {
        JSONObject rebackParam = new JSONObject();
        rebackParam.put("type","http");
        rebackParam.put("method","get");
        rebackParam.put("reback",new JSONObject().put("orderNumber","123456"));
    }
}
