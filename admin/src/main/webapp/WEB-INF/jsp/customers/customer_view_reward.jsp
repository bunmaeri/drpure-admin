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
	                                <li class="active">
	                                    <a href="javascript:;" data-toggle="tab"> 적립포인트 </a>
	                                </li>
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/customer/view/loginhistory/${info.customer_id}.dr'" data-toggle="tab"> Login History </a>
	                                </li>
	                            </ul>
	                            <div class="tab-content">
	                                <div class="tab-pane active" id="tab_general">
	                                	<div class="row">
	                        				<div class="col-md-12">
			                                    <div class="form-body">
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">적립내용
			                                            	<span class="required"> * </span>
			                                            </label>
			                                            <div class="col-md-10">
			                                            	<input type="text" name="description" id="description" class="form-control input-large"/>
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">포인트( - 입력 가능 )
			                                            	<span class="required"> * </span>
			                                            </label>
			                                            <div class="col-md-6">
			                                            	<input type="text" name="points" id="points" class="form-control input-large"/>
			                                            </div>
			                                            <div class="col-md-4">
			                                            	<button id="button-submit" class="btn btn-success"><i class="fa fa-check"></i> Save</button>
			                                            </div>
			                                        </div>
			                                    </div>
			                                </div>
			                            </div>
	                                	<div class="portlet light portlet-fit portlet-datatable bordered">
							                <div class="portlet-body">
							                    <div class="table-container dataTables_wrapper no-footer">
							                    	<div id="datatable_orders_wrapper" class="dataTables_wrapper dataTables_extended_wrapper no-footer">
							                    		<div class="row">
							                    			<div class="col-md-6 col-sm-6 pull-left">
							                    				<p>${pageMaker.from} ~ ${pageMaker.to} 표시 중 - <strong>총 ${pageMaker.count}개</strong></p>
							                    		    </div>
							                    		    <div class="col-md-6 col-sm-6 text-align-right">
							                    				<p><strong>적립포인트 합계: ${ctag:getNumber(sum_points)}</strong></p>
							                    		    </div>
							                    		</div>
							                    	</div>
							                    	<div class="table-responsive">
									                    <table class="table table-striped table-bordered table-hover table-checkable" id="datatable_orders">
								                            <thead>
								                                <tr role="row" class="heading">
								                                    <th> 생성일자 </th>
								                                    <th> 주문번호 </th>
								                                    <th> 설명 </th>
								                                    <th> 포인트 </th>
								                                    <th> 삭제 </th>
								                                </tr>
								                            </thead>
								                            <tbody>
								                            <c:forEach items="${list}" var="item">
								                            	<tr role="row">
								                            		<td>${item.create_date}</td>
								                            		<td><c:if test="${item.order_id!='0'}">${item.order_id}</c:if></td>
								                            		<td>${item.description}</td>
								                            		<td>${ctag:getNumber(item.points)}</td>
								                            		<td><button type="button" class="btn btn-secondary-outline red" onclick="goDeleteReward(${item.customer_reward_id})"><i class="fa fa-trash"></i> 삭제</button></td>
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
	var action = '/customer/view/reward/'+$('#customer_id').val()+'.dr';
	$('#page').val(page);
	$("#form").attr("action", action);
	$('#form').submit();
}
//--></script> 
<script type="text/javascript"><!--
$('#button-submit').on('click', function() {
	if(!confirm("적립포인트를 저장하시겠습니까?")) return false;
    $.ajax({
        url: '/customer/save/reward.dr',
        type: 'post',
        data: $('#form input'),
        dataType: 'json',
        beforeSend: function() {
            $('#button-submit').prop('disabled', true);
        },
        complete: function() {
            $('#button-submit').prop('disabled', false).button('reset');
        },
        success: function(json) {
        	$('.custom-alerts').remove();
            $('.has-error').remove();
        
            if (json.success) {
                $('.page-title').after('<div class="custom-alerts alert alert-success fade in"><button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button><i class="fa-lg fa fa-check"></i> ' + json.success + ' </div>');
                setTimeout("location.reload()", 2000); // 2000ms(2초)
            }
            if(json.error) {
                if (json.Message.error_description) {
                	$('input[name=\'description\']').parent().addClass('has-error');
                    $('input[name=\'description\']').after('<span class="help-block"> ' + json.Message.error_description + '</span>');
                }
                if (json.Message.error_points) {
                	$('input[name=\'points\']').parent().addClass('has-error');
                    $('input[name=\'points\']').after('<span class="help-block"> ' + json.Message.error_points + '</span>');
                }
            }
        },
        error: function(xhr, ajaxOptions, thrownError) {
            alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
        }
    });
});

// 적립포인트 삭제
function goDeleteReward(customer_reward_id) {
	if(!confirm("적립포인트를 삭제하시겠습니까?")) return false;
	$.ajax({
        url: '/customer/delete/reward/'+customer_reward_id+'.dr',
        type: 'post',
        dataType: 'json',
        success: function(json) {
        	$('.custom-alerts').remove();
            $('.has-error').remove();
        
            if (json.success) {
                $('.page-title').after('<div class="custom-alerts alert alert-success fade in"><button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button><i class="fa-lg fa fa-check"></i> ' + json.success + ' </div>');
                setTimeout("location.reload()", 3000); // 3000ms(3초)가 경과하면 location.reload() 함수를 실행합니다.
            }
        },
        error: function(xhr, ajaxOptions, thrownError) {
            alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
        }
    });
}
//--></script> 
<%@include file="/WEB-INF/inc/bottom.jspf" %>
