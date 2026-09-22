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
                // 1. Recursos estáticos e íconos (Acceso libre absoluto)
                .requestMatchers("/favicon.ico", "/assets/**", "/css/**", "/js/**", "/plugins/**", "/dist/**").permitAll()
                .requestMatchers("/Scripts/**", "/Content/**").permitAll()
                  .requestMatchers(HttpMethod.GET, "/api/categorias/**").permitAll()
           // 2. CAMBIO CRUCIAL: Permitimos el acceso a las páginas HTML para que JS pueda inyectarlas
                .requestMatchers("/paginas/**").permitAll() 
                   .requestMatchers(HttpMethod.POST, "/api/categorias/**").hasAuthority("ADMINISTRADOR")
        
                // 3. Páginas base del sistema
                .requestMatchers("/", "/login.html", "/index.html").permitAll()
                
                // 4. Endpoints de la API para Login y Registro
                .requestMatchers(HttpMethod.POST, "/api/usuarios/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/usuarios/crear").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/usuarios/listaUsuarios").permitAll()
                // 5. Permite ver productos sin estar logueado (Público)
                .requestMatchers(HttpMethod.GET, "/api/productos/**").permitAll()
             
                // Cualquier otra petición (POST, PUT, DELETE de tu ProductoController) requerirá autenticación
                .anyRequest().authenticated()
            );

        return http.build();
    }
}