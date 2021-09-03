package net.shyshkin.study.fullstack.ecommerce.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ActiveProfiles({"local", "no_cache"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "server.ssl.enabled=false"
})
class GetOrdersSecurityTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    private final static String defaultUserEmail = String.format("user%s@example.com", UUID.randomUUID());

    @Test
    void checkout_unauthorized() {
        //given

        //when
        var responseEntity = testRestTemplate
                .getForEntity("/api/orders/search/by-customer-email?email={email}", String.class, defaultUserEmail);

        //then
        log.debug("Response entity: {}", responseEntity);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(responseEntity.getBody())
                .isBlank();
//                .isNotNull()
//                .isEqualTo("401 Unauthorized");
    }
}