package com.edu.iga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class IgaWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(IgaWebApplication.class, args);
		System.out.println("启动成功");
	}

}
