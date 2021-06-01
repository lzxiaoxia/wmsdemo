package com.huasheng.wmssystem.config;

import com.huasheng.wmssystem.shiro.AccountRealm;
import com.huasheng.wmssystem.shiro.JwtFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisClusterManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/5/13 15:52
 * @Description ：权限校验,shiro+redis管理缓存和会话信息
 */
@Configuration
public class ShiroConfig {

    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();

        // inject redisSessionDAO
        sessionManager.setSessionDAO(redisSessionDAO);
        return sessionManager;
    }

    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setPassword("test123456");
        redisStandaloneConfiguration.setHostName("127.0.0.1");
        redisStandaloneConfiguration.setPort(6379);

        return redisStandaloneConfiguration;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(AccountRealm accountRealm,
                                                     SessionManager sessionManager,
                                                     RedisCacheManager redisCacheManager) {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(accountRealm);

        //inject sessionManager
        securityManager.setSessionManager(sessionManager);

        // inject redisCacheManager


//        RedisClusterManager redisClusterManager=new RedisClusterManager();
//        redisClusterManager.setHost(hostName);
//        redisClusterManager.setPassword(password);
//        redisClusterManager.setDatabase(0);
//        redisCacheManager.setRedisManager(redisClusterManager);

        securityManager.setCacheManager(redisCacheManager);
        return securityManager;
    }


    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        Map<String, String> filterMap = new LinkedHashMap<>();

        filterMap.put("/test/**", "anon");
        filterMap.put("/api/**", "jwt");
        chainDefinition.addPathDefinitions(filterMap);
        return chainDefinition;
    }

    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager,
                                                         ShiroFilterChainDefinition shiroFilterChainDefinition) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        Map<String, Filter> filters = new HashMap<>();
        filters.put("jwt", jwtFilter);
        shiroFilter.setFilters(filters);

//        Map<String, String> filterMap = shiroFilterChainDefinition.getFilterChainMap();

        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("/test/**", "anon");
        filterMap.put("/api/**", "jwt");

//        // 设置无权限时跳转的 url;
//        factoryBean.setUnauthorizedUrl("/unauthorized/无权限");
//        Map<String, String> filterRuleMap = new HashMap<>();
//
//        filterRuleMap.put("/api/**","anon");
//        //静态资源放行
//        filterRuleMap.put("/**/*.html","anon");

//        // 所有请求通过我们自己的JWT Filter
//        filterRuleMap.put("/**", "jwt");
//        // 访问 /unauthorized/** 不通过JWTFilter
//        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
//        return factoryBean;

        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;


    }

}
