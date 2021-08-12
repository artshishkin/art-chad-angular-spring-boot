package net.shyshkin.study.fullstack.ecommerce.entity;

import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(
        name = "with-states",
        types = {Country.class})
public interface CountryWithStates {

    Integer getId();

    String getCode();

    String getName();

    Set<State> getStates();

}
