package com.bbok.restaurant.cart.entity;

import java.util.Date;

import com.bbok.restaurant.menu.entity.Menu;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity(name = "CartAndMenu")
@Table(name = "CART")
@SequenceGenerator(
		name = "CART_SEQ_GENERATOR",
		sequenceName = "SEQ_CART_CODE",
		initialValue = 1,
		allocationSize = 1
		)
public class CartAndMenu {

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "CART_SEQ_GENERATOR"
			)
	@Column(name = "CART_CODE")
	private int cartCode;
	
	@Column(name = "MENU_CODE")
	private Menu menuCode;
	
	@Column(name = "CREATE_DATE")
	private Date createDate;
	
	@Column(name = "TOTAL_PRICE")
	private int totalPrice;
	
	@Column(name = "AMOUNT")
	private int amount;
	
	@Column(name = "ORDER_CODE")
	private int orderCode;
	
	public CartAndMenu() {
	}

	public CartAndMenu(int cartCode, Menu menuCode, Date createDate, int totalPrice, int amount, int orderCode) {
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
		return "CartAndMenu [cartCode=" + cartCode + ", menuCode=" + menuCode + ", createDate=" + createDate
				+ ", totalPrice=" + totalPrice + ", amount=" + amount + ", orderCode=" + orderCode + "]";
	}

	
}
