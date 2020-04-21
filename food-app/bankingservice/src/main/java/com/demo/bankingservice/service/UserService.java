package com.demo.bankingservice.service;

import com.demo.bankingservice.dto.UserRequestDto;
import com.demo.bankingservice.dto.UserResponseDto;

public interface UserService {
	
	UserResponseDto registerUser(UserRequestDto userDto);
	

}
