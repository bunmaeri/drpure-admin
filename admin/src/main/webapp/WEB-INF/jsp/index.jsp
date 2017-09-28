<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> Dashboard</h1>
        <div class="row">
	        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 margin-bottom-10">
	            <div class="dashboard-stat blue">
	                <div class="visual">
	                    <i class="fa fa-briefcase fa-icon-medium"></i>
	                </div>
	                <div class="details">
	                    <div class="number"> ${ctag:getNumber(orders.total_orders)} 건</div>
	                    <div class="desc"> ${orders.title} </div>
	                </div>
	                <a class="more" href="${orders.view_more}"> View more
	                    <i class="m-icon-swapright m-icon-white"></i>
	                </a>
	            </div>
	        </div>
	        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
	            <div class="dashboard-stat red">
	                <div class="visual">
	                    <i class="fa fa-shopping-cart"></i>
	                </div>
	                <div class="details">
	                    <div class="number"> ${ctag:getCurrency(sales.total_orders)} </div>
	                    <div class="desc"> ${sales.title} </div>
	                </div>
	                <a class="more" href="${sales.view_more}"> View more
	                    <i class="m-icon-swapright m-icon-white"></i>
	                </a>
	            </div>
	        </div>
	        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
	            <div class="dashboard-stat green">
	                <div class="visual">
	                    <i class="fa fa-group fa-icon-medium"></i>
	                </div>
	                <div class="details">
	                    <div class="number"> ${ctag:getNumber(customers.total_orders)} 명</div>
	                    <div class="desc"> ${customers.title} </div>
	                </div>
	                <a class="more" href="${customers.view_more}"> View more
	                    <i class="m-icon-swapright m-icon-white"></i>
	                </a>
	            </div>
	        </div>
	    </div>
	    <div class="row">
	        <div class="col-md-12">
	            <div class="portlet light portlet-fit bordered">
	                <div class="portlet-title">
	                    <div class="caption">
	                        <i class=" icon-layers font-green"></i>
	                        <span class="caption-subject font-green bold uppercase">주문 수량 Chart</span>
	                    </div>
	                    
	                    <div class="actions">
	                    	<a class="btn btn-circle btn-icon-only btn-default" href="/index.dr?from_date=${mon2}">
	                            2개월
	                        </a>
	                        <a class="btn btn-circle btn-icon-only btn-default" href="/index.dr?from_date=${mon3}">
	                            3개월
	                        </a>
	                        <a class="btn btn-circle btn-icon-only btn-default" href="/index.dr?from_date=${mon6}">
	                            6개월
	                        </a>
	                        <a class="btn btn-circle btn-icon-only btn-default" href="/index.dr?from_date=${mon9}">
	                            9개월
	                        </a>
	                        <a class="btn btn-circle btn-icon-only btn-default" href="/index.dr?from_date=${year1}">
	                            1년
	                        </a>
	                        <a class="btn btn-circle btn-icon-only btn-default" href="/index.dr?from_date=${year2}">
	                            2년
	                        </a>
	                        <a class="btn btn-circle btn-icon-only btn-default" href="/index.dr?from_date=${year3}">
	                            3년
	                        </a>
	                        <a class="btn btn-circle btn-icon-only btn-default" href="/index.dr?from_date=${year5}">
	                            5년
	                        </a>
	                    </div>
	                    <%-- <div class="actions">
	                    <form action="/index.dr" id="form" class="horizontal-form">
	                    	<div class="input-group input-large date-picker input-daterange" data-date="${today}" data-date-format="yyyy-mm-dd">
                                <input type="text" class="form-control" name="from_date" id="from_date" value="${from_date}">
                                <span class="input-group-addon"> to </span>
                                <input type="text" class="form-control" name="to_date" id="to_date" value="${to_date}">
                            </div>
                            <a class="btn btn-circle btn-icon-only btn-default" id="a_search" href="/index.dr?from_date=${year5}">
	                            <i class="fa fa-search"></i>
	                        </a>
                        </form>
	                    </div> --%>
	                </div>
	                <div class="portlet-body">
	                    <div id="highchart_1" style="height:350px;"></div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <div class="row">
	        <div class="col-md-12">
	            <div class="portlet light portlet-fit bordered">
	                <div class="portlet-title">
	                    <div class="caption">
	                        <i class=" icon-layers font-green"></i>
	                        <span class="caption-subject font-green bold uppercase">국가별 주문 수량 Chart</span>
	                    </div>
	                    
	                    <div class="actions">
	                    	<a class="btn btn-circle btn-icon-only btn-default" href="/index.dr?from_date=${mon2}">
	                            2개월
	                        </a>
	                        <a class="btn btn-circle btn-icon-only btn-default" href="/index.dr?from_date=${mon3}">
	                            3개월
	                        </a>
	                        <a class="btn btn-circle btn-icon-only btn-default" href="/index.dr?from_date=${mon6}">
	                            6개월
	                        </a>
	                        <a class="btn btn-circle btn-icon-only btn-default" href="/index.dr?from_date=${mon9}">
	                            9개월
	                        </a>
	                        <a class="btn btn-circle btn-icon-only btn-default" href="/index.dr?from_date=${year1}">
	                            1년
	                        </a>
	                        <a class="btn btn-circle btn-icon-only btn-default" href="/index.dr?from_date=${year2}">
	                            2년
	                        </a>
	                        <a class="btn btn-circle btn-icon-only btn-default" href="/index.dr?from_date=${year3}">
	                            3년
	                        </a>
	                        <a class="btn btn-circle btn-icon-only btn-default" href="/index.dr?from_date=${year5}">
	                            5년
	                        </a>
	                    </div>
	                </div>
	                <div class="portlet-body">
	                    <div id="highchart_2" style="height:350px;"></div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <div class="row">
	        <div class="col-md-6">
	            <!-- Begin: life time stats -->
	            <div class="portlet light bordered">
	                <div class="portlet-title" style="margin-bottom:0;border-bottom:none;">
	                    <div class="caption">
	                        <i class="icon-share font-blue"></i>
	                        <span class="caption-subject font-blue bold uppercase">가입 경로(2017.04.18 이후)</span>
	                    </div>
	                </div>
	                <div class="portlet-body">
	                    <div class="tabbable-line">
	                        <div class="tab-content" style="padding:0;border-top:none;">
	                            <div class="tab-pane active" id="overview_1">
	                                <div class="table-responsive">
	                                    <table class="table table-striped table-hover table-bordered">
	                                        <thead>
	                                            <tr>
	                                                <th> 가입경로 </th>
	                                                <th> 고객(명) </th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:forEach items="${join_path}" var="item">
	                                            <tr>
	                                                <td>${item.join_path_name}</td>
	                                                <td>${ctag:getNumber(item.cnt)}</td>
	                                            </tr>
	                                            </c:forEach>
	                                        </tbody>
	                                    </table>
	                                </div>
	                            </div>
	                            
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <!-- End: life time stats -->
	        </div>
	        <div class="col-md-6">
	            <!-- Begin: life time stats -->
	            <div class="portlet light bordered">
	                <div class="portlet-title" style="margin-bottom:0;border-bottom:none;">
	                    <div class="caption">
	                        <i class="icon-share font-blue"></i>
	                        <span class="caption-subject font-blue bold uppercase">최근 주문 정보</span>
	                    </div>
	                </div>
	                <div class="portlet-body">
	                    <div class="tabbable-line">
	                        <div class="tab-content" style="padding:0;border-top:none;">
	                            <div class="tab-pane active" id="overview_1">
	                                <div class="table-responsive">
	                                    <table class="table table-striped table-hover table-bordered">
	                                        <thead>
	                                            <tr>
	                                                <th> 주문번호 </th>
	                                                <th> 고객 </th>
	                                                <th> 주문상태 </th>
	                                                <th> 주문금액 </th>
	                                                <th> 주문일 </th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:forEach items="${recent}" var="item">
	                                            <tr>
	                                                <td><a href="/sales/order/view/${item.order_id}/${item.store_id}.dr">${item.order_id}</a></td>
	                                                <td>${item.customer_name}</td>
	                                                <td>${item.order_status}</td>
	                                                <td>${ctag:getCurrency(item.total)}</td>
	                                                <td>${item.date_added}</td>
	                                            </tr>
	                                            </c:forEach>
	                                        </tbody>
	                                    </table>
	                                </div>
	                            </div>
	                            
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <!-- End: life time stats -->
	        </div>
	    </div>
	    
	</div>
	<!-- END CONTENT BODY -->
</div>

<%@include file="/WEB-INF/inc/footer.jspf" %>
<script type="text/javascript"><!--
// 검색
$('#a_search').on('click', function() {
	var action = '/index.dr';
	$("#form").attr("action", action);
	$('#form').submit();
});
</script>
<script>
jQuery(document).ready(function() {
    $("#highchart_1").highcharts({
        chart: {
            style: {
                fontFamily: "Verdana"
            }
        },
        title: {
            text: "조회기간: ${from_date} ~ ${to_date}",
            x: -20
        },
        xAxis: {
            categories: [
            	<c:forEach items="${dailyOrderTotal}" var="item" varStatus="status">
	        	    <c:if test="${status.index>0}">,</c:if>"${item.date_added}(${item.day_name_kor})"
	        	</c:forEach>
            ]
        },
        yAxis: {
            title: {
                text: "주문수량"
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: "#808080"
            }]
        },
        tooltip: {
            valueSuffix: "건"
        },
        legend: {
            layout: "vertical",
            align: "right",
            verticalAlign: "middle",
            borderWidth: 0
        },
        series: [{
            name: "전체",
            data: [
            	<c:forEach items="${dailyOrderTotal}" var="item" varStatus="status">
            	    <c:if test="${status.index>0}">,</c:if>${item.cnt}
            	</c:forEach>
            	]
        }, {
            name: "DR",
            data: [
            	<c:forEach items="${dailyOrderDrTotal}" var="item" varStatus="status">
            	    <c:if test="${status.index>0}">,</c:if>${item.cnt}
            	</c:forEach>
            	]
        }, {
            name: "MY",
            data: [
            	<c:forEach items="${dailyOrderMyTotal}" var="item" varStatus="status">
            	    <c:if test="${status.index>0}">,</c:if>${item.cnt}
            	</c:forEach>
            	]
        }]
    })
    
    $("#highchart_2").highcharts({
        chart: {
            style: {
                fontFamily: "Verdana"
            }
        },
        title: {
            text: "조회기간: ${from_date} ~ ${to_date}",
            x: -20
        },
        xAxis: {
            categories: [
            	<c:forEach items="${dailyOrderTotal}" var="item" varStatus="status">
	        	    <c:if test="${status.index>0}">,</c:if>"${item.date_added}(${item.day_name_kor})"
	        	</c:forEach>
            ]
        },
        yAxis: {
            title: {
                text: "주문수량"
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: "#808080"
            }]
        },
        tooltip: {
            valueSuffix: "건"
        },
        legend: {
            layout: "vertical",
            align: "right",
            verticalAlign: "middle",
            borderWidth: 0
        },
        series: [{
            name: "전체",
            data: [
            	<c:forEach items="${dailyOrderTotal}" var="item" varStatus="status">
            	    <c:if test="${status.index>0}">,</c:if>${item.cnt}
            	</c:forEach>
            	]
        }, {
            name: "한국",
            data: [
            	<c:forEach items="${dailyOrderKorTotal}" var="item" varStatus="status">
            	    <c:if test="${status.index>0}">,</c:if>${item.cnt}
            	</c:forEach>
            	]
        }, {
            name: "미국",
            data: [
            	<c:forEach items="${dailyOrderUsaTotal}" var="item" varStatus="status">
            	    <c:if test="${status.index>0}">,</c:if>${item.cnt}
            	</c:forEach>
            	]
        }]
    })
});
</script>
<%@include file="/WEB-INF/inc/bottom.jspf" %>
