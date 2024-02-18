package com.dark.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dark.domain.MemberVO;
import com.dark.domain.OrderDetailInfoVO;
import com.dark.dto.CartDTOList;
import com.dark.dto.Criteria;
import com.dark.dto.PageDTO;
import com.dark.service.AdOrderService;
import com.dark.service.CartService;
import com.dark.service.KakaoPayServiceImpl;
import com.dark.service.OrderService;
import com.dark.domain.CartVO;
import com.dark.domain.OrderVO;
import com.dark.domain.PaymentVO;
import com.dark.kakaopay.ApproveResponse;
import com.dark.kakaopay.ReadyResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@RequestMapping("/user/order/*")
@Log4j
@Controller
public class OrderController {
	
	private final CartService cartService;
	private final OrderService orderService;
	private final KakaoPayServiceImpl kakaoPayServiceImpl;
	private final AdOrderService adOrderService;
	
	@GetMapping("/order_info")
	public void order_info(HttpSession session, Model model) throws Exception {
		
		String dark_id = ((MemberVO) session.getAttribute("loginStatus")).getDark_id();
		
		List<CartDTOList> order_info = cartService.cart_list(dark_id);
		
		int order_price = 0;
		
		for(int i=0; i<order_info.size(); i++) {
			CartDTOList vo = order_info.get(i);
			
			vo.setItem_up_folder(vo.getItem_up_folder().replace("\\", "/"));
			
			order_price += (vo.getItem_price() * vo.getCart_amount());
		}
		model.addAttribute("order_info", order_info);
		model.addAttribute("order_price", order_price);
	}
	
	// 상품상세에서 주문하기.
		@GetMapping("/order_ready")
		public String order_ready(CartVO vo, HttpSession session) throws Exception{
		
			String dark_id = ((MemberVO) session.getAttribute("loginStatus")).getDark_id();
			vo.setDark_id(dark_id);
			
			cartService.cart_add(vo);
			
			return "redirect:/user/order/order_info";
		}
		
		
		// 결제선택 : 카카오페이
		@GetMapping(value = "/orderPay", produces = "application/json")
		public @ResponseBody ReadyResponse payReady(String paymethod, OrderVO o_vo, PaymentVO p_vo, int totalprice, HttpSession session) throws Exception {
			
		      /* 1) 주문정보구성 
		         - 주문테이블 : odr_status, payment_status, payment_status 정보존재 안함.
		         - 주문상세테이블(OrderDetailVO) : 
		         	- 장바구니테이블에서 데이터를 참조
		         - 결제테이블 : 보류
			        주문테이블, 주문상세테이블, 결제테이블 저장에 필요한 정보구성
		         2) 카카오페이 결제에 필요한 정보구성
		       		스프링에서 처리 할 수 있는 부분 
		       */
			
			String dark_id = ((MemberVO) session.getAttribute("loginStatus")).getDark_id();
			o_vo.setDark_id(dark_id); // 아이디 값 할당.
			
			// 시퀀스를 주문번호로 사용 : 동일한 주문번호값이 사용.
			Long ord_code = (long) orderService.getOrderSeq();
			o_vo.setOrd_code(ord_code); // 주문번호 저장.
			
			
			
			p_vo.setOrd_code(ord_code);
			p_vo.setDark_id(dark_id);
			p_vo.setPay_method("카카오페이");
			p_vo.setPay_tot_price(totalprice);
			
			o_vo.setOrd_status("주문완료");
			o_vo.setPayment_status("결제완료");
			
			log.info("결제방법: " + paymethod);
			log.info("주문정보: " + o_vo);
			log.info("결제정보: " + p_vo);
			
			
			
			List<CartDTOList> cart_list = cartService.cart_list(dark_id);
			
			String itemName = cart_list.get(0).getItem_name() + "외" + String.valueOf(cart_list.size() - 1) + " 건";
			
			// 1) 주문테이블, 주문상세테이블 저장. 장바구니 삭제.
			orderService.order_insert(o_vo, p_vo);
			
			
			// 3) Kakao Pay 호출 
			ReadyResponse readyResponse = kakaoPayServiceImpl.payReady(o_vo.getOrd_code(), itemName, cart_list.size(), dark_id, totalprice);
					
			log.info("결제고유번호:" + readyResponse.getTid());
			log.info("결제요청URL:" + readyResponse.getNext_redirect_pc_url());
			
			
			// 카카오페이 결제승인요청작업에 필요한 정보
			session.setAttribute("tid", readyResponse.getTid());
			session.setAttribute("odr_code", o_vo.getOrd_code());
			
			
			return readyResponse;
		}
		
		// 결제승인요청작업 http://localhost:8081/user/order/orderApproval
		@GetMapping("/orderApproval")
		public String orderApproval(@RequestParam("pg_token") String pg_token, HttpSession session) {
			
			
			// 2) Kakao Pay 결제승인요청작업
			Long odr_code = (Long) session.getAttribute("odr_code");
			String tid = (String) session.getAttribute("tid");
			String dark_id = ((MemberVO) session.getAttribute("loginStatus")).getDark_id();
			
			ApproveResponse approveResponse = kakaoPayServiceImpl.payApprove(odr_code, tid, pg_token, dark_id);
			
			session.removeAttribute("odr_code");
			session.removeAttribute("tid");
			
			return "redirect:/user/order/orderComplete";
		}
		
		// 결제 성공시.  http://localhost:8081/user/order/orderComplete
		@GetMapping("/orderComplete")
		public void orderComplete() {
			
		}
		
		// 결제 취소시.  http://localhost:8081/user/order/orderCancel
		@GetMapping("/orderCancel")
		public void orderCancel() {
			
		}
		
		// 결제 실패시.  http://localhost:8081/user/order/orderFail
		@GetMapping("/orderFail")
		public void orderFail() {
			
		}
		
		// 결제선택 : 무통장 입금
		@GetMapping("/nobank")
		public ResponseEntity<String> nobank(String paymethod, OrderVO o_vo, PaymentVO p_vo, int totalprice, HttpSession session) throws Exception {
			
			ResponseEntity<String> entity = null;
			
			String dark_id = ((MemberVO) session.getAttribute("loginStatus")).getDark_id();
			o_vo.setDark_id(dark_id); // 아이디 값 할당.
			
			// 시퀀스를 주문번호로 사용 : 동일한 주문번호값이 사용.
			Long ord_code = (long) orderService.getOrderSeq();
			o_vo.setOrd_code(ord_code); // o_vo에 오더 코드 저장.
			
			
			
			
			
			p_vo.setPay_method("무통장입금");
			p_vo.setOrd_code(ord_code);
			p_vo.setDark_id(dark_id);
			p_vo.setPay_tot_price(totalprice);
			p_vo.setPay_nobank_price(totalprice);
			
			o_vo.setOrd_status("주문완료");
			o_vo.setPayment_status("결제완료");
			
			log.info("결제방법: " + paymethod);
			log.info("주문정보: " + o_vo);
			log.info("결제정보: " + p_vo);
			
			
			// 1) 주문테이블, 주문상세테이블 저장. 장바구니 삭제.
			orderService.order_insert(o_vo, p_vo);
			
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
			
			
			return entity;
		}
		
		@GetMapping("/order_list")
		public void order_list(Model model, HttpSession session) throws Exception {
			
			// this(1, 10);
			// 10 -> 2
			
			String dark_id = ((MemberVO) session.getAttribute("loginStatus")).getDark_id();
			
			List<OrderVO> order_list = orderService.order_list(dark_id);
			model.addAttribute("order_list", order_list);
		}
		
		
		// 주문상세 방법1.  주문상세정보가 클라이언트 json 형태로 변환되어 보내진다. (pom.xml 에 jaskson-databind 라이브러리 백그라운드로 작동)
		@GetMapping("/order_detail_info1/{ord_code}")
		public ResponseEntity<List<OrderDetailInfoVO>> order_detail_list1(@PathVariable("ord_code") Long ord_code) throws Exception {
					
			// 클래스명 = 주문상세 + 상품테이블 조인한 결과를 담는 클래스
			
			ResponseEntity<List<OrderDetailInfoVO>> entity = null;
			
			List<OrderDetailInfoVO> OrderDetailList = adOrderService.orderDetailInfo1(ord_code);
			
			// 브라우저에서 상품이미지 출력시 역슬래시 사용이 문제가 된다. 그래서 슬래시로 변환해서 클라이언트로 보냄.
			OrderDetailList.forEach(vo -> {
				vo.setItem_up_folder(vo.getItem_up_folder().replace("\\", "/"));
			});
			
			
			entity = new ResponseEntity<List<OrderDetailInfoVO>>(OrderDetailList, HttpStatus.OK);
			
			return entity;
		}
	}
