package com.huasheng.wmssystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import io.swagger.v3.oas.models.info.Info;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/5/14 14:30
 * @Description ：swagger3配置
 */
@Configuration
public class SwaggerConfig {

 /*   @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huasheng.wmssystem.controller"))
                .build()
//                .directModelSubstitute(org.threeten.bp.LocalDate.class, java.sql.Date.class)
//                .directModelSubstitute(org.threeten.bp.OffsetDateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("wms接口文档")
                .description("")
//                .license("Apache 2.0")
//                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .version("1.0.0")
//                .contact(new Contact("","", "apiteam@swagger.io"))
                .build();
    }

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("wms接口文档")
                        .description("")
                        .termsOfService("")
                        .version("1.0.0")
//                        .contact(new io.swagger.v3.oas.models.info.Contact()
//                                .email("apiteam@swagger.io"))
                );
    }*/


    // 配置 Swagger 的Docket的bean实例
    @Bean
    public Docket docket(){
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
