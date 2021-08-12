package net.shyshkin.study.fullstack.ecommerce.entity;

import org.springframework.data.rest.core.config.Projection;

@Projection(
        name = "without-states",
        types = {Country.class})
public interface CountryWithoutStates {

    Integer getId();

    String getCode();

    String getName();
}
