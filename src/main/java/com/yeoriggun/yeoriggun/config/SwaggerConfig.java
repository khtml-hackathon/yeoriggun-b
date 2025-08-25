package com.yeoriggun.yeoriggun.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Yeoriggun API")
                        .description("Yeoriggun 프로젝트 API 문서입니다.")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Yeoriggun Developer")
                                .email("your-email@example.com")));
    }
}