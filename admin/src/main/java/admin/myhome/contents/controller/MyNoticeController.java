package admin.myhome.contents.controller;

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
import admin.myhome.common.util.MyStoreUtils;
import admin.contents.language.Notice;
import admin.myhome.contents.service.MyNoticeService;
import admin.system.service.LanguagesService;

@Controller
public class MyNoticeController extends BaseController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="myNoticeService")
	private MyNoticeService myNoticeService;
	
	@Resource(name="languagesService")
	private LanguagesService languagesService;
	
	@Resource(name="fileUtils")
    private FileUtils fileUtils;
	
	/**
	 * Contents > 공지사항
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/my_contents/notice.dr")
    public ModelAndView notice(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_contents/notice");
    	
    	int count = 0;
    	pagemaker.setPage(pagemaker.getPage());
    	count = myNoticeService.totalNotice(commandMap.getMap()); // 레코드 총 갯수 구함
    	pagemaker.setCount(count); // 페이지 계산

    	commandMap.put("language_id", MyStoreUtils.getLanguageId());
    	commandMap.put("code", "main_notice");
    	commandMap.put("page", (pagemaker.getPage()-1)*pagemaker.getPER());
    	commandMap.put("per_page", pagemaker.getPER());
    	List<Map<String,Object>> list = myNoticeService.notice(commandMap.getMap());
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
	@RequestMapping(value="/my_contents/notice/add.dr")
    public ModelAndView noticeNew(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_contents/notice_info");
    	
    	Map<String,Object> info = new HashMap<String,Object>();
    	info.put("language_id", MyStoreUtils.getLanguageId());
    	
    	mv.addObject("languages", languagesService.languages());
    	mv.addObject("title", "Add");
    	mv.addObject("contents_id", "0");
    	mv.addObject("language_id", MyStoreUtils.getLanguageId());
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
	@RequestMapping(value="/my_contents/notice/info/{contents_id}/{language_id}.dr")
    public ModelAndView noticeInfo(@PathVariable String contents_id, @PathVariable String language_id, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_contents/notice_info");
    	
    	/**
    	 * 기본정보
    	 */
    	commandMap.put("contents_id", contents_id);
    	commandMap.put("language_id", 1);
    	Map<String,Object> notice = myNoticeService.noticeInfo(commandMap.getMap());
    	mv.addObject("notice", notice.get("map"));
    	
    	commandMap.put("language_id", language_id);
    	Map<String,Object> info = myNoticeService.noticeInfo(commandMap.getMap());
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
	@RequestMapping(value="/my_contents/notice/save.dr")
    public ModelAndView noticeSave(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
    	
		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validForm(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
			commandMap.put("code", "notice");
			if(commandMap.getMap().get("sort_order").equals("")) {
				commandMap.put("sort_order", "0");
			}
			int count = myNoticeService.isNotice(commandMap.getMap());
			if(count==0) {
				if(commandMap.getMap().get("contents_id").equals("0")) {
					commandMap.put("contents_id", myNoticeService.maxContentsId(commandMap.getMap()));
				}
				myNoticeService.addNotice(commandMap.getMap());
			} else {
				myNoticeService.updateNotice(commandMap.getMap());
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
	 * 이미지 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/my_contents/notice/image/{contents_id}/{language_id}.dr")
    public ModelAndView categoryViewImage(HttpServletRequest request, @PathVariable String contents_id, @PathVariable String language_id, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_contents/notice_image");
    	
    	/**
    	 * 기본 정보
    	 */
    	commandMap.put("contents_id", contents_id);
    	commandMap.put("language_id", 1);
    	
    	Map<String,Object> info = myNoticeService.noticeInfo(commandMap.getMap());
    	mv.addObject("info", info.get("map")); //
   
    	mv.addObject("contents_id", contents_id);
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
	@RequestMapping(value= "/my_contents/notice/save/image.dr", method = RequestMethod.POST)
    public ModelAndView categorySaveImage(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/my_contents/notice/image/"+commandMap.get2String("contents_id")+"/"+commandMap.get2String("language_id")+".dr");

		String folder = "banner/";
		fileUtils.setFilePath(StoreUtils.getFilepath()+folder);
		fileUtils.setViewPath(folder);
		fileUtils.parseInsertFileInfo(commandMap.getMap(), request);
		
		if(!commandMap.get2String("image").equals("")) {
//			commandMap.put("image", commandMap.get2String("original_image"));
			myNoticeService.updateImage(commandMap.getMap());
		}
		
		BaseController.setCustomSession(request, "정상적으로 저장되었습니다.", Session.SUCCESS);
//		mv.addObject("success", "정상적으로 저장되었습니다.");
    	
       	return mv;
    }
	
	/**
	 * 공지사항 삭제
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/my_contents/notice/delete/{contents_id}.dr")
    public ModelAndView deleteNotice(@PathVariable String contents_id, CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/my_contents/notice.dr");

    	commandMap.put("contents_id", contents_id);
    	myNoticeService.deleteNotice(commandMap.getMap());
    	
    	return mv;
    }
}
