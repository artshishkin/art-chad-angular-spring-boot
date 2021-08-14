package net.shyshkin.study.fullstack.ecommerce.controller;

import net.shyshkin.study.fullstack.ecommerce.dto.PurchaseDto;
import net.shyshkin.study.fullstack.ecommerce.entity.Customer;
import net.shyshkin.study.fullstack.ecommerce.repositotry.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Service
public class AssertionService {

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public void assertThatPurchaseSuccessfullyPopulated(long initialSize, PurchaseDto purchase, String orderTrackingNumber) {
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
                                .allSatisfy(order -> assertThat(order.getOrderTrackingNumber()).isEqualTo(orderTrackingNumber))
                                .allSatisfy(order -> assertThat(order.getOrderItems())
                                        .hasSize(purchase.getOrderItems().size())
                                        .allSatisfy(orderItem -> assertThat(orderItem)
                                                .hasNoNullFieldsOrProperties())))
                );
    }
}
