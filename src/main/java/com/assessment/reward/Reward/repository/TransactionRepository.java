package com.assessment.reward.Reward.repository;

import java.sql.Timestamp;
import java.util.List;

import com.assessment.reward.Reward.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findAllByCustomerId(Long customerId);

    List<Transaction> findAllByCustomerIdAndTransactionDateBetween(Long customerId, Timestamp from, Timestamp to);

    List<Transaction> findByTransactionDateBetween(Timestamp from, Timestamp to);

    List<Transaction> findAll();
}