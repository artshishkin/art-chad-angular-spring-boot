package net.shyshkin.study.fullstack.ecommerce.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDto {

    private BigDecimal totalPrice;
    private Integer totalQuantity;
    private String status;

}
