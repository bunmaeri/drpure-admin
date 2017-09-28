<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> 고객 그룹 </h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	        	<!-- BEGIN FORM-->
	            <form action="/customers_groups.dr" id="form" class="horizontal-form">
	            <div class="portlet light portlet-fit portlet-datatable bordered">
	            	<div class="portlet-title">
	                    <div class="actions btn-set">
	                    	<button type="button" class="btn green" onclick="javascript:location='/customers_groups/view/0.dr'"><i class="icon-plus"></i> 추가</button>
		                </div>
	                </div>
	                <div class="portlet-body">
	                    <div class="table-container dataTables_wrapper no-footer">
	                    	<div class="table-responsive">
			                    <table class="table table-striped table-bordered table-hover table-checkable" id="datatable_orders">
		                            <thead>
		                                <tr role="row" class="heading">
		                                    <th> 그룹명 </th>
		                                    <th> 적립 포인트 </th>
		                                    <th> 그룹 설명 </th>
		                                </tr>
		                            </thead>
		                            <tbody>
		                            <c:forEach items="${list}" var="item">
		                            	<tr role="row">
		                            		<td class="text-align-center"><a href="/customers_groups/view/${item.customer_group_id}.dr" target="_self">${item.name}</a></td>
		                            		<td class="text-align-center">${item.reward} <c:if test="${item.reward!=''}">%</c:if></td>
		                            		<td class="text-align-left">${item.description}</td>
		                            	</tr>
		                            </c:forEach>
		                            </tbody>
		                        </table>
		                    </div>
	                    </div>
	                </div>
	            </div>
	            </form>
	            <!-- END FORM-->
	        </div>
	    </div>
	</div>
</div>

<%@include file="/WEB-INF/inc/footer.jspf" %>
<%@include file="/WEB-INF/inc/bottom.jspf" %>
