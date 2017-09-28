<%@ page pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="ctag" uri="/WEB-INF/tlds/CustomTag.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html dir="ltr" lang="ko">
<head>
<meta charset="utf-8" />
<title>Print Invoice</title>
<base href="/" />
<link href="/view/javascript/bootstrap/css/bootstrap.css" rel="stylesheet" media="all" />
<link href="/view/javascript/font-awesome/css/font-awesome.min.css" type="text/css" rel="stylesheet" />
<link type="text/css" href="/view/stylesheet/stylesheet.css" rel="stylesheet" media="all" />
<link type="text/css" href="/view/stylesheet/print.css" rel="stylesheet" media="all">
</head>
<body>
<div >
  <c:forEach items="${list}" var="list">
  <div style="page-break-after: always;">
    <table class="table borderless">
        <tr>
          <td style="width: 50%;"><img src="/image/logo${list.logo}.png" alt="Invoice" title="Invoice"></td> <!-- ${list.info.store_url} -->
          <td style="width: 50%;text-align: right;font-size: 24px;color:#A3A3A3;"><b><font style="color:red">${ctag:getCurrency(list.info.sum_total)}</font><br/>주문횟수(${list.info.sum_count})<c:if test="${list.icebox!='0'}"> / ICE ITEM(${list.icebox})</c:if></b></td>
        </tr>
    </table>
    <table class="table borderless">
        <tbody>
        <tr>
          <td class="address-box"><b>결제자 정보</b></td>
          <td class="address-box"><b>배송자 정보</b></td>
          <td rowspan ="2" style="width: 33%;">
              <strong>주문번호:</strong><h2>${list.info.order_id}</h2>
              <h3>배송자 : ${list.info.shipping_customer_name}</h3><br />
              <c:if test="${list.info.invoice_no ne null && list.info.invoice_no!=''}">
              Invoice No: <b>${list.info.invoice_no}</b><br />
              </c:if>
              주문일자: <b>${list.info.order_date}</b><br />
              결제방법: <b>${list.info.payment_method}</b><br />
              <c:if test="${list.info.shipping_method ne null && list.info.shipping_method!=''}">
              배송방법: <b>${list.info.shipping_method}</b><br />
              </c:if>
          </td>
        </tr>
   
        <tr>
          <td><address>${list.info.payment_address}
          	  <br>${list.info.email}
          	</address></td>
          <td><address>${list.info.shipping_address}
          	  <br>전화번호: ${list.info.shipping_telephone}  
              <c:if test="${list.info.requisition_id!=''}"><br>개인통관번호: ${list.info.requisition_id}</c:if>
          	</address></td>
        </tr>
      </tbody>
    </table>
    <table class="table borderless order-list">
      <thead>
        <tr>
          <td><b>수량</b></td>
          <td><b>제조사</b></td>
          <td><b>모델</b></td>    
          <td><b>제품명</b></td>              
          <td class="text-right"><b>가격</b></td>
          <td class="text-right"><b>합계</b></td>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${list.products}" var="product">
        <tr>
          <td class="w_special"><b>${product.quantity}</b></td>
          <td>${product.manufacturer_name}</td>
          <td>${product.model}</td>
          <td><c:if test="${product.icebox=='1'}"><b>[ICE BOX] </b></c:if>
            <c:choose>
            <c:when test="${product.jan ne null && product.jan!=''}">
              <span class="w_special">${product.jan}</span>
            </c:when>
            <c:otherwise>
              ${product.name}
            </c:otherwise>
            </c:choose>
          </td>
          <td class="text-right"><strong>${product.price}</strong></td>
          <td class="text-right"><strong>${product.total}</strong></td>
        </tr>
        </c:forEach>
        <tr class="spacer"><td>&nbsp;</td></tr>
        <c:forEach items="${list.totals}" var="total">
        <tr class="row-total">
          <td class="text-right" colspan="5" >
            <c:choose>
            <c:when test="${total.code=='total'}">
              <strong>${total.title}</strong>
            </c:when>
            <c:otherwise>
              ${total.title}
            </c:otherwise>
            </c:choose>
          </td>
          <td class="text-right"><strong>${ctag:getCurrency(total.value)}</strong></td>
        </tr>
        </c:forEach>
      </tbody>
    </table>
    <c:if test="${list.info.comment!=''}">
    <table class="table table-bordered">
      <thead>
        <tr>
          <td><b>주문 메모</b></td>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>${list.info.comment}</td>
        </tr>
      </tbody>
    </table>
    </c:if>
  </div>
  </c:forEach>
</div>
</body>
</html>