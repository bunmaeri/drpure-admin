package admin.products.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("productsDAO")
public class ProductsDAO extends AbstractDAO {
	/**
	 * Product autocomplete 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> autocompleteProducts(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("products.autocompleteProducts", map);
	}
	
	/**
	 * 제품 목록 Total
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int totalProduct(Map<String, Object> map) throws Exception{
		return (int) selectOne("products.totalProduct", map);
	}
	
	/**
	 * 제품 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> listProduct(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("products.listProduct", map);
	}
	
	/**
	 * 제품이 참조된 제품 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> referenceProducts(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("products.referenceProducts", map);
	}
	
	/**
	 * 제품이 참조된 건강, 임상, 질병 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> referenceProductsInfomation(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("products.referenceProductsInfomation", map);
	}
	
	/**
	 * 제품이 참조된 생약제 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> referenceProductsMedicine(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("products.referenceProductsMedicine", map);
	}
	
	/**
	 *제조사 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> productsManufacturer(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("products.productsManufacturer", map);
	}
	
	/**
	 * 제조사 수정
	 * @param map
	 * @throws Exception
	 */
	public void updateManufacturer(Map<String, Object> map) throws Exception{
		update("products.updateManufacturer", map);
	}
	
	/**
	 * 제조사 추가
	 * @param map
	 * @throws Exception
	 */
	public void addManufacturer(Map<String, Object> map) throws Exception{
		insert("products.addManufacturer", map);
	}
	
	/**
	 * 제조사 삭제
	 * @param map
	 * @throws Exception
	 */
	public void deleteManufacturer(Map<String, Object> map) throws Exception{
		delete("products.deleteManufacturer", map);
	}
}
