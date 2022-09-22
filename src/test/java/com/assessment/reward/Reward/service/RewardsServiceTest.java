package com.assessment.reward.Reward.service;

import com.assessment.reward.Reward.entity.Customer;
import com.assessment.reward.Reward.entity.Transaction;
import com.assessment.reward.Reward.model.Rewards;
import com.assessment.reward.Reward.repository.CustomerRepository;
import com.assessment.reward.Reward.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Timestamp;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RewardsServiceTest {

    @InjectMocks
    private RewardsServiceImpl rewardsService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(rewardsService, "monthlyDays", 30);
    }

    @Test
    public void testGetRewardsByCustomerId() {
        Transaction transaction = new Transaction(10001L, 1001L, Timestamp.valueOf("2022-09-12 00:00:00"), 120);
        Transaction transaction1 = new Transaction(10002L, 1001L, Timestamp.valueOf("2022-08-01 00:00:00"), 85);
        Transaction transaction2 = new Transaction(10003L, 1001L, Timestamp.valueOf("2022-07-04 00:00:00"), 160);
        Transaction transaction3 = new Transaction(10004L, 1001L, Timestamp.valueOf("2022-06-01 00:00:00"), 90);
        Transaction transaction4 = new Transaction(10005L, 1001L, Timestamp.valueOf("2022-05-04 00:00:00"), 120);
        Transaction transaction5 = new Transaction(10006L, 1001L, Timestamp.valueOf("2022-04-05 00:00:00"), 165);

        Transaction transaction6 = new Transaction(10007L, 1002L, Timestamp.valueOf("2022-09-05 00:00:00"), 113);
        Transaction transaction7 = new Transaction(10008L, 1002L, Timestamp.valueOf("2022-08-27 00:00:00"), 80);
        Transaction transaction8 = new Transaction(10009L, 1002L, Timestamp.valueOf("2022-07-04 00:00:00"), 102);
        Transaction transaction9 = new Transaction(10010L, 1002L, Timestamp.valueOf("2022-06-01 00:00:00"), 210);
        Transaction transaction10 = new Transaction(10011L, 1002L, Timestamp.valueOf("2022-05-27 00:00:00"), 130);
        Transaction transaction11 = new Transaction(10012L, 1002L, Timestamp.valueOf("2022-04-15 00:00:00"), 88);
        Transaction transaction12 = new Transaction(10013L, 1003L, Timestamp.valueOf("2022-09-05 00:00:00"), 102);
        Transaction transaction13 = new Transaction(10014L, 1003L, Timestamp.valueOf("2022-08-27 00:00:00"), 84);
        Transaction transaction14 = new Transaction(10015L, 1003L, Timestamp.valueOf("2022-07-04 00:00:00"), 200);
        Transaction transaction15 = new Transaction(10016L, 1003L, Timestamp.valueOf("2022-06-01 00:00:00"), 103);
        Transaction transaction16 = new Transaction(10017L, 1003L, Timestamp.valueOf("2022-05-27 00:00:00"), 500);
        Transaction transaction17 = new Transaction(10018L, 1003L, Timestamp.valueOf("2022-04-20 00:00:00"), 585);
        Transaction transaction18 = new Transaction(10019L, 1004L, Timestamp.valueOf("2022-09-14 00:00:00"), 102);
        Transaction transaction19 = new Transaction(10020L, 1004L, Timestamp.valueOf("2022-08-14 00:00:00"), 84);
        Transaction transaction20 = new Transaction(10021L, 1004L, Timestamp.valueOf("2022-07-14 00:00:00"), 200);
        Transaction transaction21 = new Transaction(10022L, 1004L, Timestamp.valueOf("2022-06-14 00:00:00"), 103);
        Transaction transaction22 = new Transaction(10023L, 1004L, Timestamp.valueOf("2022-05-14 00:00:00"), 500);
        Transaction transaction23 = new Transaction(10024L, 1004L, Timestamp.valueOf("2022-04-14 00:00:00"), 585);

        List<Transaction> transactionList = List.of(transaction, transaction1, transaction2, transaction3, transaction4, transaction5,
                transaction6, transaction7, transaction8, transaction9, transaction10,
                transaction11, transaction12, transaction13, transaction14, transaction15,
                transaction16, transaction17, transaction18, transaction19, transaction20,
                transaction21, transaction22, transaction23);
        when(transactionRepository.findAllByCustomerIdAndTransactionDateBetween(anyLong(), any(), any())).thenReturn(transactionList);
        Rewards rewardsByCustomerId = rewardsService.getRewardsByCustomerId(1004L);
        assertNotNull(rewardsByCustomerId);
        assertEquals(5711, rewardsByCustomerId.getLastMonthRewardPoints());
        assertEquals(5711, rewardsByCustomerId.getLastSecondMonthRewardPoints());
        assertEquals(5711, rewardsByCustomerId.getLastThirdMonthRewardPoints());
        assertEquals(17133, rewardsByCustomerId.getTotalRewards());

    }

    @Test
    public void testAllTransactions() {
        Transaction t1 = new Transaction(10001l, 1001l, Timestamp.valueOf("2022-09-12 00:00:00"), 120);
        Transaction t2 = new Transaction(10002l, 1001l, Timestamp.valueOf("2022-08-01 00:00:00"), 85);
        Transaction t3 = new Transaction(10003l, 1001l, Timestamp.valueOf("2022-07-04 00:00:00"), 160);
        List<Transaction> transactionList = List.of(t1, t2, t3);
        when(rewardsService.getAllTransactions()).thenReturn(transactionList);
        List<Transaction> allTransactions = rewardsService.getAllTransactions();
        assertThat(!allTransactions.isEmpty());
        assertEquals(3, transactionList.size());
    }

    @Test
    public void testFindByCustomerId() {
        Customer customer = new Customer(1001L, "Customer1");
        when(rewardsService.findByCustomerId(anyLong())).thenReturn(customer);
        Customer customerId = rewardsService.findByCustomerId(1001L);
        assertNotNull(customerId);
        assertEquals(1001L, customerId.getCustomerId());
    }

    @Test
    public void testGetAllRewards() {

    }
}
