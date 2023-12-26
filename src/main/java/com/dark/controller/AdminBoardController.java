package com.dark.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dark.domain.AdminVO;
import com.dark.domain.BoardVO;
import com.dark.dto.Criteria;
import com.dark.dto.PageDTO;
import com.dark.service.AdminService;
import com.dark.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@RequestMapping("/admin/board/*")
@Log4j
@Controller
public class AdminBoardController {

	private final AdminService adminService;
	private final BoardService boardService;
	
	@GetMapping("/register")
	public void register(HttpSession session, Model model) throws Exception {
//		log.info("called register...");
        String ad_id = ((AdminVO) session.getAttribute("adminStatus")).getAd_id();
		
		AdminVO db_vo = adminService.admin_ok(ad_id);
		model.addAttribute("adminVO", db_vo);
	}
	
	@PostMapping("/register")
	public String register(BoardVO board) {
		
//		log.info("게시판 입력데이타: " + board); // board.toString()
		//db저장.  
		/*
		 1)BoardMapper인터페이스와 BoardMapper.xml 작업
		 2)BoardService인터페이스와 BoardServiceImple 작업 
		 */
		boardService.register(board);
		
		
		return "redirect:/admin/board/list"; // 주소는 절대경로
	}
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model, HttpSession session) {
		
//		log.info("list: " + cri); // cri.toString()
		// Criteria(pageNum=1, amount=10, type=null, keyword=null)
		
		//1)목록 데이타 - List<BoardVO>
		List<BoardVO> list = boardService.getListWithPaging(cri);
		model.addAttribute("list", list);
		
		//2)페이징기능 - PageDTO 
		int total = boardService.getTotalCount(cri); // 테이블의 데이타 전체개수
		
//		log.info("데이타 총개수: " + total);
		
		PageDTO pageDTO = new PageDTO(cri, total);
		model.addAttribute("pageMaker", pageDTO);
		
		// 페이징정보: PageDTO(startPage=1, endPage=10, prev=false, next=true, total=2048, cri=Criteria(pageNum=1, amount=10, type=null, keyword=null))
//		log.info("페이징정보: " + pageDTO);
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
//		//목록에서 선택한 게시물번호
//		log.info("게시물번호: " + bno);
//		
//		// 페이징과 검색정보
//		log.info("페이징과 검색정보: " + cri);
		
		BoardVO board = boardService.get(bno);
		model.addAttribute("board", board);
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
		
//		log.info("수정 데이타: " + board);
//		log.info("Criteria: " + cri);
		
		boardService.modify(board);
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/admin/board/list"; // list(Criteria cri, Model model)
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("bno") Long bno, Criteria cri, RedirectAttributes rttr) {
		
//		log.info("삭제할 번호: " + bno);
		
		boardService.delete(bno);
		
		return "redirect:/admin/board/list" + cri.getListLink();
	}
}
