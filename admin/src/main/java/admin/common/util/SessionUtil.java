package admin.common.util;

import javax.servlet.http.HttpSession;

import admin.common.constant.Session;
import admin.common.dto.CustomerDTO;

public class SessionUtil {
	public static CustomerDTO getCustomer(HttpSession session) {
		CustomerDTO customer = (CustomerDTO) session.getAttribute(Session.CUSTOMER);
		return customer;
	}
	
	public static String getId(HttpSession session) {
		return session.getAttribute(Session.CUSTOMER_ID).toString();
	}
	
	public static boolean getIsSession(HttpSession session) {
		return (boolean) session.getAttribute(Session.IS_SESSION);
	}
}
