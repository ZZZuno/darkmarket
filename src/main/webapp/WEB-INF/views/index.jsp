<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<div id="category_menu">
  <ul class="nav justify-content-center">
    <c:forEach items="${firstCategoryList }" var="category">
      <li class="nav-item">
        <a class="nav-link active" href="#" data-cg_name="${category.cg_name}" data-cg_code="${category.cg_code}">${category.cg_name}</a>
      </li>
    </c:forEach>
  </ul>
</div>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
  <h1 class="display-4">Pricing</h1>
  <p class="lead">Quickly build an effective pricing table for your potential customers with this Bootstrap example. It’s built with default Bootstrap components and utilities with little customization.</p>
</div>

<div class="container">
  <div class="card-deck mb-3 text-center">
    <div class="card mb-4 shadow-sm">
      <div class="card-header">
        <h4 class="my-0 font-weight-normal">Free</h4>
      </div>
      <div class="card-body">
        <h1 class="card-title pricing-card-title">$0 <small class="text-muted">/ mo</small></h1>
        <ul class="list-unstyled mt-3 mb-4">
          <li>10 users included</li>
          <li>2 GB of storage</li>
          <li>Email support</li>
          <li>Help center access</li>
        </ul>
        <button type="button" class="btn btn-lg btn-block btn-outline-primary">Sign up for free</button>
      </div>
    </div>
    <div class="card mb-4 shadow-sm">
      <div class="card-header">
        <h4 class="my-0 font-weight-normal">Pro</h4>
      </div>
      <div class="card-body">
        <h1 class="card-title pricing-card-title">$15 <small class="text-muted">/ mo</small></h1>
        <ul class="list-unstyled mt-3 mb-4">
          <li>20 users included</li>
          <li>10 GB of storage</li>
          <li>Priority email support</li>
          <li>Help center access</li>
        </ul>
        <button type="button" class="btn btn-lg btn-block btn-primary">Get started</button>
      </div>
    </div>
    <div class="card mb-4 shadow-sm">
      <div class="card-header">
        <h4 class="my-0 font-weight-normal">Enterprise</h4>
      </div>
      <div class="card-body">
        <h1 class="card-title pricing-card-title">$29 <small class="text-muted">/ mo</small></h1>
        <ul class="list-unstyled mt-3 mb-4">
          <li>30 users included</li>
          <li>15 GB of storage</li>
          <li>Phone and email support</li>
          <li>Help center access</li>
        </ul>
        <button type="button" class="btn btn-lg btn-block btn-primary">Contact us</button>
      </div>
    </div>
  </div>

  <%@include file="/WEB-INF/views/comm/footer.jsp" %>
</div>

<%@include file="/WEB-INF/views/comm/plugIn.jsp" %>
<!-- 카테고리 메뉴 자바스크립트 작업소스 -->
<script>
  $(document).ready(function() {

    // 1차 카테고리 마우스 올려놨을때.
    $("div#category_menu li a").on("mouseover", function(e) {
      e.preventDefault(); // 링크기능제거
      // console.log("1차카테고리오버");

      let sel_first_category = $(this);
      let cg_code = $(this).data("cg_code");
      let cg_name = $(this).data("cg_name");

       console.log("1차카테고리이름: " + cg_name);

      let url = '/category/secondCategory/' + cg_code;
      $.getJSON(url, function(category) {

        // console.log(category);
        let str = '<ul class="nav justify-content-center" id="second_category">';
        for(let i=0; i<category.length; i++) {
          str += '<li class="nav-item">';
          str += '<a class="nav-link active" href="#" data-cg_name="' + category[i].cg_name + '" data-cg_code="' + category[i].cg_code + '">' + category[i].cg_name + '</a>';
          str += '</li>';
        }
        str += '</ul>';

        sel_first_category.parent().parent().next().remove();
        sel_first_category.parent().parent().after(str);
      }); 

    });

    // 2차 카테고리 선택
    //  [중요] $("div#category_menu").on("이벤트명", "동적태그 참조선택자", function() {

      $("div#category_menu").on("click", "ul#second_category li a" ,function() {

        let cg_code = $(this).data("cg_code");
        let cg_name = $(this).data("cg_name");

        console.log("선택된 2차카테고리코드: " + cg_code);
        console.log("선택된 2차카테고리이름: " + cg_name);


        location.href = `/user/product/pro_list?cg_code=${cg_code}&cg_name=${cg_name}`;
      });
  });
</script>
    
  </body>
</html>
    