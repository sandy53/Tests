package com.sandy.tests.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;

import com.sandy.tests.record.model.ReqParams;

/**
 *  初始化生成工具类
 *    利用模型生成初始化SQL
 * @author sandy
 * @version $Id: GenernateUtils.java, v 0.1 2019年4月23日 上午8:53:53 sandy Exp $
 */
public final class GenernateUtils {

    private static final List<String> IGNORE_LIST = new ArrayList<String>(
        Arrays.asList("serialVersionUID"));

    public static void main1(String[] args) {
        genernateBase(ReqParams.class);
    }

    public static void genernateBase(List<Class<?>> clzs) {
        for (Class<?> clz : clzs) {
            genernateBase(clz);
        }
    }
    public static void genernateBase(Class<?> clz) {

        String recordCode = clz.getSimpleName();
        genernateRecordInfo(clz);
        Genernate baseAnnotation = clz.getAnnotation(Genernate.class);
        if (baseAnnotation == null) {
            System.err.println("class need  info @Genernate");
            return;
        }
        Field[] fields = clz.getDeclaredFields();
        StringBuilder creator = new StringBuilder("CREATE TABLE ").append(baseAnnotation.value())
            .append(" (\r\n    `id` int(7) NOT NULL AUTO_INCREMENT COMMENT '")
            .append(baseAnnotation.desc()).append("'");

        //
        StringBuilder columnBuilder = new StringBuilder(
            "INSERT INTO `record_field` ( `record_code`, `field_code`, `field_name`, `field_column`,  `field_desc`,  `create_time`, `update_time`) VALUES ");
        String fieldName = null;
        String column = null;
        Genernate annotation = null;
        for (Field field : fields) {
            fieldName = field.getName();
            if (IGNORE_LIST.contains(fieldName)) {
                continue; //忽略
            }
            annotation = field.getAnnotation(Genernate.class);
            if (annotation != null) {
                if (annotation.ignore()) {
                    continue; //忽略
                }
                column = annotation.value();
            }
            if (StringUtils.isEmpty(column)) {
                column = fieldName;
            }
            creator.append(", \r\n  ").append(column)
                .append("  varchar(248) DEFAULT NULL COMMENT '");
            if (annotation != null) {
                creator.append(annotation.desc()).append("'");
            }

            columnBuilder.append("\r\n (");
            columnBuilder.append("'").append(recordCode).append("',");
            columnBuilder.append("'").append(fieldName).append("',");
            columnBuilder.append("'").append(annotation.desc()).append("',");
            columnBuilder.append("'").append(column).append("',");
            columnBuilder.append("'").append(annotation.desc()).append("',");
            columnBuilder.append("'").append(System.currentTimeMillis()).append("',");
            columnBuilder.append("'").append(System.currentTimeMillis()).append("'");
            columnBuilder.append("),");
        }

        creator.append(",\r\n PRIMARY KEY (`id`)  \r\n ); ");
        System.err.println(columnBuilder.toString());
        System.err.println(creator.toString());
    }


    /**
     * 生成记录信息sql
     * 
     * @param clz
     */
    public static void genernateRecordInfo(Class<?> clz) {
        Genernate annotation = clz.getAnnotation(Genernate.class);
        StringBuilder record = new StringBuilder("INSERT INTO `record_info` ( ").append(
            "`record_code`, `record_name`, `record_table`, `record_type`, `record_desc`, `create_time`, `update_time`) \r\n VALUE ( '")
            .append(clz.getSimpleName()).append("','").append(annotation.desc())
            .append("','").append(annotation.value()).append("','")
            .append(clz.getSimpleName()).append("','").append(annotation.desc())
            .append("',").append(System.currentTimeMillis()).append(", ")
            .append(System.currentTimeMillis()).append(");");
        System.err.println(record.toString());

    }

}
