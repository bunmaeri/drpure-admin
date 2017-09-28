<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> 주문 <small>Sales Orders</small></h1>
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
                                        <label class="control-label">주문번호 (Order ID)</label>
                                        <input type="text" id="order_id" name="order_id" class="form-control" placeholder="" value="${param.order_id}">
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="control-label">주문상태 (Order Status)</label>
                                        <select class="form-control" id="order_status_id" name="order_status_id">
                                        	<option value=""></option>
                                        <c:forEach items="${orderStatusList}" var="item">
                                        	<option value="${item.order_status_id}" <c:if test="${item.order_status_id==param.order_status_id}">selected</c:if>>${item.name}</option>
                                        </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="control-label">주문일자 (Order Date)</label>
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
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="control-label">고객 (Customer)</label>
                                        <input type="text" id="customer_name" name="customer_name" class="form-control" placeholder="" value="${param.customer_name}">
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="control-label">배송국가 (Ship Country)</label>
                                        <select class="form-control" id="ship_country" name="ship_country">
                                            <option value=""></option>
                                            <option value="113" <c:if test="${'113'==param.ship_country}">selected</c:if>>한국(KOR)</option>
                                            <option value="223" <c:if test="${'223'==param.ship_country}">selected</c:if>>미국(USA)</option>
                                        </select>
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="control-label">ICE Box</label>
                                        <select class="form-control" id="icebox" name="icebox">
                                            <option value=""></option>
                                            <option value="1" <c:if test="${'1'==param.icebox}">selected</c:if>>ICE Box 주문</option>
                                            <option value="2" <c:if test="${'2'==param.icebox}">selected</c:if>>Except ICE Box</option>
                                        </select>
                                    </div>
                                </div>
                                <!--/span-->
                            </div>
                            <!--/row-->
                        </div>
                        <div>
	                        <div class="form-actions left" style="float:left">
	                        	<!-- <button class="btn blue" id="button_create_invoice"><i class="fa fa-pencil-square-o"></i> Create & Print Invoice</button> -->
	                        	<button type="submit" class="btn blue" id="button_print_invoice" form="form" formaction="/sales/order/create/invoice.dr" formtarget="_blank" data-toggle="tooltip"><i class="fa fa-print"></i> Create & Print Invoice</button>
	                        	<button class="btn green" id="button_export_orders"><i class="fa fa-file-excel-o"></i> Export Orders</button>
	                        	<button class="btn red" id="button_on_hold" data-type="31"><i class="fa fa-hand-stop-o"></i> On Hold</button>
	                        	<button class="btn red" id="button_ice_hold" data-type="32"><i class="fa fa-hand-stop-o"></i> ICE Hold</button>
	                        	<button class="btn blue" id="button_invoice" data-type="34"><i class="fa fa-refresh"></i> Invoice</button>
	                        	<button class="btn green" id="button_gps_gracking"><i class="fa fa-truck"></i> GPS Tracking</button>
	                        </div>
	                        <div class="form-actions right" style="padding: 20px 20px 0 0;">
	                        	<button type="button" id="button-filter-shippingfee" class="btn purple"><i class="fa fa-search"></i> 배송비 전액</button>
	                        	<button type="button" id="button-reset" class="btn black"><i class="fa fa-refresh"></i> Reset</button>
	                            <button type="button" id="button-submit" class="btn blue"><i class="fa fa-search"></i> 검색</button>
	                        </div>
                        </div>
                        <input type="hidden" name="ship_shippingfee" id="ship_shippingfee" value="">
                        <input type="hidden" name="order_ids" id="order_ids" value="">
                        <input type="hidden" name="order_status_change" id="order_status_change" value="">
                        <input type="hidden" name="page" id="page" value="${page}">
	                    
	                </div>
	            </div>
	            
	            <div class="portlet light portlet-fit portlet-datatable bordered">
	                <div class="portlet-body">
	                    <div class="table-container dataTables_wrapper no-footer">
	                    	<div id="datatable_orders_wrapper" class="dataTables_wrapper dataTables_extended_wrapper no-footer">
	                    		<div class="row">
	                    			<div class="col-md-8 col-sm-8 pull-left">
	                    				<p>${pageMaker.from} ~ ${pageMaker.to} 표시 중 - <strong>총 ${pageMaker.count}개</strong>
	                    					<span style="font-size:16px;margin-left:100px;">Invoice 상태 갯수(전체, IceBox) ==> </span><span style="font-size:18px;font-weight:600;color:darkblue;">DR(${invoice_dr}, ${invoice_ice_dr}) / MY(${invoice_my}, ${invoice_ice_my})</span>
	                    				</p>
	                    			</div>
	                    		    <div class="col-md-4 col-sm-4">
	                    		    	<div class="table-group-actions pull-right">
                                            <span></span>
                                            <select name="select_order_status_change" id="select_order_status_change" class="table-group-action-input form-control input-inline">
                                                <option value="">Select...</option>
			                                    <option value="2">Processing</option>
			                                    <option value="34">Invoice</option>
			                                    <option value="3">Shipped</option>
			                                    <option value="1">Pending</option>
			                                    <option value="31">On Hold</option>
			                                    <option value="32">Ice Hold</option>
			                                </select>
                                            <button id="button_order_status_change" class="btn btn btn-warning table-group-action-submit"><i class="fa fa-check"></i> 주문상태변경</button>
                                        </div>
	                    		    </div>
	                    		</div>
	                    	</div>
	                    	<div class="table-responsive">
			                    <table class="table table-striped table-bordered table-hover table-checkable" id="datatable_orders">
		                            <thead>
		                                <tr role="row" class="heading">
		                                    <th width="2%">
		                                        <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
		                                            <input type="checkbox" name="selectedAll" id="selectedAll" class="group-checkable"  onclick="$('input[name*=\'selected\']').prop('checked', this.checked);"/>
		                                            <span></span>
		                                        </label>
		                                    </th>
		                                    <th> 주문번호 </th>
		                                    <th> 고객 </th>
		                                    <th> 주문상태 </th>
		                                    <th> 배송 / Tracking </th>
		                                    <th> Total </th>
		                                    <th> 결제방법 </th>
		                                    <th> 배송국가 </th>
		                                    <th> IceBox </th>
		                                    <th> 개인통관번호 </th>
		                                    <th> 배송비 </th>
		                                    <th> Reward </th>
		                                    <th> 주문일자 </th>
		                                    <!-- <th>  </th> -->
		                                </tr>
		                            </thead>
		                            <tbody>
		                            <c:forEach items="${list}" var="item">
		                            	<tr role="row">
		                            		<td>
		                            			<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
		                            				<input name="selected[]" type="checkbox" class="checkboxes" value="${item.order_id}"><span></span>
		                            			</label>
		                            		</td>
		                            		<td><a href="/sales/order/view/${item.order_id}/${item.store_id}.dr" target="_self">${item.order_id}</a></td>
		                            		<td><a href="/customer/view/${item.customer_id}.dr">
		                            		    ${item.shipping_customer_name}(${item.customer_id})
		                            		    <c:if test="${item.customer_order_count>1}"><br/><span class="font-red-mint">[DUPLICATED]</span></c:if>
		                            		    <br/>(${item.email})
		                            		    </a>
		                            		</td>
		                            		<td>${ctag:getOrderStatusName(item.order_status_id)}</td>
		                            		<td>${item.carrier_name}<br/>${item.tracking}</td>
		                            		<td class="text-align-right">${ctag:getCurrency(item.total)}</td>
		                            		<td>${item.payment_method}</td>
		                            		<td>${item.iso_code_3}</td>
		                            		<td><c:if test="${item.icebox>0}"><span class="font-red-mint">ICEBOX</span></c:if></td>
		                            		<td>${item.requisition_id}</td>
		                            		<td><c:if test="${item.shippingfee_type=='1'}">전액</c:if></td>
		                            		<td class="text-align-right">${ctag:getNumber(item.reward)}</td>
		                            		<td>${item.order_date}</td>
		                            		<%-- <td><a href="/sales/order/view/${item.order_id}/${item.store_id}.dr" class="btn btn-sm btn-outline grey-salsa"><i class="fa fa-edit"></i> Edit</a></td> --%>
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
<script type="text/javascript"><!--
$('input[name^=\'selected\']').on('change', function() {
	$('#button_print_invoice').prop('disabled', true); // Create & Print Invoice
	$('#button_on_hold').prop('disabled', true); // On Hold
	$('#button_ice_hold').prop('disabled', true); // ICE Hold
	$('#button_invoice').prop('disabled', true); // Invoice
	$('#button_order_status_change').prop('disabled', true); // 주문상태변경
	var selected = $('input[name^=\'selected\']:checked');
	if (selected.length) {
		$('#button_print_invoice').prop('disabled', false);
		$('#button_on_hold').prop('disabled', false);
		$('#button_ice_hold').prop('disabled', false);
		$('#button_invoice').prop('disabled', false); // Invoice
		$('#button_order_status_change').prop('disabled', false);
	} 
});

$('#button_print_invoice').prop('disabled', true);
$('#button_on_hold').prop('disabled', true);
$('#button_ice_hold').prop('disabled', true);
$('#button_invoice').prop('disabled', true); // Invoice
$('#button_order_status_change').prop('disabled', true);

$('input[name^=\'selected\']:first').trigger('change');

// 검색
$('#button-submit').on('click', function() {
	var action = '/sales/orders.dr';
	$("#form").attr("action", action);
	$('#form').submit();
});

//폼 초기화
$("#button-reset").click(function() {  
    $('#order_id').val('');
    $('#customer_name').val('');
    $('#order_status_id').val('');
    $('#ship_country').val('');
    $('#date_added').val('');
    $('#icebox').val('');
}); 

//페이지이동
function goPage(page) {
	var action = '/sales/orders.dr';
	$('#page').val(page);
	$("#form").attr("action", action);
	$('#form').submit();
}

// 배송비 전액 주문 조회
$('#button-filter-shippingfee').on('click', function() {
	var action = '/sales/orders.dr';
	$('#ship_shippingfee').val('1');
	$("#form").attr("action", action);
	$('#form').submit();
});

// 인보이스 생성
$('#button_create_invoice').on('click', function() {
	if(!confirm("Create Invoice를 진행하시겠습니까?")) return false;
	
	var action = '/sales/order/create/invoice.dr';
	var DATA='';
	$("input[name=\'selected[]\']:checked").each(function() {
	  if($(this).val()!='on') {
    	 if(DATA!='') DATA += ",";
    	 DATA += ($(this).val());
      }
    });
	
    if(DATA=='') {
    	alert('작업할 Order를 선택하세요!');
    	return;
    }
    $('#order_ids').val(DATA);
    $("#form").attr("action", action);
	$('#form').submit();
});

// 인보이스 출력
$('#button_print_invoice').on('click', function(e) {
	if(!confirm("Create & Print Invoice를 진행하시겠습니까?")) return false;
	
	var DATA='';
	$("input[name=\'selected[]\']:checked").each(function() {
	  if($(this).val()!='on') {
    	 if(DATA!='') DATA += ",";
    	 DATA += ($(this).val());
      }
    });
	
    if(DATA=='') {
    	alert('출력할 Order를 선택하세요!');
    	return;
    }
    $('#order_ids').val(DATA);
	$('#form').attr('action', this.getAttribute('formAction'));
});

// 주문 Export
$('#button_export_orders').on('click', function() {
	var url = '/sales/orders/export.dr';
    $('#form').attr('action', url);
});

// GPS Tracking
$('#button_gps_gracking').on('click', function() {
	url = '/sales/orders/tracking.dr';
	$('#form').attr('action', url);
});

//On Hold, Ice Hold, Invoice로 주문상태 변경
$('#button_on_hold,#button_ice_hold,#button_invoice').on('click', function(e) {
	var url = '/sales/orders/status/change.dr';
	var status_type = $(this).data('type');
	var DATA='';
	$("input[name=\'selected[]\']:checked").each(function() {
	  if($(this).val()!='on') {
    	 if(DATA!='') DATA += ",";
    	 DATA += ($(this).val());
      }
    });
	
    if(DATA=='') {
    	alert('주문상태를 변경할 Order를 선택하세요!');
    	return false;
    }
    
    if(!confirm("선택한 주문의 상태변경을 진행하시겠습니까?")) return false;
	
    $('#order_ids').val(DATA);
    $('#order_status_change').val(status_type);
	$('#form').attr('action', url);
});

// 주문상태 변경
$('#button_order_status_change').on('click', function(e) {
	var url = '/sales/orders/status/change.dr';
	var DATA='';
	$("input[name=\'selected[]\']:checked").each(function() {
	  if($(this).val()!='on') {
    	 if(DATA!='') DATA += ",";
    	 DATA += ($(this).val());
      }
    });
	
    if(DATA=='') {
    	alert('주문상태를 변경할 Order를 선택하세요!');
    	return false;
    }
    
    if($('#select_order_status_change').val()=="") {
    	alert('변경할 주문상태코드를 선택하세요!');
    	$('#select_order_status_change').focus();
    	return false;
    }
    
    if(!confirm("선택한 주문의 상태변경을 진행하시겠습니까?")) return false;
	
    $('#order_ids').val(DATA);
    $('#order_status_change').val($('#select_order_status_change').val());
	$('#form').attr('action', url);
});

//--></script> 
<%@include file="/WEB-INF/inc/bottom.jspf" %>
