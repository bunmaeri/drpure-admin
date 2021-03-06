<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/froala_css.jspf" %>

<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> 템플릿 관리</h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	            <!-- Begin: life time stats -->
	            <form action="/marketing/templates/save.dr" id="form" class="form-horizontal form-row-seperated">
	            	<div class="portlet light portlet-fit portlet-datatable bordered">
		                <div class="portlet-title">
		                    <div class="caption">
		                        <i class="icon-settings font-dark"></i>
		                        <span class="caption-subject font-dark sbold uppercase"> 제목: ${info.alias}</span>
		                    </div>
		                    <div class="actions">
	                    		<button type="button" class="btn dark" id="button_back" onclick="javascript:location='/marketing/templates.dr'"><i class="fa fa-reply"></i> BACK</button>
			                	<button type="button" id="button-submit" class="btn btn-success"><i class="fa fa-check"></i> Save</button>
			                </div>
		                </div>
	                    <div class="portlet-body">
	                        <div class="tabbable-bordered">
	                            <ul class="nav nav-tabs">
	                                <li class="active">
	                                    <a href="javascript:;" data-toggle="tab"> Edit </a>
	                                </li>
	                            </ul>
	                            <div class="tab-content">
	                                <div class="tab-pane active">
	                                	<div class="row">
	                        				<div class="col-md-12">
			                                    <div class="form-body">
			                                    	<div class="form-group">
			                                            <label class="control-label">템플릿 명
			                                            </label>
			                                            <input type="text" class="form-control" name="alias" id="alias" value="${info.alias}">
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="control-label">제목<span class="required"> * </span>
			                                            </label>
			                                            <input type="text" class="form-control" name="title" id="title" value="${info.title}">
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="control-label">내용</label>
			                                            <textarea name="description" id="description" class="form-control summernote">${info.description}</textarea>
			                                        </div>
			                                        <c:if test="${info.date_added!=null}">
			                                        <div class="form-group">
			                                            <label class="control-label">만든 날짜</label>
			                                            ${info.date_added}
			                                        </div>
			                                        </c:if>
			                                        <c:if test="${info.date_modified!=null}">
			                                        <div class="form-group">
			                                            <label class="control-label">마지막 변경날짜</label>
			                                            ${info.date_modified}
			                                        </div>
			                                        </c:if>
			                                        <c:if test="${info.send_date!=null}">
			                                        <div class="form-group">
			                                            <label class="control-label">마지막 이메일 발송일자</label>
			                                            ${info.send_date}
			                                        </div>
			                                        </c:if>
			                                        <c:if test="${info.send_count!=null}">
													<div class="form-group class-sort-order">
														<label class="control-label" for="sort_order">이메일 발송 횟수</label>
														${info.send_count}
													</div>
													</c:if>
			                                    </div>
			                                </div>
			                            </div>
	                                </div>
	                                
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                <input type="hidden" name="template_id" id="template_id" value="${template_id}">
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
$('#button-submit').on('click', function() {
    $.ajax({
        url: '/marketing/templates/save.dr',
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
            	if (json.Error.title) {
            		$('input[name=\'title\']').parent().addClass('has-error');
                    $('input[name=\'title\']').after('<span class="help-block"> ' + json.Error.title + '</span>');
                }
                if (json.Error.description) {
                	$('input[name=\'description\']').parent().addClass('has-error');
                    $('input[name=\'description\']').after('<span class="help-block"> ' + json.Error.description + '</span>');
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
