package com.dark.mapper;

import com.dark.domain.AdminVO;

public interface AdminMapper {

	AdminVO admin_ok(String ad_id);
	
	void login_date(String ad_id);
}
