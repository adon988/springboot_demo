package com.base.basetest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@MapperScan("com.base.basetest.mapper") //使用 MapperScan 批次掃描 mapper 資料夾：mybatis 對 mapper 層自動掃瞄
@ConfigurationPropertiesScan("com.base.basetest.bean")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
