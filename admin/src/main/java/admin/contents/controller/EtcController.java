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
import admin.contents.language.Etc;
import admin.contents.service.EtcService;
import admin.system.service.LanguagesService;

@Controller
public class EtcController extends BaseController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="etcService")
	private EtcService etcService;
	
	@Resource(name="languagesService")
	private LanguagesService languagesService;

	/**
	 * 컨텐츠 관리 > 기타 컨텐츠
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/etc.dr")
    public ModelAndView contents(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/contents/etc");
    
    	int count = 0;
    	pagemaker.setPage(pagemaker.getPage());
    	count = etcService.totalContents(commandMap.getMap()); // 레코드 총 갯수 구함
    	pagemaker.setCount(count); // 페이지 계산

    	commandMap.put("language_id", StoreUtils.getLanguageId());
    	commandMap.put("page", (pagemaker.getPage()-1)*pagemaker.getPER());
    	commandMap.put("per_page", pagemaker.getPER());
    	List<Map<String,Object>> list = etcService.contents(commandMap.getMap());
    	mv.addObject("list", list);
    	if(list.size() > 0){
    		mv.addObject("pageMaker", pagemaker); 
    	}
    	else{
    		mv.addObject("TOTAL", 0);
    	}
    	
    	MetaUtils.contentsEtc(mv);
    	
    	return mv;
    }

	/**
	 * 기타 컨텐츠 추가
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/etc/add.dr")
    public ModelAndView contentsNew(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/contents/etc_info");
    	
    	Map<String,Object> info = new HashMap<String,Object>();
    	info.put("language_id", StoreUtils.getLanguageId());
    	
    	mv.addObject("languages", languagesService.languages());
    	mv.addObject("title", "Add");
    	mv.addObject("contents_id", "0");
    	mv.addObject("language_id", StoreUtils.getLanguageId());
    	
    	MetaUtils.contentsEtc(mv);
    	
    	return mv;
    }
	
	/**
	 * 기타 컨텐츠 상세조회
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/etc/info/{contents_id}/{language_id}.dr")
    public ModelAndView contentsEdit(@PathVariable String contents_id, @PathVariable String language_id, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/contents/etc_info");
    	
    	/**
    	 * 기본 정보
    	 */
    	commandMap.put("contents_id", contents_id);
    	commandMap.put("language_id", 1);
    	
    	Map<String,Object> contents = etcService.contentsInfo(commandMap.getMap());
    	mv.addObject("contents", contents.get("map"));
    	
    	/**
    	 * 언어별 정보
    	 */
    	commandMap.put("language_id", language_id);
    	Map<String,Object> info = etcService.contentsInfo(commandMap.getMap());
    	mv.addObject("info", info.get("map"));
    	
    	mv.addObject("languages", languagesService.languages());
    	mv.addObject("title", "Edit");
    	mv.addObject("contents_id", contents_id);
    	mv.addObject("language_id", language_id);
    	
    	
    	MetaUtils.contentsEtc(mv);
    	
    	return mv;
    }
	
	/**
	 * 기타 컨텐츠 저장
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/etc/save.dr")
    public ModelAndView contentsSave(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
    	
		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validForm(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
			if(commandMap.getMap().get("sort_order").equals("")) {
				commandMap.put("sort_order", "0");
			}
			int count = etcService.isContents(commandMap.getMap());
			if(count==0) {
				if(commandMap.getMap().get("contents_id").equals("0")) {
					commandMap.put("contents_id", etcService.maxContentsId(commandMap.getMap()));
				}
				etcService.addContents(commandMap.getMap());
			} else {
				etcService.updateContents(commandMap.getMap());
			}
			
			mv.addObject("Success", Message.SUCCESS);
		} else {
			validMap.put("warning", Message.WARNING);
			mv.addObject("Error", validMap);
		}
    	return mv;
    }

	/**
	 * 폼 유효성 체크
	 * @param validMap
	 * @param commandMap
	 * @throws Exception
	 */
	public void validForm(Map<String, Object> validMap, CommandMap commandMap) throws Exception{
		String title = commandMap.get("title").toString();
		if(ObjectUtils.isEmpty(title) || (title.length()<3 && title.length()>64)) {
			BaseController.setErrorMessage(validMap, "title", Etc.Error.TITLE);
		}
		
		String description = commandMap.get("description").toString();
		if(ObjectUtils.isEmpty(description) || description.length()<1) {
			BaseController.setErrorMessage(validMap, "description", Etc.Error.DESCRIPTION);
		}
		
//		String meta_title = commandMap.get("meta_title").toString();
//		if(ObjectUtils.isEmpty(meta_title) || meta_title.length()<1) {
//			BaseController.setErrorMessage(validMap, "meta_title", Etc.Error.META_TAG_TITLE);
//		}
	}
	
	/**
	 * 기타 컨텐츠 삭제
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/etc/delete/{contents_id}.dr")
    public ModelAndView deleteContents(@PathVariable String contents_id, CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/contents/etc.dr");

    	commandMap.put("contents_id", contents_id);
    	etcService.deleteContents(commandMap.getMap());
    	
    	return mv;
    }
}
