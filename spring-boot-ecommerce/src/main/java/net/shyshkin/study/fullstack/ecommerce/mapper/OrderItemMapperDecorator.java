package net.shyshkin.study.fullstack.ecommerce.mapper;

import net.shyshkin.study.fullstack.ecommerce.dto.OrderItemDto;
import net.shyshkin.study.fullstack.ecommerce.entity.OrderItem;
import net.shyshkin.study.fullstack.ecommerce.entity.Product;
import net.shyshkin.study.fullstack.ecommerce.repositotry.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class OrderItemMapperDecorator implements OrderItemMapper {

    private ProductRepository productRepository;
    private OrderItemMapper orderItemMapper;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    @Qualifier("delegate")
    public void setOrderItemMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    public OrderItem toEntity(OrderItemDto orderItemDto) {

        OrderItem orderItem = orderItemMapper.toEntity(orderItemDto);
        Product product = productRepository.getById(orderItemDto.getProductId());
        orderItem.setProduct(product);
        return orderItem;
    }
}
