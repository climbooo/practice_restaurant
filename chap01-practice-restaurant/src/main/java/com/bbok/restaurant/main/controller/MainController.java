package com.bbok.restaurant.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bbok.restaurant.menu.service.MenuService;


@Controller
public class MainController {

	@Autowired	
	ResourceLoader resourceLoader;
	
    @GetMapping(value = {"/", "/main"})
    public String main() throws Exception{
    	Resource resource = resourceLoader.getResource("classpath:/static/upload/test.txt");
    	System.out.println(resource.exists());  //resource 존재 확인
        System.out.println(resource.getDescription());
        System.out.println(resource.getFile()); //파일 객체
        System.out.println(resource.getFilename()); //파일 이름
        System.out.println(resource.getInputStream()); //InputStream 객체
        System.out.println(resource.getURI().getPath()); //URL 객체
        return "main/main";
    }
//	private final MenuService menuService;
//	
//	
//	@Autowired
//    public MainController(MenuService menuService) {
//    	this.menuService = menuService;
//	}

//	@GetMapping(value= {"/", "/main"})

	@PostMapping(value="/")
    public String redirectMain() {

        return "redirect:/";
    }
}
