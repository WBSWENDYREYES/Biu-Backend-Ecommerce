package com.wendyecommerce.biuwendyecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity 
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            
            .authorizeHttpRequests(auth -> auth
                // 1. Permitimos acceso libre a los métodos GET de productos
                .requestMatchers(HttpMethod.GET, "/api/productos/**").permitAll()
                
                // 2. CORREGIDO: Agregamos explícitamente "/login.html" a los accesos permitidos
                .requestMatchers("/", "/login.html", "/css/**", "/js/**", "/assets/**", "/Scripts/**", "/Content/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/usuarios/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/usuarios/crear").permitAll()
             
                // 3. Cualquier otra petición requerirá estar autenticado
                .anyRequest().authenticated()
            )
            
            // 4. CORREGIDO: Apuntamos la página de login al archivo físico real .html
            .formLogin(form -> form
                .loginPage("/login.html") // 👈 Cambiado de "/login" a "/login.html"
                .usernameParameter("email") 
                .passwordParameter("password")
                .defaultSuccessUrl("/api/productos", true) 
                .permitAll()
            )
            
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html?logout") // 👈 Cambiado también aquí
                .permitAll()
            );

        return http.build();
    }
}