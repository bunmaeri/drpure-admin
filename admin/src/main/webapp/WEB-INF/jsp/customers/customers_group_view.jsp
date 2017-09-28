<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> 고객 그룹 수정</h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	            <!-- Begin: life time stats -->
	            <form action="/customers_groups/save.dr" id="form" class="form-horizontal form-row-seperated">
		            <div class="portlet light portlet-fit portlet-datatable bordered">
		                <div class="portlet-title">
		                    <div class="actions btn-set">
		                    	<button type="button" class="btn dark" id="button_back"><i class="fa fa-reply"></i> BACK</button>
	                    		<button type="button" id="button-submit" class="btn btn-success"><i class="fa fa-check"></i> Save</button>
			                </div>
		                </div>
	                    <div class="portlet-body">
	                        <div class="tabbable-bordered">
	                            <div class="tab-content">
	                                <div class="tab-pane active" id="tab_general">
	                                	<div class="row">
	                        				<div class="col-md-12">
			                                    <div class="form-body">
			                                    	<div class="form-group">
			                                            <label class="col-md-2 control-label">고객 그룹 명
			                                                <span class="required"> * </span>
			                                            </label>
			                                            <div class="col-md-10">
			                                            	<input type="text" class="form-control input-large" name="name" id="name" value="${info.name}">
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">적립 포인트(%)</label>
			                                            <div class="col-md-10">
			                                            	<input type="text" class="form-control input-large" name="reward" id="reward" value="${info.reward}">
			                                            </div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="col-md-2 control-label">그룹 설명</label>
			                                            <div class="col-md-10">
			                                            	<input type="text" class="form-control" name="description" id="description" value="${info.description}">
			                                            </div>
			                                        </div>
			                                        
			                                    </div>
			                                </div>
			                            </div>
	                                </div>
	                                <input type="hidden" name="customer_group_id" id="customer_group_id" value="${info.customer_group_id}">
	                                <input type="hidden" name="language_id" id="language_id" value="${info.language_id}">
	                            </div>
	                        </div>
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
<script type="text/javascript"><!--
$('#button-submit').on('click', function() {
    $.ajax({
        url: '/customers_groups/save.dr',
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
            if(json.error) {
                if (json.Message.error_name) {
                	$('input[name=\'name\']').parent().addClass('has-error');
                    $('input[name=\'name\']').after('<span class="help-block"> ' + json.Message.error_name + '</span>');
                }
            }
        },
        error: function(xhr, ajaxOptions, thrownError) {
            alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
        }
    });
});

// Back
$('#button_back').on('click', function() {
	var action = '/customers_groups.dr';
	$("#form").attr("action", action);
	$('#form').submit();
});
//--></script>
<%@include file="/WEB-INF/inc/bottom.jspf" %>
