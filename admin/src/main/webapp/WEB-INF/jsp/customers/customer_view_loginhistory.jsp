<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> 고객 정보 <small>edit customer</small></h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	            <form class="form-horizontal form-row-seperated" id="form" action="/customer/save/reward.dr" method="post">
	                <div class="portlet">
	                    <div class="portlet-title">
	                        <div class="caption">
	                            <i class="icon-user"></i> ${info.customer_name} (#${info.customer_id})</div>
	                        <div class="actions btn-set">
	                            <button type="button" name="back" class="btn btn-secondary-outline" onclick="javascript:location='/customer/back.dr'"><i class="fa fa-angle-left"></i> Back</button>
	                        </div>
	                    </div>
	                    <div class="portlet-body">
	                        <div class="tabbable-bordered">
	                            <ul class="nav nav-tabs">
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/customer/view/${info.customer_id}.dr'" data-toggle="tab"> 기본정보 </a>
	                                </li>
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/customer/view/orderhistory/${info.customer_id}.dr'" data-toggle="tab"> 주문이력 </a>
	                                </li>
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/customer/view/reward/${info.customer_id}.dr'" data-toggle="tab"> 적립포인트 </a>
	                                </li>
	                                <li class="active">
	                                    <a href="javascript:;" data-toggle="tab"> Login History </a>
	                                </li>
	                            </ul>
	                            <div class="tab-content">
	                                <div class="tab-pane active" id="tab_general">
	                                	<div class="portlet light portlet-fit portlet-datatable bordered">
							                <div class="portlet-body">
							                    <div class="table-container dataTables_wrapper no-footer">
							                    	<div id="datatable_orders_wrapper" class="dataTables_wrapper dataTables_extended_wrapper no-footer">
							                    		<div class="row">
							                    			<div class="col-md-6 col-sm-6 pull-left">
							                    				<p>${pageMaker.from} ~ ${pageMaker.to} 표시 중 - <strong>총 ${pageMaker.count}개</strong></p>
							                    		    </div>
							                    		    <div class="col-md-6 col-sm-6 text-align-right">
							                    				<p><strong>로그인 횟수: ${ctag:getNumber(pageMaker.count)}회</strong></p>
							                    		    </div>
							                    		</div>
							                    	</div>
							                    	<div class="table-responsive">
									                    <table class="table table-striped table-bordered table-hover table-checkable" id="datatable_orders">
								                            <thead>
								                                <tr role="row" class="heading">
								                                    <th> 로그인 일자 </th>
								                                    <th> 접속 아이피 </th>
								                                </tr>
								                            </thead>
								                            <tbody>
								                            <c:forEach items="${list}" var="item">
								                            	<tr role="row">
								                            		<td>${item.login_date}</td>
								                            		<td>${item.ip}</td>
								                            	</tr>
								                            </c:forEach>
								                            </tbody>
								                        </table>
								                    </div>
							                    </div>
							                    <c:if test="${pageMaker.end>1}">
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
	                </div>
	                <input type="hidden" name="customer_id" id="customer_id" value="${info.customer_id}"/>
	                <input type="hidden" name="page" id="page" value="${page}">
	            </form>
	        </div>
	    </div>
	</div>
</div>

<%@include file="/WEB-INF/inc/footer.jspf" %>
<script type="text/javascript"><!--
//페이지이동
function goPage(page) {
	var action = '/customer/view/loginhistory/'+$('#customer_id').val()+'.dr';
	$('#page').val(page);
	$("#form").attr("action", action);
	$('#form').submit();
}
//--></script> 
<%@include file="/WEB-INF/inc/bottom.jspf" %>
