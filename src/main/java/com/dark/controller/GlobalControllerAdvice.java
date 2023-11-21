package com.dark.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.dark.domain.CategoryVO;
import com.dark.service.AdCategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@Log4j
@ControllerAdvice(basePackages = {"com.dark.controller"})
public class GlobalControllerAdvice {

	private final AdCategoryService adCategoryService;
	
	@ModelAttribute
	public void getFirstCategoryList(Model model) {
		
//		log.info("1차카테고리 리스트");
		
		List<CategoryVO> firstCategoryList = adCategoryService.getFirstCategoryList();
		model.addAttribute("firstCategoryList", firstCategoryList);
	}
	
}
