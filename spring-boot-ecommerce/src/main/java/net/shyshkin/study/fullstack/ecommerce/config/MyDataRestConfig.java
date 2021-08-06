package net.shyshkin.study.fullstack.ecommerce.config;

import lombok.extern.slf4j.Slf4j;
import net.shyshkin.study.fullstack.ecommerce.entity.Product;
import net.shyshkin.study.fullstack.ecommerce.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Slf4j
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Value("${app.cors.allowedOrigins}")
    private List<String> corsAllowedOrigins;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] unsupportedActions = {POST, PUT, DELETE};

        List.of(Product.class, ProductCategory.class)
                .forEach(clazz ->
                        config.getExposureConfiguration()
                                .forDomainType(clazz)
                                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions))
                                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions))
                );

        if (corsAllowedOrigins != null && !corsAllowedOrigins.isEmpty()) {
            String[] origins = corsAllowedOrigins.toArray(new String[0]);
            log.debug("CORS Allowed {} origins: {}", origins.length, origins);
            cors.addMapping("/api/**").allowedOrigins(origins);
        }
    }
}
