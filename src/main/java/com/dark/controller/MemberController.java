package com.dark.controller;

import org.springframework.stereotype.Controller;

import com.dark.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@Log4j
@Controller
public class MemberController {

	private final MemberService memberService;
}
