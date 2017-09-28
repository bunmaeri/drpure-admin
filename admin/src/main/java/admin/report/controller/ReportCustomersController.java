package admin.report.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import admin.common.common.CommandMap;
import admin.common.service.CommonService;
import admin.common.util.MetaUtils;
import admin.common.util.ObjectUtils;
import admin.common.util.Pagemaker;
import admin.common.util.ScriptUtils;
import admin.common.util.StoreUtils;
import admin.report.service.ReportCustomersService;

@Controller
public class ReportCustomersController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="reportCustomersService")
	private ReportCustomersService reportCustomersService;

	@Resource(name="commonService")
	private CommonService commonService;

	/**
	 * Report > Customers > Order
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/reports/customers/order.dr")
    public ModelAndView reportSalesOrder(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/report/customers_order");
    	
    	String page = ObjectUtils.null2Value(commandMap.getMap().get("page"),"1");
    	String date_start = ObjectUtils.null2void(commandMap.getMap().get("date_start"));
//    	if(date_start.equals("")) {
//    		date_start = DateUtils.thisYearMonth("-")+"-01";
//    	}
    	String date_end = ObjectUtils.null2void(commandMap.getMap().get("date_end"));
//    	if(date_end.equals("")) {
//    		date_end = DateUtils.getToday("yyyy-MM-dd");
//    	}
//    	log.debug("date_start>>>>>>>>>>>>>>>>>>>>>>>>"+date_start);
//    	log.debug("date_end>>>>>>>>>>>>>>>>>>>>>>>>"+date_end);
    	commandMap.put("language_id", StoreUtils.getLanguageId());
    	commandMap.put("date_start", date_start);
    	commandMap.put("date_end", date_end);
    
    	int count = 0;
    	pagemaker.setPage(pagemaker.getPage());
    	count = reportCustomersService.totalOrder(commandMap.getMap()); // 레코드 총 갯수 구함
    	pagemaker.setCount(count); // 페이지 계산

    	commandMap.put("page", (pagemaker.getPage()-1)*pagemaker.getPER());
    	commandMap.put("per_page", pagemaker.getPER());
    	List<Map<String,Object>> list = reportCustomersService.order(commandMap.getMap());
    	mv.addObject("list", list);
    	if(list.size() > 0){
    		mv.addObject("pageMaker", pagemaker); 
    	}
    	
    	mv.addObject("page", page);
    	// order status 코드 목록
    	commandMap.put("language_id", StoreUtils.getLanguageId());
    	mv.addObject("orderStatus", commonService.orderStatus(commandMap.getMap()));
    	
    	// 파라미터 값 셋팅
    	Map<String,Object> filter = new HashMap<String,Object>();
    	filter.put("date_start", date_start);
    	filter.put("date_end", date_end);
    	filter.put("customer", ObjectUtils.null2void(commandMap.getMap().get("customer")));
    	filter.put("order_status_id", ObjectUtils.null2void(commandMap.getMap().get("order_status_id")));
    	mv.addObject("filter", filter);
    	
    	MetaUtils.reportCustomersOrder(mv);
    	ScriptUtils.reportsScript(mv);
    	
    	return mv;
    }
	
	/**
	 * Report > Customers > Reward Points
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/reports/customers/reward.dr")
    public ModelAndView reportSalesTax(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/report/customers_reward");
    	
    	String page = ObjectUtils.null2Value(commandMap.getMap().get("page"),"1");
    	String date_start = ObjectUtils.null2void(commandMap.getMap().get("date_start"));
//    	if(date_start.equals("")) {
//    		date_start = DateUtils.thisYearMonth("-")+"-01";
//    	}
    	String date_end = ObjectUtils.null2void(commandMap.getMap().get("date_end"));
//    	if(date_end.equals("")) {
//    		date_end = DateUtils.getToday("yyyy-MM-dd");
//    	}
//    	log.debug("date_start>>>>>>>>>>>>>>>>>>>>>>>>"+date_start);
//    	log.debug("date_end>>>>>>>>>>>>>>>>>>>>>>>>"+date_end);
    	commandMap.put("language_id", StoreUtils.getLanguageId());
    	commandMap.put("date_start", date_start);
    	commandMap.put("date_end", date_end);
    	
    	int count = 0;
    	pagemaker.setPage(pagemaker.getPage());
    	count = reportCustomersService.totalRewardPoints(commandMap.getMap()); // 레코드 총 갯수 구함
    	pagemaker.setCount(count); // 페이지 계산

    	commandMap.put("page", (pagemaker.getPage()-1)*pagemaker.getPER());
    	commandMap.put("per_page", pagemaker.getPER());
    	List<Map<String,Object>> list = reportCustomersService.rewardPoints(commandMap.getMap());
    	mv.addObject("list", list);
    	if(list.size() > 0){
    		mv.addObject("pageMaker", pagemaker); 
    	}
    	
    	mv.addObject("page", page);
    	// 파라미터 값 셋팅
    	Map<String,Object> filter = new HashMap<String,Object>();
    	filter.put("date_start", date_start);
    	filter.put("date_end", date_end);
    	filter.put("customer", ObjectUtils.null2void(commandMap.getMap().get("customer")));
    	mv.addObject("filter", filter);
    	
    	MetaUtils.reportCustomersRewardPoints(mv);
    	ScriptUtils.reportsScript(mv);
    	
    	return mv;
    }
	
	/**
	 * Report > Customers > Donator
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/reports/customers/donator.dr")
    public ModelAndView reportSalesShipping(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/report/customers_donator");
    	
    	String page = ObjectUtils.null2Value(commandMap.getMap().get("page"),"1");
    	String date_start = ObjectUtils.null2void(commandMap.getMap().get("date_start"));
    	String date_end = ObjectUtils.null2void(commandMap.getMap().get("date_end"));
//    	log.debug("date_start>>>>>>>>>>>>>>>>>>>>>>>>"+date_start);
//    	log.debug("date_end>>>>>>>>>>>>>>>>>>>>>>>>"+date_end);
    	commandMap.put("language_id", StoreUtils.getLanguageId());
    	commandMap.put("date_start", date_start);
    	commandMap.put("date_end", date_end);
    	
    	int count = 0;
    	pagemaker.setPage(pagemaker.getPage());
    	count = reportCustomersService.totalDonator(commandMap.getMap()); // 레코드 총 갯수 구함
    	pagemaker.setCount(count); // 페이지 계산

    	commandMap.put("page", (pagemaker.getPage()-1)*pagemaker.getPER());
    	commandMap.put("per_page", pagemaker.getPER());
    	List<Map<String,Object>> list = reportCustomersService.donator(commandMap.getMap());
    	mv.addObject("list", list);
    	if(list.size() > 0){
    		mv.addObject("pageMaker", pagemaker); 
    	}
    	
    	mv.addObject("page", page);
    	// 파라미터 값 셋팅
    	Map<String,Object> filter = new HashMap<String,Object>();
    	filter.put("date_start", date_start);
    	filter.put("date_end", date_end);
    	filter.put("customer", ObjectUtils.null2void(commandMap.getMap().get("customer")));
    	mv.addObject("filter", filter);
    	
    	MetaUtils.reportCustomersDonator(mv);
    	ScriptUtils.reportsScript(mv);
    	
    	return mv;
    }
}
