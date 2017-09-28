package admin.categories.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.categories.dao.CategoryViewDAO;
import admin.common.util.ObjectUtils;

@Service("categoryViewService")
public class CategoryViewServiceImpl implements CategoryViewService {
Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="categoryViewDAO")
	private CategoryViewDAO categoryViewDAO;
	
	/**
	 * 카테고리 Meta 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> categoryMeta(Map<String, Object> map) throws Exception {
		return categoryViewDAO.categoryMeta(map);
	}
	
	/**
	 * 카테고리 Meta 저장
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updateCategoryMeta(Map<String, Object> map) throws Exception{
		categoryViewDAO.updateCategoryMeta(map);
	}
	
	/**
	 * 카테고리 Parent 저장
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updateCategoryParent(Map<String, Object> map) throws Exception{
		categoryViewDAO.updateCategoryParent(map);
	}
	
	/**
	 * 카테고리 path 저장
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updateCategoryPaths(Map<String, Object> map) throws Exception{
		String category_id = ObjectUtils.null2void(map.get("category_id"));
		categoryViewDAO.deleteCategoryPaths(map);
		map.put("category_id", ObjectUtils.null2void(map.get("parent_id")));
		List<Map<String,Object>> list = categoryViewDAO.categoryPaths(map);
		int size = list.size();
		Map<String,Object> pathMap = null;
		int level = -1;
		for(int i=0;i<size;i++) {
			pathMap = list.get(i);
			pathMap.put("category_id", category_id);
			categoryViewDAO.addCategoryPath(pathMap);
			level = Integer.parseInt(ObjectUtils.null2Value(pathMap.get("level"),"0"));
		}
		level++;
		pathMap = new HashMap<String,Object>();
		pathMap.put("category_id", category_id);
		pathMap.put("path_id", category_id);
		pathMap.put("level", level);
		categoryViewDAO.addCategoryPath(pathMap);
	}
	
	/**
	 * 카테고리 NEW
	 */
	@Override
	public void newCategory(Map<String, Object> map) throws Exception{
		categoryViewDAO.newCategory(map);
	}
	
	/**
	 * 카테고리 Meta 추가
	 */
	@Override
	public void addCategoryMeta(Map<String, Object> map) throws Exception{
		categoryViewDAO.addCategoryMeta(map);
	}
	
	/**
	 * MAX 카테고리 번호
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int maxCategoryId() throws Exception {
		return categoryViewDAO.maxCategoryId();
	}
	
	/**
	 * 카테고리 이미지 저장
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updateImage(Map<String, Object> map) throws Exception{
		categoryViewDAO.updateImage(map);
	}
}
