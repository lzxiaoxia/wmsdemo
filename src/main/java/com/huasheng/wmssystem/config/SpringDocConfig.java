package com.huasheng.wmssystem.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author ：xjTang
 * @Date ：Created By 2021/6/10 17:29
 * @Description ：
 */
@Configuration
public class SpringDocConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("SpringDoc-public")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Authorization"); // 名字和创建的SecuritySchemes一致
        List<SecurityRequirement> list = new ArrayList<>();
        list.add(securityRequirement);
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Authorization", // key值
                        new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY) //请求认证类型
                                .name("Authorization") //API key参数名
                                .description("token令牌") //API key描述
                                .in(SecurityScheme.In.HEADER))) //设置API key的存放位置
                .security(list)

                .info(new Info().title("WMS系统")
                                .description("WMS接口文档")
                                .version("v0.0.1")
//                        .license(new License().name("name").url("http://www.baidu.com"))
                )
//                .externalDocs(new ExternalDocumentation()
//                        .description("SpringShop Wiki Documentation")
//                        .url("https://springshop.wiki.github.org/docs"))
                ;
    }

}
