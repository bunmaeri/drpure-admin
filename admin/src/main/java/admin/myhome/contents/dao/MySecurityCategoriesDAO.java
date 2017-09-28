package admin.myhome.contents.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("mySecurityCategoriesDAO")
public class MySecurityCategoriesDAO extends AbstractDAO {
	/**
	 * Total Security 카테고리
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int totalSecurityCategories(Map<String, Object> map) throws Exception{
		return (int) selectOne("my_securityCategories.totalSecurityCategories", map);
	}
	
	/**
	 * Security 카테고리
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> securityCategories(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("my_securityCategories.securityCategories", map);
	}

	/**
	 * Security 카테고리 Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> securityCategoryInfo(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("my_securityCategories.securityCategoryInfo", map);
	}
	
	/**
	 * Security 카테고리 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int isSecurityCategory(Map<String, Object> map) throws Exception{
		return (int) selectOne("my_securityCategories.isSecurityCategory", map);
	}
	
	/**
	 * Security 카테고리 수정
	 * @param map
	 * @throws Exception
	 */
	public void updateSecurityCategory(Map<String, Object> map) throws Exception{
		update("my_securityCategories.updateSecurityCategory", map);
	}
	
	/**
	 * Security 카테고리 추가
	 * @param map
	 * @throws Exception
	 */
	public void addSecurityCategory(Map<String, Object> map) throws Exception{
		insert("my_securityCategories.addSecurityCategory", map);
	}
	
	/**
	 * Security 카테고리 삭제
	 * @param map
	 * @throws Exception
	 */
	public void deleteSecurityCategory(Map<String, Object> map) throws Exception{
		delete("my_securityCategories.deleteSecurityCategory", map);
	}
	
	/**
	 * 이미지 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateImage(Map<String, Object> map) throws Exception{
		update("my_securityCategories.updateImage", map);
	}
}
