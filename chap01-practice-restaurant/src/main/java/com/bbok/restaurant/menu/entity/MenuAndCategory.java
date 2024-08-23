package com.bbok.restaurant.menu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity(name="MenuAndCategory")
@Table(name = "MENU")
@SequenceGenerator(
		name = "MENU_SEQ_GENERATOR",
		sequenceName = "SEQ_MENU_CODE",
		initialValue = 1,
		allocationSize = 1
		)

public class MenuAndCategory {

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "MENU_SEQ_GENERATOR"
			)
	@Column(name = "MENU_CODE")
	private int menuCode;
	
//	@Column(name = "CATEGORY_CODE")
//	private int categoryCode;
	
	@Column(name = "MENU_NAME")
	private String menuName;
	
	@Column(name = "MENU_PRICE")
	private int menuPrice;
	
	@Column(name = "ORDERABLE_STATUS")
	private String orderableStatus;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_CODE")
	private Category categoryCode;
	
	public MenuAndCategory() {
	}

	public MenuAndCategory(int menuCode, String menuName, int menuPrice, String orderableStatus,
			Category categoryCode) {
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

	public Category getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(Category categoryCode) {
		this.categoryCode = categoryCode;
	}

	@Override
	public String toString() {
		return "MenuAndCategory [menuCode=" + menuCode + ", menuName=" + menuName + ", menuPrice=" + menuPrice
				+ ", orderableStatus=" + orderableStatus + ", categoryCode=" + categoryCode + "]";
	}


	
}