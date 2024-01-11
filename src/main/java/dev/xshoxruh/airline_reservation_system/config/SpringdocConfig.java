package dev.xshoxruh.airline_reservation_system.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SpringdocConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*");
            }
        };
    }


    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Airline Reservation System")
                        .description("It is an online platform that customers can use to book air tickets and check flight information.")
                        .version("14")
                        .contact(new Contact()
                                .name("Shoxruh Xolboyev")
                                .email("shoxruh9902gmail.com")
                                .url("https://github.com/xshoxruh"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                        .termsOfService("http://swagger.io/terms/"))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Test Server"),
                        new Server().url("http://localhost:9090").description("Prod Server")
                )).addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components((new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                ));
    }

    @Bean
    public GroupedOpenApi adminOpenApi() {
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch("/api/admin/**")
                .build();
    }
    @Bean
    public GroupedOpenApi agentOpenApi() {
        return GroupedOpenApi.builder()
                .group("agent")
                .pathsToMatch("/api/agent/**")
                .build();
    }
    @Bean
    public GroupedOpenApi authUserOpenApi() {
        return GroupedOpenApi.builder()
                .group("authUser")
                .pathsToMatch("/api/auth/**")
                .build();
    }

}
