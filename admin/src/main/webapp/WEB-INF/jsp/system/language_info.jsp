<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> Language</h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	            <!-- Begin: life time stats -->
	            <form action="/settings/language/save.dr" id="form" class="form-horizontal form-row-seperated">
	            	<div class="portlet light portlet-fit portlet-datatable bordered">
		                <div class="portlet-title">
		                    <div class="caption">
		                        <i class="icon-settings font-dark"></i>
		                        <span class="caption-subject font-dark sbold uppercase"> 제목: ${info.name}</span>
		                    </div>
		                    <div class="actions">
	                    		<button type="button" class="btn dark" id="button_back" onclick="javascript:location='/settings/languages.dr'"><i class="fa fa-reply"></i> BACK</button>
			                	<button type="button" id="button-submit" class="btn btn-success"><i class="fa fa-check"></i> Save</button>
			                </div>
		                </div>
	                    <div class="portlet-body">
	                        <div class="tabbable-bordered">
	                            <ul class="nav nav-tabs">
	                                <li class="active">
	                                    <a href="javascript:;" onclick="javascript:location='/settings/language/info/${language_id}.dr'" data-toggle="tab"> Edit </a>
	                                </li>
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/settings/language/image/${language_id}.dr'" data-toggle="tab"> 이미지 </a>
	                                </li>
	                            </ul>
	                            <div class="tab-content">
	                                <div class="tab-pane active" id="language${info.language_id}">
	                                	<div class="row">
	                        				<div class="col-md-12">
			                                    <div class="form-body">
			                                        <div class="form-group">
			                                            <label class="control-label">언어
			                                                <span class="required"> * </span>
			                                            </label>
			                                            <input type="text" class="form-control" name="name" id="name" value="${info.name}">
			                                        </div>
			                                        <div class="form-group class-status">
														<label class="control-label" for="status">Status</label>
														<select name="status" id="status" class="form-control">
															<option value="1" <c:if test="${info.status=='1'}">selected="selected"</c:if>>활성</option>
															<option value="0" <c:if test="${info.status=='0'}">selected="selected"</c:if>>비활성</option>
														</select>
													</div>
													<div class="form-group class-sort-order">
														<label class="control-label" for="sort_order">Sort Order</label>
														<input type="text" name="sort_order" value="${info.sort_order}" placeholder="Sort Order" id="sort_order" class="form-control" />
													</div>

			                                    </div>
			                                </div>
			                            </div>
	                                </div>
	                                
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                <input type="hidden" name="language_id" id="language_id" value="${language_id}">
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
        url: '/settings/language/save.dr',
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
            	if (json.Error.name) {
            		$('input[name=\'name\']').parent().addClass('has-error');
                    $('input[name=\'name\']').after('<span class="help-block"> ' + json.Error.name + '</span>');
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
