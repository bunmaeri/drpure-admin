package admin.common.interceptor;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import admin.common.constant.Session;
import admin.common.controller.BaseController;
import admin.common.controller.UserIpsController;
import admin.common.util.CommonUtils;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {  
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		BaseController.setCustomSession(request, null, Session.URL);
		BaseController.setCustomSession(request, null, Session.NAV);
		
		UserIpsController ips = new UserIpsController();
		String client_ip = CommonUtils.getClientIP(request);
//		System.err.println(client_ip);
		String reqUrl = request.getRequestURI().toString();
//		System.err.println("-----------------> Url check Interceptor , reqUrl : " +reqUrl );
		if(reqUrl.equals("/denied/access.dr") || reqUrl.contains("/settings/access/ip")) {
			BaseController.setCustomSession(request, reqUrl, Session.URL);
			String[] urls = reqUrl.split("/");
			if(urls.length>2) {
//				System.err.println("Session.NAV: /"+urls[1]);
				BaseController.setCustomSession(request, "/"+urls[1], Session.NAV);
			}
			return true;
		}
		if(!ips.getBoolean(client_ip)) {
			if(isAjaxRequest(request)) {
				response.sendError(300);
				return false;
			} else {
//				System.err.println(client_ip);
				response.sendRedirect(request.getContextPath()+"/denied/access.dr");
				return true;
			}
		}
		BaseController.setCustomSession(request, reqUrl, Session.URL);
		String[] urls = reqUrl.split("/");
		if(urls.length>2) {
//			System.err.println("Session.NAV: /"+urls[1]);
			BaseController.setCustomSession(request, "/"+urls[1], Session.NAV);
		}
		
		return true;
	}

	private boolean isAjaxRequest(HttpServletRequest req) {
		String header = req.getHeader("AJAX");
		//    	System.err.println("header============>"+header);
		if ("true".equals(header)) {
			return true;
		} else {
			return false;
		}
	}
}
