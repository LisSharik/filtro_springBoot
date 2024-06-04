package com.riwi.filtro_spring_boot.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API del filtro", version = "1.0", description = "Documentacion de la API"))
public class OpenApiConfig {
    
}
