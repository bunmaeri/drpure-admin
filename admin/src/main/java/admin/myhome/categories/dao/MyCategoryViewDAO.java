package admin.myhome.categories.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("myCategoryViewDAO")
public class MyCategoryViewDAO extends AbstractDAO {
	/**
	 * 카테고리 Meta 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> categoryMeta(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("my_categoryView.categoryMeta", map);
	}
	
	/**
	 * 카테고리 Meta 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateCategoryMeta(Map<String, Object> map) throws Exception{
		update("my_categoryView.updateCategoryMeta", map);
	}
	
	/**
	 * 카테고리 Parent 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateCategoryParent(Map<String, Object> map) throws Exception{
		update("my_categoryView.updateCategoryParent", map);
	}
	
	/**
	 * 카테고리 path 삭제
	 * @param map
	 * @throws Exception
	 */
	public void deleteCategoryPaths(Map<String, Object> map) throws Exception{
		delete("my_categoryView.deleteCategoryPaths", map);
	}
	
	/**
	 * 카테고리 path 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> categoryPaths(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("my_categoryView.categoryPaths", map);
	}
	
	/**
	 * 카테고리 path 추가
	 * @param map
	 * @throws Exception
	 */
	public void addCategoryPath(Map<String, Object> map) throws Exception{
		insert("my_categoryView.addCategoryPath", map);
	}
	
	/**
	 * 카테고리 NEW
	 * @param map
	 * @throws Exception
	 */
	public void newCategory(Map<String, Object> map) throws Exception{
		insert("my_categoryView.newCategory", map);
	}
	
	/**
	 * 카테고리 Meta 추가
	 * @param map
	 * @throws Exception
	 */
	public void addCategoryMeta(Map<String, Object> map) throws Exception{
		insert("my_categoryView.addCategoryMeta", map);
	}
	
	/**
	 * MAX 카테고리 번호
	 * @return int
	 * @throws Exception
	 */
	public int maxCategoryId() throws Exception{
		return (int) selectOne("my_categoryView.maxCategoryId");
	}
	
	/**
	 * 카테고리 이미지 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateImage(Map<String, Object> map) throws Exception{
		update("my_categoryView.updateImage", map);
	}
}
