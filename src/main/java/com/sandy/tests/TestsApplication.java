package com.sandy.tests;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan({ "com.sandy.tests.dao", "com.sandy.tests.record.dao" }) // MYBATIS
@EnableTransactionManagement //如果mybatis中service实现类中加入事务注解，需要此处添加该注解
@EnableCaching
@SpringBootApplication
public class TestsApplication {



    public static void main(String[] args) {
        SpringApplication.run(TestsApplication.class, args);
    }

}
