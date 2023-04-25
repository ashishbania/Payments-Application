package com.payments.balancesapi;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.payments.balancesapi.exception.ElementAlreadyExistsException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
class BalancesServiceImplTest {

  @Mock private BalancesRepository balancesRepository;
  @Autowired private BalancesServiceImpl balancesService;

  @BeforeEach
  void setUp() {
    this.balancesService = new BalancesServiceImpl(this.balancesRepository);
  }

  @Test
  @Disabled
  void getTransactions() {
    Balances balances =
        new Balances(1, 1, "5048375892149526", "$789.8", "USD", "Debit", "Rejected");
    balancesService.addTransactions(balances);
    balancesService.getTransactions(balances.getTransactionId());
    verify(balancesRepository.findById(1));
  }

  @Test
  void getAllTransactions() {
    balancesService.getAllTransactions();
    verify(balancesRepository).findAll();
  }

  @Test
  void addTransactions() {
    Balances balances =
        new Balances(1, 1, "5048375892149526", "$789.8", "USD", "Debit", "Rejected");
    balancesService.addTransactions(balances);
    ArgumentCaptor<Balances> balancesArgumentCaptor = ArgumentCaptor.forClass(Balances.class);
    verify(balancesRepository).save(balancesArgumentCaptor.capture());

    Balances capturedBalance = balancesArgumentCaptor.getValue();
    assertThat(capturedBalance).isEqualTo(balances);
  }

  @Test
  void willThrowWhenIdIsTaken() {
    Balances balances =
        new Balances(1, 1, "5048375892149526", "$789.8", "USD", "Debit", "Rejected");

    given(balancesRepository.findById(balances.getAccountId())).willReturn(Optional.of(balances));
    assertThatThrownBy(() -> balancesService.addTransactions(balances))
        .isInstanceOf(ElementAlreadyExistsException.class)
        .hasMessageContaining("Transaction already exists with id: " + "1");

    verify(balancesRepository, never()).save(any());
  }

  @Test
  void updateTransactions() {}

  @Test
  void deleteTransactionById() {}
}
