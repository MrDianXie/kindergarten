package com.dq408.kindergarten;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.dq408.kindergarten.dao")
@SpringBootApplication
public class KindergartenApplication {

    public static void main(String[] args) {
        SpringApplication.run(KindergartenApplication.class, args);
    }

}
