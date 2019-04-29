package com.sandy.tests.common.util.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandy.tests.common.util.StringUtil;

/**
 *   HTTP请求工具类
 * 
 * @author zhangyg
 * @version $Id: HttpUtil.java, v 0.1 2016年11月9日 下午2:54:19 zhangyg Exp $
 */
public final class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     *  从request的 requestParams中获取所有参数
     * 
     * @param requestParams
     * @return
     */
    public static Map<String, String> fetchParams(Map<String, String[]> requestParams) {
        Map<String, String> params = new HashMap<String, String>();
        Iterator<String> iter = requestParams.keySet().iterator();
        while (iter.hasNext()) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = StringUtil.join(values);
            params.put(name, valueStr);
        }
        return params;
    }

    /**
     *  请求 响应结果
     * 
     * @param response   响应流
     * @param resp   请求结果
     */
    public static void response(HttpServletResponse response, String resp) {
        PrintWriter writer = null;
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            writer = response.getWriter();
            writer.write(resp);
            writer.flush();
        } catch (IOException e) {
            logger.error(" responseResultcode  E", e);
        } finally {
            if (null != writer) {
                try {
                    writer.close();
                } catch (Exception e2) {
                    logger.error(" order-redirect  close writer    E", e2);
                }
            }
        }
    }

}
