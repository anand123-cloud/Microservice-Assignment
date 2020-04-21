package com.demo.foodservice.dto;

import java.math.BigDecimal;

public class MenuItemDto {
	
	    private String vendorId;
	    private String name;

	    private String desc;

	    private BigDecimal price;
		
		private String type;
		
		public MenuItemDto() {
			
		}

		public MenuItemDto(String name, String desc, BigDecimal price, String type) {
			super();
			this.name = name;
			this.desc = desc;
			this.price = price;
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getVendorId() {
			return vendorId;
		}

		public void setVendorId(String vendorId) {
			this.vendorId = vendorId;
		}

}
