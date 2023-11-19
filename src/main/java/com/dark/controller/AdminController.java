package com.dark.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dark.domain.AdminVO;
import com.dark.service.AdminService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@Log4j
@RequestMapping("/admin/*")
@Controller
public class AdminController {

	private final AdminService adminService;
	private final PasswordEncoder passwordEncoder;
	
	@GetMapping("/intro")
	public String adminLogin() {
		
		return "/admin/adLogin";
	}
	
	// 관리자 로그인인증
	@PostMapping("/admin_ok")
	public String admin_ok(AdminVO vo, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		log.info("관리자 로그인 호출");
		
		AdminVO db_vo = adminService.admin_ok(vo.getAd_id());
		
		String url = "";
		String msg = "";
		
		if(db_vo != null) {
			if(passwordEncoder.matches(vo.getAd_pw(), db_vo.getAd_pw())) {
				session.setAttribute("adminStatus", db_vo);
				
				// 로그인시간 업데이트
				adminService.login_date(vo.getAd_id());
				
				url = "/admin/admin_menu";
			}else {
				url = "/admin/intro";
				msg = "failPW";
				rttr.addFlashAttribute("msg", msg);
			}
		}else {
			url = "/admin/intro";
			msg = "failID";
			rttr.addFlashAttribute("msg", msg);
		}
			
		return "redirect:" + url;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/admin/intro";
	}
	
	@GetMapping("/admin_menu")
	public void admin_menu() {
		
	}
}
