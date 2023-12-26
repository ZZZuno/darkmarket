package com.dark.controller;

//import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dark.domain.ItemVO;
import com.dark.dto.Criteria;
import com.dark.dto.PageDTO;
import com.dark.service.UserProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */

@RequiredArgsConstructor
@Log4j
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private final UserProductService userProductService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root(String item_title, Criteria cri, Locale locale, Model model) {
		cri.setAmount(8);
		
		List<ItemVO> pro_title = userProductService.pro_title(cri);
		
		// 날짜폴더의 역슬래시 슬래시로 바꾸는 작업
		pro_title.forEach(vo -> {
			vo.setItem_up_folder(vo.getItem_up_folder().replace("\\", "/"));
	  	});
		
		model.addAttribute("pro_list", pro_title);
		
		int totalCount = userProductService.getTitleTotalCount(item_title);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
		
		return "index"; // index.jsp
	}
	
}
