package net.shyshkin.study.fullstack.ecommerce.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {

    private String imageUrl;
    private Integer quantity;
    private BigDecimal unitPrice;
    private Long productId;

}
