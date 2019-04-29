package com.sandy.tests.common.util.exception;

import com.sandy.tests.common.model.RespCode;

/**
 *  基础异常类
 * 
 * @author sandy
 * @version $Id: BaseExcepion.java, v 0.1 2018年8月8日 下午5:04:16 sandy Exp $
 */
public interface BaseExcepion {

    /**
     * 获取返回码
     * 
     * @return
     */
    RespCode getCode();

}
