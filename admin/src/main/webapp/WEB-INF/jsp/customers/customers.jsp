<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> 고객 <small>Customers</small></h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	        	
	            <div class="portlet box blue">
	                <div class="portlet-title">
	                    &nbsp;
	                </div>
	                <div class="portlet-body form">
	                    <!-- BEGIN FORM-->
	            		<form action="/sales/orders.dr" id="form" class="horizontal-form">
                        <div class="form-body">
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label class="control-label">고객 이름</label>
                                        <input type="text" id="customer_name" name="customer_name" class="form-control" placeholder="" value="${param.customer_name}">
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label class="control-label">고객 그룹</label>
                                        <select class="form-control" id="customer_group_id" name="customer_group_id">
                                        	<option value=""></option>
                                        <c:forEach items="${customerGroupsList}" var="item">
                                        	<option value="${item.customer_group_id}" <c:if test="${item.customer_group_id==param.customer_group_id}">selected</c:if>>${item.name}</option>
                                        </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label class="control-label">가입 경로</label>
                                        <select class="form-control" id="join_path_id" name="join_path_id">
                                        	<option value=""></option>
                                        <c:forEach items="${customerJoinPathList}" var="item">
                                        	<option value="${item.join_path_id}" <c:if test="${item.join_path_id==param.join_path_id}">selected</c:if>>${item.join_path_name}</option>
                                        </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label class="control-label">가입일자</label>
                                        <div class="input-group date date-picker" data-provide="datepicker" data-date-format="yyyy-mm-dd" data-date="${param.date_added}">
                                           <input type="text" class="form-control form-filter" name="date_added" id="date_added" value="${param.date_added}">
                                           <span class="input-group-btn">
                                               <button class="btn default" type="button">
                                                   <i class="fa fa-calendar"></i>
                                               </button>
                                           </span>
                                        </div>
                                    </div>
                                </div>
                                <!--/span-->
                            </div>
                            <!--/row-->
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label class="control-label">이메일</label>
                                        <input type="text" id="email" name="email" class="form-control" placeholder="" value="${param.email}">
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label class="control-label">My Home Doc 고객</label>
                                        <select class="form-control" id="myhomedoc" name="myhomedoc">
                                            <option value=""></option>
                                            <option value="1" <c:if test="${'1'==param.myhomedoc}">selected</c:if>>예</option>
                                            <option value="0" <c:if test="${'0'==param.myhomedoc}">selected</c:if>>아니오</option>
                                        </select>
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label class="control-label">고객 상태</label>
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
                        </div>
                        <div>
	                        <div class="form-actions right" >
	                        	<button type="button" id="button-reset" class="btn black"><i class="fa fa-refresh"></i> Reset</button>
	                            <button type="button" id="button-submit" class="btn blue"><i class="fa fa-search"></i> 검색</button>
	                        </div>
                        </div>
                        <input type="hidden" name="page" id="page" value="${page}">
                        <input type="hidden" name="page_end" id="page_end" value="${pageMaker.end}"/>
	                    </form>
	            		<!-- END FORM-->
	                </div>
	            </div>
	            
	            <div class="portlet light portlet-fit portlet-datatable bordered">
	                <div class="portlet-body">
	                    <div class="table-container dataTables_wrapper no-footer">
	                    	<c:if test="${pageMaker.end>0}">
	                    	<div id="datatable_orders_wrapper" class="dataTables_wrapper dataTables_extended_wrapper no-footer">
	                    		<div class="row">
	                    			<div class="col-md-6 col-sm-6 pull-left">
	                    				<div class="dataTables_paginate paging_bootstrap_extended">
		                    				<div class="pagination-panel left">
		                    					Page <a href="javascript:;" onclick="goPage(${page-1})" class="btn btn-sm default prev"><i class="fa fa-angle-left"></i></a>
		                    					<input type="text" id="click_page" class="pagination-panel-input form-control input-sm input-inline input-mini" value="${page}" style="text-align:center; margin: 0 5px;" readonly>
		                    					<a href="javascript:;" onclick="goPage(${page+1})" class="btn btn-sm default next"><i class="fa fa-angle-right"></i></a> of <span class="pagination-panel-total">${pageMaker.end}</span>
		                    				</div>
	                    				</div>
	                    		    </div>
	                    		    <div class="col-md-6 col-sm-6 text-align-right">
	                    		    	<p>${pageMaker.from} ~ ${pageMaker.to} 표시 중 - <strong>총 ${pageMaker.count}개</strong></p>
	                    		    </div>
	                    		</div>
	                    	</div>
	                    	</c:if>
	                    	<div class="table-responsive">
			                    <table class="table table-striped table-bordered table-hover table-checkable" id="datatable_orders">
		                            <thead>
		                                <tr role="row" class="heading">
		                                    <th style="width:100px;"> 고객이름 </th>
		                                    <th> 패스워드 변경 </th>
		                                    <th> 이메일 </th>
		                                    <th> 전화번호 </th>
		                                    <th> 고객 그룹 </th>
		                                    <th> 개인통관고유부호 </th>
		                                    <th> 국가 </th>
		                                    <th> My Home Doc </th>
		                                    <th> 가입경로 </th>
		                                    <th> 고객상태 </th>
		                                    <th> 가입일자 </th>
		                                </tr>
		                            </thead>
		                            <tbody>
		                            <c:forEach items="${list}" var="item">
		                            	<tr role="row">
		                            		<td style="min-width:100px;"><a href="/customer/view/${item.customer_id}.dr" target="_self">${item.customer_name}</a></td>
		                            		<td><input type="text" name="resetpwd[]" id="resetpwd" value="" class="form-control" style="max-width:100px;" data-id="${item.customer_id}" ></td>
		                            		<td class="text-align-left">${item.email}</td>
		                            		<td>${item.telephone}</td>
		                            		<td>${item.customer_group}</td>
		                            		<td>${item.requisition_id}</td>
		                            		<td>${item.country_name}</td>
		                            		<td>${ctag:getYesOrNo(item.myhomedoc)}</td>
		                            		<td>${item.join_path_name}</td>
		                            		<td>${ctag:getActive(item.status)}</td>
		                            		<td>${item.join_date}</td>
		                            	</tr>
		                            </c:forEach>
		                            </tbody>
		                        </table>
		                    </div>
	                    </div>
	                    <c:if test="${pageMaker.end>0}">
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
	            
	        </div>
	    </div>
	</div>
</div>

<%@include file="/WEB-INF/inc/footer.jspf" %>
<script type="text/javascript"><!--
// 검색
$('#button-submit').on('click', function() {
	var action = '/customers.dr';
	$('#page').val(1);
	$('#click_page').val(1);
	$("#form").attr("action", action);
	$('#form').submit();
});

// 폼 초기화
$("#button-reset").click(function() {  
    $('#customer_name').val('');
    $('#customer_group_id').val('');
    $('#join_path_id').val('');
    $('#date_added').val('');
    $('#email').val('');
    $('#myhomedoc').val('');
    $('#status').val('');
}); 

// 페이지이동
function goPage(page) {
	var action = '/customers.dr';
	if(page<1) {
		$('#page').val(1);
	} else {
		$('#page').val(page);
	}
	var page_end = $('#page_end').val();
	if(page>page_end) {
		$('#page').val(page_end);
	}
	$("#form").attr("action", action);
	$('#form').submit();
}

// 패스워드 변경
$('input[name=\'resetpwd[]\']').each(function() {
    $(this).keydown(function (e) {
        if (e.which === 13) {
            var pwd = $(this).val();
            var customer_id = $(this).data("id");
          
		    $.ajax({			   
                url: '/customers/resetpwd/'+customer_id+'.dr?pwd='+pwd,
		        dataType: 'json',
			    success: function(json) {		
				    if(json.success) {
 					   alert(json.success);
 					   $(this).val('');
				    }
			    }
		    });    
        }
    });
});
//--></script> 
<%@include file="/WEB-INF/inc/bottom.jspf" %>
