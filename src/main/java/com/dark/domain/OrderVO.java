package com.dark.domain;

import java.util.Date;

import lombok.Data;

@Data
public class OrderVO {

	// ajax로 받아내서 컨트롤러로 보내줘야 함.
private Long   ord_code; // 시퀀스로 처리
private String dark_id; // 세션에서 처리

// 주문정보 페이지(jsp)에서 전송.
private String ord_name; // o
private String ord_zipcode; // o
private String ord_addr_basic; // o
private String ord_addr_detail; // o
private String ord_tel; // o
private int    ord_price; // o


private Date   ord_regdate; // sysdate로 처리

private String ord_status; 
private String payment_status; 
}
