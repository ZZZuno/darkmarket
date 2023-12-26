<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



    <table class="table table-sm">
        <caption style="display: table-caption;text-align: center;color: red; font-weight: bold;">[주문상세정보]</caption>
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
            <c:forEach items="${orderProductList }" var="orderProductVO">
          <tr>
            <th scope="row">${orderProductVO.orderDetailVO.ord_code }</th>
            <td>${orderProductVO.orderDetailVO.item_num }</td>
            <td><img width="65%" height="100" src="/admin/order/imageDisplay?dateFolderName=${orderProductVO.itemVO.item_up_folder }&fileName=${orderProductVO.itemVO.item_img }"></td>
            <td>${orderProductVO.itemVO.item_name }</td>
            <td>${orderProductVO.orderDetailVO.dt_amount }</td>
            <td>${orderProductVO.itemVO.item_price * orderProductVO.orderDetailVO.dt_amount }</td>
            <td><button type="button" class="btn btn-info" name="btn_order_delete" data-ord_code="${orderProductVO.orderDetailVO.ord_code }" data-item_num="${orderProductVO.itemVO.item_num }">delete</button></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
</html>