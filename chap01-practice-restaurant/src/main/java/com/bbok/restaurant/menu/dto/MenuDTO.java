package com.bbok.restaurant.menu.dto;

public class MenuDTO {

	private int menuCode;
	private int categoryCode;
	private String menuName;
	private int menuPrice;
	private String orderableStatus;
	
	public MenuDTO() {
	}

	public MenuDTO(int menuCode, int categoryCode, String menuName, int menuPrice, String orderableStatus) {
		super();
		this.menuCode = menuCode;
		this.categoryCode = categoryCode;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.orderableStatus = orderableStatus;
	}

	public int getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(int menuCode) {
		this.menuCode = menuCode;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
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

	@Override
	public String toString() {
		return "MenuDTO{" + 
				"menuCode=" + menuCode + 
				", categoryCode=" + categoryCode + 
				", menuName='" + menuName + '\'' +
				", menuPrice=" + menuPrice + 
				", orderableStatus='" + orderableStatus + '\'' +
				'}';
	}

	
}
