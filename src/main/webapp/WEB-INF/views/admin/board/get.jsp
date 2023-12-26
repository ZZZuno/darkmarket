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
        <li class="active">내용</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">

      <div class="row">
    		<div class="col-md-12">
    			<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title mt-5">내용</h3>
					</div>
					<!-- 절대경로 /board/register -->
						<div class="box-body">
						<div class="form-group">
                                    <label for="bno">글번호</label>
                                    <input type="text" class="form-control" name="bno" id="bno" value="${board.bno }" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="title">제목</label>
                                    <input type="text" class="form-control" name="title" id="title" value="${board.title }" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="writer">작성자</label>
                                    <input type="text" class="form-control" name="writer" id="writer" value="${board.writer }" readonly>
                                </div>
                                <div class="form-group">
                                    <label>내용</label>
                                    <textarea class="form-control" rows="3" name="content" readonly>${board.content }</textarea>
                                </div>
                                <div class="form-group">
                                    <label for="regdate">작성일</label>
                                    <input type="text" class="form-control" name="regdate" id="regdate" value='<fmt:formatDate value="${board.regdate }" pattern="yyyy/MM/dd" />' readonly>
                                </div>
                                <div class="form-group">
                                    <label for="updateddate">수정일</label>
                                    <input type="text" class="form-control" name="updateddate" id="updateddate" value='<fmt:formatDate value="${board.updateddate }" pattern="yyyy/MM/dd" />' readonly>
                                </div>
                            </div>

                            <div class="box-footer">
                                <!-- Modify, Delete, List 버튼 클릭시 아래 form태그를 전송 -->
                                <form id="curListInfo" action="" method="get">
                                    <input type="hidden" name="pageNum" id="pageNum" value="${cri.pageNum}" />
                                    <input type="hidden" name="amount" id="amount" value="${cri.amount}" />
                                    <input type="hidden" name="type" id="type" value="${cri.type}" />
                                    <input type="hidden" name="keyword" id="keyword" value="${cri.keyword}" />
                                    <input type="hidden" name="bno" id="bno" value="${board.bno}" />
                                </form>

                                
                                    <c:if test="${sessionScope.adminStatus != null && sessionScope.adminStatus.ad_id == board.writer}">
                                        <button type="button" id="btn_modify" class="btn btn-primary">수정</button>
                                        <button type="button" id="btn_delete" class="btn btn-primary">삭제</button>
                                    </c:if>
                                    
                                        <button type="button" id="btn_list" class="btn btn-primary" onclick="fn_list()">목록</button>
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
<script src="/bower_components/ckeditor/ckeditor.js"></script>
<script>

//form 태그 참조
let curListInfo = document.getElementById("curListInfo");

// 수정버튼 클릭
document.getElementById("btn_modify").addEventListener("click", fn_modify);

function fn_modify() {
    curListInfo.setAttribute("action", "/admin/board/modify");
    curListInfo.submit();
}

// 삭제버튼 클릭
document.getElementById("btn_delete").addEventListener("click", fn_delete);

function fn_delete() {
    if (!confirm("삭제를 하시겠읍니까?")) return;
    curListInfo.setAttribute("action", "/admin/board/delete");
    curListInfo.submit();
}

// 리스트 클릭
document.getElementById("btn_list").addEventListener("click", fn_list);

function fn_list() {
    curListInfo.setAttribute("action", "/admin/board/list");
    curListInfo.submit();
}

 document.getElementById("btn_cancel").addEventListener("click", fn_cancel);

function fn_cancel() {
history.back();
}
</script>

</body>
</html>