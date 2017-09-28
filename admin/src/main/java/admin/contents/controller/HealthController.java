package admin.contents.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import admin.common.util.StoreUtils;
import admin.contents.language.Health;
import admin.contents.service.HealthService;
import admin.system.service.LanguagesService;

@Controller
public class HealthController extends BaseController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="healthService")
	private HealthService healthService;
	
	@Resource(name="languagesService")
	private LanguagesService languagesService;
	
	/**
	 * Contents > 건강정보
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/_contents/health.dr")
    public ModelAndView notice(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/contents/health");
    	
    	Map<String,Object> map = healthService.health(commandMap.getMap());
    	mv.addObject("info", map.get("map"));
    	
    	int count = 0;
    	pagemaker.setPage(pagemaker.getPage());
    	count = healthService.totalHealthContents(commandMap.getMap()); // 레코드 총 갯수 구함
    	pagemaker.setCount(count); // 페이지 계산

    	commandMap.put("language_id", StoreUtils.getLanguageId());
    	commandMap.put("page", (pagemaker.getPage()-1)*pagemaker.getPER());
    	commandMap.put("per_page", pagemaker.getPER());
    	List<Map<String,Object>> list = healthService.healthContents(commandMap.getMap());
    	mv.addObject("list", list);
    	if(list.size() > 0){
    		mv.addObject("pageMaker", pagemaker); 
    	}
    	else{
    		mv.addObject("TOTAL", 0);
    	}
    	
    	MetaUtils.contentsHealth(mv);
    	
    	return mv;
    }
	
	/**
	 * Contents > 건강정보 추가
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/_contents/health/add.dr")
    public ModelAndView healthContentsNew(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/contents/health_info");
    	
    	Map<String,Object> info = new HashMap<String,Object>();
    	info.put("language_id", StoreUtils.getLanguageId());
    	
    	mv.addObject("languages", languagesService.languages());
    	mv.addObject("title", "Add");
    	mv.addObject("contents_id", "0");
    	mv.addObject("language_id", StoreUtils.getLanguageId());
    	
    	MetaUtils.contentsHealth(mv);
    	
    	return mv;
    }
	
	/**
	 * Contents > 건강정보 Edit
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/_contents/health/info/{language_id}.dr")
    public ModelAndView healthInfo(@PathVariable String language_id, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/contents/health_info");
    	
    	commandMap.put("language_id", language_id);
    	
    	Map<String,Object> info = healthService.healthInfo(commandMap.getMap());
    	mv.addObject("info", info.get("map"));
    	
    	mv.addObject("languages", languagesService.languages());
    	mv.addObject("title", "Edit");
    	mv.addObject("language_id", language_id);
    	
    	MetaUtils.contentsHealth(mv);
    	
    	return mv;
    }
	
	/**
	 * Contents > 건강정보 Edit
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/_contents/health/info/{contents_id}/{language_id}.dr")
    public ModelAndView healthContentsInfo(@PathVariable String contents_id, @PathVariable String language_id, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/contents/health_info");
    	
    	/**
    	 * 기본정보
    	 */
    	commandMap.put("contents_id", contents_id);
    	commandMap.put("language_id", 1);
    	Map<String,Object> health = healthService.healthContentsInfo(commandMap.getMap());
    	mv.addObject("health", health.get("map"));
    	
    	/**
    	 * 건강정보
    	 */
    	commandMap.put("language_id", language_id);
    	Map<String,Object> info = healthService.healthContentsInfo(commandMap.getMap());
    	mv.addObject("info", info.get("map"));
    	
    	mv.addObject("languages", languagesService.languages());
    	mv.addObject("title", "Edit");
    	mv.addObject("contents_id", contents_id);
    	mv.addObject("language_id", language_id);
    	
    	MetaUtils.contentsHealth(mv);
    	
    	return mv;
    }
	
	/**
	 * Health 저장
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/_contents/health/save.dr")
    public ModelAndView updateHealth(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
    	
		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validForm(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
			healthService.updateHealth(commandMap.getMap());
			
			mv.addObject("Success", Message.SUCCESS);
		} else {
			validMap.put("warning", Message.WARNING);
			mv.addObject("Error", validMap);
		}
    	return mv;
    }
	
	/**
	 * Health Contents 저장
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/_contents/health/contents/save.dr")
    public ModelAndView updateHealthContents(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
    	
		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validForm(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
			commandMap.put("code", "notice");
			if(commandMap.getMap().get("sort_order").equals("")) {
				commandMap.put("sort_order", "0");
			}
			int count = healthService.isHealthContents(commandMap.getMap());
			if(count==0) {
				if(commandMap.getMap().get("contents_id").equals("0")) {
					commandMap.put("contents_id", healthService.maxHealthContentsId(commandMap.getMap()));
				}
				healthService.addHealthContents(commandMap.getMap());
			} else {
				healthService.updateHealthContents(commandMap.getMap());
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
			BaseController.setErrorMessage(validMap, "title", Health.Error.TITLE);
		}
		
		String description = commandMap.get("description").toString();
		if(ObjectUtils.isEmpty(description) || description.length()<1) {
			BaseController.setErrorMessage(validMap, "description", Health.Error.DESCRIPTION);
		}
		
		String meta_title = commandMap.get("meta_title").toString();
		if(ObjectUtils.isEmpty(meta_title) || meta_title.length()<1) {
			BaseController.setErrorMessage(validMap, "meta_title", Health.Error.META_TAG_TITLE);
		}
	}
	
	/**
	 * Health Contents 삭제
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/_contents/health/delete/{contents_id}.dr")
    public ModelAndView deleteHealthContents(@PathVariable String contents_id, CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/contents/health.dr");

    	commandMap.put("contents_id", contents_id);
    	healthService.deleteHealthContents(commandMap.getMap());
    	
    	return mv;
    }
}
