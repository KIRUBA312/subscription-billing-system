package com.example.subscription.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI subscriptionManagementOpenAPI() {
		
		return new OpenAPI()
				.info(new Info()
						.title("Subscription Billing System API")
						.description("Enterprise Level Subscription billing System"
								+"using Spring Boot + MySQL"
								)
						.version("1.0")
						.contact(new Contact()
								.name("contact name")
								.email("support@subscription.com")
								.url("https://subscription.com")
								)
						)
				.externalDocs(
						new ExternalDocumentation()
						.description("project Documentation")
						.url("https://subscription.com/docs")
						);
	}
}
