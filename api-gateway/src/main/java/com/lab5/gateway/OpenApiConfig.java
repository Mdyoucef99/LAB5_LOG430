package com.lab5.gateway;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("LAB5 API Gateway")
                        .description("API Gateway pour les microservices")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("LAB5 Team")
                                ));
    }
} 