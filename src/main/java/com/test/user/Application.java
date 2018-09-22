package com.test.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		
		
		SpringApplication.run(Application.class, args);
	}
	
//	@Bean
//	public PageHelper pageHelper() {
//		
//		PageHelper pageHelper = new PageHelper();
//		Properties properties = new Properties();
//		properties.setProperty("offsetAsPageNum","true");
//		properties.setProperty("rowBoundsWithCount","true");
//		properties.setProperty("reasonable","true");
//		properties.setProperty("dialect","mysql"); 
//		pageHelper.setProperties(properties);
//		return pageHelper;
//	}

}
