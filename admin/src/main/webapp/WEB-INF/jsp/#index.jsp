<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/header.jspf"%>
<%@ include file="/WEB-INF/include/menu.jspf"%>
<div id="content">
	<div class="page-header">
		<div class="container-fluid">
			<h1>Dashboard</h1>
			<ul class="breadcrumb">
				<li><a href="/">첫화면</a></li>
				<li><a href="/">Dashboard</a></li>
			</ul>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-3 col-md-3 col-sm-6">
				<div class="tile">
					<div class="tile-heading">
						Total Orders <span class="pull-right"> <i class="fa fa-caret-down"></i> ${orders.percentage}%</span>
					</div>
					<div class="tile-body">
						<i class="fa fa-shopping-cart"></i>
						<h2 class="pull-right">${orders.total_orders}</h2>
					</div>
					<div class="tile-footer">
						<a href="${orders.view_more}">View more...</a>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-6">
				<div class="tile">
					<div class="tile-heading">
						Total Sales <span class="pull-right"> <i class="fa fa-caret-down"></i> ${sales.percentage}%</span>
					</div>
					<div class="tile-body">
						<i class="fa fa-credit-card"></i>
						<h2 class="pull-right">${sales.total_sales}</h2>
					</div>
					<div class="tile-footer">
						<a href="${sales.view_more}">View more...</a>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-6">
				<div class="tile">
					<div class="tile-heading">
						Total Customers <span class="pull-right"> <i class="fa fa-caret-up"></i> ${customers.percentage}%</span>
					</div>
					<div class="tile-body">
						<i class="fa fa-user"></i>
						<h2 class="pull-right">${customers.total_sales}</h2>
					</div>
					<div class="tile-footer">
						<a href="${customers.view_more}">View more...</a>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-6">
				<div class="tile">
					<div class="tile-heading">People Online</div>
					<div class="tile-body">
						<i class="fa fa-users"></i>
						<h2 class="pull-right">0</h2>
					</div>
					<div class="tile-footer">
						<a href="https://drpurenatural.com/admin/index.php?route=report/customer_online&amp;token=F8SCTnuTtYGRfAPIYLrIb5gXmxKI3RiA">View more...</a>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6 col-md-12 col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="fa fa-globe"></i> World Map
						</h3>
					</div>
					<div class="panel-body">
						<div id="vmap" style="width: 100%; height: 260px;"></div>
					</div>
				</div>
				<link type="text/css" href="/view/javascript/jquery/jqvmap/jqvmap.css" rel="stylesheet" media="screen" />
				<script type="text/javascript" src="/view/javascript/jquery/jqvmap/jquery.vmap.js"></script>
				<script type="text/javascript" src="/view/javascript/jquery/jqvmap/maps/jquery.vmap.world.js"></script>
				
			</div>
			<div class="col-lg-6 col-md-12 col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="pull-right">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
								class="fa fa-calendar"></i> <i class="caret"></i></a>
							<ul id="range" class="dropdown-menu dropdown-menu-right">
								<li><a href="day">Today</a></li>
								<li><a href="week">Week</a></li>
								<li class="active"><a href="month">Month</a></li>
								<li><a href="year">Year</a></li>
							</ul>
						</div>
						<h3 class="panel-title">
							<i class="fa fa-bar-chart-o"></i> Sales Analytics
						</h3>
					</div>
					<div class="panel-body">
						<div id="chart-sale" style="width: 100%; height: 260px;"></div>
					</div>
				</div>
				<script type="text/javascript" src="/view/javascript/jquery/flot/jquery.flot.js"></script>
				<script type="text/javascript" src="/view/javascript/jquery/flot/jquery.flot.resize.min.js"></script>
				
			</div>
		</div>
		<div class="row">
			<div class="col-lg-4 col-md-12 col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="fa fa-shopping-cart"></i> 가입 경로(2017.04.18 이후)
						</h3>
					</div>
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<td class="text-center">가입경로</td>
									<td class="text-center">고객(명)</td>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${join_path}" var="item">
								<tr>
									<td class="text-center">${item.join_path_name}</td>
									<td class="text-center">${ctag:getNumber(item.cnt)}</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-lg-8 col-md-12 col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="fa fa-shopping-cart"></i> Latest Orders
						</h3>
					</div>
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<td class="text-right">Order ID</td>
									<td>Customer</td>
									<td>Status</td>
									<td>Date Added</td>
									<td class="text-right">Total</td>
									<td class="text-right">Action</td>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${recent}" var="item">
								<tr>
									<td class="text-right">${item.order_id}</td>
									<td>${item.customer_name}</td>
									<td>${item.order_status}</td>
									<td>${item.date_added}</td>
									<td class="text-right">${ctag:getCurrency(item.total)}</td>
									<td class="text-right"><a href="/sale/order/${item.order_id}.dr" data-toggle="tooltip" title="보기" class="btn btn-info"><i class="fa fa-eye"></i></a></td>
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


<%@ include file="/WEB-INF/include/footer.jspf"%>
<%@ include file="/WEB-INF/include/end.jspf"%>
