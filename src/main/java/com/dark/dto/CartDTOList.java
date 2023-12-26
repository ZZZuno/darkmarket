package com.dark.dto;

import lombok.Data;

@Data
public class CartDTOList {

	private Long cart_code;
	private Integer item_num;
	private int cart_amount;
	
	private String item_name;
	private int item_price;
	private String item_up_folder;
	private String item_img;
	private int item_discount;
	
}
