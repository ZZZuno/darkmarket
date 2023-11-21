package com.dark.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dark.domain.ItemVO;
import com.dark.service.AdCategoryService;
import com.dark.service.AdProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@Log4j
@RequestMapping("/admin/product/*")
@Controller
public class AdProductController {

	private final AdProductService adProductService;
	private final AdCategoryService adCategoryService;
	
	// 메인 및 썸네일이미지 업로드 폴더경로 주입작업.
	@Resource(name = "uploadPath") // servlet-context.xml의 bean이름 참조를 해야함.
	@GetMapping("/pro_insert")
	public void pro_insert() {
//		log.info("상품등록 페이지 호출");
		
	}
	
	@PostMapping("/pro_insert")
	public String pro_insert(ItemVO vo, MultipartFile file, RedirectAttributes rttr) {
		
		
		return null;
	}
}
