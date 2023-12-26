package com.dark.service;


import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dark.domain.MemberVO;
import com.dark.dto.Criteria;
import com.dark.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public String idCheck(String dark_id) {
		
		return memberMapper.idCheck(dark_id);
	}

	@Override
	public void join(MemberVO vo) {
		memberMapper.join(vo);
		
	}

	@Override
	public MemberVO login(String dark_id) {
		// TODO Auto-generated method stub
		return memberMapper.login(dark_id);
	}

	@Override
	public void loginTimeUpdate(String dark_id) {
		// TODO Auto-generated method stub
		memberMapper.loginTimeUpdate(dark_id);
	}

	@Override
	public void modify(MemberVO vo) {
		
		memberMapper.modify(vo);
		
	}

	@Override
	public void delete(String dark_id) {
		memberMapper.delete(dark_id);
		
	}

	@Override
	public MemberVO idFind(String dark_name) {
		
		return memberMapper.idFind(dark_name);
	}

	@Override
	public void updatePassword(String id, String newPassword) {
	    // 비밀번호 암호화
	    String encryptedPassword = passwordEncoder.encode(newPassword);
	    
	    // 데이터베이스에 업데이트
	    // memberMapper를 사용하여 데이터베이스에 업데이트하는 로직 구현
	    memberMapper.updatePassword(id, encryptedPassword);
	}

	@Override
	public List<MemberVO> member_list(Criteria cri) {
		
		return memberMapper.member_list(cri);
	}

	@Override
	public int getTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		return memberMapper.getTotalCount(cri);
	}
}
