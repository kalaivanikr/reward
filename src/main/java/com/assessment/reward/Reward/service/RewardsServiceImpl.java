package com.assessment.reward.Reward.service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.assessment.reward.Reward.constants.RewardConstants;
import com.assessment.reward.Reward.entity.Customer;
import com.assessment.reward.Reward.entity.Transaction;
import com.assessment.reward.Reward.model.Rewards;
import com.assessment.reward.Reward.repository.CustomerRepository;
import com.assessment.reward.Reward.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RewardsServiceImpl implements RewardsService {

    @Value("${total.days.in.month:30}")
    private Integer monthlyDays;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Rewards getRewardsByCustomerId(Long customerId) {

        Timestamp lastMonthTimestamp = getDateBasedOnOffSetDays(monthlyDays);

        Timestamp lastSecondMonthTimestamp = getDateBasedOnOffSetDays(2 * monthlyDays);

        Timestamp lastThirdMonthTimestamp = getDateBasedOnOffSetDays(3 * monthlyDays);

        List<Transaction> lastMonthTransactions = transactionRepository.findAllByCustomerIdAndTransactionDateBetween(
                customerId, lastMonthTimestamp, Timestamp.from(Instant.now()));

        List<Transaction> lastSecondMonthTransactions = transactionRepository
                .findAllByCustomerIdAndTransactionDateBetween(customerId, lastSecondMonthTimestamp, lastMonthTimestamp);

        List<Transaction> lastThirdMonthTransactions = transactionRepository
                .findAllByCustomerIdAndTransactionDateBetween(customerId, lastThirdMonthTimestamp,
                        lastSecondMonthTimestamp);

        Long lastMonthRewardPoints = getRewardsPerMonth(lastMonthTransactions);

        Long lastSecondMonthRewardPoints = getRewardsPerMonth(lastSecondMonthTransactions);

        Long lastThirdMonthRewardPoints = getRewardsPerMonth(lastThirdMonthTransactions);

        Rewards customerRewards = new Rewards();
        customerRewards.setCustomerId(customerId);
        customerRewards.setLastMonthRewardPoints(lastMonthRewardPoints);
        customerRewards.setLastSecondMonthRewardPoints(lastSecondMonthRewardPoints);
        customerRewards.setLastThirdMonthRewardPoints(lastThirdMonthRewardPoints);
        customerRewards.setTotalRewards(lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints);

        return customerRewards;

    }

    private Long getRewardsPerMonth(List<Transaction> transactions) {
        return transactions.stream().map(this::calculateRewards).mapToLong(r -> r).sum();
    }

    private Long calculateRewards(Transaction t) {
        if (t.getTransactionAmount() > RewardConstants.firstRewardLimit && t.getTransactionAmount() <= RewardConstants.secondRewardLimit) {
            return Math.round(t.getTransactionAmount() - RewardConstants.firstRewardLimit);
        } else if (t.getTransactionAmount() > RewardConstants.secondRewardLimit) {
            return Math.round(t.getTransactionAmount() - RewardConstants.secondRewardLimit) * 2
                    + (RewardConstants.secondRewardLimit - RewardConstants.firstRewardLimit);
        } else
            return 0l;

    }

    public Timestamp getDateBasedOnOffSetDays(int days) {
        return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
    }

    @Override
    public List<Rewards> getAllRewards() {

        Timestamp lastMonthTimestamp = getDateBasedOnOffSetDays(monthlyDays);
        Timestamp lastSecondMonthTimestamp = getDateBasedOnOffSetDays(2 * monthlyDays);
        Timestamp lastThirdMonthTimestamp = getDateBasedOnOffSetDays(3 * monthlyDays);

        List<Transaction> lastMonthTransactions = transactionRepository.findByTransactionDateBetween(
                lastMonthTimestamp, Timestamp.from(Instant.now()));

        List<Transaction> lastSecondMonthTransactions = transactionRepository
                .findByTransactionDateBetween(lastSecondMonthTimestamp, lastMonthTimestamp);

        List<Transaction> lastThirdMonthTransactions = transactionRepository
                .findByTransactionDateBetween(lastThirdMonthTimestamp,
                        lastSecondMonthTimestamp);

        Map<Timestamp, List<Transaction>> lastMonthCustIdTransListMap
                = lastMonthTransactions.stream().collect(Collectors.groupingBy(Transaction::getTransactionDate));

        Map<Timestamp, List<Transaction>> lastSecondMonthCustIdTransListMap
                = lastMonthTransactions.stream().collect(Collectors.groupingBy(Transaction::getTransactionDate));


        Map<Timestamp, List<Transaction>> lastThirdMonthCustIdTransListMap
                = lastMonthTransactions.stream().collect(Collectors.groupingBy(Transaction::getTransactionDate));
        List<Rewards> rewardsList = new ArrayList<>();
        lastMonthCustIdTransListMap.forEach((k, v) -> {
            if (v != null) {
                v.forEach(rec -> {
                    Rewards r = new Rewards();
                    r.setCustomerId(rec.getCustomerId());
                    r.setLastMonthRewardPoints(calculateRewards(rec));
                    r.setLastSecondMonthRewardPoints(calculateRewards(rec));
                    r.setLastThirdMonthRewardPoints(calculateRewards(rec));
                    r.setLastMonthRewardPoints(calculateRewards(rec));
                    rewardsList.add(r);
                });
            }
        });

        return rewardsList;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Customer findByCustomerId(Long customerId) {
        return customerRepository.findByCustomerId(customerId);
    }
}