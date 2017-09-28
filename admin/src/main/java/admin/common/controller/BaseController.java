package admin.common.controller;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import admin.common.constant.Message;
import admin.common.constant.Session;
import admin.common.dto.CustomerDTO;
import admin.common.filter.SessionBox;

@Controller
public class BaseController {
	/**
     * 화면단에서 alert()메시지를 보여주기 위해서 session에 담는다.<br>
     * 이렇게 세션에 담으면, 나중에 화면단에서 메시지를 alert() 창으로 보여준다.
     * 요것은 중간에 request가 끊겼을때도 에러메시지를 보여줘야 할때 유용함.
     *
     * @param request
     * @param msg
     */
    public void setAlertMsg(HttpServletRequest request, String msg) {
        HttpSession session = request.getSession();
        if (session != null && msg != null) {
            session.setAttribute(Message.KEY, msg);
        }
    }
    
    /**
     * 로그인한 사용자의 정보를 반환.
     */
    public static CustomerDTO getUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        } else {
            return (CustomerDTO) session.getAttribute(Session.CUSTOMER);
        }
    }
    
    /**
     * 로그인한 사용자의 정보를 반환.
     */
    public static CustomerDTO getUserInfo(HttpSession session) {
        if (session == null) {
            return null;
        } else {
            return (CustomerDTO) session.getAttribute(Session.CUSTOMER);
        }
    }

    /**
     * 로그인한 사용자의 id를 반환.
     */
    public static String getId(HttpServletRequest request) {
    	CustomerDTO customer = getUserInfo(request);
        if (customer == null) {
            return null;
        } else {
            return customer.getId();
        }
    }
    
    /**
     * 로그인한 사용자의 id를 반환.
     */
    public static String getId(HttpSession session) {
    	if (session == null) {
            return null;
        } else {
            return session.getAttribute(Session.CUSTOMER_ID).toString();
        }
    }
    
    /**
     * 세션에 저장되어 있는 세션 객체를 반환한다.
     */
    public static Object getCustomSession(HttpServletRequest request, String sessionKey) {
        HttpSession session = request.getSession(false);
        if (session == null) {
        	Hashtable<String, Object> sessionBox = SessionBox.getSessionBox();
            return sessionBox;
        }

        return session.getAttribute(sessionKey);
    }
    
    /**
     * 세션에 저장되어 있는 세션 객체를 반환한다.
     */
    public static Object getCustomSession(HttpSession session, String sessionKey) {
        if (session == null) {
        	return null;
        }

        return session.getAttribute(sessionKey);
    }
    
    /**
     * 세션에 저장되어 있는 세션 객체를 반환한다.
     */
    public static boolean getBooleanCustomSession(HttpSession session, String sessionKey) {
        if (session == null) {
        	return false;
        }

        if(null!=session.getAttribute(sessionKey)) {
        	return (boolean) session.getAttribute(sessionKey);
        }
        return false;
    }
    
    /**
     * 세션에 저장되어 있는 세션 객체를 삭제한다.
     */
    public static void removeCustomSession(HttpServletRequest request, String sessionKey) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(sessionKey);
        }
    }

    /**
     * 사용자정의 세션을 저장한다.
     */
    public static void setCustomSession(HttpServletRequest request, Object obj, String sessionKey) {
        HttpSession session = request.getSession(true);
        session.setAttribute(sessionKey, obj);
    }
    
    /**
     * 사용자정의 세션을 저장한다.
     */
    public static void setCustomSession(HttpSession session, Object obj, String sessionKey) {
        session.setAttribute(sessionKey, obj);
    }
    
    public static void setCustomMessage(Map<String, Object> message, String key, Object value) {
    	if(null==message) {
    		message = new HashMap<String,Object>();
    	}
    	message.put(key, value);
    }
    
    public static void setErrorMessage(Map<String, Object> error, String key, Object value) {
    	if(null==error) {
    		error = new HashMap<String,Object>();
    	}
    	error.put(key, value);
    }
}
