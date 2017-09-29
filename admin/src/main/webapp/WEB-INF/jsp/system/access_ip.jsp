<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> Access IPs</h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	        	<!-- BEGIN FORM-->
	            <form action="/settings/access/ip/add.dr" id="form" class="horizontal-form">
	            <div class="portlet box blue">
	                <div class="portlet-title">
	                    &nbsp;
	                </div>
	                <div class="portlet-body form">
	                    
                        <div class="form-body">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="control-label">IP <span class="required"> * </span></label>
                                        <input type="text" id="ip" name="ip" class="form-control" placeholder="" value="${ip}">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="control-label">접속하는 곳 <span class="required"> * </span></label>
                                        <input type="text" id="memo" name="memo" class="form-control" placeholder="" value="">
                                    </div>
                                </div>
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
	            
	            <div class="portlet light portlet-fit portlet-datatable bordered">
	                <div class="portlet-body">
	                    <div class="table-container dataTables_wrapper no-footer">
	                    	<div class="table-responsive">
			                    <table class="table table-striped table-bordered table-hover table-checkable" id="datatable_orders">
		                            <thead>
		                                <tr role="row" class="heading">
		                                    <th width="15%"> IP </th>
		                                    <th> 접속하는 곳 </th>
		                                    <th> 등록일자 </th>
		                                    <th> </th>
		                                </tr>
		                            </thead>
		                            <tbody>
		                            <c:forEach items="${list}" var="item">
		                            	<tr role="row">
		                            		<td>${item.ip}</td>
		                            		<td>${item.memo}</td>
		                            		<td>${item.date_added}</td>
		                            		<td class="text-center">
												<a href="javascript:;" data-toggle="tooltip" title="삭제" class="btn btn-sm btn-outline red" onclick="goDelete('${item.no}', '${item.ip}')"><i class="fa fa-trash"></i> 삭제</a>
											</td>
		                            	</tr>
		                            </c:forEach>
		                            </tbody>
		                        </table>
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
        url: '/settings/access/ip/add.dr',
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
            	var url = '/settings/access/ip.dr';
            	setTimeout(goRefresh(url), 5000);
            }
            if(json.Error) {
            	if (json.Error.ip) {
            		$('input[name=\'ip\']').parent().addClass('has-error');
                    $('input[name=\'ip\']').after('<span class="help-block"> ' + json.Error.ip + '</span>');
                }
                if (json.Error.memo) {
                	$('input[name=\'memo\']').parent().addClass('has-error');
                    $('input[name=\'memo\']').after('<span class="help-block"> ' + json.Error.memo + '</span>');
                }
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

function goRefresh(url){
	location = url;
}

// 삭제
function goDelete(no,ip) {
	if(confirm('삭제하시겠습니까?')) {
		var action = '/settings/access/ip/delete/'+no+'/'+ip+'.dr';
		$("#form").attr("action", action);
		$('#form').submit();
	}
}
//--></script> 
<%@include file="/WEB-INF/inc/bottom.jspf" %>
