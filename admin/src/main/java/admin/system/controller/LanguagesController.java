package admin.system.controller;

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
import admin.common.util.ScriptUtils;
import admin.common.util.StoreUtils;
import admin.system.service.LanguagesService;

@Controller
public class LanguagesController extends BaseController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="languagesService")
	private LanguagesService languagesService;
	
	@Resource(name="fileUtils")
    private FileUtils fileUtils;
	
	/**
	 * Settings > Language
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/settings/languages.dr")
    public ModelAndView information(CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/system/languages");
    	
    	List<Map<String,Object>> list = languagesService.languages();
    	mv.addObject("list", list);
    	
    	MetaUtils.settingsLanguage(mv);
    	ScriptUtils.contentsViewImaeScript(mv);
    	
    	return mv;
    }
	
	/**
	 * Language 상세
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/settings/language/info/{language_id}.dr")
    public ModelAndView informationEdit(@PathVariable String language_id, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/system/language_info");
    	
    	/**
    	 * 기본 정보
    	 */
    	commandMap.put("language_id", language_id);
    	Map<String,Object> information = languagesService.languageInfo(commandMap.getMap());
    	mv.addObject("info", information.get("map"));
    	
    	mv.addObject("title", "Edit");
    	mv.addObject("language_id", language_id);
    	
    	MetaUtils.settingsLanguage(mv);
    	ScriptUtils.contentsViewImaeScript(mv);
    	
    	return mv;
    }
	
	/**
	 * 저장
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/settings/language/save.dr")
    public ModelAndView informationSave(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
    	
		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validForm(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
			if(commandMap.getMap().get("sort_order").equals("")) {
				commandMap.put("sort_order", "0");
			}
			languagesService.updateLanguage(commandMap.getMap());
			
			mv.addObject("Success", Message.SUCCESS);
		} else {
			validMap.put("warning", Message.WARNING);
			mv.addObject("Error", validMap);
		}
    	return mv;
    }
	
	public void validForm(Map<String, Object> validMap, CommandMap commandMap) throws Exception{
		String title = commandMap.get("name").toString();
		if(ObjectUtils.isEmpty(title) || (title.length()<3 && title.length()>64)) {
			BaseController.setErrorMessage(validMap, "name", "언어명은 필수 입력입니다.");
		}
	}
	
	/**
	 * 이미지 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/settings/language/image/{language_id}.dr")
    public ModelAndView categoryViewImage(HttpServletRequest request, @PathVariable String language_id, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/system/language_image");
    	
    	/**
    	 * 기본 정보
    	 */
    	commandMap.put("language_id", language_id);
    	Map<String,Object> info = languagesService.languageInfo(commandMap.getMap());
    	mv.addObject("info", info.get("map")); //
   
    	mv.addObject("language_id", language_id);
    	
    	if(null!=BaseController.getCustomSession(request, Session.SUCCESS)) {
    		mv.addObject("successMsg", BaseController.getCustomSession(request, Session.SUCCESS));
    		BaseController.setCustomSession(request, null, Session.SUCCESS);
    	}
    
    	MetaUtils.settingsLanguage(mv);
    	ScriptUtils.contentsViewImaeScript(mv);

    	return mv;
    }
	
	/**
	 * 이미지 페이지
	 * @param commandMap       
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/settings/language/save/image.dr", method = RequestMethod.POST)
    public ModelAndView categorySaveImage(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/settings/language/image/"+commandMap.get2String("language_id")+".dr");

		String folder = "flag/";
		fileUtils.setFilePath(StoreUtils.getFilepath()+folder);
		fileUtils.setViewPath(folder);
		fileUtils.parseInsertFileInfo(commandMap.getMap(), request);
		
		if(!commandMap.get2String("image").equals("")) {
			languagesService.updateImage(commandMap.getMap());
		}
		
		BaseController.setCustomSession(request, "정상적으로 저장되었습니다.", Session.SUCCESS);
//		mv.addObject("success", "정상적으로 저장되었습니다.");
    	
       	return mv;
    }
}
