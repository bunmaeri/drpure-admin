package admin.common.util;

import java.util.ArrayList;
import java.util.Enumeration;

import org.apache.commons.codec.binary.Base64;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import admin.common.filter.PasswordEncoding;
import admin.common.filter.SHAPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.ModelAndView;

public class CommonUtils {
	private static final Logger log = Logger.getLogger(CommonUtils.class);

	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static void printMap(Map<String, Object> map) {
		Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
		Entry<String, Object> entry = null;
		log.debug("--------------------printMap--------------------\n");
		while (iterator.hasNext()) {
			entry = iterator.next();
			log.debug("key : " + entry.getKey() + ",\tvalue : " + entry.getValue());
		}
		log.debug("");
		log.debug("------------------------------------------------\n");
	}

	public static void printList(List<Map<String, Object>> list) {
		Iterator<Entry<String, Object>> iterator = null;
		Entry<String, Object> entry = null;
		log.debug("--------------------printList--------------------\n");
		int listSize = list.size();
		for (int i = 0; i < listSize; i++) {
			log.debug("list index : " + i);
			iterator = list.get(i).entrySet().iterator();
			while (iterator.hasNext()) {
				entry = iterator.next();
				log.debug("key : " + entry.getKey() + ",\tvalue : " + entry.getValue());
			}
			log.debug("\n");
		}
		log.debug("------------------------------------------------\n");
	}

	/**
	 * 이메일 체크
	 * @param strInput
	 * @return
	 */
	public static boolean validEmail(String strInput) {
		return Pattern.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+$", strInput);
	}

	/**
	 * 전화번호 체크
	 * @param strInput
	 * @return
	 */
	public static boolean validTelephone(String strInput, String country_id) {
		String telephone = strInput.trim().replaceAll("[^0-9]", "");
		if (telephone.length() < 9)
			return false;
		// 한국 전화번호일 때
		if(country_id.equals("113")) {
			if(!telephone.substring(0,1).equals("0")) {
				return false;
			}
		} else
		if(country_id.equals("223")) {
			
		}
		boolean flag = validNum(telephone);
		return flag;
	}
	
	/**
	 * 적립포인트 체크
	 * @param strInput
	 * @return
	 */
	public static boolean validRewardPoint(String strInput, String country_id) {
		String minus = "";
		if(strInput.startsWith("-")) {
			minus = "-";
		}
		String reward = strInput.trim().replaceAll("[^0-9]", "");
		boolean flag = validNum(reward);
		return flag;
	}
	
	/**
	 * 개인통관고유번호 체크
	 * @param strInput
	 * @return
	 */
	public static boolean validRequisitionId(String strInput) {
		if(strInput.trim().length()==0) return false;
		
		String first = strInput.substring(0, 1);
		if(!first.equals("P") && !first.equals("p")) return false;
		
		String requisition_id = strInput.substring(1);
		log.debug("requisition_id:"+requisition_id);
		if (requisition_id.length() != 12) return false;
		
		boolean flag = validNum(requisition_id);
		return flag;
	}
	
	/**
	 * 우편번호 체크
	 * @param strInput
	 * @return
	 */
	public static boolean validPostcode(String strInput) {
		if (strInput.length() != 5) return false;
		
		boolean flag = validNum(strInput);
		return flag;
	}

	/**
	 * 비밀번호 체크
	 * @param strInput
	 * @return
	 */
	public static boolean validPasword(String strInput) {
		String password = strInput.replace("-", "");
		if (password.length() < 6 || password.length() > 32)
			return false;
		String Passwrod_PATTERN = "^(?=.*[a-zA-Z]+)(?=.*[0-9]+).{6,32}$";
		Pattern pattern = Pattern.compile(Passwrod_PATTERN);
		Matcher matcher = pattern.matcher(strInput);
		return matcher.matches();
		// return Pattern.matches("^[a-zA-Z0-9]*$", strInput);
	}

	/**
	 * 숫자 여부 체크
	 * @param strInput
	 * @return
	 */
	public static boolean validNum(String strInput) {
		return Pattern.matches("^[0-9]*$", strInput);
	}

	/**
	 * 영문 + 숫자 조합 여부 체크
	 * @param strInput
	 * @return
	 */
	public static boolean validAlphaNum(String strInput) {
		return Pattern.matches("^[a-zA-Z0-9]*$", strInput);
	}

	/**
	 * 암호화하기(BCrypt)
	 * @param str
	 * @return
	 */
	public static String encoder(String str) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		PasswordEncoding passwordEncoding = new PasswordEncoding(passwordEncoder);
		
		return passwordEncoding.encode(str);
	}
	
	/**
	 * 암호화 비교하기(BCrypt)
	 * @param encryptStr
	 * @param normalStr
	 * @return
	 */
	public static boolean matches(String encryptStr, String normalStr) {
		return encryptStr.equals(encoder(normalStr));
	}

	/**
	 * 암호화하기(SHA)
	 * @param str
	 * @return
	 */
	public static String shaEncoder(String str) {
		SHAPasswordEncoder shaPasswordEncoder = new SHAPasswordEncoder(512);
		shaPasswordEncoder.setEncodeHashAsBase64(true);
		PasswordEncoding passwordEncoding = new PasswordEncoding(shaPasswordEncoder);
		
		return passwordEncoding.encode(str);
	}

	/**
	 * 암호화 비교하기(SHA)
	 * @param encryptStr
	 * @param normalStr
	 * @return
	 */
	public static boolean shaMatches(String encryptStr, String normalStr) {
		return encryptStr.equals(shaEncoder(normalStr));
	}
	
	/**
	 * Base64 암호화
	 * @param encodeStr
	 * @return
	 */
	public static String base64Encode(String str) {
		byte[] encoded = Base64.encodeBase64(str.getBytes());
		return new String(encoded);
	}
	
	/**
	 * Base64 복호화
	 * @param encodeStr
	 * @return
	 */
	public static String base64Decode(String encodeStr) {
		byte[] decoded = Base64.decodeBase64(encodeStr);
		return new String(decoded);
	}
	
	public static List<Map<String,Object>> getGroupBy() {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("value", "day");
    	map.put("text", "Days");
    	list.add(map);
    	
    	map = new HashMap<String,Object>();
    	map.put("value", "week");
    	map.put("text", "Weeks");
    	list.add(map);
    	
    	map = new HashMap<String,Object>();
    	map.put("value", "month");
    	map.put("text", "Months");
    	list.add(map);
    	
    	map = new HashMap<String,Object>();
    	map.put("value", "year");
    	map.put("text", "Years");
    	list.add(map);
    	
    	return list;
	}
	
	/**
	 * Client IP를 가져온다
	 * @param request
	 * @return
	 */
	public static String getClientIP(HttpServletRequest request) {
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
	
	/**
	 * Return URL을 List에 담는다.
	 * @param request
	 * @return
	 */
	public static ArrayList<Map<String,Object>> setReturnUrl(HttpServletRequest request) {
		String return_url = request.getRequestURI();
    	Enumeration params = request.getParameterNames();
//    	StringBuffer strParam = new StringBuffer();
    	ArrayList<Map<String,Object>> rtnList = new ArrayList<Map<String,Object>>();
    	Map<String,Object> urlMap = new HashMap<String,Object>();
    	urlMap.put("url", return_url);
    	rtnList.add(urlMap);
    	while(params.hasMoreElements()) {
    		urlMap = new HashMap<String,Object>();
    	    String name = (String) params.nextElement();
    	    String value = request.getParameter(name);
    	    urlMap.put("name",name);
    	    urlMap.put("value", value);
    	    rtnList.add(urlMap);
    	}
    	return rtnList;
	}
	
	/**
	 * Return URL의 URL과 파라미터를 리턴한다.
	 * @param mv
	 * @param list
	 * @param url2
	 * @return
	 */
	public static String getReturnUrl(ModelAndView mv, ArrayList<Map<String,Object>> list, String url2) {
		String url = "";
		int size = list.size();
		Map<String,Object> map = null;
		for(int i=0;i<size;i++) {
			map = list.get(i);
			if(null!=map.get("url")) {
				url = ObjectUtils.null2Value(map.get("url"), url2);
			}
			if(null!=map.get("name")) {
				mv.addObject(ObjectUtils.null2void(map.get("name")), ObjectUtils.null2void(map.get("value")));
			}
		}
		
		return url;
	}
}
