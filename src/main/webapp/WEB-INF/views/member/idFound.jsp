<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.101.0">
    <title>Pricing example · Bootstrap v4.6</title>

    <!-- Bootstrap core CSS -->
<%@include file="/WEB-INF/views/comm/plugIn2.jsp" %>



    <!-- Favicons -->


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

    <script>
      let msg = '${msg}';
      if(msg != "") {
        alert(msg);
      }
    </script>

  </head>
  <body>
    
<%@include file="/WEB-INF/views/comm/header.jsp" %>

<div class="container">
   <div class="text-center">
    <div class="box box-primary">
      <div class="box-header with-border">
      <h3 class="box-title">아이디 찾기 결과</h3>
      </div>
      
      <form role="form" id="loginForm" method="post" action="/member/login">
      <div class="box-body">
      <div class="form-group row">
        <label for="dark_name" class="col-2">아이디</label>
        <div class="col-10">
          <input type="text" class="form-control" value="${memberVO.dark_id}" readonly>
        </div>
      </div>
      <div class="box-footer">
        <a class="btn btn-primary" href="/member/login" role="button">로그인하기</a>
      </div>
      </form>
      </div>
   </div>

  <%@include file="/WEB-INF/views/comm/footer.jsp" %>
</div>

  <%@include file="/WEB-INF/views/comm/plugIn.jsp" %>
  
<script>
  // jquery.slim.min.js 파일에 jquery 명령어가 정의되어 있음
  // 별칭: $  -> jQuery()함수
  // ready()이벤트 메서드 : 브라우저가 html태그를 모두 읽고난 후에 동작하는 이벤트 특징.
  // 자바스크립트 이벤트 등록 : https://www.w3schools.com/js/js_htmldom_eventlistener.asp
  $(document).ready(function() {
	  $("#mailAuth").click(function() {

	      if($("#dark_email").val() == "") {
	        alert("이메일을 입력하세요.");
	        $("#dark_email").focus();
	        return;
	      }

	      $.ajax({
	        url: '/email/authcode',
	        type: 'get',
	        datatype: 'text', // 스프링에서 받는 데이터타입.
	        data: {receiverMail: $("#dark_email").val()},
	        success: function(result) {
	          if(result == "success") {
	            alert("인증메일이 발송되었습니다. 메일 확인바랍니다.");
	          }
	        }
	      });
	    });

	    let isConfirmAuth = false; // 메일인증 상태 변수선언
	    
	    // 인증확인 버튼태그의 아이디 따서 함수 생성
	   $("#btnConfirmAuth").click(function() {

			if($("#authCode").val() == "") {
				alert("인증코드를 입력해주세요.");
				$("#authCode").focus();
				return;
			}

			$.ajax({
	      url: '/email/confirmAuthcode',
	      type: 'get',
	      dataType: 'text',
	      data: {authCode: $("#authCode").val()},
	      success: function(result) {
	        if(result == "success") {
	          alert("인증 확인되었습니다.");
	          isConfirmAuth = true;
	        }else if(result == "fail") {
	          alert("인증코드가 틀립니다. 다시 확인해주십시오.");
	          $("#authCode").val("");
	          isConfirmAuth = false;
	        }else if(result = "request") {
	          alert("메일인증을 다시 시도해주십시오.");
	          $("#authCode").val("");
	          isConfirmAuth = false;
	        }
	      }
				});
		   });

       let loginForm = $("#loginForm");
	   $("#btnFind").click(function() {

		   if($("#dark_name").val() == "") {
				alert("이름을 입력해주세요.");
				$("#dark_name").focus();
				return;
			}
		   
	      if(!isConfirmAuth) {
	          alert("메일인증 바랍니다.");
	          return;
	        }

          loginForm.submit();


	        
	   });
  });
   
</script>
  </body>
</html>