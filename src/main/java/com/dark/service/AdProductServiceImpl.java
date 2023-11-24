package com.dark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dark.domain.ItemVO;
import com.dark.dto.Criteria;
import com.dark.mapper.AdProductMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdProductServiceImpl implements AdProductService {

	private final AdProductMapper adProductMapper;

	@Override
	public void pro_insert(ItemVO vo) {
		adProductMapper.pro_insert(vo);
		
	}

	@Override
	public List<ItemVO> pro_list(Criteria cri) {
		
		return adProductMapper.pro_list(cri);
	}

	@Override
	public int getTotalCount(Criteria cri) {
		
		return adProductMapper.getTotalCount(cri);
	}

	@Override
	public void pro_checked_modify(List<Integer> item_num_arr, List<Integer> item_price_arr,
			List<String> item_buy_arr) {

		for(int i=0; i<item_num_arr.size(); i++) {
			adProductMapper.pro_checked_modify(item_num_arr.get(i), item_price_arr.get(i), item_buy_arr.get(i));
		}
		
	}


}
