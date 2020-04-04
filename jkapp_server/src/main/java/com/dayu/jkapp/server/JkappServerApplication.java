package com.dayu.jkapp.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mac
 */
@MapperScan(basePackages = "com.dayu.jkapp.server.mapper")
@SpringBootApplication
public class JkappServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JkappServerApplication.class, args);
	}

}
