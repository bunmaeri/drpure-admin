<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> <span style="color:red">[My Home Doc]</span> 건강정보, 질병과 추천생약제, 임상사례 카테고리</h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	        	<!-- BEGIN FORM-->
	            <form action="/my_contents/security/categories.dr" id="form" class="horizontal-form">
	            <div class="portlet box blue">
	                <div class="portlet-title">
	                    &nbsp;
	                </div>
	                <div class="portlet-body form">
	                    <div>
	                        <div class="form-actions right">
	                        	<a href="/my_contents/security/category/add.dr" data-toggle="tooltip" title="새로 추가" class="btn green"><i class="fa fa-plus"></i> 추가</a>
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
		                                    <th> 이미지 </th>
		                                    <th width="60%"> 카테고리 명 </th>
		                                    <th> Status </th>
		                                    <th>  </th>
		                                </tr>
		                            </thead>
		                            <tbody>
									<c:forEach items="${list}" var="item">
										<tr role="row">
											<td class="text-align-center"><a href="/my_contents/security/category/image/${item.code}/${ctag:getLanguage()}.dr" target="_self"><img src="/image/${item.image}" class="img-responsive" style="width:auto;height:50px;margin-left:auto;margin-right:auto;display:block;"></a></td>
											<td class="text-align-left"><a href="/my_contents/security/category/info/${item.code}/${ctag:getLanguage()}.dr">${item.title}</a></td>
											<td class="text-align-center">${ctag:getYesOrNo(item.status)}</td>
											<td class="text-center">
												<a href="javascript:;" data-toggle="tooltip" title="삭제" class="btn btn-sm btn-outline red" onclick="goDelete('${item.code}')"><i class="fa fa-trash"></i> 삭제</a>
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
// 삭제
function goDelete(code) {
	if(confirm('삭제하시면 사용하는 메뉴에 에러가 발생할 수 있습니다.\n삭제하시겠습니까?')) {
		var action = '/my_contents/security/category/delete/'+code+'.dr';
		$("#form").attr("action", action);
		$('#form').submit();
	}
}
//--></script> 
<%@include file="/WEB-INF/inc/bottom.jspf" %>
