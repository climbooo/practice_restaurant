package com.bbok.restaurant.menu.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bbok.restaurant.common.paging.Pagenation;
import com.bbok.restaurant.common.paging.SelectCriteria;
import com.bbok.restaurant.menu.dto.MenuAndCategoryDTO;
import com.bbok.restaurant.menu.dto.MenuDTO;
import com.bbok.restaurant.menu.service.MenuService;
import com.bbok.restaurant.util.FileUploadUtils;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.http.HttpServletRequest;



@Controller
@RequestMapping("/menu")
public class MenuController {

	private final MenuService menuService;

	@Autowired
	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}
	
	@GetMapping("/{menuCode}")
	@ResponseBody
	public ModelAndView findMenuByCode(ModelAndView mv, @PathVariable int menuCode) {
		
		MenuAndCategoryDTO menu = menuService.findMenuByCode(menuCode);
		
		String newUrl = menu.getPictureUrl();
		String pictureUrl = "/menuImages/" + newUrl;
		
		menu.setPictureUrl(pictureUrl);
		
		mv.addObject("menu", menu);
		mv.setViewName("/menu/one");
		
		System.out.println("상세 페이지 mv에 담긴 값:" + mv);
		return mv;
	}
	
	/* 메뉴 리스트 */
//	@GetMapping("/list")
//	public ModelAndView findMenuList(ModelAndView mv) {
//		
//		List<MenuAndCategoryDTO> menuList = menuService.findMenuList();
//		
//		mv.addObject("menuList", menuList);
//		mv.setViewName("/menu/list");
//		
//		return mv;
//	}
	
	/* 관리자용 메뉴 리스트 */
	@GetMapping("/admin")
	public ModelAndView findAdminMenuList(HttpServletRequest request, ModelAndView mv) {
		
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		int totalCount = menuService.selectTotalCount(searchCondition, searchValue);

		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 10;		//얘도 파라미터로 전달받아도 된다.

		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 5;

		/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
		SelectCriteria selectCriteria = null;
		if(searchValue != null && !"".equals(searchValue)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		System.out.println(selectCriteria);

		List<MenuAndCategoryDTO> menuList = menuService.findMenuList(selectCriteria);
		
		for(MenuAndCategoryDTO menu : menuList) {
			System.out.println(menu);
		}
		
		mv.addObject("menuList", menuList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("/menu/admin");
		
		return mv;
	}
	
//	@GetMapping("/{menuCode}")
//	@ResponseBody
//	public MenuAndCategoryDTO getMenuByCode(@PathVariable int menuCode) {
//		return menuService.findMenuByCode(menuCode);
//	}
	
	/* 메뉴 등록 페이지 */
	@GetMapping("/regist")
	public void registPage() {}
	
	/* 카테고리 이름을 json문자열로 반환받기 위한 설정 */
	@GetMapping(value="/category", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String findCategoryList() {
		
		Gson gson = new GsonBuilder()
				   .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				   .setPrettyPrinting()
				   .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				   .serializeNulls()
				   .disableHtmlEscaping()
				   .create();
		
		return gson.toJson(menuService.findAllCategory());
	}
	
	/* 메뉴 등록 요청 */
	@PostMapping("/regist")
	public String registMenu(@RequestParam MultipartFile menuImg, @ModelAttribute MenuDTO newMenu, RedirectAttributes rttr)throws UnsupportedEncodingException {
//		(value = "file", required = false)
		/* 파일 저장 */
		String filePath = "C:\\restaurant2\\chap01-practice-restaurant\\src\\main\\resources\\static\\menuImages";
		System.out.println("넘어온 menuImg: " + menuImg);
		/* uploadFile 폴더 생성 */
		File mkdir = new File(filePath);
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		
		/* 파일명 변경 */
		String originFileName = menuImg.getOriginalFilename();
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		String saveName = UUID.randomUUID().toString().replace("-", "") + ext;
		
		if(menuImg != null) {
			
			try {
				menuImg.transferTo(new File(filePath + "/" + saveName));
			} catch (IllegalStateException | IOException e){
				e.printStackTrace();
				
				new File(filePath + "/" + saveName).delete();
				rttr.addAttribute("fileUploadFailMessage", "파일 업로드 실패");
			}
		}
		
		newMenu.setOriginUrl(originFileName);
		newMenu.setPictureUrl(saveName);
		
		System.out.println("등록 할 newMenu" + newMenu);
		menuService.registNewMenu(newMenu);
		
		rttr.addFlashAttribute("registSuccessMessage", "메뉴 등록 완료");
//		mv.setViewName("redirect:/menu/admin");
		
		return "redirect:/menu/admin";
	}
	
	/* 메뉴 수정 페이지 */
	@GetMapping("/modify/{menuCode}")
	public ModelAndView modifyPage(ModelAndView mv, @PathVariable int menuCode) {
		
		MenuAndCategoryDTO menu = menuService.findMenuByCode(menuCode);
		
//		String newUrl = menu.getPictureUrl();
//		String pictureUrl = "/menuImages/" + newUrl;
//		
//		menu.setPictureUrl(pictureUrl);
		
		mv.addObject("menu", menu);
		mv.setViewName("/menu/modify");
		
		System.out.println("수정페이지 mv에 닮긴 값: " + mv);
		
		return mv;
	}
	
	/* 메뉴 수정 요청 */
	@PostMapping("/modify")
	public String modifyMenu(@RequestParam MultipartFile menuImg, RedirectAttributes rttr, @ModelAttribute MenuDTO modifyMenu) {
		
		String oriName = null;
		String oriImage = null;
		
			
		MenuAndCategoryDTO menu = menuService.findMenuByCode(modifyMenu.getMenuCode());
		System.out.println("menu 값: " + menu);
		
		oriName = menu.getOriginUrl();
		oriImage = menu.getPictureUrl();
		System.out.println("oriName 값: " + oriName);
		System.out.println("oriImage 값: " + oriImage);
		
		/* 파일 저장 */
		String filePath = "C:\\restaurant2\\chap01-practice-restaurant\\src\\main\\resources\\static\\menuImages";
		
		System.out.println("넘어온 menuImg: " + menuImg);
		
		/* uploadFile 폴더 생성 */
		File mkdir = new File(filePath);
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		
		if(menuImg != null && menuImg.getOriginalFilename() != oriName) {
			
			/* 파일명 변경 */
			String originFileName = menuImg.getOriginalFilename();
			String ext = originFileName.substring(originFileName.lastIndexOf("."));
			String saveName = UUID.randomUUID().toString().replace("-", "") + ext;
			
			System.out.println("넘어 온 originFilename: " + originFileName);
			
			try {
				menuImg.transferTo(new File(filePath + "/" + saveName));
			
				if(oriImage != null) {
					boolean isDelete = FileUploadUtils.deleteFile(filePath, oriImage);
				}
			} catch (IOException e){
				e.printStackTrace();
				new File(filePath + "/" + saveName).delete();
//				throw new RuntimeException(e);
				
			rttr.addAttribute("fileUploadFailMessage", "파일 업로드 실패");
		}
			
			modifyMenu.setOriginUrl(originFileName);
			modifyMenu.setPictureUrl(saveName);
		
			System.out.println("이미지 바뀐 modifyMenu 값: " + modifyMenu);
		
		} else if(menuImg != null && menuImg.getOriginalFilename() == oriName){
			
			modifyMenu.setOriginUrl(oriName);
			modifyMenu.setPictureUrl(oriImage);
			
			System.out.println("이미지 그대로인 modifyMenu 값: " + modifyMenu);
		}
			
		menuService.modifyMenu(modifyMenu, menuImg);
		
		rttr.addFlashAttribute("modifySuccessMessage", "메뉴 수정 완료");
		
		return "redirect:/menu/" + modifyMenu.getMenuCode();
	}
	
//	@GetMapping("/delete")
//	public ModelAndView deletePage(ModelAndView mv) {
//		
//		List<MenuAndCategoryDTO> menuList = menuService.findMenuList();
//		
//		mv.addObject("menuList", menuList);
//		mv.setViewName("/menu/delete");
//		
//		System.out.println("menuList에 들어있는 값 :" + menuList);
//		return mv;
//	}
	
	/* 메뉴 삭제 요청 */
	@PostMapping("/delete/{menuCode}")
	public String deleteMenu(RedirectAttributes rttr, @PathVariable int menuCode) {
		
		menuService.deleteMenu(menuCode);
		
		rttr.addFlashAttribute("deleteSuccessMessage", "메뉴 삭제 성공");
		
		System.out.println("menuCode: " + menuCode);
		return "redirect:/menu/admin";
	}
	
	@GetMapping("/search")
	public ModelAndView searchPage(HttpServletRequest request, ModelAndView mv) {

		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		int totalCount = menuService.selectTotalCount(searchCondition, searchValue);

		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 10;		//얘도 파라미터로 전달받아도 된다.

		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 5;

		/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
		SelectCriteria selectCriteria = null;
		if(searchValue != null && !"".equals(searchValue)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		System.out.println(selectCriteria);

		List<MenuAndCategoryDTO> menuList = menuService.searchMenuList(selectCriteria);

		for(MenuAndCategoryDTO menu : menuList) {
			System.out.println(menu);
		}

		mv.addObject("menuList", menuList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("menu/search");

		return mv;
	}
}
