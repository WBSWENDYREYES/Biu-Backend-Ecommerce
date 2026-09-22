package com.wendyecommerce.biuwendyecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
public class SecurityStaticResourcesConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
            // Pasamos todas las rutas juntas en un solo método separadas por comas
            .requestMatchers(
                "/favicon.ico",
                "/paginas/**",
                "/css/**",
                "/js/**",
                "/assets/**",
                "/plugins/**",
                "/dist/**"
            );
    }
}