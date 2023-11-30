package com.dark.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.dark.domain.ItemVO;
import com.dark.dto.Criteria;

public interface AdProductMapper {

	void pro_insert(ItemVO vo);
	
	List<ItemVO> pro_list(Criteria cri);
	
	int getTotalCount(Criteria cri);
	
	ItemVO pro_edit(Integer item_num);
	
	void pro_checked_modify(
			@Param("item_num") Integer item_num,
			@Param("item_price") Integer item_price,
			@Param("item_buy") String item_buy);
	
	void pro_edit_ok(ItemVO vo);
	
	void pro_delete(Integer item_num);
}
