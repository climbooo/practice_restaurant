package com.bbok.restaurant.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbok.restaurant.cart.repository.CartAndMenuRepository;

@Service
public class CartService {

	private final CartAndMenuRepository cartAndMenuRepository;

	@Autowired
	public CartService(CartAndMenuRepository cartAndMenuRepository) {
		this.cartAndMenuRepository = cartAndMenuRepository;
	}
}
