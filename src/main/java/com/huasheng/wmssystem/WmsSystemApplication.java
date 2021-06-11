package com.huasheng.wmssystem;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
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
