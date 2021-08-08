package net.shyshkin.study.fullstack.ecommerce.eventhandlers;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.fullstack.ecommerce.entity.ProductCategory;
import org.springframework.cache.CacheManager;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import java.util.Optional;

@RepositoryEventHandler
@RequiredArgsConstructor
public class ProductCategoryEventHandler {

    private final CacheManager cacheManager;

    @HandleAfterCreate
    @HandleAfterSave
    @HandleAfterDelete
    public void handleCachesEviction(ProductCategory entity) {

        Optional.ofNullable(cacheManager.getCache("productCategory:findById"))
                .ifPresent(c -> c.evict(entity.getId()));

        Optional.ofNullable(cacheManager.getCache("productCategory:findAll:page"))
                .ifPresent(c -> c.clear());
    }

}
