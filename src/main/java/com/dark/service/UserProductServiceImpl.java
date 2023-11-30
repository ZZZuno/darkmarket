package com.dark.service;

import org.springframework.stereotype.Service;

import com.dark.mapper.UserProductMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserProductServiceImpl implements UserProductService {

	private final UserProductMapper userProductMapper;
}
