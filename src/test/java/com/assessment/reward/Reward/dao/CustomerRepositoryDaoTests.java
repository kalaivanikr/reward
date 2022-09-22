package com.assessment.reward.Reward.dao;

import com.assessment.reward.Reward.entity.Customer;
import com.assessment.reward.Reward.repository.CustomerRepository;
import com.assessment.reward.Reward.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class CustomerRepositoryDaoTests {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(customerRepository).isNotNull();
    }

    @Test
    public void findAllCustomersTest() {
        Iterable<Customer> users = customerRepository.findAll();
        assertThat(users).hasSize(4);
    }

    @Test
    public void findByCustomerId() {
        Customer users = customerRepository.findByCustomerId(1001L);
        assertThat(users).isNotNull();
    }
}
