package admin.system.service;

import java.util.List;
import java.util.Map;

public interface LanguagesService {
	/**
	 * Languages
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> languages() throws Exception;
	
	/**
	 * 상세
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> languageInfo(Map<String, Object> map) throws Exception;
	
	/**
	 * 수정
	 * @param map
	 * @throws Exception
	 */
	void updateLanguage(Map<String, Object> map) throws Exception;
	
	/**
	 * 이미지 저장
	 * @param map
	 * @throws Exception
	 */
	void updateImage(Map<String, Object> map) throws Exception;
}
