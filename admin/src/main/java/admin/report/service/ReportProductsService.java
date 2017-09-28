package admin.report.service;

import java.util.List;
import java.util.Map;

public interface ReportProductsService {
	/**
	 * Report Product Total Views 전체 제품의 조회건수 합계 (% 구하기 위해서)
	 * @return int
	 * @throws Exception
	 */
	int totalProductViews() throws Exception;
	
	/**
	 * Report Product Total Viewed 총 건수 조회
	 * @return int
	 * @throws Exception
	 */
	int totalProductsViewed() throws Exception;
	
	/**
	 * Report Product Viewed 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> productsViewed(Map<String, Object> map) throws Exception;
	
	/**
	 * Total Products Purchased Report
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int totalProductsPurchased(Map<String, Object> map) throws Exception;
	
	/**
	 * Sales Shipping Report
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> productsPurchased(Map<String, Object> map) throws Exception;
}
