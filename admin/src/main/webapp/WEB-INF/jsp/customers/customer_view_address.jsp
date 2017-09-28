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
	            <form class="form-horizontal form-row-seperated" id="form" action="/customer/save/address.dr" method="post">
	                <div class="portlet">
	                    <div class="portlet-title">
	                        <div class="caption">
	                            <i class="icon-user"></i> ${info.customer_name} (#${info.customer_id})</div>
	                        <div class="actions btn-set">
	                            <button type="button" name="back" class="btn btn-secondary-outline" onclick="javascript:location='/customer/back.dr'"><i class="fa fa-angle-left"></i> Back</button>
	                            <button type="button" id="button-delete" class="btn red"><i class="fa fa-trash"></i> Delete</button>
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
					                                <li>
					                                    <a data-toggle="tab" href="javascript:;" onclick="javascript:location='/customer/view/${info.customer_id}.dr'">
					                                        <i class="fa fa-cog"></i> 기본정보 </a>
					                                    <span class="after"> </span>
					                                </li>
					                                <c:forEach items="${addressList}" var="address" varStatus="index">
					                                <li <c:if test="${address.address_id==address_id}"> class="active"</c:if>>
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
					                                <li <c:if test="${address_id=='0'}"> class="active"</c:if>>
					                                    <a data-toggle="tab" href="javascript:;" onclick="javascript:location='/customer/view/address/${info.customer_id}/0.dr'">
					                                        <i class="fa fa-plus-square"></i> 주소추가 </a>
					                                </li>
					                            </ul>
					                        </div>
					                        <div class="col-md-9">
			                                    <div class="form-body">
			                                    	<div class="form-group">
			                                            <label class="col-md-2 control-label">주소별명 (Alias)</label>
			                                            <div class="col-md-10">
			                                            	<input type="text" name="address_alias" id="address_alias" class="form-control input-large" value="${address.address_alias}"/>
			                                            </div>
			                                        </div>
			                                    	<div class="form-group">
			                                            <label class="col-md-2 control-label">이름 (First Name)
			                                            	<span class="required"> * </span>
			                                            </label>
			                                            <div class="col-md-10">
			                                            	<input type="text" name="firstname" id="firstname" class="form-control input-large" value="${address.firstname}"/>
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">성 (Last Name)</label>
			                                            <div class="col-md-10">
			                                            	<input type="text" name="lastname" id="lastname" class="form-control input-large" value="${address.lastname}"/>
			                                            </div>
			                                        </div>
													<div class="form-group">
			                                            <label class="col-md-2 control-label">회사 (Company)</label>
			                                            <div class="col-md-10">
			                                            	<input type="text" name="company" id="company" class="form-control input-large" value="${address.company}"/>
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">전화번호</label>
			                                            <div class="col-md-10">
			                                            	<input type="text" name="telephone" id="telephone" class="form-control input-large" value="${address.telephone}"/>
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">개인통관고유부호</label>
			                                            <div class="col-md-10">
			                                            	<input type="text" name="requisition_id" id="requisition_id" class="form-control input-large" value="${address.requisition_id}"/>
			                                            </div>
			                                        </div>
		                                            <div class="form-group">
			                                            <label class="col-md-2 control-label">주소 1 (Address 1)
			                                            	<span class="required"> * </span>
			                                            </label>
			                                            <div class="col-md-10">
			                                            	<input type="text" name="address_1" id="address_1" class="form-control" value="${address.address_1}"/>
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">주소 2 (Address 2)</label>
			                                            <div class="col-md-10">
			                                            	<input type="text" name="address_2" id="address_2" class="form-control" value="${address.address_2}"/>
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">시 (City)</label>
			                                            <div class="col-md-10">
			                                            	<input type="text" name="city" id="city" class="form-control input-large" value="${address.city}"/>
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">우편번호 (Postcode)
			                                            	<span class="required"> * </span>
			                                            </label>
			                                            <div class="col-md-10">
			                                            	<input type="text" name="postcode" id="postcode" class="form-control input-large" value="${address.postcode}"/>
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
									                    <label class="col-md-2 control-label">국가 (Country)
			                                            	<span class="required"> * </span>
			                                            </label>
									                    <div class="col-md-10">
												                <select class="form-control" id="country_id" name="country_id">
										                        <option value="113" <c:if test="${address.country_id=='113'}">selected</c:if>>대한민국</option>
										                        <option value="223" <c:if test="${address.country_id=='223'}">selected</c:if>>미국</option>
										                        <option value="44" <c:if test="${address.country_id=='44'}">selected</c:if>>중국</option>
										                        <option value="107" <c:if test="${address.country_id=='107'}">selected</c:if>>일본</option>
										                    </select>
										                </div>
									                </div>
									                <div class="form-group">
									                    <label class="col-md-2 control-label">지역 (Region / State)</label>
									                    <div class="col-md-10">
										                    <select class="form-control" id="zone_id" name="zone_id">
											                    <option value=""></option>
											                    <c:forEach items="${zoneList}" var="zone">
									    	                    <option value="${zone.zone_id}" <c:if test="${zone.zone_id==address.zone_id}">selected</c:if>>${zone.name}</option>
																</c:forEach>
											                </select>
										                </div>
									                </div>
									                <div class="form-group">
									                    <label class="col-md-2 control-label">기본 결제 주소</label>
									                    <div class="col-md-10">
										                    <div class="mt-radio-list">
	                                                            <label class="mt-radio">
	                                                                <input type="radio" name="radio_default_address" <c:if test="${address.address_id==info.address_id}">checked</c:if> onclick="goRadio('default_address')">
	                                                                <span></span>
	                                                            </label>
	                                                        </div>
										                </div>
									                </div>
									                <div class="form-group">
									                    <label class="col-md-2 control-label">기본 배송 주소</label>
									                    <div class="col-md-10">
										                    <div class="mt-radio-list">
	                                                            <label class="mt-radio">
	                                                                <input type="radio" name="radio_default_shipping_address" <c:if test="${address.address_id==info.shipping_address_id}">checked</c:if> onclick="goRadio('default_shipping_address')">
	                                                                <span></span>
	                                                            </label>
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
	                </div>
	                <input type="hidden" name="customer_id" id="customer_id" value="${info.customer_id}"/>
	                <input type="hidden" name="address_id" id="address_id" value="${address.address_id}"/>
	                <input type="hidden" name="default_address" id="default_address" value="<c:if test='${address.address_id==info.address_id}'>1</c:if>"/>
	                <input type="hidden" name="default_shipping_address" id="default_shipping_address" value="<c:if test='${address.address_id==info.shipping_address_id}'>1</c:if>"/>
	            </form>
	        </div>
	    </div>
	</div>
</div>

<%@include file="/WEB-INF/inc/footer.jspf" %>
<script type="text/javascript"><!--
// 기본결제주소 선택
function goRadio(item) {
	if($('input:radio[name=radio_'+item+']').is(':checked')) {
		$('#'+item).val('1');
	}
}

// 국가가 변경될 때
$('select[name=\'country_id\']').on('change', function() {
	$.ajax({
		url: '/sales/order/change/country/' + this.value+'.dr',
		dataType: 'json',
		beforeSend: function() {
			$('select[name=\'country_id\']').after(' <i class="fa fa-circle-o-notch fa-spin"></i>');
		},
		complete: function() {
			$('.fa-spin').remove();
		},
		success: function(json) {
			$('select[name=\'zone_id\']').html(json.zone);
		},
		error: function(xhr, ajaxOptions, thrownError) {
			alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
		}
	});
});

// 저장
$('#button-submit').on('click', function() {
    $.ajax({
        url: '/customer/save/address.dr',
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
                setTimeout("location.reload()", 3000); // 3000ms(2초)
            }
            if(json.error) {
            	if (json.Message.error_address_alias) {
                	$('input[name=\'address_alias\']').parent().addClass('has-error');
                    $('input[name=\'address_alias\']').after('<span class="help-block"> ' + json.Message.error_address_alias + '</span>');
                }
                if (json.Message.error_firstname) {
                	$('input[name=\'firstname\']').parent().addClass('has-error');
                    $('input[name=\'firstname\']').after('<span class="help-block"> ' + json.Message.error_firstname + '</span>');
                }
                if (json.Message.error_lastname) {
                	$('input[name=\'lastname\']').parent().addClass('has-error');
                    $('input[name=\'lastname\']').after('<span class="help-block"> ' + json.Message.error_lastname + '</span>');
                }
                if (json.Message.error_company) {
                	$('input[name=\'company\']').parent().addClass('has-error');
                    $('input[name="company"]').after('<span class="help-block"> ' + json.Message.error_company + '</span>');
                }
                if (json.Message.error_telephone) {
                	$('input[name=\'telephone\']').parent().addClass('has-error');
                    $('input[name=\'telephone\']').after('<span class="help-block"> ' + json.Message.error_telephone + '</span>');
                }
                if (json.Message.error_requisition_id) {
                	$('input[name=\'requisition_id\']').parent().addClass('has-error');
                    $('input[name=\'requisition_id\']').after('<span class="help-block"> ' + json.Message.error_requisition_id + '</span>');
                }
                if (json.Message.error_address_1) {
                	$('input[name=\'address_1\']').parent().addClass('has-error');
                    $('input[name=\'address_1\']').after('<span class="help-block"> ' + json.Message.error_address_1 + '</span>');
                }
                if (json.Message.error_address_2) {
                	$('input[name=\'address_2\']').parent().addClass('has-error');
                    $('input[name=\'address_2\']').after('<span class="help-block"> ' + json.Message.error_address_2 + '</span>');
                }
                if (json.Message.error_city) {
                	$('input[name=\'city\']').parent().addClass('has-error');
                    $('input[name=\'city\']').after('<span class="help-block"> ' + json.Message.error_city + '</span>');
                }
                if (json.Message.error_postcode) {
                	$('input[name=\'postcode\']').parent().addClass('has-error');
                    $('input[name=\'postcode\']').after('<span class="help-block"> ' + json.Message.error_postcode + '</span>');
                }
            }
        },
        error: function(xhr, ajaxOptions, thrownError) {
            alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
        }
    });
});

// 주소 삭제
$('#button-delete').on('click', function() {
	var default_address = $('#default_address').val();
	var default_shipping_address = $('#default_shipping_address').val();
	if(default_address=="1" || default_shipping_address=="1") {
		alert("기본주소로 설정되어있습니다.\n먼저 다른 주소로 기본주소를 변경하신 후에 삭제를 진행하십시요.");
		return false;
	}
	var address_id = $('#address_id').val();
	if(address_id=='' || address_id=='0') {
		return false;
	} else {
	if(!confirm("주소를 삭제하시겠습니까?")) return false;
		$.ajax({
	        url: '/customer/delete/address/'+address_id+'.dr',
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
});
//--></script> 
<%@include file="/WEB-INF/inc/bottom.jspf" %>
