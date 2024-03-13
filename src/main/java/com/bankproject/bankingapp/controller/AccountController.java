package com.bankproject.bankingapp.controller;

import com.bankproject.bankingapp.dto.AccountDto;
import com.bankproject.bankingapp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;
    public AccountController(AccountService accountService){
        this.accountService=accountService;
    }
    //add account rest API
    @PostMapping
    public ResponseEntity<AccountDto>addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
    //get account rest API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto>getAccountbyId(@PathVariable Long id){
        AccountDto accountDto=accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }
    //deposite rest API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String,Double>request){
      Double amount=request.get("amount");
       AccountDto accountDto=accountService.deposit(id,amount);
    return ResponseEntity.ok(accountDto);
    }
    //Withdraw Rest API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String,Double>request){
        double amount=request.get("amount");
        AccountDto accountDto=accountService.withdraw(id,amount);
    return ResponseEntity.ok(accountDto);
    }
}
