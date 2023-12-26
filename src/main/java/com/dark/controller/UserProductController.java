package com.dark.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dark.domain.ItemVO;
import com.dark.dto.Criteria;
import com.dark.dto.PageDTO;
import com.dark.service.UserProductService;
import com.dark.util.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/user/product/*")
@RequiredArgsConstructor
@Log4j
@Controller
public class UserProductController {

	private final UserProductService userProductService;
	
	// 메인 및 썸네일이미지 업로드 폴더경로 주입작업.
		@Resource(name = "uploadPath") // servlet-context.xml의 bean이름 참조를 해야함.
		private String uploadPath;
	
	// 상품리스트. (목록과페이징)
		@GetMapping("/pro_list")
		public String pro_list(Criteria cri, @ModelAttribute("cg_code") Integer cg_code, @ModelAttribute("cg_name") String cg_name, Model model) throws Exception {
			
			cri.setAmount(8);
			
			List<ItemVO> pro_list = userProductService.pro_list(cg_code, cri);
			
			// 날짜폴더의 역슬래시 슬래시로 바꾸는 작업
			pro_list.forEach(vo -> {
				vo.setItem_up_folder(vo.getItem_up_folder().replace("\\", "/"));
		  	});
			
			model.addAttribute("pro_list", pro_list);
			
			int totalCount = userProductService.getTotalCount(cg_code);
			model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
			
			return "/user/product/pro_list";
		}
		
		@ResponseBody
		@GetMapping("/imageDisplay")
		public ResponseEntity<byte[]> imageDisplay(String dateFolderName, String fileName) throws Exception {
			
			return FileUtils.getFile(uploadPath + dateFolderName, fileName);
		}
		
		@GetMapping("/pro_detail")
		public void pro_detail(Criteria cri, Integer cg_code, @ModelAttribute("cg_name") String cg_name, Integer item_num, Model model) {
			
			ItemVO itemVO = userProductService.pro_detail(item_num);
			
			itemVO.setItem_up_folder(itemVO.getItem_up_folder().replace("\\", "/"));
			
			model.addAttribute("itemVO", itemVO);
			
		}
		
}
	
