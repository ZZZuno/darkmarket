package com.dark.service;

import com.dark.domain.AdminVO;

public interface AdminService {

	AdminVO admin_ok(String ad_id);
	
	void login_date(String ad_id);
}
