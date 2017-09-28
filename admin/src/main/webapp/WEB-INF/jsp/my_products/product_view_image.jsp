<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> <span style="color:red">[My Home Doc]</span> 제품 수정 <small>edit product</small></h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	            <!-- Begin: life time stats -->
	            <form action="/my_product/save/image.dr" id="form" class="form-horizontal form-row-seperated" method="POST" enctype="multipart/form-data">
		            <div class="portlet light portlet-fit portlet-datatable bordered">
		                <div class="portlet-title">
		                    <div class="caption">
		                        <i class="icon-settings font-dark"></i>
		                        <span class="caption-subject font-dark sbold"> 제품명: ${product.name}&nbsp;&nbsp;/&nbsp;&nbsp;제품번호: ${product.product_id}</span>
		                    </div>
		                    <div class="actions btn-set">
		                    	<button type="button" class="btn dark" id="button_back" onclick="javascript:location='/my_product/back.dr'"><i class="fa fa-reply"></i> BACK</button>
	                    		<button type="submit" id="button-submitss" class="btn btn-success"><i class="fa fa-check"></i> Save</button>
			                </div>
		                </div>
	                    <div class="portlet-body">
	                        <div class="tabbable-bordered">
	                            <ul class="nav nav-tabs">
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/my_product/view/meta/${product_id}/${language_id}.dr'" data-toggle="tab"> META 정보 </a>
	                                </li>
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/my_product/view/info/${product_id}/${language_id}.dr'" data-toggle="tab"> 제품정보 </a>
	                                </li>
	                                <li class="active">
	                                    <a href="javascript:;" onclick="javascript:location='/my_product/view/image/${product_id}/${language_id}.dr'" data-toggle="tab"> 이미지 </a>
	                                </li>
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/my_product/view/category/${product_id}/${language_id}.dr'" data-toggle="tab"> 관련 카테고리 </a>
	                                </li>
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/my_product/view/disease/${product_id}/${language_id}.dr'" data-toggle="tab"> 추천 생약제 </a>
	                                </li>
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/my_product/view/discount/${product_id}/${language_id}.dr'" data-toggle="tab"> 할인가격 </a>
	                                </li>
	                            </ul>
	                            <div class="tab-content">
	                                <div class="tab-pane active" id="tab_general">
	                                	<div class="row">
	                        				<div class="col-md-12">
			                                    <div class="form-body">
			                                    	<div class="form-group last">
	                                                    <label class="control-label col-md-2">제품 이미지</label>
	                                                    <div class="col-md-10">
	                                                        <div class="fileinput fileinput-new" data-provides="fileinput">
	                                                            <div class="fileinput-new thumbnail" style="width:600px; height:450px;">
	                                                                <img src="/image/${info.image}" alt="${info.model}" /> </div>
	                                                            <div class="fileinput-preview fileinput-exists thumbnail" style="max-width:600px; max-height:450px;"> </div>
	                                                            <div>
	                                                                <span class="btn default btn-file">
	                                                                    <span class="fileinput-new"> Select image </span>
	                                                                    <span class="fileinput-exists"> Change </span>
	                                                                    <input type="file" name="file"> </span>
	                                                                <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> Remove </a>
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
	                </div>
	                <input type="hidden" name="product_id" id="product_id" value="${product_id}"/>
	                <input type="hidden" name="language_id" id="language_id" value="${language_id}"/>
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
        url: '/my_product/save/image.dr',
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
            $('.has-error').removeClass();
        
            if (json.success) {
                $('.page-title').after('<div class="custom-alerts alert alert-success fade in"><button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button><i class="fa-lg fa fa-check"></i> ' + json.success + ' </div>');
            }
        },
        error: function(xhr, ajaxOptions, thrownError) {
            alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
        }
    });
});

// Back
$('#button_back').on('click', function() {
	var action = '/my_product/back.dr';
	$("#form").attr("action", action);
	$('#form').submit();
});
//--></script>
<%@include file="/WEB-INF/inc/bottom.jspf" %>
