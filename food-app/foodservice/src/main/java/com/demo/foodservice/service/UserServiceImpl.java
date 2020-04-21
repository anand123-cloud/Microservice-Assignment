package com.demo.foodservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.foodservice.dto.MenuItemDto;
import com.demo.foodservice.dto.MenuItemResponse;
import com.demo.foodservice.dto.UserRequestDto;
import com.demo.foodservice.dto.UserResponseDto;
import com.demo.foodservice.entity.FoodVendor;
import com.demo.foodservice.entity.MenuItem;
import com.demo.foodservice.entity.User;
import com.demo.foodservice.repository.MenuRepository;
import com.demo.foodservice.repository.UserRepository;
import com.demo.foodservice.repository.VendorRepository;




@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private VendorRepository vendorRepository;
	

	@Override
	@Transactional
	public UserResponseDto registerUser(UserRequestDto userDto) {
		
		User user=new User();
		user.setUserId(generateUniqueUserId());
		user.setEmail(userDto.getEmail());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPhone(userDto.getPhone());
		User savedUser = userRepository.save(user);
		UserResponseDto userResponse = createUserResponse(savedUser);
		return userResponse;
	}

	private UserResponseDto createUserResponse(User user) {
		UserResponseDto responseDto=new UserResponseDto();
		responseDto.setEmail(user.getEmail());
		responseDto.setFirstName(user.getFirstName());
		responseDto.setLastName(user.getLastName());
		responseDto.setPhone(user.getPhone());
		responseDto.setUserId(user.getUserId());
		return responseDto;
		
	}

	
	private String generateUniqueUserId() {
		
		int unique = 100 + new Random().nextInt(900);	
		String userName="U0"+unique;
		return userName;
	}

	@Override
	public List<MenuItemResponse> findMenuItems(String menuItem) {
		
			List<MenuItem> items = menuRepository.findByMenuNameContainingIgnoreCase(menuItem);
			List<MenuItemResponse> itemResponses=createMenuItemDtos(items);
		return itemResponses;
	}

	private List<MenuItemResponse> createMenuItemDtos(List<MenuItem> items) {
		List<MenuItemResponse> itemResponses=null;
		if(!items.isEmpty()) {
			itemResponses=items.stream().map(item -> {
				MenuItemDto itemDto=new MenuItemDto(item.getMenuName(), item.getMenuDesc(), item.getMenuPrice(), item.getMenuType());
				MenuItemResponse itemResponse=new MenuItemResponse();
				itemResponse.setItemDto(itemDto);
				itemResponse.setRestaurantName(item.getFoodVendor().getName());
				itemResponse.setRestaurantLocation(item.getFoodVendor().getName());
				return itemResponse;				
			}).collect(Collectors.toList());
		} else {
			itemResponses=new ArrayList<>();
		}
		
		return itemResponses;
	}

	@Override
	public List<MenuItemResponse> findMenuItemsForVendor(String vendorName) {
		FoodVendor vendor = vendorRepository.findByNameContainingIgnoreCase(vendorName);
		List<MenuItemResponse> itemResponses=null;
		if(vendor!=null) {
			List<MenuItem> menuItems = vendor.getMenuItems();
			itemResponses=createMenuItemDtos(menuItems);
		} else {
			itemResponses=new ArrayList<>();
		}
		
		return itemResponses;
	}

	
}
