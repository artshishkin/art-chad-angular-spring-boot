package net.shyshkin.study.fullstack.ecommerce.mapper;

import net.shyshkin.study.fullstack.ecommerce.dto.OrderDto;
import net.shyshkin.study.fullstack.ecommerce.entity.Order;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {

    Order toEntity(OrderDto orderDto);
}
