package com.dark.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dark.domain.ItemVO;
import com.dark.dto.Criteria;

public interface UserProductMapper {

	List<ItemVO> pro_list(@Param("cg_code") Integer cg_code, @Param("cri") Criteria cri);
	
	int getTotalCount(Integer cg_code);
}
