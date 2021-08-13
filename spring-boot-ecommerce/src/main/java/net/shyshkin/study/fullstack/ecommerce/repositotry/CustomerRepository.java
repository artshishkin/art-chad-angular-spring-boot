package net.shyshkin.study.fullstack.ecommerce.repositotry;

import net.shyshkin.study.fullstack.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
