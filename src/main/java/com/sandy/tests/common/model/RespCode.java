package com.sandy.tests.common.model;

/**
 * 通用返回码定义
 * 
 * @author sandy
 * @version $Id: RespCode.java, v 0.1 2019年4月18日 下午1:34:18 sandy Exp $
 */
public interface RespCode {

    /**
     * 获取返回码
     * 
     * @return
     */
    int getCode();
    
    
    /**
     * 获取返回描述
     * 
     * @return
     */
    String getDesc();

    String getEnDesc();
}
