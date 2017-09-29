<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jspf" %>
<%@include file="/WEB-INF/inc/left_menu.jspf" %>
<div class="page-content-wrapper">
    <div class="page-content">
        <h1 class="page-title"> 주문 상세 <small>view order details</small></h1>
        ${ctag:getSuccess(successMsg)}
        ${ctag:getError(errroMsg)}
        <div class="row">
	        <div class="col-md-12">
	            <!-- Begin: life time stats -->
	            <form action="/sales/order/save/${order_id}.dr" id="form" class="horizontal-form">
	            <div class="portlet light portlet-fit portlet-datatable bordered">
	                <div class="portlet-title">
	                    <div class="caption">
	                        <i class="icon-settings font-dark"></i>
	                        <span class="caption-subject font-dark sbold uppercase"> 주문번호: #${info.order_id}
	                            <span class="hidden-xs"> | 주문일자: ${info.order_date} </span>
	                        </span>
	                    </div>
	                    <div class="actions">
                    		<button type="submit" class="btn blue" id="button_print_invoice" form="form" formaction="/sales/order/view/print/invoice.dr" formtarget="_blank" data-toggle="tooltip"><i class="fa fa-print"></i> INVOICE 출력</button>
		                	<button type="button" class="btn dark" id="button_back"><i class="fa fa-reply"></i> BACK</button>
		                </div>
	                </div>
	                <div class="portlet-body">
	                    <div class="tabbable-line">
	                            
                            <div class="row">
                                <div class="col-md-6 col-sm-12">
                                    <div class="portlet yellow-crusta box">
                                        <div class="portlet-title">
                                            <div class="caption">
                                                <i class="fa fa-cogs"></i>주문상세정보 </div>
                                        </div>
                                        <div class="portlet-body">
                                            <div class="row static-info">
                                                <div class="col-md-5 name"> 주문상태 </div>
                                                <div class="col-md-7 value"> ${info.order_status_name} </div>
                                            </div>
                                            <div class="row static-info">
                                                <div class="col-md-5 name"> 총합계 </div>
                                                <div class="col-md-7 value"> ${ctag:getCurrency(info.total)} </div>
                                            </div>
                                            <div class="row static-info">
                                                <div class="col-md-5 name"> Reward Points </div>
                                                <div class="col-md-7 value"> ${ctag:getNumber(info.points)} </div>
                                            </div>
                                            <div class="row static-info">
                                                <div class="col-md-5 name"> 결제방법 </div>
                                                <div class="col-md-7 value"> ${info.payment_method} </div>
                                            </div>
                                            <div class="row static-info">
                                                <div class="col-md-5 name"> 배송방법 </div>
                                                <div class="col-md-7 value"> ${info.shipping_method} </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-12">
                                    <div class="portlet blue-hoki box">
                                        <div class="portlet-title">
                                            <div class="caption">
                                                <i class="fa fa-cogs"></i>고객정보 </div>
                                        </div>
                                        <div class="portlet-body">
                                            <div class="row static-info">
                                                <div class="col-md-5 name"> 고객이름 </div>
                                                <div class="col-md-7 value"> ${info.customer_name} (${info.customer_grorup_name})</div>
                                            </div>
                                            <div class="row static-info">
                                                <div class="col-md-5 name"> 이메일 </div>
                                                <div class="col-md-7 value"> ${info.email} </div>
                                            </div>
                                            <div class="row static-info">
                                                <div class="col-md-5 name"> 전화번호 </div>
                                                <div class="col-md-7 value"> ${info.telephone} </div>
                                            </div>
                                            <div class="row static-info">
                                                <div class="col-md-5 name"> MyHomeDoc 고객 </div>
                                                <div class="col-md-7 value"> ${ctag:getYesOrNo(info.myhomedoc)} </div>
                                            </div>
                                            <div class="row static-info">
                                                <div class="col-md-5 name"> 가입일자 </div>
                                                <div class="col-md-7 value"> ${info.join_date} </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 col-sm-12">
                                    <div class="portlet green-meadow box">
                                        <div class="portlet-title">
                                            <div class="caption">
                                                <i class="fa fa-cogs"></i>결제 정보 </div>
                                            <div class="actions">
                                                <a href="#MODAL_payment_info" data-toggle="modal" class="btn btn-default btn-sm">
                                                    <i class="fa fa-pencil"></i> Edit </a>
                                            </div>
                                        </div>
                                        <div class="portlet-body">
                                            <div class="row static-info">
                                                <div class="col-md-12 value">
                                                	${info.payment_address}
                                                	<c:if test="${info.payment_telephone!=''}"><br/><i class="fa fa-phone-square"></i> ${info.payment_telephone}</c:if>
                                                	<c:if test="${info.payment_requisition_id!=''}"><br/><i class="fa fa-info-circle"></i> ${info.payment_requisition_id}</c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <!-- 결제정보 수정 Modal 시작 -->
                                <div id="MODAL_payment_info" class="modal fade" data-backdrop="static" data-keyboard="false" data-attention-animation="false" data-width="700">
                                	<div class="modal-header">
								        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								        <h4 class="modal-title">결제정보 변경</h4>
								    </div>
								    <div class="modal-body">
								        <div class="row">
								            <div class="col-md-12">
								                <div class="form-group">
								                    <label class="control-label">등록한 주소 목록</label>
								                    <select class="form-control" id="payment_address_id" name="payment_address_id">
								                        <option value=""></option>
														<c:forEach items="${addressList}" var="address">
							    	                    <option value="${address.address_id}" <c:if test="${address.address_id==info.payment_address_id}">selected</c:if>>[${address.customer_name}] ${address.address_1} ${address.address_2}, ${address.zone_name}, ${address.country_name}&nbsp;${address.postcode}</option>
														</c:forEach>
									                </select>
								                </div>
								            </div>
								        </div>
								        <div class="row">
								            <div class="col-md-6">
								                <div class="form-group">
								                    <label class="control-label">이름 (First Name)</label>
								                    <input type="text" id="payment_firstname" name="payment_firstname" class="form-control" value="${info.payment_firstname}"/>
								                </div>
								            </div>
								            <div class="col-md-6">
								                <div class="form-group">
								                    <label class="control-label">성 (Last Name)</label>
								                    <input type="text" id="payment_lastname" name="payment_lastname" class="form-control" value="${info.payment_lastname}"/>
								                </div>
								            </div>
								        </div>
								        <div class="row">
								            <div class="col-md-12">
								                <div class="form-group">
								                    <label class="control-label">회사이름 (Company)</label>
								                    <input type="text" id="payment_company" name="payment_company" class="form-control" value="${info.payment_company}"/>
								                </div>
								            </div>
								        </div>
								        <div class="row">
								            <div class="col-md-12">
								                <div class="form-group">
								                    <label class="control-label">전화번호 (Telephone)</label>
								                    <input type="text" id="payment_telephone" name="payment_telephone" class="form-control" value="${info.payment_telephone}"/>
								                </div>
								            </div>
								        </div>
								        <div class="row">
								            <div class="col-md-12">
								                <div class="form-group">
								                    <label class="control-label">주소 1 (Address 1)</label>
								                    <input type="text" id="payment_address_1" name="payment_address_1" class="form-control" value="${info.payment_address_1}"/>
								                </div>
								            </div>
								        </div>
								        <div class="row">
								            <div class="col-md-12">
								                <div class="form-group">
								                    <label class="control-label">주소 2 (Address 2)</label>
								                    <input type="text" id="payment_address_2" name="payment_address_2" class="form-control" value="${info.payment_address_2}"/>
								                </div>
								            </div>
								        </div>
								        <div class="row">
								            <div class="col-md-12">
								                <div class="form-group">
								                    <label class="control-label">시 (City)</label>
								                    <input type="text" id="payment_city" name="payment_city" class="form-control" value="${info.payment_city}"/>
								                </div>
								            </div>
								        </div>
								        <div class="row">
								            <div class="col-md-12">
								                <div class="form-group">
								                    <label class="control-label">우편번호 (Postcode)</label>
								                    <input type="text" id="payment_postcode" name="payment_postcode" class="form-control" value="${info.payment_postcode}"/>
								                </div>
								            </div>
								        </div>
								        <div class="row">
								            <div class="col-md-12">
								                <div class="form-group">
								                    <label class="control-label">국가 (Country)</label>
								                    <select class="form-control" id="payment_country_id" name="payment_country_id">
								                        <option value="113" <c:if test="${info.payment_country_id=='113'}">selected</c:if>>대한민국</option>
								                        <option value="223" <c:if test="${info.payment_country_id=='223'}">selected</c:if>>미국</option>
								                        <option value="44" <c:if test="${info.payment_country_id=='44'}">selected</c:if>>중국</option>
								                        <option value="107" <c:if test="${info.payment_country_id=='107'}">selected</c:if>>일본</option>
								                    </select>
								                </div>
								            </div>
								        </div>
								        <div class="row">
								            <div class="col-md-12">
								                <div class="form-group">
								                    <label class="control-label">지역 (Region / State)</label>
									                <select class="form-control" id="payment_zone_id" name="payment_zone_id">
									                    <option value=""></option>
									                    <c:forEach items="${zoneList}" var="zone">
							    	                    <option value="${zone.zone_id}" <c:if test="${zone.zone_id==info.payment_zone_id}">selected</c:if>>${zone.name}</option>
														</c:forEach>
									                </select>
								                </div>
								            </div>
								        </div>
								    </div>
								    <div class="modal-footer">
								        <button type="button" data-dismiss="modal" class="btn btn-outline dark">닫기</button>
								        <button type="button" id="button-payment-info-save" class="btn green">저장</button>
								    </div>
                                </div>
                                <!-- 결제정보 수정 Modal 종료 -->
                                
                                <div class="col-md-6 col-sm-12">
                                    <div class="portlet green-meadow box">
                                        <div class="portlet-title">
                                            <div class="caption">
                                                <i class="fa fa-cogs"></i>배송정보 </div>
                                            <div class="actions">
                                                <a href="#MODAL_shipping_info" data-toggle="modal" class="btn btn-default btn-sm">
                                                    <i class="fa fa-pencil"></i> Edit </a>
                                            </div>
                                        </div>
                                        <div class="portlet-body">
                                            <div class="row static-info">
                                                <div class="col-md-12 value">
                                                	${info.shipping_address}
                                                	<c:if test="${info.shipping_telephone!=''}"><br/><i class="fa fa-phone-square"></i> ${info.shipping_telephone}</c:if>
                                                	<c:if test="${info.requisition_id!=''}"><br/><i class="fa fa-info-circle"></i> ${info.requisition_id}</c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- 배송정보 수정 Modal 시작 -->
                                <div id="MODAL_shipping_info" class="modal fade" data-backdrop="static" data-keyboard="false" data-attention-animation="false" data-width="700">
                                	<div class="modal-header">
								        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								        <h4 class="modal-title">배송정보 변경</h4>
								    </div>
								    <div class="modal-body">
								        <div class="row">
								            <div class="col-md-12">
								                <div class="form-group">
								                    <label class="control-label">등록한 주소 목록</label>
								                    <select class="form-control" id="shipping_address_id" name="shipping_address_id">
								                        <option value=""></option>
														<c:forEach items="${addressList}" var="address">
							    	                    <option value="${address.address_id}" <c:if test="${address.address_id==info.shipping_address_id}">selected</c:if>>[${address.customer_name}] ${address.address_1} ${address.address_2}, ${address.zone_name}, ${address.country_name}&nbsp;${address.postcode}</option>
														</c:forEach>
									                </select>
								                </div>
								            </div>
								        </div>
								        <div class="row">
								            <div class="col-md-6">
								                <div class="form-group">
								                    <label class="control-label">이름 (First Name)</label>
								                    <input type="text" id="shipping_firstname" name="shipping_firstname" class="form-control" value="${info.shipping_firstname}"/>
								                </div>
								            </div>
								            <div class="col-md-6">
								                <div class="form-group">
								                    <label class="control-label">성 (Last Name)</label>
								                    <input type="text" id="shipping_lastname" name="shipping_lastname" class="form-control" value="${info.shipping_lastname}"/>
								                </div>
								            </div>
								        </div>
								        <div class="row">
								            <div class="col-md-12">
								                <div class="form-group">
								                    <label class="control-label">개인통관고유부호</label>
								                    <input type="text" id="requisition_id" name="requisition_id" class="form-control" value="${info.requisition_id}"/>
								                </div>
								            </div>
								        </div>
								        <div class="row">
								            <div class="col-md-12">
								                <div class="form-group">
								                    <label class="control-label">회사이름 (Company)</label>
								                    <input type="text" id="shipping_company" name="shipping_company" class="form-control" value="${info.shipping_company}"/>
								                </div>
								            </div>
								        </div>
								        <div class="row">
								            <div class="col-md-12">
								                <div class="form-group">
								                    <label class="control-label">전화번호 (Telephone)</label>
								                    <input type="text" id="shipping_telephone" name="shipping_telephone" class="form-control" value="${info.shipping_telephone}"/>
								                </div>
								            </div>
								        </div>
								        <div class="row">
								            <div class="col-md-12">
								                <div class="form-group">
								                    <label class="control-label">주소 1 (Address 1)</label>
								                    <input type="text" id="shipping_address_1" name="shipping_address_1" class="form-control" value="${info.shipping_address_1}"/>
								                </div>
								            </div>
								        </div>
								        <div class="row">
								            <div class="col-md-12">
								                <div class="form-group">
								                    <label class="control-label">주소 2 (Address 2)</label>
								                    <input type="text" id="shipping_address_2" name="shipping_address_2" class="form-control" value="${info.shipping_address_2}"/>
								                </div>
								            </div>
								        </div>
								        <div class="row">
								            <div class="col-md-12">
								                <div class="form-group">
								                    <label class="control-label">시 (City)</label>
								                    <input type="text" id="shipping_city" name="shipping_city" class="form-control" value="${info.shipping_city}"/>
								                </div>
								            </div>
								        </div>
								        <div class="row">
								            <div class="col-md-12">
								                <div class="form-group">
								                    <label class="control-label">우편번호 (Postcode)</label>
								                    <input type="text" id="shipping_postcode" name="shipping_postcode" class="form-control" value="${info.shipping_postcode}"/>
								                </div>
								            </div>
								        </div>
								        <div class="row">
								            <div class="col-md-12">
								                <div class="form-group">
								                    <label class="control-label">국가 (Country)</label>
								                    <select class="form-control" id="shipping_country_id" name="shipping_country_id">
								                        <option value="113" <c:if test="${info.shipping_country_id=='113'}">selected</c:if>>대한민국</option>
								                        <option value="223" <c:if test="${info.shipping_country_id=='223'}">selected</c:if>>미국</option>
								                        <option value="44" <c:if test="${info.shipping_country_id=='44'}">selected</c:if>>중국</option>
								                        <option value="107" <c:if test="${info.shipping_country_id=='107'}">selected</c:if>>일본</option>
								                    </select>
								                </div>
								            </div>
								        </div>
								        <div class="row">
								            <div class="col-md-12">
								                <div class="form-group">
								                    <label class="control-label">지역 (Region / State)</label>
								                <select class="form-control" id="shipping_zone_id" name="shipping_zone_id">
								                    <option value=""></option>
								                    <c:forEach items="${zoneList}" var="zone">
						    	                    <option value="${zone.zone_id}" <c:if test="${zone.zone_id==info.shipping_zone_id}">selected</c:if>>${zone.name}</option>
													</c:forEach>
								                </select>
								                </div>
								            </div>
								        </div>
								    </div>
								    <div class="modal-footer">
								        <button type="button" data-dismiss="modal" class="btn btn-outline dark">닫기</button>
								        <button type="button" id="button-shipping-info-save" class="btn green">저장</button>
								    </div>
                                </div>
                                <!-- 배송정보 수정 Modal 종료 -->
                            </div>
                            <div class="row">
                                <div class="col-md-12 col-sm-12">
                                    <div class="portlet red-sunglo box">
                                        <div class="portlet-title">
                                            <div class="caption">
                                                <i class="fa fa-cogs"></i>Shopping Cart </div>
                                        </div>
                                        <div class="portlet-body">
                                            <div class="table-responsive">
                                                <table class="table table-hover table-bordered table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th class="text-align-left"> 제품 </th>
                                                            <th> 제조사 </th>
                                                            <th> 모델 </th>
                                                            <th class="text-align-right"> 수량 </th>
                                                            <th class="text-align-right"> 포인트 </th>
                                                            <th class="text-align-right"> 단위가격 </th>
                                                            <th class="text-align-right"> 총합계 </th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${products}" var="product">
                                                        <tr>
                                                            <td class="text-align-left">
                                                                <a href="/product/view/info/${product.product_id}/1.dr" target="_blank"> ${product.name} </a>
                                                            </td>
                                                            <td> ${product.manufacturer_name} </td>
                                                            <td> ${product.model} </td>
                                                            <td class="text-align-right"> ${product.quantity} </td>
                                                            <td class="text-align-right"> ${ctag:getNumber(product.reward)} </td>
                                                            <td class="text-align-right"> ${ctag:getCurrency(product.price)} </td>
                                                            <td class="text-align-right"> ${ctag:getCurrency(product.total)} </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-7"> </div>
                                <div class="col-md-5">
                                    <div class="well">
                                    <c:forEach items="${totals}" var="total">
                                        <div class="row static-info align-reverse">
                                            <div class="col-md-8 name"> ${total.title}: </div>
                                            <div class="col-md-3 value"> ${ctag:getCurrency(total.value)} </div>
                                        </div>
                                    </c:forEach>
                                    </div>
                                </div>
                            </div>

							<div class="row">
                                <div class="col-md-12 col-sm-12">
                                    <div class="portlet grey-cascade box">
                                        <div class="portlet-title">
                                            <div class="caption">
                                                <i class="fa fa-cogs"></i>Order History </div>
                                        </div>
                                        <div class="portlet-body">
                                            <div class="table-responsive">
                                                <table class="table table-hover table-bordered table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th> 이력 일자 </th>
                                                            <th class="text-align-left"> 메모 </th>
                                                            <th> 주문상태 </th>
                                                            <th> 고객 알림 여부 </th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${histories}" var="history">
                                                        <tr>
                                                            <td> ${history.history_date} </td>
                                                            <td class="text-align-left"> <c:out value="${history.comment}" escapeXml="false"/> </td>
                                                            <td> ${ctag:getOrderStatusName(history.order_status_id)} </td>
                                                            <td> ${ctag:getYesOrNo(history.notify)} </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
							<div class="row">
                                <div class="col-md-12 col-sm-12">
                                    <div class="portlet grey-cascade box">
                                        <div class="portlet-title">
                                            <div class="caption">
                                                <i class="fa fa-cogs"></i>Add Order History </div>
                                        </div>
                                        <div class="portlet-body">
                                        	<div class="row static-info">
                                                <div class="col-md-2 name"> 주문상태 (Order Status) </div>
                                                <div class="col-md-10 value">
                                                	<select class="form-control" id="order_status_id" name="order_status_id">
			                                        	<option value=""></option>
			                                        <c:forEach items="${orderStatusList}" var="item">
			                                        	<option value="${item.order_status_id}" <c:if test="${item.order_status_id==param.order_status_id}">selected</c:if>>${item.name}</option>
			                                        </c:forEach>
			                                        </select>
                                                </div>
                                            </div>
                                            <div class="row static-info">
                                                <div class="col-md-2 name"> Carrier / Tracking </div>
                                                <div class="col-md-5 ">
                                                	<select class="form-control" id="carrier_id" name="carrier_id">
			                                            <option value=""></option>
			                                            <option value="gps">GPS</option>
			                                            <option value="fedex">FedEx</option>
			                                        </select>
			                                    </div>
			                                    <div class="col-md-5 ">
			                                        <input type="text" id="tracking" name="tracking" class="form-control" placeholder="" value="">
                                                </div>
                                            </div>
                                            <div class="row static-info">
                                                <div class="col-md-2 name"> 고객 알림 여부 </div>
                                                <div class="col-md-10 value">
                                                	<input type="checkbox" name="tmp_notify" id="tmp_notify" value="1"/>
                                                </div>
                                            </div>
                                            <div class="row static-info">
                                                <div class="col-md-2 name"> 메모 </div>
                                                <div class="col-md-10 value">
                                                	<textarea name="comment" id="comment" class="form-control" rows="3"></textarea>
                                                </div>
                                            </div>
                                            <div class="row">
			                                    <div class="col-md-offset-2 col-md-10">
			                                        <button type="button" id="button-add-order-history" class="btn green">진행이력 추가</button>
			                                    </div>
			                                </div>
                                        </div>
                                    </div>
                                </div>
                                
                            </div>
                            
	                    </div>
	                </div>
	            </div>
	            <input type="hidden" name="notify" id="notify" value="0"/>
	            <input type="hidden" name="store_id" id="store_id" value="${store_id}"/>
	            <input type="hidden" name="order_id" id="order_id" value="${order_id}"/>
	            <input type="hidden" name="order_ids" id="order_ids" value="${order_id}"/>
	            <input type="hidden" name="return_url" id="return_url" value="${return_url}"/>
	            </form>
	            
	            <!-- End: life time stats -->
	        </div>
	    </div>
	</div>
	<!-- END CONTENT BODY -->
</div>
<!-- END CONTENT -->

<%@include file="/WEB-INF/inc/footer.jspf" %>

<script type="text/javascript"><!--
// 주문이력 추가
$('#button-add-order-history').on('click', function() {
	var action = '/sales/order/save/'+$('#order_id').val()+'/'+$('#store_id').val()+'.dr';
	if($('#order_status_id').val()=="") {
		alert("주문상태를 선택하세요.");
		$('#order_status_id').focus();
		return false;
	}
	if($('#tmp_notify').is(":checked")) {
		$('#notify').val('1');
	}
	$("#form").attr("action", action);
	$('#form').submit();
});

// 인보이스 출력
$('#button_print_invoice').on('click', function(e) {
	if(!confirm("INVOICE 출력을 진행하시겠습니까?")) return false;
	
    //$('#order_ids').val(DATA);
	$('#form').attr('action', this.getAttribute('formAction'));
});

// BACK
$('#button_back').on('click', function(e) {
	location = '/sales/order/back.dr';
});

// 결제정보에서 address_id 변경될 때
$('select[name=\'payment_address_id\']').on('change', function() {
	$.ajax({
		url: '/sales/order/change/address/' + this.value+'.dr',
		dataType: 'json',
		beforeSend: function() {
			$('select[name=\'payment_address_id\']').after(' <i class="fa fa-circle-o-notch fa-spin"></i>');
		},
		complete: function() {
			$('#MODAL_payment_info .fa-spin').remove();
		},
		success: function(json) {
			// Reset all fields
			$('#MODAL_payment_info input[type=\'text\'], #MODAL_payment_info textarea').val('');
			$('#MODAL_payment_info select[name=\'payment_country_id\']').removeAttr('selected');
			$('#MODAL_payment_info select[name=\'payment_zone_id\']').removeAttr('selected');
			$('#MODAL_payment_info input[type=\'checkbox\'], #MODAL_payment_info input[type=\'radio\']').removeAttr('checked');

			$('select[name=\'payment_address_id\']').val(json.address.address_id);
			$('#MODAL_payment_info input[name=\'payment_firstname\']').val(json.address.firstname);
			$('#MODAL_payment_info input[name=\'payment_lastname\']').val(json.address.lastname);
			$('#MODAL_payment_info input[name=\'payment_company\']').val(json.address.company);
			$('#MODAL_payment_info input[name=\'payment_telephone\']').val(json.address.telephone);
			$('#MODAL_payment_info input[name=\'payment_address_1\']').val(json.address.address_1);
			$('#MODAL_payment_info input[name=\'payment_address_2\']').val(json.address.address_2);
			$('#MODAL_payment_info input[name=\'payment_city\']').val(json.address.city);
			$('#MODAL_payment_info input[name=\'payment_postcode\']').val(json.address.postcode);
			$('#MODAL_payment_info select[name=\'payment_country_id\']').val(json.address.country_id);

			$('#MODAL_payment_info select[name=\'payment_country_id\']').trigger('change');
			
			$('#MODAL_payment_info select[name=\'payment_zone_id\']').html(json.zone);
		},
		error: function(xhr, ajaxOptions, thrownError) {
			alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
		}
	});
});

// 결제정보에서 country_id 변경될 때
$('select[name=\'payment_country_id\']').on('change', function() {
	$.ajax({
		url: '/sales/order/change/country/' + this.value+'.dr',
		dataType: 'json',
		beforeSend: function() {
			$('select[name=\'payment_country_id\']').after(' <i class="fa fa-circle-o-notch fa-spin"></i>');
		},
		complete: function() {
			$('#MODAL_payment_info .fa-spin').remove();
		},
		success: function(json) {
			$('#MODAL_payment_info select[name=\'payment_zone_id\']').html(json.zone);
		},
		error: function(xhr, ajaxOptions, thrownError) {
			alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
		}
	});
});

// 결제정보 저장
$('#button-payment-info-save').on('click', function() {
  var _params = 'address_id='+$("#payment_address_id option:checked").val()
  +'&firstname='+$("#payment_firstname").val()+'&lastname='+$("#payment_lastname").val()+'&company='+$("#payment_company").val()+'&telephone='+$("#payment_telephone").val()
  +'&address_1='+$("#payment_address_1").val()+'&address_2='+$("#payment_address_2").val()+'&city='+$("#payment_city").val()+'&postcode='+$("#payment_postcode").val()
  +'&country='+$("#payment_country_id option:checked").text()
  +'&country_id='+$("#payment_country_id option:checked").val()
  +'&zone='+$("#payment_zone_id option:checked").text()
  +'&zone_id='+$("#payment_zone_id option:checked").val();
  //alert(_params);
  var _url = '/sales/order/paymentinfo/save/'+$('#order_id').val()+'.dr';
  $.ajax({
    url: _url,
    type: 'post',
    dataType: 'json',
    data: _params,
    beforeSend: function() {
      $('#button-payment-info-save').after(' <i class="fa fa-circle-o-notch fa-spin"></i>');
    },
    complete: function() {
    	$('#MODAL_payment_info .fa-spin').remove();
    },
    success: function(json) {
      if (json.success) {
    	alert(json.success);
    	location="/sales/order/view/"+$('#order_id').val()+"/"+$('#store_id').val()+".dr";
      }
    }
  });
});

// 배송정보에서 address_id 변경될 때
$('select[name=\'shipping_address_id\']').on('change', function() {
	$.ajax({
		url: '/sales/order/change/address/' + this.value+'.dr',
		dataType: 'json',
		beforeSend: function() {
			$('select[name=\'shipping_address_id\']').after(' <i class="fa fa-circle-o-notch fa-spin"></i>');
		},
		complete: function() {
			$('#MODAL_shipping_info .fa-spin').remove();
		},
		success: function(json) {
			// Reset all fields
			$('#MODAL_shipping_info input[type=\'text\'], #MODAL_shipping_info textarea').val('');
			$('#MODAL_shipping_info select[name=\'shipping_country_id\']').removeAttr('selected');
			$('#MODAL_shipping_info select[name=\'shipping_zone_id\']').removeAttr('selected');
			$('#MODAL_shipping_info input[type=\'checkbox\'], #MODAL_shipping_info input[type=\'radio\']').removeAttr('checked');

			$('select[name=\'shipping_address_id\']').val(json.address.address_id);
			$('#MODAL_shipping_info input[name=\'shipping_firstname\']').val(json.address.firstname);
			$('#MODAL_shipping_info input[name=\'shipping_lastname\']').val(json.address.lastname);
			$('#MODAL_shipping_info input[name=\'shipping_company\']').val(json.address.company);
			$('#MODAL_shipping_info input[name=\'shipping_telephone\']').val(json.address.telephone);
			$('#MODAL_shipping_info input[name=\'shipping_address_1\']').val(json.address.address_1);
			$('#MODAL_shipping_info input[name=\'shipping_address_2\']').val(json.address.address_2);
			$('#MODAL_shipping_info input[name=\'shipping_city\']').val(json.address.city);
			$('#MODAL_shipping_info input[name=\'shipping_postcode\']').val(json.address.postcode);
			$('#MODAL_shipping_info select[name=\'shipping_country_id\']').val(json.address.country_id);

			$('#MODAL_shipping_info select[name=\'shipping_country_id\']').trigger('change');
			
			$('#MODAL_shipping_info select[name=\'shipping_zone_id\']').html(json.zone);
		},
		error: function(xhr, ajaxOptions, thrownError) {
			alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
		}
	});
});

// 배송정보에서 country_id 변경될 때
$('select[name=\'shipping_country_id\']').on('change', function() {
	$.ajax({
		url: '/sales/order/change/country/' + this.value+'.dr',
		dataType: 'json',
		beforeSend: function() {
			$('select[name=\'shipping_country_id\']').after(' <i class="fa fa-circle-o-notch fa-spin"></i>');
		},
		complete: function() {
			$('#MODAL_shipping_info .fa-spin').remove();
		},
		success: function(json) {
			$('#MODAL_shipping_info select[name=\'shipping_zone_id\']').html(json.zone);
		},
		error: function(xhr, ajaxOptions, thrownError) {
			alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
		}
	});
});

// 배송정보 저장
$('#button-shipping-info-save').on('click', function() {
  var _params = 'address_id='+$("#shipping_address_id option:checked").val()
  +'&firstname='+$("#shipping_firstname").val()+'&lastname='+$("#shipping_lastname").val()
  +'&requisition_id='+$("#requisition_id").val()
  +'&company='+$("#shipping_company").val()+'&telephone='+$("#shipping_telephone").val()
  +'&address_1='+$("#shipping_address_1").val()+'&address_2='+$("#shipping_address_2").val()+'&city='+$("#shipping_city").val()+'&postcode='+$("#shipping_postcode").val()
  +'&country='+$("#shipping_country_id option:checked").text()
  +'&country_id='+$("#shipping_country_id option:checked").val()
  +'&zone='+$("#shipping_zone_id option:checked").text()
  +'&zone_id='+$("#shipping_zone_id option:checked").val();
  //alert(_params);
  var _url = '/sales/order/shippinginfo/save/'+$('#order_id').val()+'.dr';
  $.ajax({
    url: _url,
    type: 'post',
    dataType: 'json',
    data: _params,
    beforeSend: function() {
      $('#button-shipping-info-save').after(' <i class="fa fa-circle-o-notch fa-spin"></i>');
    },
    complete: function() {
    	$('#MODAL_shipping_info .fa-spin').remove();
    },
    success: function(json) {
      if (json.success) {
    	alert(json.success);
    	location="/sales/order/view/"+$('#order_id').val()+"/"+$('#store_id').val()+".dr";
      }
    }
  });
});
//--></script> 

<%@include file="/WEB-INF/inc/bottom.jspf" %>
