package com.huasheng.wmssystem.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/4/26 16:58
 * @Description ：JPA配置
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.huasheng.wmssystem.data.dao")
@EnableTransactionManagement
public class JPAConfig {

    @Autowired
    DataSourceProperties dataSourceProperties;

    @Primary
    @Bean
    public DataSource dataSource(){
        HikariConfig hikariConfig=new HikariConfig();
        hikariConfig.setJdbcUrl(dataSourceProperties.getUrl());
        hikariConfig.setUsername(dataSourceProperties.getUsername());
        hikariConfig.setPassword(dataSourceProperties.getPassword());

        hikariConfig.setDriverClassName(dataSourceProperties.getDriverClassName());
        HikariDataSource hikariDataSource=new HikariDataSource(hikariConfig);
        return hikariDataSource;
    }

    // 配置字段
    protected Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.physical_naming_strategy", DataBaseNamingStrategy.class.getName());
//        props.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getDepartmentName());
//        props.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getDepartmentName());
        return props;
    }

    //配置jpa连接工厂和实体映射
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter japVendor = new HibernateJpaVendorAdapter();
        japVendor.setDatabasePlatform("com.huasheng.wmssystem.SqlServerDialect");
        japVendor.setGenerateDdl(false);

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setJpaVendorAdapter(japVendor);
        entityManagerFactory.setJpaPropertyMap(jpaProperties());
        entityManagerFactory.setPackagesToScan("com.huasheng.wmssystem.domain.entity");
        return entityManagerFactory;
    }



    //事务管理器
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}