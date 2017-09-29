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
import org.springframework.web.servlet.ModelAndView;

import admin.common.common.CommandMap;
import admin.common.constant.Message;
import admin.common.constant.Session;
import admin.common.controller.BaseController;
import admin.common.controller.UserIpsController;
import admin.common.util.CommonUtils;
import admin.common.util.ObjectUtils;
import admin.system.service.AccessService;

@Controller
public class AccessController extends BaseController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="accessService")
	private AccessService service;

	/**
	 * IP 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/settings/access/ip.dr")
    public ModelAndView accessIpList(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/system/access_ip");

		// IP 목록
		List<Map<String,Object>> list = service.accessIpList();
		mv.addObject("list", list);
		
		mv.addObject("ip", AccessController.getClientIp(request));
				
		if(null!=BaseController.getCustomSession(request, Session.SUCCESS)) {
    		mv.addObject("successMsg", BaseController.getCustomSession(request, Session.SUCCESS));
    		BaseController.setCustomSession(request, null, Session.SUCCESS);
    	}
		
		return mv;
    }
	
	/**
	 * IP 저장
	 * @param commandMap       
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/settings/access/ip/add.dr")
    public ModelAndView addAccessIp(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		    	
		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validForm(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
			String input_password = commandMap.get2String("input_password");
			String db_password = service.accessIpPassword();
			
			log.error("db_password:"+db_password);
        	log.error("input_password:"+CommonUtils.shaEncoder(input_password));
        	
			if(!CommonUtils.shaMatches(db_password, input_password)) {
				validMap.put("warning", Message.WARNING);
				BaseController.setErrorMessage(validMap, "input_password", "비밀번호가 일치하지 않습니다.");
				mv.addObject("Error", validMap);
				return mv;
			}
//			commandMap.put("password", CommonUtils.shaEncoder(input_password));
			commandMap.put("status", "1");
			service.addAccessIp(commandMap.getMap());
			
			mv.addObject("Success", Message.SUCCESS);
			new UserIpsController().addCode(commandMap.get2String("ip")); // 메모리 Reload
		} else {
			validMap.put("warning", Message.WARNING);
			mv.addObject("Error", validMap);
		}
    	return mv;
    }
	
	public void validForm(Map<String, Object> validMap, CommandMap commandMap) throws Exception{
		String ip = commandMap.get2String("ip");
		if(ip.equals("") || ip.length()<1) {
			BaseController.setErrorMessage(validMap, "ip", "필수 입력입니다.");
		}
		
		String memo = commandMap.get2String("memo");
		if(memo.equals("") || memo.length()<1) {
			BaseController.setErrorMessage(validMap, "memo", "필수 입력입니다.");
		}
		
		String input_password = commandMap.get2String("input_password");
		if(input_password.equals("") || input_password.length()<1) {
			BaseController.setErrorMessage(validMap, "input_password", "필수 입력입니다.");
		}
	}
	
	/**
	 * IP 삭제
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/settings/access/ip/delete/{no}/{ip}.dr")
    public ModelAndView deleteAccessIp(HttpServletRequest request, @PathVariable String no, @PathVariable String ip, CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/settings/access/ip.dr");

    	commandMap.put("no", no);
    	service.deleteAccessIp(commandMap.getMap());
    	new UserIpsController().deleteCode(ip); // 메모리 Reload
    	
    	BaseController.setCustomSession(request, "정상적으로 삭제되었습니다.", Session.SUCCESS);
    	return mv;
    }
	
	/**
	 * Password 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/settings/access/password.dr")
    public ModelAndView accessPassword(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/system/access_password");
				
		if(null!=BaseController.getCustomSession(request, Session.SUCCESS)) {
    		mv.addObject("successMsg", BaseController.getCustomSession(request, Session.SUCCESS));
    		BaseController.setCustomSession(request, null, Session.SUCCESS);
    	}
		
		return mv;
    }
	
	/**
	 * Password 저장
	 * @param commandMap       
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/settings/access/password/save.dr")
    public ModelAndView accessPasswordSave(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		    	
		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validFormPassword(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
			String input_password = commandMap.get2String("input_password");
        	log.error("input_password:"+input_password);
        	commandMap.put("password", CommonUtils.shaEncoder(input_password));
        	service.updateAccessPassword(commandMap.getMap());
			mv.addObject("Success", Message.SUCCESS);
		} else {
			validMap.put("warning", Message.WARNING);
			mv.addObject("Error", validMap);
		}
    	return mv;
    }
	
	public void validFormPassword(Map<String, Object> validMap, CommandMap commandMap) throws Exception{
		String input_password = commandMap.get2String("input_password");
		if(input_password.equals("") || input_password.length()<1) {
			BaseController.setErrorMessage(validMap, "input_password", "필수 입력입니다.");
		}
	}
	
	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("Proxy-Client-IP"); 
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("WL-Proxy-Client-IP"); 
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("HTTP_CLIENT_IP"); 
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		    ip = request.getRemoteAddr(); 
		}
		
		return ip;
    }
}
