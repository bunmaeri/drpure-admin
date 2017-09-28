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
import admin.contents.language.Information;
import admin.contents.service.InformationService;
import admin.system.service.LanguagesService;

@Controller
public class InformationController extends BaseController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="informationService")
	private InformationService informationService;
	
	@Resource(name="languagesService")
	private LanguagesService languagesService;
	
	@Resource(name="fileUtils")
    private FileUtils fileUtils;

	/**
	 * Contents > Information
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/information.dr")
    public ModelAndView information(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/contents/information");
    	
    	int count = 0;
    	pagemaker.setPage(pagemaker.getPage());
    	count = informationService.totalInformation(commandMap.getMap()); // 레코드 총 갯수 구함
    	pagemaker.setCount(count); // 페이지 계산

    	commandMap.put("language_id", StoreUtils.getLanguageId());
    	commandMap.put("page", (pagemaker.getPage()-1)*pagemaker.getPER());
    	commandMap.put("per_page", pagemaker.getPER());
    	List<Map<String,Object>> list = informationService.information(commandMap.getMap());
    	mv.addObject("list", list);
    	if(list.size() > 0){
    		mv.addObject("pageMaker", pagemaker); 
    	}
    	else{
    		mv.addObject("TOTAL", 0);
    	}
    	
    	MetaUtils.contentsInformation(mv);
    	ScriptUtils.contentsViewImaeScript(mv);
    	
    	return mv;
    }

	/**
	 * Contents > Information
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/information/add.dr")
    public ModelAndView informationNew(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/contents/information_info");
    	
    	Map<String,Object> info = new HashMap<String,Object>();
    	info.put("language_id", StoreUtils.getLanguageId());
    	
    	mv.addObject("languages", languagesService.languages());
    	mv.addObject("title", "Add");
    	mv.addObject("information_id", "0");
    	mv.addObject("language_id", StoreUtils.getLanguageId());
    	
    	MetaUtils.contentsInformation(mv);
    	ScriptUtils.contentsViewImaeScript(mv);
    	
    	return mv;
    }
	
	/**
	 * Contents > Information
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/information/info/{information_id}/{language_id}.dr")
    public ModelAndView informationEdit(@PathVariable String information_id, @PathVariable String language_id, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/contents/information_info");
    	
    	/**
    	 * 기본 정보
    	 */
    	commandMap.put("information_id", information_id);
    	commandMap.put("language_id", 1);
    	
    	Map<String,Object> information = informationService.informationInfo(commandMap.getMap());
    	mv.addObject("information", information.get("map"));
    	
    	/**
    	 * 언어별 정보
    	 */
    	commandMap.put("language_id", language_id);
    	Map<String,Object> info = informationService.informationInfo(commandMap.getMap());
    	mv.addObject("info", info.get("map"));
    	
    	mv.addObject("languages", languagesService.languages());
    	mv.addObject("title", "Edit");
    	mv.addObject("information_id", information_id);
    	mv.addObject("language_id", language_id);
    	
    	
    	MetaUtils.contentsInformation(mv);
    	ScriptUtils.contentsViewImaeScript(mv);
    	
    	return mv;
    }
	
	/**
	 * Information 저장
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/information/save.dr")
    public ModelAndView informationSave(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
    	
		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validForm(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
			if(commandMap.getMap().get("sort_order").equals("")) {
				commandMap.put("sort_order", "0");
			}
			int count = informationService.isInformation(commandMap.getMap());
			if(count==0) {
				if(commandMap.getMap().get("information_id").equals("0")) {
					commandMap.put("information_id", informationService.maxInformationId(commandMap.getMap()));
				}
				informationService.addInformation(commandMap.getMap());
			} else {
				informationService.updateInformation(commandMap.getMap());
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
			BaseController.setErrorMessage(validMap, "title", Information.Error.TITLE);
		}
		
		String description = commandMap.get("description").toString();
		if(ObjectUtils.isEmpty(description) || description.length()<1) {
			BaseController.setErrorMessage(validMap, "description", Information.Error.DESCRIPTION);
		}
		
		String meta_title = commandMap.get("meta_title").toString();
		if(ObjectUtils.isEmpty(meta_title) || meta_title.length()<1) {
			BaseController.setErrorMessage(validMap, "meta_title", Information.Error.META_TAG_TITLE);
		}
	}
	
	/**
	 * 이미지 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/contents/information/image/{information_id}/{language_id}.dr")
    public ModelAndView categoryViewImage(HttpServletRequest request, @PathVariable String information_id, @PathVariable String language_id, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/contents/information_image");
    	
    	/**
    	 * 기본 정보
    	 */
    	commandMap.put("information_id", information_id);
    	commandMap.put("language_id", 1);
    	
    	Map<String,Object> info = informationService.informationInfo(commandMap.getMap());
    	mv.addObject("info", info.get("map")); //
   
    	mv.addObject("information_id", information_id);
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
	@RequestMapping(value= "/contents/information/save/image.dr", method = RequestMethod.POST)
    public ModelAndView categorySaveImage(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/contents/information/image/"+commandMap.get2String("information_id")+"/"+commandMap.get2String("language_id")+".dr");

		String folder = "catalog/title-images/";
		fileUtils.setFilePath(StoreUtils.getFilepath()+folder);
		fileUtils.setViewPath(folder);
		fileUtils.parseInsertFileInfo(commandMap.getMap(), request);
		
		if(!commandMap.get2String("image").equals("")) {
//			commandMap.put("image", commandMap.get2String("original_image"));
			informationService.updateImage(commandMap.getMap());
		}
		
		BaseController.setCustomSession(request, "정상적으로 저장되었습니다.", Session.SUCCESS);
//		mv.addObject("success", "정상적으로 저장되었습니다.");
    	
       	return mv;
    }
	
	/**
	 * Information 삭제
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/information/delete/{information_id}.dr")
    public ModelAndView deleteInformation(@PathVariable String information_id, CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/contents/information.dr");

    	commandMap.put("information_id", information_id);
    	informationService.deleteInformation(commandMap.getMap());
    	
    	return mv;
    }
}
