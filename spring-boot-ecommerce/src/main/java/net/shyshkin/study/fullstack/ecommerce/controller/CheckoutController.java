package net.shyshkin.study.fullstack.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.shyshkin.study.fullstack.ecommerce.dto.PurchaseDto;
import net.shyshkin.study.fullstack.ecommerce.dto.PurchaseResponseDto;
import net.shyshkin.study.fullstack.ecommerce.service.CheckoutService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping("/purchase")
    public PurchaseResponseDto checkout(@RequestBody PurchaseDto purchase) {
        log.debug("Received request to store order:{}", purchase);
        return checkoutService.placeOrder(purchase);
    }
}
