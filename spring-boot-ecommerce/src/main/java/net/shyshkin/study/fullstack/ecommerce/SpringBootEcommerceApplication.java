package net.shyshkin.study.fullstack.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(exclude = {RedissonAutoConfiguration.class})
public class SpringBootEcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEcommerceApplication.class, args);
    }

}
