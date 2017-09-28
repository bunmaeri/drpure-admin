<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/froala_css.jspf" %>

<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> 제품 카테고리 수정</h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	            <!-- Begin: life time stats -->
	            <form action="/category/save/meta.dr" id="form" class="form-horizontal form-row-seperated">
		            <div class="portlet light portlet-fit portlet-datatable bordered">
		                <div class="portlet-title">
		                    <div class="caption">
		                        <i class="icon-settings font-dark"></i>
		                        <span class="caption-subject font-dark sbold uppercase"> 카테고리 명: ${category.name}</span>
		                    </div>
		                    <div class="actions">
	                    		<button type="button" class="btn dark" id="button_back" onclick="javascript:location='/categories.dr'"><i class="fa fa-reply"></i> BACK</button>
			                	<button type="button" id="button-submit" class="btn btn-success"><i class="fa fa-check"></i> Save</button>
			                </div>
		                </div>
	                    <div class="portlet-body">
	                        <div class="tabbable-bordered">
	                            <ul class="nav nav-tabs">
	                                <li class="active">
	                                    <a href="javascript:;" onclick="javascript:location='/category/view/meta/${category_id}/${language_id}.dr'" data-toggle="tab"> META 정보 </a>
	                                </li>
	                                <c:if test="${category_id!='0'}">
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/category/view/image/${category_id}/${language_id}.dr'" data-toggle="tab"> 이미지 </a>
	                                </li>
	                                </c:if>
	                            </ul>
	                            <div class="tab-content">
	                                <div class="tab-pane active" id="tab_general">
	                                	<div class="row">
	                        				<div class="col-md-1" style="width:11%">
	                        					<ul class="ver-inline-menu tabbable margin-bottom-10">
	                        					<c:forEach items="${languages}" var="item">
	                        						<li <c:if test="${item.language_id==language_id}">class="active"</c:if>>
	                        							<a data-toggle="tab" href="javascript:;" onclick="javascript:location='/category/view/meta/${category_id}/${item.language_id}.dr'">
													        <i><img src="/image/${item.image}" title="${item.name}" style="width:20px;height:auto;"/></i> ${item.name}
													    </a>
													</li>
												</c:forEach>
					                            </ul>
					                        </div>
					                        <div class="col-md-1" style="width:2%">&nbsp;</div>
					                        <div class="col-md-11" style="width:86%">
			                                    <div class="form-body">
			                                        <div class="form-group">
			                                            <label class="control-label">제품 카테고리 명
			                                                <span class="required"> * </span>
			                                            </label>
			                                            <input type="text" class="form-control" name="name" id="name" value="${info.name}">
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="control-label">설명</label>
			                                            <textarea name="description" id="description" class="form-control summernote">${info.description}</textarea>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="control-label">Meta Title
			                                                <span class="required"> * </span>
			                                            </label>
			                                            <input type="text" class="form-control" name="meta_title" id="meta_title" value="${info.meta_title}">
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="control-label">Meta Description</label>
			                                            <textarea name="meta_description" rows="2" id="meta_description" class="form-control">${info.meta_description}</textarea>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="control-label">Meta Keywords</label>
			                                            <textarea name="meta_keyword" rows="2" id="meta_keyword" class="form-control">${info.meta_keyword}</textarea>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="control-label">PARENT 카테고리</label>
			                                            <select class="form-control" id="parent_id" name="parent_id">
			                                            	<c:forEach items="${categories}" var="item">
				                                        	<option value="${item.category_id}" <c:if test="${item.category_id==category.parent_id}">selected</c:if>>${item.path_name}</option>
				                                        	</c:forEach>
				                                        </select>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="control-label">Status</label>
		                                                <select class="form-control input-large" id="status" name="status">
				                                            <option value="1" <c:if test="${'1'==category.status}">selected</c:if>>활성</option>
				                                            <option value="0" <c:if test="${'0'==category.status}">selected</c:if>>비활성</option>
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
	                <input type="hidden" name="category_id" id="category_id" value="${category_id}"/>
	                <input type="hidden" name="language_id" id="language_id" value="${language_id}"/>
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
        url: '/category/save/meta.dr',
        type: 'post',
        data: $('#form input, #form select, #form textarea'),
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
                if (json.success_category_id) {
                	var url = '/category/view/meta/'+json.success_category_id+'/'+json.success_language_id+'.dr';
                	setTimeout(goRefresh(url), 3000); // 3000ms(3초)가 경과하면 location.reload() 함수를 실행합니다.
                }
            }
            if(json.error) {
                if (json.Message.error_name) {
                	$('input[name=\'name\']').parent().addClass('has-error');
                    $('input[name=\'name\']').after('<span class="help-block"> ' + json.Message.error_name + '</span>');
                }
                if (json.Message.error_description) {
                	$('input[name=\'description\']').parent().addClass('has-error');
                    $('input[name=\'description\']').after('<span class="help-block"> ' + json.Message.error_description + '</span>');
                }
                if (json.Message.error_meta_title) {
                	$('input[name=\'meta_title\']').parent().addClass('has-error');
                    $('input[name="meta_title"]').after('<span class="help-block"> ' + json.Message.error_meta_title + '</span>');
                }
                if (json.Message.error_meta_keyword) {
                	$('input[name=\'meta_keyword\']').parent().addClass('has-error');
                    $('input[name=\'meta_keyword\']').after('<span class="help-block"> ' + json.Message.error_meta_keyword + '</span>');
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
//--></script>
<%@include file="/WEB-INF/inc/bottom.jspf" %>
