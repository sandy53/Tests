package com.sandy.tests.record.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;

import com.sandy.tests.model.ReqResponse;

/**
 *  初始化生成工具类
 *    利用模型生成初始化SQL
 * @author sandy
 * @version $Id: GenernateUtils.java, v 0.1 2019年4月23日 上午8:53:53 sandy Exp $
 */
public final class GenernateUtils {

    private static final List<String> IGNORE_LIST = new ArrayList<String>(
        Arrays.asList("serialVersionUID"));

    public static void main(String[] args) {
        genernateBase(ReqResponse.class);
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

        Field[] subFields = clz.getDeclaredFields();
        StringBuilder creator = new StringBuilder("CREATE TABLE ").append(baseAnnotation.value())
            .append(" (\r\n    `record_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '")
            .append(baseAnnotation.desc()).append("',");
        //
        StringBuilder columnBuilder = new StringBuilder(
            "INSERT INTO `record_field` ( `record_code`, `field_code`, `field_name`, `field_column`, `primary_key`,  `field_desc`,  `create_time`, `update_time`) VALUES ");
        //通用主键
        columnBuilder.append("\r\n   ('" + recordCode + "','recordId','" + baseAnnotation.desc()
                             + "','record_id','1','','1556546725400','1556546725400'),");
        
        
        genernateFields(subFields, creator, columnBuilder, recordCode);
        String columnBase = "\r\n  ('" + recordCode
                            + "','status','记录状态','status','0','记录状态','1556585925242','1556585925242'),\r\n"
                            + " ('" + recordCode
                            + "','createTime','创建时间 ','create_time','0','创建时间 ','1556585925242','1556585925242'),\r\n"
                            + " ('" + recordCode
                            + "','updateTime','修改时间','update_time','0','修改时间','1556585925243','1556585925243');";

        columnBuilder.append(columnBase);

        //通用字段
        String commonFiled = " \r\n  status  int(2) DEFAULT 0 COMMENT '状态', \r\n"
                             + "  create_time  bigint(15) DEFAULT 0 COMMENT '创建时间', \r\n"
                             + "  update_time  bigint(15) DEFAULT 0 COMMENT '修改时间 ', ";
        creator.append(commonFiled);
        creator.append("\r\n PRIMARY KEY (`record_id`)  \r\n ); ");
        System.err.println(columnBuilder.toString());
        System.err.println(creator.toString());
    }

    private static void genernateFields(Field[] fields, StringBuilder creator,
                                            StringBuilder columnBuilder, String recordCode) {
        String fieldName = null;
        String column = null;
        Genernate annotation = null;
        for (Field field : fields) {
            fieldName = field.getName();
            column = null;
            if (IGNORE_LIST.contains(fieldName)) {
                continue; //忽略
            }
            annotation = field.getAnnotation(Genernate.class);
            if (annotation != null) {
                column = annotation.value();
            }
            if (StringUtils.isEmpty(column)) {
                column = fieldName;
            }
            creator.append("\r\n  ").append(column)
                .append("  varchar(128) DEFAULT NULL COMMENT '")
                .append(annotation != null ? annotation.desc() : "").append("',");

            columnBuilder.append("\r\n (");
            columnBuilder.append("'").append(recordCode).append("',");
            columnBuilder.append("'").append(fieldName).append("',");
            columnBuilder.append("'").append(annotation != null ? annotation.desc() : "")
                .append("',");
            columnBuilder.append("'").append(column).append("',");
            columnBuilder.append("'").append(0).append("',");
            columnBuilder.append("'").append(annotation != null ? annotation.desc() : "")
                .append("',");
            columnBuilder.append("'").append(System.currentTimeMillis()).append("',");
            columnBuilder.append("'").append(System.currentTimeMillis()).append("'");
            columnBuilder.append("),");

        }
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
