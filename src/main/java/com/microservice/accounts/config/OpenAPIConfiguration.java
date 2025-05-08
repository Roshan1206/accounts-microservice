package com.microservice.accounts.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Accounts microservice")
                        .description("Accounts microservice REST API documentation")
                        .version("v1")
                        .contact(new Contact()
                                .name("whitedevil")
                                .email("whitedevil@xyz.com")
                                .url("whitedevil.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("whitedevil.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Accounts microservice REST API documentation")
                        .url("whitedevil.com/index.html"));
    }
}
