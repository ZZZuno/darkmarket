package com.dark.domain;

import java.util.Date;

import lombok.Data;

@Data
public class AdminVO {

	private String ad_id;
	private String ad_pw;
	private Date   ad_visit_date;
}
