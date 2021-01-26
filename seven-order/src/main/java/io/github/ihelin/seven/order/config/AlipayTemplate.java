package io.github.ihelin.seven.order.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import io.github.ihelin.seven.order.vo.PayVo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "alipay")
public class AlipayTemplate {

    //在支付宝创建的应用的id
    private   String app_id = "2021000117605584";

    // 商户私钥，您的PKCS8格式RSA2私钥
    private  String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDTY4MnT25EB7ykdrqtP1wyVIEL2Nmn5CMyTXhWvYQF7Pq2BXzthYcGZfFsgT0k/9iXvgl7Du0s84mXnvIGEzOQBq0oVlORhnbiYo/w03IVFIf/+EL8T1aS/Zm3fLmHt8OwlSHrTAWYb+plj38emQl1gkgKMe61oIc0Bw8MoIPvBZ4MUawju7hK6B0VdiAh1za5w6rgq9mquKuUW5CxOm6Okl/K1v0Qs1LVh/OGdfgesWhlrJoQeTMoA6TMUqoJH6DV6t9h9Fv3Gtc1GS6PLGJ1nbK9hbKcbaaxWZwuMbGLCGG1UBcvYSZRywkmEBwS9sPn2D94RkVII2X02yMFt5d5AgMBAAECggEBAJXlErNlV8NV2SamKml/bFkkIv3Ln0u2Ml8RICwh5uRszrsT2Sp0bPLUhwVx/3GlGmk/kGl6hCRD7WVeiDBWnVGb6l2YGVowB00wpvEWfucD0AxqdtWDTNiei8nY35alcFZe6dZ36d/WPFgYEQQZcOOqpnrUPXj9g+IYv0ss52EtSCWzhMGPTviinUd8xE28NmvTlmaxAU11h50emNWh6MSp+zOwhJw5n25iOsRbFAroAgXI8vGPLMtJFd/e8EaVUolAP4oRuLtKjGKy7higTgTR0nI0IN6qn6XbX5BY4M8frG6/HekUbrw9vgBOiDfyzEMYmbZSCYdfPw47eDBRQoECgYEA9ad3HN6XSf4nS8AQM+fozZGQR5lCqOfHVN7mFr7qU98BZNieqEK+cwlO/J+3RrCX/mXPFFdrveldhHeVt/gi36G9HvzsiCIJbvdsEC0HLMoF8wxZz07p4VkWM4qZGKXOh/BLD5z+HY7NDZ5Wu8L2jr2ZJrQ6mW9XYrbvgR/oqp0CgYEA3Eqct278aaw+QEoEVPdAUCtc2FCrNOmZIKo7I7kjvCobzNF0tHQQfFUueDWNmPBCJrepvJNchx+Xcdq6H9L+pL4lJ1QOjf+fASIi9M5jk1V7U/bD3ekxdU/UUtkjXap6hnFbKO5vazWXpATQBmtDY/p+C67fHifPMb/HjFOqa40CgYBnEGPBEQKYYLFL7sDdhib+WFwYLgzxoIzY5svSLzdRCPckwF2QarBMw1MIWloipmSer0g6WJMzkn1R3npKPSkkek9GeQcj9mPO0q/b+YhRGlF0r3HesFUf3qiZ50RkOuxzzDUxz8Qt0oPletEY5atcJ9/hhSwww63+FJJXsnODYQKBgQDIOWzN8SutJKgEKQJc88pZ07/0wQWL91J99RwBsTJAwYk5FZvctK2TRJ0TF6HWw3Fs3XyCcPIlwtoU2vhXGqDmvgjpASalfBRapFtfZQPuk0CgJWcLmPuiI+fZUZEqflwVPIzd3DIbIL/ujY1CeFXtVm0gU1Zqg0Z4clZ+PkFktQKBgQCkA8DX9P7SZGA1r+9e8j/DU6Nii2r+lWOSKAS0aM1jTac5wr2xScrS8pGNRAK1Jc7xbY5ksiPRhWT5pib8rv4BL3pQlafYAhdycGoH3tq87nUZWTdJ88IlD++heB/e8lVxPhQmW2SC0gyEk4IeeWecLFVsljd9kZv9WDs5i1wK+g==";
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private  String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu6wSer95SC0oMr7EQHbig2UALE1fU/sKIvmBoLhpoFZkZMySk81U4/HpxQdY3JaMLo5Nhy5UNRxvIP0F5UeJiCo+zyTsuuvDwiJI4sG3EXBB5TdXTCQbPFclEZIMCbG0T5zU2xIAbJw+keS4YGb7jE5tQkfoKsHM6pA9FuW8tMSV3uZwF8WfUlzL+yFYaYprsmCHB8IQzR10WRN0CgV3uU+MUWcvMwffasOTVSu3IZwMopv4KkHIJlLsn3pnoSYMSM15on4l8JtAfkWiP4Y4w90azJos77rGjNndb0qh+eU6QfpbcFkfGMYn3tX5BsEhlxcaNbzh8wCwrU2SON58fQIDAQAB";
	// 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
    private  String notify_url = "";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //同步通知，支付成功，一般跳转到成功页
    private  String return_url = "http://member.seven.com/memberOrder.html";

    // 签名方式
    private  String sign_type = "RSA2";

    // 字符编码格式
    private  String charset = "utf-8";

    // 自动关单时间
    private String timeout = "15m";

    // 支付宝网关
    private  String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public  String pay(PayVo vo) throws AlipayApiException {
        //AlipayClient alipayClient = new DefaultAlipayClient(AlipayTemplate.gatewayUrl, AlipayTemplate.app_id, AlipayTemplate.merchant_private_key, "json", AlipayTemplate.charset, AlipayTemplate.alipay_public_key, AlipayTemplate.sign_type);
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);

        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        String total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();

        // 30分钟内不付款就会自动关单
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\"" + timeout + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
        return result;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getMerchant_private_key() {
        return merchant_private_key;
    }

    public void setMerchant_private_key(String merchant_private_key) {
        this.merchant_private_key = merchant_private_key;
    }

    public String getAlipay_public_key() {
        return alipay_public_key;
    }

    public void setAlipay_public_key(String alipay_public_key) {
        this.alipay_public_key = alipay_public_key;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }
}
