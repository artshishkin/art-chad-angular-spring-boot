package net.shyshkin.study.fullstack.ecommerce.repository;

import net.shyshkin.study.fullstack.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

}
