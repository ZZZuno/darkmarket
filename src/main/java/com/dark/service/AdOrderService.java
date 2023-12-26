package com.dark.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dark.domain.OrderDetailInfoVO;
import com.dark.domain.OrderDetailProductVO;
import com.dark.domain.OrderVO;
import com.dark.dto.Criteria;

public interface AdOrderService {

	List<OrderVO> order_list(Criteria cri, String start_date, String end_date);
	
	int getTotalCount(Criteria cri, String start_date, String end_date);
	
	List<OrderDetailInfoVO> orderDetailInfo1(Long ord_code);
	
	List<OrderDetailProductVO> orderDetailInfo2(Long ord_code);
	
	void order_product_delete(@Param("ord_code") Long ord_code, @Param("item_num") Integer item_num);
	
}
