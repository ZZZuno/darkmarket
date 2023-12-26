package com.dark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dark.domain.ItemVO;
import com.dark.dto.Criteria;
import com.dark.mapper.UserProductMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserProductServiceImpl implements UserProductService {

	private final UserProductMapper userProductMapper;

	@Override
	public List<ItemVO> pro_list(Integer cg_code, Criteria cri) {
	
		return userProductMapper.pro_list(cg_code, cri);
	}

	@Override
	public int getTotalCount(Integer cg_code) {
		
		return userProductMapper.getTotalCount(cg_code);
	}

	@Override
	public ItemVO pro_detail(Integer item_num) {
		
		return userProductMapper.pro_detail(item_num);
	}

	@Override
	public List<ItemVO> pro_title(Criteria cri) {
		
		return userProductMapper.pro_title(cri);
	}

	@Override
	public int getTitleTotalCount(String item_title) {
		
		return userProductMapper.getTitleTotalCount(item_title);
	}




}
