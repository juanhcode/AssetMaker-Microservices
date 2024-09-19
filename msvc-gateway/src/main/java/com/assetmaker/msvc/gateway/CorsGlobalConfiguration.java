package com.assetmaker.msvc.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsGlobalConfiguration {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:3000"); // Permitir todos los orígenes, reemplaza "*" por dominios específicos en producción
        config.addAllowedMethod("*"); // Permitir todos los métodos (GET, POST, etc.)
        config.addAllowedHeader("*"); // Permitir todos los headers

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Aplicar la configuración para todas las rutas

        return new CorsFilter(source);
    }
}

