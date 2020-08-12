package com.keer.bdql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //需要扫描的API(controller)包名
                .apis(RequestHandlerSelectors.basePackage("com.keer.bdql.Controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 网页上的一些配置，标题，版本等等
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swaager Demo Rest API")
                .contact("可耳")
                .version("v1.0")
                .build();
    }
}
