package net.shyshkin.study.fullstack.ecommerce.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PurchaseDto {

    private CustomerDto customer;
    private AddressDto shippingAddress;
    private AddressDto billingAddress;
    private OrderDto order;
    private Set<OrderItemDto> orderItems;
}
