<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> Access Password</h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	        	<!-- BEGIN FORM-->
	            <form action="/settings/access/password/save.dr" id="form" class="horizontal-form">
	            <div class="portlet box blue">
	                <div class="portlet-title">
	                    &nbsp;
	                </div>
	                <div class="portlet-body form">
	                    
                        <div class="form-body">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="control-label">Password <span class="required"> * </span></label>
                                        <input type="password" id="input_password" name="input_password" class="form-control" placeholder="" value="">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
	                        <div class="form-actions right" style="padding: 20px;">
	                        	<button type="button" id="button-submit" class="btn btn-success"><i class="fa fa-check"></i> Save</button>
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
$('#button-submit').on('click', function() {
    $.ajax({
        url: '/settings/access/password/save.dr',
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
            $('.help-block').remove();
        
            if (json.Success) {
            	$('.page-title').after('<div class="custom-alerts alert alert-success fade in"><button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button><i class="fa-lg fa fa-check"></i> ' + json.Success + ' </div>');
            }
            if(json.Error) {
                if (json.Error.input_password) {
                	$('input[name=\'input_password\']').parent().addClass('has-error');
                    $('input[name=\'input_password\']').after('<span class="help-block"> ' + json.Error.input_password + '</span>');
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
