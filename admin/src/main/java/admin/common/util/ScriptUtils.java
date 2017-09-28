package admin.common.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

public class ScriptUtils {
	/**
	 * Dashboard
	 * @param mv
	 */
	public static void dashboardScript(ModelAndView mv) {
		mv.addObject("META_TITLE", "ADMIN::Dashboard");
		
		List<String> css = new ArrayList<String>();
		css.add("/assets/global/plugins/datatables/datatables.min.css");
		css.add("/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.css");
		css.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css");
    	css.add("/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css");
    	css.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css");
    	mv.addObject("HEADER_CSS", css);
    	
    	List<String> js = new ArrayList<String>();
    	js.add("/assets/global/plugins/moment.min.js");
    	js.add("/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.js");
    	js.add("/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js");
    	js.add("/assets/global/plugins/highcharts/js/highcharts.js");
    	js.add("/assets/global/plugins/highcharts/js/highcharts-3d.js");
    	js.add("/assets/global/plugins/highcharts/js/highcharts-more.js");
    	mv.addObject("FOOTER_JS", js);
    	
    	List<String> page_js = new ArrayList<String>();
    	page_js.add("/assets/pages/scripts/components-date-time-pickers.min.js");
    	mv.addObject("PAGE_LEVEL_JS", page_js);
	}
	
	/**
	 * 주문페이지
	 * @param mv
	 */
	public static void salesOrdersScript(ModelAndView mv) {
		mv.addObject("META_TITLE", "ADMIN::주문");
		
		List<String> css = new ArrayList<String>();
    	css.add("/assets/global/plugins/datatables/datatables.min.css");
    	css.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css");
    	css.add("/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css");
    	css.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css");
    	mv.addObject("HEADER_CSS", css);
    	
    	List<String> js = new ArrayList<String>();
//    	js.add("/assets/global/scripts/datatable.js");
//    	js.add("/assets/global/plugins/datatables/datatables.min.js");
//    	js.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js");
    	js.add("/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js");
//    	js.add("/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js");
    	mv.addObject("FOOTER_JS", js);
	}
	
	/**
	 * 주문 상세페이지
	 * @param mv
	 */
	public static void salesOrderViewScript(ModelAndView mv) {
		mv.addObject("META_TITLE", "ADMIN::주문 상세");
		
		List<String> css = new ArrayList<String>();
    	css.add("/assets/global/plugins/datatables/datatables.min.css");
    	css.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css");
    	css.add("/assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css");
    	css.add("/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css");
    	mv.addObject("HEADER_CSS", css);
    	
    	List<String> js = new ArrayList<String>();
    	js.add("/assets/global/plugins/bootstrap-modal/js/bootstrap-modalmanager.js");
    	js.add("/assets/global/plugins/bootstrap-modal/js/bootstrap-modal.js");
    	js.add("/assets/pages/scripts/jsp/sales-order-view-payment.js");
    	js.add("/assets/pages/scripts/jsp/sales-order-view-shipping.js");
    	mv.addObject("FOOTER_JS", js);
	}
	
	/**
	 * 고객 목록
	 * @param mv
	 */
	public static void customersScript(ModelAndView mv) {
		mv.addObject("META_TITLE", "ADMIN::고객 목록");
		
		List<String> css = new ArrayList<String>();
    	css.add("/assets/global/plugins/datatables/datatables.min.css");
    	css.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css");
    	css.add("/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css");
    	css.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css");
    	mv.addObject("HEADER_CSS", css);
    	
    	List<String> js = new ArrayList<String>();
//    	js.add("/assets/global/scripts/datatable.js");
//    	js.add("/assets/global/plugins/datatables/datatables.min.js");
//    	js.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js");
    	js.add("/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js");
//    	js.add("/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js");
    	mv.addObject("FOOTER_JS", js);
	}
	
	/**
	 * 고객 상세
	 * @param mv
	 */
	public static void customerViewScript(ModelAndView mv) {
		mv.addObject("META_TITLE", "ADMIN::고객 상세");
		
		List<String> css = new ArrayList<String>();
    	css.add("/assets/pages/css/profile-2.min.css");
    	mv.addObject("HEADER_CSS", css);
    	
    	List<String> js = new ArrayList<String>();
//    	js.add("/assets/global/scripts/datatable.js");
//    	js.add("/assets/global/plugins/datatables/datatables.min.js");
//    	js.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js");
//    	js.add("/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js");
//    	js.add("/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js");
    	mv.addObject("FOOTER_JS", js);
	}
	
	/**
	 * 제품 카테고리
	 * @param mv
	 */
	public static void categoriesScript(ModelAndView mv) {
		mv.addObject("META_TITLE", "ADMIN::제품 카테고리");
		
		List<String> css = new ArrayList<String>();
    	css.add("/assets/global/plugins/datatables/datatables.min.css");
    	css.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css");
//    	css.add("/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css");
    	css.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css");
    	mv.addObject("HEADER_CSS", css);
    	
    	List<String> js = new ArrayList<String>();
//    	js.add("/assets/global/scripts/datatable.js");
//    	js.add("/assets/global/plugins/datatables/datatables.min.js");
//    	js.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js");
//    	js.add("/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js");
//    	js.add("/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js");
    	mv.addObject("FOOTER_JS", js);
	}
	
	/**
	 * 제품 카테고리 상세페이지
	 * @param mv
	 */
	public static void categoryViewScript(ModelAndView mv) {
		mv.addObject("META_TITLE", "ADMIN::제품 카테고리 상세");
		
		List<String> css = new ArrayList<String>();
    	css.add("/assets/global/plugins/datatables/datatables.min.css");
    	css.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css");
    	css.add("/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css");

    	mv.addObject("HEADER_CSS", css);
    	
    	List<String> js = new ArrayList<String>();
    	js.add("/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js");
    	
    	mv.addObject("FOOTER_JS", js);
	}
	
	/**
	 * 제품 카테고리 이미지 페이지
	 * @param mv
	 */
	public static void categoryViewImaeScript(ModelAndView mv) {
		mv.addObject("META_TITLE", "ADMIN::제품 카테고리 이미지");
		
		List<String> css = new ArrayList<String>();
    	css.add("/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css");

    	mv.addObject("HEADER_CSS", css);
    	
    	List<String> js = new ArrayList<String>();
    	js.add("/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js");
    	
    	mv.addObject("FOOTER_JS", js);
	}
	
	/**
	 * 제품 목록
	 * @param mv
	 */
	public static void productsScript(ModelAndView mv) {
		mv.addObject("META_TITLE", "ADMIN::제품 목록");
		
		List<String> css = new ArrayList<String>();
    	css.add("/assets/global/plugins/datatables/datatables.min.css");
    	css.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css");
//    	css.add("/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css");
    	css.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css");
    	mv.addObject("HEADER_CSS", css);
    	
    	List<String> js = new ArrayList<String>();
//    	js.add("/assets/global/scripts/datatable.js");
//    	js.add("/assets/global/plugins/datatables/datatables.min.js");
//    	js.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js");
//    	js.add("/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js");
//    	js.add("/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js");
    	mv.addObject("FOOTER_JS", js);
	}
	
	/**
	 * 제품 상세페이지
	 * @param mv
	 */
	public static void productViewScript(ModelAndView mv) {
		mv.addObject("META_TITLE", "ADMIN::제품 상세");
		
		List<String> css = new ArrayList<String>();
    	css.add("/assets/global/plugins/datatables/datatables.min.css");
    	css.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css");
    	css.add("/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css");

    	mv.addObject("HEADER_CSS", css);
    	
    	List<String> js = new ArrayList<String>();
    	js.add("/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js");
    	
    	mv.addObject("FOOTER_JS", js);
	}
	
	/**
	 * 제품 이미지 페이지
	 * @param mv
	 */
	public static void productViewImaeScript(ModelAndView mv) {
		mv.addObject("META_TITLE", "ADMIN::제품 상세");
		
		List<String> css = new ArrayList<String>();
    	css.add("/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css");

    	mv.addObject("HEADER_CSS", css);
    	
    	List<String> js = new ArrayList<String>();
    	js.add("/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js");
    	
    	mv.addObject("FOOTER_JS", js);
	}
	
	/**
	 * 제품 할인 페이지
	 * @param mv
	 */
	public static void productViewDiscountScript(ModelAndView mv) {
		mv.addObject("META_TITLE", "ADMIN::제품 상세");
		
		List<String> css = new ArrayList<String>();
    	css.add("/assets/global/plugins/datatables/datatables.min.css");
    	css.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css");
    	css.add("/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css");
    	css.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css");
    	mv.addObject("HEADER_CSS", css);
    	
    	List<String> js = new ArrayList<String>();
//    	js.add("/assets/global/scripts/datatable.js");
//    	js.add("/assets/global/plugins/datatables/datatables.min.js");
//    	js.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js");
    	js.add("/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js");
//    	js.add("/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js");
    	mv.addObject("FOOTER_JS", js);
	}
	
	/**
	 * 컨텐츠 페이지
	 * @param mv
	 */
	public static void contentsViewImaeScript(ModelAndView mv) {
		List<String> css = new ArrayList<String>();
    	css.add("/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css");

    	mv.addObject("HEADER_CSS", css);
    	
    	List<String> js = new ArrayList<String>();
    	js.add("/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js");
    	
    	mv.addObject("FOOTER_JS", js);
	}
	
	/**
	 * 질병과 추천 생약제 상세
	 * @param mv
	 */
	public static void securityDiseaseViewScript(ModelAndView mv) {
		mv.addObject("META_TITLE", "ADMIN::질병과 추천 생약제 상세");
		
		List<String> css = new ArrayList<String>();
//    	css.add("/assets/global/plugins/bootstrap-select/css/bootstrap-select.css");
//    	css.add("/assets/global/plugins/select2/css/select2-bootstrap.min.css");
    	mv.addObject("HEADER_CSS", css);
    	
    	List<String> js = new ArrayList<String>();
//    	js.add("/assets/global/plugins/bootstrap-select/js/bootstrap-select.min.js");
    	mv.addObject("FOOTER_JS", js);
    	
    	List<String> js2 = new ArrayList<String>();
//    	js2.add("/assets/pages/scripts/components-bootstrap-select.min.js");
    	mv.addObject("PAGE_LEVEL_JS", js2);
	}
	
	/**
	 * 책 페이지
	 * @param mv
	 */
	public static void bookViewImaeScript(ModelAndView mv) {
		mv.addObject("META_TITLE", "ADMIN::책 상세");
		
		List<String> css = new ArrayList<String>();
    	css.add("/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css");

    	mv.addObject("HEADER_CSS", css);
    	
    	List<String> js = new ArrayList<String>();
    	js.add("/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js");
    	
    	mv.addObject("FOOTER_JS", js);
	}
	
	/**
	 * 레포트
	 * @param mv
	 */
	public static void reportsScript(ModelAndView mv) {
		List<String> css = new ArrayList<String>();
    	css.add("/assets/global/plugins/datatables/datatables.min.css");
    	css.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css");
    	css.add("/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css");
    	css.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css");
    	mv.addObject("HEADER_CSS", css);
    	
    	List<String> js = new ArrayList<String>();
//    	js.add("/assets/global/scripts/datatable.js");
//    	js.add("/assets/global/plugins/datatables/datatables.min.js");
//    	js.add("/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js");
    	js.add("/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js");
//    	js.add("/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js");
    	mv.addObject("FOOTER_JS", js);
	}

}
