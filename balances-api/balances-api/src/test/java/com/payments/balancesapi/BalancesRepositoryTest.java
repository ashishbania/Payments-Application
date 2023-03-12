package com.payments.balancesapi;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BalancesRepositoryTest {
    @Autowired
    private BalancesRepository balancesRepository;

    @AfterEach
    void tearDown() {
        System.out.println("Tearing Down");
        balancesRepository.deleteAll();
    }

    @Test
    void findById() {
        Balances balances = new Balances(1,
                1,
                "5048375892149526",
                "$789.8",
                "USD",
                "Debit",
                "Rejected");
        balancesRepository.save(balances);
        Balances tempBalance =balancesRepository.findById(1).orElse(null);
        assertThat(tempBalance).isNotNull();
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteById() {
    }
}