package com.huasheng.wmssystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.oas.annotations.EnableOpenApi;

//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableOpenApi
@SpringBootApplication
//@EnableJpaRepositories("com.huasheng.wmssystem")
//@EnableJpaRepositories("com.huasheng.wmssystem.data.dao")
//@ComponentScan(basePackages = {"com.huasheng.wmssystem"})
//@ComponentScan(basePackages = {"com.huasheng.wmssystem.core","com.huasheng.wmssystem.controller","com.huasheng.wmssystem.config"})//扫描 @Service、@Controller 注解
//@EnableJpaRepositories(basePackages = "com.huasheng.wmssystem.data.dao")//扫描 @Repository 注解
//@EntityScan(basePackages = "com.huasheng.wmssystem.domain.entity")//扫描 @Entity 注解
public class WmsSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmsSystemApplication.class, args);
    }

}
