package net.shyshkin.study.fullstack.ecommerce.repositotry;

import lombok.extern.slf4j.Slf4j;
import net.shyshkin.study.fullstack.ecommerce.entity.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
@ActiveProfiles({"local","no_cache"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductCategoryRepositoryTest {

    @Autowired
    ProductCategoryRepository repository;

    @Test
    void getById() {
        //given
        long id = 1L;

        //when
        Optional<ProductCategory> byId = repository.findById(id);

        //then
        assertThat(byId)
                .hasValueSatisfying(pc -> assertThat(pc)
                        .isNotNull()
                        .hasFieldOrPropertyWithValue("id", 1L)
                        .hasFieldOrPropertyWithValue("categoryName", "Books")
                        .satisfies(category -> assertThat(category.getProducts())
                                .isNotNull()
                                .hasSize(25)
                                .allSatisfy(product -> assertThat(product)
                                        .hasNoNullFieldsOrPropertiesExcept("lastUpdated")
                                        .satisfies(pr -> log.debug("{}", pr)))
                        )
                );
    }

}