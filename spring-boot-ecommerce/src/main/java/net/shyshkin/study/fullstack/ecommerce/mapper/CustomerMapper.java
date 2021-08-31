package net.shyshkin.study.fullstack.ecommerce.mapper;

import net.shyshkin.study.fullstack.ecommerce.dto.CustomerDto;
import net.shyshkin.study.fullstack.ecommerce.entity.Customer;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper
@DecoratedWith(CustomerMapperDecorator.class)
public interface CustomerMapper {

    Customer toEntity(CustomerDto customerDto);

}
