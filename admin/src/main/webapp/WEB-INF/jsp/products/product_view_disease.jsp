<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> 제품 수정 <small>edit product</small></h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	            <!-- Begin: life time stats -->
	            <form action="/product/save/disease.dr" id="form" class="form-horizontal form-row-seperated" method="POST" enctype="multipart/form-data">
		            <div class="portlet light portlet-fit portlet-datatable bordered">
		                <div class="portlet-title">
		                    <div class="caption">
		                        <i class="icon-settings font-dark"></i>
		                        <span class="caption-subject font-dark sbold"> 제품명: ${product.name}&nbsp;&nbsp;/&nbsp;&nbsp;제품번호: ${product.product_id}</span>
		                    </div>
		                    <div class="actions btn-set">
		                    	<button type="button" class="btn dark" id="button_back" onclick="javascript:location='/product/back.dr'"><i class="fa fa-reply"></i> BACK</button>
	                    		<button type="submit" id="button-submit" class="btn btn-success"><i class="fa fa-check"></i> Save</button>
			                </div>
		                </div>
	                    <div class="portlet-body">
	                        <div class="tabbable-bordered">
	                            <ul class="nav nav-tabs">
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/product/view/meta/${product_id}/${language_id}.dr'" data-toggle="tab"> META 정보 </a>
	                                </li>
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/product/view/info/${product_id}/${language_id}.dr'" data-toggle="tab"> 제품정보 </a>
	                                </li>
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/product/view/image/${product_id}/${language_id}.dr'" data-toggle="tab"> 이미지 </a>
	                                </li>
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/product/view/category/${product_id}/${language_id}.dr'" data-toggle="tab"> 관련 카테고리 </a>
	                                </li>
	                                <li class="active">
	                                    <a href="javascript:;" onclick="javascript:location='/product/view/disease/${product_id}/${language_id}.dr'" data-toggle="tab"> 추천 생약제 </a>
	                                </li>
	                                <li>
	                                    <a href="javascript:;" onclick="javascript:location='/product/view/discount/${product_id}/${language_id}.dr'" data-toggle="tab"> 할인가격 </a>
	                                </li>
	                            </ul>
	                            <div class="tab-content">
	                                <div class="tab-pane active" id="tab_general">
	                                	<div class="row">
	                        				<div class="col-md-5">
			                                    <div class="form-body">
			                                    	<div class="form-group">
			                                    		<label class="control-label"><span class="required"> * </span> 필수 추천 생약제</label>
                                                        <div class="form-control height-auto">
                                                            <div class="scroller" style="height:100%;" data-always-visible="1">
                                                                <ul class="list-unstyled">
                                                                <c:forEach items="${mustList}" var="list">
                                                                    <li>
                                                                        <label>
                                                                            <input type="checkbox" name="must_contents_id" id="must_contents_id" value="${list.contents_id}" <c:if test="${list.checked>0}">checked</c:if>>&nbsp;${list.title}
                                                                        </label>
                                                                    </li>
                                                                </c:forEach>
                                                                </ul>
                                                            </div>
                                                        </div>
	                                                </div>
			                                    </div>
			                                </div>
			                                <div class="col-md-1">&nbsp;</div>
			                                <div class="col-md-6">
			                                    <div class="form-body">
			                                    	<div class="form-group">
			                                    		<label class="control-label"><span class="required"> * </span> 추가 추천 생약제</label>
                                                        <div class="form-control height-auto">
                                                            <div class="scroller" style="height:100%;" data-always-visible="1">
                                                                <ul class="list-unstyled">
                                                                <c:forEach items="${addList}" var="list">
                                                                    <li>
                                                                        <label>
                                                                            <input type="checkbox" name="add_contents_id" id="add_contents_id" value="${list.contents_id}" <c:if test="${list.checked>0}">checked</c:if>>&nbsp;${list.title}
                                                                        </label>
                                                                    </li>
                                                                </c:forEach>
                                                                </ul>
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
	                <input type="hidden" name="product_id" id="product_id" value="${product_id}"/>
	                <input type="hidden" name="language_id" id="language_id" value="${language_id}"/>
	                <input type="hidden" name="must_contents_ids" id="must_contents_ids" value="">
	                <input type="hidden" name="add_contents_ids" id="add_contents_ids" value="">
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
	var action = '/product/save/disease.dr';
	var mustDATA='';
	$("input[name=\'must_contents_id\']:checked").each(function() {
	  if($(this).val()!='on') {
    	 if(mustDATA!='') mustDATA += ",";
    	 mustDATA += ($(this).val());
      }
    });
	
    if(mustDATA=='') {
    	return;
    }
    $('#must_contents_ids').val(mustDATA);
    
	var addDATA='';
	$("input[name=\'add_contents_id\']:checked").each(function() {
	  if($(this).val()!='on') {
    	 if(addDATA!='') addDATA += ",";
    	 addDATA += ($(this).val());
      }
    });
	
    if(mustDATA=='') {
    	return;
    }
    $('#add_contents_ids').val(addDATA);
    
    
	$('#form').attr('action', action);
});

// Back
$('#button_back').on('click', function() {
	var action = '/product/back.dr';
	$("#form").attr("action", action);
	$('#form').submit();
});
//--></script>
<%@include file="/WEB-INF/inc/bottom.jspf" %>
