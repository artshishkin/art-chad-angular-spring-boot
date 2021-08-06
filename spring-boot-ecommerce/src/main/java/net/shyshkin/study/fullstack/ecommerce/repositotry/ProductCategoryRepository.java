package net.shyshkin.study.fullstack.ecommerce.repositotry;

import net.shyshkin.study.fullstack.ecommerce.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin //from everywhere
//@CrossOrigin({"http://localhost:4200","http://angular-ecommerce:4200"})
@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
