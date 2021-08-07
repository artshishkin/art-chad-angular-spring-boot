package net.shyshkin.study.fullstack.ecommerce.repositotry;

import net.shyshkin.study.fullstack.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

//@CrossOrigin //from everywhere
//@CrossOrigin({"http://localhost:4200","http://angular-ecommerce:4200"})
public interface ProductRepository extends JpaRepository<Product, Long> {


    Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);

}
