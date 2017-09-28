<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> <span style="color:red">[My Home Doc]</span> 책 <small>Books</small></h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	        	<!-- BEGIN FORM-->
	            <form action="/my_contents/books.dr" id="form" class="horizontal-form">
	            <div class="portlet box blue">
	                <div class="portlet-title">
	                    &nbsp;
	                </div>
	                <div class="portlet-body form">
	                    <div>
	                        <div class="form-actions right">
	                        	<a href="/my_contents/book/add.dr" data-toggle="tooltip" title="새로 추가" class="btn green"><i class="fa fa-plus"></i> 추가</a>
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
		                                    <th width=""> 이미지 </th>
		                                    <th width=""> 제목 </th>
		                                    <th> 정렬 순서 </th>
		                                    <th> 메인 </th>
		                                    <th> 조회수 </th>
		                                    <th> Status </th>
		                                    <th>  </th>
		                                </tr>
		                            </thead>
		                            <tbody>
									<c:forEach items="${list}" var="item">
										<tr role="row">
											<td><img src="/image/${item.image}" style="height:40px;width:auto;" title="${item.title}"/></td>
											<td class="text-align-left"><a href="/my_contents/book/${item.book_id}/${item.language_id}.dr">${item.title}</a></td>
											<td>${item.sort_order}</td>
											<td>${ctag:getYesOrNo(item.is_main)}</td>
											<td>${ctag:getNumber(item.hits)}</td>
											<td>${ctag:getYesOrNo(item.status)}</td>
											<td class="text-center">
												<a href="javascript:;" data-toggle="tooltip" title="삭제" class="btn btn-sm btn-outline red" onclick="goDelete('${item.book_id}')"><i class="fa fa-trash"></i> 삭제</a>
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
function goDelete(book_id) {
	if(confirm('삭제하시겠습니까?')) {
		var action = '/my_contents/book/delete/'+book_id+'.dr';
		$("#form").attr("action", action);
		$('#form').submit();
	}
}
//--></script> 
<%@include file="/WEB-INF/inc/bottom.jspf" %>
