package com.dark.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dark.domain.OrderDetailInfoVO;
import com.dark.domain.OrderDetailProductVO;
import com.dark.domain.OrderVO;
import com.dark.dto.Criteria;

public interface AdOrderMapper {

	List<OrderVO> order_list(@Param("cri") Criteria cri, @Param("start_date") String start_date, @Param("end_date") String end_date);
	
	int getTotalCount(@Param("cri") Criteria cri, @Param("start_date") String start_date, @Param("end_date") String end_date);
	
	// 새로운 클래스 생성.
	List<OrderDetailInfoVO> orderDetailInfo1(Long ord_code);
	
	// 기존클래스 이용 OrderDetailVO, ProductVO 필드가 있는 클래스. mybatis의 resultMap 사용
	List<OrderDetailProductVO> orderDetailInfo2(Long ord_code);
	
	void order_product_delete(@Param("ord_code") Long ord_code, @Param("item_num") Integer item_num);
}
