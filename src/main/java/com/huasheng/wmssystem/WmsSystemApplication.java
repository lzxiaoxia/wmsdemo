package com.huasheng.wmssystem;

import com.huasheng.wmssystem.core.CoreConfiguration;
import com.huasheng.wmssystem.data.DataConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableJpaRepositories("com.huasheng.wmssystem")
@Import({CoreConfiguration.class, DataConfiguration.class})
public class WmsSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmsSystemApplication.class, args);
    }

}
