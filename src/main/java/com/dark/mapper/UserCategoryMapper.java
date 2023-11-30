package com.dark.mapper;

import java.util.List;

import com.dark.domain.CategoryVO;

public interface UserCategoryMapper {

	List<CategoryVO> getSecondCategoryList(Integer cg_parent_code);
}
