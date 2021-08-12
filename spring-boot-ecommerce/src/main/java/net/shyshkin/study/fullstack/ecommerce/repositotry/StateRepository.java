package net.shyshkin.study.fullstack.ecommerce.repositotry;

import net.shyshkin.study.fullstack.ecommerce.entity.State;
import net.shyshkin.study.fullstack.ecommerce.entity.StateWithoutCountry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(excerptProjection = StateWithoutCountry.class)
public interface StateRepository extends JpaRepository<State, Integer> {

    @Override
    @RestResource(exported = false)
    List<State> findAll();

    @Override
    @RestResource(exported = false)
    Page<State> findAll(Pageable pageable);

//    @RestResource(path = "by-country-code")
//    List<State> findByCountryCode(@Param("code") String code);

}
