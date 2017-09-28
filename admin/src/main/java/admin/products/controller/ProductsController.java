package admin.products.controller;

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
import admin.common.constant.Session;
import admin.common.controller.BaseController;
import admin.common.controller.CodeController;
import admin.common.service.CommonService;
import admin.common.util.CommonUtils;
import admin.common.util.ObjectUtils;
import admin.common.util.Pagemaker;
import admin.common.util.ScriptUtils;
import admin.common.util.StoreUtils;
import admin.products.service.ProductViewService;
import admin.products.service.ProductsService;

@Controller
public class ProductsController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="productsService")
	private ProductsService productsService;
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	@Resource(name="productViewService")
	private ProductViewService productViewService;
	
	private CodeController code = new CodeController();
	
	/**
	 * Products 자동 조회
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/products/autocomplete.dr")
    public ModelAndView autocomplete(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		commandMap.put("language_id", StoreUtils.getLanguageId());
		commandMap.put("name", commandMap.getMap().get("name"));
    	List<Map<String,Object>> list = productsService.autocompleteProducts(commandMap.getMap());
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
	@RequestMapping(value="/products.dr")
    public ModelAndView salesOrder(HttpServletRequest request, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/products/products");
    	
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
	    	commandMap.put("language_id", StoreUtils.getLanguageId());
	    	commandMap.put("config_default_reward", code.getValue("config_default_reward"));
	    	count = productsService.totalProduct(commandMap.getMap()); // 레코드 총 갯수 구함
	    	pagemaker.setCount(count); // 페이지 계산
	
	    	commandMap.put("page", (pagemaker.getPage()-1)*pagemaker.getPER());
	    	commandMap.put("per_page", pagemaker.getPER());
	    	List<Map<String,Object>> list = productsService.listProduct(commandMap.getMap());
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
	@RequestMapping(value="/products/reference.dr")
    public ModelAndView productsReference(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/products/reference_products");

		String product_id = commandMap.get2String("product_id");
		if(!product_id.equals("")) {
			commandMap.put("language_id", StoreUtils.getLanguageIdString());
			Map<String,Object> info = productViewService.productInfo(commandMap.getMap());
			mv.addObject("info", info);
			
			// 제품이 참조된 제품 목록
			List<Map<String,Object>> products = productsService.referenceProducts(commandMap.getMap());
			mv.addObject("products", products);
			
			// 제품이 참조된 건강 목록
			commandMap.put("code", "health");
			commandMap.put("product_id", "%product_id="+product_id+"%");
			List<Map<String,Object>> healths = productsService.referenceProductsInfomation(commandMap.getMap());
			mv.addObject("healths", healths);
			
			// 제품이 참조된 임상 목록
			commandMap.put("code", "clinic");
	//		commandMap.put("product_id", "%product_id="+commandMap.get2String("product_id")+"%");
			List<Map<String,Object>> clinics = productsService.referenceProductsInfomation(commandMap.getMap());
			mv.addObject("clinics", clinics);

			// 제품이 참조된 질병 목록
			commandMap.put("code", "disease");
	//		commandMap.put("product_id", "%product_id="+commandMap.get2String("product_id")+"%");
			List<Map<String,Object>> diseases = productsService.referenceProductsInfomation(commandMap.getMap());
			mv.addObject("diseases", diseases);
			
			// 제품이 참조된 생약제 목록
			commandMap.put("product_id", product_id);
			List<Map<String,Object>> medicinese = productsService.referenceProductsMedicine(commandMap.getMap());
			mv.addObject("medicinese", medicinese);
		}
		
		return mv;
    }
	
	/**
	 * 제조사 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/products/manufacturer.dr")
    public ModelAndView productsManufacturer(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/products/manufacturer");

		// 제조사 목록
		List<Map<String,Object>> list = productsService.productsManufacturer(commandMap.getMap());
		mv.addObject("list", list);
		
		if(null!=BaseController.getCustomSession(request, Session.SUCCESS)) {
    		mv.addObject("successMsg", BaseController.getCustomSession(request, Session.SUCCESS));
    		BaseController.setCustomSession(request, null, Session.SUCCESS);
    	}
		
		return mv;
    }
	
	/**
	 * 제조사 저장
	 * @param commandMap       
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/products/manufacturer/save.dr")
    public ModelAndView productSaveCategory(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/products/manufacturer.dr");
		
		String[] manufacturer_ids = request.getParameterValues("manufacturer_id");
		String[] names = request.getParameterValues("name");
		int size = manufacturer_ids.length;
		Map<String,Object> map = null;
		for(int i=0;i<size;i++) {
			map = new HashMap<String,Object>();
			if(!manufacturer_ids[i].equals("") && !names[i].equals("")) {
				map.put("manufacturer_id", manufacturer_ids[i]);
				map.put("name", names[i]);
				productsService.updateManufacturer(map);
			} else 
			if(!names[i].equals("")) {
				map.put("name", names[i]);
				map.put("image", "");
				map.put("sort_order", "0");
				productsService.addManufacturer(map);
			}
		}
		
		BaseController.setCustomSession(request, "정상적으로 저장되었습니다.", Session.SUCCESS);
    	
       	return mv;
    }
	
	/**
	 * 제조사 삭제
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/products/manufacturer/delete/{manufacturer_id}.dr")
    public ModelAndView deleteManufacturer(HttpServletRequest request, @PathVariable String manufacturer_id, CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/products/manufacturer.dr");

    	commandMap.put("manufacturer_id", manufacturer_id);
    	productsService.deleteManufacturer(commandMap.getMap());
    	
    	BaseController.setCustomSession(request, "정상적으로 삭제되었습니다.", Session.SUCCESS);
    	return mv;
    }

	/**
	 * BACK
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/product/back.dr")
    public ModelAndView back(HttpServletRequest request, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView();

    	// 마지막 검색 조건을 확인해서 있으면 그쪽으로 없으면..
    	if(null!=BaseController.getCustomSession(request, Session.PRODUCTS_URL)) {
    		String return_url = CommonUtils.getReturnUrl(mv, (ArrayList<Map<String,Object>>) BaseController.getCustomSession(request, Session.PRODUCTS_URL), "/products.dr");
    		mv.setViewName("redirect:"+return_url);
    	} else {
    		mv.setViewName("redirect:/products.dr");
    	}
    
    	return mv;
    }
}
