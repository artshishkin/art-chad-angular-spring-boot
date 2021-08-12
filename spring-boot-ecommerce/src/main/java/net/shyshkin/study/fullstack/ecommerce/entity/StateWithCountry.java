package net.shyshkin.study.fullstack.ecommerce.entity;

import org.springframework.data.rest.core.config.Projection;

@Projection(
        name = "with-country",
        types = {State.class})
public interface StateWithCountry {

    Integer getId();

    String getName();

    Country getCountry();
}
