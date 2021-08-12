package net.shyshkin.study.fullstack.ecommerce.entity;

import org.springframework.data.rest.core.config.Projection;

@Projection(
        name = "without-country",
        types = {State.class})
public interface StateWithoutCountry {

    Integer getId();

    String getName();
}
