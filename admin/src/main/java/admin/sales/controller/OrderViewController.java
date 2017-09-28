package admin.sales.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import admin.common.common.CommandMap;
import admin.common.constant.Session;
import admin.common.controller.BaseController;
import admin.common.controller.CodeController;
import admin.common.service.CodeService;
import admin.common.service.CommonService;
import admin.common.util.ObjectUtils;
import admin.common.util.OrderUtils;
import admin.common.util.ScriptUtils;
import admin.common.util.StoreUtils;
import admin.customers.service.CustomersService;
import admin.sales.service.OrdersService;

@Controller
public class OrderViewController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="ordersService")
	private OrdersService ordersService;
	
	@Resource(name="codeService")
	private CodeService codeService;
	
	@Resource(name="customersService")
	private CustomersService customersService;
	
	@Resource(name="commonService")
	private CommonService commonService;

	private CodeController code = new CodeController();
	
	/**
	 * 주문 상세 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/sales/order/view/{order_id}/{store_id}.dr")
    public ModelAndView salesOrderView(HttpServletRequest request, @PathVariable String order_id, @PathVariable String store_id, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/sales/order_view");
    
    	commandMap.put("table_prefix", StoreUtils.getTablePrefix(store_id));
    	mv.addObject("store_id", store_id);
    	
    	OrderUtils outil = new OrderUtils();
    	commandMap.put("order_id", order_id);
    	/**
    	 * 주문 상세정보 조회
    	 */
    	Map<String,Object> map = ordersService.orderView(commandMap.getMap());
    	if(null==map || null==map.get("order_id")) {
        	mv.addObject("order_id", order_id);
        	
        	ScriptUtils.salesOrderViewScript(mv);
        
    		return mv;
    	}
    	map.put("payment_address", outil.orderHistoryAddress("payment_", map));
    	map.put("shipping_address", outil.orderHistoryAddress("shipping_", map));
    	mv.addObject("info", map);

    	/**
    	 * 주문한 제품 목록 조회
    	 */
    	List<Map<String,Object>> products = ordersService.orderProducts(commandMap.getMap());
    	mv.addObject("products", products);
    	
    	/**
    	 * 주문 합계를 목록 조회한다.
    	 */
    	List<Map<String,Object>> totals = ordersService.orderTotals(commandMap.getMap());
    	mv.addObject("totals", totals);
   
    	/**
    	 * 주문 이력을 목록 조회한다.
    	 */
    	List<Map<String,Object>> histories = ordersService.orderHistories(commandMap.getMap());
    	mv.addObject("histories", histories);

    	// 주문상태코드 목록
    	List<Map<String, Object>> ORSER_STATUS_LIST = codeService.getOrderStatus(StoreUtils.getLanguageIdString());
    	mv.addObject("orderStatusList", ORSER_STATUS_LIST);
    	
    	/**
    	 * 주소를 목록 조회한다.
    	 */
    	List<Map<String,Object>> addressList = customersService.addressList(ObjectUtils.null2void(map.get("customer_id")));
    	mv.addObject("addressList", addressList);
    	
    	/**
    	 * 국가별 zone 목록
    	 */
    	List<Map<String,Object>> zoneList = commonService.zonesPerCountry(ObjectUtils.null2void(map.get("payment_country_id")));
    	mv.addObject("zoneList", zoneList);
    	
    	mv.addObject("order_id", order_id);
    	
    	ScriptUtils.salesOrderViewScript(mv);

    	return mv;
    }
	
	/**
	 * 진행 이력 생성
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sales/order/save/{order_id}/{store_id}.dr")
    public ModelAndView addOrderHistory(HttpServletRequest request, @PathVariable String order_id, @PathVariable String store_id, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("redirect:/sales/order/view/"+order_id+"/"+store_id+".dr");
    	
    	commandMap.put("table_prefix", StoreUtils.getTablePrefix(store_id));
    	
    	String order_status_list = "2,34,3,1,31,32,"; // 정상 상태코
    	String cancel_status_list = "16,7,11,"; // 취소 상태코드
    	// 주문 정보 조회
    	Map<String,Object> map = ordersService.orderView(commandMap.getMap());
    	String db_order_status_id = ObjectUtils.null2void(map.get("order_status_id"))+",";
    	String param_order_status_id = commandMap.get2String("order_status_id");
    	// 정상 상태코드에서 취소 코드로 변경되면 제품 수량에 플러스 해준다.
    	if(order_status_list.contains(db_order_status_id) && cancel_status_list.contains(param_order_status_id)) {
    		List<Map<String,Object>> orderProductList = ordersService.orderProducts(commandMap.getMap());
    		Map<String,Object> orderProductMap = null;
    		int size = orderProductList.size();
    		for(int i=0;i<size;i++) {
    			orderProductMap = orderProductList.get(i);
    			/**
    			 * 제품 수량 plus
    			 * 제품 수량 조회해서 0이면 재고 상태코드를 5(재고 없음)으로 업데이트
    			 * 제품 수량이 0보다 작으면 재고 상태코드를 5(재고 없음)으로 업데이트하고 제품 수량을 0으로 셋팅
    			 */
    			if(null!=orderProductMap) {
	    			orderProductMap.put("plusOrMinus", "+");
	    			ordersService.updateProductQuantity(orderProductMap);
    			}
    		}
    	} else
    	// 취소 상태코드에서 정상 상태 코드로 변경되면 제품 수량에 마이너스 해준다.
		if(order_status_list.contains(param_order_status_id) && cancel_status_list.contains(db_order_status_id)) {
			List<Map<String,Object>> orderProductList = ordersService.orderProducts(commandMap.getMap());
    		Map<String,Object> orderProductMap = null;
    		int size = orderProductList.size();
    		for(int i=0;i<size;i++) {
    			orderProductMap = orderProductList.get(i);
    			/**
    			 * 제품 수량 minus
    			 * 제품 수량 조회해서 0이면 재고 상태코드를 5(재고 없음)으로 업데이트
    			 * 제품 수량이 0보다 작으면 재고 상태코드를 5(재고 없음)으로 업데이트하고 제품 수량을 0으로 셋팅
    			 */
    			if(null!=orderProductMap) {
	    			orderProductMap.put("plusOrMinus", "-");
	    			ordersService.updateProductQuantity(orderProductMap);
    			}
    		}
		}
    	
    	ordersService.updateOrderStatus(commandMap.getMap()); // 주문상태코드 업데이트
    	
    	ordersService.addOrderHistory(commandMap.getMap()); // 주문 이력 생성
    	
    	mv.addObject("order_id", order_id);
    	
    	return mv;
    }
	
	/**
	 * 주소 ID 변경
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sales/order/change/address/{address_id}.dr", produces = "application/json; charset=utf8")
    public ModelAndView changeAddressId(HttpServletRequest request, @PathVariable String address_id, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		
		/**
		 * 주소 조회
		 */
		Map<String,Object> address = customersService.address(address_id);
		mv.addObject("address", address);
		
		List<Map<String,Object>> zoneList = commonService.zonesPerCountry(ObjectUtils.null2void(address.get("country_id")));
		int size = zoneList.size();
		Map<String,Object> zone = null;
		StringBuffer zoneBuff = new StringBuffer();
		for(int i=0;i<size;i++) {
			zone = zoneList.get(i);
			zoneBuff.append("<option value='").append(zone.get("zone_id")).append("'>");
			if(zone.get("zone_id").equals(address.get("zone_id"))) zoneBuff.append(" selected ");
			zoneBuff.append(zone.get("name")).append("</option>");
		}
		mv.addObject("zone", zoneBuff.toString());
		
		return mv;
    }
	
	/**
	 * 국가 변경
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sales/order/change/country/{country_id}.dr", produces = "application/json; charset=utf8")
    public ModelAndView changeCountryId(HttpServletRequest request, @PathVariable String country_id, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		
		List<Map<String,Object>> zoneList = commonService.zonesPerCountry(country_id);
		int size = zoneList.size();
		Map<String,Object> zone = null;
		StringBuffer zoneBuff = new StringBuffer();
		for(int i=0;i<size;i++) {
			zone = zoneList.get(i);
			zoneBuff.append("<option value='").append(zone.get("zone_id")).append("'>");
			zoneBuff.append(zone.get("name")).append("</option>");
		}
		mv.addObject("zone", zoneBuff.toString());
		
		return mv;
    }
	
	/**
	 * 결제정보 저장
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sales/order/paymentinfo/save/{order_id}.dr")
    public ModelAndView savePaymentInfo(HttpServletRequest request, @PathVariable String order_id, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
//    System.err.println(commandMap.getMap().get("address_id"));
		String customer_name = ObjectUtils.null2void(commandMap.getMap().get("firstname"));
		if(null!=commandMap.getMap().get("lastname") && !commandMap.getMap().get("lastname").equals("")) {
			customer_name = customer_name+" "+ObjectUtils.null2void(commandMap.getMap().get("lastname"));
		}
		commandMap.put("customer_name", customer_name);
		commandMap.put("zone_id", ObjectUtils.null2Value(commandMap.getMap().get("zone_id"),"0"));
		commandMap.put("address_id", ObjectUtils.null2void(commandMap.getMap().get("address_id")));
		commandMap.put("order_id", order_id);
		ordersService.updateOrderPaymentInfo(commandMap.getMap()); // 결제정보 저장
		
		if(null!=commandMap.getMap().get("adress_id") && !commandMap.getMap().get("adress_id").equals("")) {
			ordersService.updateAddress(commandMap.getMap()); // 주소정보 저장
		}
    	
       	mv.addObject("success", "결제정보가 저장되었습니다.");
    	mv.addObject("order_id", order_id);
    	
    	return mv;
    }
	
	/**
	 * 배송정보 저장
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sales/order/shippinginfo/save/{order_id}.dr")
    public ModelAndView saveShippingInfo(HttpServletRequest request, @PathVariable String order_id, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");

		String customer_name = ObjectUtils.null2void(commandMap.getMap().get("firstname"));
		if(null!=commandMap.getMap().get("lastname") && !commandMap.getMap().get("lastname").equals("")) {
			customer_name = customer_name+" "+ObjectUtils.null2void(commandMap.getMap().get("lastname"));
		}
		commandMap.put("customer_name", customer_name);
		commandMap.put("zone_id", ObjectUtils.null2Value(commandMap.getMap().get("zone_id"),"0"));
		commandMap.put("address_id", ObjectUtils.null2void(commandMap.getMap().get("address_id")));
		commandMap.put("order_id", order_id);
		ordersService.updateOrderShippingInfo(commandMap.getMap()); // 배송정보 저장
		
		if(null!=commandMap.getMap().get("adress_id") && !commandMap.getMap().get("adress_id").equals("")) {
			ordersService.updateAddress(commandMap.getMap()); // 주소정보 저장
		}
    	
       	mv.addObject("success", "배송정보가 저장되었습니다.");
    	mv.addObject("order_id", order_id);
    	
    	return mv;
    }
	
	/**
	 * 인보이스 생성
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sales/order/view/create/invoice.dr")
    public ModelAndView createInvoice(CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("redirect:/sales/order/view/print/invoice.dr");
    	
    	commandMap.put("table_prefix", StoreUtils.getTablePrefix(commandMap.get2String("store_id")));
    	
    	String from_order_status_id = "2"; // Processing
    	String to_order_status_id = "34"; // Invoice
//    	System.err.println("commandMap.get2String(order_ids)====================>"+commandMap.get2String("order_ids"));
    	String[] array = commandMap.get2String("order_ids").split(",");
    	
    	commandMap.put("order_status_id", from_order_status_id);
    	commandMap.put("array", array);
    	List<Map<String,Object>> list = ordersService.listOrderForAction(commandMap.getMap());
    	
    	Map<String,Object> map = null;
    	int size = list.size();
//    	System.err.println("size==========================>"+size);
    	for(int i=0;i<size;i++) {
    		map = list.get(i);
    		map.put("order_status_id", to_order_status_id);
    		ordersService.updateOrderStatus(map); // 주문상태코드 업데이트 (Invoice)
    		
//    		ordersService.orderTotals(map);
    		if(ordersService.countCustomerRewardForDuplication(map)<1) {
	    		map.put("description", "Order ID: #"+map.get("order_id"));
	    		ordersService.addCustomerReward(map); // 고객 Reward 추가
    		}
    		
    		map.put("notify", "0");
    		map.put("comment", "Create Invoice No");
    		ordersService.addOrderHistory(map); // 주문 이력 생성
    		
    		map.put("invoice_prefix", code.getValue("config_invoice_prefix"));
    		ordersService.createInvoiceNo(map); // 인보이스 생성
    	}
    	
        mv.addObject("order_ids", commandMap.get("order_ids"));
        mv.addObject("order_status_id", to_order_status_id);
        mv.addObject("store_id", commandMap.get2String("store_id"));
    	return mv;
    }

	/**
	 * 인보이스 출력
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sales/order/view/print/invoice.dr")
    public ModelAndView printInvoiceOne(HttpServletRequest request, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/sales/orders_invoice");
    	
    	commandMap.put("table_prefix", StoreUtils.getTablePrefix(commandMap.get2String("store_id")));
//    	String[] array = commandMap.get2String("order_ids").split(",");
//    	commandMap.put("array", array);
    	List<Map<String,Object>> list = ordersService.printInvoiceOne(commandMap.getMap());
    	
    	mv.addObject("list", list);
    	mv.addObject("store_id", commandMap.get2String("store_id"));
    	
    	return mv;
    }
	
	/**
	 * BACK
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sales/order/back/backup.dr")
    public ModelAndView back(HttpServletRequest request, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("redirect:/sales/orders.dr");

    	// 마지막 검색 조건을 확인해서 있으면 그쪽으로 없으면..
    	if(null!=BaseController.getCustomSession(request, Session.SALES_ORDERS_URL)) {
    		String return_url = ObjectUtils.null2void(BaseController.getCustomSession(request, Session.SALES_ORDERS_URL));
    		BaseController.setCustomSession(request, null, Session.SALES_ORDERS_URL);
    		return new ModelAndView("redirect:"+return_url);
    	}
    	
    	return mv;
    }

}