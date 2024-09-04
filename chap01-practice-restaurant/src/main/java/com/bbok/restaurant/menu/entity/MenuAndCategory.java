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
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_CODE")
	private Category categoryCode;
	
	@Column(name = "MENU_NAME")
	private String menuName;
	
	@Column(name = "MENU_PRICE")
	private int menuPrice;
	
	@Column(name = "ORDERABLE_STATUS")
	private String orderableStatus;
	
	@Column(name = "ORIGIN_URL")
	private String originUrl;
	
	@Column(name = "PICTURE_URL")
	private String pictureUrl;
	
	
	public MenuAndCategory() {
	}


	public MenuAndCategory(int menuCode, Category categoryCode, String menuName, int menuPrice, String orderableStatus,
			String originUrl, String pictureUrl) {
		this.menuCode = menuCode;
		this.categoryCode = categoryCode;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.orderableStatus = orderableStatus;
		this.originUrl = originUrl;
		this.pictureUrl = pictureUrl;
	}


	public int getMenuCode() {
		return menuCode;
	}


	public void setMenuCode(int menuCode) {
		this.menuCode = menuCode;
	}


	public Category getCategoryCode() {
		return categoryCode;
	}


	public void setCategoryCode(Category categoryCode) {
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


	public String getOriginUrl() {
		return originUrl;
	}


	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}


	public String getPictureUrl() {
		return pictureUrl;
	}


	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}


	@Override
	public String toString() {
		return "MenuAndCategory [menuCode=" + menuCode + ", categoryCode=" + categoryCode + ", menuName=" + menuName
				+ ", menuPrice=" + menuPrice + ", orderableStatus=" + orderableStatus + ", originUrl=" + originUrl
				+ ", pictureUrl=" + pictureUrl + "]";
	}

	
}
