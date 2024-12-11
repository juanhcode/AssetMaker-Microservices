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
        config.setAllowCredentials(true); // Permitir envío de credenciales (opcional, dependiendo del caso)
        config.addAllowedOriginPattern("*"); // Permitir cualquier origen
        config.addAllowedMethod("*"); // Permitir todos los métodos (GET, POST, etc.)
        config.addAllowedHeader("*"); // Permitir todos los headers

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Aplicar configuración a todas las rutas

        return new CorsFilter(source);
    }
}
