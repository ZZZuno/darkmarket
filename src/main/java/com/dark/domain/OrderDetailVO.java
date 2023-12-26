package com.dark.domain;

import lombok.Data;

@Data
public class OrderDetailVO {

   private Long    	 ord_code;
   private Integer   item_num;
   private int       dt_amount;
   private int   	 dt_price;
}