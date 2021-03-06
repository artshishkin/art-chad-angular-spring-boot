package net.shyshkin.study.fullstack.ecommerce.mapper;

import net.shyshkin.study.fullstack.ecommerce.dto.OrderItemDto;
import net.shyshkin.study.fullstack.ecommerce.entity.OrderItem;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper
@DecoratedWith(OrderItemMapperDecorator.class)
public interface OrderItemMapper {

    OrderItem toEntity(OrderItemDto orderItemDto);

}
