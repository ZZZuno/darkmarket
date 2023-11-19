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

    

  </head>
  <body>
    
<%@include file="/WEB-INF/views/comm/header.jsp" %>

<div class="container">
   <div class="text-center">
    <div class="box box-primary">
      <div class="box-header with-border">
      <h3 class="box-title">MyPage</h3>
      </div>
      
      <form role="form" id="mypageForm" method="post" action="/member/mypage">
      <div class="box-body">
      <div class="form-group row">
        <label for="dark_id" class="col-2">아이디</label>
        <div class="col-10">
          <input type="text" class="form-control" name="dark_id" id="dark_id" value="${memberVO.dark_id }" readonly>
        </div>
      </div>
      <div class="form-group row">
        <label for="dark_name" class="col-2">이름</label>
        <div class="col-10">
          <input type="text" class="form-control" name="dark_name" id="dark_name" value="${memberVO.dark_name }" readonly>
        </div>
      </div>
      <div class="form-group row">
        <label for="dark_email" class="col-2">전자우편</label>
        <div class="col-10">
          <input type="email" class="form-control" name="dark_email" id="dark_email" value="${memberVO.dark_email }" readonly>
        </div>
      </div>
      </div>
      <div class="form-group row">
        <label for="sample2_postcode" class="col-2">우편번호</label>
        <div class="col-10">
          <input type="text" class="form-control" name="dark_zipcode" id="sample2_postcode" value="${memberVO.dark_zipcode }" readonly>
        </div>
      </div>
      <div class="form-group row">
        <label for="sample2_address" class="col-2">기본주소</label>
        <div class="col-10">
          <input type="text" class="form-control" name="dark_addr" id="sample2_address" value="${memberVO.dark_addr }" readonly>
        </div>
      </div>
      <div class="form-group row">
        <label for="sample2_detailAddress" class="col-2">상세주소</label>
        <div class="col-10">
          <input type="text" class="form-control" name="dark_deaddr" id="sample2_detailAddress" value="${memberVO.dark_deaddr }" readonly>
        </div>
      </div>
      <div class="form-group row">
        <label for="dark_phone" class="col-2">전화번호</label>
        <div class="col-10">
          <input type="text" class="form-control" name="dark_phone" id="dark_phone" value="${memberVO.dark_phone }" readonly>
        </div>
      </div>
      
      </div>
      
      <div class="box-footer">
        <a class="btn btn-primary" href="/member/confirmPw" role="button">회원수정</a>
      <button type="button" class="btn btn-primary" id="btnDelete">회원탈퇴</button>
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

    $("#btnDelete").click(function() {

      location.href = "/member/delConfirmPw";
    });
	
  });
   
</script>
  </body>
</html>