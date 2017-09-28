<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/header.jspf"%>
<%@ include file="/WEB-INF/include/menu.jspf"%>
<div id="content">
	<div class="page-header">
		<div class="container-fluid">
			<h1>Customer Donator Report</h1>
			<ul class="breadcrumb">
				<li><a href="/">첫화면</a></li>
				<li><a href="/report/customers/donator.dr">Customer Donator Report</a></li>
			</ul>
		</div>
	</div>
	<div class="container-fluid">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					<i class="fa fa-bar-chart"></i> Customer Donator List
				</h3>
			</div>
			<div class="panel-body">
				<div class="well">
					<div class="row">
					<form action="/report/customers/reward.dr" method="post" id="form">
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
								<label class="control-label" for="input-group">Customer</label>
								<input type="text" name="customer" value="${filter.customer}" placeholder="Customer" id="customer" class="form-control" />
							</div>
						</div>
						<div class="col-sm-3">
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
								<td class="text-center">Customer Name</td>
								<td class="text-left">E-Mail</td>
								<td class="text-center">Customer Group</td>
								<td class="text-center">Order No</td>
								<td class="text-center">Order Date</td>
								<td class="text-center">Order Status</td>
								<td class="text-right">Donate Amount</td>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${list}" var="list">
							<tr>
								<td class="text-center"><a href="/customer/customer/edit/${list.customer_id}.dr">${list.customer_name}</a></td>
								<td class="text-left">${list.email}</td>
								<td class="text-center">${list.customer_group}</td>
								<td class="text-center"><a href="/sales/order/info/${list.order_id}.dr">${list.order_id}</a></td>
								<td class="text-center">${list.order_date}</td>
								<td class="text-center">${list.order_status}</td>
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
    comSubmit.setUrl("<c:url value='/report/customers/donator.dr' />");
    comSubmit.addParam("page", page);
    comSubmit.addParam("date_start", $('#date_start').val());
    comSubmit.addParam("date_end", $('#date_end').val());
    comSubmit.addParam("customer", $('#customer').val());
    comSubmit.submit();
}

$('.date').datetimepicker({
	pickTime: false
});
//--></script>
<script type="text/javascript"><!--
$('input[name=\'customer\']').autocomplete({
  'source': function(request, response) {
    $.ajax({
      url: '/customer/customer/autocomplete.dr?filter_name=' +  encodeURIComponent(request),
      dataType: 'json',
      success: function(json) {
        response($.map(json, function(item) {
          return {
            label: item['name'],
            value: item['customer_id']
          }
        }));
      }
    });
  },
  'select': function(item) {
    $('input[name=\'customer\']').val(item['label']);
  }
});
//--></script>
</div>
<%@ include file="/WEB-INF/include/footer.jspf"%>
<%@ include file="/WEB-INF/include/end.jspf"%>