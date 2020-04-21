package com.demo.foodservice.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "foodmenu")
public class MenuItem {

	@Id
	@Column(name = "menuitemid")
	private String menuItemId;

	@Column(name = "menuname")
	private String menuName;

	@Column(name = "menudesc")
	private String menuDesc;

	@Column(name = "menuprice")
	private BigDecimal menuPrice;

	@Column(name = "menutype")
	private String menuType;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "restaurantid", nullable = false)
	private FoodVendor foodVendor;

	public MenuItem() {

	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public BigDecimal getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(BigDecimal menuPrice) {
		this.menuPrice = menuPrice;
	}

	public FoodVendor getFoodVendor() {
		return foodVendor;
	}

	public void setFoodVendor(FoodVendor foodVendor) {
		this.foodVendor = foodVendor;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(String menuItemId) {
		this.menuItemId = menuItemId;
	}

}
