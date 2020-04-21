package com.demo.foodservice.batch;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.foodservice.dto.MenuItemDto;
import com.demo.foodservice.entity.FoodVendor;
import com.demo.foodservice.entity.MenuItem;
import com.demo.foodservice.repository.VendorRepository;

@Component
public class DBWriter implements ItemWriter<MenuItemDto>{
	
	@Autowired
	VendorRepository vendorRepository;

	@Override
	public void write(List<? extends MenuItemDto> menuItemsDtos) throws Exception {
		System.out.println("data saved in db"+menuItemsDtos);
		
		String vendorId = menuItemsDtos.get(0).getVendorId();
		FoodVendor foodVendor=vendorRepository.findById(vendorId).get();
		List<MenuItem> menuItems = createMenuItem(menuItemsDtos,foodVendor);
		foodVendor.setMenuItems(menuItems);
		vendorRepository.save(foodVendor);
		
	}

	private List<MenuItem> createMenuItem(List<? extends MenuItemDto> menuItemsDtos, FoodVendor foodVendor) {
		
		List<MenuItem> meItems = menuItemsDtos.stream().map(itemDto -> {
			MenuItem item=new MenuItem();
			item.setFoodVendor(foodVendor);
			item.setMenuDesc(itemDto.getDesc());
			item.setMenuItemId(generateUniqueMenuId());
			item.setMenuName(itemDto.getName());
			item.setMenuPrice(itemDto.getPrice());
			item.setMenuType(itemDto.getType());
			return item;
			
		}).collect(Collectors.toList());
		
		return meItems;
		
		
	}

	private String generateUniqueMenuId() {
		int unique = 100 + new Random().nextInt(900);	
		String menuId="M0"+unique;
		return menuId;
	}

	

}
