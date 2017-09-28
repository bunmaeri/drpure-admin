package admin.myhome.report.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("myReportProductsDAO")
public class MyReportProductsDAO extends AbstractDAO {
	/**
	 * Report Product Total Views 전체 제품의 조회건수 합계 (% 구하기 위해서)
	 * @return
	 * @throws Exception
	 */
	public int totalProductViews() throws Exception{
		return (int) selectOne("my_reportProducts.totalProductViews");
	}
	
	/**
	 * Report Product Total Viewed 총 건수 조회
	 * @return
	 * @throws Exception
	 */
	public int totalProductsViewed() throws Exception{
		return (int) selectOne("my_reportProducts.totalProductsViewed");
	}
	
	/**
	 * Report Product Viewed 목록 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> productsViewed(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("my_reportProducts.productsViewed", map);
	}
	
	/**
	 * Total Products Purchased Report
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int totalProductsPurchased(Map<String, Object> map) throws Exception{
		return (int) selectOne("my_reportProducts.totalProductsPurchased", map);
	}
	
	/**
	 * Products Purchased Report
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> productsPurchased(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("my_reportProducts.productsPurchased", map);
	}
}
