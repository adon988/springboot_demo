package com.base.basetest;

import com.base.basetest.bean.MypersonConfiguraties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Spring boot 單元測試
 * 可以在測試期間，很方便的類似編碼一樣自動注入容器的功能
 */
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	MypersonConfiguraties mypersonConfiguraties;

	@Test
	void contextLoads() {
		System.out.println(mypersonConfiguraties);
	}

}
