package com.shuz.ecommerce.service.impl;

import com.shuz.ecommerce.dto.Response.UserResponseDto;
import com.shuz.ecommerce.dto.request.UserRequestDto;
import com.shuz.ecommerce.entity.User;
import com.shuz.ecommerce.repo.RoleRepo;
import com.shuz.ecommerce.repo.UserRepo;
import com.shuz.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;

    @Override
    public UserResponseDto saveUserToTable(UserRequestDto dto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User newUser = new User();

        newUser.setName(dto.getName());
        newUser.setUsername(dto.getUsername());
        newUser.setPassword(encoder.encode(dto.getPassword()));
        newUser.setEmail(dto.getEmail());
        newUser.setRoles(roleRepo.getUserRole("USER"));

        User savedUser = userRepo.save(newUser);

        return new UserResponseDto(savedUser);
    }

    @Override
    public UserResponseDto findUserByUserName(String username) {
        return new UserResponseDto(userRepo.getUserByUsername(username));
    }
}
