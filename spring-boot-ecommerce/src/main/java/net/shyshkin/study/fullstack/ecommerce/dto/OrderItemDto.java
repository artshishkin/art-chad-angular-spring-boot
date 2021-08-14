package net.shyshkin.study.fullstack.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto {

    private String imageUrl;
    private Integer quantity;
    private BigDecimal unitPrice;
    private Long productId;

}
