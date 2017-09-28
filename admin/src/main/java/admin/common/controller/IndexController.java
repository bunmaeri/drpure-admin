package admin.common.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import admin.common.common.CommandMap;
import admin.common.service.IndexService;
import admin.common.util.DateUtils;
import admin.common.util.ScriptUtils;
import admin.common.util.StoreUtils;
import admin.common.util.StringUtils;


@Controller
public class IndexController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="indexService")
	private IndexService indexService;
	
	/**
	 * 빈페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/blank.dr")
    public ModelAndView blank(CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/blank");
    	
    	return mv;
    }

	/**
	 * 메인페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/index.dr")
    public ModelAndView index(CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/index");
    	
    	// 지난 주 날짜 계산
//    	commandMap.put("from_date", DateUtils.addDays(DateUtils.getToday(), -7));
//    	commandMap.put("to_date", DateUtils.addDays(DateUtils.getToday(), -1));
    	
    	// TOTAL ORDERS
    	mv.addObject("orders", this.getOrders(commandMap));
    	
    	// TOTAL SALES
    	mv.addObject("sales", this.getSales(commandMap));
    	
    	// TOTAL CUSTOMERS
    	mv.addObject("customers", this.getCustomers(commandMap));
    	
    	// 가입경로
    	mv.addObject("join_path", indexService.joinPathInfo());
    	
    	// 최신 주문 데이터 조회(5개)
    	commandMap.put("language_id", StoreUtils.getLanguageId());
    	commandMap.put("to", 6);
    	mv.addObject("recent", indexService.recentOrders(commandMap.getMap()));
    	
    	String today = DateUtils.getToday("yyyyMMdd");

    	if(commandMap.get2String("from_date").equals("")) {
    		commandMap.put("from_date", DateUtils.addMonths(today, -1));
    	}
    	if(commandMap.get2String("to_date").equals("")) {
    		commandMap.put("to_date", today);
    	}
//   System.err.println(commandMap.get2String("from_date"));
//   System.err.println(commandMap.get2String("to_date"));
    	mv.addObject("dailyOrderTotal", indexService.dailyOrderTotal(commandMap.getMap()));
    	commandMap.put("store_id", "0");
    	mv.addObject("dailyOrderDrTotal", indexService.dailyOrderStoreTotal(commandMap.getMap()));
    	commandMap.put("store_id", "1");
    	mv.addObject("dailyOrderMyTotal", indexService.dailyOrderStoreTotal(commandMap.getMap()));
    	
    	// 국가별
    	commandMap.put("shipping_country_id", "113");
    	mv.addObject("dailyOrderKorTotal", indexService.dailyOrderCountryTotal(commandMap.getMap()));
    	commandMap.put("shipping_country_id", "223");
    	mv.addObject("dailyOrderUsaTotal", indexService.dailyOrderCountryTotal(commandMap.getMap()));
    
    	mv.addObject("today", today);
    	mv.addObject("mon2", DateUtils.addMonths(today, -2));
    	mv.addObject("mon3", DateUtils.addMonths(today, -3));
    	mv.addObject("mon6", DateUtils.addMonths(today, -6));
    	mv.addObject("mon9", DateUtils.addMonths(today, -9));
    	mv.addObject("year1", DateUtils.addYears(today, -1));
    	mv.addObject("year2", DateUtils.addYears(today, -2));
    	mv.addObject("year3", DateUtils.addYears(today, -3));
    	mv.addObject("year4", DateUtils.addYears(today, -5));
    	
    	mv.addObject("from_date", DateUtils.formatStr2Date(commandMap.get2String("from_date"), "-"));
    	mv.addObject("to_date", DateUtils.formatStr2Date(commandMap.get2String("to_date"), "-"));
    	
    
    	ScriptUtils.dashboardScript(mv);
    	return mv;
    }
	
	/**
	 * Map 데이터 조회
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/index/map.dr")
    public Map<String, Object>[] mapInfo(CommandMap commandMap) throws Exception {
		Map<String, Object>[] array = null;
    	List<Map<String,Object>> list = indexService.mapInfo();
    	int size = list.size();
//    	array = new HashMap<String, Object>()[size];
    	Map<String,Object> map = null;
    	for(int i=0;i<size;i++) {
    		map = list.get(i);
    		array[i] = map;
    	}
    	return array;
    }
	
	/**
	 * TOTAL ORDERS
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	private Map<String,Object> getOrders(CommandMap commandMap) throws Exception {
		Map<String,Object> orders = new HashMap<String,Object>();
    	orders.put("title", "총 주문 갯수");
//    	// 지난주 주문합계
//    	String last_week = StringUtils.defaultValue(indexService.sumOrder(commandMap.getMap()),"0");
//    	orders.put("last_week", last_week);
//    	// 이번주 주문합계
//    	commandMap.put("from_date", DateUtils.addDays(DateUtils.getToday(), -15));
//    	commandMap.put("to_date", DateUtils.addDays(DateUtils.getToday(), -8));
//    	String this_week = StringUtils.defaultValue(indexService.sumOrder(commandMap.getMap()),"0");
//    	orders.put("this_week", this_week);
//    	
//    	if(last_week.equals("0") || this_week.equals("0")) {
//    		orders.put("percentage", "0");
//    	} else {
//	    	long difference = Math.round(Double.parseDouble(this_week) - Double.parseDouble(last_week));
//	    	long percentage = difference / Math.round(Double.parseDouble(this_week)) * 100;
//	    	orders.put("percentage", percentage);
//    	}
   
    	BigDecimal total = new BigDecimal(StringUtils.defaultValue(indexService.totalOrders(commandMap.getMap()),"0"));
    	orders.put("total_orders", total);
//    	if (total.compareTo(new BigDecimal("1000000000000"))>0) {
//    		orders.put("total_orders", total.divide(new BigDecimal("1000000000000"), 1, BigDecimal.ROUND_UP)+"T");
//		} else if (total.compareTo(new BigDecimal("1000000000"))>0) {
//			orders.put("total_orders", total.divide(new BigDecimal("1000000000"), 1, BigDecimal.ROUND_UP)+"B");
//		} else if (total.compareTo(new BigDecimal("1000000"))>0) {
//			orders.put("total_orders", total.divide(new BigDecimal("1000000"), 1, BigDecimal.ROUND_UP)+"M");
//		} else if (total.compareTo(new BigDecimal("1000"))>0) {
//			orders.put("total_orders", total.divide(new BigDecimal("1000"), 1, BigDecimal.ROUND_UP)+"K");
//		} else {
//			orders.put("total_orders", total.divide(new BigDecimal("1"), 1, BigDecimal.ROUND_UP));
//		}
    	
    	orders.put("view_more", "/sales/orders.dr");
    	return orders;
	}
	
	/**
	 * TOTAL SALES
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	private Map<String,Object> getSales(CommandMap commandMap) throws Exception {
		Map<String,Object> sales = new HashMap<String,Object>();
    	sales.put("title", "총 주문 합계");
//    	// 지난주 주문합계
//    	String last_week = StringUtils.defaultValue(indexService.sumSale(commandMap.getMap()),"0");
//        sales.put("last_week", last_week);
//    	// 이번주 주문합계
//    	commandMap.put("from_date", DateUtils.addDays(DateUtils.getToday(), -8));
//    	commandMap.put("to_date", DateUtils.addDays(DateUtils.getToday(), -15));
//    	String this_week = StringUtils.defaultValue(indexService.sumSale(commandMap.getMap()),"0");
//    	sales.put("this_week", this_week);
//    	
//    	if(last_week.equals("0") || this_week.equals("0")) {
//    		sales.put("percentage", "0");
//    	} else {
//	    	long difference = Math.round(Double.parseDouble(this_week) - Double.parseDouble(last_week));
//	    	long percentage = difference / Math.round(Double.parseDouble(this_week)) * 100;
//	    	sales.put("percentage", percentage);
//    	}
    
    	BigDecimal total = new BigDecimal(StringUtils.defaultValue(indexService.totalSales(commandMap.getMap()),"0"));
    	sales.put("total_orders", total);
//    	if (total.compareTo(new BigDecimal("1000000000000"))>0) {
//    		sales.put("total_sales", total.divide(new BigDecimal("1000000000000"), 1, BigDecimal.ROUND_UP)+"T");
//		} else if (total.compareTo(new BigDecimal("1000000000"))>0) {
//			sales.put("total_sales", total.divide(new BigDecimal("1000000000"), 1, BigDecimal.ROUND_UP)+"B");
//		} else if (total.compareTo(new BigDecimal("1000000"))>0) {
//			sales.put("total_sales", total.divide(new BigDecimal("1000000"), 1, BigDecimal.ROUND_UP)+"M");
//		} else if (total.compareTo(new BigDecimal("1000"))>0) {
//			sales.put("total_sales", total.divide(new BigDecimal("1000"), 1, BigDecimal.ROUND_UP)+"K");
//		} else {
//			sales.put("total_sales", total.divide(new BigDecimal("1"), 1, BigDecimal.ROUND_UP));
//		}
    	
    	sales.put("view_more", "/sales/orders.dr");
    	
    	return sales;
	}
	
	/**
	 * TOTAL CUSTOMERS
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	private Map<String,Object> getCustomers(CommandMap commandMap) throws Exception {
		Map<String,Object> customers = new HashMap<String,Object>();
		customers.put("title", "총 고객 수");
//    	// 지난주 주문합계
//    	String last_week = StringUtils.defaultValue(indexService.sumCustomer(commandMap.getMap()),"0");
//        customers.put("last_week", last_week);
//    	// 이번주 주문합계
//    	commandMap.put("from_date", DateUtils.addDays(DateUtils.getToday(), -8));
//    	commandMap.put("to_date", DateUtils.addDays(DateUtils.getToday(), -15));
//    	String this_week = StringUtils.defaultValue(indexService.sumCustomer(commandMap.getMap()),"0");
//    	customers.put("this_week", this_week);
//    	
//    	if(last_week.equals("0") || this_week.equals("0")) {
//    		customers.put("percentage", "0");
//    	} else {
//	    	long difference = Math.round(Double.parseDouble(this_week) - Double.parseDouble(last_week));
//	    	long percentage = difference / Math.round(Double.parseDouble(this_week)) * 100;
//	    	customers.put("percentage", percentage);
//    	}
    
    	BigDecimal total = new BigDecimal(StringUtils.defaultValue(indexService.totalCustomers(commandMap.getMap()),"0"));
    	customers.put("total_orders", total);
//    	log.debug("total>>>>>>>>>>>>>"+total);
//    	if (total.compareTo(new BigDecimal("1000000000000"))>0) {
//    		customers.put("total_sales", total.divide(new BigDecimal("1000000000000"), 1, BigDecimal.ROUND_UP)+"T");
//		} else if (total.compareTo(new BigDecimal("1000000000"))>0) {
//			customers.put("total_sales", total.divide(new BigDecimal("1000000000"), 1, BigDecimal.ROUND_UP)+"B");
//		} else if (total.compareTo(new BigDecimal("1000000"))>0) {
//			customers.put("total_sales", total.divide(new BigDecimal("1000000"), 1, BigDecimal.ROUND_UP)+"M");
//		} else if (total.compareTo(new BigDecimal("1000"))>0) {
//			customers.put("total_sales", total.divide(new BigDecimal("1000"), 1, BigDecimal.ROUND_UP)+"K");
//		} else {
//			customers.put("total_sales", total.divide(new BigDecimal("1"), 1, BigDecimal.ROUND_UP));
//		}
    	
    	customers.put("view_more", "/customer/customer.dr");
    	
    	return customers;
	}
}
