package admin.marketing.controller;

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
import admin.marketing.service.EmailTemplateService;

@Controller
public class EmailTemplateController extends BaseController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="emailTemplateService")
	private EmailTemplateService emailTemplateService;

	/**
	 * 이메일 템플릿 목록
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/marketing/templates.dr")
    public ModelAndView notice(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/marketing/email_template");
    	
    	int count = 0;
    	pagemaker.setPage(pagemaker.getPage());
    	count = emailTemplateService.totalEmailTemplate(commandMap.getMap()); // 레코드 총 갯수 구함
    	pagemaker.setCount(count); // 페이지 계산

    	commandMap.put("page", (pagemaker.getPage()-1)*pagemaker.getPER());
    	commandMap.put("per_page", pagemaker.getPER());
    	List<Map<String,Object>> list = emailTemplateService.emailTemplateList(commandMap.getMap());
    	mv.addObject("list", list);
    	if(list.size() > 0){
    		mv.addObject("pageMaker", pagemaker); 
    	}
    	    	
    	MetaUtils.marketingTemplets(mv);
    	
    	return mv;
    }
	
	/**
	 * 이메일 템플릿 Add
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/marketing/templates/add.dr")
    public ModelAndView noticeNew(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/marketing/email_template_info");
    	
    	mv.addObject("title", "Add");
    	mv.addObject("template_id", "");
    	
    	MetaUtils.marketingTemplets(mv);
    	
    	return mv;
    }
	
	/**
	 * 이메일 템플릿 Edit
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/marketing/templates/info/{template_id}.dr")
    public ModelAndView noticeInfo(@PathVariable String template_id, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/marketing/email_template_info");
    	
    	/**
    	 * 기본정보
    	 */
    	commandMap.put("template_id", template_id);
    	Map<String,Object> info = emailTemplateService.emailTemplateInfo(commandMap.getMap());
    	mv.addObject("info", info.get("map"));
    	
    	mv.addObject("title", "Edit");
    	mv.addObject("template_id", template_id);
    	
    	MetaUtils.marketingTemplets(mv);
    	
    	return mv;
    }
	
	/**
	 * 이메일 템플릿 저장
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/marketing/templates/save.dr")
    public ModelAndView noticeSave(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
    	
		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validForm(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
			if(commandMap.get2String("template_id").equals("")) {
				emailTemplateService.addEmailTemplate(commandMap.getMap());
			} else {
				emailTemplateService.updateEmailTemplate(commandMap.getMap());
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
			BaseController.setErrorMessage(validMap, "title", "필수 입력입니다.");
		}
		
		String description = commandMap.get("description").toString();
		if(ObjectUtils.isEmpty(description) || description.length()<1) {
			BaseController.setErrorMessage(validMap, "description", "필수 입력입니다.");
		}
	}
	
	/**
	 * 이메일 템플릿 삭제
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/marketing/templates/delete/{template_id}.dr")
    public ModelAndView deleteNotice(@PathVariable String template_id, CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/marketing/templates.dr");

    	commandMap.put("template_id", template_id);
    	emailTemplateService.deleteEmailTemplate(commandMap.getMap());
    	
    	return mv;
    }
}
