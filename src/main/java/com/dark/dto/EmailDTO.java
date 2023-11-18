package com.dark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailDTO {

	private String senderName; // 발신자이름
	private String senderMail; // 발신자 메일주소
	private String receiverMail; // 수신자 메일주소
	private String subject; // 제목
	private String message; // 본문내용
	
	public EmailDTO() {
		this.senderName = "닭마켓";
		this.senderMail = "Darkmarket";
		this.subject = "닭마켓 회원가입 인증코드입니다.";
		this.message = "인증코드 확인하시고, 회원가입시 인증코드 입력란에 입력하세요.";
	}
}
