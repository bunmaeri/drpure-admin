<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> <span style="color:red">[My Home Doc]</span> Products Viewed</h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	        	
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
		                                	<td class="" width="70%">Product Name</td>
											<td class="">Model</td>
											<td class="text-align-right">Viewed</td>
											<td class="text-align-right">Percent</td>
		                                </tr>
		                            </thead>
		                            <tbody>
		                            <c:forEach items="${list}" var="list">
										<tr>
											<td class="text-align-left">${list.name}</td>
											<td class="">${list.model}</td>
											<td class="text-align-right">${ctag:getNumber(list.viewed)}</td>
											<td class="text-align-right">${list.percent}%</td>
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
	            <form action="/my_reports/products/viewed.dr" id="form" class="horizontal-form">
	            <input type="hidden" name="page" id="page" value="${page}">
                <input type="hidden" name="page_end" id="page_end" value="${pageMaker.end}"/>
                </form>
	        </div>
	    </div>
	</div>
</div>

<%@include file="/WEB-INF/inc/footer.jspf" %>
<script type="text/javascript"><!--
// 페이지이동
function goPage(page) {
	var action = '/my_reports/products/viewed.dr';
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
