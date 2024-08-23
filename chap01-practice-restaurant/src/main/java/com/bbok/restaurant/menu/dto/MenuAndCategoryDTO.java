package com.bbok.restaurant.menu.dto;

public class MenuAndCategoryDTO {

	private int menuCode;
//	private int categoryCode;
	private String menuName;
	private int menuPrice;
	private String orderableStatus;
	private CategoryDTO categoryCode;
	
	public MenuAndCategoryDTO() {
		super();
	}

	public MenuAndCategoryDTO(int menuCode, String menuName, int menuPrice, String orderableStatus,
			CategoryDTO categoryCode) {
		super();
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.orderableStatus = orderableStatus;
		this.categoryCode = categoryCode;
	}

	public int getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(int menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}

	public String getOrderableStatus() {
		return orderableStatus;
	}

	public void setOrderableStatus(String orderableStatus) {
		this.orderableStatus = orderableStatus;
	}

	public CategoryDTO getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(CategoryDTO categoryCode) {
		this.categoryCode = categoryCode;
	}

	@Override
	public String toString() {
		return "MenuAndCategoryDTO [menuCode=" + menuCode + ", menuName=" + menuName + ", menuPrice=" + menuPrice
				+ ", orderableStatus=" + orderableStatus + ", categoryCode=" + categoryCode + "]";
	}


	
}