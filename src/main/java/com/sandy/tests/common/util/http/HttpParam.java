package com.sandy.tests.common.util.http;

import java.util.HashMap;
import java.util.Map;

/**
 *   测试用
 *   
 * @author zhangyg
 * @version $Id: HttpParam.java, v 0.1 2017年7月21日 上午11:37:15 zhangyg Exp $
 */
public class HttpParam {
    public static String              URL_PREFIX      = "http://127.0.0.1:8080";

    public static String              URL_TEST_PREFIX = "http://192.168.1.211:8088";

    public static final String        TOKEN_KEY       = "Authorization";

    public static Map<String, String> reqHeader       = new HashMap<String, String>();

    static {
        reqHeader.put(TOKEN_KEY, "123");
    }
}
