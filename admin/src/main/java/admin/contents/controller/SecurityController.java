package admin.contents.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import admin.common.common.CommandMap;
import admin.common.constant.Message;
import admin.common.controller.BaseController;
import admin.common.util.MetaUtils;
import admin.common.util.ObjectUtils;
import admin.common.util.Pagemaker;
import admin.common.util.ScriptUtils;
import admin.common.util.StoreUtils;
import admin.contents.language.Security;
import admin.contents.service.SecurityService;
import admin.products.service.ProductsService;
import admin.system.service.LanguagesService;

@Controller
public class SecurityController extends BaseController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="securityService")
	private SecurityService securityService;
	
	@Resource(name="languagesService")
	private LanguagesService languagesService;
	
	@Resource(name="productsService")
	private ProductsService productsService;
	
	/**
	 * Contents > 보안 정보
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/security/{code}.dr")
    public ModelAndView security(HttpSession session, @PathVariable String code, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/contents/"+code);
    	
    	commandMap.put("code", code);
    	commandMap.put("language_id", StoreUtils.getLanguageId());
    	String filter_name = ObjectUtils.null2void(commandMap.getMap().get("filter_name"));
    	if(!filter_name.equals("")) {
    		commandMap.put("title", filter_name);
    		BaseController.setCustomSession(session, filter_name, "SS_FILTER_NAME");
    	} else {
    		if(null!=BaseController.getCustomSession(session, "SS_FILTER_NAME_CANCEL")) {
    			filter_name = ObjectUtils.null2void(BaseController.getCustomSession(session, "SS_FILTER_NAME_CANCEL"));
    			commandMap.put("title", filter_name);
    			BaseController.setCustomSession(session, null, "SS_FILTER_NAME_CANCEL");
    		}
    	}
    	Map<String,Object> map = securityService.security(commandMap.getMap());
    	mv.addObject("info", map.get("map"));
    	
    	int count = 0;
    	pagemaker.setPage(pagemaker.getPage());
    	count = securityService.totalSecurityContents(commandMap.getMap()); // 레코드 총 갯수 구함
    	pagemaker.setCount(count); // 페이지 계산

    	commandMap.put("page", (pagemaker.getPage()-1)*pagemaker.getPER());
    	commandMap.put("per_page", pagemaker.getPER());
    	List<Map<String,Object>> list = securityService.securityContents(commandMap.getMap());
    	mv.addObject("list", list);
    	if(list.size() > 0){
    		mv.addObject("pageMaker", pagemaker); 
    	}
    	else{
    		mv.addObject("TOTAL", 0);
    	}
    	mv.addObject("filter_name", filter_name);
    	
    	List<Map<String,Object>> all = securityService.securityContentsAll(commandMap.getMap());
    	mv.addObject("all", all);
    	
    	MetaUtils.contentsSecurity(mv, code);
    	
    	return mv;
    }
	
	/**
	 * Contents > 보안 정보
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/security/{code}/cancel.dr")
    public ModelAndView securityCancel(HttpSession session, @PathVariable String code, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
		if(null!=BaseController.getCustomSession(session, "SS_FILTER_NAME")) {
			String filter_name = ObjectUtils.null2void(BaseController.getCustomSession(session, "SS_FILTER_NAME"));
			commandMap.put("title", filter_name);
			BaseController.setCustomSession(session, filter_name, "SS_FILTER_NAME_CANCEL");
			BaseController.setCustomSession(session, null, "SS_FILTER_NAME");
		}
		
    	return security(session, code, pagemaker, commandMap);
    }
	
	/**
	 * Contents > 보안정보 Edit
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/security/{code}/info/{language_id}.dr")
    public ModelAndView securityInfo(@PathVariable String code, @PathVariable String language_id, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/contents/"+code+"_info");
    	
    	/**
    	 * 기본정보
    	 */
    	commandMap.put("code", code);
    	commandMap.put("language_id", 1);
    	Map<String,Object> health = securityService.securityInfo(commandMap.getMap());
    	mv.addObject("health", health.get("map"));
    	
    	/**
    	 * 
    	 */
    	commandMap.put("language_id", language_id);
    	
    	Map<String,Object> info = securityService.securityInfo(commandMap.getMap());
    	mv.addObject("info", info.get("map"));
    	
    	mv.addObject("languages", languagesService.languages());
    	mv.addObject("title", "Edit");
    	mv.addObject("language_id", language_id);
    	mv.addObject("code", code);
    	
    	MetaUtils.contentsSecurity(mv, code);
    	
    	return mv;
    }
	
	/**
	 * Contents > 보안정보 저장
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/security/{code}/save.dr")
    public ModelAndView updatesecurity(@PathVariable String code, CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
    	
		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validForm(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
			securityService.updateSecurity(commandMap.getMap());
			
			mv.addObject("Success", Message.SUCCESS);
		} else {
			validMap.put("warning", Message.WARNING);
			mv.addObject("Error", validMap);
		}
    	return mv;
    }
	
	/**
	 * Contents > 보안정보 컨텐츠 추가
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/security/{code}/add.dr")
    public ModelAndView securityContentsNew(@PathVariable String code, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/contents/"+code+"_contents");
    	
    	Map<String,Object> info = new HashMap<String,Object>();
    	info.put("language_id", StoreUtils.getLanguageId());
    	
    	mv.addObject("languages", languagesService.languages());
    	mv.addObject("title", "Add");
    	mv.addObject("contents_id", "0");
    	mv.addObject("language_id", StoreUtils.getLanguageId());
    	mv.addObject("code", code);
    	
    	MetaUtils.contentsSecurity(mv, code);
    	
    	return mv;
    }
	
	/**
	 * Contents > 보안정보 컨텐츠 Edit -> 이게 진짜.
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/security/{code}/contents/{contents_id}/{language_id}.dr")
    public ModelAndView securityContentsInfo(@PathVariable String code, @PathVariable String contents_id, @PathVariable String language_id, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/contents/"+code+"_contents");
    	
    	/**
    	 * 기본정보
    	 */
    	commandMap.put("contents_id", contents_id);
    	commandMap.put("language_id", 1);
    	commandMap.put("code", code);
    	Map<String,Object> main = securityService.securityContentsInfo(commandMap.getMap());
    	mv.addObject("main", main.get("map"));
    	
    	/**
    	 * 
    	 */
    	commandMap.put("language_id", language_id);
    	Map<String,Object> info = securityService.securityContentsInfo(commandMap.getMap());
    	mv.addObject("info", info.get("map"));
    	
    	mv.addObject("languages", languagesService.languages());
    	mv.addObject("title", "Edit");
    	mv.addObject("contents_id", contents_id);
    	mv.addObject("language_id", language_id);
    	mv.addObject("code", code);
    	
//    	commandMap.put("language_id", 1);
//    	List<Map<String,Object>> productList = productsService.autocompleteProducts(commandMap.getMap());
//    	mv.addObject("productList", productList);
    	
    	if(code.equals("disease")) {
	    	commandMap.put("medicine_id", "2");
	    	mv.addObject("required_products", securityService.securityContentsMedicine(commandMap.getMap()));
	    	
	    	commandMap.put("medicine_id", "3");
	    	mv.addObject("additional_products", securityService.securityContentsMedicine(commandMap.getMap()));
    	}
    	
    	ScriptUtils.securityDiseaseViewScript(mv);
    	
    	return mv;
    }
	
	/**
	 * Contents > 보안정보 컨텐츠 저장
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/security/{code}/contents/save.dr")
    public ModelAndView updatesecurityContents(@PathVariable String code, CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
    	
		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validForm(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
			commandMap.put("code", code);
			int count = securityService.isSecurityContents(commandMap.getMap());
			if(count==0) {
				if(commandMap.getMap().get("contents_id").equals("0")) {
					commandMap.put("contents_id", securityService.maxSecurityContentsId(commandMap.getMap()));
				}
				securityService.addSecurityContents(commandMap.getMap());
			} else {
				securityService.updateSecurityContents(commandMap.getMap());
			}
			
			// 해당 질병과 추천생약제의 전체 제품 삭제
			securityService.deleteSecurityContentsMedicine(commandMap.getMap());
			
			// 필수 추천 생약제 등록
			if(null!=commandMap.get("required_category_product")) {
				String classType = commandMap.get("required_category_product").getClass().getName();
				if(classType.equals("[Ljava.lang.String;")) {
					String[] required_category_product = (String[]) commandMap.get("required_category_product");
					if(null!=required_category_product) {
						int size = required_category_product.length;
						for(int i=0;i<size;i++) {
							commandMap.put("medicine_id", "2");
							commandMap.put("product_id", required_category_product[i]);
							securityService.addSecurityContentsMedicine(commandMap.getMap());
						}
						
					}
				} else
				if(classType.equals("java.lang.String")) {
					String required_category_product = (String) commandMap.get("required_category_product");
					commandMap.put("medicine_id", "2");
					commandMap.put("product_id", required_category_product);
					securityService.addSecurityContentsMedicine(commandMap.getMap());
				}
			}
			
			// 추가 추천 생약제 등록
			if(null!=commandMap.get("additional_category_product")) {
				String classType = commandMap.get("additional_category_product").getClass().getName();
				if(classType.equals("[Ljava.lang.String;")) {
					String[] additional_category_product = (String[]) commandMap.get("additional_category_product");
					if(null!=additional_category_product) {
						int size = additional_category_product.length;
						for(int i=0;i<size;i++) {
							commandMap.put("medicine_id", "3");
							commandMap.put("product_id", additional_category_product[i]);
							securityService.addSecurityContentsMedicine(commandMap.getMap());
						}
						
					}
				} else
				if(classType.equals("java.lang.String")) {
					String additional_category_product = (String) commandMap.get("additional_category_product");
					commandMap.put("medicine_id", "3");
					commandMap.put("product_id", additional_category_product);
					securityService.addSecurityContentsMedicine(commandMap.getMap());
				}
			}
			
			mv.addObject("Success", Message.SUCCESS);
		} else {
			validMap.put("warning", Message.WARNING);
			mv.addObject("Error", validMap);
		}
    	return mv;
    }
	
	public void validForm(Map<String, Object> validMap, CommandMap commandMap) throws Exception{
		String title = commandMap.get("title").toString();
		if(ObjectUtils.isEmpty(title) || (title.length()<3 && title.length()>64)) {
			BaseController.setErrorMessage(validMap, "title", Security.Error.TITLE);
		}
		
		String description = commandMap.get("description").toString();
		if(ObjectUtils.isEmpty(description) || description.length()<1) {
			BaseController.setErrorMessage(validMap, "description", Security.Error.DESCRIPTION);
		}
		
		String meta_title = commandMap.get("meta_title").toString();
		if(ObjectUtils.isEmpty(meta_title) || meta_title.length()<1) {
			BaseController.setErrorMessage(validMap, "meta_title", Security.Error.META_TAG_TITLE);
		}
	}
	
	/**
	 * Contents > 보안정보 컨텐츠 삭제
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/security/{code}/delete/{contents_id}.dr")
    public ModelAndView deletesecurityContents(@PathVariable String code, @PathVariable String contents_id, CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/contents/"+code+".dr");

    	commandMap.put("contents_id", contents_id);
    	securityService.deleteSecurityContents(commandMap.getMap());
    	
    	return mv;
    }
	
	/**
	 * Contents > 보안정보 컨텐츠 자동 조회
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/security/{code}/autocomplete.dr")
    public ModelAndView autocomplete(@PathVariable String code, CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		commandMap.put("code", code);
		commandMap.put("language_id", StoreUtils.getLanguageId());
		commandMap.put("title", commandMap.getMap().get("filter_name"));
    	List<Map<String,Object>> list = securityService.securityContentsAll(commandMap.getMap());
//    	log.debug("list==========>"+list.size());
    	mv.addObject("list", list);
    	
    	return mv;
    }
}
