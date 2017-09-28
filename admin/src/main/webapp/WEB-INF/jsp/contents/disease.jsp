<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> 질병과 추천생약제 <small> Disease Informations</small></h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	        	<!-- BEGIN FORM-->
	            <form action="/contents/security/disease.dr" id="form" class="horizontal-form">
	            <div class="portlet box blue">
	                <div class="portlet-title">
	                    &nbsp;
	                </div>
	                <div class="portlet-body form">
	                	<div class="form-body">
                            <div class="row">
                                <div class="col-md-10">
                                    <div class="form-group">
                                        <label class="col-md-1 control-label">제목</label>
                                        <div class="col-md-9">
                                        	<input type="text" id="filter_name" name="filter_name" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
                        	<div class="form-actions left" style="float:left">
	                        	<a href="/contents/security/disease/add.dr" data-toggle="tooltip" title="새로 추가" class="btn green"><i class="fa fa-plus"></i> 추가</a>
	                        </div>
	                        <div class="form-actions right">
	                        	<button type="submit" id="button-submit" class="btn blue"><i class="fa fa-search"></i> 검색</button>
	                        </div>
                        </div>
	                </div>
	            </div>
	            
	            <div class="portlet light portlet-fit portlet-datatable bordered">
	                <div class="portlet-body">
	                    <div class="table-container dataTables_wrapper no-footer">
	                    	<div id="datatable_orders_wrapper" class="dataTables_wrapper dataTables_extended_wrapper no-footer">
	                    		<div class="row">
	                    			<div class="col-md-6 col-sm-6 pull-left">
	                    				<p>${pageMaker.from} ~ ${pageMaker.to} 표시 중 - <strong>총 ${pageMaker.count}개</strong></p>
	                    		    </div>
	                    		</div>
	                    	</div>
	                    	<div class="table-responsive">
			                    <table class="table table-striped table-bordered table-hover table-checkable" id="datatable_orders">
		                            <thead>
		                                <tr role="row" class="heading">
		                                    <th width="70%"> 제목 </th>
		                                    <th width="15%"> Status </th>
		                                    <th>  </th>
		                                </tr>
		                            </thead>
		                            <tbody>
									<c:forEach items="${list}" var="item">
										<tr role="row">
											<td class="text-align-left"><a href="/contents/security/disease/contents/${item.contents_id}/${ctag:getLanguage()}.dr">${item.title}</a></td>
											<td class="text-align-center">${ctag:getYesOrNo(item.status)}</td>
											<td class="text-center">
												<a href="javascript:;" data-toggle="tooltip" title="삭제" class="btn btn-sm btn-outline red" onclick="goDelete('${item.contents_id}')"><i class="fa fa-trash"></i> 삭제</a>
											</td>
										</tr>
									</c:forEach>
									</tbody>
							        
		                        </table>
		                    </div>
	                    </div>
	                    <c:if test="${pageMaker.end>1}">
	                    <div class="row">
	                    	<div class="col-md-4 col-sm-4">
	                    		<p style="float:left;">${pageMaker.from} ~ ${pageMaker.to} 표시 중 - <strong>총 ${pageMaker.count}개</strong></p>
	                    	</div>
	                    	<div class="col-md-8 col-sm-8">
	                    		<div class="dataTables_paginate paging_bootstrap_full_number">
		                        	<ul class="pagination" style="visibility: visible;">
		                        	<c:if test="${pageMaker.prev}">
		                        		<li class="prev"><a href="javascript:;" onclick="goPage(1)" title="First"><i class="fa fa-angle-double-left"></i></a></li>
		                        		<li class="prev"><a href="javascript:;" onclick="goPage(${pageMaker.page-1})" title="Prev"><i class="fa fa-angle-left"></i></a></li>
		                        	</c:if>
	                        		<c:forEach begin="${pageMaker.start}" end="${pageMaker.end}" var="idx">
		                                <c:choose>
		                                <c:when test="${idx == pageMaker.page}">
		                                    <li class="active"><span>${idx}</span></li>
		                                </c:when>
		                                <c:otherwise>
		                                    <li><a href="javascript:;" onclick="goPage(${idx})">${idx}</a></li>
		                                </c:otherwise>
		                                </c:choose>
		                            </c:forEach>
		                        	<c:if test="${pageMaker.next }">
		                        		<li class="next"><a href="javascript:;" onclick="goPage(${pageMaker.page+1})" title="Next"><i class="fa fa-angle-right"></i></a></li>
		                        		<li class="next"><a href="javascript:;" onclick="goPage(${pageMaker.end})" title="Last"><i class="fa fa-angle-double-right"></i></a></li>
		                        	</c:if>
		                        	</ul>
		                        </div>
	                    	</div>
	                    </div>
	                    </c:if>
	                </div>
	            </div>
	            </form>
	            <!-- END FORM-->
	        </div>
	    </div>
	</div>
</div>

<%@include file="/WEB-INF/inc/footer.jspf" %>
<script type="text/javascript">
$(function(){
    $('#filter_name').autocomplete({"source":function(request,response){
       $.getJSON("/contents/security/disease/autocomplete.dr",{"filter_name":$('#filter_name').val()},
       function(json) {
          return response($.map(json.list, function(item){
              return {label:item.title, value:item.title};
          }));
       });
    }});
});
</script>
<script type="text/javascript"><!--
//페이지이동
function goPage(page) {
	var action = '/contents/security/disease.dr';
	$('#page').val(page);
	$("#form").attr("action", action);
	$('#form').submit();
}

// 삭제
function goDelete(contents_id) {
	if(confirm('삭제하시겠습니까?')) {
		var action = '/contents/security/disease/delete/'+contents_id+'.dr';
		$("#form").attr("action", action);
		$('#form').submit();
	}
}
//--></script> 
<%@include file="/WEB-INF/inc/bottom.jspf" %>
