package com.currency.service.impl;

import com.currency.dto.AccountDTO;
import com.currency.dto.UserDTO;
import com.currency.entity.Account;
import com.currency.entity.User;
import com.currency.repository.AccountRepository;
import com.currency.service.AccountService;
import com.currency.service.UserService;
import com.currency.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final MapperUtil mapperUtil;
    private final UserService userService;

    public AccountServiceImpl(AccountRepository accountRepository, MapperUtil mapperUtil, UserService userService) {
        this.accountRepository = accountRepository;
        this.mapperUtil = mapperUtil;
        this.userService = userService;
    }

    @Override
    public List<AccountDTO> findAllByUsername(String username) {

        return accountRepository.findAllByUser_Username(username).stream().map(account-> {
            AccountDTO accountDTO=mapperUtil.convert(account, new AccountDTO());
            accountDTO.setUsername(username);
//            Map<String, BigDecimal> otherCurrencies= new HashMap<>();
            return accountDTO;
        }).collect(Collectors.toList());
    }


    @Override
    public AccountDTO create(AccountDTO accountDTO) {
       /// Find the user by username
        UserDTO user = userService.findByUsername(accountDTO.getUsername());

        ///Convert AccountDTO to Account
        Account accountToSave=mapperUtil.convert(accountDTO, new Account());

        ///Set the user of the account y converting UserDTO to user
        accountToSave.setUser(mapperUtil.convert(user, new User()));

        /// Generate a random accountNumber for the new account
        accountToSave.setAccountNumber(generateAccountNumber());

        /// Save the newly created account to the database
        Account newAccount=accountRepository.save(accountToSave);

        /// Convert the saved account Entity back to AccountDTO
        AccountDTO accountToReturn= mapperUtil.convert(accountToSave, new AccountDTO());

        /// Set the username in the returned AccountDTO for consistency
        accountToReturn.setUsername(user.getUsername());

        return accountToReturn;
    }

    private Long generateAccountNumber(){

        return (long) (Math.random() *1000000000000L);
    }

    @Override
    public List<AccountDTO> findAll() {
        return accountRepository.findAll().stream().map(account ->
                mapperUtil.convert(account, new AccountDTO())).collect(Collectors.toList());
    }

    @Override
    public List<AccountDTO> findAllByUsernameAndCurrencyList(String username, List<String> currencyList) {
        return List.of();
    }
}
