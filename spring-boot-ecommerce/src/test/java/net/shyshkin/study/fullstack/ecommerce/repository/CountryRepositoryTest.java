package net.shyshkin.study.fullstack.ecommerce.repository;

import lombok.extern.slf4j.Slf4j;
import net.shyshkin.study.fullstack.ecommerce.entity.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
@ActiveProfiles({"local", "no_cache"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CountryRepositoryTest {

    @Autowired
    CountryRepository repository;

    @Test
    void getById() {
        //given
        int id = 1;

        //when
        Optional<Country> byId = repository.findById(id);

        //then
        assertThat(byId)
                .hasValueSatisfying(pc -> assertThat(pc)
                        .isNotNull()
                        .hasFieldOrPropertyWithValue("id", id)
                        .hasFieldOrPropertyWithValue("code", "BR")
                        .hasFieldOrPropertyWithValue("name", "Brazil")
                        .satisfies(country -> assertThat(country.getStates())
                                .isNotNull()
                                .hasSizeGreaterThan(10)
                                .allSatisfy(state -> assertThat(state)
                                        .hasNoNullFieldsOrProperties()
                                        .satisfies(pr -> log.debug("{}", pr)))
                        )
                );
    }


}