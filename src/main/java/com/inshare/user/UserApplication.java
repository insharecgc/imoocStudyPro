package com.inshare.user;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//扫描mybatis mapper包路径，！！！注意这里的MapperScan是tk.mybatis.spring.annotation包下的
@MapperScan("com.inshare.user.mapper")
//启动定时器任务
@EnableScheduling
//开启异步调用方法
@EnableAsync
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
}
