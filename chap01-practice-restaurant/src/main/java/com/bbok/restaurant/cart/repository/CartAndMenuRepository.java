package com.bbok.restaurant.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbok.restaurant.cart.entity.CartAndMenu;

public interface CartAndMenuRepository extends JpaRepository<CartAndMenu, Integer>{

}
