<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> <span style="color:red">[My Home Doc]</span> 제품 카테고리</h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	        	<!-- BEGIN FORM-->
	            <form action="/my_categories.dr" id="form" class="horizontal-form">
	            <div class="portlet light portlet-fit portlet-datatable bordered">
	            	<div class="portlet-title">
	                    <div class="actions btn-set">
	                    	<button type="button" class="btn green" onclick="javascript:location='/my_category/view/0/1.dr'"><i class="icon-plus"></i> 추가</button>
		                </div>
	                </div>
	                <div class="portlet-body">
	                    <div class="table-container dataTables_wrapper no-footer">
	                    	<div class="table-responsive">
			                    <table class="table table-striped table-bordered table-hover table-checkable" id="datatable_orders">
		                            <thead>
		                                <tr role="row" class="heading">
		                                    <th> 이미지 </th>
		                                    <th> 카테고리 경로 </th>
		                                    <th> 카테고리 명 </th>
		                                    <th> 활성여부 </th>
		                                </tr>
		                            </thead>
		                            <tbody>
		                            <c:forEach items="${list}" var="item">
		                            	<tr role="row">
		                            		<td class="text-align-center"><a href="/my_category/view/image/${item.category_id}/1.dr" target="_self"><img src="/image/${item.image}" class="img-responsive" style="width:auto;height:50px;margin-left:auto;margin-right:auto;display:block;"></a></td>
		                            		<td class="text-align-left"><a href="/my_category/view/meta/${item.category_id}/1.dr" target="_self">${item.path_name}</a></td>
		                            		<td class="text-align-left">${item.name}</td>
		                            		<td class="text-align-center">${ctag:getActive(item.status)}</td>
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
