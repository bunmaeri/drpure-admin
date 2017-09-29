package admin.myhome.products.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import admin.myhome.categories.service.MyCategoriesService;
import admin.common.common.CommandMap;
import admin.common.constant.Session;
import admin.common.controller.BaseController;
import admin.common.service.CommonService;
import admin.common.util.CommonUtils;
import admin.common.util.DateUtils;
import admin.common.util.FileUtils;
import admin.common.util.ObjectUtils;
import admin.common.util.ScriptUtils;
import admin.myhome.common.util.MyStoreUtils;
import admin.myhome.products.service.MyProductViewService;
import admin.system.service.LanguagesService;

@Controller
public class MyProductViewController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="myProductViewService")
	private MyProductViewService myProductViewService;
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	@Resource(name="languagesService")
	private LanguagesService languagesService;
	
	@Resource(name="fileUtils")
    private FileUtils fileUtils;
	
	@Resource(name="myCategoriesService")
	private MyCategoriesService myCategoriesService;
	
	/**
	 * 제품 상세 페이지(Meta)
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/my_product/view/meta/{product_id}/{language_id}.dr")
    public ModelAndView productViewMeta(HttpServletRequest request, @PathVariable String product_id, @PathVariable String language_id, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_products/product_view_meta");
    	
    	commandMap.put("product_id", product_id);
    	commandMap.put("language_id", 1);
    	/**
    	 * 한글 제품 Meta 조회
    	 */
    	Map<String,Object> product = myProductViewService.productMeta(commandMap.getMap());
    	mv.addObject("product", product); // 제품 Meta 정보
    	
    	commandMap.put("language_id", language_id);
    	/**
    	 * 제품 Meta 조회
    	 */
    	Map<String,Object> info = myProductViewService.productMeta(commandMap.getMap());

    	mv.addObject("info", info); // 제품 Meta 정보
    	mv.addObject("languages", languagesService.languages()); // 언어 종류

    	mv.addObject("product_id", product_id);
    	mv.addObject("language_id", language_id);
    	
    	ScriptUtils.productViewScript(mv);

    	return mv;
    }

	/**
	 * 제품 저장 페이지(Meta)
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/my_product/save/meta.dr")
    public ModelAndView productSaveMeta(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");

		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validFormMeta(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
			// 기존에 존재하는지 체크
			Map<String,Object> info = myProductViewService.productMeta(commandMap.getMap());
			if(null!=info && null!=info.get("product_id")) {
				myProductViewService.updateProductMeta(commandMap.getMap()); // 업데이트
			} else {
				if(!commandMap.get2String("product_id").equals("0")) {
					myProductViewService.addProductMeta(commandMap.getMap()); // 추가
				} else {
					commandMap.put("model", myProductViewService.maxModel()); // MAX 모델번호 가져오기
					commandMap.put("stock_status_id", "7");
					commandMap.put("shipping", "1");
					commandMap.put("length", "0.00000000");
					commandMap.put("width", "0.00000000");
					commandMap.put("height", "0.00000000");
					commandMap.put("length_class_id", "2");
					commandMap.put("subtract", "1");
					commandMap.put("sort_order", "0");
					commandMap.put("viewed", "0");
					myProductViewService.newProduct(commandMap.getMap()); // 제품 테이블에 신규로 추가
					
					myProductViewService.newProductMeta(commandMap.getMap()); // 제품 Meta NEW

					commandMap.put("quantity", "0");
					myProductViewService.newProductQuantity(commandMap.getMap()); // 제품 수량 테이블에 신규로 추가
					
					mv.addObject("success_product_id", commandMap.get("product_id"));
					mv.addObject("success_language_id", commandMap.get("language_id"));
				}
			}
			mv.addObject("success", "정상적으로 저장되었습니다.");
		} else {
			mv.addObject("error", "오류 메세지를 확인해주십시요.");
			mv.addObject("Message", validMap);
		}
    
       	return mv;
    }
	/**
	 * Meta Validation
	 * @param validMap
	 * @param commandMap
	 * @throws Exception
	 */
	public void validFormMeta(Map<String, Object> validMap, CommandMap commandMap) throws Exception{
		String name = commandMap.get2String("name");
		if(ObjectUtils.isEmpty(name) || (name.length()<3 && name.length()>255)) {
			BaseController.setErrorMessage(validMap, "error_name", "3~255글자 사이로 입력하세요.");
		}
		
//		String description = commandMap.get2String("description");
//		if(ObjectUtils.isEmpty(description) || description.length()<1) {
//			BaseController.setErrorMessage(validMap, "error_description", Health.Error.DESCRIPTION);
//		}
		
		String meta_title = commandMap.get2String("meta_title");
		if(!ObjectUtils.isEmpty(meta_title) && meta_title.length()>255) {
			BaseController.setErrorMessage(validMap, "error_meta_title", "255글자보다 작아야 합니다.");
		}
		
		String meta_description = commandMap.get2String("meta_description");
		if(!ObjectUtils.isEmpty(meta_description) && meta_description.length()>255) {
			BaseController.setErrorMessage(validMap, "error_meta_description", "255글자보다 작아야 합니다.");
		}
		
		String meta_keyword = commandMap.get2String("meta_keyword");
		if(!ObjectUtils.isEmpty(meta_keyword) && meta_keyword.length()>255) {
			BaseController.setErrorMessage(validMap, "error_meta_keyword", "255글자보다 작아야 합니다.");
		}
	}
	
	/**
	 * 제품 상세 페이지(Info)
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/my_product/view/info/{product_id}/{language_id}.dr")
    public ModelAndView productViewInfo(HttpServletRequest request, @PathVariable String product_id, @PathVariable String language_id, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_products/product_view_info");
    	
    	commandMap.put("product_id", product_id);
    	commandMap.put("language_id", 1);
    	/**
    	 * 한글 제품 Meta 조회
    	 */
    	Map<String,Object> product = myProductViewService.productMeta(commandMap.getMap());
    	mv.addObject("product", product); // 제품 Meta 정보
   
    	commandMap.put("language_id", language_id);
    	/**
    	 * 제품 Info 조회
    	 */
    	Map<String,Object> info = myProductViewService.product(commandMap.getMap());
    	if(null!=info && ObjectUtils.null2void(info.get("date_available")).equals("")) {
    		info.put("date_available", DateUtils.getToday("yyyy-MM-dd"));
    	}
    	mv.addObject("info", info); // 제품 Info 정보

    	// 제조사 목록
    	List<Map<String, Object>> manufacturerList = commonService.manufacturerAll();
    	mv.addObject("manufacturerList", manufacturerList);
    	
    	// 원산지 목록
    	List<Map<String, Object>> locationList = commonService.locationAll();
    	mv.addObject("locationList", locationList);
    	
    	// 무게 단위 목록
    	List<Map<String, Object>> weightClassList = commonService.weightClassAll();
    	mv.addObject("weightClassList", weightClassList);

    	mv.addObject("product_id", product_id);
    	mv.addObject("language_id", language_id);
    	
    	ScriptUtils.productViewScript(mv);

    	return mv;
    }
	
	/**
	 * 제품 저장 페이지(Info)
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/my_product/save/info.dr")
    public ModelAndView productSaveInfo(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");

		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validFormInfo(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
			String stock_status_id = "7";
			if(commandMap.get2String("quantity").trim().equals("0")) {
				stock_status_id = "5";
			}
			commandMap.put("stock_status_id", stock_status_id);
			// 기존에 존재하는지 체크
			Map<String,Object> info = myProductViewService.product(commandMap.getMap());
			//System.err.println(info.get("product_id"));
			if(null!=info && null!=info.get("product_id")) {
				myProductViewService.updateProduct(commandMap.getMap()); // 업데이트
				myProductViewService.updateProductQuantity(commandMap.getMap()); // 제품 수량 업데이트
			} else {
				myProductViewService.addProduct(commandMap.getMap()); // 추가
				myProductViewService.addProductQuantity(commandMap.getMap()); // 제품 수량 추가
			}
			
			mv.addObject("success", "정상적으로 저장되었습니다.");
		} else {
			mv.addObject("error", "오류 메세지를 확인해주십시요.");
			mv.addObject("Message", validMap);
		}
    	
       	return mv;
    }
	/**
	 * Info Validation
	 * @param validMap
	 * @param commandMap
	 * @throws Exception
	 */
	public void validFormInfo(Map<String, Object> validMap, CommandMap commandMap) throws Exception{
		String reward_group_id = commandMap.get2String("reward_group_id");
		if(!ObjectUtils.isEmpty(reward_group_id) && reward_group_id.length()>1) {
			if(!CommonUtils.validNum(reward_group_id)) {
				BaseController.setErrorMessage(validMap, "error_reward_group_id", "숫자만 입력하십시요.");
			}
		}
		
//		String model = commandMap.get2String("model");
//		if(!model.equals(commandMap.get2String("last_model"))) {
//			if(ObjectUtils.isEmpty(model) || (model.length()<1 && model.length()>10)) {
//				BaseController.setErrorMessage(validMap, "error_model", "1~10글자 사이로 입력하세요.");
//			} else {
//				if(!CommonUtils.validNum(model)) {
//					BaseController.setErrorMessage(validMap, "error_model", "숫자만 입력하십시요.");
//				} else {
//					int cnt = myProductViewService.duplicateModel(model);
//					if(cnt>0) {
//						BaseController.setErrorMessage(validMap, "error_model", "이미 사용중인 Model입니다.");
//					}
//				}
//			}
//		}

		String price = commandMap.get2String("price");
		if(ObjectUtils.isEmpty(price) || (price.length()<1 && price.length()>15)) {
			BaseController.setErrorMessage(validMap, "error_price", "1~64글자 사이로 입력하세요.");
		} else {
			int dotcount = StringUtils.countOccurrencesOf(price, ".");
			if(dotcount>1) {
				BaseController.setErrorMessage(validMap, "error_price", "가격이 잘못 입력되었습니다.");
			} else {
				price = price.replace(".", "");
				if(!CommonUtils.validNum(price)) {
					BaseController.setErrorMessage(validMap, "error_price", "가격이 잘못 입력되었습니다.");
				}
			}
		}
		
		String quantity = commandMap.get2String("quantity");
		if(ObjectUtils.isEmpty(quantity) || (quantity.length()<1 && quantity.length()>4)) {
			BaseController.setErrorMessage(validMap, "error_quantity", "1~4글자 사이로 입력하세요.");
		} else {
			if(!CommonUtils.validNum(quantity)) {
				BaseController.setErrorMessage(validMap, "error_quantity", "숫자만 입력하십시요.");
			}
		}
		
		String minimum = commandMap.get2String("minimum");
		if(!ObjectUtils.isEmpty(minimum) && minimum.length()>1) {
			if(!CommonUtils.validNum(minimum)) {
				BaseController.setErrorMessage(validMap, "error_minimum", "숫자만 입력하십시요.");
			}
		}
		
		String weight = commandMap.get2String("weight");
		if(!ObjectUtils.isEmpty(weight) && weight.length()>1) {
			int dotcount = StringUtils.countOccurrencesOf(weight, ".");
			if(dotcount>1) {
				BaseController.setErrorMessage(validMap, "error_weight", "무게가 잘못 입력되었습니다.");
			} else {
				weight = weight.replace(".", "");
				if(!CommonUtils.validNum(weight)) {
					BaseController.setErrorMessage(validMap, "error_weight", "무게가 잘못 입력되었습니다.");
				}
			}
		}
		
		String sku = commandMap.get2String("sku");
		if(!ObjectUtils.isEmpty(sku) && sku.length()>64) {
			BaseController.setErrorMessage(validMap, "error_sku", "64글자보다 작아야 합니다.");
		}
		
		String upc = commandMap.get2String("upc");
		if(!ObjectUtils.isEmpty(upc) && upc.length()>12) {
			BaseController.setErrorMessage(validMap, "error_upc", "12글자보다 작아야 합니다.");
		}
		
		String date_available = commandMap.get2String("date_available");
		if(ObjectUtils.isEmpty(date_available) || date_available.length()<1) {
			BaseController.setErrorMessage(validMap, "error_date_available", "반드시 입력하셔야 합니다.");
		} else {
			if(!DateUtils.isValid(date_available, "yyyy-MM-dd")) {
				BaseController.setErrorMessage(validMap, "error_date_available", "날짜 포맷을 확인해주십시요. (ex, 2017-08-30)");
			}
		}
	}
	
	/**
	 * 제품 상세 페이지(Image)
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/my_product/view/image/{product_id}/{language_id}.dr")
    public ModelAndView productViewImage(HttpServletRequest request, @PathVariable String product_id, @PathVariable String language_id, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_products/product_view_image");
    	
    	commandMap.put("product_id", product_id);
    	commandMap.put("language_id", 1);
    	/**
    	 * 한글 제품 Meta 조회
    	 */
    	Map<String,Object> product = myProductViewService.productMeta(commandMap.getMap());
    	mv.addObject("product", product); // 제품 Meta 정보
   
    	commandMap.put("language_id", language_id);
    	/**
    	 * 제품 Info 조회
    	 */
    	Map<String,Object> info = myProductViewService.product(commandMap.getMap());

    	mv.addObject("info", info); // 제품 Info 정보

    	mv.addObject("product_id", product_id);
    	mv.addObject("language_id", language_id);
    	
    	if(null!=BaseController.getCustomSession(request, Session.SUCCESS)) {
    		mv.addObject("successMsg", BaseController.getCustomSession(request, Session.SUCCESS));
    		BaseController.setCustomSession(request, null, Session.SUCCESS);
    	}
    	
    	ScriptUtils.productViewImaeScript(mv);

    	return mv;
    }
	
	/**
	 * 제품 저장 페이지(Image)
	 * @param commandMap       
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/my_product/save/image.dr", method = RequestMethod.POST)
    public ModelAndView productSaveImage(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/my_product/view/image/"+commandMap.get2String("product_id")+"/"+commandMap.get2String("language_id")+".dr");

		String folder = "product/";
		fileUtils.setFilePath(MyStoreUtils.getFilepath()+folder);
		fileUtils.setViewPath(folder);
		fileUtils.parseInsertFileInfo(commandMap.getMap(), request);
		
		if(!commandMap.get2String("image").equals("")) {
//			commandMap.put("image", commandMap.get2String("original_image"));
			myProductViewService.updateImage(commandMap.getMap());
		}
		
		BaseController.setCustomSession(request, "정상적으로 저장되었습니다.", Session.SUCCESS);
//		mv.addObject("success", "정상적으로 저장되었습니다.");
    	
       	return mv;
    }
	
	/**
	 * 제품 저장 페이지(Image) --> 안씀
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/my_product/save/image/ajax.dr")
    public ModelAndView productSaveImageAjax(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");

		fileUtils.parseInsertFileInfo(commandMap.getMap(), request);
		
		if(!commandMap.get2String("image").equals("")) {
//			commandMap.put("image", commandMap.get2String("original_image"));
			myProductViewService.updateImage(commandMap.getMap());
		}
		
		mv.addObject("success", "정상적으로 저장되었습니다.");
    	
       	return mv;
    }
	
	/**
	 * 제품 상세 페이지(카테고리)
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/my_product/view/category/{product_id}/{language_id}.dr")
    public ModelAndView productViewCategory(HttpServletRequest request, @PathVariable String product_id, @PathVariable String language_id, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_products/product_view_category");
    	
    	commandMap.put("product_id", product_id);
    	commandMap.put("language_id", 1);
    	/**
    	 * 한글 제품 Meta 조회
    	 */
    	Map<String,Object> product = myProductViewService.productMeta(commandMap.getMap());
    	mv.addObject("product", product); // 제품 Meta 정보
   
    	commandMap.put("language_id", language_id);
    	/**
    	 * 제품 카테고리 조회
    	 */
    	List<Map<String,Object>> categoryList = new ArrayList<Map<String,Object>>();
    	List<Map<String,Object>> list = myProductViewService.categoriesList(commandMap.getMap());
    	Map<String,Object> categoryMap = null;
    	int size = list.size();
    	int occurance = 0;
    	for(int i=0;i<size;i++) {
    		categoryMap = list.get(i);
    		occurance = StringUtils.countOccurrencesOf(ObjectUtils.null2void(categoryMap.get("name_path")),"||");
    		
    		categoryMap.put("level", occurance);
    		
    		categoryMap.put("margin", occurance*20);
    		categoryList.add(categoryMap);
    	}

    	mv.addObject("categoryList", categoryList); // 제품 카테고리 조회
    	mv.addObject("product_id", product_id);
    	mv.addObject("language_id", language_id);
    	
    	if(null!=BaseController.getCustomSession(request, Session.SUCCESS)) {
    		mv.addObject("successMsg", BaseController.getCustomSession(request, Session.SUCCESS));
    		BaseController.setCustomSession(request, null, Session.SUCCESS);
    	}
    	
    	ScriptUtils.productViewImaeScript(mv);

    	return mv;
    }
	
	/**
	 * 제품 저장 페이지(카테고리)
	 * @param commandMap       
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/my_product/save/category.dr")
    public ModelAndView productSaveCategory(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/my_product/view/category/"+commandMap.get2String("product_id")+"/"+commandMap.get2String("language_id")+".dr");
		
		System.err.println(commandMap.get2String("category_ids"));
		myProductViewService.deleteProductCategory(commandMap.getMap());
		
		String[] category_ids = commandMap.get2String("category_ids").split(",");
		for(int i=0;i<category_ids.length;i++) {
			commandMap.put("category_id", category_ids[i]);
			myProductViewService.addProductCategory(commandMap.getMap());
		}
		
		BaseController.setCustomSession(request, "정상적으로 저장되었습니다.", Session.SUCCESS);
//		mv.addObject("success", "정상적으로 저장되었습니다.");
    	
       	return mv;
    }
	
	/**
	 * 제품 상세 페이지(질병과 추천생약제)
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/my_product/view/disease/{product_id}/{language_id}.dr")
    public ModelAndView productViewDisease(HttpServletRequest request, @PathVariable String product_id, @PathVariable String language_id, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_products/product_view_disease");
    	
    	commandMap.put("product_id", product_id);
    	commandMap.put("language_id", 1);
    	/**
    	 * 한글 제품 Meta 조회
    	 */
    	Map<String,Object> product = myProductViewService.productMeta(commandMap.getMap());
    	mv.addObject("product", product); // 제품 Meta 정보
   
    	commandMap.put("language_id", language_id);
    	/**
    	 * 질병과 추천생약제 카테고리 목록 조회
    	 */
    	commandMap.put("code", "disease");
    	commandMap.put("medicine_id", 2); // 필수 추천 생약제
    	List<Map<String,Object>> mustList = myProductViewService.diseaseSecurityContentsMedicine(commandMap.getMap());

    	mv.addObject("mustList", mustList);
    	
    	commandMap.put("medicine_id", 3); // 추가 추천 생제
    	List<Map<String,Object>> addList = myProductViewService.diseaseSecurityContentsMedicine(commandMap.getMap());

    	mv.addObject("addList", addList); // 제품 카테고리 조회
    	
    	mv.addObject("product_id", product_id);
    	mv.addObject("language_id", language_id);
    	
    	if(null!=BaseController.getCustomSession(request, Session.SUCCESS)) {
    		mv.addObject("successMsg", BaseController.getCustomSession(request, Session.SUCCESS));
    		BaseController.setCustomSession(request, null, Session.SUCCESS);
    	}
   
    	ScriptUtils.productViewImaeScript(mv);

    	return mv;
    }
	
	/**
	 * 제품 저장 페이지(질병과 추천생약제)
	 * @param commandMap       
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/my_product/save/disease.dr")
    public ModelAndView productSaveDisease(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/my_product/view/disease/"+commandMap.get2String("product_id")+"/"+commandMap.get2String("language_id")+".dr");
		
		System.err.println(commandMap.get2String("must_contents_ids"));
		myProductViewService.deleteSecurityContentsMedicine(commandMap.getMap());
		
		// 필수 추천 생약제 등록
		String[] must_contents_ids = commandMap.get2String("must_contents_ids").split(",");
		for(int i=0;i<must_contents_ids.length;i++) {
			commandMap.put("contents_id", must_contents_ids[i]);
			commandMap.put("medicine_id", 2);
			commandMap.put("status", 1);
			myProductViewService.addSecurityContentsMedicine(commandMap.getMap());
		}
		
		// 추가 추천 생약제 등록
		String[] add_contents_ids = commandMap.get2String("add_contents_ids").split(",");
		for(int i=0;i<add_contents_ids.length;i++) {
			commandMap.put("contents_id", add_contents_ids[i]);
			commandMap.put("medicine_id", 3);
			commandMap.put("status", 1);
			myProductViewService.addSecurityContentsMedicine(commandMap.getMap());
		}
		
		BaseController.setCustomSession(request, "정상적으로 저장되었습니다.", Session.SUCCESS);
//		mv.addObject("success", "정상적으로 저장되었습니다.");
    	
       	return mv;
    }
	
	/**
	 * 제품 상세 페이지(할인가격)
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/my_product/view/discount/{product_id}/{language_id}.dr")
    public ModelAndView productViewDiscount(HttpServletRequest request, @PathVariable String product_id, @PathVariable String language_id, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_products/product_view_discount");
    	
    	commandMap.put("product_id", product_id);
    	commandMap.put("language_id", 1);
    	/**
    	 * 한글 제품 Meta 조회
    	 */
    	Map<String,Object> product = myProductViewService.productMeta(commandMap.getMap());
    	mv.addObject("product", product); // 제품 Meta 정보
   
    	commandMap.put("language_id", language_id);
    	List<Map<String,Object>> list = myProductViewService.productSpecial(commandMap.getMap());
    	mv.addObject("list", list); // 가격 할인
    	
    	// 고객 그룹 목록
    	List<Map<String, Object>> customerGroupsList = commonService.customerGroups(language_id);
    	mv.addObject("customerGroupsList", customerGroupsList);
    	
    	mv.addObject("product_id", product_id);
    	mv.addObject("language_id", language_id);
    	mv.addObject("today", DateUtils.getToday("yyyy-MM-dd"));
    	
    	if(null!=BaseController.getCustomSession(request, Session.SUCCESS)) {
    		mv.addObject("successMsg", BaseController.getCustomSession(request, Session.SUCCESS));
    		BaseController.setCustomSession(request, null, Session.SUCCESS);
    	}
    	
    	if(null!=BaseController.getCustomSession(request, Session.ERROR)) {
    		mv.addObject("errorMsg", BaseController.getCustomSession(request, Session.ERROR));
    		BaseController.setCustomSession(request, null, Session.ERROR);
    	}
  
    	ScriptUtils.productViewDiscountScript(mv);

    	return mv;
    }
	
	/**
	 * 제품 저장 페이지(할인가격)
	 * @param commandMap       
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/my_product/save/discount.dr")
    public ModelAndView productSaveDiscount(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/my_product/view/discount/"+commandMap.get2String("product_id")+"/"+commandMap.get2String("language_id")+".dr");
		
		if(commandMap.get2String("price").equals("")) {
			BaseController.setCustomSession(request, "가격을 입력하세요.", Session.ERROR);
			return mv;
		}
		if(commandMap.get2String("date_start").equals("")) {
			BaseController.setCustomSession(request, "시작일자를 입력하세요.", Session.ERROR);
			return mv;
		}
		
		commandMap.put("priority", 0);
		if(commandMap.get2String("date_end").equals("")) {
			commandMap.put("date_end", "2099-12-31");
		}
		myProductViewService.addProductSpecial(commandMap.getMap());
		
		BaseController.setCustomSession(request, "정상적으로 저장되었습니다.", Session.SUCCESS);
//		mv.addObject("success", "정상적으로 저장되었습니다.");
    	
       	return mv;
    }
	
	/**
	 * 할인가격 삭제
	 * @param commandMap       
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/my_product/delete/discount.dr")
    public ModelAndView productDeleteDiscount(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/my_product/view/discount/"+commandMap.get2String("product_id")+"/"+commandMap.get2String("language_id")+".dr");
		
		myProductViewService.deleteProductSpecial(commandMap.getMap());
		
		BaseController.setCustomSession(request, "정상적으로 삭제되었습니다.", Session.SUCCESS);
//		mv.addObject("success", "정상적으로 저장되었습니다.");
    	
       	return mv;
    }
}
