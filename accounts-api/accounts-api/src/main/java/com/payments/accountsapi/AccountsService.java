package com.payments.accountsapi;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {
  private final AccountsRepository accountsRepository;

  public AccountsService(AccountsRepository accountsRepository) {
    this.accountsRepository = accountsRepository;
  }

  // find Accounts by Id
  public Optional<Accounts> findAccountsById(Integer id) {
    return accountsRepository.findById(id);
  }

  // find all Accounts
  public List<Accounts> findAllAccounts() {
    return accountsRepository.findAll();
  }

  public void deleteAccountById(Integer id) {
    accountsRepository.deleteById(id);
  }
}
