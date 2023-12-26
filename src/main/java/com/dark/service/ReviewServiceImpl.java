package com.dark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dark.domain.ReviewVO;
import com.dark.dto.Criteria;
import com.dark.mapper.ReviewMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

	private final ReviewMapper reviewMapper;

	@Override
	public void review_insert(ReviewVO vo) {
		reviewMapper.review_insert(vo);
		
	}

	@Override
	public List<ReviewVO> list(Integer item_num, Criteria cri) {
		
		return reviewMapper.list(item_num, cri);
	}

	@Override
	public int listCount(Integer item_num) {
		
		return reviewMapper.listCount(item_num);
	}

	@Override
	public void modify(ReviewVO vo) {
		reviewMapper.modify(vo);
		
	}

	@Override
	public void delete(Long rew_num) {
		reviewMapper.delete(rew_num);
		
	}
}
