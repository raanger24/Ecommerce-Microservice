package com.ecommerce.orders.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(metaData());
        }

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo("Ecommerce Orders Service",
            "REST API service for processing Orders",
            "1.0",
            "Terms of service",
            new Contact("Developer Name", "http://localhost:8080/swagger-ui.html#/", "Developer Email"),
            null,
            null);
        return apiInfo;
    }
}
