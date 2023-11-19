package com.dark.service;

import org.springframework.stereotype.Service;

import com.dark.domain.AdminVO;
import com.dark.mapper.AdminMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

	private final AdminMapper adminMapper;

	@Override
	public AdminVO admin_ok(String ad_id) {
		
		return adminMapper.admin_ok(ad_id);
	}

	@Override
	public void login_date(String ad_id) {
		adminMapper.login_date(ad_id);
		
	}
}
