package net.shyshkin.study.fullstack.ecommerce.dto;

import lombok.Data;


@Data
public class AddressDto {
    private String city;
    private String country;
    private String state;
    private String street;
    private String zipCode;
}
