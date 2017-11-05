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
import admin.common.email.MailChimpEmail;
import admin.common.util.MetaUtils;
import admin.common.util.ObjectUtils;
import admin.common.util.Pagemaker;
import admin.marketing.service.EmailTemplateService;
import admin.marketing.service.NewsletterService;

@Controller
public class NewsletterController extends BaseController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="newsletterService")
	private NewsletterService service;
	
	@Resource(name="emailTemplateService")
	private EmailTemplateService emailTemplateService;

	/**
	 * 이메일 템플릿 목록
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/marketing/newsletter.dr")
    public ModelAndView marketingNewsletter(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/marketing/newsletter");
    	
       	List<Map<String,Object>> list = service.emailTemplateList();
    	mv.addObject("list", list);
    	
    	List<Map<String,Object>> groups = service.newsletterCustomerList();
    	mv.addObject("groups", groups);
   
    	MetaUtils.marketingNewsletter(mv);
    
    	return mv;
    }

	/**
	 * 템플릿 가져오기
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/marketing/newsletter/template.dr")
    public ModelAndView marketingNewsletterTemplate(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
    	
		if(commandMap.get2String("template_id").equals("")) {
			mv.addObject("title", "");
			mv.addObject("description", "");
			mv.addObject("Success", "Success");
			return mv;
		}
		Map<String,Object> info = emailTemplateService.emailTemplateInfo(commandMap.getMap());
		Map<String,Object> map = (Map<String,Object>) info.get("map");
		if(null!=map) {
			mv.addObject("title", map.get("title"));
			mv.addObject("description", map.get("description"));
			mv.addObject("Success", "Success");
		} else {
			mv.addObject("Error", "템플릿을 가져오지 못했습니다. 잠시 후에 다시 이용해 주십시요.");
		}
		
		return mv;
    }

	/**
	 * 이메일 보내기
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/marketing/newsletter/send.dr")
    public ModelAndView noticeSave(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
    	
		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validForm(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
			/**
			 * 고객에게 이메일 보내기
			 */
			String html = commandMap.get2String("contents");
	    	commandMap.put("subject", commandMap.get2String("title"));
			commandMap.put("html", html);
//			System.err.println(commandMap.get2String("customer_group_id"));
			int size = 0;
			String customer_group_id = commandMap.get2String("customer_group_id");
			String custom_email = commandMap.get2String("custom_email");
			if(customer_group_id.equals("custom") && !custom_email.equals("")) {
				String[] emails = custom_email.split(";");
				size = emails.length;
//				System.err.println(size);
				for(int i=0;i<size;i++) {
//					System.err.println(emails[i]);
					commandMap.put("recipient_name", emails[i].trim());
					commandMap.put("recipient_email", emails[i].trim());
					MailChimpEmail.run(commandMap.getMap());
				}
			} else {
				List<Map<String,Object>> list = service.sendCustomerList(commandMap.getMap());
				size = list.size();
				Map<String,Object> map = null;
				for(int i=0;i<size;i++) {
					map = list.get(i);
					commandMap.put("recipient_name", ObjectUtils.null2void(map.get("email")));
					commandMap.put("recipient_email", ObjectUtils.null2void(map.get("customer_name")));
//					commandMap.put("recipient_name", "Kyungil Jo");
//					commandMap.put("recipient_email", "bunmaeri@gmail.com");
					System.err.println(ObjectUtils.null2void(map.get("email"))+" >> "+ObjectUtils.null2void(map.get("customer_name")));
					MailChimpEmail.run(commandMap.getMap());
				}
			}
			
			mv.addObject("Success", "이메일이 발송되었습니다.");
		} else {
			validMap.put("warning", Message.WARNING);
			mv.addObject("Error", validMap);
		}
    	return mv;
    }
	
	public void validForm(Map<String, Object> validMap, CommandMap commandMap) throws Exception{
		String customer_group_id = commandMap.get2String("customer_group_id");
		String custom_email = commandMap.get2String("custom_email");
		if(customer_group_id.equals("custom") && custom_email.equals("")) {
			BaseController.setErrorMessage(validMap, "custom_email", "이메일을 입력해주세요.");
		}
		
		String title = commandMap.get2String("title");
		if(title.equals("") || title.length()<1) {
			BaseController.setErrorMessage(validMap, "title", "필수 입력입니다.");
		}
		
		String description = commandMap.get2String("contents");
		if(description.equals("") || description.length()<1) {
			BaseController.setErrorMessage(validMap, "description", "필수 입력입니다.");
		}
	}

}
