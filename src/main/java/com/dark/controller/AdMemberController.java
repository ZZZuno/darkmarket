package com.dark.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dark.domain.MemberVO;
import com.dark.dto.Criteria;
import com.dark.dto.PageDTO;
import com.dark.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@RequestMapping("/admin/member/*")
@Log4j
@Controller
public class AdMemberController {

	private final MemberService memberService;
	
	@GetMapping("/member_list")
	public void member_list(Criteria cri, Model model) {
		
		cri.setAmount(8);
		
		List<MemberVO> vo = memberService.member_list(cri);
		
		model.addAttribute("member_list", vo);
		
		int totalCount = memberService.getTotalCount(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
		
	}
	
	@GetMapping("/delete/{dark_id}")
	public String delete(@PathVariable("dark_id") String dark_id, Criteria cri) {
		
		memberService.delete(dark_id);
		
		return "redirect:/admin/member/member_list" + cri.getListLink();
	}
}
