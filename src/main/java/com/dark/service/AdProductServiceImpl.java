package com.dark.service;

import org.springframework.stereotype.Service;

import com.dark.domain.ItemVO;
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
}
