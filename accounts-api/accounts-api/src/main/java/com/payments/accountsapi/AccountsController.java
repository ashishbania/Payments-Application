package com.payments.accountsapi;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/account")
public class AccountsController {

  private final AccountsService accountsService;
  @Autowired private final AccountService accountService;
  Logger logger = LoggerFactory.getLogger(AccountsController.class);

  public AccountsController(AccountsService accountsService, AccountService accountService) {
    this.accountsService = accountsService;
    this.accountService = accountService;
  }

  @GetMapping(path = "/")
  public List<Accounts> getAllAccounts(
      @RequestParam(required = false, defaultValue = "Enabled") String status) {
    logger.info("Accounts Controller with getAllAccounts called");

    return accountService.getAllAccounts().stream()
        .filter(x -> x.getStatus().equals(status))
        .collect(Collectors.toList());
  }

  @GetMapping(path = "/{id}")
  public Accounts getAccountById(@PathVariable("id") Integer id) {
    // return  Stream.of(accountService.getAccounts(id)).map(x->
    // x.getStatus().toLowerCase()).collect(Collectors.toList());
    logger.info("Accounts Controller with getById called");
    return accountService.getAccounts(id);
  }

  //    @GetMapping(path="/{status}")
  //    public List<Accounts> getDisabledAccounts(@PathVariable("status") String status){
  //        return accountService.getAllAccounts().stream().filter(x->
  // x.getStatus().equals(status)).collect(Collectors.toList());
  //    }

  @PostMapping
  public ResponseEntity addAccounts(@RequestBody Accounts accounts) {
    String addAccountResponse = accountService.addAccounts(accounts);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity removeAccountByID(@PathVariable("id") Integer id) throws Exception {
    Accounts accounts = accountService.getAccounts(id);
    accountService.deleteAccountById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
