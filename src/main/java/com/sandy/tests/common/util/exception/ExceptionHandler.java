package com.sandy.tests.common.util.exception;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.sandy.tests.common.model.ReqResult;
import com.sandy.tests.common.model.ResultCode;


/**
 *  异常处理信息封装
 * 
 * @author zhangyg
 * @version $Id: ExceptionHandler.java, v 0.1 2019年2月1日 下午12:05:27 zhangyg Exp $
 */
public class ExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    /**
     * 处理
     * 
     * @param e
     * @return
     */
    public static ReqResult<String> doHandler(Throwable e) {
        ReqResult<String> result = new ReqResult<>(ResultCode.FAIL);
        String errorCode = UUID.randomUUID().toString();
        result.setErrorCode(errorCode);
        result.setError(e.getLocalizedMessage());
        logger.error("EXCEPTION-HANDLER:{}", errorCode, e);
        // 按照异常类型进行处理
        if (e instanceof BaseExcepion) {
            result.setResultCode(((BaseExcepion) e).getCode());
        } else if (e instanceof NotFoundException) { //服务不可用
            result.setResultCode(ResultCode.SERVICE_UNUSE);
        } else if (e instanceof DataAccessException) {
            result.setResultCode(ResultCode.DATABASE_ERROR);
        } else if (e instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) e;
            HttpStatus httpStatus = responseStatusException.getStatus();
            result.setHttpStatus(httpStatus.value());
            result.setError(e.getMessage());
        }

        return result;
    }

}
