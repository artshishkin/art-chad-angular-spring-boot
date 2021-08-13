package net.shyshkin.study.fullstack.ecommerce.mapper;

import net.shyshkin.study.fullstack.ecommerce.dto.AddressDto;
import net.shyshkin.study.fullstack.ecommerce.entity.Address;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {

    Address toEntity(AddressDto addressDto);
}
