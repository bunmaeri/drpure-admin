package admin.myhome.report.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import admin.common.util.DateUtils;
import admin.common.util.MetaUtils;
import admin.common.util.ObjectUtils;
import admin.common.util.Pagemaker;
import admin.common.util.ScriptUtils;
import admin.common.util.StoreUtils;
import admin.myhome.report.service.MyReportProductsService;

@Controller
public class MyReportProductsController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="myReportProductsService")
	private MyReportProductsService myReportProductsService;
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	/**
	 * Report > Products > Viewed
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/my_reports/products/viewed.dr")
    public ModelAndView reportProductsViewed(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_report/products_viewed");
    	String page = ObjectUtils.null2Value(commandMap.getMap().get("page"),"1");
    	
    	int count = 0;
    	pagemaker.setPage(pagemaker.getPage());
    	count = myReportProductsService.totalProductsViewed(); // 레코드 총 갯수 구함
    	pagemaker.setCount(count); // 페이지 계산
    	
    	int product_viewed_total = myReportProductsService.totalProductViews(); // 전체 제품의 조회건수 합계 (% 구하기 위해서)

    	commandMap.put("page", (pagemaker.getPage()-1)*pagemaker.getPER());
    	commandMap.put("per_page", pagemaker.getPER());
    	commandMap.put("language_id", StoreUtils.getLanguageId());
    	List<Map<String,Object>> rtnList = new ArrayList<Map<String,Object>>();
    	List<Map<String,Object>> list = myReportProductsService.productsViewed(commandMap.getMap());
    	Map<String,Object> map = null;
    	int size = list.size();
    	for(int i=0;i<size;i++) {
    		map = list.get(i);
    		if (null!=map.get("viewed")) {
    			map.put("percent", new BigDecimal(ObjectUtils.null2void(map.get("viewed"))).divide(new BigDecimal(product_viewed_total), 2, BigDecimal.ROUND_UP));
			} else {
				map.put("percent", "0");
			}
    		rtnList.add(map);
    	}
    	
    	mv.addObject("list", rtnList);
    	if(rtnList.size() > 0){
    		mv.addObject("pageMaker", pagemaker); 
    	}
    	
    	mv.addObject("page", page);
    	MetaUtils.reportProductsViewed(mv);
    	ScriptUtils.reportsScript(mv);
    
    	return mv;
    }
	
	/**
	 * Report > Products > Purchased
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/my_reports/products/purchased.dr")
    public ModelAndView reportProductsPurchased(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_report/products_purchased");
    	
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
    	count = myReportProductsService.totalProductsPurchased(commandMap.getMap()); // 레코드 총 갯수 구함
    	pagemaker.setCount(count); // 페이지 계산

    	commandMap.put("page", (pagemaker.getPage()-1)*pagemaker.getPER());
    	commandMap.put("per_page", pagemaker.getPER());
    	List<Map<String,Object>> list = myReportProductsService.productsPurchased(commandMap.getMap());
    	mv.addObject("list", list);
    	if(list.size() > 0){
    		mv.addObject("pageMaker", pagemaker); 
    	}
    	
    	mv.addObject("page", page);
    	// manufacturer 코드 목록
    	mv.addObject("manufacturers", commonService.manufacturerAll());
    	
    	// order status 코드 목록
    	commandMap.put("language_id", StoreUtils.getLanguageId());
    	mv.addObject("orderStatus", commonService.orderStatus(commandMap.getMap()));
    	
    	// 파라미터 값 셋팅
    	Map<String,Object> filter = new HashMap<String,Object>();
    	filter.put("date_start", date_start);
    	filter.put("date_end", date_end);
    	filter.put("manufacturer_id", ObjectUtils.null2void(commandMap.getMap().get("manufacturer_id")));
    	filter.put("order_status_id", ObjectUtils.null2void(commandMap.getMap().get("order_status_id")));
    	mv.addObject("filter", filter);
    	
    	MetaUtils.reportProductsPurchased(mv);
    	ScriptUtils.reportsScript(mv);
    	
    	return mv;
    }
}
