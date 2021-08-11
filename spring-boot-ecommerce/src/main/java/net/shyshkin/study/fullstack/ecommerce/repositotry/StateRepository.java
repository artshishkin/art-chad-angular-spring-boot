package net.shyshkin.study.fullstack.ecommerce.repositotry;

import net.shyshkin.study.fullstack.ecommerce.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Integer> {

    @RestResource(path = "by-country-code")
    List<State> findByCountryCode(@Param("code") String code);

}
