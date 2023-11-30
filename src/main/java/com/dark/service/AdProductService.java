package com.dark.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dark.domain.ItemVO;
import com.dark.dto.Criteria;

public interface AdProductService {

	void pro_insert(ItemVO vo);
	
	List<ItemVO> pro_list(Criteria cri);
	
	int getTotalCount(Criteria cri);
	
	ItemVO pro_edit(Integer item_num);
	
	void pro_checked_modify(List<Integer> item_num_arr, List<Integer> item_price_arr, List<String> item_buy_arr);
	
	void pro_edit_ok(ItemVO vo);
	
	void pro_delete(Integer item_num);
}
