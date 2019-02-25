package com.jack.springbootdubbocontrol;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.github.pagehelper.PageHelper;
import com.jack.springbootdubbocontrol.service.CrawServiceImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Properties;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

@EnableDubbo
@SpringBootApplication
public class SpringbootDubboControlApplication{

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDubboControlApplication.class, args);
        try {
            TimeUnit.MINUTES.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("服务提供者------>>服务关闭");
    }

    @Bean
    PageHelper pageHelper(){
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        /*new SqlSessionFactoryBean().setPlugins(new Interceptor[]{pageHelper});*/
        return pageHelper;
    }
}

