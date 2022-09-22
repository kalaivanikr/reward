package com.assessment.reward.Reward.dao;

import com.assessment.reward.Reward.entity.Transaction;
import com.assessment.reward.Reward.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class TransactionRepositoryDaoTests {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void testFindAllCustomers() {
        Iterable<Transaction> users = transactionRepository.findAll();
        assertThat(users).hasSize(24);
    }

    @Test
    public void testFindAllByCustomerIdAndTransactionDateBetween() {
        Iterable<Transaction> users = transactionRepository.findAllByCustomerIdAndTransactionDateBetween(1001L,
                Timestamp.valueOf("2022-09-12 00:00:00"), Timestamp.valueOf("2022-08-01 00:00:00"));
        assertThat(users).hasSize(0);
    }


    @Test
    public void testFindByTransactionDateBetween() {
        Iterable<Transaction> users = transactionRepository.findByTransactionDateBetween(Timestamp.valueOf("2022-09-12 00:00:00"), Timestamp.valueOf("2022-08-01 00:00:00"));
        assertThat(users).hasSize(0);
    }

}
