package admin.myhome.categories.controller;

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
import admin.common.constant.Session;
import admin.common.controller.BaseController;
import admin.common.service.CommonService;
import admin.common.util.FileUtils;
import admin.common.util.ObjectUtils;
import admin.common.util.ScriptUtils;
import admin.common.util.StoreUtils;
import admin.myhome.categories.service.MyCategoriesService;
import admin.myhome.categories.service.MyCategoryViewService;
import admin.system.service.LanguagesService;

@Controller
public class MyCategoryViewController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="myCategoriesService")
	private MyCategoriesService categoriesService;
	
	@Resource(name="myCategoryViewService")
	private MyCategoryViewService categoryViewService;
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	@Resource(name="languagesService")
	private LanguagesService languagesService;
	
	@Resource(name="fileUtils")
    private FileUtils fileUtils;
	
	/**
	 * 카테고리 상세 페이지(Meta)
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/my_category/view/meta/{category_id}/{language_id}.dr")
    public ModelAndView categoryViewMeta(HttpServletRequest request, @PathVariable String category_id, @PathVariable String language_id, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_categories/category_view_meta");
    	
    	commandMap.put("category_id", category_id);
    	commandMap.put("language_id", StoreUtils.getLanguageId());
    	/**
    	 * 한글 Meta 조회
    	 */
    	Map<String,Object> category = categoryViewService.categoryMeta(commandMap.getMap());
    	mv.addObject("category", category); // 제품 Meta 정보
    	
    	/**
    	 * 카테고리 Meta 조회
    	 */
    	commandMap.put("language_id", language_id);
    	Map<String,Object> info = categoryViewService.categoryMeta(commandMap.getMap());
    	mv.addObject("info", info); // 제품 Meta 정보
    	
    	/**
    	 * 카테고리 목록
    	 */
    	List<Map<String,Object>> list = categoriesService.categories(commandMap.getMap());
    	Map<String,Object> firstMap = new HashMap<String,Object>();
    	firstMap.put("category_id", "0");
    	firstMap.put("path_name", "최상위 카테고리");
    	list.add(0, firstMap);
    	mv.addObject("categories", list);
    	
    	mv.addObject("languages", languagesService.languages()); // 언어 종류

    	mv.addObject("category_id", category_id);
    	mv.addObject("language_id", language_id);
    	
    	ScriptUtils.categoryViewScript(mv);

    	return mv;
    }

	/**
	 * 카테고리 저장 페이지(Meta)
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/my_category/save/meta.dr")
    public ModelAndView categorySaveMeta(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");

		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validFormMeta(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
			// 기존에 존재하는지 체크
			Map<String,Object> info = categoryViewService.categoryMeta(commandMap.getMap());
			if(null!=info && null!=info.get("category_id")) {
				categoryViewService.updateCategoryMeta(commandMap.getMap()); // 업데이트
				categoryViewService.updateCategoryParent(commandMap.getMap()); // 카테고리 Parent 저장
			} else {
				if(!commandMap.get2String("category_id").equals("0")) {
					categoryViewService.addCategoryMeta(commandMap.getMap()); // 추가
					categoryViewService.updateCategoryParent(commandMap.getMap()); // 카테고리 Parent 저장
//					categoryViewService.updateCategoryPaths(commandMap.getMap()); // 카테고리 path 저장
				} else {
					commandMap.put("category_id", categoryViewService.maxCategoryId()); // MAX 번호 가져오기
					commandMap.put("image", "catalog/title-images/blank.png");
					commandMap.put("top", "1");
					commandMap.put("column", "1");
					commandMap.put("sort_order", "1");
					categoryViewService.newCategory(commandMap.getMap()); // 테이블에 신규로 추가
				
					categoryViewService.addCategoryMeta(commandMap.getMap()); // Meta NEW
					
					mv.addObject("success_category_id", commandMap.get("category_id"));
					mv.addObject("success_language_id", commandMap.get("language_id"));
				}
			}
			categoryViewService.updateCategoryPaths(commandMap.getMap()); // 카테고리 path 저장
			mv.addObject("success", "정상적으로 저장되었습니다.");
		} else {
			mv.addObject("error", "오류 메세지를 확인해주십시요.");
			mv.addObject("Message", validMap);
		}
    
       	return mv;
    }
	/**
	 * Meta Validation
	 * @param validMap
	 * @param commandMap
	 * @throws Exception
	 */
	public void validFormMeta(Map<String, Object> validMap, CommandMap commandMap) throws Exception{
		String name = commandMap.get2String("name");
		if(ObjectUtils.isEmpty(name) || (name.length()<3 && name.length()>255)) {
			BaseController.setErrorMessage(validMap, "error_name", "3~255글자 사이로 입력하세요.");
		}
		
//		String description = commandMap.get2String("description");
//		if(ObjectUtils.isEmpty(description) || description.length()<1) {
//			BaseController.setErrorMessage(validMap, "error_description", Health.Error.DESCRIPTION);
//		}
		
		String meta_title = commandMap.get2String("meta_title");
		if(!ObjectUtils.isEmpty(meta_title) && meta_title.length()>255) {
			BaseController.setErrorMessage(validMap, "error_meta_title", "255글자보다 작아야 합니다.");
		}
		
		String meta_description = commandMap.get2String("meta_description");
		if(!ObjectUtils.isEmpty(meta_description) && meta_description.length()>255) {
			BaseController.setErrorMessage(validMap, "error_meta_description", "255글자보다 작아야 합니다.");
		}
		
		String meta_keyword = commandMap.get2String("meta_keyword");
		if(!ObjectUtils.isEmpty(meta_keyword) && meta_keyword.length()>255) {
			BaseController.setErrorMessage(validMap, "error_meta_keyword", "255글자보다 작아야 합니다.");
		}
	}
	
	/**
	 * 이미지 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/my_category/view/image/{category_id}/{language_id}.dr")
    public ModelAndView categoryViewImage(HttpServletRequest request, @PathVariable String category_id, @PathVariable String language_id, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_categories/category_view_image");
    	
    	commandMap.put("category_id", category_id);
    	commandMap.put("language_id", 1);
    	/**
    	 * 한글 제품 Meta 조회
    	 */
    	Map<String,Object> category = categoryViewService.categoryMeta(commandMap.getMap());
    	mv.addObject("category", category); // 제품 Meta 정보
   
    	mv.addObject("category_id", category_id);
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
	@RequestMapping(value= "/my_category/save/image.dr", method = RequestMethod.POST)
    public ModelAndView categorySaveImage(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/my_category/view/image/"+commandMap.get2String("category_id")+"/"+commandMap.get2String("language_id")+".dr");

		String folder = "catalog/title-images/";
		fileUtils.setFilePath(StoreUtils.getFilepath()+folder);
		fileUtils.setViewPath(folder);
		fileUtils.parseInsertFileInfo(commandMap.getMap(), request);
		
		if(!commandMap.get2String("image").equals("")) {
//			commandMap.put("image", commandMap.get2String("original_image"));
			categoryViewService.updateImage(commandMap.getMap());
		}
		
		BaseController.setCustomSession(request, "정상적으로 저장되었습니다.", Session.SUCCESS);
//		mv.addObject("success", "정상적으로 저장되었습니다.");
    	
       	return mv;
    }

}
