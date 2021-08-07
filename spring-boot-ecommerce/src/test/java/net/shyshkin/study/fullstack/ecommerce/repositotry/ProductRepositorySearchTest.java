package net.shyshkin.study.fullstack.ecommerce.repositotry;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ActiveProfiles("local")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductRepositorySearchTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void getAllowed() {

        //given
        long categoryId = 1L;

        //when
//    http://localhost:8080/api/products/search/findByCategoryId?id=1
        var responseEntity = testRestTemplate.exchange("/api/products/search/findByCategoryId?id={categoryId}", HttpMethod.GET, null, String.class, categoryId);

        //then
        log.debug("ResponseEntity: {}", responseEntity);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody())
                .isNotNull()
                .contains("/api/products/1", "/api/products/20");
    }
}