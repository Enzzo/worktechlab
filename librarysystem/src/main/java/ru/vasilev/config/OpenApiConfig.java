package ru.vasilev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	@Bean
	OpenAPI libraryOpenApi() {
		return new OpenAPI()
				.info(new Info()
						.title("Library System API")
						.description("Документация для API системы библиотеки")
						.version("1.0.0"));
	}
}