package com.demo.foodservice.batch;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.demo.foodservice.dto.MenuItemDto;

@Component
public class Processor implements ItemProcessor<MenuItemDto, MenuItemDto> {
	
	public  Processor() {
		
	}

	@Override
	public MenuItemDto process(MenuItemDto menuItemDto) throws Exception {
	
		  List<String> list = Arrays.stream(menuItemDto.getDesc().split(","))
		    		.map(desc -> desc.toUpperCase()).collect(Collectors.toList());
		String description = String.join(", ", list);
		menuItemDto.setType(menuItemDto.getType().toUpperCase());
	    menuItemDto.setDesc(description);
		return menuItemDto;
	}

}
