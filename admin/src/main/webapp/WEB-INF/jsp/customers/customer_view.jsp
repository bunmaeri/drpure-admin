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
	            <form class="form-horizontal form-row-seperated" id="form" action="/customer/save/info.dr" method="post">
	                <div class="portlet">
	                    <div class="portlet-title">
	                        <div class="caption">
	                            <i class="icon-user"></i> ${info.customer_name} (#${info.customer_id})</div>
	                        <div class="actions btn-set">
	                            <button type="button" name="back" class="btn btn-secondary-outline" onclick="javascript:location='/customer/back.dr'"><i class="fa fa-angle-left"></i> Back</button>
	                            <button type="button" id="button-submit" class="btn btn-success"><i class="fa fa-check"></i> Save</button>
	                        </div>
	                    </div>
	                    <div class="portlet-body">
	                        <div class="tabbable-bordered">
	                            <ul class="nav nav-tabs">
	                                <li class="active">
	                                    <a href="#tab_general" data-toggle="tab"> 기본정보 </a>
	                                </li>
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/customer/view/orderhistory/${info.customer_id}.dr'" data-toggle="tab"> 주문이력 </a>
	                                </li>
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/customer/view/reward/${info.customer_id}.dr'" data-toggle="tab"> 적립포인트 </a>
	                                </li>
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/customer/view/loginhistory/${info.customer_id}.dr'" data-toggle="tab"> Login History </a>
	                                </li>
	                            </ul>
	                            <div class="tab-content">
	                                <div class="tab-pane active" id="tab_general">
	                                	<div class="row">
	                        				<div class="col-md-3">
	                        					<ul class="ver-inline-menu tabbable margin-bottom-10">
					                                <li class="active">
					                                    <a data-toggle="tab" href="javascript:;" onclick="javascript:location='/customer/view/${info.customer_id}.dr'">
					                                        <i class="fa fa-cog"></i> 기본정보 </a>
					                                    <span class="after"> </span>
					                                </li>
					                                <c:forEach items="${addressList}" var="address" varStatus="index">
					                                <li>
					                                    <a data-toggle="tab" href="javascript:;" onclick="javascript:location='/customer/view/address/${info.customer_id}/${address.address_id}.dr'">
					                                        <i class="icon-notebook"></i> 
					                                        <c:choose>
					                                        <c:when test="${address.address_alias!=''}">
					                                        	주소 ${index.count} (${address.address_alias})
					                                        </c:when>
					                                        <c:otherwise>
					                                        	주소 ${index.count} (${address.customer_name})
					                                        </c:otherwise>
					                                        </c:choose>
					                                    </a>
					                                </li>
					                                </c:forEach>
					                                <li>
					                                    <a data-toggle="tab" href="javascript:;" onclick="javascript:location='/customer/view/address/${info.customer_id}/0.dr'">
					                                        <i class="fa fa-plus-square"></i> 주소추가 </a>
					                                </li>
					                            </ul>
					                        </div>
					                        <div class="col-md-9">
			                                    <div class="form-body">
			                                    	<div class="form-group">
		                                                <label class="col-md-3 control-label">My Home Doc 고객</label>
		                                                <div class="col-md-9">
		                                                    <select class="table-group-action-input form-control input-large" name="myhomedoc" id="myhomedoc">
		                                                        <option value="1" <c:if test="${info.myhomedoc=='1'}">selected</c:if>>예</option>
		                                                        <option value="0" <c:if test="${info.myhomedoc=='0'}">selected</c:if>>아니오</option>
		                                                    </select>
		                                                </div>
		                                            </div>
			                                        <div class="form-group">
			                                            <label class="col-md-3 control-label">이름 (First Name)
			                                            	<span class="required"> * </span>
			                                            </label>
			                                            <div class="col-md-9">
			                                            	<input type="text" name="firstname" id="firstname" class="form-control input-large" value="${info.firstname}"/>
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-3 control-label">성 (Last Name)</label>
			                                            <div class="col-md-9">
			                                            	<input type="text" name="lastname" id="lastname" class="form-control input-large" value="${info.lastname}"/>
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
		                                                <label class="col-md-3 control-label">고객 그룹
		                                                    <span class="required"> * </span>
		                                                </label>
		                                                <div class="col-md-9">
		                                                    <select class="table-group-action-input form-control input-large" name="customer_group_id" id="customer_group_id">
		                                                    	<c:forEach items="${customerGroupsList}" var="group">
		                                                        <option value="${group.customer_group_id}" <c:if test="${group.customer_group_id==info.customer_group_id}">selected</c:if>>${group.name}</option>
		                                                        </c:forEach>
		                                                    </select>
		                                                </div>
		                                            </div>
		                                            <div class="form-group">
			                                            <label class="col-md-3 control-label">이메일
			                                            	<span class="required"> * </span>
			                                            </label>
			                                            <div class="col-md-9">
			                                            	<input type="text" name="email" id="email" class="form-control input-large" value="${info.email}"/>
			                                            	<input type="hidden" name="last_email" id="last_email" value="${info.email}"/>
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-3 control-label">전화번호
			                                            	<span class="required"> * </span>
			                                            </label>
			                                            <div class="col-md-9">
			                                            	<input type="text" name="telephone" id="telephone" class="form-control input-large" value="${info.telephone}"/>
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-3 control-label">가입경로</label>
			                                            <div class="col-md-9">
			                                            	<input type="text" name="join_path_name" id="join_path_name" class="form-control input-large" value="${info.join_path_name} <c:if test='${info.join_path_etc ne null}'>${info.join_path_etc}</c:if>" readonly/>
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-3 control-label">비밀번호</label>
			                                            <div class="col-md-9">
			                                            	<input type="password" name="password" id="password" class="form-control input-large"/>
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-3 control-label">마지막 비밀번호</label>
			                                            <div class="col-md-9">
			                                            	<input type="text" name="last_password" id="last_password" class="form-control input-large" value="${info.last_password}&nbsp;&nbsp;[${info.login_date}]" readonly/>
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
		                                                <label class="col-md-3 control-label">Newsletter</label>
		                                                <div class="col-md-9">
		                                                    <select class="table-group-action-input form-control input-large" name="newsletter" id="newsletter">
		                                                        <option value="1" <c:if test="${info.newsletter=='1'}">selected</c:if>>활성</option>
		                                                        <option value="0" <c:if test="${info.newsletter=='0'}">selected</c:if>>비활성</option>
		                                                    </select>
		                                                </div>
		                                            </div>
		                                            <div class="form-group">
		                                                <label class="col-md-3 control-label">불량고객 or 정상고객<br/>(마이홈닥 고객 안되도록)</label>
		                                                <div class="col-md-9">
		                                                    <select class="table-group-action-input form-control input-large" name="safe" id="safe">
		                                                        <option value="1" <c:if test="${info.safe=='1'}">selected</c:if>>불량고객</option>
		                                                        <option value="0" <c:if test="${info.safe=='0'}">selected</c:if>>정상고객</option>
		                                                    </select>
		                                                </div>
		                                            </div>
			                                        <div class="form-group">
		                                                <label class="col-md-3 control-label">고객 상태
		                                                    <span class="required"> * </span>
		                                                </label>
		                                                <div class="col-md-9">
		                                                    <select class="table-group-action-input form-control input-large" name="status" id="status">
		                                                        <option value="1" <c:if test="${info.status=='1'}">selected</c:if>>활성</option>
		                                                        <option value="0" <c:if test="${info.status=='0'}">selected</c:if>>비활성</option>
		                                                    </select>
		                                                </div>
		                                            </div>
		                                            <div class="form-group">
									                    <label class="col-md-3 control-label">기본 결제 주소</label>
									                    <div class="col-md-9">
										                    <select class="form-control" id="address_id" name="address_id">
										                        <option value=""></option>
																<c:forEach items="${addressList}" var="address">
									    	                    <option value="${address.address_id}" <c:if test="${address.address_id==info.address_id}">selected</c:if>>[${address.customer_name}] ${address.address_1} ${address.address_2}, ${address.zone_name}, ${address.country_name}&nbsp;${address.postcode}</option>
																</c:forEach>
											                </select>
										                </div>
									                </div>
									                <div class="form-group">
									                    <label class="col-md-3 control-label">기본 배송 주소</label>
									                    <div class="col-md-9">
										                    <select class="form-control" id="shipping_address_id" name="shipping_address_id">
										                        <option value=""></option>
																<c:forEach items="${addressList}" var="address">
									    	                    <option value="${address.address_id}" <c:if test="${address.address_id==info.shipping_address_id}">selected</c:if>>[${address.customer_name}] ${address.address_1} ${address.address_2}, ${address.zone_name}, ${address.country_name}&nbsp;${address.postcode}</option>
																</c:forEach>
											                </select>
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
	                <input type="hidden" name="customer_id" id="customer_id" value="${info.customer_id}"/>
	            </form>
	        </div>
	    </div>
	</div>
</div>

<%@include file="/WEB-INF/inc/footer.jspf" %>
<script type="text/javascript"><!--
$('#button-submit').on('click', function() {
    $.ajax({
        url: '/customer/save/info.dr',
        type: 'post',
        data: $('#form input, #form select'),
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
            }
            if(json.error) {
                if (json.Message.error_firstname) {
                	$('input[name=\'firstname\']').parent().addClass('has-error');
                    $('input[name=\'firstname\']').after('<span class="help-block"> ' + json.Message.error_firstname + '</span>');
                }
                if (json.Message.error_lastname) {
                	$('input[name=\'lastname\']').parent().addClass('has-error');
                    $('input[name=\'lastname\']').after('<span class="help-block"> ' + json.Message.error_lastname + '</span>');
                }
                if (json.Message.error_email) {
                	$('input[name=\'email\']').parent().addClass('has-error');
                    $('input[name="email"]').after('<span class="help-block"> ' + json.Message.error_email + '</span>');
                }
                if (json.Message.error_telephone) {
                	$('input[name=\'telephone\']').parent().addClass('has-error');
                    $('input[name=\'telephone\']').after('<span class="help-block"> ' + json.Message.error_telephone + '</span>');
                }
            }
        },
        error: function(xhr, ajaxOptions, thrownError) {
            alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
        }
    });
});
//--></script> 
<%@include file="/WEB-INF/inc/bottom.jspf" %>
