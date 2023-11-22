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
}
