<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> 제품 수정 <small>edit product</small></h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	            <!-- Begin: life time stats -->
	            <form action="/product/save/discount.dr" id="form" class="form-horizontal form-row-seperated">
		            <div class="portlet light portlet-fit portlet-datatable bordered">
		                <div class="portlet-title">
		                    <div class="caption">
		                        <i class="icon-settings font-dark"></i>
		                        <span class="caption-subject font-dark sbold"> 제품명: ${product.name}&nbsp;&nbsp;/&nbsp;&nbsp;제품번호: ${product.product_id}</span>
		                    </div>
		                    <div class="actions btn-set">
		                    	<button type="button" class="btn dark" id="button_back" onclick="javascript:location='/product/back.dr'"><i class="fa fa-reply"></i> BACK</button>
	                    		<button type="button" id="button-submit" class="btn btn-success"><i class="fa fa-check"></i> Save</button>
			                </div>
		                </div>
	                    <div class="portlet-body">
	                        <div class="tabbable-bordered">
	                            <ul class="nav nav-tabs">
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/product/view/meta/${product_id}/${language_id}.dr'" data-toggle="tab"> META 정보 </a>
	                                </li>
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/product/view/info/${product_id}/${language_id}.dr'" data-toggle="tab"> 제품정보 </a>
	                                </li>
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/product/view/image/${product_id}/${language_id}.dr'" data-toggle="tab"> 이미지 </a>
	                                </li>
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/product/view/category/${product_id}/${language_id}.dr'" data-toggle="tab"> 관련 카테고리 </a>
	                                </li>
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/product/view/disease/${product_id}/${language_id}.dr'" data-toggle="tab"> 추천 생약제 </a>
	                                </li>
	                                <li class="active">
	                                    <a href="javascript:;" onclick="javascript:location='/product/view/discount/${product_id}/${language_id}.dr'" data-toggle="tab"> 할인가격 </a>
	                                </li>
	                            </ul>
	                            <div class="tab-content">
	                                <div class="tab-pane active" id="tab_general">
	                                	<div class="row">
	                        				<div class="col-md-12">
			                                    <div class="portlet ">

				                                    <div class="portlet-body">
				                                        <div class="table-container">
				                                            <table class="table table-striped table-bordered table-hover table-checkable" id="datatable_products">
				                                                <thead>
				                                                    <tr role="row" class="heading">
				                                                        <th width="20%"> 고객 그룹 </th>
				                                                        <th width=""> 할인 가격 </th>
				                                                        <th width=""> 시작 일자 </th>
				                                                        <th width=""> 종료일자 </th>
				                                                        <th width="10%"> </th>
				                                                    </tr>
				                                                    <tr role="row" class="filter">
				                                                        <td>
				                                                        	<select class="form-control" id="customer_group_id" name="customer_group_id">
									                                        	<option value="0">전체 고객</option>
									                                        	<c:forEach items="${customerGroupsList}" var="item">
									                                        	<option value="${item.customer_group_id}">${item.name}</option>
									                                        	</c:forEach>
									                                        </select>    
				                                                        </td>
				                                                        <td>
				                                                            <input type="text" class="form-control form-filter input-sm" name="price" id="price"> </td>
				                                                        <td>
				                                                        	<div class="input-group date date-picker" data-provide="datepicker" data-date-format="yyyy-mm-dd" data-date="${today}">
									                                            <input type="text" class="form-control form-filter" name="date_start" id="date_start">
									                                            <span class="input-group-btn">
									                                                <button class="btn default" type="button">
									                                                    <i class="fa fa-calendar"></i>
									                                                </button>
									                                            </span>
									                                        </div>
				                                                        </td>
				                                                        <td>
				                                                        	<div class="input-group date date-picker" data-provide="datepicker" data-date-format="yyyy-mm-dd" data-date="${today}">
									                                            <input type="text" class="form-control form-filter" name="date_end" id="date_end">
									                                            <span class="input-group-btn">
									                                                <button class="btn default" type="button">
									                                                    <i class="fa fa-calendar"></i>
									                                                </button>
									                                            </span>
									                                        </div>
				                                                        </td>
				                                                        <td>
				                                                            <button class="btn btn-sm btn-default filter-cancel">
				                                                                <i class="fa fa-trash"></i> 삭제</button>
				                                                        </td>
				                                                    </tr>
				                                                </thead>
				                                                <tbody>
									                            <c:forEach items="${list}" var="item">
									                            	<tr role="row">
									                            		<td>
								                            			<c:choose>
								                            			<c:when test="${item.customer_group_id == '0'}">
								                            				전체 고객
								                            			</c:when>
								                            			<c:otherwise>
								                            				${item.customer_group_name}
								                            			</c:otherwise>
								                            			</c:choose>
									                            		</td>
									                            		<td class="text-align-right">${ctag:getCurrency(item.price)}</td>
									                            		<td>${item.date_start}</td>
									                            		<td>${item.date_end}</td>
									                            		<td><a href="javascript:;" onclick="goDelete(${item.product_special_id})" class="btn btn-sm btn-outline red"><i class="fa fa-trash"></i> 삭제</a></td>
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
	                                
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                <input type="hidden" name="product_id" id="product_id" value="${product_id}"/>
	                <input type="hidden" name="language_id" id="language_id" value="${language_id}"/>
	                <input type="hidden" name="product_special_id" id="product_special_id" value=""/>
	            </form>
	        </div>
	    </div>
   
   </div>
	<!-- END CONTENT BODY -->
</div>
<!-- END CONTENT -->
<%@include file="/WEB-INF/inc/footer.jspf" %>
<script type="text/javascript"><!--
// 저장
$('#button-submit').on('click', function() {
	var action = '/product/save/discount.dr';
	$('#form').attr('action', action);
	$('#form').submit();
});

// 삭제
function goDelete(product_special_id) {
	if(!confirm("삭제하시겠습니까?")) return false;
	
	var action = '/product/delete/discount.dr';
	$('#product_special_id').val(product_special_id);
	$("#form").attr("action", action);
	$('#form').submit();
}

// Back
$('#button_back').on('click', function() {
	var action = '/product/back.dr';
	$("#form").attr("action", action);
	$('#form').submit();
});
//--></script>
<%@include file="/WEB-INF/inc/bottom.jspf" %>
