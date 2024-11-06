package com.example.banking_application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking_application.entity.Account;
import com.example.banking_application.repository.AccountRepository;

@Service
public class AccountService {

  @Autowired
  private final AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository){
    this.accountRepository=accountRepository;
  }

  public String createAccount(Account account){
    accountRepository.save(account);
    return "Added Successfully";
  }

  public Optional<Account> getAccount(Long id){
    Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exists"));
    return Optional.of(account);
  }

  public String deleteAccount(Long id){
    accountRepository.deleteById(id);
    return "Deleted Successfully";
  }

  public String deposit(Long id, double amount){
    Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exists"));
    double total=account.getBalance()+amount;
    account.setBalance(total);
    accountRepository.save(account);
    return "Amount deposited";
  }

  public String withdraw(Long id, double amount){
    Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exists"));

    if(account.getBalance()<amount){
      throw new RuntimeException("Insufficient amount");
    }
    double total=account.getBalance()-amount;
    account.setBalance(total);
    accountRepository.save(account);
    return "Withdraw Successfully";
  }

  public List<Account> getAllAccount(){
    return accountRepository.findAll();
  }
}
