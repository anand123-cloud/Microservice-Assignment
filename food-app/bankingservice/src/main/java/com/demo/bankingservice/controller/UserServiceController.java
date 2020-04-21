package com.demo.bankingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bankingservice.dto.UserRequestDto;
import com.demo.bankingservice.dto.UserResponseDto;
import com.demo.bankingservice.service.UserService;

@RestController
@RequestMapping("/userservice")
public class UserServiceController {
	
	@Autowired
	private UserService userService;

	
	@PostMapping("/users")
	private UserResponseDto registerUser(@RequestBody UserRequestDto userDto) {		
		return userService.registerUser(userDto);
	}
	
}
