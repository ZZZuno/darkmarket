package com.dark.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dark.domain.OrderVO;
import com.dark.domain.PaymentVO;

public interface OrderService {

	int getOrderSeq();
	
	// 주문하기
	void order_insert(OrderVO o_vo, PaymentVO p_vo);
	
	List<OrderVO> order_list(String dark_id);
}
