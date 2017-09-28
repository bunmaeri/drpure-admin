<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> <span style="color:red">[My Home Doc]</span> 참조된 제품 찾기</h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	        	<!-- BEGIN FORM-->
	            <form action="/my_products/reference.dr" id="form" class="horizontal-form">
	            <div class="portlet box blue">
	                <div class="portlet-title">
	                    &nbsp;
	                </div>
	                <div class="portlet-body form">
	                	<div class="form-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="col-md-2 control-label">제품번호<br/>(제품 수정 메뉴에서 확인)</label>
                                        <div class="col-md-2">
                                        	<input type="text" id="product_id" name="product_id" class="form-control" placeholder="제품번호" value="${param.product_id}">
                                        </div>
                                        <div class="col-md-8">
                                        	<c:if test="${info.name!=null}">
                                        	<div class="form-group">
								                <img src="/image/${info.image}" alt="${info.image}" title="${info.image}" style="width:auto;height:180px;"/><br/>
								                ${info.name}&nbsp;&nbsp;/&nbsp;&nbsp;모델: ${info.model}
							                </div>
							                </c:if>
							            </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
                        	<div class="form-actions right">
	                        	<button type="submit" id="button-submit" class="btn blue"><i class="fa fa-search"></i> 검색</button>
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
		                                <tr>
						                    <td class="text-align-left"><i class="fa fa-check-square-o" aria-hidden="true"></i> 제품</td>
						                </tr>
		                            </thead>
		                            <tbody>
									<c:forEach items="${products}" var="item">
										<tr role="row">
											<td class="text-align-left"><a href="/my_product/view/meta/${item.product_id}/${item.language_id}.dr" target="_blank">${item.name}</a></td>
										</tr>
									</c:forEach>
									</tbody>
							        
		                        </table>
		                    </div>
	                    </div>
	                    
	                    <div class="table-container dataTables_wrapper no-footer">
	                    	<div class="table-responsive">
			                    <table class="table table-striped table-bordered table-hover table-checkable" id="datatable_orders">
		                            <thead>
		                                <tr>
						                    <td class="text-align-left"><i class="fa fa-check-square-o" aria-hidden="true"></i> 건강 정보</td>
						                </tr>
		                            </thead>
		                            <tbody>
									<c:forEach items="${healths}" var="item">
										<tr role="row">
											<td class="text-align-left"><a href="/my_contents/security/health/contents/${item.contents_id}/${item.language_id}.dr" target="_blank">${item.title}</a></td>
										</tr>
									</c:forEach>
									</tbody>
							        
		                        </table>
		                    </div>
	                    </div>
	                    
	                    <div class="table-container dataTables_wrapper no-footer">
	                    	<div class="table-responsive">
			                    <table class="table table-striped table-bordered table-hover table-checkable" id="datatable_orders">
		                            <thead>
		                                <tr>
						                    <td class="text-align-left"><i class="fa fa-check-square-o" aria-hidden="true"></i> 임상 사례</td>
						                </tr>
		                            </thead>
		                            <tbody>
									<c:forEach items="${clinics}" var="item">
										<tr role="row">
											<td class="text-align-left"><a href="/my_contents/security/clinic/contents/${item.contents_id}/${item.language_id}.dr" target="_blank">${item.title}</a></td>
										</tr>
									</c:forEach>
									</tbody>
							        
		                        </table>
		                    </div>
	                    </div>
	                    
	                    <div class="table-container dataTables_wrapper no-footer">
	                    	<div class="table-responsive">
			                    <table class="table table-striped table-bordered table-hover table-checkable" id="datatable_orders">
		                            <thead>
		                                <tr>
						                    <td class="text-align-left"><i class="fa fa-check-square-o" aria-hidden="true"></i> 질병정보</td>
						                </tr>
		                            </thead>
		                            <tbody>
									<c:forEach items="${diseases}" var="item">
										<tr role="row">
											<td class="text-align-left"><a href="/my_contents/security/disease/contents/${item.contents_id}/${item.language_id}.dr" target="_blank">${item.title}</a></td>
										</tr>
									</c:forEach>
									</tbody>
							        
		                        </table>
		                    </div>
	                    </div>
	                    
	                    <div class="table-container dataTables_wrapper no-footer">
	                    	<div class="table-responsive">
			                    <table class="table table-striped table-bordered table-hover table-checkable" id="datatable_orders">
		                            <thead>
		                                <tr>
						                    <td class="text-align-left"><i class="fa fa-check-square-o" aria-hidden="true"></i> 추천 생약제</td>
						                </tr>
		                            </thead>
		                            <tbody>
									<c:forEach items="${medicinese}" var="item">
										<tr role="row">
											<td class="text-align-left">
												<a href="/my_contents/security/disease/contents/${item.contents_id}/${item.language_id}.dr#tab-data" target="_blank">
													<c:choose>
													<c:when test="${item.medicine_id=='2'}">
													[필수 생약제]&nbsp;${item.title}
													</c:when>
													<c:otherwise>
													[추가 생약제]&nbsp;${item.title}
													</c:otherwise>
													</c:choose>
												</a>
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
<%@include file="/WEB-INF/inc/bottom.jspf" %>
