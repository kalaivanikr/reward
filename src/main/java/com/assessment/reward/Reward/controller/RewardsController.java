package com.assessment.reward.Reward.controller;

import com.assessment.reward.Reward.entity.Customer;
import com.assessment.reward.Reward.entity.Transaction;
import com.assessment.reward.Reward.model.Rewards;
import com.assessment.reward.Reward.repository.CustomerRepository;
import com.assessment.reward.Reward.repository.TransactionRepository;
import com.assessment.reward.Reward.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class RewardsController {

    @Autowired
    RewardsService rewardsService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping(value = "/{customerId}/rewards", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rewards> getRewardsByCustomerId(@PathVariable("customerId") Long customerId) {
        Customer customer = rewardsService.findByCustomerId(customerId);
        if (customer == null) {
            throw new RuntimeException("Invalid / Missing customer Id ");
        }
        Rewards customerRewards = rewardsService.getRewardsByCustomerId(customerId);
        return new ResponseEntity<>(customerRewards, HttpStatus.OK);
    }


    @GetMapping(value = "/rewards", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rewards> getAllRewards() {
        List<Rewards> rewardsList = rewardsService.getAllRewards();
        return new ResponseEntity(rewardsList, HttpStatus.OK);
    }

    @GetMapping(value = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactionList = rewardsService.getAllTransactions();
        return new ResponseEntity(transactionList, HttpStatus.OK);
    }

}