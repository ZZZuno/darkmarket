package com.dark.mapper;

import java.util.List;

import com.dark.domain.ItemVO;
import com.dark.dto.Criteria;

public interface AdProductMapper {

	void pro_insert(ItemVO vo);
	
	List<ItemVO> pro_list(Criteria cri);
	
	int getTotalCount(Criteria cri);
}
