<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
 <p>나의 주문</p>
</div>

<div class="container">

  <table class="table table-striped">
      <thead><tr>
        <th style="width: 7%">번호</th>
        <th style="width: 10%">주문자</th>
        <th style="width: 18%">주문일시</th>
        <th style="width: 35%">배송지</th>
        <th style="width: 10%">총주문액</th>
        <th style="width: 10%">주문상태</th>
        <th style="width: 10%">결제상태</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${order_list }" var="orderVO">
      <tr>
        <td>${orderVO.ord_code }</td>
        <td>${orderVO.ord_name}</td>
        <td>
          <fmt:formatDate value="${orderVO.ord_regdate }" pattern="yyyy-MM-dd hh:mm:ss" />
        </td>
        <td><span class="btn_order_detail">${orderVO.ord_addr_basic } ${orderVO.ord_addr_detail }</span></td>
        <td>${orderVO.ord_price}</td>
        <td>
          ${orderVO.ord_status}
        </td>
        
        
        <td>${orderVO.payment_status}</td>
      </tr>
      </c:forEach>
      </tbody></table>

  <%@include file="/WEB-INF/views/comm/footer.jsp" %>
</div>

<%@include file="/WEB-INF/views/comm/plugIn.jsp" %>
  <!-- 카테고리 메뉴 자바스크립트 작업소스 -->
  <script src="/js/category_menu.js"></script>
  
  <script>
	$(document).ready(function() {

let actionForm = $("#actionForm");

// [이전] 1 2 3 4 5 [다음] 클릭 이벤트 설정. <a>태그
$(".movepage").on("click", function(e) {
  e.preventDefault(); // a태그의 href 링크기능을 제거. href속성에 페이지번호를 숨겨둠.

  actionForm.attr("action", "/admin/order/order_list");
  actionForm.find("input[name='pageNum']").val($(this).attr("href"));

  // <input type="date" name="start_date" value="${start_date}">

  actionForm.append('<input type="date" name="start_date" value="${start_date}">');
  actionForm.append('<input type="date" name="end_date" value="${end_date}">');

   actionForm.submit();
});


// 주문상세 방법1 이벤트
$(".btn_order_detail1").on("click", function() {

let cur_tr = $(this).parent().parent();
let ord_code = $(this).data("ord_code");

console.log("주문번호", ord_code);

let url = "/admin/order/order_detail_info1/" + ord_code;
getOrderDetailInfo(url, cur_tr);
});

function getOrderDetailInfo(url,cur_tr) {
$.getJSON(url, function(data) {

  // data : 주문상세정보
  console.log("상세정보", data[0].ord_code);

  printOrderDetailList(data, cur_tr, $("#orderDetailTemplate"))


});
}

let printOrderDetailList = function(orderDetailData, target, template) {
  let templateObj = Handlebars.compile(template.html());
  let orderDetailHtml = templateObj(orderDetailData);

    //상품후기목록 위치를 참조하여, 추가
    // table태그에서 추가된 주문상세 tr을 모두제거.
    target.parent().find(".tr_detail_info").remove();
    // 선택된 주문상세 tr이 바로아래 추가된다.
    target.after(orderDetailHtml);
}

//주문상세에서 개별삭제
$("table#order_info_tbl").on("click", "button[name='btn_order_delete']", function() {

// console.log("개별삭제");

// 주문상세테이블은 primary key가 2개컬럼을 대상으로 복합키 설정이 되어있다.
let ord_code = $(this).data("ord_code");
let item_num = $(this).data("item_num");

if(!confirm("상품코드 " + item_num + " 번을 삭제하시겠읍니까?")) return;

// console.log("주문번호", ord_code);
// console.log("상품코드", item_num);

// <input type='hidden' name='ord_code' value=''>

actionForm.append("<input type='hidden' name='ord_code' value='" + ord_code + "'>");
actionForm.append("<input type='hidden' name='item_num' value='" + item_num + "'>");

actionForm.attr("action", "/admin/order/order_product_delete");
actionForm.submit();

});

}); // ready 이벤트

  </script>  
  </body>
</html>
    