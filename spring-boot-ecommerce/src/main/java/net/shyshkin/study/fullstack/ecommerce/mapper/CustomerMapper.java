package net.shyshkin.study.fullstack.ecommerce.mapper;

import net.shyshkin.study.fullstack.ecommerce.dto.CustomerDto;
import net.shyshkin.study.fullstack.ecommerce.entity.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer toEntity(CustomerDto customerDto);

}
