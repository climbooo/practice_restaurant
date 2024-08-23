package com.bbok.restaurant.menu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity(name="Menu")
@Table(name = "MENU")
@SequenceGenerator(
		name = "MENU_SEQ_GENERATOR",
		sequenceName = "SEQ_MENU_CODE",
		initialValue = 1,
		allocationSize = 1
		)
public class Menu {

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "MENU_SEQ_GENERATOR"
			)
	@Column(name= "MENU_CODE")
	private int menuCode;
	
	@Column(name= "CATEGORY_CODE")
	private int categoryCode;
	
	@Column(name= "MENU_NAME")
	private String menuName;
	
	@Column(name= "MENU_PRICE")
	private int menuPrice;
	
	@Column(name= "ORDERABLE_STATUS")
	private String orderableStatus;
	
	public Menu() {
	}

	public Menu(int menuCode, int categoryCode, String menuName, int menuPrice, String orderableStatus) {
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
		return "Menu [menuCode=" + menuCode + ", categoryCode=" + categoryCode + ", menuName=" + menuName
				+ ", menuPrice=" + menuPrice + ", orderableStatus=" + orderableStatus + "]";
	}
	
	
}
