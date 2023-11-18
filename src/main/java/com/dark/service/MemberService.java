package com.dark.service;

import com.dark.domain.MemberVO;

public interface MemberService {

	String idCheck(String dark_id);
	
	void join(MemberVO vo);
	
	MemberVO login(String dark_id);
	
	void loginTimeUpdate(String dark_id);
}
