package com.bankproject.bankingapp.service.impl;

import com.bankproject.bankingapp.dto.AccountDto;
import com.bankproject.bankingapp.entity.Account;
import com.bankproject.bankingapp.mapper.AccountMapper;
import com.bankproject.bankingapp.repository.AccountRepository;
import com.bankproject.bankingapp.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {


    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        Account account= AccountMapper.maptoAccount(accountDto);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
       Account account=accountRepository
               .findById(id).orElseThrow(()->new RuntimeException("Account does not existes..."));

        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does nit exists"));
        double total=account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {

            Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does nit exists"));
            if(account.getBalance()<amount){
                throw new RuntimeException("Insufficient amount...");

            }
            double total=account.getBalance()-amount;
            account.setBalance(total);
            Account savedAccount=accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }
}
