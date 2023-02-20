package com.backend.university.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.backend.university.config.swagger.SwaggerMessages.API_DESCRIPTION;
import static com.backend.university.config.swagger.SwaggerMessages.API_TITLE;
import static com.backend.university.config.swagger.SwaggerMessages.API_VERSION;
import static com.backend.university.config.swagger.SwaggerMessages.BASE_PACKAGE;
import static com.backend.university.config.swagger.SwaggerMessages.CONTACT_EMAIL;
import static com.backend.university.config.swagger.SwaggerMessages.CONTACT_NAME;
import static com.backend.university.config.swagger.SwaggerMessages.CONTACT_URL;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .version(API_VERSION)
                .contact(new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL))
                .build();
    }

}
