package net.shyshkin.study.fullstack.ecommerce.mapper;

import net.shyshkin.study.fullstack.ecommerce.dto.CustomerDto;
import net.shyshkin.study.fullstack.ecommerce.entity.Customer;
import net.shyshkin.study.fullstack.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public abstract class CustomerMapperDecorator implements CustomerMapper {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    @Qualifier("delegate")
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    public Customer toEntity(CustomerDto customerDto) {

        return customerRepository
                .findByEmail(customerDto.getEmail())
                .orElse(customerMapper.toEntity(customerDto));
    }
}
