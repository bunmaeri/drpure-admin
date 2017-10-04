<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> <span style="color:red">[My Home Doc]</span> 메인페이지 공지사항 수정</h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	            <!-- Begin: life time stats -->
	            <form action="/my_contents/notice/save/image.dr" id="form" class="form-horizontal form-row-seperated" method="POST" enctype="multipart/form-data">
		            <div class="portlet light portlet-fit portlet-datatable bordered">
		                <div class="portlet-title">
		                    <div class="caption">
		                        <i class="icon-settings font-dark"></i>
		                        <span class="caption-subject font-dark sbold uppercase"> 제목: ${info.title}</span>
		                    </div>
		                    <div class="actions btn-set">
		                    	<button type="button" class="btn dark" id="button_back" onclick="javascript:location='/my_contents/notice.dr'"><i class="fa fa-reply"></i> BACK</button>
	                    		<button type="submit" id="button-submitss" class="btn btn-success"><i class="fa fa-check"></i> Save</button>
			                </div>
		                </div>
	                    <div class="portlet-body">
	                        <div class="tabbable-bordered">
	                            <ul class="nav nav-tabs">
                            	    <li>
	                                    <a href="javascript:;" onclick="javascript:location='/my_contents/notice/info/${contents_id}/${language_id}.dr'" data-toggle="tab"> Edit </a>
	                                </li>
	                                <li class="active">
	                                    <a href="javascript:;" onclick="javascript:location='/my_contents/notice/image/${contents_id}/${language_id}.dr'" data-toggle="tab"> 이미지 </a>
	                                </li>
		                        </ul>
	                            <div class="tab-content">
	                                <div class="tab-pane active" id="tab_general">
	                                	<div class="row">
	                        				<div class="col-md-12">
			                                    <div class="form-body">
			                                    	<div class="form-group last">
	                                                    <div class="col-md-12">
	                                                        <div class="fileinput fileinput-new" data-provides="fileinput">
	                                                            <div class="fileinput-new thumbnail" style="width:100%; height:auto;">
	                                                                <img src="/image/${info.image}" alt="${info.image}" style="margin-top:0px;"/> </div>
	                                                            <div class="fileinput-preview fileinput-exists thumbnail" style="max-width:100%; max-height:auto;"> </div>
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
	                <input type="hidden" name="contents_id" id="contents_id" value="${contents_id}"/>
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
        url: '/my_contents/notice/save/image.dr',
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
	var action = '/my_contents/notice.dr';
	$("#form").attr("action", action);
	$('#form').submit();
});
//--></script>
<%@include file="/WEB-INF/inc/bottom.jspf" %>
