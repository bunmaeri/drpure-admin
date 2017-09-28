package admin.contents.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import admin.common.common.CommandMap;
import admin.common.constant.Message;
import admin.common.constant.Session;
import admin.common.controller.BaseController;
import admin.common.util.FileUtils;
import admin.common.util.MetaUtils;
import admin.common.util.ObjectUtils;
import admin.common.util.Pagemaker;
import admin.common.util.ScriptUtils;
import admin.common.util.StoreUtils;
import admin.contents.service.SecurityCategoriesService;
import admin.system.service.LanguagesService;

@Controller
public class SecurityCategoriesController extends BaseController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="securityCategoriesService")
	private SecurityCategoriesService securityCategoriesService;
	
	@Resource(name="languagesService")
	private LanguagesService languagesService;
	
	@Resource(name="fileUtils")
    private FileUtils fileUtils;

	/**
	 * Contents > Security 카테고리
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/security/categories.dr")
    public ModelAndView securityCategories(CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/contents/security_categories");
    
       	commandMap.put("language_id", StoreUtils.getLanguageId());
    	List<Map<String,Object>> list = securityCategoriesService.securityCategories(commandMap.getMap());
    	mv.addObject("list", list);
    	
    	MetaUtils.contentsInformation(mv);
    	ScriptUtils.contentsViewImaeScript(mv);
    	
    	return mv;
    }

	/**
	 * Contents > Security 카테고리 ADD
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/security/category/add.dr")
    public ModelAndView securityCategoriesNew(CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/contents/security_category_info");
    	
    	Map<String,Object> info = new HashMap<String,Object>();
    	info.put("language_id", StoreUtils.getLanguageId());
    	
    	mv.addObject("languages", languagesService.languages());
    	mv.addObject("title", "Add");
    	mv.addObject("code", "");
    	mv.addObject("language_id", StoreUtils.getLanguageId());
    	
    	MetaUtils.contentsInformation(mv);
    	ScriptUtils.contentsViewImaeScript(mv);
    	
    	return mv;
    }
	
	/**
	 * Contents > Security 카테고리 VIEW
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/security/category/info/{code}/{language_id}.dr")
    public ModelAndView securityCategoriesEdit(@PathVariable String code, @PathVariable String language_id, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/contents/security_category_info");
    	
    	/**
    	 * 기본 정보
    	 */
    	commandMap.put("code", code);
    	commandMap.put("language_id", 1);
    	
    	Map<String,Object> category = securityCategoriesService.securityCategoryInfo(commandMap.getMap());
    	mv.addObject("category", category.get("map"));
    	
    	/**
    	 * 언어별 정보
    	 */
    	commandMap.put("language_id", language_id);
    	Map<String,Object> info = securityCategoriesService.securityCategoryInfo(commandMap.getMap());
    	mv.addObject("info", info.get("map"));
    	
    	mv.addObject("languages", languagesService.languages());
    	mv.addObject("title", "Edit");
    	mv.addObject("code", code);
    	mv.addObject("language_id", language_id);
    	
    	
    	MetaUtils.contentsInformation(mv);
    	ScriptUtils.contentsViewImaeScript(mv);
    	
    	return mv;
    }
	
	/**
	 * Security 카테고리 저장
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/security/category/save.dr")
    public ModelAndView securityCategoriesSave(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
    	
		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validForm(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
			int count = securityCategoriesService.isSecurityCategory(commandMap.getMap());
			if(count==0) {
				securityCategoriesService.addSecurityCategory(commandMap.getMap());
			} else {
				securityCategoriesService.updateSecurityCategory(commandMap.getMap());
			}
			
			mv.addObject("Success", Message.SUCCESS);
		} else {
			validMap.put("warning", Message.WARNING);
			mv.addObject("Error", validMap);
		}
    	return mv;
    }
	
	public void validForm(Map<String, Object> validMap, CommandMap commandMap) throws Exception{
		String code = commandMap.get("code").toString();
		if(ObjectUtils.isEmpty(code) || (code.length()<1 && code.length()>64)) {
			BaseController.setErrorMessage(validMap, "code", "필수 입력입니다.");
		}
		
		String title = commandMap.get("title").toString();
		if(ObjectUtils.isEmpty(title) || (title.length()<3 && title.length()>64)) {
			BaseController.setErrorMessage(validMap, "title", "필수 입력입니다.");
		}
		
		String description = commandMap.get("description").toString();
		if(ObjectUtils.isEmpty(description) || description.length()<1) {
			BaseController.setErrorMessage(validMap, "description", "필수 입력입니다.");
		}
		
		String meta_title = commandMap.get("meta_title").toString();
		if(ObjectUtils.isEmpty(meta_title) || meta_title.length()<1) {
			BaseController.setErrorMessage(validMap, "meta_title", "필수 입력입니다.");
		}
	}
	
	/**
	 * 이미지 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/contents/security/category/image/{code}/{language_id}.dr")
    public ModelAndView securityCategoriesViewImage(HttpServletRequest request, @PathVariable String code, @PathVariable String language_id, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/contents/security_category_image");
    	
    	/**
    	 * 기본 정보
    	 */
    	commandMap.put("code", code);
    	commandMap.put("language_id", 1);
    	
    	Map<String,Object> info = securityCategoriesService.securityCategoryInfo(commandMap.getMap());
    	mv.addObject("info", info.get("map")); //
   
    	mv.addObject("code", code);
    	mv.addObject("language_id", language_id);
    	
    	if(null!=BaseController.getCustomSession(request, Session.SUCCESS)) {
    		mv.addObject("successMsg", BaseController.getCustomSession(request, Session.SUCCESS));
    		BaseController.setCustomSession(request, null, Session.SUCCESS);
    	}
    
    	ScriptUtils.categoryViewImaeScript(mv);

    	return mv;
    }
	
	/**
	 * 이미지 페이지
	 * @param commandMap       
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/contents/security/category/save/image.dr", method = RequestMethod.POST)
    public ModelAndView securityCategoriesSaveImage(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/contents/security/category/image/"+commandMap.get2String("code")+"/"+commandMap.get2String("language_id")+".dr");

		String folder = "catalog/title-images/";
		fileUtils.setFilePath(StoreUtils.getFilepath()+folder);
		fileUtils.setViewPath(folder);
		fileUtils.parseInsertFileInfo(commandMap.getMap(), request);
		
		if(!commandMap.get2String("image").equals("")) {
//			commandMap.put("image", commandMap.get2String("original_image"));
			securityCategoriesService.updateImage(commandMap.getMap());
		}
		
		BaseController.setCustomSession(request, "정상적으로 저장되었습니다.", Session.SUCCESS);
//		mv.addObject("success", "정상적으로 저장되었습니다.");
    	
       	return mv;
    }
	
	/**
	 * Security 카테고리 삭제
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/security/category/delete/{code}.dr")
    public ModelAndView deleteSecurityCategories(@PathVariable String code, CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/contents/security/categories.dr");

    	commandMap.put("code", code);
    	securityCategoriesService.deleteSecurityCategory(commandMap.getMap());
    	
    	return mv;
    }
}
