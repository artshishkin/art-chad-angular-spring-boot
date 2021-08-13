package net.shyshkin.study.fullstack.ecommerce.service;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.fullstack.ecommerce.dto.PurchaseDto;
import net.shyshkin.study.fullstack.ecommerce.dto.PurchaseResponseDto;
import net.shyshkin.study.fullstack.ecommerce.repositotry.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final CustomerRepository customerRepository;

    @Override
    public PurchaseResponseDto placeOrder(PurchaseDto purchase) {
        return null;
    }
}
