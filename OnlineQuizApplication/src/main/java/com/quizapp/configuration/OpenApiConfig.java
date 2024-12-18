package com.quizapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Online Quiz Application API")
                        .description("API documentation for the Online Quiz Application")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Shubham Rothe")
                                .email("shubhamrothe01@gmail.com")
                                .url("https://github.com/shubhamrothe/OnlineQuizApplication.git"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}

