package com.sandy.tests.common.util;

import java.util.Collection;
import java.util.Map;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.sandy.tests.common.model.RespCode;
import com.sandy.tests.common.model.ResultCode;
import com.sandy.tests.common.util.exception.RuningException;

/**
 *  断言类
 * 
 * @author sandy
 * @version $Id: Assert.java, v 0.1 2018年8月8日 下午2:06:18 sandy Exp $
 */
public final class Assert {

    /**
     *  断言为null
     * 
     * @param object
     * @param message
     */
    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new RuningException(message);
        }
    }

    /**
     * 断言为 正整数
     * 
     * @param i
     * @param message
     */
    public static void isPositiveInteger(Number number) {
        isPositiveInteger(number, ResultCode.METHOD_CALL_PARAMETER_ERROR, null);
    }

    /**
     * 断言为 正整数
     * 
     * @param i
     * @param message
     */
    public static void isPositiveInteger(Number number, RespCode respCode) {
        isPositiveInteger(number, respCode, null);
    }

    /**
     * 断言为 正整数
     * 
     * @param i
     * @param message
     */
    public static void isPositiveInteger(Number number, String message) {
        isPositiveInteger(number, ResultCode.METHOD_CALL_PARAMETER_ERROR, message);
    }

    /**
     * 断言为 正整数
     * 
     * @param i
     * @param message
     */
    public static void isPositiveInteger(Number number, RespCode respCode, String message) {
        if (number == null || number.longValue() <= 0) {
            throw new RuningException(respCode, message);
        }
    }

    /**
     *  参数不能为空， 为空时抛出运行时异常
     * 
     * @param object
     * @param message
     */
    public static void notNull(Object object) {
        notNull(object, ResultCode.METHOD_CALL_PARAMETER_ERROR, null);
    }

    /**
     *  参数不能为空， 为空时抛出运行时异常
     * 
     * @param object
     * @param message
     */
    public static void notNull(Object object, String message) {
        notNull(object, ResultCode.METHOD_CALL_PARAMETER_ERROR, message);
    }

    /**
     *  参数不能为空， 为空时抛出运行时异常
     * 
     * @param object
     * @param message
     */
    public static void notNull(Object object, RespCode respCode) {
        notNull(object, respCode, null);
    }

    /**
     *  参数不能为空， 为空时抛出运行时异常
     * 
     * @param object
     * @param message
     */
    public static void notNull(Object object, RespCode respCode, String message) {
        if (object == null) {
            throw new RuningException(respCode, message);
        }
    }

    /**
     *  字符串不能为空
     * 
     * @param text
     */
    public static void notEmpty(String text) {
        notEmpty(text, ResultCode.METHOD_CALL_PARAMETER_ERROR, null);
    }

    /**
     *  字符串不能为空
     * 
     * @param text  字符串
     * @param message  提示信息
     */
    public static void notEmpty(String text, String message) {
        notEmpty(text, ResultCode.METHOD_CALL_PARAMETER_ERROR, message);
    }

    /**
     * 字符串不能为空
     * 
     * @param text
     * @param resultCode
     */
    public static void notEmpty(String text, RespCode respCode) {
        notEmpty(text, ResultCode.METHOD_CALL_PARAMETER_ERROR, null);
    }

    /**
     * 字符串不能为空
     * 
     * @param text
     * @param resultCode
     * @param message
     */
    public static void notEmpty(String text, RespCode respCode, String message) {
        if (!StringUtils.hasText(text)) {
            throw new RuningException(respCode, message);
        }
    }

    public static void state(boolean expression, String message) {
        if (!expression) {
            throw new RuningException(message);
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new RuningException(message);
        }
    }

    public static void hasLength(String text, String message) {
        if (!StringUtils.hasLength(text)) {
            throw new RuningException(message);
        }
    }

    public static void hasText(String text, String message) {
        if (!StringUtils.hasText(text)) {
            throw new RuningException(message);
        }
    }

    public static void doesNotContain(String textToSearch, String substring, String message) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring)
            && textToSearch.contains(substring)) {
            throw new RuningException(message);
        }
    }

    /**
     * Assert that an array contains elements; that is, it must not be
     * {@code null} and must contain at least one element.
     * <pre class="code">Assert.notEmpty(array, "The array must contain elements");</pre>
     * @param array the array to check
     * @param message the exception message to use if the assertion fails
     * @throws RuningException if the object array is {@code null} or contains no elements
     */
    public static void notEmpty(Object[] array, String message) {
        if (ObjectUtils.isEmpty(array)) {
            throw new RuningException(message);
        }
    }

    /**
     * Assert that an array contains no {@code null} elements.
     * <p>Note: Does not complain if the array is empty!
     * <pre class="code">Assert.noNullElements(array, "The array must contain non-null elements");</pre>
     * @param array the array to check
     * @param message the exception message to use if the assertion fails
     * @throws RuningException if the object array contains a {@code null} element
     */
    public static void noNullElements(Object[] array, String message) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new RuningException(message);
                }
            }
        }
    }

    /**
     * 断言集合为空
     * 
     * @param collection
     * @param message
     */
    public static void notEmpty(Collection<?> collection) {
        notEmpty(collection, ResultCode.METHOD_CALL_PARAMETER_ERROR, null);
    }

    /**
     * 断言集合为空
     * 
     * @param collection
     * @param resultCode
     * @param message
     */
    public static void notEmpty(Collection<?> collection, RespCode respCode, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new RuningException(respCode, message);
        }
    }

    /**
     * Assert that a Map contains entries; that is, it must not be {@code null}
     * and must contain at least one entry.
     * <pre class="code">Assert.notEmpty(map, "Map must contain entries");</pre>
     * @param map the map to check
     * @param message the exception message to use if the assertion fails
     * @throws RuningException if the map is {@code null} or contains no entries
     */
    public static void notEmpty(Map<?, ?> map, String message) {
        if (CollectionUtils.isEmpty(map)) {
            throw new RuningException(message);
        }
    }

}
