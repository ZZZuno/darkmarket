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
      <h3 class="box-title">회원수정 인증확인</h3>
      </div>
      
      <form role="form" id="loginForm" method="post" action="/member/confirmPw">
      <div class="box-body">
      <div class="form-group row">
        <label for="dark_id" class="col-2">아이디</label>
        <div class="col-10">
          <input type="text" class="form-control" name="dark_id" id="dark_id" placeholder="아이디 입력...">
        </div>
      </div>
      <div class="form-group row">
        <label for="dark_password" class="col-2">비밀번호</label>
        <div class="col-10">
          <input type="password" class="form-control" name="dark_password" id="dark_password" placeholder="비밀번호 입력...">
        </div>
      </div>
      
      <div class="box-footer">
      <button type="submit" class="btn btn-primary" id="btnLogin">인증확인</button>
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

	
  });
   
</script>
  </body>
</html>