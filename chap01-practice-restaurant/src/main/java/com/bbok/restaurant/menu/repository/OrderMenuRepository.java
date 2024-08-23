package com.bbok.restaurant.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbok.restaurant.menu.entity.OrderMenu;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Integer>{

}
