package com.bbok.restaurant.menu.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bbok.restaurant.menu.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {


}
