package com.bbok.restaurant.cart.dto;

import java.util.Date;

import com.bbok.restaurant.menu.entity.Menu;

public class CartAndMenuDTO {

	private int cartCode;
	private Menu menuCode;
	private Date createDate;
	private int totalPrice;
	private int amount;
	private int orderCode;
	
	public CartAndMenuDTO() {
	}

	public CartAndMenuDTO(int cartCode, Menu menuCode, Date createDate, int totalPrice, int amount, int orderCode) {
		super();
		this.cartCode = cartCode;
		this.menuCode = menuCode;
		this.createDate = createDate;
		this.totalPrice = totalPrice;
		this.amount = amount;
		this.orderCode = orderCode;
	}

	public int getCartCode() {
		return cartCode;
	}

	public void setCartCode(int cartCode) {
		this.cartCode = cartCode;
	}

	public Menu getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(Menu menuCode) {
		this.menuCode = menuCode;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	@Override
	public String toString() {
		return "CartAndMenuDTO [cartCode=" + cartCode + ", menuCode=" + menuCode + ", createDate=" + createDate
				+ ", totalPrice=" + totalPrice + ", amount=" + amount + ", orderCode=" + orderCode + "]";
	}

	
	
}
