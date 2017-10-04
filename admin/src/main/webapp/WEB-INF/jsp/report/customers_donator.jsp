<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> Customer Donator</h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	        	
	            <div class="portlet box blue">
	                <div class="portlet-title">
	                    &nbsp;
	                </div>
	                <div class="portlet-body form">
	                    <!-- BEGIN FORM-->
	            		<form action="/reports/customers/donator.dr" id="form" class="horizontal-form">
                        <div class="form-body">
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label class="control-label">Date Start</label>
                                        <div class="input-group date date-picker" data-provide="datepicker" data-date-format="yyyy-mm-dd" data-date="${filter.date_start}">
                                           <input type="text" class="form-control form-filter" name="date_start" id="date_start" value="${filter.date_start}">
                                           <span class="input-group-btn">
                                               <button class="btn default" type="button">
                                                   <i class="fa fa-calendar"></i>
                                               </button>
                                           </span>
                                        </div>
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label class="control-label">Date End</label>
                                        <div class="input-group date date-picker" data-provide="datepicker" data-date-format="yyyy-mm-dd" data-date="${filter.date_end}">
                                           <input type="text" class="form-control form-filter" name="date_end" id="date_end" value="${filter.date_end}">
                                           <span class="input-group-btn">
                                               <button class="btn default" type="button">
                                                   <i class="fa fa-calendar"></i>
                                               </button>
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
                                
                            </div>
                            
                        </div>
                        <div>
	                        <div class="form-actions right" >
	                        	<button type="button" id="button-submit" class="btn blue"><i class="fa fa-search"></i> 검색</button>
	                        </div>
                        </div>
                        <input type="hidden" name="page" id="page" value="${page}">
                        <input type="hidden" name="page_end" id="page_end" value="${pageMaker.end}"/>
	                    </form>
	            		<!-- END FORM-->
	                </div>
	            </div>
	            
	            <div class="portlet light portlet-fit portlet-datatable bordered">
	                <div class="portlet-body">
	                    <div class="table-container dataTables_wrapper no-footer">
	                    	<c:if test="${pageMaker.end>0}">
	                    	<div id="datatable_orders_wrapper" class="dataTables_wrapper dataTables_extended_wrapper no-footer">
	                    		<div class="row">
	                    			<div class="col-md-6 col-sm-6 pull-left">
	                    				<div class="dataTables_paginate paging_bootstrap_extended">
		                    				<div class="pagination-panel left">
		                    					Page <a href="javascript:;" onclick="goPage(${page-1})" class="btn btn-sm default prev"><i class="fa fa-angle-left"></i></a>
		                    					<input type="text" id="click_page" class="pagination-panel-input form-control input-sm input-inline input-mini" value="${page}" style="text-align:center; margin: 0 5px;" readonly>
		                    					<a href="javascript:;" onclick="goPage(${page+1})" class="btn btn-sm default next"><i class="fa fa-angle-right"></i></a> of <span class="pagination-panel-total">${pageMaker.end}</span>
		                    				</div>
	                    				</div>
	                    		    </div>
	                    		    <div class="col-md-6 col-sm-6 text-align-right">
	                    		    	<p>${pageMaker.from} ~ ${pageMaker.to} 표시 중 - <strong>총 ${pageMaker.count}개</strong></p>
	                    		    </div>
	                    		</div>
	                    	</div>
	                    	</c:if>
	                    	<div class="table-responsive">
			                    <table class="table table-striped table-bordered table-hover table-checkable" id="datatable_orders">
		                            <thead>
		                                <tr role="row" class="heading">
		                                	<td class="text-align-left" width="15%">Customer Name</td>
											<td class="text-align-left" width="20%">E-Mail</td>
											<td class="">Customer Group</td>
											<td class="">Order No</td>
											<td class="">Order Date</td>
											<td class="">Order Status</td>
											<td class="text-align-right">Total</td>
											<td class="text-align-right">Donate Amount</td>
		                                </tr>
		                            </thead>
		                            <tbody>
		                            <c:forEach items="${list}" var="list">
										<tr>
											<td class="text-align-left"><a href="/customer/view/${list.customer_id}.dr" target="_blank">${list.customer_name}</a></td>
											<td class="text-align-left">${list.email}</td>
											<td class="">${list.customer_group}</td>
											<td class=""><a href="/sales/order/view/${list.order_id}/0.dr" target="_blank">${list.order_id}</a></td>
											<td class="">${list.order_date}</td>
											<td class="">${list.order_status}</td>
											<td class="text-align-right">${ctag:getCurrency(list.total)}</td>
											<td class="text-align-right">${ctag:getCurrency(list.shipping_total)}</td>
										</tr>
									</c:forEach>
		                            </tbody>
		                        </table>
		                    </div>
	                    </div>
	                    <c:if test="${pageMaker.end>0}">
	                    <div class="row">
	                    	<div class="col-md-4 col-sm-4">
	                    		<p style="float:left;">${pageMaker.from} ~ ${pageMaker.to} 표시 중 - <strong>총 ${pageMaker.count}개</strong></p>
	                    	</div>
	                    	<div class="col-md-8 col-sm-8">
	                    		<div class="dataTables_paginate paging_bootstrap_full_number">
		                        	<ul class="pagination" style="visibility: visible;">
		                        	<c:if test="${pageMaker.prev}">
		                        		<li class="prev"><a href="javascript:;" onclick="goPage(1)" title="First"><i class="fa fa-angle-double-left"></i></a></li>
		                        		<li class="prev"><a href="javascript:;" onclick="goPage(${pageMaker.page-1})" title="Prev"><i class="fa fa-angle-left"></i></a></li>
		                        	</c:if>
	                        		<c:forEach begin="${pageMaker.start}" end="${pageMaker.end}" var="idx">
		                                <c:choose>
		                                <c:when test="${idx == pageMaker.page}">
		                                    <li class="active"><span>${idx}</span></li>
		                                </c:when>
		                                <c:otherwise>
		                                    <li><a href="javascript:;" onclick="goPage(${idx})">${idx}</a></li>
		                                </c:otherwise>
		                                </c:choose>
		                            </c:forEach>
		                        	<c:if test="${pageMaker.next }">
		                        		<li class="next"><a href="javascript:;" onclick="goPage(${pageMaker.page+1})" title="Next"><i class="fa fa-angle-right"></i></a></li>
		                        		<li class="next"><a href="javascript:;" onclick="goPage(${pageMaker.end})" title="Last"><i class="fa fa-angle-double-right"></i></a></li>
		                        	</c:if>
		                        	</ul>
		                        </div>
	                    	</div>
	                    </div>
	                    </c:if>
	                </div>
	            </div>
	            
	        </div>
	    </div>
	</div>
</div>

<%@include file="/WEB-INF/inc/footer.jspf" %>
<script type="text/javascript"><!--
// 검색
$('#button-submit').on('click', function() {
	var action = '/reports/customers/donator.dr';
	$('#page').val(1);
	$('#click_page').val(1);
	$("#form").attr("action", action);
	$('#form').submit();
});

// 페이지이동
function goPage(page) {
	var action = '/reports/customers/donator.dr';
	if(page<1) {
		$('#page').val(1);
	} else {
		$('#page').val(page);
	}
	var page_end = $('#page_end').val();
	if(page>page_end) {
		$('#page').val(page_end);
	}
	$("#form").attr("action", action);
	$('#form').submit();
}
//--></script> 
<%@include file="/WEB-INF/inc/bottom.jspf" %>
