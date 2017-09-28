package admin.categories.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("categoryViewDAO")
public class CategoryViewDAO extends AbstractDAO {
	/**
	 * 카테고리 Meta 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> categoryMeta(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("categoryView.categoryMeta", map);
	}
	
	/**
	 * 카테고리 Meta 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateCategoryMeta(Map<String, Object> map) throws Exception{
		update("categoryView.updateCategoryMeta", map);
	}
	
	/**
	 * 카테고리 Parent 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateCategoryParent(Map<String, Object> map) throws Exception{
		update("categoryView.updateCategoryParent", map);
	}
	
	/**
	 * 카테고리 path 삭제
	 * @param map
	 * @throws Exception
	 */
	public void deleteCategoryPaths(Map<String, Object> map) throws Exception{
		delete("categoryView.deleteCategoryPaths", map);
	}
	
	/**
	 * 카테고리 path 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> categoryPaths(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("categoryView.categoryPaths", map);
	}
	
	/**
	 * 카테고리 path 추가
	 * @param map
	 * @throws Exception
	 */
	public void addCategoryPath(Map<String, Object> map) throws Exception{
		insert("categoryView.addCategoryPath", map);
	}
	
	/**
	 * 카테고리 NEW
	 * @param map
	 * @throws Exception
	 */
	public void newCategory(Map<String, Object> map) throws Exception{
		insert("categoryView.newCategory", map);
	}
	
	/**
	 * 카테고리 Meta 추가
	 * @param map
	 * @throws Exception
	 */
	public void addCategoryMeta(Map<String, Object> map) throws Exception{
		insert("categoryView.addCategoryMeta", map);
	}
	
	/**
	 * MAX 카테고리 번호
	 * @return int
	 * @throws Exception
	 */
	public int maxCategoryId() throws Exception{
		return (int) selectOne("categoryView.maxCategoryId");
	}
	
	/**
	 * 카테고리 이미지 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateImage(Map<String, Object> map) throws Exception{
		update("categoryView.updateImage", map);
	}
}
