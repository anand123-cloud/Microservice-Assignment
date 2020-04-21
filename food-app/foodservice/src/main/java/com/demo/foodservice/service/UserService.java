package com.demo.foodservice.service;

import java.util.List;

import com.demo.foodservice.dto.MenuItemResponse;
import com.demo.foodservice.dto.UserRequestDto;
import com.demo.foodservice.dto.UserResponseDto;

public interface UserService {
	
	UserResponseDto registerUser(UserRequestDto userDto);
	
	List<MenuItemResponse> findMenuItems(String menuItem);
	List<MenuItemResponse> findMenuItemsForVendor(String vendor);
	

}
