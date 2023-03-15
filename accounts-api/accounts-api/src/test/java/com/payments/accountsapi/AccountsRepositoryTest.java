package com.payments.accountsapi;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountsRepositoryTest {
    @Autowired
    private AccountsRepository accountsRepository;

    @BeforeEach
    void setUp() {
        System.out.println("Seting Up");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Tearing Down");
    }

    @Test
    void findById() {
        Accounts accounts = accountsRepository.findById(1).orElse(null);
        assertThat(accounts).isNotNull();
    }

    @Test
    void findAll() {
        assertThat(accountsRepository.findAll()).isNotNull();
    }

    @Test
    void deleteById() {
    }
}