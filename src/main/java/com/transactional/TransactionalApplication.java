package com.transactional;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/12/2 10:39
 * @Description:
 */
@SpringBootApplication
@MapperScan("com.transactional.dao")
public class TransactionalApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionalApplication.class,args);
    }
}
