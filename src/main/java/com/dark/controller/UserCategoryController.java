package com.dark.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dark.domain.CategoryVO;
import com.dark.service.UserCategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/category/*")
@RequiredArgsConstructor
@Log4j
@RestController
public class UserCategoryController {

	private final UserCategoryService userCategoryService;
	
	@ResponseBody
	@GetMapping("/secondCategory/{cg_parent_code}")
	public ResponseEntity<List<CategoryVO>> secondCategory(@PathVariable("cg_parent_code") Integer cg_parent_code) throws Exception {
		
		ResponseEntity<List<CategoryVO>> entity = null;
		
		entity = new ResponseEntity<List<CategoryVO>>(userCategoryService.getSecondCategoryList(cg_parent_code), HttpStatus.OK);
		
		return entity;
	}
}
