package net.shyshkin.study.fullstack.ecommerce.service;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.fullstack.ecommerce.dto.PurchaseDto;
import net.shyshkin.study.fullstack.ecommerce.dto.PurchaseResponseDto;
import net.shyshkin.study.fullstack.ecommerce.entity.Customer;
import net.shyshkin.study.fullstack.ecommerce.entity.Order;
import net.shyshkin.study.fullstack.ecommerce.mapper.AddressMapper;
import net.shyshkin.study.fullstack.ecommerce.mapper.CustomerMapper;
import net.shyshkin.study.fullstack.ecommerce.mapper.OrderItemMapper;
import net.shyshkin.study.fullstack.ecommerce.mapper.OrderMapper;
import net.shyshkin.study.fullstack.ecommerce.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final CustomerRepository customerRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final AddressMapper addressMapper;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    public PurchaseResponseDto placeOrder(PurchaseDto purchase) {

        // retrieve the order from the dto
        Order order = orderMapper.toEntity(purchase.getOrder());

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with orderItems
        purchase.getOrderItems()
                .stream()
                .map(orderItemMapper::toEntity)
                .forEach(order::addOrderItem);

        // populate order with billingAddress and shippingAddress
        order.setBillingAddress(addressMapper.toEntity(purchase.getBillingAddress()));
        order.setShippingAddress(addressMapper.toEntity(purchase.getShippingAddress()));

        // populate customer with order
        Customer customer = customerMapper.toEntity(purchase.getCustomer());
        customer.addOrder(order);

        // save to the database
        customerRepository.save(customer);

        // return a response
        return new PurchaseResponseDto(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
