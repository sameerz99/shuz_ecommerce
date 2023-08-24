package com.shuz.ecommerce.service;

import com.shuz.ecommerce.dto.Response.UserResponseDto;
import com.shuz.ecommerce.dto.request.UserRequestDto;

public interface UserService {
    UserResponseDto saveUserToTable(UserRequestDto userRequestDto);

    UserResponseDto findUserByUserName(String username);
}
