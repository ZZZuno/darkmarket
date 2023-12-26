package com.dark.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dark.domain.ReviewVO;
import com.dark.dto.Criteria;

public interface ReviewService {

	void review_insert(ReviewVO vo);
	
	List<ReviewVO> list(@Param("item_num") Integer item_num, @Param("cri") Criteria cri);
	
	int listCount(Integer item_num);
	
	void modify(ReviewVO vo);
	
	void delete(Long rew_num);
}
