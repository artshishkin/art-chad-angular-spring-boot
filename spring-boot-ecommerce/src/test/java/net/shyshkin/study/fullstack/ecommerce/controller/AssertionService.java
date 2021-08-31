package net.shyshkin.study.fullstack.ecommerce.controller;

import net.shyshkin.study.fullstack.ecommerce.dto.OrderItemDto;
import net.shyshkin.study.fullstack.ecommerce.dto.PurchaseDto;
import net.shyshkin.study.fullstack.ecommerce.entity.Customer;
import net.shyshkin.study.fullstack.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Service
public class AssertionService {

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public void assertThatPurchaseSuccessfullyPopulated(long expectedFinalSize, PurchaseDto purchase, String orderTrackingNumber) {
        List<Customer> customers = customerRepository.findAll();

        var productIds = purchase.getOrderItems().stream()
                .map(OrderItemDto::getProductId)
                .collect(Collectors.toSet());

        assertThat(customers)
                .hasSize((int) expectedFinalSize)
                .anySatisfy(customer -> assertThat(customer)
                        .hasNoNullFieldsOrProperties()
                        .hasFieldOrPropertyWithValue("firstName", purchase.getCustomer().getFirstName())
                        .hasFieldOrPropertyWithValue("lastName", purchase.getCustomer().getLastName())
                        .hasFieldOrPropertyWithValue("email", purchase.getCustomer().getEmail())
                        .satisfies(c -> assertThat(c.getOrders())
                                .hasSizeGreaterThanOrEqualTo(1)
                                .anySatisfy(order -> assertAll(
                                        () -> assertThat(order.getOrderTrackingNumber()).isEqualTo(orderTrackingNumber),
                                        () -> assertThat(order)
                                                .hasNoNullFieldsOrPropertiesExcept("status")
                                                .hasFieldOrPropertyWithValue("totalPrice", purchase.getOrder().getTotalPrice())
                                                .hasFieldOrPropertyWithValue("totalQuantity", purchase.getOrder().getTotalQuantity()),
                                        () -> assertThat(order.getShippingAddress())
                                                .hasNoNullFieldsOrPropertiesExcept("order")
                                                .hasFieldOrPropertyWithValue("city", purchase.getShippingAddress().getCity())
                                                .hasFieldOrPropertyWithValue("country", purchase.getShippingAddress().getCountry())
                                                .hasFieldOrPropertyWithValue("state", purchase.getShippingAddress().getState())
                                                .hasFieldOrPropertyWithValue("street", purchase.getShippingAddress().getStreet())
                                                .hasFieldOrPropertyWithValue("zipCode", purchase.getShippingAddress().getZipCode()),
                                        () -> assertThat(order.getBillingAddress())
                                                .hasNoNullFieldsOrPropertiesExcept("order")
                                                .hasFieldOrPropertyWithValue("city", purchase.getBillingAddress().getCity())
                                                .hasFieldOrPropertyWithValue("country", purchase.getBillingAddress().getCountry())
                                                .hasFieldOrPropertyWithValue("state", purchase.getBillingAddress().getState())
                                                .hasFieldOrPropertyWithValue("street", purchase.getBillingAddress().getStreet())
                                                .hasFieldOrPropertyWithValue("zipCode", purchase.getBillingAddress().getZipCode()),
                                        () -> assertThat(order.getOrderItems())
                                                .hasSize(purchase.getOrderItems().size())
                                                .allSatisfy(orderItem -> assertThat(orderItem)
                                                        .hasNoNullFieldsOrProperties())
                                                .allSatisfy(orderItem -> assertThat(orderItem.getProduct().getId()).isIn(productIds))
                                ))
                        ));
    }
}
