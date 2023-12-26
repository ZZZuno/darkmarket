package com.dark.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dark.domain.CartVO;
import com.dark.dto.CartDTOList;

public interface CartService {

	void cart_add(CartVO vo);
	
	List<CartDTOList> cart_list(String dark_id);
	
	void cart_amount_change(@Param("cart_code") Long cart_code, @Param("cart_amount") int cart_amount);
	
	void cart_list_del(Long cart_code);
}
