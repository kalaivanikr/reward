package com.assessment.reward.Reward.service;


import com.assessment.reward.Reward.entity.Customer;
import com.assessment.reward.Reward.entity.Transaction;
import com.assessment.reward.Reward.model.Rewards;

import java.util.List;

public interface RewardsService {
    Rewards getRewardsByCustomerId(Long customerId);

    List<Rewards> getAllRewards();

    List<Transaction> getAllTransactions();

    Customer findByCustomerId(Long customerId);
}