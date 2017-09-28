package admin.products.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.products.dao.ProductsDAO;


@Service("productsService")
public class ProductsServiceImpl implements ProductsService {
Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="productsDAO")
	private ProductsDAO productsDAO;
	
	/**
	 * Product autocomplete 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> autocompleteProducts(Map<String, Object> map) throws Exception {
		return productsDAO.autocompleteProducts(map);
	}
	
	/**
	 * 제품 목록 Total
	 * @param int
	 * @return
	 * @throws Exception
	 */
	@Override
	public int totalProduct(Map<String, Object> map) throws Exception {
		return productsDAO.totalProduct(map);
	}
	
	/**
	 * 제품 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> listProduct(Map<String, Object> map) throws Exception {
		return productsDAO.listProduct(map);
	}

	/**
	 * 제품이 참조된 제품 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> referenceProducts(Map<String, Object> map) throws Exception {
		return productsDAO.referenceProducts(map);
	}
	
	/**
	 * 제품이 참조된 건강, 임상, 질병 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> referenceProductsInfomation(Map<String, Object> map) throws Exception {
		return productsDAO.referenceProductsInfomation(map);
	}
	
	/**
	 * 제품이 참조된 생약제 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> referenceProductsMedicine(Map<String, Object> map) throws Exception {
		return productsDAO.referenceProductsMedicine(map);
	}
	
	/**
	 * 제조사 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> productsManufacturer(Map<String, Object> map) throws Exception {
		return productsDAO.productsManufacturer(map);
	}
	
	/**
	 * 제조사 수정
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateManufacturer(Map<String, Object> map) throws Exception {
		productsDAO.updateManufacturer(map);
	}
	
	/**
	 * 제조사 추가
	 */
	@Override
	public void addManufacturer(Map<String, Object> map) throws Exception {
		productsDAO.addManufacturer(map);
	}
	
	/**
	 * 제조사 삭제
	 */
	@Override
	public void deleteManufacturer(Map<String, Object> map) throws Exception {
		productsDAO.deleteManufacturer(map);
	}
}
