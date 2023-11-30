package com.dark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dark.domain.CategoryVO;
import com.dark.mapper.UserCategoryMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserCategoryServiceImpl implements UserCategoryService {

	private final UserCategoryMapper userCategoryMapper;

	@Override
	public List<CategoryVO> getSecondCategoryList(Integer cg_parent_code) {
		// TODO Auto-generated method stub
		return userCategoryMapper.getSecondCategoryList(cg_parent_code);
	}
}
