package admin.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("languagesDAO")
public class LanguagesDAO extends AbstractDAO {

	/**
	 * languages
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> languages() throws Exception{
		return (List<Map<String, Object>>) selectList("system_languages.languages");
	}
	
	/**
	 * 상세
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> languageInfo(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("system_languages.languageInfo", map);
	}
	
	/**
	 * 수정
	 * @param map
	 * @throws Exception
	 */
	public void updateLanguage(Map<String, Object> map) throws Exception{
		update("system_languages.updateLanguage", map);
	}
	
	/**
	 * 이미지 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateImage(Map<String, Object> map) throws Exception{
		update("system_languages.updateImage", map);
	}
}
