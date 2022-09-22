package com.assessment.reward.Reward.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.assessment.reward.Reward.swagger.SpringFoxConfig;
import com.assessment.reward.Reward.entity.Customer;
import com.assessment.reward.Reward.entity.Transaction;
import com.assessment.reward.Reward.model.Rewards;
import com.assessment.reward.Reward.repository.CustomerRepository;
import com.assessment.reward.Reward.repository.TransactionRepository;
import com.assessment.reward.Reward.service.RewardsService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.List;

@WebMvcTest(RewardsController.class)
@ExtendWith(MockitoExtension.class)
@Import(RewardsController.class)
@ContextConfiguration(classes = SpringFoxConfig.class)
public class RewardsControllerTest {
    @MockBean
    private RewardsService rewardsService;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testgetAllRewards() throws Exception {
        Rewards reward = new Rewards(1001, 120, 90, 100, 310);
        Rewards reward2 = new Rewards(1002, 120, 90, 100, 310);
        Rewards reward3 = new Rewards(1003, 120, 90, 100, 310);
        Rewards reward4 = new Rewards(1004, 120, 90, 100, 310);
        Rewards reward5 = new Rewards(1005, 120, 90, 100, 310);
        Rewards reward6 = new Rewards(1006, 120, 90, 100, 310);
        List<Rewards> rewardsList = List.of(reward, reward2, reward3, reward4, reward5, reward6);
        Mockito.when(rewardsService.getAllRewards()).thenReturn(rewardsList);
        mockMvc.perform(get("/api/v1/rewards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(6)))
                .andExpect(jsonPath("$[0].customerId", Matchers.is(1001)));
    }

    @Test
    public void testGetAllTransactions() throws Exception {
        Transaction t1 = new Transaction(10001l, 1001l, Timestamp.valueOf("2022-04-12 00:00:00"), 120);
        Transaction t2 = new Transaction(10001l, 1001l, Timestamp.valueOf("2022-05-01 00:00:00"), 100);
        Transaction t3 = new Transaction(10001l, 1001l, Timestamp.valueOf("2022-06-04 00:00:00"), 80);
        List<Transaction> transactionList = List.of(t1, t2, t3);
        Mockito.when(rewardsService.getAllTransactions()).thenReturn(transactionList);
        mockMvc.perform(get("/api/v1/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[1].customerId", Matchers.is(1001)));
    }

    @Test
    public void testRewardsByCustomerId() throws Exception {
        Customer c = new Customer(1001L, "Cust1");
        Mockito.when(rewardsService.findByCustomerId(1001L)).thenReturn(c);
        Rewards reward = new Rewards(1001, 120, 90, 100, 310);
        Mockito.when(rewardsService.getRewardsByCustomerId(1001L)).thenReturn(reward);
        mockMvc.perform(get("/api/v1/1001/rewards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.aMapWithSize(5)))
                .andExpect(jsonPath("$.lastMonthRewardPoints", Matchers.is(120)));
    }
}
