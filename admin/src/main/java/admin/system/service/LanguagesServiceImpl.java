package admin.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.system.dao.LanguagesDAO;

@Service("languagesService")
public class LanguagesServiceImpl implements LanguagesService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="languagesDAO")
	private LanguagesDAO languagesDAO;
	
	/**
	 * Languages
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> languages() throws Exception {
		return languagesDAO.languages();
	}
	
	/**
	 * 상세
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> languageInfo(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> tempMap = languagesDAO.languageInfo(map);
		resultMap.put("map", tempMap);
		
		return resultMap;
	}
	
	/**
	 * 수정
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateLanguage(Map<String, Object> map) throws Exception {
		languagesDAO.updateLanguage(map);
	}
	
	/**
	 * 이미지 저장
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateImage(Map<String, Object> map) throws Exception {
		languagesDAO.updateImage(map);
	}
}
