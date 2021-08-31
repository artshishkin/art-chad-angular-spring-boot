package net.shyshkin.study.fullstack.ecommerce.repository;

import net.shyshkin.study.fullstack.ecommerce.entity.ProductCategory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

//@CrossOrigin //from everywhere
//@CrossOrigin({"http://localhost:4200","http://angular-ecommerce:4200"})
@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    @Cacheable("productCategory:findById")
    @Override
    Optional<ProductCategory> findById(Long id);

    @Cacheable("productCategory:findAll:page")
    @Override
    Page<ProductCategory> findAll(Pageable pageable);

}
