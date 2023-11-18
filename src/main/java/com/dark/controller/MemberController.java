package com.dark.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dark.domain.MemberVO;
import com.dark.dto.LoginDTO;
import com.dark.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@RequestMapping("/member/*")
@Log4j
@Controller
public class MemberController {

	private final MemberService memberService;
	
	private final PasswordEncoder passwordEncoder;
	
	@GetMapping("/join")
	public void join() {
		
		log.info("회원가입 호출");
	}
	
	@GetMapping("/idCheck")
	public ResponseEntity<String> idCheck(String dark_id) throws Exception {
		log.info("아이디 :" + dark_id);
		
		ResponseEntity<String> entity = null;
		
		String idUse = "";
		if(memberService.idCheck(dark_id) != null) {
			idUse = "no"; // 아이디 존재.
		}else {
			idUse = "yes"; // 아이디 존재 X 사용가능.
		}
		
		entity = new ResponseEntity<String>(idUse, HttpStatus.OK); // ajax로 "yes"를 보냄으로써 success 구문 동작.
		
		return entity;
		
		
	}
	// 회원정보 저장 -> 다른주소로 이동 (redirect)
	@PostMapping("/join")
	public String join(MemberVO vo, RedirectAttributes rttr) {
		
		log.info("회원정보: " + vo);
		
		vo.setDark_password(passwordEncoder.encode(vo.getDark_password()));
		
		log.info("암호화 비밀번호: " + vo.getDark_password());
		
		memberService.join(vo);
		
		return "redirect:/member/login";
	}
	
	@GetMapping("/login")
	public void login() {
		log.info("로그인 호출");
	}
	
	//1)로그인 인증성공 -> 메인페이지(/) 주소이동.  2)로그인 인증실패 -> 로그인 폼주소로 이동
		// String dark_id, String dark_password 파라미터로 사용해도 됨.
	@PostMapping("/login")
	public String login(LoginDTO dto, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		log.info("로그인 정보: " + dto);
		
		// db에 입력한 아이디와 일치하는 데이터의 정보를 db_vo에 대입하는 작업.
		MemberVO db_vo = memberService.login(dto.getDark_id());
		
		String url = "";
		String msg = "";
		
		// 입력한 아이디가 db에 존재하는경우.
		if(db_vo != null) {
			// 사용자가 입력한 비밀번호(평문)와 db에서 가져온 암호화된 비밀번호를 대조.
			if(passwordEncoder.matches(dto.getDark_password(), db_vo.getDark_password())) {
				// 로그인 성공결과로 서버측의 메모리를 사용하는 세션형태 작업
				session.setAttribute("loginStatus", db_vo);
				
				// 로그인 시간 업데이트
				memberService.loginTimeUpdate(dto.getDark_id());
				url = "/";
				
			}else {
				url = "/member/login"; // 로그인 폼
				msg = "비밀번호가 일치하지 않습니다.";
				rttr.addFlashAttribute("msg", msg); // jsp에서 쓸것임.
				
			}
			
			// 입력한 아이디가 db에 존재하지 않을 경우
		}else {
			url = "/member/login";
			msg = "존재하지 않는 ID입니다.";
			rttr.addFlashAttribute("msg", msg); // jsp에서 사용
			
		}
		
		return "redirect:" + url;
	}
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	
	
}
