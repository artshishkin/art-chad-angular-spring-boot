package net.shyshkin.study.fullstack.ecommerce.repositotry;

import net.shyshkin.study.fullstack.ecommerce.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    ProductRepository repository;

    @Test
    void getById() {
        //given
        long id = 2L;

        //when
        Optional<Product> byId = repository.findById(id);

        //then
        assertThat(byId)
                .hasValueSatisfying(product -> assertThat(product)
                        .isNotNull()
                        .hasFieldOrPropertyWithValue("id", 2L)
                        .hasFieldOrPropertyWithValue("sku", "BOOK-TECH-1001")
                        .hasFieldOrPropertyWithValue("name", "Spring Framework Tutorial")
                        .satisfies(pr -> assertThat(pr.getProductCategory())
                                .isNotNull()
                                .hasNoNullFieldsOrProperties()
                                .hasFieldOrPropertyWithValue("categoryName", "BOOKS")
                        )
                );
    }
}