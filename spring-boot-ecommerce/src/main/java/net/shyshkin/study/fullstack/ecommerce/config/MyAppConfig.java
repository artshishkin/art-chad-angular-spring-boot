package net.shyshkin.study.fullstack.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyAppConfig implements WebMvcConfigurer {

    @Value("${app.cors.allowedOrigins}")
    private String[] corsAllowedOrigins;

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping(basePath + "/**")
                .allowedOrigins(corsAllowedOrigins);
    }
}
