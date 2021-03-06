package com.wyj.ytyn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.wyj.ytyn.mapper")
public class YtynApplication {

    public static void main(String[] args) {
        SpringApplication.run(YtynApplication.class, args);
    }

}
