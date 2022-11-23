package com.harulab.adapfit.global;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "DELETE", "PATCH", "PUT")
                .allowedOrigins(
                        "http://localhost:3000", "http://localhost:3001", "http://localhost:3002");
    }

}
