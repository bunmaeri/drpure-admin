package admin.myhome.categories.controller;

import java.util.ArrayList;
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
import admin.myhome.categories.service.MyCategoriesService;

@Controller
public class MyCategoriesController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="myCategoriesService")
	private MyCategoriesService myCategoriesService;
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	private CodeController code = new CodeController();
	
	/**
	 * categories 자동 조회
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/my_categories/autocomplete.dr")
    public ModelAndView categoriesAutocomplete(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
//		commandMap.put("language_id", StoreUtils.getLanguageId());
//		commandMap.put("name", commandMap.getMap().get("name"));
//    	List<Map<String,Object>> list = productsService.autocompleteProducts(commandMap.getMap());
////    	log.debug("list==========>"+list.size());
//    	mv.addObject("list", list);
    	
    	return mv;
    }
	
	/**
	 * 카테고리 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/my_categories.dr")
    public ModelAndView categories(HttpServletRequest request, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_categories/categories");

		commandMap.put("language_id", StoreUtils.getLanguageId());
    	List<Map<String,Object>> list = myCategoriesService.categories(commandMap.getMap());
    	mv.addObject("list", list);
    	
    	ScriptUtils.categoriesScript(mv);
    	
    	return mv;
    }
}
