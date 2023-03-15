package com.payments.accountsapi;

import com.payments.accountsapi.exception.ElementAlreadyExistsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountsRepository accountsRepository;
    @Autowired
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        System.out.println("Setting up");
        this.accountService = new AccountServiceImpl(this.accountsRepository);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Tearing Down");
    }

    @Test
    @Disabled
    void getAccounts() {

    }

    @Test
    void getAllAccounts() {
        accountService.getAllAccounts();
        verify(accountsRepository).findAll();
    }

    @Test
    void addAccounts() {
        Accounts accounts1 = new Accounts(
                1,
                "Enabled",
                "2020/07/21",
                "USD",
                "Business",
                "2018/01/11"
        );
        accountService.addAccounts(accounts1);
        ArgumentCaptor<Accounts> argumentCaptor = ArgumentCaptor.forClass(Accounts.class);
        verify(accountsRepository).save(argumentCaptor.capture());
        Accounts capturedAccount = argumentCaptor.getValue();
        assertThat(capturedAccount).isEqualTo(accounts1);
    }
    @Test
    void AddAccountsThrowElementAlreadyExistsException() {
        Accounts accounts1 = new Accounts(
                1,
                "Enabled",
                "2020/07/21",
                "USD",
                "Business",
                "2018/01/11"
        );
        given(accountsRepository.findById(accounts1.getAccountId())).willReturn(Optional.of(accounts1));
        assertThatThrownBy(()-> accountService.addAccounts(accounts1))
                .isInstanceOf(ElementAlreadyExistsException.class)
                .hasMessageContaining("Account already exists with id: "+"1");

        verify(accountsRepository,never()).save(any());
    }

    @Test
    void updateAccounts() {
    }

    @Test
    void deleteAccountById() {
    }
}