<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/header.jspf"%>
<%@ include file="/WEB-INF/include/menu.jspf"%>
<div id="content">
	<div class="page-header">
		<div class="container-fluid">
			<h1>Products Purchased Report</h1>
			<ul class="breadcrumb">
				<li><a href="/">첫화면</a></li>
				<li><a href="/report/products/purchased.dr">Products Purchased Report</a></li>
			</ul>
		</div>
	</div>
	<div class="container-fluid">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					<i class="fa fa-bar-chart"></i> Products Purchased List
				</h3>
			</div>
			<div class="panel-body">
				<div class="well">
					<div class="row">
					<form action="/report/products/purchased.dr" method="post" id="form">
						<div class="col-sm-3">
							<div class="form-group">
								<label class="control-label" for="input-date-start">Date Start</label>
								<div class="input-group date">
									<input type="text" name="date_start" value="${filter.date_start}" placeholder="Date Start" data-date-format="YYYY-MM-DD" id="date_start" class="form-control" />
									<span class="input-group-btn">
										<button type="button" class="btn btn-default"><i class="fa fa-calendar"></i></button>
									</span>
								</div>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="form-group">
								<label class="control-label" for="input-date-end">Date End</label>
								<div class="input-group date">
									<input type="text" name="date_end" value="${filter.date_end}" placeholder="Date End" data-date-format="YYYY-MM-DD" id="date_end" class="form-control" />
									<span class="input-group-btn">
										<button type="button" class="btn btn-default"><i class="fa fa-calendar"></i></button>
									</span>
								</div>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="form-group">
								<label class="control-label" for="order_status_id">Order Status</label>
								<select name="order_status_id" id="order_status_id" class="form-control">
								    <option value="">All Statuses</option>
								<c:forEach items="${orderStatus}" var="item">
									<option value="${item.order_status_id}" <c:if test="${item.order_status_id==filter.order_status_id}">selected</c:if>>${item.name}</option>
								</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="form-group">
								<label class="control-label" for="input-group">Manufacturer</label>
								<select name="manufacturer_id" id="manufacturer_id" class="form-control">
								    <option value="">All Manufacturers</option>
								<c:forEach items="${manufacturers}" var="item">
									<option value="${item.manufacturer_id}" <c:if test="${item.manufacturer_id==filter.manufacturer_id}">selected</c:if>>${item.name}</option>
								</c:forEach>
								</select>
							</div>
							<button type="submit" id="button-filter" class="btn btn-primary pull-right">
								<i class="fa fa-filter"></i> 검색
							</button>
						</div>
					</form>
					</div>
				</div>
				<div class="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr>
								<td class="text-left">Manufacturer</td>
								<td class="text-left">Product Name</td>
								<td class="text-left">Model</td>
								<td class="text-right">Quantity</td>
								<td class="text-right">Total</td>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${list}" var="list">
							<tr>
								<td class="text-left">${list.manufacturer}</td>
								<td class="text-left">${list.name}</td>
								<td class="text-left">${list.model}</td>
								<td class="text-right">${ctag:getNumber(list.quantity)}</td>
								<td class="text-right">${ctag:getCurrency(list.total)}</td>
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
    comSubmit.setUrl("<c:url value='/report/products/purchased.dr' />");
    comSubmit.addParam("page", page);
    comSubmit.addParam("date_start", $('#date_start').val());
    comSubmit.addParam("date_end", $('#date_end').val());
    comSubmit.addParam("manufacturer_id", $('#manufacturer_id').val());
    comSubmit.addParam("order_status_id", $('#order_status_id').val());
    comSubmit.submit();
}

$('.date').datetimepicker({
	pickTime: false
});
//--></script>
</div>
<%@ include file="/WEB-INF/include/footer.jspf"%>
<%@ include file="/WEB-INF/include/end.jspf"%>