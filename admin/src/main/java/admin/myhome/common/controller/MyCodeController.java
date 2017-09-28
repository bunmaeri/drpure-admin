package admin.myhome.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import admin.myhome.common.service.MyCodeService;
import admin.common.util.ObjectUtils;

@Controller
public class MyCodeController {
	public static HashMap<String, Object> MY_CODE_MAP;
	public static String MY_codeResult;
	
	@Autowired
	private MyCodeService myCodeService;

	public MyCodeController() {}

	@PostConstruct
	public void setCodeList() throws Exception {  
		MY_CODE_MAP = new HashMap<>();
		List<Map<String, Object>> CODE_LIST = myCodeService.getCodes();
		if(CODE_LIST != null && CODE_LIST.size() > 0){
			for(Map<String,Object> map : CODE_LIST){
				MY_CODE_MAP.put(ObjectUtils.null2void(map.get("key")), map.get("value"));
			}
		}
	}
	
   /**
	* Key에 의한 Value 가져오기
	* @param key
	* @return
	*/
	public String getValue(String key) throws Exception {
		MY_codeResult = "";

		if(MY_CODE_MAP!= null){
			
			if(MY_CODE_MAP.containsKey(key)){
				MY_codeResult = ObjectUtils.null2Value(MY_CODE_MAP.get(key), "");
			}
		} else {
			this.setCodeList();
			if(MY_CODE_MAP.containsKey(key)){
				MY_codeResult = ObjectUtils.null2Value(MY_CODE_MAP.get(key), "");
			}
		}

		return MY_codeResult;
	}
	
   /**
	* Key 의한 Value 가져오기
	* @param key
	* @return
	*/
	public int getValueInt(String key) throws Exception {
		MY_codeResult = "";

		if(MY_CODE_MAP!= null){
			
			if(MY_CODE_MAP.containsKey(key)){
				MY_codeResult = ObjectUtils.null2Value(MY_CODE_MAP.get(key), "");
			}
		} else {
			this.setCodeList();
			if(MY_CODE_MAP.containsKey(key)){
				MY_codeResult = ObjectUtils.null2Value(MY_CODE_MAP.get(key), "");
			}
		}

		return Integer.parseInt(MY_codeResult);
	}
	
   /**
	* 새로고침
	* @return
	*/
	public void reload() throws Exception {
		MY_CODE_MAP.clear();
		this.setCodeList();
	}
}