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
import com.dark.util.FileUtils;

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
	private String uploadPath;
	
	// CKEDitor에서 사용되는 업로드 폴더경로
	@Resource(name = "uploadCKPath") // servlet-context.xml의 bean이름 참조를 해야함.
	private String uploadCKPath;
	
	@GetMapping("/pro_insert")
	public void pro_insert() {
//		log.info("상품등록 페이지 호출");
		
	}
	
	//상품정보 저장
		// 파일업로드 기능 : 1)스프링에서 내장된 기본라이브러리. servlet-context.xml 에서 MultipartFile 대한 bean등록작업 
		//              2)외부라이브러리(commons-fileupload)를 이용(pom.xml). servlet-context.xml 에서 MultipartFile 대한 bean등록작업 
		// MultipartFile uploadFile : <input type="file" class="form-control" name="uploadFile" id="uploadFile">
		// MultipartFile uploadFile 필드명과 input태그의 name="uploadFile" 이름이 일치.
	
	@PostMapping("/pro_insert")
	public String pro_insert(ItemVO vo, MultipartFile uploadFile, RedirectAttributes rttr) {
		
		
		
		String dateFolder = FileUtils.getDateFolder();
		String savedFileName = FileUtils.uploadFile(uploadPath, dateFolder, uploadFile);
		
		vo.setItem_img(savedFileName);
		vo.setItem_up_folder(dateFolder);
		
		log.info("상품정보: " + vo);
		
		//db에 저장
		adProductService.pro_insert(vo);
		
		return "redirect:/admin/product/pro_list";
	}
	
	
}
