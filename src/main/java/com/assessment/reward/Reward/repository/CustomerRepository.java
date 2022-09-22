package com.assessment.reward.Reward.repository;

import com.assessment.reward.Reward.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByCustomerId(Long customerId);

    List<Customer> findAll();
}