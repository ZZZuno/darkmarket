package com.dark.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dark.domain.OrderVO;
import com.dark.domain.PaymentVO;
import com.dark.mapper.OrderMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

	private final OrderMapper orderMapper;

	@Override
	public int getOrderSeq() {
		
		return orderMapper.getOrderSeq();
	}

	@Transactional // 트랜잭션 적용.(중요) . root-context.xml 에서 트랜잭션 설정이 되어 있어야, 기능이 동작된다.
	@Override
	public void order_insert(OrderVO o_vo, PaymentVO p_vo) {
		
		orderMapper.order_insert(o_vo);
		orderMapper.order_detail_insert(o_vo.getOrd_code(), o_vo.getDark_id());
		orderMapper.cart_del(o_vo.getDark_id());
		orderMapper.payment_insert(p_vo);
	}
}
