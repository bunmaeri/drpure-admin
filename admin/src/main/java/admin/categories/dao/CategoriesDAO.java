package admin.categories.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("categoriesDAO")
public class CategoriesDAO extends AbstractDAO {
	/**
	 * Category autocomplete 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> autocompleteCategories(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("categories.autocompleteCategories", map);
	}

	
	/**
	 * 카테고리 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> categories(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("categories.categories", map);
	}
	
	/**
	 * 보안 카테고리
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> securityCategories(String disease) throws Exception{
		return (List<Map<String, Object>>) selectList("categories.securityCategories", disease);
	}
}
