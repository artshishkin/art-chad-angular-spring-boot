package net.shyshkin.study.fullstack.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.shyshkin.study.fullstack.ecommerce.dto.*;
import net.shyshkin.study.fullstack.ecommerce.repositotry.CustomerRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
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

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AssertionService assertionService;

    @ParameterizedTest
    @ValueSource(strings = {"random", "json"})
    void checkout(String purchaseGenerationType) {
        //given
        long initialSize = customerRepository.count();

        final PurchaseDto purchase =
                "random".equals(purchaseGenerationType) ? generateRandomPurchase() :
                        "json".equals(purchaseGenerationType) ? generatePurchaseFromJson() :
                                null;
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
        String orderTrackingNumber = responseEntity.getBody().getOrderTrackingNumber();
        assertionService.assertThatPurchaseSuccessfullyPopulated(initialSize, purchase, orderTrackingNumber);
    }

    private PurchaseDto generateRandomPurchase() {
        //purchase
        PurchaseDto purchase = new PurchaseDto();

        //order
        OrderDto order = new OrderDto();
        order.setTotalQuantity(3);
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

    @SneakyThrows
    private PurchaseDto generatePurchaseFromJson() {

        String samplePurchaseJson = "{\n" +
                "   \"customer\":{\n" +
                "      \"firstName\":\"afasa\",\n" +
                "      \"lastName\":\"afasa\",\n" +
                "      \"email\":\"afasa@test.com\"\n" +
                "   },\n" +
                "   \"shippingAddress\":{\n" +
                "      \"street\":\"afasa\",\n" +
                "      \"city\":\"afasa\",\n" +
                "      \"state\":\"Alberta\",\n" +
                "      \"country\":\"Canada\",\n" +
                "      \"zipCode\":\"afasa\"\n" +
                "   },\n" +
                "   \"billingAddress\":{\n" +
                "      \"street\":\"fsfsf\",\n" +
                "      \"city\":\"sfdsf\",\n" +
                "      \"state\":\"Acre\",\n" +
                "      \"country\":\"Brazil\",\n" +
                "      \"zipCode\":\"19111\"\n" +
                "   },\n" +
                "   \"order\":{\n" +
                "      \"totalPrice\":36.98,\n" +
                "      \"totalQuantity\":2\n" +
                "   },\n" +
                "   \"orderItems\":[\n" +
                "      {\n" +
                "         \"imageUrl\":\"assets/images/products/coffeemugs/coffeemug-luv2code-1000.png\",\n" +
                "         \"quantity\":1,\n" +
                "         \"unitPrice\":18.99,\n" +
                "         \"productId\":26\n" +
                "      },\n" +
                "      {\n" +
                "         \"imageUrl\":\"assets/images/products/mousepads/mousepad-luv2code-1000.png\",\n" +
                "         \"quantity\":1,\n" +
                "         \"unitPrice\":17.99,\n" +
                "         \"productId\":51\n" +
                "      }\n" +
                "   ]\n" +
                "}";

        return objectMapper.readValue(samplePurchaseJson, PurchaseDto.class);
    }

}