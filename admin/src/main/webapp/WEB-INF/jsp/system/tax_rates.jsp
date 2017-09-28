<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> Tax Rates</h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	        	<!-- BEGIN FORM-->
	            <form action="/settings/tax/rates/save.dr" id="form" class="horizontal-form">
	            <div class="portlet light portlet-fit portlet-datatable bordered">
	            	<div class="portlet-title">
	                    <div class="actions btn-set">
	                    	<button type="submit" id="button-submit" class="btn btn-success"><i class="fa fa-check"></i> Save</button>
		                </div>
	                </div>
	                <div class="portlet-body">
	                    <div class="table-container dataTables_wrapper no-footer">
	                    	<div class="table-responsive">
			                    <table class="table table-striped table-bordered table-hover table-checkable" id="datatable_orders">
		                            <thead>
		                                <tr role="row" class="heading">
		                                    <th> ID </th>
		                                    <th> Shipping Method </th>
		                                    <th> Tax Name </th>
		                                    <th> Tax Rate </th>
		                                    <th> </th>
		                                </tr>
		                            </thead>
		                            <tbody>
		                            <c:forEach items="${list}" var="item">
		                            	<tr role="row">
		                            		<td class="text-align-center">${item.tax_rate_id}<input type="hidden" name="tax_rate_id" id="tax_rate_id" value="${item.tax_rate_id}"></td>
		                            		<td class="text-align-left">
		                            			<select class="form-control input-large" id="geo_zone_id" name="geo_zone_id">
		                                        	<c:forEach items="${geoZoneList}" var="zone">
		                                        	<option value="${zone.geo_zone_id}" <c:if test="${item.geo_zone_id==zone.geo_zone_id}">selected</c:if>>${zone.name}</option>
		                                        	</c:forEach>
		                                        </select>
		                            		</td>
		                            		<td class="text-align-left"><input type="text" class="form-control" name="name" id="name" value="${item.name}"></td>
		                            		<td class="text-align-left"><input type="text" class="form-control" name="rate" id="rate" value="${item.rate}"></td>
		                            		<td class="text-center">
												<a href="javascript:;" data-toggle="tooltip" title="삭제" class="btn btn-sm btn-outline red" onclick="goDelete('${item.tax_rate_id}')"><i class="fa fa-trash"></i> 삭제</a>
											</td>
		                            	</tr>
		                            </c:forEach>
		                                <tr role="row">
		                            		<td class="text-align-center">추가<input type="hidden" name="tax_rate_id" id="tax_rate_id" value=""></td>
		                            		<td class="text-align-left">
		                            			<select class="form-control input-large" id="geo_zone_id" name="geo_zone_id">
		                                        	<c:forEach items="${geoZoneList}" var="zone">
		                                        	<option value="${zone.geo_zone_id}">${zone.name}</option>
		                                        	</c:forEach>
		                                        </select>
		                            		</td>
		                            		<td class="text-align-left"><input type="text" class="form-control" name="name" id="name" value=""></td>
		                            		<td class="text-align-left"><input type="text" class="form-control" name="rate" id="rate" value=""></td>
		                            		<td class="text-center"></td>
		                            	</tr>
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
<script type="text/javascript"><!--
// 삭제
function goDelete(tax_rate_id) {
	if(confirm('삭제하시겠습니까?')) {
		var action = '/settings/tax/rates/delete/'+tax_rate_id+'.dr';
		$("#form").attr("action", action);
		$('#form').submit();
	}
}
//--></script> 
<%@include file="/WEB-INF/inc/bottom.jspf" %>
