package admin.myhome.contents.service;

import java.util.List;
import java.util.Map;

public interface MySecurityCategoriesService {
	/**
	 * Total Security 카테고리
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int totalSecurityCategories(Map<String, Object> map) throws Exception;
	
	/**
	 * Security 카테고리
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> securityCategories(Map<String, Object> map) throws Exception;

	/**
	 * Security 카테고리 Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> securityCategoryInfo(Map<String, Object> map) throws Exception;
	
	/**
	 * Security 카테고리 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int isSecurityCategory(Map<String, Object> map) throws Exception;
	
	/**
	 * Security 카테고리 수정
	 * @param map
	 * @throws Exception
	 */
	void updateSecurityCategory(Map<String, Object> map) throws Exception;
	
	/**
	 * Security 카테고리 추가
	 * @param map
	 * @throws Exception
	 */
	void addSecurityCategory(Map<String, Object> map) throws Exception;
	
	/**
	 * Security 카테고리 삭제
	 * @param map
	 * @throws Exception
	 */
	void deleteSecurityCategory(Map<String, Object> map) throws Exception;
	
	/**
	 * 이미지 저장
	 * @param map
	 * @throws Exception
	 */
	void updateImage(Map<String, Object> map) throws Exception;
}
