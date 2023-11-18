package com.dark.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dark.dto.EmailDTO;
import com.dark.service.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@RequestMapping("/email/*")
@Log4j
@RestController
public class EmailController {

	private final EmailService emailService;
	
	@GetMapping("/authcode")
	public ResponseEntity<String> authSend(EmailDTO dto, HttpSession session) {
	
		log.info("이메일정보: " + dto);
		
		ResponseEntity<String> entity = null;
		
		//인증코드 6자리 랜덤생성
		String authCode = "";
		for(int i=0; i<6; i++) {
			authCode += String.valueOf((int)(Math.random() * 10));
		}
		log.info("인증코드: " + authCode);
		
		// 사용자에게 발급한 인증코드 비교목적으로 세션형태로 저장
		session.setAttribute("authCode", authCode);
		
		try {
			emailService.sendMail(dto, authCode);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
	@GetMapping("/confirmAuthcode")
	public ResponseEntity<String> confirmAuthcode(String authCode, HttpSession session) {
		
		ResponseEntity<String> entity = null;
		
		if(session.getAttribute("authCode") != null) {
			if(authCode.equals(session.getAttribute("authCode"))) {
				entity = new ResponseEntity<String>("success", HttpStatus.OK);
			}else {
				entity = new ResponseEntity<String>("fail", HttpStatus.OK);
			}
		}else {
			entity = new ResponseEntity<String>("request", HttpStatus.OK);
		}
		
		return entity;
	}

	
}
