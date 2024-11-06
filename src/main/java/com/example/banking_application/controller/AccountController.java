package com.example.banking_application.controller;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking_application.entity.Account;
import com.example.banking_application.service.AccountService;

@RestController
//@RequestMapping("/accounts")
public class AccountController {

  @Autowired
  private AccountService accountService;

  public AccountController(AccountService accountService){
    this.accountService=accountService;
  }

  @RequestMapping(value = "/accounts", method=RequestMethod.POST)
  public String createAccount(@RequestBody Account account) {
    return accountService.createAccount(account);
  }

  @RequestMapping(value="/accounts/{id}")
  public Optional<Account> getAccount(@PathVariable Long id){
    return accountService.getAccount(id);
  }

  @RequestMapping(value ="/accounts/{id}", method=RequestMethod.DELETE)
  public String deleteAccount(@PathVariable Long id) {
    return accountService.deleteAccount(id);
  }

  //deposit amount
  @RequestMapping(value = "/accounts/{id}/deposit", method = RequestMethod.PUT)
  public Optional<String> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){
    Double amount=request.get("amount");
    accountService.deposit(id, amount);
    return Optional.of("New balance "+amount);
  }

  //withdraw amount
  @RequestMapping(value = "/accounts/{id}/withdraw", method = RequestMethod.PUT)
  public Optional<String> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request){
    Double amount=request.get("amount");
    accountService.withdraw(id, amount);
    return Optional.of("New balance "+amount);
  }

  @RequestMapping(value="/accounts", method=RequestMethod.GET)
  public List<Account> getAllAccount(){
    return accountService.getAllAccount();
  }
}
