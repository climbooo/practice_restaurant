package com.bbok.restaurant.menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bbok.restaurant.menu.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	@Query(value = "SELECT c FROM Category c ORDER BY c.categoryCode")
	public List<Category> findAllCategory();

}
