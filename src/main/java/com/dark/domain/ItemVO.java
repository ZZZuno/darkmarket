package com.dark.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ItemVO {

	private Integer item_num;
	
	private Integer cg_code;
	private String item_name;
	private int item_price;
	private int item_discount;
	private String item_publisher;
	private String item_content;
	
	private String item_up_folder;
	private String item_img;
	
	private int item_amount;
	private String item_buy;
	
	private Date item_date;
	private Date item_updatedate;
}
