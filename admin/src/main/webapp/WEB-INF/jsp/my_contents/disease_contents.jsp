<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/froala_css.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> <span style="color:red">[My Home Doc]</span> 질병과 추천생약제 <small> edit disease information</small></h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	            <!-- Begin: life time stats -->
	            <form action="/my_contents/security/disease/contents/save.dr" id="form" class="form-horizontal form-row-seperated">
	            	<div class="portlet light portlet-fit portlet-datatable bordered">
		                <div class="portlet-title">
		                    <div class="caption">
		                        <i class="icon-settings font-dark"></i>
		                        <span class="caption-subject font-dark sbold uppercase"> 제목: ${main.title}</span>
		                    </div>
		                    <div class="actions">
	                    		<button type="button" class="btn dark" id="button_back" onclick="javascript:location='/my_contents/security/disease/cancel.dr'"><i class="fa fa-reply"></i> BACK</button>
			                	<button type="button" id="button-submit" class="btn btn-success"><i class="fa fa-check"></i> Save</button>
			                </div>
		                </div>
	                    <div class="portlet-body">
	                        <div class="tabbable-bordered">
	                            <ul class="nav nav-tabs">
	                                <li class="active">
	                                    <a href="#tab-general" data-toggle="tab"> 기본정보 </a>
	                                </li>
	                                <li>
	                                    <a href="#tab-data" data-toggle="tab"> 추천 생약제 </a>
	                                </li>
	                            </ul>
	                            <div class="tab-content">
	                                <div class="tab-pane active" id="tab-general">
	                                	<div class="row">
	                        				<div class="col-md-1" style="width:11%">
	                        					<ul class="ver-inline-menu tabbable margin-bottom-10">
	                        					<c:forEach items="${languages}" var="item">
	                        						<li <c:if test="${item.language_id==language_id}">class="active"</c:if>>
	                        							<a data-toggle="tab" href="javascript:;" onclick="javascript:location='/my_contents/security/disease/contents/${contents_id}/${item.language_id}.dr'">
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
			                                        <div class="form-group class-status">
														<label class="control-label" for="status">Status</label>
														<select name="status" id="status" class="form-control">
															<option value="1" <c:if test="${info.status=='1'}">selected="selected"</c:if>>활성</option>
															<option value="0" <c:if test="${info.status=='0'}">selected="selected"</c:if>>비활성</option>
														</select>
													</div>

			                                    </div>
			                                </div>
			                            </div>
	                                </div>
	                                
	                                <div class="tab-pane" id="tab-data">
	                                	<div class="row">
	                                		<div class="col-md-6">
	                        					<div class="form-body">
	                        						<div class="form-group">
									                    <label class="control-label" for="required_product">필수 추천 생약제</label>
							                            <input type="text" name="required_product" id="required_product" class="form-control" />
							                  			<div id="disease_required_product" class="well well-sm" style="height:500px; overflow: auto; font-size:16px;">
										                    <c:forEach items="${required_products}" var="item">
										                    <div id="disease_required_product${item.product_id}"><i class="fa fa-trash"></i> ${item.name}<input type="hidden" name="required_category_product" value="${item.product_id}"></div>
										                    </c:forEach>
							                  			</div>
								                	</div>
						                		</div>
							                </div>
					                        <div class="col-md-1">&nbsp;</div>
					                        <div class="col-md-5">
			                                    <div class="form-body">
			                                        <label class="control-label" for="additional_product">추가 추천 생약제</label>
							                        <input type="text" name="additional_product" id="additional_product" class="form-control" />
							                  		<div id="disease_additional_product" class="well well-sm" style="height:500px; overflow: auto; font-size:16px;">
							                    		<c:forEach items="${additional_products}" var="item">
							                    		<div id="disease_additional_product${item.product_id}"><i class="fa fa-trash"></i> ${item.name}<input type="hidden" name="additional_category_product" value="${item.product_id}"></div>
							                    		</c:forEach>
							                  		</div>
								                	
			                                    </div>
			                                </div>
			                            </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                <input type="hidden" name="contents_id" id="contents_id" value="${contents_id}">
					<input type="hidden" name="language_id" id="language_id" value="${language_id}">
					<input type="hidden" name="code" id="code" value="${code}">
	            </form>
	        </div>
	    </div>
   
   </div>
	<!-- END CONTENT BODY -->
</div>
<!-- END CONTENT -->
<%@include file="/WEB-INF/inc/footer.jspf" %>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<%@include file="/WEB-INF/inc/froala_js.jspf" %>
<script type="text/javascript"><!--
$(function(){
    $('#required_product').autocomplete({
        "source":function(request,response) {
            $.getJSON("/products/autocomplete.dr",{"name":$('#required_product').val()},
            function(json) {
                return response($.map(json.list, function(items){
                    return {
                    	label:items.name.toLowerCase().replace($( "#required_product" ).val().toLowerCase(),"<span style='font-weight:bold;color:Blue;'>" + $( "#required_product" ).val().toLowerCase() + "</span>"),
                    	title:items.name,
                    	value:items.product_id
                    };
                    
                }));
            });
        },
    	"select": function(items) {
    		//alert(items.name);
    		//$('#required_product').val('');
    		//$('#disease_required_product' + items['value']).remove();
    		//$('#disease_required_product').append('<div id="disease_required_product' + items['value'] + '"><i class="fa fa-minus-circle"></i> ' + items['label'] + '</div>');
    	},
    	close: function() {
    		$('#required_product').val('');
        }
    })
    .data('uiAutocomplete')._renderItem = function( ul, item ) {
        return $( "<li style='cursor:hand;font-size:16px;'></li>" )
            .data( "item.autocomplete", item )
            .append("<a onclick=\"setData('"+item.title+"','"+item.value+"');\">"  + unescape(item.label) + "</a>")
        .appendTo( ul );
    };
});

function setData(title, value) {
	//alert(title+","+value);
    //$("<div>").text(message).prependTo("#disease_required_product");
    $('#disease_required_product' + value).remove();
    $('#disease_required_product').append('<div id="disease_required_product' +value+ '"><i class="fa fa-trash"></i> ' +title + '<input type="hidden" name="required_category_product" value="' +value+ '"></div>');
    $("#disease_required_product").scrollTop(0);
    
}

$('#disease_required_product').delegate('.fa-trash', 'click', function() {
	$(this).parent().remove();
});
//--></script> 
<script type="text/javascript"><!--
$(function(){
    $('#additional_product').autocomplete({
        "source":function(request,response) {
            $.getJSON("/products/autocomplete.dr",{"name":$('#additional_product').val()},
            function(json) {
                return response($.map(json.list, function(items){
                    return {
                    	label:items.name.toLowerCase().replace($("#additional_product").val().toLowerCase(),"<span style='font-weight:bold;color:Blue;'>" + $("#additional_product").val().toLowerCase() + "</span>"),
                    	title:items.name,
                    	value:items.product_id
                    };
                    
                }));
            });
        },
    	"select": function(items) {
    	},
    	close: function() {
    		$('#additional_product').val('');
        }
    })
    .data('uiAutocomplete')._renderItem = function( ul, item ) {
        return $( "<li style='cursor:hand;font-size:16px;'></li>" )
            .data( "item.autocomplete", item )
            .append("<a onclick=\"setData2('"+item.title+"','"+item.value+"');\">"  + unescape(item.label) + "</a>")
        .appendTo( ul );
    };
});

function setData2(title, value) {
    $('#disease_additional_product' + value).remove();
    $('#disease_additional_product').append('<div id="disease_additional_product' +value+ '"><i class="fa fa-trash"></i> ' +title + '<input type="hidden" name="additional_category_product" value="' +value+ '"></div>');
    $("#disease_additional_product").scrollTop(0);
    
}

$('#disease_additional_product').delegate('.fa-trash', 'click', function() {
	$(this).parent().remove();
});
//--></script> 

<script type="text/javascript"><!--
$('#button-submit').on('click', function() {
    $.ajax({
        url: '/my_contents/security/disease/contents/save.dr',
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
