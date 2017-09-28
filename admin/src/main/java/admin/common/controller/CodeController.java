package admin.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import admin.common.service.CodeService;
import admin.common.util.ObjectUtils;
import admin.common.util.StoreUtils;

@Controller
public class CodeController {
	public static HashMap<String, Object> CODE_MAP;
	public static String codeResult;
	
	public static HashMap<String, Object> ORSER_STATUS_MAP;
	
	@Autowired
	private CodeService codeService;

	public CodeController() {}

	@PostConstruct
	public void setCodeList() throws Exception {  
		CODE_MAP = new HashMap<>();
		List<Map<String, Object>> CODE_LIST = codeService.getCodes();
		if(CODE_LIST != null && CODE_LIST.size() > 0){
			for(Map<String,Object> map : CODE_LIST){
				CODE_MAP.put(ObjectUtils.null2void(map.get("key")), map.get("value"));
			}
		}

		ORSER_STATUS_MAP = new HashMap<>();
		List<Map<String, Object>> ORSER_STATUS_LIST = codeService.getOrderStatus(StoreUtils.getLanguageIdString());
		if(ORSER_STATUS_LIST != null && ORSER_STATUS_LIST.size() > 0){
			for(Map<String,Object> map : ORSER_STATUS_LIST){
				ORSER_STATUS_MAP.put(ObjectUtils.null2void(map.get("order_status_id")), map.get("name"));
			}
		}
	}
	
   /**
	* Key에 의한 Value 가져오기
	* @param key
	* @return
	*/
	public String getValue(String key) throws Exception {
		codeResult = "";

		if(CODE_MAP!= null){
			
			if(CODE_MAP.containsKey(key)){
				codeResult = ObjectUtils.null2Value(CODE_MAP.get(key), "");
			}
		} else {
			this.setCodeList();
			if(CODE_MAP.containsKey(key)){
				codeResult = ObjectUtils.null2Value(CODE_MAP.get(key), "");
			}
		}

		return codeResult;
	}
	
   /**
	* Key 의한 Value 가져오기
	* @param key
	* @return
	*/
	public int getValueInt(String key) throws Exception {
		codeResult = "";

		if(CODE_MAP!= null){
			
			if(CODE_MAP.containsKey(key)){
				codeResult = ObjectUtils.null2Value(CODE_MAP.get(key), "");
			}
		} else {
			this.setCodeList();
			if(CODE_MAP.containsKey(key)){
				codeResult = ObjectUtils.null2Value(CODE_MAP.get(key), "");
			}
		}

		return Integer.parseInt(codeResult);
	}
	
	/**
	* Key 의한 Value 가져오기
	* @param key
	* @return
	*/
	public String getOrderStatusName(String order_status_id) throws Exception {
		codeResult = "";

		if(ORSER_STATUS_MAP!= null){
			
			if(ORSER_STATUS_MAP.containsKey(order_status_id)){
				codeResult = ObjectUtils.null2Value(ORSER_STATUS_MAP.get(order_status_id), "");
			}
		} else {
			this.setCodeList();
			if(ORSER_STATUS_MAP.containsKey(order_status_id)){
				codeResult = ObjectUtils.null2Value(ORSER_STATUS_MAP.get(order_status_id), "");
			}
		}

		return codeResult;
	}
	
   /**
	* 새로고침
	* @return
	*/
	public void reload() throws Exception {
		CODE_MAP.clear();
		ORSER_STATUS_MAP.clear();
		this.setCodeList();
	}
}