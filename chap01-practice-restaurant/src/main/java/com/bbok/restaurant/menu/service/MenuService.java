package com.bbok.restaurant.menu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbok.restaurant.common.paging.SelectCriteria;
import com.bbok.restaurant.menu.dto.CategoryDTO;
import com.bbok.restaurant.menu.dto.MenuAndCategoryDTO;
import com.bbok.restaurant.menu.dto.MenuDTO;
import com.bbok.restaurant.menu.entity.Category;
import com.bbok.restaurant.menu.entity.Menu;
import com.bbok.restaurant.menu.entity.MenuAndCategory;
import com.bbok.restaurant.menu.repository.CategoryRepository;
import com.bbok.restaurant.menu.repository.MenuAndCategoryRepository;
import com.bbok.restaurant.menu.repository.MenuRepository;

@Service
public class MenuService {

	private final MenuAndCategoryRepository menuAndCategoryRepository;
	private final MenuRepository menuRepository;
	private final CategoryRepository categoryRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public MenuService(MenuRepository menuRepository, CategoryRepository categoryRepository, ModelMapper modelMapper, MenuAndCategoryRepository menuAndCategoryRepository) {
		this.menuAndCategoryRepository = menuAndCategoryRepository;
		this.menuRepository = menuRepository;
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}
	
	public MenuAndCategoryDTO findMenuByCode(int menuCode) {
		
		MenuAndCategory menu = menuAndCategoryRepository.findById(menuCode).get();
		
		return modelMapper.map(menu, MenuAndCategoryDTO.class);
	}
	
	public List<MenuAndCategoryDTO> findMenuList(SelectCriteria selectCriteria)	{
		
		int index = selectCriteria.getPageNo() - 1;			// Pageble객체를 사용시 페이지는 0부터 시작(1페이지가 0)
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		/* 페이징 처리와 정렬을 위한 객체 생성 */
		Pageable paging = PageRequest.of(index, count, Sort.by("menuCode"));	// Pageable은 org.springframework.data.domain패키지로 import

		List<MenuAndCategory> menuList = new ArrayList<MenuAndCategory>();
		if(searchValue != null) {

			/* category 검색일 경우 */
			if("menuName".equals(selectCriteria.getSearchCondition())) {
				menuList = menuAndCategoryRepository.findByMenuNameContaining(selectCriteria.getSearchValue(), paging);
			}

			/* 가격 검색일 경우 */
			if("menuPrice".equals(selectCriteria.getSearchCondition())) {
				menuList = menuAndCategoryRepository.findByMenuPriceLessThanEqual(Integer.valueOf(selectCriteria.getSearchValue()), paging);
			}
		} else {
			menuList = menuAndCategoryRepository.findAll(paging).toList();
		}
		
		return menuList.stream().map(menu -> modelMapper.map(menu, MenuAndCategoryDTO.class)).collect(Collectors.toList());
	}

	public List<CategoryDTO> findAllCategory() {
		
		List<Category> categoryList = categoryRepository.findAllCategory();
		
		return categoryList.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
	}

	@Transactional
	public void registNewMenu(MenuDTO newMenu) {
		
		menuRepository.save(modelMapper.map(newMenu, Menu.class));
	}

	@Transactional
	public void modifyMenu(MenuDTO menu) {
		
		Menu foundMenu = menuRepository.findById(menu.getMenuCode()).get();
		foundMenu.setMenuName(menu.getMenuName());
		foundMenu.setMenuPrice(menu.getMenuPrice());
		foundMenu.setOrderableStatus(menu.getOrderableStatus());
		foundMenu.setCategoryCode(menu.getCategoryCode());
	}

	public CategoryDTO findCategoryByCode(int categoryCode) {
		
		Category category = categoryRepository.findById(categoryCode).get();
		
		return modelMapper.map(category, CategoryDTO.class);
	}

	@Transactional
	public void deleteMenu(int menuCode) {
		
		menuRepository.deleteById(menuCode);
	}
	
	public int selectTotalCount(String searchCondition, String searchValue) {

		int count = 0;
		if(searchValue != null) {
			if("menuName".equals(searchCondition)) {
				count = menuAndCategoryRepository.countByMenuNameContaining(searchValue);
			}

			/* 가격 검색일 경우 */
			if("menuPrice".equals(searchCondition)) {
				count = menuAndCategoryRepository.countByMenuPriceLessThanEqual(Integer.valueOf(searchValue));
			}
		} else {
			count = (int)menuAndCategoryRepository.count();
		}

		return count;
	}

	public List<MenuAndCategoryDTO> searchMenuList(SelectCriteria selectCriteria) {

		int index = selectCriteria.getPageNo() - 1;			// Pageble객체를 사용시 페이지는 0부터 시작(1페이지가 0)
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		/* 페이징 처리와 정렬을 위한 객체 생성 */
		Pageable paging = PageRequest.of(index, count, Sort.by("menuCode"));	// Pageable은 org.springframework.data.domain패키지로 import

		List<MenuAndCategory> menuList = new ArrayList<MenuAndCategory>();
		if(searchValue != null) {

			/* category 검색일 경우 */
			if("menuName".equals(selectCriteria.getSearchCondition())) {
				menuList = menuAndCategoryRepository.findByMenuNameContaining(selectCriteria.getSearchValue(), paging);
			}

			/* 가격 검색일 경우 */
			if("menuPrice".equals(selectCriteria.getSearchCondition())) {
				menuList = menuAndCategoryRepository.findByMenuPriceLessThanEqual(Integer.valueOf(selectCriteria.getSearchValue()), paging);
			}
		} else {
			menuList = menuAndCategoryRepository.findAll(paging).toList();
		}

		/* 자바의 Stream API와 ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return menuList.stream().map(menu -> modelMapper.map(menu, MenuAndCategoryDTO.class)).collect(Collectors.toList());
	}
	

}
