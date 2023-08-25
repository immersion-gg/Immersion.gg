package com.immersion.riot.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final CorsConfig corsConfig;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins(corsConfig.getAllowOrigins())
                .allowedMethods(corsConfig.getAllowMethods());
    }

    @Component
    @ConfigurationProperties(prefix = "cors")
    @Data
    public static class CorsConfig {
        private String[] allowOrigins;
        private String[] allowMethods;
    }
}
