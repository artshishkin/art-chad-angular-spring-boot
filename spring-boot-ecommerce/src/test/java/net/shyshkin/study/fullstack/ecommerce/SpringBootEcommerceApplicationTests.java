package net.shyshkin.study.fullstack.ecommerce;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"local","no_cache"})
class SpringBootEcommerceApplicationTests {

    @Test
    void contextLoads() {
    }

}
