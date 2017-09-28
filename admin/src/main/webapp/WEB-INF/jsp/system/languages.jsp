<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> Languages</h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	        	<!-- BEGIN FORM-->
	            <form action="/settings/languages.dr" id="form" class="horizontal-form">
	            <div class="portlet light portlet-fit portlet-datatable bordered">
	                <div class="portlet-body">
	                    <div class="table-container dataTables_wrapper no-footer">
	                    	<div class="table-responsive">
			                    <table class="table table-striped table-bordered table-hover table-checkable" id="datatable_orders">
		                            <thead>
		                                <tr role="row" class="heading">
		                                    <th> 이미지 </th>
		                                    <th width="60%"> 언어 </th>
		                                    <th> 정렬 순서 </th>
		                                    <th> Status </th>
		                                </tr>
		                            </thead>
		                            <tbody>
									<c:forEach items="${list}" var="item">
										<tr role="row">
											<td class="text-align-center"><a href="/settings/language/image/${item.language_id}.dr" target="_self"><img src="/image/${item.image}" class="img-responsive" style="width:auto;height:15px;margin-left:auto;margin-right:auto;display:block;"></a></td>
											<td class="text-align-left"><a href="/settings/language/info/${item.language_id}.dr">${item.name}</a></td>
											<td class="text-align-center">${item.sort_order}</td>
											<td class="text-align-center">${ctag:getYesOrNo(item.status)}</td>
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
