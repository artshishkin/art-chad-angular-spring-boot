package net.shyshkin.study.fullstack.ecommerce.config;

import net.shyshkin.study.fullstack.ecommerce.entity.Product;
import net.shyshkin.study.fullstack.ecommerce.entity.ProductCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ConfigurableHttpMethods;
import org.springframework.data.rest.core.mapping.ExposureConfigurer;
import org.springframework.data.rest.core.mapping.ResourceMetadata;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

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
    }
}
