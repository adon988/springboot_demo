package com.base.basetest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 聲明當前是一個配置類別
 */
@Configuration
public class MyConfig {

    //Bean 可將方法返回值，添加到容器中
    @Bean
    public String aaa(){
        System.out.println("在配置類別設定添加組件");
        return "helloworld";
    }
}
