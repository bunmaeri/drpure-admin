<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/froala_css.jspf" %>

<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> 책 정보 수정 <small>edit book</small></h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getErrorList(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	            <!-- Begin: life time stats -->
	            <form action="/contents/book/save.dr" id="form" class="form-horizontal form-row-seperated" method="POST" enctype="multipart/form-data">
	            	<div class="portlet light portlet-fit portlet-datatable bordered">
		                <div class="portlet-title">
		                    <div class="caption">
		                        <i class="icon-settings font-dark"></i>
		                        <span class="caption-subject font-dark sbold uppercase"> 제목: ${book.title}</span>
		                    </div>
		                    <div class="actions">
	                    		<button type="button" class="btn dark" id="button_back" onclick="javascript:location='/contents/books.dr'"><i class="fa fa-reply"></i> BACK</button>
			                	<button type="submit" id="button-submitss" class="btn btn-success"><i class="fa fa-check"></i> Save</button>
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
	                                <div class="tab-pane active" id="language${info.language_id}">
	                                	<div class="row">
	                        				<div class="col-md-1" style="width:11%">
	                        					<ul class="ver-inline-menu tabbable margin-bottom-10">
	                        					<c:forEach items="${languages}" var="item">
	                        						<li <c:if test="${item.language_id==language_id}">class="active"</c:if>>
	                        							<a data-toggle="tab" href="javascript:;" onclick="javascript:location='/contents/book/${book_id}/${item.language_id}.dr'">
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
			                                            <label class="control-label">제목
			                                                <span class="required"> * </span>
			                                            </label>
			                                            <input type="text" class="form-control" name="title" id="title" value="${info.title}">
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="control-label">설명</label>
			                                            <textarea name="description" id="description" class="form-control summernote">${info.description}</textarea>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="control-label">Meta Title</label>
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
			                                        <div class="form-group class-sort-order">
														<label class="control-label" for="sort_order">Sort Order</label>
														<input type="text" name="sort_order" value="${info.sort_order}" placeholder="Sort Order" id="sort_order" class="form-control" />
													</div>
													<div class="form-group class-status">
														<label class="control-label" for="status">메인</label>
														<select name="is_main" id="is_main" class="form-control">
															<option value="1" <c:if test="${info.is_main=='1'}">selected="selected"</c:if>>예</option>
															<option value="0" <c:if test="${info.is_main=='0'}">selected="selected"</c:if>>아니오</option>
														</select>
													</div>
													<div class="form-group class-status">
														<label class="control-label" for="status">Status</label>
														<select name="status" id="status" class="form-control">
															<option value="1" <c:if test="${info.status=='1'}">selected="selected"</c:if>>활성</option>
															<option value="0" <c:if test="${info.status=='0'}">selected="selected"</c:if>>비활성</option>
														</select>
													</div>
													<div class="form-group class-status">
	                                                    <label class="control-label">제품 이미지</label>
                                                        <div class="fileinput fileinput-new" data-provides="fileinput">
                                                            <div class="fileinput-new thumbnail" style="width:200px; height:150px;">
                                                                <img src="/image/${info.image}" alt="${info.title}" /> </div>
                                                            <div class="fileinput-preview fileinput-exists thumbnail" style="max-width:200px; max-height:150px;"> </div>
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
	                <input type="hidden" name="book_id" id="book_id" value="${book_id}">
					<input type="hidden" name="language_id" id="language_id" value="${language_id}">
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
        url: '/contents/book/save.dr',
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
        	$('.custom-alerts').remove();
            $('.has-error').removeClass();
        
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
                if (json.Error.meta_title) {
                	$('input[name=\'meta_title\']').parent().addClass('has-error');
                    $('input[name=\'meta_title\']').after('<span class="help-block"> ' + json.Error.meta_title + '</span>');
                }
                if (json.Error.meta_description) {
                	$('input[name=\'meta_description\']').parent().addClass('has-error');
                    $('input[name=\'meta_description\']').after('<span class="help-block"> ' + json.Error.meta_description + '</span>');
                }
                if (json.Error.meta_keyword) {
                	$('input[name=\'meta_keyword\']').parent().addClass('has-error');
                    $('input[name=\'meta_keyword\']').after('<span class="help-block"> ' + json.Error.meta_keyword + '</span>');
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
