package admin.categories.service;

import java.util.List;
import java.util.Map;

public interface CategoriesService {
	/**
	 * Categories autocomplete 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> autocompleteCategories(Map<String, Object> map) throws Exception;
	
	/**
	 * 카테고리 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> categories(Map<String, Object> map) throws Exception;
	
	/**
	 * 보안 카테고리
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> securityCategories(String disease) throws Exception;
}
