<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/header.jspf"%>
<%@ include file="/WEB-INF/include/menu.jspf"%>
<div id="content">
	<div class="page-header">
		<div class="container-fluid">
			<h1>Products Viewed Report</h1>
			<ul class="breadcrumb">
				<li><a href="/">첫화면</a></li>
				<li><a href="/report/products/viewed.dr">Products Viewed</a></li>
			</ul>
		</div>
	</div>
	<div class="container-fluid">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					<i class="fa fa-bar-chart"></i> Products Viewed List
				</h3>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr>
								<td class="text-left">Product Name</td>
								<td class="text-left">Model</td>
								<td class="text-right">Viewed</td>
								<td class="text-right">Percent</td>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${list}" var="list">
							<tr>
								<td class="text-left">${list.name}</td>
								<td class="text-left">${list.model}</td>
								<td class="text-right">${ctag:getNumber(list.viewed)}</td>
								<td class="text-right">${list.percent}%</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="row">
					<div class="col-sm-9 text-left">
					<c:if test="${pageMaker.end>1}">
					    <ul class="pagination">
					    <c:if test="${pageMaker.prev}">
					        <li><a href="javascript:;" onclick="goMovePage('1')">|&lt;</a></li>
					        <li><a href="javascript:;" onclick="goMovePage('${pageMaker.prevZone}')">&lt;</a></li>
					    </c:if>
					    <c:forEach begin="${pageMaker.start}" end="${pageMaker.end}" var="idx">
                            <c:choose>
                            <c:when test="${idx == pageMaker.page}">
                                <li class="active"><span>${idx}</span></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="javascript:;" onclick="goMovePage('${idx}')">${idx}</a></li>
                            </c:otherwise>
                            </c:choose>
                        </c:forEach>
					    <c:if test="${pageMaker.next}">
					        <li><a href="javascript:;" onclick="goMovePage('${pageMaker.nextZone}')">&gt;</a></li>
					        <li><a href="javascript:;" onclick="goMovePage('${pageMaker.totalPage}')">&gt;|</a></li>
					    </c:if>
					    </ul>
					</c:if>
					</div>
					<div class="col-sm-3 text-right">Showing ${pageMaker.from} ~ ${pageMaker.to} of ${pageMaker.count} (${pageMaker.page} Page)</div>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript"><!--
function goMovePage(page) {
    var comSubmit = new ComSubmit();
    comSubmit.setUrl("<c:url value='/report/products/viewed.dr' />");
    comSubmit.addParam("page", page);
    comSubmit.submit();
}
//--></script>
</div>
<%@ include file="/WEB-INF/include/footer.jspf"%>
<%@ include file="/WEB-INF/include/end.jspf"%>