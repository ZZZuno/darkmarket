<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | Starter</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <style>
        /* Important part */
    .modal-content{
      overflow-y: initial !important
      }
    .modal-body{
      height: 250px;
      overflow-y: auto;
    }
  </style>
  <%@include file="/WEB-INF/views/admin/include/plugin1.jsp" %>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
  <script id="orderDetailTemplate" type="text/x-handlebars-template">
    <tr class="tr_detail_info">
      <td colspan="9" >
        <table class="table table-sm">
          <caption style="display: table-caption;text-align: center;color: red;font-weight: bold;">[주문상세정보]</caption>
          <thead>
            <tr>
              <th scope="col">주문번호</th>
              <th scope="col">상품코드</th>
              <th scope="col">상품이미지</th>
              <th scope="col">상품명</th>
              <th scope="col">주문수량</th>
              <th scope="col">주문금액</th>
              <th scope="col">비고</th>
            </tr>
          </thead>
          <tbody>
            {{#each .}}
            <tr>
              <th scope="row">{{ord_code}}</th>
              <td>{{item_num}}</td>
              <td><img width="35%" height="100" src='/admin/order/imageDisplay?dateFolderName={{item_up_folder}}&fileName={{item_img}}'></td>
              <td>{{item_name}}</td>
              <td>{{dt_amount}}</td>
              <td>{{ord_price}}</td>
              <td><button type="button"  name="btn_order_delete" class="btn btn-danger" data-ord_code="{{ord_code}}" data-item_num="{{item_num}}">delete</button></td>
            </tr>
            {{/each}}
          </tbody>
        </table>
      </td>
    </tr>
</script>
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <!-- Main Header -->
  <%@ include file="/WEB-INF/views/admin/include/header.jsp" %>
  <!-- Left side column. contains the logo and sidebar -->
  <%@ include file="/WEB-INF/views/admin/include/nav.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        회원관리
        <small>목록</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 회원관리</a></li>
        <li class="active">목록</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">

      <div class="row">
    		<div class="col-md-12">
    			<div class="box">
					<div class="box-header with-border">
					<h3 class="box-title">목록</h3>
					</div>

					<div class="box-body">
						<div>
							<form action="/admin/member/member_list" method="get" >
									<select name="type">
										<option selected>검색종류선택</option>
										<option value="N" ${pageMaker.cri.type == 'N'? 'selected': ''}>아이디</option>
										<option value="C" ${pageMaker.cri.type == 'C'? 'selected': ''}>이름</option>
									</select>
									<input type="text" name="keyword" value="${pageMaker.cri.keyword}" />
									<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}" />
									<input type="hidden" name="amount" value="${pageMaker.cri.amount}" />

									<button type="submit" class="btn btn-primary">검색</button>
							</form>
						</div>
						<table class="table table-bordered" id="order_info_tbl">
							<tbody><tr>
							  <th style="width: 7%">아이디</th>
							  <th style="width: 10%">이름</th>
								<th style="width: 13%">이메일</th>
								<th style="width: 30%">주소</th>
								<th style="width: 10%">전화번호</th>
								<th style="width: 13%">마지막로그인</th>
								<th style="width: 7%">포인트</th>
                				<th style="width: 10%">비고</th>
							</tr>
							<c:forEach items="${member_list }" var="memberVO">
							<tr>
								<td>${memberVO.dark_id }</td>
								<td>${memberVO.dark_name}</td>
								<td>${memberVO.dark_email }</td>
								<td><span class="btn_order_detail">${memberVO.dark_addr } ${memberVO.dark_deaddr }</span></td>
								<td>${memberVO.dark_phone}</td>
								<td>
									<fmt:formatDate value="${memberVO.dark_lastlogin}" pattern="yyyy-MM-dd hh:mm:ss"/>
								</td>
								
								
								<td>${memberVO.dark_point}</td>
                				<td>
                					<button type="button" class="btn btn-danger btn_member_delete" data-dark_id="${memberVO.dark_id }">회원삭제</button>
                				</td>
							</tr>
							</c:forEach>
							</tbody></table>
					</div>
					<div class="box-footer clearfix">
								
							<!--1)페이지번호 클릭할 때 사용  [이전]  1	2	3	4	5 [다음]  -->
							<!--2)목록에서 상품이미지 또는 상품명 클릭할 때 사용   -->
							  <form id="actionForm" action="" method="get">
								<input type="hidden" name="pageNum" id="pageNum" value="${pageMaker.cri.pageNum}" />
								<input type="hidden" name="amount"  id="amount" value="${pageMaker.cri.amount}" />
								<input type="hidden" name="type" id="type" value="${pageMaker.cri.type}" />
								<input type="hidden" name="keyword" id="keyword" value="${pageMaker.cri.keyword}" />
								
							  </form>
							</div>
							<div class="row">
							<div class="col-md-12 text-center">
								<nav aria-label="...">
								<ul class="pagination">
									<!-- 이전 표시여부 -->
									<c:if test="${pageMaker.prev }">
										<li class="page-item">
											<a href="${pageMaker.startPage - 1 }" class="page-link movepage">Previous</a>
										</li>
									</c:if>
									<!-- 페이지번호 출력 -->
									<!-- 1	2	3	4	5 6	7	8	9	10  [다음] -->
									<!-- [이전] 11	12	13	14	15 16	17	18	19	20   -->
									<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="num">
										<li class='page-item ${pageMaker.cri.pageNum ==  num ? "active":"" }'aria-current="page">
											<a class="page-link movepage" href="${num }" data-page="${num }">${num }</a>
										</li>
									</c:forEach>
									
									<!--  다음 표시여부 -->
									<c:if test="${pageMaker.next }">
										<li class="page-item">
										<a href="${pageMaker.endPage + 1 }" class="page-link movepage" href="#">Next</a>
										</li>
									</c:if>
									
								</ul>
								</nav>
							</div>
						</div>
					</div>
			   </div>
    		
    	</div>

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
  <%@include file="/WEB-INF/views/admin/include/footer.jsp" %>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Create the tabs -->
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
      <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
      <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
      <!-- Home tab content -->
      <div class="tab-pane active" id="control-sidebar-home-tab">
        <h3 class="control-sidebar-heading">Recent Activity</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:;">
              <i class="menu-icon fa fa-birthday-cake bg-red"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                <p>Will be 23 on April 24th</p>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

        <h3 class="control-sidebar-heading">Tasks Progress</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:;">
              <h4 class="control-sidebar-subheading">
                Custom Template Design
                <span class="pull-right-container">
                    <span class="label label-danger pull-right">70%</span>
                  </span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

      </div>
      <!-- /.tab-pane -->
      <!-- Stats tab content -->
      <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
      <!-- /.tab-pane -->
      <!-- Settings tab content -->
      <div class="tab-pane" id="control-sidebar-settings-tab">
        <form method="post">
          <h3 class="control-sidebar-heading">General Settings</h3>

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Report panel usage
              <input type="checkbox" class="pull-right" checked>
            </label>

            <p>
              Some information about this general settings option
            </p>
          </div>
          <!-- /.form-group -->
        </form>
      </div>
      <!-- /.tab-pane -->
    </div>
  </aside>
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
  immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->
<%@include file="/WEB-INF/views/admin/include/plugin2.jsp" %>

<script>
  $(document).ready(function() {

    let actionForm = $("#actionForm");

    // [이전] 1 2 3 4 5 [다음] 클릭 이벤트 설정. <a>태그
    $(".movepage").on("click", function(e) {
      e.preventDefault(); // a태그의 href 링크기능을 제거. href속성에 페이지번호를 숨겨둠.

      actionForm.attr("action", "/admin/member/member_list");
      actionForm.find("input[name='pageNum']").val($(this).attr("href"));

      // <input type="date" name="start_date" value="${start_date}">

       actionForm.submit();
    });
   

  // 회원삭제
  $(".btn_member_delete").on("click", function() {
    
    let dark_id = $(this).data("dark_id");

    if(!confirm("아이디 " + dark_id + " 을 삭제하시겠읍니까?")) return;

    let url = "/admin/member/delete/" + dark_id;
    location.href = url;
  });
  
}); // ready 이벤트
  </script>
</body>
</html>