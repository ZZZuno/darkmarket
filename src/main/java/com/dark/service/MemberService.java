package com.dark.service;

import org.apache.ibatis.annotations.Param;

import com.dark.domain.MemberVO;
import com.dark.dto.IdFindDTO;

public interface MemberService {

	String idCheck(String dark_id);
	
	void join(MemberVO vo);
	
	MemberVO login(String dark_id);
	
	void loginTimeUpdate(String dark_id);
	
	void modify(MemberVO vo);
	
	void delete(String dark_id);
	
	MemberVO idFind(String dark_name);

	void updatePassword(String id, String newPassword);
}
