package net.shyshkin.study.fullstack.ecommerce.service;

import net.shyshkin.study.fullstack.ecommerce.dto.PurchaseDto;
import net.shyshkin.study.fullstack.ecommerce.dto.PurchaseResponseDto;

public interface CheckoutService {

    PurchaseResponseDto placeOrder(PurchaseDto purchase);

}
