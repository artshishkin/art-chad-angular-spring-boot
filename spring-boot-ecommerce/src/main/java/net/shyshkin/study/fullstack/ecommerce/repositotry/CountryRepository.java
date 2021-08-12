package net.shyshkin.study.fullstack.ecommerce.repositotry;

import net.shyshkin.study.fullstack.ecommerce.entity.Country;
import net.shyshkin.study.fullstack.ecommerce.entity.CountryWithoutStates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = CountryWithoutStates.class)
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
