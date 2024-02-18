package com.dark.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dark.domain.OrderVO;
import com.dark.domain.PaymentVO;

public interface OrderMapper {

	int getOrderSeq();
	
	void order_insert(OrderVO o_vo);
	
	void order_detail_insert(@Param("ord_code") Long ord_code, @Param("dark_id") String dark_id);
	
	void cart_del(String dark_id);
	
	void payment_insert(PaymentVO vo);
	
	List<OrderVO> order_list(String dark_id);
}
