package com.dark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dark.domain.OrderDetailInfoVO;
import com.dark.domain.OrderDetailProductVO;
import com.dark.domain.OrderVO;
import com.dark.dto.Criteria;
import com.dark.mapper.AdOrderMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdOrderServiceImpl implements AdOrderService {

	private final AdOrderMapper adOrderMapper;

	@Override
	public List<OrderVO> order_list(Criteria cri, String start_date, String end_date) {
		
		return adOrderMapper.order_list(cri, start_date, end_date);
	}

	@Override
	public int getTotalCount(Criteria cri, String start_date, String end_date) {
		
		return adOrderMapper.getTotalCount(cri, start_date, end_date);
	}

	@Override
	public List<OrderDetailInfoVO> orderDetailInfo1(Long ord_code) {
		// TODO Auto-generated method stub
		return adOrderMapper.orderDetailInfo1(ord_code);
	}

	@Override
	public void order_product_delete(Long ord_code, Integer item_num) {
		
		adOrderMapper.order_product_delete(ord_code, item_num);
		
	}

	// mybatis 에서 resultMap 사용
	@Override
	public List<OrderDetailProductVO> orderDetailInfo2(Long ord_code) {
		
		return adOrderMapper.orderDetailInfo2(ord_code);
	}


}
