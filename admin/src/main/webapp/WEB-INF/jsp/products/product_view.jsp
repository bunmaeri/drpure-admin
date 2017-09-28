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
	            <form action="/products/save/product.dr" id="form" class="horizontal-form">
		            <div class="portlet light portlet-fit portlet-datatable bordered">
		                <div class="portlet-title">
		                    <div class="caption">
		                        <i class="icon-settings font-dark"></i>
		                        <span class="caption-subject font-dark sbold"> 제품명: ${info.name}&nbsp;&nbsp;/&nbsp;&nbsp;제품번호: ${info.product_id}</span>
		                    </div>
		                    <div class="actions">
	                    		<button type="button" class="btn dark" id="button_back"><i class="fa fa-reply"></i> BACK</button>
			                	<button type="button" id="button-submit" class="btn btn-success"><i class="fa fa-check"></i> Save</button>
			                </div>
		                </div>
	                    <div class="portlet-body">
	                        <div class="tabbable-bordered">
	                            <ul class="nav nav-tabs">
	                                <li class="active">
	                                    <a href="#tab_general" data-toggle="tab"> General </a>
	                                </li>
	                                <li>
	                                    <a href="#tab_meta" data-toggle="tab"> Meta </a>
	                                </li>
	                                <li>
	                                    <a href="#tab_images" data-toggle="tab"> Images </a>
	                                </li>
	                                <li>
	                                    <a href="#tab_reviews" data-toggle="tab"> Reviews
	                                        <span class="badge badge-success"> 3 </span>
	                                    </a>
	                                </li>
	                                <li>
	                                    <a href="#tab_history" data-toggle="tab"> History </a>
	                                </li>
	                            </ul>
	                            <div class="tab-content">
	                                <div class="tab-pane active" id="tab_general">
	                                    <div class="form-body">
	                                        <div class="form-group">
	                                            <label class="col-md-2 control-label">Name:
	                                                <span class="required"> * </span>
	                                            </label>
	                                            <div class="col-md-10">
	                                                <input type="text" class="form-control" name="product[name]" placeholder=""> </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="col-md-2 control-label">Description:
	                                                <span class="required"> * </span>
	                                            </label>
	                                            <div class="col-md-10">
	                                                <textarea class="form-control" name="product[description]"></textarea>
	                                            </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="col-md-2 control-label">Short Description:
	                                                <span class="required"> * </span>
	                                            </label>
	                                            <div class="col-md-10">
	                                                <textarea class="form-control" name="product[short_description]"></textarea>
	                                                <span class="help-block"> shown in product listing </span>
	                                            </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="col-md-2 control-label">Categories:
	                                                <span class="required"> * </span>
	                                            </label>
	                                            <div class="col-md-10">
	                                                <div class="form-control height-auto">
	                                                    <div class="scroller" style="height:275px;" data-always-visible="1">
	                                                        <ul class="list-unstyled">
	                                                            <li>
	                                                                <label>
	                                                                    <input type="checkbox" name="product[categories][]" value="1">Mens</label>
	                                                                <ul class="list-unstyled">
	                                                                    <li>
	                                                                        <label>
	                                                                            <input type="checkbox" name="product[categories][]" value="1">Footwear</label>
	                                                                    </li>
	                                                                    <li>
	                                                                        <label>
	                                                                            <input type="checkbox" name="product[categories][]" value="1">Clothing</label>
	                                                                    </li>
	                                                                    <li>
	                                                                        <label>
	                                                                            <input type="checkbox" name="product[categories][]" value="1">Accessories</label>
	                                                                    </li>
	                                                                    <li>
	                                                                        <label>
	                                                                            <input type="checkbox" name="product[categories][]" value="1">Fashion Outlet</label>
	                                                                    </li>
	                                                                </ul>
	                                                            </li>
	                                                            <li>
	                                                                <label>
	                                                                    <input type="checkbox" name="product[categories][]" value="1">Football Shirts</label>
	                                                                <ul class="list-unstyled">
	                                                                    <li>
	                                                                        <label>
	                                                                            <input type="checkbox" name="product[categories][]" value="1">Premier League</label>
	                                                                    </li>
	                                                                    <li>
	                                                                        <label>
	                                                                            <input type="checkbox" name="product[categories][]" value="1">Football League</label>
	                                                                    </li>
	                                                                    <li>
	                                                                        <label>
	                                                                            <input type="checkbox" name="product[categories][]" value="1">Serie A</label>
	                                                                    </li>
	                                                                    <li>
	                                                                        <label>
	                                                                            <input type="checkbox" name="product[categories][]" value="1">Bundesliga</label>
	                                                                    </li>
	                                                                </ul>
	                                                            </li>
	                                                            <li>
	                                                                <label>
	                                                                    <input type="checkbox" name="product[categories][]" value="1">Brands</label>
	                                                                <ul class="list-unstyled">
	                                                                    <li>
	                                                                        <label>
	                                                                            <input type="checkbox" name="product[categories][]" value="1">Adidas</label>
	                                                                    </li>
	                                                                    <li>
	                                                                        <label>
	                                                                            <input type="checkbox" name="product[categories][]" value="1">Nike</label>
	                                                                    </li>
	                                                                    <li>
	                                                                        <label>
	                                                                            <input type="checkbox" name="product[categories][]" value="1">Airwalk</label>
	                                                                    </li>
	                                                                    <li>
	                                                                        <label>
	                                                                            <input type="checkbox" name="product[categories][]" value="1">Kangol</label>
	                                                                    </li>
	                                                                </ul>
	                                                            </li>
	                                                        </ul>
	                                                    </div>
	                                                </div>
	                                                <span class="help-block"> select one or more categories </span>
	                                            </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="col-md-2 control-label">Available Date:
	                                                <span class="required"> * </span>
	                                            </label>
	                                            <div class="col-md-10">
	                                                <div class="input-group input-large date-picker input-daterange" data-date="10/11/2012" data-date-format="mm/dd/yyyy">
	                                                    <input type="text" class="form-control" name="product[available_from]">
	                                                    <span class="input-group-addon"> to </span>
	                                                    <input type="text" class="form-control" name="product[available_to]"> </div>
	                                                <span class="help-block"> availability daterange. </span>
	                                            </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="col-md-2 control-label">SKU:
	                                                <span class="required"> * </span>
	                                            </label>
	                                            <div class="col-md-10">
	                                                <input type="text" class="form-control" name="product[sku]" placeholder=""> </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="col-md-2 control-label">Price:
	                                                <span class="required"> * </span>
	                                            </label>
	                                            <div class="col-md-10">
	                                                <input type="text" class="form-control" name="product[price]" placeholder=""> </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="col-md-2 control-label">Tax Class:
	                                                <span class="required"> * </span>
	                                            </label>
	                                            <div class="col-md-10">
	                                                <select class="table-group-action-input form-control input-medium" name="product[tax_class]">
	                                                    <option value="">Select...</option>
	                                                    <option value="1">None</option>
	                                                    <option value="0">Taxable Goods</option>
	                                                    <option value="0">Shipping</option>
	                                                    <option value="0">USA</option>
	                                                </select>
	                                            </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="col-md-2 control-label">Status:
	                                                <span class="required"> * </span>
	                                            </label>
	                                            <div class="col-md-10">
	                                                <select class="table-group-action-input form-control input-medium" name="product[status]">
	                                                    <option value="">Select...</option>
	                                                    <option value="1">Published</option>
	                                                    <option value="0">Not Published</option>
	                                                </select>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                                <div class="tab-pane" id="tab_meta">
	                                    <div class="form-body">
	                                        <div class="form-group">
	                                            <label class="col-md-2 control-label">Meta Title:</label>
	                                            <div class="col-md-10">
	                                                <input type="text" class="form-control maxlength-handler" name="product[meta_title]" maxlength="100" placeholder="">
	                                                <span class="help-block"> max 100 chars </span>
	                                            </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="col-md-2 control-label">Meta Keywords:</label>
	                                            <div class="col-md-10">
	                                                <textarea class="form-control maxlength-handler" rows="8" name="product[meta_keywords]" maxlength="1000"></textarea>
	                                                <span class="help-block"> max 1000 chars </span>
	                                            </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="col-md-2 control-label">Meta Description:</label>
	                                            <div class="col-md-10">
	                                                <textarea class="form-control maxlength-handler" rows="8" name="product[meta_description]" maxlength="255"></textarea>
	                                                <span class="help-block"> max 255 chars </span>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                                <div class="tab-pane" id="tab_images">
	                                    <div class="alert alert-success margin-bottom-10">
	                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
	                                        <i class="fa fa-warning fa-lg"></i> Image type and information need to be specified. </div>
	                                    <div id="tab_images_uploader_container" class="text-align-reverse margin-bottom-10">
	                                        <a id="tab_images_uploader_pickfiles" href="javascript:;" class="btn btn-success">
	                                            <i class="fa fa-plus"></i> Select Files </a>
	                                        <a id="tab_images_uploader_uploadfiles" href="javascript:;" class="btn btn-primary">
	                                            <i class="fa fa-share"></i> Upload Files </a>
	                                    </div>
	                                    <div class="row">
	                                        <div id="tab_images_uploader_filelist" class="col-md-6 col-sm-12"> </div>
	                                    </div>
	                                    <table class="table table-bordered table-hover">
	                                        <thead>
	                                            <tr role="row" class="heading">
	                                                <th width="8%"> Image </th>
	                                                <th width="25%"> Label </th>
	                                                <th width="8%"> Sort Order </th>
	                                                <th width="10%"> Base Image </th>
	                                                <th width="10%"> Small Image </th>
	                                                <th width="10%"> Thumbnail </th>
	                                                <th width="10%"> </th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                            <tr>
	                                                <td>
	                                                    <a href="../assets/pages/media/works/img1.jpg" class="fancybox-button" data-rel="fancybox-button">
	                                                        <img class="img-responsive" src="../assets/pages/media/works/img1.jpg" alt=""> </a>
	                                                </td>
	                                                <td>
	                                                    <input type="text" class="form-control" name="product[images][1][label]" value="Thumbnail image"> </td>
	                                                <td>
	                                                    <input type="text" class="form-control" name="product[images][1][sort_order]" value="1"> </td>
	                                                <td>
	                                                    <label>
	                                                        <input type="radio" name="product[images][1][image_type]" value="1"> </label>
	                                                </td>
	                                                <td>
	                                                    <label>
	                                                        <input type="radio" name="product[images][1][image_type]" value="2"> </label>
	                                                </td>
	                                                <td>
	                                                    <label>
	                                                        <input type="radio" name="product[images][1][image_type]" value="3" checked> </label>
	                                                </td>
	                                                <td>
	                                                    <a href="javascript:;" class="btn btn-default btn-sm">
	                                                        <i class="fa fa-times"></i> Remove </a>
	                                                </td>
	                                            </tr>
	                                            <tr>
	                                                <td>
	                                                    <a href="../assets/pages/media/works/img2.jpg" class="fancybox-button" data-rel="fancybox-button">
	                                                        <img class="img-responsive" src="../assets/pages/media/works/img2.jpg" alt=""> </a>
	                                                </td>
	                                                <td>
	                                                    <input type="text" class="form-control" name="product[images][2][label]" value="Product image #1"> </td>
	                                                <td>
	                                                    <input type="text" class="form-control" name="product[images][2][sort_order]" value="1"> </td>
	                                                <td>
	                                                    <label>
	                                                        <input type="radio" name="product[images][2][image_type]" value="1"> </label>
	                                                </td>
	                                                <td>
	                                                    <label>
	                                                        <input type="radio" name="product[images][2][image_type]" value="2" checked> </label>
	                                                </td>
	                                                <td>
	                                                    <label>
	                                                        <input type="radio" name="product[images][2][image_type]" value="3"> </label>
	                                                </td>
	                                                <td>
	                                                    <a href="javascript:;" class="btn btn-default btn-sm">
	                                                        <i class="fa fa-times"></i> Remove </a>
	                                                </td>
	                                            </tr>
	                                            <tr>
	                                                <td>
	                                                    <a href="../assets/pages/media/works/img3.jpg" class="fancybox-button" data-rel="fancybox-button">
	                                                        <img class="img-responsive" src="../assets/pages/media/works/img3.jpg" alt=""> </a>
	                                                </td>
	                                                <td>
	                                                    <input type="text" class="form-control" name="product[images][3][label]" value="Product image #2"> </td>
	                                                <td>
	                                                    <input type="text" class="form-control" name="product[images][3][sort_order]" value="1"> </td>
	                                                <td>
	                                                    <label>
	                                                        <input type="radio" name="product[images][3][image_type]" value="1" checked> </label>
	                                                </td>
	                                                <td>
	                                                    <label>
	                                                        <input type="radio" name="product[images][3][image_type]" value="2"> </label>
	                                                </td>
	                                                <td>
	                                                    <label>
	                                                        <input type="radio" name="product[images][3][image_type]" value="3"> </label>
	                                                </td>
	                                                <td>
	                                                    <a href="javascript:;" class="btn btn-default btn-sm">
	                                                        <i class="fa fa-times"></i> Remove </a>
	                                                </td>
	                                            </tr>
	                                        </tbody>
	                                    </table>
	                                </div>
	                                <div class="tab-pane" id="tab_reviews">
	                                    <div class="table-container">
	                                        <table class="table table-striped table-bordered table-hover" id="datatable_reviews">
	                                            <thead>
	                                                <tr role="row" class="heading">
	                                                    <th width="5%"> Review&nbsp;# </th>
	                                                    <th width="10%"> Review&nbsp;Date </th>
	                                                    <th width="10%"> Customer </th>
	                                                    <th width="20%"> Review&nbsp;Content </th>
	                                                    <th width="10%"> Status </th>
	                                                    <th width="10%"> Actions </th>
	                                                </tr>
	                                                <tr role="row" class="filter">
	                                                    <td>
	                                                        <input type="text" class="form-control form-filter input-sm" name="product_review_no"> </td>
	                                                    <td>
	                                                        <div class="input-group date date-picker margin-bottom-5" data-date-format="dd/mm/yyyy">
	                                                            <input type="text" class="form-control form-filter input-sm" readonly name="product_review_date_from" placeholder="From">
	                                                            <span class="input-group-btn">
	                                                                <button class="btn btn-sm default" type="button">
	                                                                    <i class="fa fa-calendar"></i>
	                                                                </button>
	                                                            </span>
	                                                        </div>
	                                                        <div class="input-group date date-picker" data-date-format="dd/mm/yyyy">
	                                                            <input type="text" class="form-control form-filter input-sm" readonly name="product_review_date_to" placeholder="To">
	                                                            <span class="input-group-btn">
	                                                                <button class="btn btn-sm default" type="button">
	                                                                    <i class="fa fa-calendar"></i>
	                                                                </button>
	                                                            </span>
	                                                        </div>
	                                                    </td>
	                                                    <td>
	                                                        <input type="text" class="form-control form-filter input-sm" name="product_review_customer"> </td>
	                                                    <td>
	                                                        <input type="text" class="form-control form-filter input-sm" name="product_review_content"> </td>
	                                                    <td>
	                                                        <select name="product_review_status" class="form-control form-filter input-sm">
	                                                            <option value="">Select...</option>
	                                                            <option value="pending">Pending</option>
	                                                            <option value="approved">Approved</option>
	                                                            <option value="rejected">Rejected</option>
	                                                        </select>
	                                                    </td>
	                                                    <td>
	                                                        <div class="margin-bottom-5">
	                                                            <button class="btn btn-sm btn-success filter-submit margin-bottom">
	                                                                <i class="fa fa-search"></i> Search</button>
	                                                        </div>
	                                                        <button class="btn btn-sm btn-danger filter-cancel">
	                                                            <i class="fa fa-times"></i> Reset</button>
	                                                    </td>
	                                                </tr>
	                                            </thead>
	                                            <tbody> </tbody>
	                                        </table>
	                                    </div>
	                                </div>
	                                <div class="tab-pane" id="tab_history">
	                                    <div class="table-container">
	                                        <table class="table table-striped table-bordered table-hover" id="datatable_history">
	                                            <thead>
	                                                <tr role="row" class="heading">
	                                                    <th width="25%"> Datetime </th>
	                                                    <th width="55%"> Description </th>
	                                                    <th width="10%"> Notification </th>
	                                                    <th width="10%"> Actions </th>
	                                                </tr>
	                                                <tr role="row" class="filter">
	                                                    <td>
	                                                        <div class="input-group date datetime-picker margin-bottom-5" data-date-format="dd/mm/yyyy hh:ii">
	                                                            <input type="text" class="form-control form-filter input-sm" readonly name="product_history_date_from" placeholder="From">
	                                                            <span class="input-group-btn">
	                                                                <button class="btn btn-sm default date-set" type="button">
	                                                                    <i class="fa fa-calendar"></i>
	                                                                </button>
	                                                            </span>
	                                                        </div>
	                                                        <div class="input-group date datetime-picker" data-date-format="dd/mm/yyyy hh:ii">
	                                                            <input type="text" class="form-control form-filter input-sm" readonly name="product_history_date_to" placeholder="To">
	                                                            <span class="input-group-btn">
	                                                                <button class="btn btn-sm default date-set" type="button">
	                                                                    <i class="fa fa-calendar"></i>
	                                                                </button>
	                                                            </span>
	                                                        </div>
	                                                    </td>
	                                                    <td>
	                                                        <input type="text" class="form-control form-filter input-sm" name="product_history_desc" placeholder="To" /> </td>
	                                                    <td>
	                                                        <select name="product_history_notification" class="form-control form-filter input-sm">
	                                                            <option value="">Select...</option>
	                                                            <option value="pending">Pending</option>
	                                                            <option value="notified">Notified</option>
	                                                            <option value="failed">Failed</option>
	                                                        </select>
	                                                    </td>
	                                                    <td>
	                                                        <div class="margin-bottom-5">
	                                                            <button class="btn btn-sm btn-default filter-submit margin-bottom">
	                                                                <i class="fa fa-search"></i> Search</button>
	                                                        </div>
	                                                        <button class="btn btn-sm btn-danger-outline filter-cancel">
	                                                            <i class="fa fa-times"></i> Reset</button>
	                                                    </td>
	                                                </tr>
	                                            </thead>
	                                            <tbody> </tbody>
	                                        </table>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </form>
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
	var action = '/sales/order/save/'+$('#order_id').val()+'.dr';
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
	
    $('#order_ids').val(DATA);
	$('#form').attr('action', this.getAttribute('formAction'));
});

// BACK
$('#button_back').on('click', function(e) {
	location = '/product/back.dr';
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
    	location="/sales/order/view/"+$('#order_id').val()+".dr";
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
    	location="/sales/order/view/"+$('#order_id').val()+".dr";
      }
    }
  });
});
//--></script> 

<%@include file="/WEB-INF/inc/bottom.jspf" %>
