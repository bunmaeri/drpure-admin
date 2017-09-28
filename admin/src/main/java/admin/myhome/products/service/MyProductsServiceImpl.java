package admin.myhome.products.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.myhome.products.dao.MyProductsDAO;


@Service("myProductsService")
public class MyProductsServiceImpl implements MyProductsService {
Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="myProductsDAO")
	private MyProductsDAO myProductsDAO;
	
	/**
	 * Product autocomplete 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> autocompleteProducts(Map<String, Object> map) throws Exception {
		return myProductsDAO.autocompleteProducts(map);
	}
	
	/**
	 * 제품 목록 Total
	 * @param int
	 * @return
	 * @throws Exception
	 */
	@Override
	public int totalProduct(Map<String, Object> map) throws Exception {
		return myProductsDAO.totalProduct(map);
	}
	
	/**
	 * 제품 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> listProduct(Map<String, Object> map) throws Exception {
		return myProductsDAO.listProduct(map);
	}

	/**
	 * 제품이 참조된 제품 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> referenceProducts(Map<String, Object> map) throws Exception {
		return myProductsDAO.referenceProducts(map);
	}
	
	/**
	 * 제품이 참조된 건강, 임상, 질병 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> referenceProductsInfomation(Map<String, Object> map) throws Exception {
		return myProductsDAO.referenceProductsInfomation(map);
	}
	
	/**
	 * 제품이 참조된 생약제 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> referenceProductsMedicine(Map<String, Object> map) throws Exception {
		return myProductsDAO.referenceProductsMedicine(map);
	}
}
