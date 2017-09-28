package admin.sales.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import admin.common.common.CommandMap;
import admin.common.constant.Session;
import admin.common.controller.BaseController;
import admin.common.controller.CodeController;
import admin.common.email.GpsTrackingEmail;
import admin.common.email.MailChimpEmail;
import admin.common.service.CodeService;
import admin.common.util.CommonUtils;
import admin.common.util.ObjectUtils;
import admin.common.util.Pagemaker;
import admin.common.util.ScriptUtils;
import admin.common.util.StoreUtils;
import admin.common.util.XmlParsering;
import admin.sales.service.OrdersService;

@Controller
public class OrderController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="ordersService")
	private OrdersService ordersService;
	
	@Resource(name="codeService")
	private CodeService codeService;

	private CodeController code = new CodeController();

	/**
	 * TOP에서 주문번호로 검색했을 때..
	 * @param request
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/search/top.dr")
    public ModelAndView searchTop(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = null;
		
		String order_id = ObjectUtils.null2void(commandMap.get("top_order_id"));
		List<Map<String,Object>> list = ordersService.searchOrder(order_id);
		int size = list.size();
		if(size==0) {
			mv = new ModelAndView("redirect:/sales/orders.dr");
			return mv;
		} else
		if(size==1) {
			Map<String,Object> order = list.get(0);
			mv = new ModelAndView("redirect:/sales/order/view/"+order.get("order_id")+"/"+order.get("store_id")+".dr");
			mv.addObject("order_id", order_id);
			return mv;
		} else {
			mv = new ModelAndView("redirect:/sales/orders.dr");
			mv.addObject("order_id", order_id);
			return mv;
		}
	}

	/**
	 * 주문 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sales/orders.dr")
    public ModelAndView salesOrder(HttpServletRequest request, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/sales/orders");
    	
    	String array = "";
    	if(null!=BaseController.getCustomSession(request, Session.ORDER_IDS)) {
    		commandMap.put("array", BaseController.getCustomSession(request, Session.ORDER_IDS));
    		BaseController.setCustomSession(request, null, Session.ORDER_IDS);
    		array = "Y";
    	}
   
    	String order_status_id = ObjectUtils.null2void(commandMap.getMap().get("order_status_id"));
    	String order_id = ObjectUtils.null2void(commandMap.getMap().get("order_id"));
    	String customer_name = ObjectUtils.null2void(commandMap.getMap().get("customer_name"));
    	String date_added = ObjectUtils.null2void(commandMap.getMap().get("date_added"));
    	String ship_country = ObjectUtils.null2void(commandMap.getMap().get("ship_country"));
    	String icebox = ObjectUtils.null2void(commandMap.getMap().get("icebox"));
    	String page = ObjectUtils.null2Value(commandMap.getMap().get("page"),"1");
//    	System.err.println("============================================"+icebox);
    	// 검색 조건이 하나도 없으면 검색을 하지 않는다.
    	if(order_status_id.equals("") && order_id.equals("") && customer_name.equals("") && date_added.equals("") && ship_country.equals("") && icebox.equals("") && array.equals("")) {
    	} else {
    		int count = 0;
	    	pagemaker.setPage(pagemaker.getPage());
	    	commandMap.put("language_id", StoreUtils.getLanguageId());
	    	count = ordersService.totalOrder(commandMap.getMap()); // 레코드 총 갯수 구함
	    	pagemaker.setCount(count); // 페이지 계산

	    	commandMap.put("page", (pagemaker.getPage()-1)*pagemaker.getPER());
	    	commandMap.put("per_page", pagemaker.getPER());
	    	List<Map<String,Object>> list = ordersService.listOrder(commandMap.getMap());
	    	mv.addObject("list", list);
	    	if(list.size() > 0){
	    		mv.addObject("pageMaker", pagemaker); 
	    	}
    	}
    	// 주문상태코드 목록
    	List<Map<String, Object>> ORSER_STATUS_LIST = codeService.getOrderStatus(StoreUtils.getLanguageIdString());
    	mv.addObject("orderStatusList", ORSER_STATUS_LIST);
    	mv.addObject("param", commandMap.getMap());
    	mv.addObject("page", page);
    	
    	// Invoice 상태의 주문 수량
    	List<Map<String, Object>> list = ordersService.invoiceOrderCnt(commandMap.getMap());
        int size = list.size();
        Map<String,Object> map = null;
        String invoice_dr = "0";
        String invoice_my = "0";
        for(int i=0;i<size;i++) {
        	map = list.get(i);
        	if(ObjectUtils.null2void(map.get("store_id")).equals("0")) {
        		invoice_dr = ObjectUtils.null2Value(map.get("cnt"), "0");
        	}
        	if(ObjectUtils.null2void(map.get("store_id")).equals("1")) {
        		invoice_my = ObjectUtils.null2Value(map.get("cnt"), "0");
        	}
        }
        
        // Invoice 상태의 주문 수량(ICE)
    	list = ordersService.invoiceOrderCntIce(commandMap.getMap());
        size = list.size();
        map = null;
        String invoice_ice_dr = "0";
        String invoice_ice_my = "0";
        for(int i=0;i<size;i++) {
        	map = list.get(i);
        	if(ObjectUtils.null2void(map.get("store_id")).equals("0")) {
        		invoice_ice_dr = ObjectUtils.null2Value(map.get("cnt"), "0");
        	}
        	if(ObjectUtils.null2void(map.get("store_id")).equals("1")) {
        		invoice_ice_my = ObjectUtils.null2Value(map.get("cnt"), "0");
        	}
        }
        mv.addObject("invoice_dr", invoice_dr);
        mv.addObject("invoice_ice_dr", invoice_ice_dr);
        mv.addObject("invoice_my", invoice_my);
        mv.addObject("invoice_ice_my", invoice_ice_my);
    
    	ScriptUtils.salesOrdersScript(mv);
    
    	// 에러가 있을 때(Excel Export, GPS Tracking)
    	if(null!=BaseController.getCustomSession(request, Session.SALES_ORDERS_ERROR)) {
    		mv.addObject("errroMsg", BaseController.getCustomSession(request, Session.SALES_ORDERS_ERROR));
    		BaseController.setCustomSession(request, null, Session.SALES_ORDERS_ERROR);
    	}
    	// 성공 메세지가 있을 때(Excel Export, GPS Tracking)
    	if(null!=BaseController.getCustomSession(request, Session.SALES_ORDERS_SUCCESS)) {
    		mv.addObject("successMsg", BaseController.getCustomSession(request, Session.SALES_ORDERS_SUCCESS));
    		BaseController.setCustomSession(request, null, Session.SALES_ORDERS_SUCCESS);
    	}
    	
    	// Return URL
    	BaseController.setCustomSession(request, CommonUtils.setReturnUrl(request), Session.SALES_ORDERS_URL);
    	
    	return mv;
    }
	
	/**
	 * 인보이스 생성
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sales/order/create/invoice.dr")
    public ModelAndView createInvoice(CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("redirect:/sales/order/print/invoice.dr");
    	
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
    		map.put("table_prefix", StoreUtils.getTablePrefix(ObjectUtils.null2void(map.get("store_id"))));
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
	@RequestMapping(value="/sales/order/print/invoice.dr")
    public ModelAndView printInvoice(HttpServletRequest request, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/sales/orders_invoice");
    
    	String[] array = commandMap.get2String("order_ids").split(",");
    	commandMap.put("array", array);
    	commandMap.put("table_prefix", "dr");
    	commandMap.put("store_id", "0");
    	commandMap.put("iceYn", "not");
    	List<Map<String,Object>> drlist = ordersService.printInvoiceByIce(commandMap.getMap());
    
    	commandMap.put("table_prefix", "my");
    	commandMap.put("store_id", "1");
    	List<Map<String,Object>> mylist = ordersService.printInvoiceByIce(commandMap.getMap());
    	
    	commandMap.put("table_prefix", "dr");
    	commandMap.put("store_id", "0");
    	commandMap.put("iceYn", "ice");
    	List<Map<String,Object>> drIcelist = ordersService.printInvoiceByIce(commandMap.getMap());
    	
    	commandMap.put("table_prefix", "my");
    	commandMap.put("store_id", "1");
    	List<Map<String,Object>> myIcelist = ordersService.printInvoiceByIce(commandMap.getMap());
    	
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	list.addAll(drlist);
    	list.addAll(mylist);
    	list.addAll(drIcelist);
    	list.addAll(myIcelist);
    	
    	mv.addObject("list", list);
//    	mv.addObject("store_id", commandMap.get2String("store_id"));
    	
    	return mv;
    }
	
	/**
	 * GPS Tracking
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sales/orders/tracking.dr")
    public ModelAndView gpsTracking(HttpServletRequest request, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("redirect:/sales/orders.dr");
    	
    	String gps_api_url = "http://www.gpslgx.com/tracking.xml?member_userid=drpure&member_password=BMax10333&order_number=";
    	
    	String order_status_id = "34";
    	String shipping_country_id = "113";
    	commandMap.put("order_status_id", order_status_id);
        commandMap.put("shipping_country_id", shipping_country_id);
    	List<Map<String,Object>> list = ordersService.listOrderForTracking(commandMap.getMap());
    	int size = list.size();
    	Map<String,Object> order = null;
    	String tracking_number = null;
    	
    	String telephone = "";
    	StringBuffer errSb = new StringBuffer();
    	StringBuffer orderSb = new StringBuffer();
    	for(int i=0;i<size;i++) {
    		order = list.get(i);
    		orderSb.append("#").append(order.get("order_id"));
    		// 수신자 전화번호 체크
    		if(null!=order.get("shipping_telephone") && !order.get("shipping_telephone").equals("")) {
            	telephone = ObjectUtils.null2void(order.get("shipping_telephone"));
            } else {
            	telephone = ObjectUtils.null2void(order.get("telephone"));
            }
    		if(!CommonUtils.validTelephone(telephone, ObjectUtils.null2void(order.get("shipping_country_id")))) {
    			orderSb.append("->").append("전화번호 오류(").append(telephone).append(")").append(" ");
    		}
    		if(ObjectUtils.null2void(order.get("shipping_address_1")).equals("")) {
    			orderSb.append("->").append("배송지 주소 오류(").append(order.get("shipping_address_1")).append(")").append(" ");
    		}
    		if(ObjectUtils.null2void(order.get("shipping_postcode")).equals("")) {
    			orderSb.append("->").append("배송지 우편번호 오류(").append(order.get("shipping_postcode")).append(")").append(" ");
    		}
    		if(!CommonUtils.validRequisitionId(ObjectUtils.null2void(order.get("requisition_id")))) {
    			orderSb.append("->").append("개인통관번호 오류(").append(order.get("requisition_id")).append(")").append(" ");
    		}
    		
    		tracking_number = getGrpTrackingResult(gps_api_url+order.get("order_id"));
    		if(tracking_number.equals("") || tracking_number.equals("error")) {
    			orderSb.append("->").append("GPS Tracking 오류").append(" ");
    		} else {
    			order.put("order_status_id", "3"); // Shipped
    			order.put("carrier_id", "gps");
    			order.put("tracking", tracking_number);
    			ordersService.updateOrderStatus(order);
    			/**
    			 * 주문 이력 생성
    			 */
    			order.put("notify", "1");
    			order.put("comment", "GPS - tracking # : "+tracking_number);
    			order.put("table_prefix", StoreUtils.getTablePrefix(ObjectUtils.null2void(order.get("store_id"))));
        		ordersService.addOrderHistory(order); // 주문 이력 생성
        		
        		/**
        		 * 이메일 발송
        		 */
        		order.put("tracking_number", tracking_number);
        		String html = GpsTrackingEmail.getHtml(order);
        		commandMap.put("subject", order.get("store_name")+" - 상품배송이 시작되었습니(주문번호: "+order.get("order_id"));
    			commandMap.put("html", html);
    			commandMap.put("recipient_name", order.get("customer_name"));
    			commandMap.put("recipient_email", order.get("email"));
//    			commandMap.put("recipient_name", "조경일");
//    			commandMap.put("recipient_email", "bunmaeri@gmail.com");
    			MailChimpEmail.run(commandMap.getMap());
        	}
    		if(orderSb.length()>0 && orderSb.toString().contains("->")) {
    			errSb.append(orderSb.toString()).append("<br/>");
    			orderSb.setLength(0);
    		}
    		
    	}
    	
    	if(errSb.length()>0) {
    		BaseController.setCustomSession(request, errSb.toString(), Session.SALES_ORDERS_ERROR);
    	} else {
    		BaseController.setCustomSession(request, "GPS Tracking이 정상적으로 완료되었습니다.", Session.SALES_ORDERS_SUCCESS);
    	}
    		
    	mv.addObject("order_status_id", order_status_id);
    	
    	return mv;
    }
	
	/**
	 * GPS Tracking 실행 후 결과값 리턴
	 * @param api_url
	 * @return
	 * @throws Exception
	 */
	public String getGrpTrackingResult(String api_url) throws Exception {
		XmlParsering xml = new XmlParsering();
		Document doc = xml.start(api_url);
		NodeList descNodes = doc.getElementsByTagName("channel");

		String tracking_ok_no = "";
		String tracking = "";
			
        for(int i=0; i<descNodes.getLength();i++){
            for(Node node = descNodes.item(i).getFirstChild(); node!=null; node=node.getNextSibling()){ //첫번째 자식을 시작으로 마지막까지 다음 형제를 실행
            	if(node.getNodeName().equals("tracking_ok_no")) {
            		tracking_ok_no = ObjectUtils.null2void(node.getFirstChild().getNodeValue());
            	} else
                if(node.getNodeName().equals("tracking")){
                	tracking = ObjectUtils.null2void(node.getFirstChild().getNodeValue());
                }
            }
        }
        if(!tracking_ok_no.equals("yes")) {
        	return "error";
        }
	        
	    return tracking;
	}
	
	/**
	 * 주문 상태 변경
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sales/orders/status/change.dr")
    public ModelAndView ordersStatusChange(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/sales/orders.dr");
    	
		String order_status_id = ObjectUtils.null2void(commandMap.getMap().get("order_status_change"));
    	String[] array = commandMap.get2String("order_ids").split(",");
    	commandMap.put("array", array);
    	commandMap.put("order_status_id", order_status_id);
    	ordersService.updateOrdersStatusChange(commandMap.getMap());
    	
    	BaseController.setCustomSession(request, array, Session.ORDER_IDS);
    	
    	mv.addObject("order_status_id", order_status_id);
    	return mv;
    }
	
	/**
	 * BACK
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/sales/order/back.dr")
    public ModelAndView back(HttpServletRequest request, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView();

    	// 마지막 검색 조건을 확인해서 있으면 그쪽으로 없으면..
    	if(null!=BaseController.getCustomSession(request, Session.SALES_ORDERS_URL)) {
    		String return_url = CommonUtils.getReturnUrl(mv, (ArrayList<Map<String,Object>>) BaseController.getCustomSession(request, Session.SALES_ORDERS_URL), "/sales/orders.dr");
    		mv.setViewName("redirect:"+return_url);
    	} else {
    		mv.setViewName("redirect:/sales/orders.dr");
    	}
    
    	return mv;
    }
}