package com.jack.springbootdubbofront;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@SpringBootApplication
@EnableDubbo
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class SpringbootDubboFrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDubboFrontApplication.class, args);
    }

}

