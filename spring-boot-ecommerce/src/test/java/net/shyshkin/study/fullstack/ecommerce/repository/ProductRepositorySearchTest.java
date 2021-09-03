package net.shyshkin.study.fullstack.ecommerce.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ActiveProfiles({"local","no_cache"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "server.ssl.enabled=false"
})
class ProductRepositorySearchTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void findByCategoryId() {

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

    @Test
    void findByNameContaining() {

        //given
        String keyword = "python";

        //when
//    http://localhost:8080/api/products/search/findByNameContaining?name=python
        var responseEntity = testRestTemplate.exchange("/api/products/search/findByNameContaining?name={keyword}", HttpMethod.GET, null, String.class, keyword);

        //then
        log.debug("ResponseEntity: {}", responseEntity);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody())
                .isNotNull()
                .contains("\"Crash Course in Python\"", "\"Introduction to Python\"");
    }
}