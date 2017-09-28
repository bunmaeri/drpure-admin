package admin.myhome.report.controller;

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
import admin.common.util.CommonUtils;
import admin.common.util.DateUtils;
import admin.common.util.MetaUtils;
import admin.common.util.ObjectUtils;
import admin.common.util.Pagemaker;
import admin.common.util.ScriptUtils;
import admin.common.util.StoreUtils;
import admin.myhome.report.service.MyReportSalesService;

@Controller
public class MyReportSalesController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="myReportSalesService")
	private MyReportSalesService myReportSalesService;
	
	@Resource(name="commonService")
	private CommonService commonService;

	/**
	 * Report > Sales > Order
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/my_reports/sales/order.dr")
    public ModelAndView reportSalesOrder(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_report/sales_order");
    	
    	String page = ObjectUtils.null2Value(commandMap.getMap().get("page"),"1");
    	String date_start = ObjectUtils.null2void(commandMap.getMap().get("date_start"));
    	if(date_start.equals("")) {
    		date_start = DateUtils.thisYearMonth("-")+"-01";
    	}
    	String date_end = ObjectUtils.null2void(commandMap.getMap().get("date_end"));
    	if(date_end.equals("")) {
    		date_end = DateUtils.getToday("yyyy-MM-dd");
    	}
//    	log.debug("date_start>>>>>>>>>>>>>>>>>>>>>>>>"+date_start);
//    	log.debug("date_end>>>>>>>>>>>>>>>>>>>>>>>>"+date_end);
    	commandMap.put("date_start", date_start);
    	commandMap.put("date_end", date_end);
    	
    	int count = 0;
    	pagemaker.setPage(pagemaker.getPage());
    	commandMap.put("language_id", StoreUtils.getLanguageId());
    	count = myReportSalesService.totalSalesOrder(commandMap.getMap()); // 레코드 총 갯수 구함
    	pagemaker.setCount(count); // 페이지 계산

    	commandMap.put("page", (pagemaker.getPage()-1)*pagemaker.getPER());
//    	System.err.println((pagemaker.getPage()-1)*pagemaker.getPER());
    	commandMap.put("per_page", pagemaker.getPER());
    	List<Map<String,Object>> list = myReportSalesService.salesOrder(commandMap.getMap());
    	mv.addObject("list", list);
    	if(list.size() > 0){
    		mv.addObject("pageMaker", pagemaker); 
    	}
    	mv.addObject("page", page);
    	// group by 코드 목록
    	mv.addObject("groupBy", CommonUtils.getGroupBy());
    	
    	// order status 코드 목록
    	commandMap.put("language_id", StoreUtils.getLanguageId());
    	mv.addObject("orderStatus", commonService.orderStatus(commandMap.getMap()));
    	
    	// 파라미터 값 셋팅
    	Map<String,Object> filter = new HashMap<String,Object>();
    	filter.put("date_start", date_start);
    	filter.put("date_end", date_end);
    	filter.put("group_by", ObjectUtils.null2Value(ObjectUtils.null2void(commandMap.getMap().get("group_by")), "month"));
    	filter.put("order_status_id", ObjectUtils.null2void(commandMap.getMap().get("order_status_id")));
    	mv.addObject("filter", filter);
    	
    	MetaUtils.reportSalesOrder(mv);
    	ScriptUtils.reportsScript(mv);
    	
    	return mv;
    }
	
	/**
	 * Report > Sales > Tax
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/my_reports/sales/tax.dr")
    public ModelAndView reportSalesTax(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_report/sales_tax");
    	
    	String page = ObjectUtils.null2Value(commandMap.getMap().get("page"),"1");
    	String date_start = ObjectUtils.null2void(commandMap.getMap().get("date_start"));
    	if(date_start.equals("")) {
    		date_start = DateUtils.thisYearMonth("-")+"-01";
    	}
    	String date_end = ObjectUtils.null2void(commandMap.getMap().get("date_end"));
    	if(date_end.equals("")) {
    		date_end = DateUtils.getToday("yyyy-MM-dd");
    	}
//    	log.debug("date_start>>>>>>>>>>>>>>>>>>>>>>>>"+date_start);
//    	log.debug("date_end>>>>>>>>>>>>>>>>>>>>>>>>"+date_end);
    	commandMap.put("date_start", date_start);
    	commandMap.put("date_end", date_end);
    	
    	int count = 0;
    	pagemaker.setPage(pagemaker.getPage());
    	count = myReportSalesService.totalSalesTax(commandMap.getMap()); // 레코드 총 갯수 구함
    	pagemaker.setCount(count); // 페이지 계산

    	commandMap.put("page", (pagemaker.getPage()-1)*pagemaker.getPER());
    	commandMap.put("per_page", pagemaker.getPER());
    	List<Map<String,Object>> list = myReportSalesService.salesTax(commandMap.getMap());
    	mv.addObject("list", list);
    	if(list.size() > 0){
    		mv.addObject("pageMaker", pagemaker); 
    	}
    	
    	mv.addObject("page", page);
    	// group by 코드 목록
    	mv.addObject("groupBy", CommonUtils.getGroupBy());
    	
    	// order status 코드 목록
    	commandMap.put("language_id", StoreUtils.getLanguageId());
    	mv.addObject("orderStatus", commonService.orderStatus(commandMap.getMap()));
    	
    	// 파라미터 값 셋팅
    	Map<String,Object> filter = new HashMap<String,Object>();
    	filter.put("date_start", date_start);
    	filter.put("date_end", date_end);
    	filter.put("group_by", ObjectUtils.null2Value(ObjectUtils.null2void(commandMap.getMap().get("group_by")), "month"));
    	filter.put("order_status_id", ObjectUtils.null2void(commandMap.getMap().get("order_status_id")));
    	mv.addObject("filter", filter);
    	
    	MetaUtils.reportSalesTax(mv);
    	ScriptUtils.reportsScript(mv);
    	
    	return mv;
    }
	
	/**
	 * Report > Sales > Shipping
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/my_reports/sales/shipping.dr")
    public ModelAndView reportSalesShipping(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_report/sales_shipping");
    	
    	String page = ObjectUtils.null2Value(commandMap.getMap().get("page"),"1");
    	String date_start = ObjectUtils.null2void(commandMap.getMap().get("date_start"));
    	if(date_start.equals("")) {
    		date_start = DateUtils.thisYearMonth("-")+"-01";
    	}
    	String date_end = ObjectUtils.null2void(commandMap.getMap().get("date_end"));
    	if(date_end.equals("")) {
    		date_end = DateUtils.getToday("yyyy-MM-dd");
    	}
//    	log.debug("date_start>>>>>>>>>>>>>>>>>>>>>>>>"+date_start);
//    	log.debug("date_end>>>>>>>>>>>>>>>>>>>>>>>>"+date_end);
    	commandMap.put("date_start", date_start);
    	commandMap.put("date_end", date_end);
    	
    	int count = 0;
    	pagemaker.setPage(pagemaker.getPage());
    	count = myReportSalesService.totalSalesShipping(commandMap.getMap()); // 레코드 총 갯수 구함
    	pagemaker.setCount(count); // 페이지 계산

    	commandMap.put("page", (pagemaker.getPage()-1)*pagemaker.getPER());
    	commandMap.put("per_page", pagemaker.getPER());
    	List<Map<String,Object>> list = myReportSalesService.salesShipping(commandMap.getMap());
    	mv.addObject("list", list);
    	if(list.size() > 0){
    		mv.addObject("pageMaker", pagemaker); 
    	}
    	
    	mv.addObject("page", page);
    	// group by 코드 목록
    	mv.addObject("groupBy", CommonUtils.getGroupBy());
    	
    	// order status 코드 목록
    	commandMap.put("language_id", StoreUtils.getLanguageId());
    	mv.addObject("orderStatus", commonService.orderStatus(commandMap.getMap()));
    	
    	// 파라미터 값 셋팅
    	Map<String,Object> filter = new HashMap<String,Object>();
    	filter.put("date_start", date_start);
    	filter.put("date_end", date_end);
    	filter.put("group_by", ObjectUtils.null2Value(ObjectUtils.null2void(commandMap.getMap().get("group_by")), "month"));
    	filter.put("order_status_id", ObjectUtils.null2void(commandMap.getMap().get("order_status_id")));
    	mv.addObject("filter", filter);
    	
    	MetaUtils.reportSalesShipping(mv);
    	ScriptUtils.reportsScript(mv);
    	
    	return mv;
    }
}
