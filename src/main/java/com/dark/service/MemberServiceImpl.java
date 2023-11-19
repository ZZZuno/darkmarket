package com.dark.service;

import org.springframework.stereotype.Service;

import com.dark.domain.MemberVO;
import com.dark.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	private final MemberMapper memberMapper;

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
}
