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
	            <form action="/product/save/info.dr" id="form" class="form-horizontal form-row-seperated">
		            <div class="portlet light portlet-fit portlet-datatable bordered">
		                <div class="portlet-title">
		                    <div class="caption">
		                        <i class="icon-settings font-dark"></i>
		                        <span class="caption-subject font-dark sbold"> 제품명: ${product.name}&nbsp;&nbsp;/&nbsp;&nbsp;제품번호: ${product.product_id}</span>
		                    </div>
		                    <div class="actions btn-set">
		                    	<button type="button" class="btn dark" id="button_back" onclick="javascript:location='/product/back.dr'"><i class="fa fa-reply"></i> BACK</button>
	                    		<a href="javascript:;" data-toggle="tooltip" title="삭제" class="btn btn-sm btn-outline red" onclick="goDelete('${product.product_id}')"><i class="fa fa-trash"></i> Delete</a>
		                    	<button type="button" id="button-submit" class="btn btn-success"><i class="fa fa-check"></i> Save</button>
			                </div>
		                </div>
	                    <div class="portlet-body">
	                        <div class="tabbable-bordered">
	                            <ul class="nav nav-tabs">
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/product/view/meta/${product_id}/${language_id}.dr'" data-toggle="tab"> META 정보 </a>
	                                </li>
	                                <li class="active">
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
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/product/view/discount/${product_id}/${language_id}.dr'" data-toggle="tab"> 할인가격 </a>
	                                </li>
	                            </ul>
	                            <div class="tab-content">
	                                <div class="tab-pane active" id="tab_general">
	                                	<div class="row">
	                        				<div class="col-md-12">
			                                    <div class="form-body">
			                                    	<div class="form-group">
			                                            <label class="col-md-2 control-label">% Reward</label>
			                                            <div class="col-md-10">
			                                            	<input type="text" class="form-control input-large" name="reward_group_id" id="reward_group_id" value="${info.reward_group_id}">
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">Model</label>
			                                            <div class="col-md-10">
			                                            	<input type="text" class="form-control input-large" name="model" id="model" value="${info.model}">
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">가격
			                                                <span class="required"> * </span>
			                                            </label>
			                                            <div class="col-md-10">
			                                            	<input type="text" class="form-control input-large" name="price" id="price" value="${info.price}">
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">남은 수량
			                                                <span class="required"> * </span>
			                                            </label>
			                                            <div class="col-md-10">
			                                            	<input type="text" class="form-control input-large" name="quantity" id="quantity" value="${info.quantity}">
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">최대 구매수량
			                                                <span class="required"> * </span>
			                                            </label>
			                                            <div class="col-md-10">
			                                            	<input type="text" class="form-control input-large" name="minimum" id="minimum" value="${info.minimum}">
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">무게(Weight)
			                                                <span class="required"> * </span>
			                                            </label>
			                                            <div class="col-md-10">
			                                            	<input type="text" class="form-control input-large" name="weight" id="weight" value="${info.weight}">
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">세관 통과 상품명</label>
			                                            <div class="col-md-10">
			                                            	<input type="text" class="form-control" name="ean" id="ean" value="${info.ean}">
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">인보이스 강조 단어</label>
			                                            <div class="col-md-4">
			                                            	<input type="text" class="form-control" name="jan" id="jan" value="${info.jan}">
			                                            </div>
			                                            <div class="col-md-6">
			                                            	단어 사이를 ,(comma)로 구분하세요. 대/소문자 정확하게 (예: Powder, 240 g)
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">HS CODE</label>
			                                            <div class="col-md-4">
			                                            	<input type="text" class="form-control input-large" name="mpn" id="mpn" value="${info.mpn}">
			                                            </div>
			                                            <div class="col-md-6">
			                                            	(21: FOOD PREPARATIONS FOR HEALTH / 33: Cosmetic)
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">제조사
			                                                <span class="required"> * </span>
			                                            </label>
			                                            <div class="col-md-10">
				                                            <select class="form-control input-large" id="manufacturer_id" name="manufacturer_id">
					                                        	<c:forEach items="${manufacturerList}" var="item">
					                                        	<option value="${item.manufacturer_id}" <c:if test="${item.manufacturer_id==info.manufacturer_id}">selected</c:if>>${item.name}</option>
					                                        	</c:forEach>
					                                        </select>
					                                    </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">원산지(Country of Origin)
			                                                <span class="required"> * </span>
			                                            </label>
			                                            <div class="col-md-10">
				                                            <select class="form-control input-large" id="location" name="location">
					                                        	<c:forEach items="${locationList}" var="location">
					                                        	<option value="${location.location_id}" <c:if test="${location.location_id==info.location}">selected</c:if>>${location.name}</option>
					                                        	</c:forEach>
					                                        </select>
														</div>
			                                        </div>
			                                        
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">무게 단위(Weight Class)</label>
			                                            <div class="col-md-10">
				                                            <select class="form-control input-large" id="weight_class_id" name="weight_class_id">
					                                            <option value=""></option>
					                                        	<c:forEach items="${weightClassList}" var="weightClass">
					                                        	<option value="${weightClass.weight_class_id}" <c:if test="${weightClass.weight_class_id==info.weight_class_id}">selected</c:if>>${weightClass.title}</option>
					                                        	</c:forEach>
					                                        </select>
					                                    </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">ICEBOX 제품
			                                                <span class="required"> * </span>
			                                            </label>
			                                            <div class="col-md-10">
				                                            <select class="form-control input-large" id="icebox" name="icebox">
					                                            <option value="0" <c:if test="${'0'==info.icebox}">selected</c:if>>해당없음</option>
					                                            <option value="1" <c:if test="${'1'==info.icebox}">selected</c:if>>아이스 박스 필요함</option>
					                                        </select>
					                                    </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">판매 가능일자</label>
			                                        	<div class="col-md-10">
				                                        	<div class="input-group date date-picker input-large" data-provide="datepicker" data-date-format="yyyy-mm-dd" data-date="${info.date_available}">
					                                           <input type="text" class="form-control form-filter" name="date_available" id="date_available" value="${info.date_available}">
					                                           <span class="input-group-btn">
					                                               <button class="btn default" type="button">
					                                                   <i class="fa fa-calendar"></i>
					                                               </button>
					                                           </span>
					                                        </div>
					                                    </div>
				                                    </div>
				                                    <div class="form-group">
			                                            <label class="col-md-2 control-label">Tax Class</label>
			                                            <div class="col-md-10">
				                                            <select class="form-control input-large" id="tax_class_id" name="tax_class_id">
					                                            <option value="9" <c:if test="${'9'==info.tax_class_id}">selected</c:if>>Taxable Goods</option>
					                                            <option value="0" <c:if test="${'0'==info.tax_class_id}">selected</c:if>>해당없음</option>
					                                        </select>
					                                    </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">SKU</label>
			                                            <div class="col-md-10">
			                                            	<input type="text" class="form-control input-large" name="sku" id="sku" value="${info.sku}">
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">UPC</label>
			                                            <div class="col-md-10">
			                                            	<input type="text" class="form-control input-large" name="upc" id="upc" value="${info.upc}">
			                                           	</div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">Status</label>
			                                            <div class="col-md-10">
				                                            <select class="form-control input-large" id="status" name="status">
					                                            <option value="1" <c:if test="${'1'==info.status}">selected</c:if>>활성</option>
					                                            <option value="0" <c:if test="${'0'==info.status}">selected</c:if>>비활성</option>
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
	                <input type="hidden" name="product_id" id="product_id" value="${product_id}"/>
	                <input type="hidden" name="language_id" id="language_id" value="${language_id}"/>
	                <input type="hidden" name="bef_model" id="bef_model" value="${info.model}"/>
	            </form>
	        </div>
	    </div>
   
   </div>
	<!-- END CONTENT BODY -->
</div>
<!-- END CONTENT -->
<%@include file="/WEB-INF/inc/footer.jspf" %>
<script type="text/javascript"><!--
$('#button-submit').on('click', function() {
    $.ajax({
        url: '/product/save/info.dr',
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
            $('.has-error').removeClass();
        
            if (json.success) {
                $('.page-title').after('<div class="custom-alerts alert alert-success fade in"><button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button><i class="fa-lg fa fa-check"></i> ' + json.success + ' </div>');
            }
            if(json.error) {
                if (json.Message.error_reward_group_id) {
                	$('input[name=\'reward_group_id\']').parent().addClass('has-error');
                    $('input[name=\'reward_group_id\']').after('<span class="help-block"> ' + json.Message.error_reward_group_id + '</span>');
                }
                /* if (json.Message.error_model) {
                	$('input[name=\'model\']').parent().addClass('has-error');
                    $('input[name=\'model\']').after('<span class="help-block"> ' + json.Message.error_model + '</span>');
                } */
                if (json.Message.error_price) {
                	$('input[name=\'price\']').parent().addClass('has-error');
                    $('input[name=\'price\']').after('<span class="help-block"> ' + json.Message.error_price + '</span>');
                }
                if (json.Message.error_quantity) {
                	$('input[name=\'quantity\']').parent().addClass('has-error');
                    $('input[name="quantity"]').after('<span class="help-block"> ' + json.Message.error_quantity + '</span>');
                }
                if (json.Message.error_minimum) {
                	$('input[name=\'minimum\']').parent().addClass('has-error');
                    $('input[name=\'minimum\']').after('<span class="help-block"> ' + json.Message.error_minimum + '</span>');
                }
                if (json.Message.error_weight) {
                	$('input[name=\'weight\']').parent().addClass('has-error');
                    $('input[name=\'weight\']').after('<span class="help-block"> ' + json.Message.error_weight + '</span>');
                }
                if (json.Message.error_upc) {
                	$('input[name=\'upc\']').parent().addClass('has-error');
                    $('input[name=\'upc\']').after('<span class="help-block"> ' + json.Message.error_upc + '</span>');
                }
                if (json.Message.error_ean) {
                	$('input[name=\'ean\']').parent().addClass('has-error');
                    $('input[name=\'ean\']').after('<span class="help-block"> ' + json.Message.error_ean + '</span>');
                }
                if (json.Message.error_jan) {
                	$('input[name=\'jan\']').parent().addClass('has-error');
                    $('input[name=\'jan\']').after('<span class="help-block"> ' + json.Message.error_jan + '</span>');
                }
                if (json.Message.error_mpn) {
                	$('input[name=\'mpn\']').parent().addClass('has-error');
                    $('input[name=\'mpn\']').after('<span class="help-block"> ' + json.Message.error_mpn + '</span>');
                }
                if (json.Message.error_date_available) {
                	$('input[name=\'date_available\']').parent().addClass('has-error');
                    $('input[name=\'date_available\']').after('<span class="help-block"> ' + json.Message.error_date_available + '</span>');
                }
            }
        },
        error: function(xhr, ajaxOptions, thrownError) {
            alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
        }
    });
});

//삭제
function goDelete(product_id) {
	if(confirm('삭제하시겠습니까?')) {
		var action = '/product/remove/'+product_id+'.dr';
		$("#form").attr("action", action);
		$('#form').submit();
	}
}

// Back
$('#button_back').on('click', function() {
	var action = '/product/back.dr';
	$("#form").attr("action", action);
	$('#form').submit();
});
//--></script>
<%@include file="/WEB-INF/inc/bottom.jspf" %>
