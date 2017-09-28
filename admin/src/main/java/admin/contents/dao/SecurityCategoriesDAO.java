package admin.contents.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("securityCategoriesDAO")
public class SecurityCategoriesDAO extends AbstractDAO {
	/**
	 * Total Security 카테고리
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int totalSecurityCategories(Map<String, Object> map) throws Exception{
		return (int) selectOne("securityCategories.totalSecurityCategories", map);
	}
	
	/**
	 * Security 카테고리
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> securityCategories(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("securityCategories.securityCategories", map);
	}

	/**
	 * Security 카테고리 Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> securityCategoryInfo(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("securityCategories.securityCategoryInfo", map);
	}
	
	/**
	 * Security 카테고리 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int isSecurityCategory(Map<String, Object> map) throws Exception{
		return (int) selectOne("securityCategories.isSecurityCategory", map);
	}
	
	/**
	 * Security 카테고리 수정
	 * @param map
	 * @throws Exception
	 */
	public void updateSecurityCategory(Map<String, Object> map) throws Exception{
		update("securityCategories.updateSecurityCategory", map);
	}
	
	/**
	 * Security 카테고리 추가
	 * @param map
	 * @throws Exception
	 */
	public void addSecurityCategory(Map<String, Object> map) throws Exception{
		insert("securityCategories.addSecurityCategory", map);
	}
	
	/**
	 * Security 카테고리 삭제
	 * @param map
	 * @throws Exception
	 */
	public void deleteSecurityCategory(Map<String, Object> map) throws Exception{
		delete("securityCategories.deleteSecurityCategory", map);
	}
	
	/**
	 * 이미지 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateImage(Map<String, Object> map) throws Exception{
		update("securityCategories.updateImage", map);
	}
}
