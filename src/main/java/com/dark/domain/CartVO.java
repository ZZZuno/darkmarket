package com.dark.domain;

import lombok.Data;

@Data
public class CartVO {

	private Long cart_code;
	private Integer item_num;
	private String dark_id;
	private int cart_amount;
}
