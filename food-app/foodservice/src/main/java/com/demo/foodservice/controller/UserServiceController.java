package com.demo.foodservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.foodservice.dto.MenuItemResponse;
import com.demo.foodservice.dto.UserRequestDto;
import com.demo.foodservice.dto.UserResponseDto;
import com.demo.foodservice.service.UserService;

@RestController
@RequestMapping("/foodservice")
public class UserServiceController {
	
	@Autowired
	private UserService userService;

	
	@PostMapping("/users")
	private UserResponseDto registerUser(@RequestBody UserRequestDto userDto) {		
		return userService.registerUser(userDto);
	}
	
	@GetMapping("/users")
	private List<MenuItemResponse> searchMenu(
			@RequestParam(required = true,name = "menuItem") String menuItem) {
		
		return userService.findMenuItems(menuItem);
		
	}
	
	@GetMapping("/users/vendors")
	private List<MenuItemResponse> searchVendorWithMenu(
			@RequestParam(required = true,name = "vendor") String vendor) {
		
		return userService.findMenuItemsForVendor(vendor);
		
	}
	

}
