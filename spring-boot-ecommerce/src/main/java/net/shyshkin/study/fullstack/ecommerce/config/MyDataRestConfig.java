package net.shyshkin.study.fullstack.ecommerce.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.shyshkin.study.fullstack.ecommerce.entity.Country;
import net.shyshkin.study.fullstack.ecommerce.entity.Product;
import net.shyshkin.study.fullstack.ecommerce.entity.ProductCategory;
import net.shyshkin.study.fullstack.ecommerce.entity.State;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Type;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private final EntityManager entityManager;

    @Value("${app.cors.allowedOrigins}")
    private String[] corsAllowedOrigins;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] unsupportedActions = {POST, PUT, DELETE, PATCH};

        List.of(Product.class, ProductCategory.class, Country.class, State.class)
                .forEach(entityClass -> disableHttpMethods(entityClass, config, unsupportedActions));

        log.debug("CORS Allowed {} origins: {}", corsAllowedOrigins.length, corsAllowedOrigins);
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(corsAllowedOrigins);

        exposeIdsForAllEntityClasses(config);
    }

    private ExposureConfigurer disableHttpMethods(Class<?> entityClass, RepositoryRestConfiguration config, HttpMethod[] unsupportedActions) {
        return config.getExposureConfiguration()
                .forDomainType(entityClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions));
    }

    private void exposeIdsForAllEntityClasses(RepositoryRestConfiguration config) {

        Class<?>[] entityClasses = entityManager
                .getMetamodel()
                .getEntities()
                .stream()
                .map(Type::getJavaType)
                .toArray(Class<?>[]::new);

        config.exposeIdsFor(entityClasses);
    }
}
