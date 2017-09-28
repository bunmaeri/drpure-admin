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

@Controller
public class CustomersGroupsController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="customersService")
	private CustomersService customersService;
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	/**
	 * 고객 그룹 목록 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/customers_groups.dr")
    public ModelAndView customersGroups(HttpServletRequest request, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/customers/customers_groups");
    	
    	// 고객 그룹 목록
    	List<Map<String, Object>> customerGroupsList = commonService.customerGroups(StoreUtils.getLanguageIdString());
    	mv.addObject("list", customerGroupsList);
    	
    	ScriptUtils.customersScript(mv);
   
    	return mv;
    }
	
	/**
	 * 고객 그룹 상세 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/customers_groups/view/{customer_group_id}.dr")
    public ModelAndView customerView(HttpServletRequest request, @PathVariable String customer_group_id, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/customers/customers_group_view");
    	
    	// 고객 상세
    	commandMap.put("customer_group_id", customer_group_id);
    	commandMap.put("language_id", StoreUtils.getLanguageIdString());
    	Map<String, Object> customer = customersService.customerGroup(commandMap.getMap());
    	mv.addObject("info", customer);
    	
    	// 에러가 있을 때
    	if(null!=BaseController.getCustomSession(request, Session.CUSTOMERS_GROUPS_ERROR)) {
    		mv.addObject("errroMsg", BaseController.getCustomSession(request, Session.CUSTOMERS_GROUPS_ERROR));
    		BaseController.setCustomSession(request, null, Session.CUSTOMERS_GROUPS_ERROR);
    	}
    	// 성공 메세지가 있을 때
    	if(null!=BaseController.getCustomSession(request, Session.CUSTOMERS_GROUPS_SUCCESS)) {
    		mv.addObject("successMsg", BaseController.getCustomSession(request, Session.CUSTOMERS_GROUPS_SUCCESS));
    		BaseController.setCustomSession(request, null, Session.CUSTOMERS_GROUPS_SUCCESS);
    	}
    	
    	ScriptUtils.customerViewScript(mv);
   
    	return mv;
    }
	
	/**
	 * 고객 그룹 정보 저장
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/customers_groups/save.dr", produces = "application/json; charset=utf8")
    public ModelAndView customerInfoSave(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
    	
		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validForm(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
			if(commandMap.get2String("customer_group_id").equals("") || commandMap.get2String("customer_group_id").equals("0")) {
				commandMap.put("language_id", StoreUtils.getLanguageIdString());
				customersService.addCustomerGroup(commandMap.getMap());
			} else {
				customersService.updateCustomerGroup(commandMap.getMap());
			}
	    	mv.addObject("success", "정상적으로 저장되었습니다.");
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
		String name = commandMap.get2String("name");
		if(ObjectUtils.isEmpty(name) || (name.length()<1 && name.length()>32)) {
			validMap.put("error_name_css", Message.DIV_ERROR);
			validMap.put("error_name", "1글자 ~ 32글자 사이로 입력해야 합니다.");
		}
	}

	/**
	 * BACK
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/customers_group/back.dr")
    public ModelAndView back(HttpServletRequest request, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView();

    	// 마지막 검색 조건을 확인해서 있으면 그쪽으로 없으면..
    	if(null!=BaseController.getCustomSession(request, Session.CUSTOMERS_URL)) {
    		String return_url = CommonUtils.getReturnUrl(mv, (ArrayList<Map<String,Object>>) BaseController.getCustomSession(request, Session.CUSTOMERS_URL), "/customers/groups.dr");
    		mv.setViewName("redirect:"+return_url);
    	} else {
    		mv.setViewName("redirect:/customers/groups.dr");
    	}
    
    	return mv;
    }
}
