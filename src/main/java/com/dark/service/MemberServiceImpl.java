package com.dark.service;

import org.springframework.stereotype.Service;

import com.dark.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	private final MemberMapper memberMapper;
}
