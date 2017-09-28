package admin.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import admin.common.service.CodeService;
import admin.common.util.ObjectUtils;

@Controller
public class UserIpsController {
	public static HashMap<String, Object> CODE_MAP;
	
	@Autowired
	private CodeService codeService;

	public UserIpsController() {}

	@PostConstruct
	public void setCodeList() throws Exception {  
		CODE_MAP = new HashMap<>();
		List<Map<String, Object>> CODE_LIST = codeService.getUserIps();
		if(CODE_LIST != null && CODE_LIST.size() > 0){
			for(Map<String,Object> map : CODE_LIST){
				CODE_MAP.put(ObjectUtils.null2void(map.get("ip")), map.get("ip"));
			}
		}
	}
	
   /**
	* Key에 의한 Value 가져오기
	* @param key
	* @return
	*/
	public boolean getBoolean(String key) throws Exception {
		if(CODE_MAP!= null){
			if(CODE_MAP.containsKey(key)){
				return true;
			}
		} else {
			this.setCodeList();
			if(CODE_MAP.containsKey(key)){
				return true;
			}
		}

		return false;
	}
	
   /**
	* 새로고침
	* @return
	*/
	public void reload() throws Exception {
		CODE_MAP.clear();
		this.setCodeList();
	}
}