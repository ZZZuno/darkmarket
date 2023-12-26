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
  
  <%@include file="/WEB-INF/views/admin/include/plugin1.jsp" %>
  
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
        공지사항
        <small>공지사항 관리</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 공지사항</a></li>
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
							<form action="/admin/board/list" method="get" >
									<select name="type">
										<option selected>검색종류선택</option>
										<option value="T" ${pageMaker.cri.type == 'T'? 'selected': '' }>제목</option>
										<option value="C" ${pageMaker.cri.type == 'C'? 'selected': '' }>내용</option>
										<option value="W" ${pageMaker.cri.type == 'W'? 'selected': '' }>작성자</option>
										<option value="TC" ${pageMaker.cri.type == 'TC'? 'selected': '' }>제목 or 내용</option>
										<option value="TW" ${pageMaker.cri.type == 'TW'? 'selected': '' }>제목 or 작성자</option>
										<option value="TWC" ${pageMaker.cri.type == 'TWC'? 'selected': '' }>제목 or 작성자 or 내용</option>
									</select>
									<input type="text" name="keyword" value="${pageMaker.cri.keyword}" />
									<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}" />
									<input type="hidden" name="amount" value="${pageMaker.cri.amount}" />
									<button type="submit" class="btn btn-primary">검색</button>
								</form>
						</div>
						<table class="table table-bordered">
							<tbody><tr>
								<th style="width: 10%">번호</th>
								<th style="width: 40%">제목</th>
								<th style="width: 20%">작성자</th>
								<th style="width: 20%">등록일</th>
								<th style="width: 10%">조회수</th>
							</tr>
							<c:forEach items="${list }" var="board">
							<tr>
								<td>${board.bno }</td>
								<td><a class="move" href="#" data-bno="${board.bno}">${board.title }</a></td>
								<td>${board.writer }</td>
								<td><fmt:formatDate value="${board.regdate }" pattern="yyyy-MM-dd" /></td>
								<td>${board.viewcount }</td>
							</tr>
							</c:forEach>
							</tbody></table>
					</div>
					<div class="box-footer clearfix">
						<div class="row">
							<!--1)페이지번호 클릭할 때 사용  [이전]  1	2	3	4	5 [다음]  -->
							<!--2)목록에서 상품이미지 또는 상품명 클릭할 때 사용   -->
							<div class="col-md-2 text-center">
							  <form id="actionForm" action="" method="get">
								<input type="hidden" name="pageNum" id="pageNum" value="${pageMaker.cri.pageNum}" />
								<input type="hidden" name="amount"  id="amount" value="${pageMaker.cri.amount}" />
								<input type="hidden" name="type" id="type" value="${pageMaker.cri.type}" />
								<input type="hidden" name="keyword" id="keyword" value="${pageMaker.cri.keyword}" />
								<input type="hidden" name="bno" id="bno" />
							  </form>
							  </div>

							<div class="col-md-8 text-center">
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
							<div class="col-md-2 text-right"> <!-- "글쓰기" 버튼 클릭시 로그인 여부 확인 -->
				                  <c:if test="${empty sessionScope.adminStatus}">
				                    <a class="btn btn-primary" href="/admin/board/list" role="button" onclick="alertLoginRequired();">글쓰기</a>
				                  </c:if>
				                  
				                  <!-- "글쓰기" 버튼 클릭시 로그인 여부 확인 -->
				                  <c:if test="${sessionScope.adminStatus != null}">
				                    <a class="btn btn-primary" href="/admin/board/register" role="button">글쓰기</a>
				                  </c:if></div>
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

//폼태그 참조.
let actionForm = document.getElementById("actionForm");

// 1) 페이지번호 클릭시 동작되는 이벤트 설정
// <a class="movepage">1</a><a class="movepage">2</a><a class="movepage">3</a><a class="movepage">4</a><a class="movepage">5</a>
const movePages = document.getElementsByClassName("movepage");
Array.from(movePages).forEach(function(mv_page) {
	// actionForm 폼 전송
	mv_page.addEventListener("click", function(event) {
		event.preventDefault();
		// data-page="1"
		// console.log("페이지번호", event.target.dataset.page);
		document.getElementById("pageNum").value = event.target.dataset.page;
		actionForm.setAttribute("action", "/admin/board/list");
		actionForm.submit();  // /board/list
	});
});

// 2) 제목 클릭시 이벤트 설정 : 게시물 읽기
const moves = document.getElementsByClassName("move");
Array.from(moves).forEach(function(move) {
	// actionForm 폼 전송
	move.addEventListener("click", function(event) {
		event.preventDefault();

		
		// bno 제거작업
		// 목록에서 제목 클릭후 게시물읽기에서 뒤로버튼에 의하여 목록으로 돌아가서
		// 다시 제목을 클릭하면, bno파라미터가 추가되기 때문에
		// 기존 bno 파라미터를 삭제해야 한다.
		document.getElementById("bno").remove();

		let bno = event.target.dataset.bno;
		// <input type='hidden' name='bno' value='게시물번호'>
		const newInput = document.createElement("input");
		newInput.setAttribute("type", "hidden");
		newInput.setAttribute("name", "bno");
		newInput.setAttribute("value", bno);
		newInput.setAttribute("id", "bno");
		actionForm.appendChild(newInput);
		actionForm.setAttribute("action", "/admin/board/get");
	    actionForm.submit();  // /board/list
	});
});
function alertLoginRequired() {
    alert("로그인이 필요한 작업입니다.");
    
  }
</script>
</body>
</html>