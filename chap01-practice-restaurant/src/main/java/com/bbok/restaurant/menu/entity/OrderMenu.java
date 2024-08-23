package com.bbok.restaurant.menu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name="OrderMenu")
@Table(name = "ORDER_MENU")
public class OrderMenu {

	@Id
	@Column(name = "ORDER_CODE")
	private int orderCode;
	
	@Column(name = "MENU_CODE")
	private int menuCode;
	
	@Column(name = "MENU_AMOUNT")
	private int menuAmount;
	
	@Column(name = "MENU_NAME")
	private String menuName;

	public OrderMenu() {
		super();
	}

	public OrderMenu(int orderCode, int menuCode, int menuAmount, String menuName) {
		super();
		this.orderCode = orderCode;
		this.menuCode = menuCode;
		this.menuAmount = menuAmount;
		this.menuName = menuName;
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public int getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(int menuCode) {
		this.menuCode = menuCode;
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
		return "OrderMenu [orderCode=" + orderCode + ", menuCode=" + menuCode + ", menuAmount=" + menuAmount
				+ ", menuName=" + menuName + "]";
	}
	
	
	
	
}
