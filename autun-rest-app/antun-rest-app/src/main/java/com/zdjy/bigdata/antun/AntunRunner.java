package com.zdjy.bigdata.antun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@MapperScan("com.zdjy.bigdata.antun.mapper")
@EnableScheduling
@SpringBootApplication
public class AntunRunner {

	public static void main(String[] args) {
		SpringApplication.run(AntunRunner.class, args);
	}
}
