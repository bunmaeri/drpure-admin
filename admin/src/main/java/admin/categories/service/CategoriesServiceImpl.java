package admin.categories.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.categories.dao.CategoriesDAO;


@Service("categoriesService")
public class CategoriesServiceImpl implements CategoriesService {
Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="categoriesDAO")
	private CategoriesDAO categoriesDAO;
	
	/**
	 * Categories autocomplete 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> autocompleteCategories(Map<String, Object> map) throws Exception {
		return categoriesDAO.autocompleteCategories(map);
	}
	
	/**
	 * 카테고리 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> categories(Map<String, Object> map) throws Exception {
		return categoriesDAO.categories(map);
	}

	/**
	 * 보안 카테고리
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> securityCategories(String disease) throws Exception {
		return categoriesDAO.securityCategories(disease);
	}
}
