package com.currency.service.impl;

import com.currency.dto.AccountDTO;
import com.currency.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public List<AccountDTO> findAllByUsername(String username) {
        return List.of();
    }

    @Override
    public AccountDTO create(AccountDTO accountDTO) {
        return null;
    }

    @Override
    public List<AccountDTO> findAll() {
        return List.of();
    }

    @Override
    public List<AccountDTO> findAllByUsernameAndCurrencyList(String username, List<String> currencyList) {
        return List.of();
    }
}
