package com.dark.mapper;

import java.util.List;

import com.dark.domain.CategoryVO;

public interface AdCategoryMapper {

	List<CategoryVO> getFirstCategoryList();
	
	List<CategoryVO> getSecondCategoryList(Integer cg_parent_code);
	
	CategoryVO get(Integer cg_code);
}
