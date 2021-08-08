package net.shyshkin.study.fullstack.ecommerce.config;

import lombok.extern.slf4j.Slf4j;
import net.shyshkin.study.fullstack.ecommerce.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ActiveProfiles({"local","no_cache"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MyDataRestConfigTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void getAllowed() {

        //given
        long id = 1L;

        //when
        ResponseEntity<Product> responseEntity = testRestTemplate.exchange("/api/products/{id}", HttpMethod.GET, null, Product.class, id);

        //then
        log.debug("ResponseEntity: {}", responseEntity);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody())
                .isNotNull()
                .hasNoNullFieldsOrPropertiesExcept("lastUpdated", "id", "category");
    }

    @Test
    void postNotAllowed() {

        //given
        Product product = Product.builder().name("Some name").build();
        RequestEntity<Product> requestEntity = RequestEntity
                .post("/api/products")
                .body(product);

        //when
        ResponseEntity<Object> responseEntity = testRestTemplate.exchange(requestEntity, Object.class);

        //then
        log.debug("ResponseEntity: {}", responseEntity);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Test
    void putNotAllowed() {

        //given
        long id = 1L;
        Product product = Product.builder().name("Some name").build();
        RequestEntity<Product> requestEntity = RequestEntity
                .put("/api/products/{id}", id)
                .body(product);

        //when
        ResponseEntity<Object> responseEntity = testRestTemplate.exchange(requestEntity, Object.class);

        //then
        log.debug("ResponseEntity: {}", responseEntity);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Test
    void deleteNotAllowed() {

        //given
        long id = 1L;

        //when
        ResponseEntity<Object> responseEntity = testRestTemplate.exchange("/api/products/{id}", HttpMethod.DELETE, null, Object.class, id);

        //then
        log.debug("ResponseEntity: {}", responseEntity);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Test
    void exposeIdTest() {

        //given
        long id = 1L;

        //when
        ResponseEntity<Product> responseEntity = testRestTemplate.exchange("/api/products/{id}", HttpMethod.GET, null, Product.class, id);

        //then
        log.debug("ResponseEntity: {}", responseEntity);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody())
                .isNotNull()
                .hasNoNullFieldsOrPropertiesExcept("lastUpdated", "category")
                .hasFieldOrPropertyWithValue("id", id);
    }


}