package com.itsupport.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI ticketSystemOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("IT Support Ticket System API")
                .description("API for managing IT support tickets")
                .version("1.0")
                .contact(new Contact()
                    .name("IT Support Team")
                    .email("support@company.com")));
    }
} 