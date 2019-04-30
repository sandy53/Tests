package com.sandy.tests.record.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.Mapping;

/**
 *  用于初始化模型到sql
 * 
 * @author sandy
 * @version $Id: Genernate.java, v 0.1 2019年4月23日 上午8:57:27 sandy Exp $
 */
@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Mapping
public @interface Genernate {

    /**
     *  是否校验非空
     *      默认为必须
     * 
     * @return
     */
    String value() default "";

    /**
     * 备注
     * 
     * @return
     */
    String desc() default "";

    boolean ignore() default false;
    
    boolean isId() default false;

    
}
