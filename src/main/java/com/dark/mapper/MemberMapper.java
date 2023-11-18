package com.dark.mapper;

import com.dark.domain.MemberVO;

public interface MemberMapper {
	
	String idCheck(String dark_id);
	
	void join(MemberVO vo);
	
	MemberVO login(String dark_id);
	
	void loginTimeUpdate(String dark_id);

}
