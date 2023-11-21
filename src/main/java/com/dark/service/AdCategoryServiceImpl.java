package com.dark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dark.domain.CategoryVO;
import com.dark.mapper.AdCategoryMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdCategoryServiceImpl implements AdCategoryService {

	private final AdCategoryMapper adCategoryMapper;

	@Override
	public List<CategoryVO> getFirstCategoryList() {
		// TODO Auto-generated method stub
		return adCategoryMapper.getFirstCategoryList();
	}

	@Override
	public List<CategoryVO> getSecondCategoryList(Integer cg_parent_code) {
		
		return adCategoryMapper.getSecondCategoryList(cg_parent_code);
	}


}
