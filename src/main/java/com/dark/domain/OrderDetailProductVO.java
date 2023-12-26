package com.dark.domain;

import lombok.Data;

@Data
public class OrderDetailProductVO {

	// 기존클래스 필드로 사용. mybatis에서는 resultMap 사용해야 한다.
	private OrderDetailVO orderDetailVO; // collection 으로 표현
	private ItemVO itemVO; // collection 으로 표현
}
