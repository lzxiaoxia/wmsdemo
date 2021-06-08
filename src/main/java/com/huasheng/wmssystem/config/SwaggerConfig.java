package com.huasheng.wmssystem.config;

import com.huasheng.wmssystem.exception.CommonErrorEnums;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/5/14 14:30
 * @Description ：swagger3配置
 */
@Configuration
public class SwaggerConfig {

    // 配置 Swagger 的Docket的bean实例
    @Bean
    public Docket docket() {
        List<Response> responseList = new ArrayList<>();
        for (CommonErrorEnums enums : CommonErrorEnums.values()) {
            responseList.add(new ResponseBuilder().code(enums.getCodeStr()).description(enums.getMsg()).build());
        }

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .groupName("wms系统")
                // enable 是否启动Swagger，如果为False，则Swagger不能在浏览器中访问
                // .enable(false)
                .select()
                // RequestHandlerSelectors 配置要扫描的接口方式
                // basePackage: 指定要扫描的包 通常使用这个
                // any(): 扫描全部
                // none(): 不扫描
                // withClassAnnotation: 扫描类上的注解，参数是一个注解的反射对象
                // withMethodAnnotation: 扫描方法上的注解，参数是一个注解的反射对象
                .apis(RequestHandlerSelectors.basePackage("com.huasheng.wmssystem.controller"))
                .paths(PathSelectors.any())
                // .paths(PathSelectors.ant(""))
                .build()
                .globalResponses(HttpMethod.GET, responseList)
                .globalResponses(HttpMethod.POST, responseList)
                .globalResponses(HttpMethod.DELETE, responseList)
                .globalResponses(HttpMethod.PUT, responseList)
                //类型左边转成右边
//                .directModelSubstitute(java.sql.Timestamp.class, java.util.Date.class)
//                .directModelSubstitute(org.threeten.bp.OffsetDateTime.class, java.util.Date.class)
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContextList = new ArrayList<>();
        List<SecurityReference> securityReferenceList = new ArrayList<>();
        securityReferenceList.add(new SecurityReference("Authorization", scopes()));
        securityContextList.add(SecurityContext
                .builder()
                .securityReferences(securityReferenceList)
                .forPaths(PathSelectors.any())
                .build()
        );
        return securityContextList;
    }

    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{new AuthorizationScope("global", "accessAnything")};
    }

    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeyList;
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3接口文档")
//                .description("更多请咨询服务开发者Ray。")
//                .contact(new Contact("Ray。", "http://www.ruiyeclub.cn", "ruiyeclub@foxmail.com"))
                .version("1.0")
                .build();
    }


}
