package com.dark.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dark.domain.MemberVO;

public class UserInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	throws Exception {
		
		boolean result = false;
		
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO) session.getAttribute("loginStatus");
		
		if(user == null) {
			
			result = false;
			
			if(isAjaxRequest(request)) {
				response.sendError(400);
				
			}else {
				
				getTargetUrl(request);
				
				response.sendRedirect("/member/login");
			}
		}else {
			
			result = true;
			
		}
		
		return result;
	}
	
	 private void getTargetUrl(HttpServletRequest request) {
	      
	      // 요청주소 http://localhost:8081/member/modify?userid=user01
	      
	      String uri = request.getRequestURI();   // /member/modify
	      String query = request.getQueryString();   //   userid=user01
	      
	      if(query == null || query.equals("null")) {
	         query = "";
	      }else {
	         query = "?" + query;
	      }
	      
	      String targerUrl = uri + query;
	      
	      if(request.getMethod().equals("GET")) {
	         request.getSession().setAttribute("targerUrl", targerUrl); 
	      }
	   }
	
	
	   private boolean isAjaxRequest(HttpServletRequest request) {
		   
		   boolean isAjax = false;
		   
		   String header = request.getHeader("AJAX");
		   if(header != null && header.equals("true")) {
			   isAjax = true;
		   }
		   
		   return isAjax;
		   
	   }
	   
}

