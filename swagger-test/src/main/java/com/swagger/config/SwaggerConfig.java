package com.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger配置
 */
@Configuration
@EnableOpenApi  //@EnableOpenApi用于开启Swagger在 Spring Boot，可放在启动类上，也可放在这里，
public class SwaggerConfig {
    /**
     * 配置Swagger的Docket的bean实例
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                //(常用) groupName：分组
                .groupName("标准接口")
                .apiInfo(apiInfo())
                //(常用) select：通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现
                .select()
                //
                /**
                 * (常用) apis：过滤规则，可以自定义指定哪些接口生成文档
                 *  RequestHandlerSelectors:配置要扫描接口的方式
                 *       basePackage:指定要扫描的包
                 *       any:扫面全部
                 *       none:不扫描
                 *       withClassAnnotation:扫描类上的注解(参数是类上注解的class对象)
                 *       withMethodAnnotation:扫描方法上的注解(参数是方法上的注解的class对象)
                 */
                .apis(RequestHandlerSelectors.basePackage("com.swagger.controller"))
                //(常用) apis：加了ApiOperation注解的类，生成接口文档
//              .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                /**
                 * (常用) paths：过滤路径
                 *  PathSelectors:配置过滤的路径
                 *      any:过滤全部路径
                 *      none:不过滤路径
                 *      ant:过滤指定路径:按照按照Spring的AntPathMatcher提供的match方法进行匹配
                 *      regex:过滤指定路径:按照String的matches方法进行匹配
                 */
                .paths(PathSelectors.any())
                //(已淘汰）所有接口统一定义响应信息
//              .build().globalResponseMessage();
                .build();
    }

    /**
     * 配置文档描述
     * 访问地址：http://ip:port/swagger-ui.html
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //文档标题
                .title("标题：Spring Boot中使用Swagger3.0.0版本构建RESTful APIs")
                //文档描述
                .description("描述：用户列表的CRUD")
                //团队链接
                .termsOfServiceUrl("https://blog.csdn.net/a924382407/article/details/127842503")
                //联系人信息
                .contact(new Contact("刘大猫", "https://blog.csdn.net/a924382407/article/details/127842503", "ldz924382407@163.com"))
                //API 版本
                .version("1.0")
                .build();
    }
}
