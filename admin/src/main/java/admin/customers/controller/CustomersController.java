package admin.customers.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import admin.common.constant.Message;
import admin.common.constant.Session;
import admin.common.controller.BaseController;
import admin.common.service.CommonService;
import admin.common.util.CommonUtils;
import admin.common.util.ObjectUtils;
import admin.common.util.Pagemaker;
import admin.common.util.ScriptUtils;
import admin.common.util.StoreUtils;
import admin.customers.service.CustomersService;
import admin.sales.service.OrdersService;

@Controller
public class CustomersController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="customersService")
	private CustomersService customersService;
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	@Resource(name="ordersService")
	private OrdersService ordersService;
	
	/**
	 * 고객 목록 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/customers.dr")
    public ModelAndView customers(HttpServletRequest request, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/customers/customers");
    	
    	String customer_name = ObjectUtils.null2void(commandMap.getMap().get("customer_name"));
    	String customer_group_id = ObjectUtils.null2void(commandMap.getMap().get("customer_group_id"));
    	String join_path_id = ObjectUtils.null2void(commandMap.getMap().get("join_path_id"));
    	String email = ObjectUtils.null2void(commandMap.getMap().get("email"));
    	String date_added = ObjectUtils.null2void(commandMap.getMap().get("date_added"));
    	String status = ObjectUtils.null2void(commandMap.getMap().get("status"));
    	String myhomedoc = ObjectUtils.null2void(commandMap.getMap().get("myhomedoc"));
    	String page = ObjectUtils.null2Value(commandMap.getMap().get("page"),"1");
    
    	// 검색 조건이 하나도 없으면 검색을 하지 않는다.
    	if(customer_name.equals("") && customer_group_id.equals("") && join_path_id.equals("") && email.equals("") && date_added.equals("") && status.equals("") && myhomedoc.equals("")) {
    	} else {
    		int count = 0;
	    	pagemaker.setPage(pagemaker.getPage());
	    	commandMap.put("language_id", StoreUtils.getLanguageId());
	    	count = customersService.totalCustomers(commandMap.getMap()); // 레코드 총 갯수 구함
	    	pagemaker.setCount(count); // 페이지 계산
	
	    	commandMap.put("page", (pagemaker.getPage()-1)*pagemaker.getPER());
	    	commandMap.put("per_page", pagemaker.getPER());
	    	List<Map<String,Object>> list = customersService.listCustomers(commandMap.getMap());
	    	mv.addObject("list", list);
	    	if(list.size() > 0){
	    		mv.addObject("pageMaker", pagemaker); 
	    	}
    	}
    	// 고객 그룹 목록
    	List<Map<String, Object>> customerGroupsList = commonService.customerGroups(StoreUtils.getLanguageIdString());
    	mv.addObject("customerGroupsList", customerGroupsList);
    	mv.addObject("param", commandMap.getMap());
    	mv.addObject("page", page);
    	
    	// 고객 가입 경로 목록
    	List<Map<String, Object>> customerJoinPathList = commonService.customerJoinPaths(StoreUtils.getLanguageIdString());
    	mv.addObject("customerJoinPathList", customerJoinPathList);
    	
    	// 에러가 있을 때(Excel Export, GPS Tracking)
    	if(null!=BaseController.getCustomSession(request, Session.CUSTOMERS_ERROR)) {
    		mv.addObject("errroMsg", BaseController.getCustomSession(request, Session.CUSTOMERS_ERROR));
    		BaseController.setCustomSession(request, null, Session.CUSTOMERS_ERROR);
    	}
    	// 성공 메세지가 있을 때(Excel Export, GPS Tracking)
    	if(null!=BaseController.getCustomSession(request, Session.CUSTOMERS_SUCCESS)) {
    		mv.addObject("successMsg", BaseController.getCustomSession(request, Session.CUSTOMERS_SUCCESS));
    		BaseController.setCustomSession(request, null, Session.CUSTOMERS_SUCCESS);
    	}

    	// Return URL
    	BaseController.setCustomSession(request, CommonUtils.setReturnUrl(request), Session.CUSTOMERS_URL);
    	
    	ScriptUtils.customersScript(mv);
   
    	return mv;
    }
	
	/**
	 * 고객 비밀번호 변경
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/customers/resetpwd/{customer_id}.dr")
    public ModelAndView resetPassword(@PathVariable String customer_id, CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");

		String password = ObjectUtils.null2void(commandMap.get("pwd"));
		if(!password.equals("")) {
			commandMap.put("password", CommonUtils.shaEncoder(password));
			commandMap.put("customer_id", customer_id);
			customersService.updatePasswordByUserid(commandMap.getMap());
		}
		mv.addObject("success", "비밀번호가 변경되었습니다.");
    	
    	return mv;
    }
	
	/**
	 * 고객 주소 목록 조회
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/customers/address/{customer_id}.dr")
    public ModelAndView addressList(@PathVariable String customer_id, CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");

    	List<Map<String,Object>> list = customersService.addressList(customer_id);
    	mv.addObject("list", list);
    	
    	return mv;
    }

	/**
	 * 고객 상세 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/customer/view/{customer_id}.dr")
    public ModelAndView customerView(HttpServletRequest request, @PathVariable String customer_id, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/customers/customer_view");
    	
    	// 고객 상세
    	Map<String, Object> customer = customersService.customerInfo(customer_id);
    	String last_password = CommonUtils.base64Decode(ObjectUtils.null2void(customer.get("login_password")));
    	customer.put("last_password", last_password);
    	mv.addObject("info", customer);
    	
    	// 주소 목록
    	List<Map<String, Object>> addressList = customersService.addressList(customer_id);
    	mv.addObject("addressList", addressList);
    	
    	// 고객 그룹 목록
    	List<Map<String, Object>> customerGroupsList = commonService.customerGroups(StoreUtils.getLanguageIdString());
    	mv.addObject("customerGroupsList", customerGroupsList);
    	
    	// 에러가 있을 때(Excel Export, GPS Tracking)
    	if(null!=BaseController.getCustomSession(request, Session.CUSTOMERS_ERROR)) {
    		mv.addObject("errroMsg", BaseController.getCustomSession(request, Session.CUSTOMERS_ERROR));
    		BaseController.setCustomSession(request, null, Session.CUSTOMERS_ERROR);
    	}
    	// 성공 메세지가 있을 때(Excel Export, GPS Tracking)
    	if(null!=BaseController.getCustomSession(request, Session.CUSTOMERS_SUCCESS)) {
    		mv.addObject("successMsg", BaseController.getCustomSession(request, Session.CUSTOMERS_SUCCESS));
    		BaseController.setCustomSession(request, null, Session.CUSTOMERS_SUCCESS);
    	}
    
    	ScriptUtils.customerViewScript(mv);
   
    	return mv;
    }

	/**
	 * 고객 기본정보 저장
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/customer/save/info.dr", produces = "application/json; charset=utf8")
    public ModelAndView customerInfoSave(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
    	
		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validForm(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
	    	String customer_name = commandMap.get2String("firstname");
	    	if(!commandMap.get2String("lastname").equals("")) {
	    		customer_name = customer_name + " " + commandMap.get2String("lastname");
	    	}
	    	commandMap.put("customer_name", customer_name);
	    	commandMap.put("password", CommonUtils.shaEncoder(commandMap.get2String("password")));
	    	customersService.updateCustomerInfo(commandMap.getMap());
	    	mv.addObject("success", "고객 기본정보가 정상적으로 저장되었습니다.");
		} else {
			mv.addObject("error", "오류 메세지를 확인해주십시요.");
			mv.addObject("Message", validMap);
		}
   
    	return mv;
    }
	
	/**
	 * 폼 유효성 체크
	 * @param validMap
	 * @param commandMap
	 * @throws Exception
	 */
	public void validForm(Map<String, Object> validMap, CommandMap commandMap) throws Exception{
		boolean flag = true;
		
		String firstname = commandMap.get2String("firstname");
		if(ObjectUtils.isEmpty(firstname) || (firstname.length()<1 && firstname.length()>32)) {
			validMap.put("error_firstname_css", Message.DIV_ERROR);
			validMap.put("error_firstname", "1글자 ~ 32글자 사이로 입력해야 합니다.");
		}
		String lastname = commandMap.get2String("lastname");
		if(!ObjectUtils.isEmpty(lastname) && lastname.length()>32) {
			validMap.put("error_lastname_css", Message.DIV_ERROR);
			validMap.put("error_lastname", "32글자보다 작아야 합니다.");
		}
		
		String email = commandMap.get2String("email");
		String last_email = commandMap.get("last_email").toString();
		if(!email.equals(last_email)) {
			flag = CommonUtils.validEmail(email);
			if(!flag) {
				int cnt = customersService.duplicateEmail(email);
				if(cnt>0) {
					validMap.put("error_email_css", Message.DIV_ERROR);
					validMap.put("error_email", "이미 사용중이 이메일입니다.");
				}
			} else {
				validMap.put("error_lastname_css", Message.DIV_ERROR);
				validMap.put("error_lastname", "잘못된 이메일입니다.");
			}
		}
		
		String telephone = commandMap.get2String("telephone");
		flag = CommonUtils.validTelephone(telephone, "0");
		if(!flag) {
			validMap.put("error_telephone_css", Message.DIV_ERROR);
			validMap.put("error_telephone", "전화번호를 확인해주십시요.");
		}
	}

	/**
	 * 고객 주문이력 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/customer/view/orderhistory/{customer_id}.dr")
    public ModelAndView customerOrderHistoryView(HttpServletRequest request, @PathVariable String customer_id, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/customers/customer_view_orderhistory");

    	String page = ObjectUtils.null2Value(commandMap.getMap().get("page"),"1");
    	
    	// 고객 상세
    	Map<String, Object> customer = customersService.customerInfo(customer_id);
    	mv.addObject("info", customer);
    	
    	commandMap.put("customer_id", customer_id);
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
    	mv.addObject("page", page); 
    	
    	ScriptUtils.customerViewScript(mv);
   
    	return mv;
    }
	
	/**
	 * 고객 적립포인트 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/customer/view/reward/{customer_id}.dr")
    public ModelAndView customerRewardView(HttpServletRequest request, @PathVariable String customer_id, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/customers/customer_view_reward");

    	String page = ObjectUtils.null2Value(commandMap.getMap().get("page"),"1");
    	
    	// 고객 상세
    	Map<String, Object> customer = customersService.customerInfo(customer_id);
    	mv.addObject("info", customer);
    	
    	commandMap.put("customer_id", customer_id);
    	int count = 0;
    	pagemaker.setPage(pagemaker.getPage());
    	commandMap.put("language_id", StoreUtils.getLanguageId());
    	Map<String,Object> totalMap = customersService.totalCustomerReward(customer_id);
    	count = Integer.parseInt(ObjectUtils.null2Value(totalMap.get("total"),"0")); // 레코드 총 갯수 구함
    	pagemaker.setCount(count); // 페이지 계산

    	commandMap.put("page", (pagemaker.getPage()-1)*pagemaker.getPER());
    	commandMap.put("per_page", pagemaker.getPER());
    	List<Map<String,Object>> list = customersService.listCustomerReward(commandMap.getMap());
    	mv.addObject("list", list);
    	if(list.size() > 0){
    		mv.addObject("pageMaker", pagemaker); 
    	}
    	mv.addObject("page", page); 
    	mv.addObject("sum_points", ObjectUtils.null2Value(totalMap.get("sum_points"),"0")); // 총 포인트 합계
    
    	ScriptUtils.customerViewScript(mv);
  
    	return mv;
    }

	/**
	 * 고객 적립포인트 저장
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/customer/save/reward.dr", produces = "application/json; charset=utf8")
    public ModelAndView customerSaveReward(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
    	
		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validFormReward(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
			commandMap.put("order_id", "0");
	    	customersService.addCustomerReward(commandMap.getMap());
	    	mv.addObject("success", "적립포인트가 정상적으로 저장되었습니다.");
		} else {
			mv.addObject("error", "오류 메세지를 확인해주십시요.");
			mv.addObject("Message", validMap);
		}
  
    	return mv;
    }
	
	/**
	 * 폼 유효성 체크
	 * @param validMap
	 * @param commandMap
	 * @throws Exception
	 */
	public void validFormReward(Map<String, Object> validMap, CommandMap commandMap) throws Exception{
		boolean flag = true;
		
		String description = commandMap.get2String("description");
		if(ObjectUtils.isEmpty(description) || description.length()<1) {
			validMap.put("error_description_css", Message.DIV_ERROR);
			validMap.put("error_description", "적립내용은 필수입력입니다.");
		}
		
		String points = commandMap.get2String("points");
		if(ObjectUtils.isEmpty(points) || points.length()<1 || points.equals("-")) {
			validMap.put("error_points_css", Message.DIV_ERROR);
			validMap.put("error_points", "포인트는 필수입력입니다.");
		} else {
			flag = CommonUtils.validRewardPoint(points, "0");
			if(!flag) {
				validMap.put("error_points_css", Message.DIV_ERROR);
				validMap.put("error_points", "숫자만 입력이 가능합니다.");
			}
		}
	}
	
	/**
	 * 고객 적립포인트 삭제
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/customer/delete/reward/{customer_reward_id}.dr", produces = "application/json; charset=utf8")
    public ModelAndView customerDeleteReward(HttpServletRequest request, @PathVariable String customer_reward_id, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
    	
    	customersService.deleteCustomerReward(customer_reward_id);
    	mv.addObject("success", "적립포인트가 삭제되었습니다.");
		
    	return mv;
    }
	
	/**
	 * 고객 적립포인트 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/customer/view/loginhistory/{customer_id}.dr")
    public ModelAndView customerViewLoginhistory(HttpServletRequest request, @PathVariable String customer_id, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/customers/customer_view_loginhistory");

    	String page = ObjectUtils.null2Value(commandMap.getMap().get("page"),"1");
    	
    	// 고객 상세
    	Map<String, Object> customer = customersService.customerInfo(customer_id);
    	mv.addObject("info", customer);
    	
    	commandMap.put("customer_id", customer_id);
    	int count = 0;
    	pagemaker.setPage(pagemaker.getPage());
    	count = customersService.totalCustomerLoginhistory(customer_id); // 레코드 총 갯수 구함
    	pagemaker.setCount(count); // 페이지 계산

    	commandMap.put("page", (pagemaker.getPage()-1)*pagemaker.getPER());
    	commandMap.put("per_page", pagemaker.getPER());
    	List<Map<String,Object>> list = customersService.listCustomerLoginhistory(commandMap.getMap());
    	mv.addObject("list", list);
    	if(list.size() > 0){
    		mv.addObject("pageMaker", pagemaker); 
    	}
    	mv.addObject("page", page); 
    
    	ScriptUtils.customerViewScript(mv);
  
    	return mv;
    }
	
	/**
	 * 주소 상세 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/customer/view/address/{customer_id}/{address_id}.dr")
    public ModelAndView customerViewAddress(HttpServletRequest request, @PathVariable String customer_id, @PathVariable String address_id, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/customers/customer_view_address");
    	
    	// 고객 상세
    	Map<String, Object> customer = customersService.customerInfo(customer_id);
    	mv.addObject("info", customer);
    	
    	// 주소 목록
    	List<Map<String, Object>> addressList = customersService.addressList(customer_id);
    	mv.addObject("addressList", addressList);
    	
    	String country_id = "113";
    	if(!address_id.equals("0")) {
    		// 선택한 주소 정보
        	Map<String,Object> address = customersService.address(address_id);
        	if(null==address || null==address.get("address_id")) {
        		address_id = ObjectUtils.null2void(customer.get("address_id"));
        		address = customersService.address(address_id);
        	}
        	mv.addObject("address_id", address_id);
        	mv.addObject("address", address);
        	country_id = ObjectUtils.null2void(address.get("country_id"));
    	} else {
    		mv.addObject("address", new HashMap<String,Object>());
    	}
    	
    	List<Map<String, Object>> zoneList = commonService.zonesPerCountry(country_id);
    	mv.addObject("zoneList", zoneList);
    	
    	ScriptUtils.customerViewScript(mv);
   
    	return mv;
    }
	
	/**
	 * 주소 저장
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/customer/save/address.dr", produces = "application/json; charset=utf8")
    public ModelAndView customerSaveAddress(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
    	
		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validFormAddress(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
			String address_id = ObjectUtils.null2Value(commandMap.get("address_id"),"0");
	    	String customer_name = commandMap.get2String("firstname");
	    	if(!commandMap.get2String("lastname").equals("")) {
	    		customer_name = customer_name + " " + commandMap.get2String("lastname");
	    	}
	    	commandMap.put("customer_name", customer_name);
	    	commandMap.put("zone_id", ObjectUtils.null2Value(commandMap.getMap().get("zone_id"),"0"));
	    	// 주소 업데이트
	    	if(!address_id.equals("0")) {
	    		customersService.updateCustomerAddress(commandMap.getMap());
	    	} else {
	    		// 주소 추가
	    		customersService.addAddress(commandMap.getMap());
	    	}
	    	
	    	/**
	    	 * 고객 기본주소 변경
	    	 */
	    	boolean addressUpdate = false;
	    	if(commandMap.get2String("default_address").equals("1")) {
	    		commandMap.put("address_id", commandMap.get2String("address_id"));
	    		addressUpdate = true;
	    	}
	    	if(commandMap.get2String("default_shipping_address").equals("1")) {
	    		commandMap.put("shipping_address_id", commandMap.get2String("address_id"));
	    		addressUpdate = true;
	    	}
	    	if(addressUpdate) {
	    		customersService.updateCustomerInfoAddress(commandMap.getMap());
	    	}
	    	mv.addObject("success", "주소가 정상적으로 저장되었습니다.");
		} else {
			mv.addObject("error", "오류 메세지를 확인해주십시요.");
			mv.addObject("Message", validMap);
		}
   
    	return mv;
    }
	
	/**
	 * 주소 폼 유효성 체크
	 * @param validMap
	 * @param commandMap
	 * @throws Exception
	 */
	public void validFormAddress(Map<String, Object> validMap, CommandMap commandMap) throws Exception{
		boolean flag = true;
		
		String address_alias = commandMap.get2String("address_alias");
		if(!ObjectUtils.isEmpty(address_alias) && address_alias.length()>40) {
			validMap.put("error_address_alias", "80글자보다 작게 입력해야 합니다.");
		}
		String firstname = commandMap.get2String("firstname");
		if(ObjectUtils.isEmpty(firstname) || (firstname.length()<1 && firstname.length()>32)) {
			validMap.put("error_firstname", "1글자 ~ 32글자 사이로 입력해야 합니다.");
		}
		String lastname = commandMap.get2String("lastname");
		if(!ObjectUtils.isEmpty(lastname) && lastname.length()>32) {
			validMap.put("error_lastname", "32글자보다 작아야 합니다.");
		}
		String telephone = commandMap.get2String("telephone");
		if(!ObjectUtils.isEmpty(telephone) && telephone.length()>1) {
			flag = CommonUtils.validTelephone(telephone, "0");
			if(!flag) {
				validMap.put("error_telephone", "전화번호를 확인해주십시요.");
			}
		}
		String requisition_id = commandMap.get2String("requisition_id");
		if(!ObjectUtils.isEmpty(requisition_id) && requisition_id.length()>1) {
			flag = CommonUtils.validRequisitionId(requisition_id);
			if(!flag) {
				validMap.put("error_requisition_id", "개인통관고유부호를 확인해주십시요.");
			}
		}
		String company = commandMap.get2String("company");
		if(!ObjectUtils.isEmpty(company) && company.length()>40) {
			validMap.put("error_fcompany", "40글자보다 작게 입력해야 합니다.");
		}
		String address_1 = commandMap.get2String("address_1");
		if(ObjectUtils.isEmpty(address_1) || (address_1.length()<1 && address_1.length()>128)) {
			validMap.put("error_address_1", "1글자 ~ 128글자 사이로 입력해야 합니다.");
		}
		String address_2 = commandMap.get2String("address_2");
		if(!ObjectUtils.isEmpty(address_2) && address_2.length()>128) {
			validMap.put("error_address_2", "128글자보다 작게 입력해야 합니다.");
		}
		String city = commandMap.get2String("city");
		if(!ObjectUtils.isEmpty(city) && city.length()>128) {
			validMap.put("error_city", "128글자보다 작게 입력해야 합니다.");
		}
		String postcode = commandMap.get2String("postcode");
		if(ObjectUtils.isEmpty(postcode) || (postcode.length()<1 && postcode.length()>10)) {
			validMap.put("error_postcode", "1글자 ~ 10글자 사이로 입력해야 합니다.");
		}
	}
	
	/**
	 * 주소 삭제
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/customer/delete/address/{address_id}.dr", produces = "application/json; charset=utf8")
    public ModelAndView customerDeleteAddress(HttpServletRequest request, @PathVariable String address_id, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
    	
    	customersService.deleteCustomerAddress(address_id);
    	mv.addObject("success", "주소가 삭제되었습니다.");
		
    	return mv;
    }

	/**
	 * BACK
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/customer/back.dr")
    public ModelAndView back(HttpServletRequest request, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView();

    	// 마지막 검색 조건을 확인해서 있으면 그쪽으로 없으면..
    	if(null!=BaseController.getCustomSession(request, Session.CUSTOMERS_URL)) {
    		String return_url = CommonUtils.getReturnUrl(mv, (ArrayList<Map<String,Object>>) BaseController.getCustomSession(request, Session.CUSTOMERS_URL), "/customers.dr");
    		mv.setViewName("redirect:"+return_url);
    	} else {
    		mv.setViewName("redirect:/customers.dr");
    	}
    
    	return mv;
    }
}
