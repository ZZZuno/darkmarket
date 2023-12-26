package com.dark.domain;

import lombok.Data;


// 관리자기능
// 주문상세정보 저장목적.(주문상세테이블과 상품테이블 조인 결과)

@Data
public class OrderDetailInfoVO {

	   private  Long    ord_code;
	   private  Integer item_num;
	   private	String	item_name;
	   private	int		item_price;
	   private  int     dt_amount;
	   
	   private  int		ord_price;	// 주문금액(pro_price * dt_amount)
	   
	   private  String	item_up_folder;
	   private	String	item_img;
}
