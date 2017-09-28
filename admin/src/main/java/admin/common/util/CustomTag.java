package admin.common.util;

import java.text.NumberFormat;
import java.util.List;

import admin.common.controller.CodeController;

public class CustomTag {
	public static String getBaseUrl() {
        return "/";
    }
	
	public static int getLanguage() {
		return StoreUtils.getLanguageId();
    }
	
	/**
	 * Success 메세지 만들기
	 * @param message
	 * @return
	 */
	public static String getSuccess(String message) {
		if(null!=message && !message.equals("") && !message.equals("{}")) {
			StringBuffer sb = new StringBuffer();
			sb.append("<div class=\"custom-alerts alert alert-success fade in\">");
			sb.append("	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\"></button>");
			sb.append("	<i class=\"fa-lg fa fa-check\"></i> ").append(message);
			sb.append("</div>");
			
			return sb.toString();
		}
		return "";
	}
	
	public static String getError(String error) {
		if(null!=error && !error.equals("") && !error.equals("{}")) {
			StringBuffer sb = new StringBuffer();
			sb.append("<div class=\"custom-alerts alert alert-danger fade in\">");
			sb.append("	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\"></button>");
			sb.append("	<i class=\"fa-lg fa fa-warning\"></i> ").append(error);
			sb.append("</div>");
			
			return sb.toString();
		}
		return "";
	}
	
	
	/**
	 * ERROR 메세지 만들기
	 * @param list
	 * @return
	 */
	public static String getErrorList(List<String> list) {
		if(null!=list && list.size()>0) {
			int size = list.size();
			StringBuffer sb = new StringBuffer();
			sb.append("<ul class='messages'><li class='error-msg'><ul>");
			for(int i=0;i<size;i++) {
				sb.append("<li><span><i class='fa fa-exclamation-triangle'></i> ").append(list.get(i)).append("</span></li>");
			}
			sb.append("</ul></li></ul>");
			return sb.toString();
		} else {
			return "";
		}
	}
	
	public static String getCurrency(String currency) {
		if(null!=currency && !currency.equals("")) {
			double value = Double.parseDouble(currency);
		    java.util.Currency usd = java.util.Currency.getInstance("USD");
		    java.text.NumberFormat format = java.text.NumberFormat.getCurrencyInstance(java.util.Locale.US);
		    format.setCurrency(usd);
		    return format.format(value);
		}
		return "0";
	    
//		double per2 = Double.parseDouble(currency);
//		double per = Double.parseDouble(String.format("%,10d.2f",per2));
//		return LanguageUtil.getCurrency()+per;
	}
	
	public static String getNumber(String number) {
		if(null!=number && !number.equals("")) {
			long val = (long) Double.parseDouble(number);
//			long val = Long.parseLong(number);
			NumberFormat format = NumberFormat.getNumberInstance();
	        return format.format(val);
		}
		return "0";
	}
	
	/**
	 * 주문 상태코드명 태그
	 * @param order_status_id
	 * @return
	 * @throws Exception
	 */
	public static String getOrderStatusName(String order_status_id) throws Exception {
		CodeController code = new CodeController();
		return ObjectUtils.null2void(code.getOrderStatusName(order_status_id));
	}
	
	/**
	 * Yes Or No
	 * @param yes_or_no
	 * @return
	 * @throws Exception
	 */
	public static String getYesOrNo(String yes_or_no) throws Exception {
		if(yes_or_no.equals("1") || yes_or_no.equals("true")) {
			return "예";
		} else {
			return "아니오";
		}
	}

	/**
	 * 활성/비활성
	 * @param yes_or_no
	 * @return
	 * @throws Exception
	 */
	public static String getActive(String active) throws Exception {
		if(active.equals("1") || active.equals("true")) {
			return "활성";
		} else {
			return "비활성";
		}
	}
	
	/**
	 * 선택된 메뉴 활성 시키기
	 * @param reqUrl
	 * @param clickUrl
	 * @return
	 * @throws Exception
	 */
	public static String getActiveMenu(String reqUrl, String clickUrl) throws Exception {
		if(reqUrl.equals(clickUrl)) return "active ";
		return "";
	}
	
	/**
	 * 선택된 메뉴 활성 시키기(URL이 포함됐는지)
	 * @param reqUrl
	 * @param clickUrl
	 * @return
	 * @throws Exception
	 */
	public static String getContainsActiveMenu(String reqUrl, String clickUrl) throws Exception {
//		System.err.println(reqUrl);
//		System.err.println(clickUrl);
//		System.err.println(clickUrl.contains(reqUrl));
//		System.err.println(reqUrl.contains(clickUrl));
		if(clickUrl.length()>3 && reqUrl.contains(clickUrl)) {
			return "active ";
		}
		return "";
	}
	
	/**
	 * 선택된 메뉴 활성 시키기(하위 메뉴가 있을 때)
	 * @param reqUrl
	 * @param clickUrl
	 * @return
	 * @throws Exception
	 */
	public static String getActiveOpenMenu(String reqUrl, String clickUrl) throws Exception {
		if(reqUrl.equals(clickUrl)) return "active open ";
		return "";
	}
}
