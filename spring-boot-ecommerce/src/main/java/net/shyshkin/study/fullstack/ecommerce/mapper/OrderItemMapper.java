package net.shyshkin.study.fullstack.ecommerce.mapper;

import net.shyshkin.study.fullstack.ecommerce.dto.OrderItemDto;
import net.shyshkin.study.fullstack.ecommerce.entity.OrderItem;
import org.mapstruct.Mapper;

@Mapper
public interface OrderItemMapper {

    OrderItem toEntity(OrderItemDto orderItemDto);

}
