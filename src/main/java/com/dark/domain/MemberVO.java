package com.dark.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	
//  dark_id, dark_name, dark_email, dark_password, 
//	dark_zipcode, dark_addr, dark_deaddr, dark_phone, 
//	dark_point, dark_lastlogin, dark_datesub, dark_updatedate
	
	private String dark_id;
	private String dark_name;
	private String dark_email;
	private String dark_password;
	private String dark_zipcode;
	private String dark_addr;
	private String dark_deaddr;
	private String dark_phone;
	private int	   dark_point;
	private Date   dark_lastlogin;
	private Date   dark_datesub;
	private Date   updatedate;
}	
