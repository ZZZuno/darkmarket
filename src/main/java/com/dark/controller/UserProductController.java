package com.dark.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dark.domain.ItemVO;
import com.dark.dto.Criteria;
import com.dark.dto.PageDTO;
import com.dark.service.UserProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/user/product/*")
@RequiredArgsConstructor
@Log4j
@Controller
public class UserProductController {

	private final UserProductService userProductService;
	
	// 상품리스트. (목록과페이징)
		@GetMapping("/pro_list")
		public void pro_list(Criteria cri, Model model) throws Exception {
			
			cri.setAmount(2);
			
			List<ItemVO> pro_list = adProductService.pro_list(cri);
			
			// 날짜폴더의 역슬래시 슬래시로 바꾸는 작업
			pro_list.forEach(vo -> {
				vo.setItem_up_folder(vo.getItem_up_folder().replace("\\", "/"));
		  	});
			
			model.addAttribute("pro_list", pro_list);
			
			int totalCount = adProductService.getTotalCount(cri);
			model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
		}
}
