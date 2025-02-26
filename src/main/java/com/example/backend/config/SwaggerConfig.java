package com.example.backend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    // 스프링 버전에 따라서 Swagger의 버전도 맞춰서 변경
    private Info apiInfo() {
        return new Info()
                .title("backend API 명세서")
                .description("Backend API 명세서입니다.")
                .version("1.0.0");
    }
}