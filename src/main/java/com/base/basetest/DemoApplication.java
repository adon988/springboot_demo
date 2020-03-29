package com.base.basetest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.base.*.mapper") //使用 MapperScan 批次掃描 mapper 資料夾：mybatis 對 mapper 層自動掃瞄
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
