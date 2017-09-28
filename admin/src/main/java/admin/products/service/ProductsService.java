package admin.products.service;

import java.util.List;
import java.util.Map;

public interface ProductsService {
	/**
	 * Product autocomplete 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> autocompleteProducts(Map<String, Object> map) throws Exception;
	
	/**
	 * 제품 목록 Total
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int totalProduct(Map<String, Object> map) throws Exception;
	
	/**
	 * 제품 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> listProduct(Map<String, Object> map) throws Exception;
	
	/**
	 * 제품이 참조된 제품 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> referenceProducts(Map<String, Object> map) throws Exception;
	
	/**
	 * 제품이 참조된 건강, 임상, 질병 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> referenceProductsInfomation(Map<String, Object> map) throws Exception;
	
	/**
	 * 제품이 참조된 생약제 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> referenceProductsMedicine(Map<String, Object> map) throws Exception;
	
	/**
	 * 제조사 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> productsManufacturer(Map<String, Object> map) throws Exception;
	
	/**
	 * 제조사 수정
	 * @param map
	 * @throws Exception
	 */
	void updateManufacturer(Map<String, Object> map) throws Exception;
	
	/**
	 * 제조사 추가
	 * @param map
	 * @throws Exception
	 */
	void addManufacturer(Map<String, Object> map) throws Exception;
	
	/**
	 * 제조사 삭제
	 * @param map
	 * @throws Exception
	 */
	void deleteManufacturer(Map<String, Object> map) throws Exception;
}
