package net.shyshkin.study.fullstack.ecommerce.controller;

import lombok.extern.slf4j.Slf4j;
import net.shyshkin.study.fullstack.ecommerce.dto.*;
import net.shyshkin.study.fullstack.ecommerce.entity.Customer;
import net.shyshkin.study.fullstack.ecommerce.repositotry.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ActiveProfiles({"local", "no_cache"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CheckoutControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void checkout() {
        //given
        long initialSize = customerRepository.count();

        PurchaseDto purchase = generateRandomPurchase();

        //when
        var responseEntity = testRestTemplate
                .postForEntity("/api/checkout/purchase", purchase, PurchaseResponseDto.class);

        //then
        log.debug("Response entity: {}", responseEntity);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody())
                .isNotNull()
                .hasNoNullFieldsOrProperties()
                .satisfies(dto -> assertThat(dto.getOrderTrackingNumber()).isNotBlank());

        List<Customer> customers = customerRepository.findAll();
        assertThat(customers)
                .hasSize((int) (initialSize + 1))
                .anySatisfy(customer -> assertThat(customer)
                        .hasNoNullFieldsOrProperties()
                        .hasFieldOrPropertyWithValue("firstName", purchase.getCustomer().getFirstName())
                        .hasFieldOrPropertyWithValue("lastName", purchase.getCustomer().getLastName())
                        .hasFieldOrPropertyWithValue("email", purchase.getCustomer().getEmail())
                        .satisfies(c -> assertThat(c.getOrders())
                                .hasSize(1)
                                .allSatisfy(order -> assertThat(order.getOrderItems())
                                        .hasSize(3)
                                        .allSatisfy(orderItem -> assertThat(orderItem)
                                                .hasNoNullFieldsOrProperties())))
                );
    }

    private PurchaseDto generateRandomPurchase() {
        //purchase
        PurchaseDto purchase = new PurchaseDto();

        //order
        OrderDto order = new OrderDto();
        order.setTotalQuantity(3);
        order.setStatus("OK");
        order.setTotalPrice(new BigDecimal("123.45"));

        purchase.setOrder(order);

        //billingAddress and shippingAddress
        AddressDto address = new AddressDto();
        address.setCity("City 1");
        address.setCountry("Country 1");
        address.setState("State 1");
        address.setStreet("Street 1");
        address.setZipCode("12345");

        purchase.setShippingAddress(address);
        purchase.setBillingAddress(address);

        //customer
        CustomerDto customer = new CustomerDto();
        customer.setEmail(UUID.randomUUID() + "email@example.com");
        customer.setFirstName("First Name " + UUID.randomUUID());
        customer.setLastName("Last Name 1" + UUID.randomUUID());

        purchase.setCustomer(customer);

        Set<OrderItemDto> orderItems =
                LongStream.rangeClosed(1, 3).boxed()
                        .map(i ->
                                OrderItemDto.builder()
                                        .imageUrl("image Url " + i)
                                        .productId(i)
                                        .quantity(1)
                                        .unitPrice(new BigDecimal("11.99"))
                                        .build())
                        .collect(Collectors.toSet());

        purchase.setOrderItems(orderItems);

        return purchase;
    }
}