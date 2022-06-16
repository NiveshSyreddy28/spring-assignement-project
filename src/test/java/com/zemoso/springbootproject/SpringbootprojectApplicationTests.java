package com.zemoso.springbootproject;

import com.zemoso.springbootproject.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;

@SpringBootTest
class SpringbootprojectApplicationTests {

	@Autowired
	MenuService menuServiceDemo;

	@MockBean
	

	@Test
	void contextLoads() {
	}

}
