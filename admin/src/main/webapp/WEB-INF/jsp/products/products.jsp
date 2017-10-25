<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> 제품 <small>Products</small></h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	        	<!-- BEGIN FORM-->
	            <form action="/sales/orders.dr" id="form" class="horizontal-form">
	            <div class="portlet box blue">
	                <div class="portlet-title">
	                    &nbsp;
	                </div>
	                <div class="portlet-body form">
	                    
                        <div class="form-body">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="control-label">제품명</label>
                                        <input type="text" id="name" name="name" class="form-control" placeholder="" value="${param.name}">
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="control-label">% Reward</label>
                                        <input type="text" id="reward_group_id" name="reward_group_id" class="form-control" placeholder="" value="${param.reward_group_id}">
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="control-label">제품 상태</label>
                                        <select class="form-control" id="status" name="status">
                                            <option value=""></option>
                                            <option value="1" <c:if test="${'1'==param.status}">selected</c:if>>활성</option>
                                            <option value="0" <c:if test="${'0'==param.status}">selected</c:if>>비활성</option>
                                        </select>
                                    </div>
                                </div>
                                <!--/span-->
                            </div>
                            <!--/row-->
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="control-label">모델번호</label>
                                        <input type="text" id="model" name="model" class="form-control" placeholder="" value="${param.model}">
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="control-label">남은 수량</label>
                                        <input type="text" id="quantity" name="quantity" class="form-control" placeholder="" value="${param.quantity}">
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="control-label">제조사</label>
                                        <select class="form-control" id="manufacturer_id" name="manufacturer_id">
                                        	<option value=""></option>
                                        <c:forEach items="${manufacturerList}" var="item">
                                        	<option value="${item.manufacturer_id}" <c:if test="${item.manufacturer_id==param.manufacturer_id}">selected</c:if>>${item.name}</option>
                                        </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <!--/span-->
                            </div>
                            <!--/row-->
                        </div>
                        <div>
                        	<div class="form-actions left" style="float:left">
	                        	<button type="button" class="btn green" onclick="javascript:location='/product/view/meta/0/1.dr'"><i class="icon-plus"></i> 추가</button>
	                        </div>
	                        <div class="form-actions right">
	                        	<button type="button" id="button-reset" class="btn black"><i class="fa fa-refresh"></i> Reset</button>
	                            <button type="button" id="button-submit" class="btn blue"><i class="fa fa-search"></i> 검색</button>
	                        </div>
                        </div>
                        <input type="hidden" name="product_ids" id="product_ids" value="">
                        <input type="hidden" name="page" id="page" value="${page}">
	                    
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
		                                    <!-- <th width="2%">
		                                        <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
		                                            <input type="checkbox" name="selectedAll" id="selectedAll" class="group-checkable"  onclick="$('input[name*=\'selected\']').prop('checked', this.checked);"/>
		                                            <span></span>
		                                        </label>
		                                    </th> -->
		                                    <th> 이미지 </th>
		                                    <th> 제품명 </th>
		                                    <th> 모델번호 </th>
		                                    <th> 제조사 </th>
		                                    <th> 가격 </th>
		                                    <th> 남은 수량 </th>
		                                    <th> % Reward </th>
		                                    <th> 포인트 </th>
		                                    <th> 제품상태 </th>
		                                </tr>
		                            </thead>
		                            <tbody>
		                            <c:forEach items="${list}" var="item">
		                            	<tr role="row">
		                            		<td class="text-align-center"><a href="/product/view/image/${item.product_id}/1.dr" target="_self"><img src="/image/${item.image}" class="img-responsive" style="width:auto;height:50px;margin-left:auto;margin-right:auto;display:block;"></a></td>
		                            		<td class="text-align-left"><a href="/product/view/info/${item.product_id}/1.dr" target="_self">${item.name}</a></td>
		                            		<td class="text-align-center">${item.model}</td>
		                            		<td class="text-align-center">${item.manufacturer_name}</td>
		                            		<td class="text-align-right">${ctag:getCurrency(item.price)}<c:if test="${item.special>0}"> <span style="color:red">(Sale)</span></c:if></td>
		                            		<td class="text-align-right">
		                            		<c:choose>
		                            		<c:when test="${item.quantity<1}">
		                            			<span class="label label-sm label-danger circle" style="font-size:18px;padding:3px 10px;">${item.quantity}</span>
		                            		</c:when>
		                            		<c:when test="${item.quantity<6}">
		                            			<span class="label label-sm label-warning circle" style="font-size:18px;padding:3px 10px;">${item.quantity}</span>
		                            		</c:when>
		                            		<c:otherwise>
		                            			${ctag:getNumber(item.quantity)}
		                            		</c:otherwise>
		                            		</c:choose>
		                            		</td>
		                            		<td class="text-align-right"><c:if test="${item.reward_group_id!='0'}">${item.reward_group_id} %</c:if></td>
		                            		<td class="text-align-right">
		                            			${ctag:getNumber(item.points)}
		                            		</td>
		                            		<td class="text-align-center">${ctag:getActive(item.status)}</td>
		                            	</tr>
		                            </c:forEach>
		                            </tbody>
		                        </table>
		                    </div>
	                    </div>
	                    <c:if test="${pageMaker.end>1}">
	                    <div class="row">
	                    	<div class="col-md-4 col-sm-4">
	                    		<p class="text-align-left">${pageMaker.from} ~ ${pageMaker.to} 표시 중 - <strong>총 ${pageMaker.count}개</strong></p>
	                    	</div>
	                    	<div class="col-md-8 col-sm-8">
	                    		<div class="dataTables_paginate paging_bootstrap_full_number">
		                        	<ul class="pagination">
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
<script type="text/javascript"><!--
// 검색
$('#button-submit').on('click', function() {
	var action = '/products.dr';
	$("#form").attr("action", action);
	$('#form').submit();
});

//폼 초기화
$("#button-reset").click(function() {  
    $('#name').val('');
    $('#reward_group_id').val('');
    $('#status').val('');
    $('#model').val('');
    $('#quantity').val('');
    $('#manufacturer_id').val('');
}); 

//페이지이동
function goPage(page) {
	var action = '/products.dr';
	$('#page').val(page);
	$("#form").attr("action", action);
	$('#form').submit();
}
//--></script> 
<%@include file="/WEB-INF/inc/bottom.jspf" %>
