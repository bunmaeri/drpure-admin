package admin.myhome.products.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("myProductsDAO")
public class MyProductsDAO extends AbstractDAO {
	/**
	 * Product autocomplete 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> autocompleteProducts(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("my_products.autocompleteProducts", map);
	}
	
	/**
	 * 제품 목록 Total
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int totalProduct(Map<String, Object> map) throws Exception{
		return (int) selectOne("my_products.totalProduct", map);
	}
	
	/**
	 * 제품 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> listProduct(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("my_products.listProduct", map);
	}
	
	/**
	 * 제품이 참조된 제품 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> referenceProducts(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("my_products.referenceProducts", map);
	}
	
	/**
	 * 제품이 참조된 건강, 임상, 질병 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> referenceProductsInfomation(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("my_products.referenceProductsInfomation", map);
	}
	
	/**
	 * 제품이 참조된 생약제 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> referenceProductsMedicine(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("my_products.referenceProductsMedicine", map);
	}
}
