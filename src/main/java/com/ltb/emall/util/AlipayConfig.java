package com.ltb.emall.util;

import java.io.FileWriter;

/**
 * ClassName: AlipayConfig
 * Description: TODO
 * User: litengbin
 * Date: 2018/5/4 12:57
 * Version: 1.0.0
 */
/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
    public class AlipayConfig {
        //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息
        // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
        public static String app_id = "2016080403162340";

        // 商户私钥，您的PKCS8格式RSA2私钥
        public static String merchant_private_key = "MIIEvAID2tulSSmawG5+F4NZbexpnxi8NKQJPZEeAA==";

        // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
        public static String alipay_public_key = "MIIBIjt26tLTKar8S1ERDWI25viBcMz7PLMxVVUmHf5tdBWfbMhUs3QIDAQAB";

        // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
        public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

        // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
        public static String return_url = "http://localhost:8080/Exam/index/gouMai";
        // 签名方式
        public static String sign_type = "RSA2";

        // 字符编码格式
        public static String charset = "utf-8";

        // 支付宝网关
        public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

        // 支付宝网关
        public static String log_path = "E:\\";

        //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息
        /**
         * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
         * @param sWord 要写入日志里的文本内容
         */
        public static void logResult(String sWord ) {
           FileWriter writer = null;
            try {
                writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
                writer.write(sWord);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
