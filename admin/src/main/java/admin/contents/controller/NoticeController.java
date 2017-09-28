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
import admin.contents.language.Notice;
import admin.contents.service.NoticeService;
import admin.system.service.LanguagesService;

@Controller
public class NoticeController extends BaseController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="noticeService")
	private NoticeService noticeService;
	
	@Resource(name="languagesService")
	private LanguagesService languagesService;
	
	/**
	 * Contents > 공지사항
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/notice.dr")
    public ModelAndView notice(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/contents/notice");
    	
    	int count = 0;
    	pagemaker.setPage(pagemaker.getPage());
    	count = noticeService.totalNotice(commandMap.getMap()); // 레코드 총 갯수 구함
    	pagemaker.setCount(count); // 페이지 계산

    	commandMap.put("language_id", StoreUtils.getLanguageId());
    	commandMap.put("code", "main_notice");
    	commandMap.put("page", (pagemaker.getPage()-1)*pagemaker.getPER());
    	commandMap.put("per_page", pagemaker.getPER());
    	List<Map<String,Object>> list = noticeService.notice(commandMap.getMap());
    	mv.addObject("list", list);
    	if(list.size() > 0){
    		mv.addObject("pageMaker", pagemaker); 
    	}
    	else{
    		mv.addObject("TOTAL", 0);
    	}
    	
    	MetaUtils.contentsNotice(mv);
    	
    	return mv;
    }
	
	/**
	 * Contents > 공지사항 Add
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/notice/add.dr")
    public ModelAndView noticeNew(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/contents/notice_info");
    	
    	Map<String,Object> info = new HashMap<String,Object>();
    	info.put("language_id", StoreUtils.getLanguageId());
    	
    	mv.addObject("languages", languagesService.languages());
    	mv.addObject("title", "Add");
    	mv.addObject("contents_id", "0");
    	mv.addObject("language_id", StoreUtils.getLanguageId());
    	mv.addObject("code", "notice");
    	
    	MetaUtils.contentsNotice(mv);
    	
    	return mv;
    }
	
	/**
	 * Contents > 공지사항 Edit
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/notice/info/{contents_id}/{language_id}.dr")
    public ModelAndView noticeInfo(@PathVariable String contents_id, @PathVariable String language_id, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/contents/notice_info");
    	
    	/**
    	 * 기본정보
    	 */
    	commandMap.put("contents_id", contents_id);
    	commandMap.put("language_id", 1);
    	Map<String,Object> notice = noticeService.noticeInfo(commandMap.getMap());
    	mv.addObject("notice", notice.get("map"));
    	
    	commandMap.put("language_id", language_id);
    	Map<String,Object> info = noticeService.noticeInfo(commandMap.getMap());
    	mv.addObject("info", info.get("map"));
    	
    	mv.addObject("languages", languagesService.languages());
    	mv.addObject("title", "Edit");
    	mv.addObject("contents_id", contents_id);
    	mv.addObject("language_id", language_id);
    	mv.addObject("code", "notice");
    	
    	MetaUtils.contentsNotice(mv);
    	
    	return mv;
    }
	
	/**
	 * 공지사항 저장
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/notice/save.dr")
    public ModelAndView noticeSave(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
    	
		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validForm(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
			commandMap.put("code", "notice");
			if(commandMap.getMap().get("sort_order").equals("")) {
				commandMap.put("sort_order", "0");
			}
			int count = noticeService.isNotice(commandMap.getMap());
			if(count==0) {
				if(commandMap.getMap().get("contents_id").equals("0")) {
					commandMap.put("contents_id", noticeService.maxContentsId(commandMap.getMap()));
				}
				noticeService.addNotice(commandMap.getMap());
			} else {
				noticeService.updateNotice(commandMap.getMap());
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
			BaseController.setErrorMessage(validMap, "title", Notice.Error.TITLE);
		}
		
		String description = commandMap.get("description").toString();
		if(ObjectUtils.isEmpty(description) || description.length()<1) {
			BaseController.setErrorMessage(validMap, "description", Notice.Error.DESCRIPTION);
		}
		
		String meta_title = commandMap.get("meta_title").toString();
		if(ObjectUtils.isEmpty(meta_title) || meta_title.length()<1) {
			BaseController.setErrorMessage(validMap, "meta_title", Notice.Error.META_TAG_TITLE);
		}
	}
	
	/**
	 * 공지사항 삭제
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contents/notice/delete/{contents_id}.dr")
    public ModelAndView deleteNotice(@PathVariable String contents_id, CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/contents/notice.dr");

    	commandMap.put("contents_id", contents_id);
    	noticeService.deleteNotice(commandMap.getMap());
    	
    	return mv;
    }
}
