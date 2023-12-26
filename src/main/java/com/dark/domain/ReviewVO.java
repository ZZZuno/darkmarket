package com.dark.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewVO {

	private Long rew_num;
	private String dark_id;
	private Integer item_num;
    private String rew_content;
    private int rew_score;
    private Date rew_regdate;
}
