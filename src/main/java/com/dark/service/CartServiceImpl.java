package com.dark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dark.domain.CartVO;
import com.dark.dto.CartDTOList;
import com.dark.mapper.CartMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

	private final CartMapper cartMapper;

	@Override
	public void cart_add(CartVO vo) {

		cartMapper.cart_add(vo);
		
	}

	@Override
	public List<CartDTOList> cart_list(String dark_id) {
		// TODO Auto-generated method stub
		return cartMapper.cart_list(dark_id);
	}

	@Override
	public void cart_amount_change(Long cart_code, int cart_amount) {
		cartMapper.cart_amount_change(cart_code, cart_amount);
		
	}

	@Override
	public void cart_list_del(Long cart_code) {
		cartMapper.cart_list_del(cart_code);
		
	}
}
