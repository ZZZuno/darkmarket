package com.dark.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dark.domain.MemberVO;
import com.dark.dto.IdFindDTO;
import com.dark.dto.LoginDTO;
import com.dark.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@RequestMapping("/member/*")
@Log4j
@Controller
public class MemberController {

	private static final String String = null;

	private final MemberService memberService;
	
	private final PasswordEncoder passwordEncoder;
	
	@GetMapping("/join")
	public void join() {
		
	}
	
	@GetMapping("/idCheck")
	public ResponseEntity<String> idCheck(String dark_id) throws Exception {
		
		ResponseEntity<String> entity = null;
		
		String idUse = "";
		
		if(memberService.idCheck(dark_id) != null) {
			idUse = "no";
		} else {
			idUse = "yes";
		}
		
		entity = new ResponseEntity<String>(idUse, HttpStatus.OK);
		
		return entity;
			}
	
	@PostMapping("/join")
	public String join(MemberVO vo, RedirectAttributes rttr) {
		
		vo.setDark_password(passwordEncoder.encode(vo.getDark_password()));
		
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
	
	@GetMapping("/mypage")
	public void mypage(HttpSession session, Model model) throws Exception {
		log.info("마이페이지 호출");
		String dark_id = ((MemberVO) session.getAttribute("loginStatus")).getDark_id();
		
		MemberVO db_vo = memberService.login(dark_id);
		model.addAttribute("memberVO", db_vo);
	}
	
	@GetMapping("/confirmPw")
	public void confirmPw() {
		log.info("회원수정 인증호출");
	}
	
	// 회원수정전 인증
	@PostMapping("/confirmPw")
	public String confirmPw(LoginDTO dto, RedirectAttributes rttr) throws Exception {
		log.info("회원수정전 인증 재확인: " + dto);
		MemberVO db_vo = memberService.login(dto.getDark_id());
		
		String url = "";
		String msg = "";
		
		if(db_vo != null) {
			if(passwordEncoder.matches(dto.getDark_password(), db_vo.getDark_password())) {
				url = "/member/modify";
			}else {
				url = "/member/confirmPw";
				msg = "비밀번호가 일치하지 않습니다.";
				rttr.addFlashAttribute("msg", msg);
			}
		}else {
			url = "/member/confirmPw";
			msg = "존재하지 않는 아이디입니다.";
			rttr.addAttribute("msg", msg);
		}
		
		return "redirect:" + url;
	}
	
	@GetMapping("/modify")
	public void modify(HttpSession session, Model model) throws Exception {
		
	String dark_id = ((MemberVO) session.getAttribute("loginStatus")).getDark_id();
		
		MemberVO db_vo = memberService.login(dark_id);
		model.addAttribute("memberVO", db_vo);
	}
	
	@PostMapping("/modify")
	public String modify(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		MemberVO db_vo = (MemberVO) session.getAttribute("loginStatus");
		
		String msg = "";
		
		String dark_id = db_vo.getDark_id();
		
		vo.setDark_id(dark_id);
		
		// db 업데이트 작업
		memberService.modify(vo);
		
		// header.jsp에서 전자우편이 수정된 내용으로 반영이 안되기 때문
		db_vo.setDark_email(vo.getDark_email()); // 수정
		session.setAttribute("loginStatus", db_vo);
		msg = "회원정보가 수정됨";
		rttr.addFlashAttribute("msg", msg);
		
		
		return "redirect:/";
	}
	
	@GetMapping("delConfirmPw")
	public void delConfirmPw() {
		
	}
	
	@PostMapping("/delete")
	public String delete(LoginDTO dto, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		MemberVO db_vo = memberService.login(dto.getDark_id());
		
		String url = "";
		String msg = "";
		
		if(db_vo != null) {
			
			if(passwordEncoder.matches(dto.getDark_password(), db_vo.getDark_password())) {
				url = "/";
				msg = "회원정보가 삭제되었습니다.";
				rttr.addFlashAttribute("msg", msg);
				session.invalidate(); // 세션소멸
				
				//회원탈퇴작업
				memberService.delete(dto.getDark_id());
				
			}else {
				url = "/member/delConfirmPw";
				msg = "비밀번호가 일치하지 않습니다.";
				rttr.addFlashAttribute("msg", msg);
			}
		}else {
			url = "/member/delConfirmPw";
			msg = "존재하지 않는 ID입니다.";
			rttr.addFlashAttribute("msg", msg);
		}
		
		return "redirect:" + url;
	}
	
	@GetMapping("/idFind")
	public void idFind() {
		
	}
	
	@PostMapping("/idFind")
	public String idFind(IdFindDTO dto, RedirectAttributes rttr) {
		
		MemberVO db_vo = memberService.idFind(dto.getDark_name());
		
		String url = "";
		String msg = "";
		
		if(db_vo != null) {
			if(db_vo.getDark_email().equals(dto.getDark_email())) {
				url = "/member/idFound";
				rttr.addFlashAttribute("memberVO", dto);
			}else {
				url = "/member/idFind";
				msg = "존재하지 않는 Email입니다.";
				rttr.addFlashAttribute("msg", msg);
			}
				
			}else {
				url = "/member/idFind";
				msg = "일치하는 id가 없습니다.";
				rttr.addFlashAttribute("msg", msg);
		}
		
		
		return "redirect:" + url;
	}
	
	@GetMapping("/idFound")
	public void idFound() {
		
	}
	
	@GetMapping("/pwFind")
	public void pwFind() {
		
	}
	
	@PostMapping("/pwFind")
	public String pwFind(MemberVO vo, RedirectAttributes rttr) {
		
		MemberVO db_vo = memberService.idFind(vo.getDark_name());
		
		String url = "";
		String msg = "";
		
		if(db_vo != null) {
			if(db_vo.getDark_id().equals(vo.getDark_id())) {
				if(db_vo.getDark_email().equals(vo.getDark_email())) {
					url = "/member/pwFound";
				}else {
					url = "/member/pwFind";
					msg = "이메일이 일치하지 않습니다.";
					rttr.addFlashAttribute("msg", msg);
				}
				
			}else {
				url = "/member/pwFind";
				msg = "아이디가 일치하지 않습니다.";
				rttr.addFlashAttribute("msg", msg);
			}
		}else {
			url = "/member/pwFind";
			msg = "존재하지 않는 사용자명입니다.";
			rttr.addFlashAttribute("msg", msg);
		}
		
		return "redirect:" + url;
	}
	
	@GetMapping("/pwFound")
	public void pwFound() {
		
	}
	
	@PostMapping("/pwFound")
	public String updatePassword(@RequestParam("id") String id, 
								 @RequestParam("newPassword") String newPassword, 
	                             RedirectAttributes rttr) {
	    
		log.info("아이디: " + id);
		log.info("비밀번호: " + newPassword);
		
	    memberService.updatePassword(id, newPassword);
	    String msg = "비밀번호가 변경되었습니다.";
	    rttr.addFlashAttribute("msg", msg);

	    return "redirect:/member/login";
	}
	
}
