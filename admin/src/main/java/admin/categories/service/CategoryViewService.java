package admin.categories.service;

import java.util.Map;

public interface CategoryViewService {
	/**
	 * 카테고리 Meta 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	Map<String, Object> categoryMeta(Map<String, Object> map) throws Exception;
	
	/**
	 * 카테고리 Meta 저장
	 * @param map
	 * @throws Exception
	 */
	void updateCategoryMeta(Map<String, Object> map) throws Exception;

	/**
	 * 카테고리 Status 저장
	 * @param map
	 * @throws Exception
	 */
	void updateCategoryStatus(Map<String, Object> map) throws Exception;
	
	/**
	 * 카테고리 Parent 저장
	 * @param map
	 * @throws Exception
	 */
	void updateCategoryParent(Map<String, Object> map) throws Exception;
	
	/**
	 * 카테고리 path 저장
	 * @param map
	 * @throws Exception
	 */
	void updateCategoryPaths(Map<String, Object> map) throws Exception;
	
	/**
	 * 카테고리 NEW
	 * @param map
	 * @throws Exception
	 */
	void newCategory(Map<String, Object> map) throws Exception;
	
	/**
	 * 카테고리 Meta 추가
	 * @param map
	 * @throws Exception
	 */
	void addCategoryMeta(Map<String, Object> map) throws Exception;
	
	/**
	 * MAX 카테고리 번호
	 * @return int
	 * @throws Exception
	 */
	int maxCategoryId() throws Exception;
	
	/**
	 * 카테고리 이미지 저장
	 * @param map
	 * @throws Exception
	 */
	void updateImage(Map<String, Object> map) throws Exception;
}
