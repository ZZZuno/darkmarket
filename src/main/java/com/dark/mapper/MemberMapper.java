package com.dark.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dark.domain.MemberVO;
import com.dark.dto.Criteria;
import com.dark.dto.IdFindDTO;

public interface MemberMapper {
	
	String idCheck(String dark_id);
	
	void join(MemberVO vo);
	
	MemberVO login(String dark_id);
	
	void loginTimeUpdate(String dark_id);

	void modify(MemberVO vo);
	
	void delete(String dark_id);
	
	MemberVO idFind(String dark_name);
	
	void updatePassword(@Param("id")  String id, @Param("encryptedPassword") String encryptedPassword);
	
	List<MemberVO>member_list(Criteria cri);
	
	int getTotalCount(Criteria cri);
}