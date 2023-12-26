package com.dark.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dark.domain.CartVO;
import com.dark.domain.MemberVO;
import com.dark.dto.CartDTOList;
import com.dark.service.CartService;
import com.dark.util.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequiredArgsConstructor
@RequestMapping("/user/cart/*")
@Controller
public class CartController {
	
	private final CartService cartService;
	
	// 메인및썸네일 이미지업로드 폴더경로 주입작업
			@Resource(name = "uploadPath") // servlet-context.xml 의 bean이름 참조를 해야 함.
			private String uploadPath;
	
	@ResponseBody
	@PostMapping("/cart_add")
	public ResponseEntity<String> cart_add(CartVO vo, HttpSession session) throws Exception {
		
		log.info("장바구니: " + vo);
		
		ResponseEntity<String> entity = null;
		
		String dark_id = ((MemberVO) session.getAttribute("loginStatus")).getDark_id();
		vo.setDark_id(dark_id);
		
		cartService.cart_add(vo);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		
		
		return entity;
	}
	
	//장바구니 목록
	@GetMapping("/cart_list")
	public void cart_list(HttpSession session, Model model) throws Exception {
		
		String dark_id = ((MemberVO) session.getAttribute("loginStatus")).getDark_id();
		
		List<CartDTOList> cart_list = cartService.cart_list(dark_id);
		
		int cart_total_price = 0;
		
		for(int i=0; i<cart_list.size(); i++) {
			CartDTOList vo = cart_list.get(i);
			
			vo.setItem_up_folder(vo.getItem_up_folder().replace("\\", "/"));
			
			
			cart_total_price += (vo.getItem_price() * vo.getCart_amount());
			
		}
		model.addAttribute("cart_list", cart_list);
		model.addAttribute("cart_total_price", cart_total_price);
	}

	// 장바구니 이미지
		//상품리스트에서 보여줄 이미지.  <img src="매핑주소">
			@ResponseBody
			@GetMapping("/imageDisplay") // /user/cart/imageDisplay?dateFolderName=값1&fileName=값2
			public ResponseEntity<byte[]> imageDisplay(String dateFolderName, String fileName) throws Exception {
				
				return FileUtils.getFile(uploadPath + dateFolderName, fileName);
			}
			
			@PostMapping("/cart_amount_change")
			public ResponseEntity<String> cart_amount_change(Long cart_code, int cart_amount) throws Exception {
				
				ResponseEntity<String> entity = null;
				
				cartService.cart_amount_change(cart_code, cart_amount);
				
				entity = new ResponseEntity<String>("success", HttpStatus.OK);
				
				return entity;
			}
			
			@PostMapping("/cart_list_del")
			public ResponseEntity<String> cart_list_del(Long cart_code) throws Exception {
				
				ResponseEntity<String> entity = null;
				
				cartService.cart_list_del(cart_code);
				
				entity = new ResponseEntity<String>("success", HttpStatus.OK);
				
				return entity;
			}
}
