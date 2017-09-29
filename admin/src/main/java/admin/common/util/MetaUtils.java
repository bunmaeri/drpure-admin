package admin.common.util;

import org.springframework.web.servlet.ModelAndView;

public class MetaUtils {
	public static void index(ModelAndView mv) {
		mv.addObject("META_TITLE", "Dashboard");
	}
	
	public static void reportSalesOrder(ModelAndView mv) {
		mv.addObject("META_TITLE", "Report > Sales > Order");
	}
	
	public static void reportSalesTax(ModelAndView mv) {
		mv.addObject("META_TITLE", "Report > Sales > Tax");
	}
	
	public static void reportSalesShipping(ModelAndView mv) {
		mv.addObject("META_TITLE", "Report > Sales > Shipping");
	}
	
	public static void reportProductsViewed(ModelAndView mv) {
		mv.addObject("META_TITLE", "Report > Products > Viewed");
	}
	
	public static void reportProductsPurchased(ModelAndView mv) {
		mv.addObject("META_TITLE", "Report > Products > Purchased");
	}
	
	public static void reportCustomersOrder(ModelAndView mv) {
		mv.addObject("META_TITLE", "Report > Customers > Order");
	}
	
	public static void reportCustomersRewardPoints(ModelAndView mv) {
		mv.addObject("META_TITLE", "Report > Customers > Reward Points");
	}
	
	public static void reportCustomersDonator(ModelAndView mv) {
		mv.addObject("META_TITLE", "Report > Customers > Donator");
	}
	
	public static void contentsInformation(ModelAndView mv) {
		mv.addObject("META_TITLE", "컨텐츠 관리 > 유용 정보");
	}
	
	public static void contentsNotice(ModelAndView mv) {
		mv.addObject("META_TITLE", "컨텐츠 관리 > 메인페이지 공지사항");
	}
	
	public static void contentsHealth(ModelAndView mv) {
		mv.addObject("META_TITLE", "컨텐츠 관리 > 건강정보");
	}
	
	public static void contentsSecurity(ModelAndView mv, String code) {
		if(code.equals("clinic")) {
			mv.addObject("META_TITLE", "컨텐츠 관리 > 임상사례");
		} else
		if(code.equals("disease")) {
			mv.addObject("META_TITLE", "컨텐츠 관리 > 질병과 추천생약제");
		} else
		if(code.equals("health")) {
			mv.addObject("META_TITLE", "컨텐츠 관리 > 건강정보");
		}
	}
	
	public static void contentsBook(ModelAndView mv) {
		mv.addObject("META_TITLE", "컨텐츠 관리 > 책");
	}
	
	public static void contentsEtc(ModelAndView mv) {
		mv.addObject("META_TITLE", "컨텐츠 관리 > 기타 컨텐츠");
	}
	
	public static void marketingTemplets(ModelAndView mv) {
		mv.addObject("META_TITLE", "이메일 마케팅 > 템플릿 관리");
	}
	
	public static void marketingNewsletter(ModelAndView mv) {
		mv.addObject("META_TITLE", "이메일 마케팅 > 이메일 보내기");
	}
	
	public static void settingsLanguage(ModelAndView mv) {
		mv.addObject("META_TITLE", "Settings > Language");
	}
}
