package com.bbok.restaurant.menu.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bbok.restaurant.menu.entity.MenuAndCategory;

public interface MenuAndCategoryRepository extends JpaRepository<MenuAndCategory, Integer>{

	int countByMenuNameContaining(String searchValue);

	int countByMenuPriceLessThanEqual(Integer valueOf);

	List<MenuAndCategory> findByMenuNameContaining(String searchValue, Pageable paging);

	List<MenuAndCategory> findByMenuPriceLessThanEqual(Integer valueOf, Pageable paging);


}
