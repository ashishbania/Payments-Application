package com.payments.balancesapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BalancesServiceImplTest {

    @Mock
    private BalancesRepository balancesRepository;
    @Autowired
    private BalancesServiceImpl balancesService;

    @BeforeEach
    void setUp() {
        this.balancesService= new BalancesServiceImpl(this.balancesRepository);
    }

    @Test
    void getTransactions() {
    }

    @Test
    void getAllTransactions() {
        balancesService.getAllTransactions();
        verify(balancesRepository).findAll();
    }

    @Test
    void addTransactions() {
    }

    @Test
    void updateTransactions() {
    }

    @Test
    void deleteTransactionById() {
    }
}