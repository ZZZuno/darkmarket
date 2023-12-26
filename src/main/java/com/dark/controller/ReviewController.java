package com.dark.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dark.domain.MemberVO;
import com.dark.domain.ReviewVO;
import com.dark.dto.Criteria;
import com.dark.dto.PageDTO;
import com.dark.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@RequestMapping("/user/review/*")
@Log4j
@RestController
public class ReviewController {

	private final ReviewService reviewService;
	
	@PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> review_insert(@RequestBody ReviewVO vo, HttpSession session) throws Exception {
		
//		log.info("리뷰: " + vo);
		
		String dark_id = ((MemberVO) session.getAttribute("loginStatus")).getDark_id();
		vo.setDark_id(dark_id);
		
		ResponseEntity<String> entity = null;
		
		reviewService.review_insert(vo);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	@PutMapping(value = "/modify", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody ReviewVO vo, HttpSession session) throws Exception {
		
		String dark_id = ((MemberVO) session.getAttribute("loginStatus")).getDark_id();
		vo.setDark_id(dark_id);
		
		ResponseEntity<String> entity = null;
		
		// db 저장
		reviewService.modify(vo);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	// @PathVariable = RestAPI 개발형태 주소를 사용하기 위해 쓰는 어노테이션
	@GetMapping("/list/{item_num}/{page}")
	public ResponseEntity<Map<String, Object>> list(@PathVariable("item_num") Integer item_num, @PathVariable("page") Integer page) {
		
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 후기목록 db에서 불러오기
		Criteria cri = new Criteria();
		cri.setPageNum(page);
		cri.setAmount(2);
		
		// db (페이징기능 포함)
		List<ReviewVO> list = reviewService.list(item_num, cri);
		
		int listCount = reviewService.listCount(item_num);
		PageDTO pageMaker = new PageDTO(cri, listCount);
		
		map.put("list", list);
		map.put("pageMaker", pageMaker);
		
		entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
		return entity;
	}
	
	@DeleteMapping("/delete/{rew_num}")
	public ResponseEntity<String> delete(@PathVariable("rew_num") Long rew_num) throws Exception {
		
		ResponseEntity<String> entity = null;
		
		// db작업
		reviewService.delete(rew_num);
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
}
