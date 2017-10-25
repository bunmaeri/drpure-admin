package admin.myhome.products.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import admin.common.common.CommandMap;
import admin.common.constant.Session;
import admin.common.controller.BaseController;
import admin.myhome.common.controller.MyCodeController;
import admin.common.service.CommonService;
import admin.common.util.CommonUtils;
import admin.common.util.ObjectUtils;
import admin.common.util.Pagemaker;
import admin.common.util.ScriptUtils;
import admin.common.util.StoreUtils;
import admin.myhome.common.util.MyStoreUtils;
import admin.myhome.products.service.MyProductViewService;
import admin.myhome.products.service.MyProductsService;

@Controller
public class MyProductsController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="myProductsService")
	private MyProductsService myProductsService;
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	@Resource(name="myProductViewService")
	private MyProductViewService myProductViewService;
	
	private MyCodeController code = new MyCodeController();

	/**
	 * Products 자동 조회
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/my_products/autocomplete.dr")
    public ModelAndView autocomplete(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		commandMap.put("language_id", MyStoreUtils.getLanguageId());
		commandMap.put("name", commandMap.getMap().get("name"));
    	List<Map<String,Object>> list = myProductsService.autocompleteProducts(commandMap.getMap());
//    	log.debug("list==========>"+list.size());
    	mv.addObject("list", list);
    	
    	return mv;
    }
	
	/**
	 * 제품 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/my_products.dr")
    public ModelAndView salesOrder(HttpServletRequest request, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_products/products");
    
    	String array = "";
    	if(null!=BaseController.getCustomSession(request, Session.PRODUCT_IDS)) {
    		commandMap.put("array", BaseController.getCustomSession(request, Session.PRODUCT_IDS));
    		BaseController.setCustomSession(request, null, Session.PRODUCT_IDS);
    		array = "Y";
    	}

    	String name = ObjectUtils.null2void(commandMap.getMap().get("name"));
    	String reward_group_id = ObjectUtils.null2void(commandMap.getMap().get("reward_group_id"));
    	String status = ObjectUtils.null2void(commandMap.getMap().get("status"));
    	String model = ObjectUtils.null2void(commandMap.getMap().get("model"));
    	String quantity = ObjectUtils.null2void(commandMap.getMap().get("quantity"));
    	String manufacturer_id = ObjectUtils.null2void(commandMap.getMap().get("manufacturer_id"));
    	String page = ObjectUtils.null2Value(commandMap.getMap().get("page"),"1");
    	
    	// 검색 조건이 하나도 없으면 검색을 하지 않는다.
    	if(name.equals("") && reward_group_id.equals("") && status.equals("") && model.equals("") && quantity.equals("") && manufacturer_id.equals("") && array.equals("")) {
    	} else {
    		int count = 0;
	    	pagemaker.setPage(pagemaker.getPage());
	    	commandMap.put("language_id", MyStoreUtils.getLanguageId());
	    	commandMap.put("config_default_reward", code.getValue("config_default_reward"));
	    	count = myProductsService.totalProduct(commandMap.getMap()); // 레코드 총 갯수 구함
	    	pagemaker.setCount(count); // 페이지 계산
	
	    	commandMap.put("page", (pagemaker.getPage()-1)*pagemaker.getPER());
	    	commandMap.put("per_page", pagemaker.getPER());
	    	List<Map<String,Object>> list = myProductsService.listProduct(commandMap.getMap());
	    	mv.addObject("list", list);
	    	if(list.size() > 0){
	    		mv.addObject("pageMaker", pagemaker); 
	    	}
    	}
    	// 제조사 목록
    	List<Map<String, Object>> manufacturerList = commonService.manufacturerAll();
    	mv.addObject("manufacturerList", manufacturerList);
    	mv.addObject("param", commandMap.getMap());
    	mv.addObject("page", page);
    	
    	ScriptUtils.productsScript(mv);
    	
    	// 에러가 있을 때(Excel Export, GPS Tracking)
    	if(null!=BaseController.getCustomSession(request, Session.PRODUCTS_ERROR)) {
    		mv.addObject("errroMsg", BaseController.getCustomSession(request, Session.PRODUCTS_ERROR));
    		BaseController.setCustomSession(request, null, Session.PRODUCTS_ERROR);
    	}
    	// 성공 메세지가 있을 때(Excel Export, GPS Tracking)
    	if(null!=BaseController.getCustomSession(request, Session.PRODUCTS_SUCCESS)) {
    		mv.addObject("successMsg", BaseController.getCustomSession(request, Session.PRODUCTS_SUCCESS));
    		BaseController.setCustomSession(request, null, Session.PRODUCTS_SUCCESS);
    	}
    
    	// Return URL
    	BaseController.setCustomSession(request, CommonUtils.setReturnUrl(request), Session.PRODUCTS_URL);
    	
    	return mv;
    }
	
	/**
	 * 참조된 제품 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/my_products/reference.dr")
    public ModelAndView productsReference(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/my_products/reference_products");

		String product_id = commandMap.get2String("product_id");
		if(!product_id.equals("")) {
			commandMap.put("language_id", StoreUtils.getLanguageIdString());
			Map<String,Object> info = myProductViewService.productInfo(commandMap.getMap());
			mv.addObject("info", info);
			
			// 제품이 참조된 제품 목록
			commandMap.put("language_id", StoreUtils.getLanguageId());
			commandMap.put("product_id", "%product_id="+product_id+"%");
			List<Map<String,Object>> products = myProductsService.referenceProducts(commandMap.getMap());
			mv.addObject("products", products);
			
			// 제품이 참조된 건강 목록
			commandMap.put("code", "health");
			commandMap.put("product_id", "%product_id="+product_id+"%");
			List<Map<String,Object>> healths = myProductsService.referenceProductsInfomation(commandMap.getMap());
			mv.addObject("healths", healths);
			
			// 제품이 참조된 임상 목록
			commandMap.put("code", "clinic");
	//		commandMap.put("product_id", "%product_id="+commandMap.get2String("product_id")+"%");
			List<Map<String,Object>> clinics = myProductsService.referenceProductsInfomation(commandMap.getMap());
			mv.addObject("clinics", clinics);

			// 제품이 참조된 질병 목록
			commandMap.put("code", "disease");
	//		commandMap.put("product_id", "%product_id="+commandMap.get2String("product_id")+"%");
			List<Map<String,Object>> diseases = myProductsService.referenceProductsInfomation(commandMap.getMap());
			mv.addObject("diseases", diseases);
			
			// 제품이 참조된 생약제 목록
			commandMap.put("product_id", product_id);
			List<Map<String,Object>> medicinese = myProductsService.referenceProductsMedicine(commandMap.getMap());
			mv.addObject("medicinese", medicinese);
		}
		
		return mv;
    }

	/**
	 * BACK
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/my_product/back.dr")
    public ModelAndView back(HttpServletRequest request, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView();

    	// 마지막 검색 조건을 확인해서 있으면 그쪽으로 없으면..
    	if(null!=BaseController.getCustomSession(request, Session.PRODUCTS_URL)) {
    		String return_url = CommonUtils.getReturnUrl(mv, (ArrayList<Map<String,Object>>) BaseController.getCustomSession(request, Session.PRODUCTS_URL), "/my_products.dr");
    		mv.setViewName("redirect:"+return_url);
    	} else {
    		mv.setViewName("redirect:/my_products.dr");
    	}
    
    	return mv;
    }
}
