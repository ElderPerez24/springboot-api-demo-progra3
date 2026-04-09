package com.ejemplo.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Spring Boot Demo",
                version = "1.0.0",
                description = "Laboratorio de Spring Boot 3.x con validaciones, manejo de errores y documentación OpenAPI",
                contact = @Contact(
                        name = "Elder Perez",
                        email = "elder@example.com"
                )
        )
)
public class OpenApiConfig {
}
