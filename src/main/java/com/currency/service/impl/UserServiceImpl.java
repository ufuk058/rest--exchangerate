package com.currency.service.impl;

import com.currency.dto.UserDTO;
import com.currency.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UserDTO> findAll() {
        return List.of();
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO findByUsername(String username) {
        return null;
    }
}
