package com.bbok.restaurant.menu.dto;

public class OrderMenuDTO {

	private int menuCode;
	private int orderCode;
	private int menuAmount;
	private String menuName;
	
	public OrderMenuDTO() {
		super();
	}

	public OrderMenuDTO(int menuCode, int orderCode, int menuAmount, String menuName) {
		super();
		this.menuCode = menuCode;
		this.orderCode = orderCode;
		this.menuAmount = menuAmount;
		this.menuName = menuName;
	}

	public int getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(int menuCode) {
		this.menuCode = menuCode;
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public int getMenuAmount() {
		return menuAmount;
	}

	public void setMenuAmount(int menuAmount) {
		this.menuAmount = menuAmount;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Override
	public String toString() {
		return "OrderMenuDTO [menuCode=" + menuCode + ", orderCode=" + orderCode + ", menuAmount=" + menuAmount
				+ ", menuName=" + menuName + "]";
	}
	
	
}
