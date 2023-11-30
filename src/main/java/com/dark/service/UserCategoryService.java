package com.dark.service;

import java.util.List;

import com.dark.domain.CategoryVO;

public interface UserCategoryService {

	List<CategoryVO> getSecondCategoryList(Integer cg_parent_code);
}
