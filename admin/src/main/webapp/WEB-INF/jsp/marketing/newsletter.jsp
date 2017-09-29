<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/froala_css.jspf" %>

<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> 이메일 보내기</h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	            <!-- Begin: life time stats -->
	            <form action="/marketing/templates/send.dr" id="form" class="form-horizontal form-row-seperated">
	            	<div class="portlet light portlet-fit portlet-datatable bordered">
		                <div class="portlet-title">
		                    <div class="actions">
	                    		<button type="button" id="button_submit" class="btn btn-success"><i class="fa fa-check"></i> 이메일 발송</button>
			                </div>
		                </div>
	                    <div class="portlet-body">

                           	<div class="row">
                           		<div class="col-md-1"></div>
                   				<div class="col-md-11">
	                                <div class="form-body">
	                                    <div class="form-group class-status">
											<label class="control-label" for="status">이메일 대상 고객</label>
											<select name="customer_group_id" id="customer_group_id" class="form-control input-large">
											    <option value="">전체 고객</option>
											  <c:forEach items="${groups}" var="group">
												<option value="${group.customer_group_id}">${group.name}(${ctag:getNumber(group.total)}명)</option>
											  </c:forEach>
											    <option value="custom">직접 입력</option>
											</select>
										</div>
										<div class="form-group custom_email" style="display:none;">
	                                         <label class="control-label">이메일 직접 입력 <span class="required"> * ( <font style="color:red">; 으로 구분하세요</font>)</span></label>
	                                         <input type="text" class="form-control" name="custom_email" id="custom_email" value="">
	                                     </div>
	                                 	<div class="form-group class-status">
											<label class="control-label" for="status">템플릿</label>
											<select name="template_id" id="template_id" class="form-control input-large">
											    <option value="">템플릿 사용 안함</option>
											<c:forEach items="${list}" var="item">
												<option value="${item.template_id}">${item.alias}</option>
											</c:forEach>
											</select>
										</div>
	                                 	<div class="form-group">
	                                         <label class="control-label">이메일 제목<span class="required"> * </span></label>
	                                         <input type="text" class="form-control" name="title" id="title" value="">
	                                     </div>
	                                     <div class="form-group">
	                                         <label class="control-label">이메일 내용<span class="required"> * </span></label>
	                                         <textarea name="description" id="description" class="form-control summernote"></textarea>
	                                     </div>
	                                     
	                                 </div>
	                             </div>
	                         </div>
							<input type="hidden" name="contents" id="contents">
	                    </div>
	                </div>
	            </form>
	        </div>
	    </div>
   
   </div>
	<!-- END CONTENT BODY -->
</div>
<!-- END CONTENT -->
<%@include file="/WEB-INF/inc/footer.jspf" %>
<%@include file="/WEB-INF/inc/froala_js.jspf" %>
<script type="text/javascript"><!--
$('#customer_group_id').on('change', function() {
    var customer_group = $('#customer_group_id').val();
    //alert(customer_group);
    if(customer_group=='custom') {
    	$('.custom_email').show();
    } else {
    	$('.custom_email').hide();
    }
});

$('#template_id').on('change', function() {
    $.ajax({
        url: '/marketing/newsletter/template.dr',
        type: 'post',
        data: $('#form select'),
        dataType: 'json',
        beforeSend: function() {
        },
        complete: function() {
        },
        success: function(json) {
            $('.alert').remove();
            $('.has-error').remove();
            $('.text-danger').remove();
            
            if (json.Success) {
            	$('#title').val(json.title);
            	//alert(json.description);
            	$('#description').froalaEditor('html.set', json.description);
            	//$('#description').val(json.description);
            }
            if(json.Error) {
            	if (json.Error) {
            		$('input[name=\'template_id\']').parent().addClass('has-error');
                    $('input[name=\'template_id\']').after('<span class="help-block"> ' + json.Error + '</span>');
                }
            }
        },
        error: function(xhr, ajaxOptions, thrownError) {
            alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
        }
    });
});

$('#button_submit').on('click', function() {
	$('#contents').val($('#description').froalaEditor('html.get'));
    $.ajax({
        url: '/marketing/newsletter/send.dr',
        type: 'post',
        data: $('#form input, #form textarea, #form select'),
        dataType: 'json',
        beforeSend: function() {
        	$('#button_submit').prop('disabled', true);
        },
        complete: function() {
        	$('#button_submit').prop('disabled', false).button('reset');
        },
        success: function(json) {
            $('.alert').remove();
            $('.has-error').remove();
            $('.text-danger').remove();
            
            if (json.Success) {
            	$('.page-title').after('<div class="custom-alerts alert alert-success fade in"><button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button><i class="fa-lg fa fa-check"></i> ' + json.Success + ' </div>');
            }
            if(json.Error) {
            	if (json.Error.custom_email) {
            		$('input[name=\'custom_email\']').parent().addClass('has-error');
                    $('input[name=\'custom_email\']').after('<span class="help-block"> ' + json.Error.custom_email + '</span>');
                }
            	if (json.Error.title) {
                	$('input[name=\'title\']').parent().addClass('has-error');
                    $('input[name=\'title\']').after('<span class="help-block"> ' + json.Error.title + '</span>');
                }
                if (json.Error.description) {
                	$('textarea[name=\'description\']').parent().addClass('has-error');
                    $('textarea[name=\'description\']').after('<span class="help-block"> ' + json.Error.description + '</span>');
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
