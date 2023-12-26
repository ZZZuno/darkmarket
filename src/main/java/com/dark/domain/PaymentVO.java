package com.dark.domain;

import java.util.Date;

import lombok.Data;

// pay_code, ord_code, mbsp_id, pay_method, 
// pay_date, pay_tot_price, pay_nobank_price, 
// pay_rest_price, pay_nobank_user, pay_nobank, pay_memo 
@Data
public class PaymentVO {

	private Integer pay_code;
	private Long ord_code;
	private String dark_id;
	private String pay_method;
	private Date pay_date;
	private Integer pay_tot_price;
	private Integer pay_nobank_price;
	private String pay_nobank_user;
	private String pay_nobank;
	
	private String pay_memo;
	private String pay_bankaccount;
}
