package net.shyshkin.study.fullstack.ecommerce.service;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.fullstack.ecommerce.dto.PurchaseDto;
import net.shyshkin.study.fullstack.ecommerce.dto.PurchaseResponseDto;
import net.shyshkin.study.fullstack.ecommerce.entity.Order;
import net.shyshkin.study.fullstack.ecommerce.mapper.OrderMapper;
import net.shyshkin.study.fullstack.ecommerce.repositotry.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final CustomerRepository customerRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public PurchaseResponseDto placeOrder(PurchaseDto purchase) {

        // retrieve the order from the dto
        Order order = orderMapper.toEntity(purchase.getOrder());

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with orderItems

        // populate order with billingAddress and shippingAddress

        // populate customer with order

        // save to the database

        // return a response

        return null;
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
