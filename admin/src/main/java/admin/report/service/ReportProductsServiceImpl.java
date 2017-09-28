package admin.report.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.report.dao.ReportProductsDAO;


@Service("reportProductsService")
public class ReportProductsServiceImpl implements ReportProductsService {
Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="reportProductsDAO")
	private ReportProductsDAO reportProductsDAO;
	
	/**
	 * Report Product Total Views 전체 제품의 조회건수 합계 (% 구하기 위해서)
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int totalProductViews() throws Exception {
		return reportProductsDAO.totalProductViews();
	}
	
	/**
	 * Report Product Total Viewed 총 건수 조회
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int totalProductsViewed() throws Exception {
		return reportProductsDAO.totalProductsViewed();
	}
	
	/**
	 * Report Product Viewed 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> productsViewed(Map<String, Object> map) throws Exception {
		return reportProductsDAO.productsViewed(map);
	}
	
	/**
	 * Total Products Purchased Report
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int totalProductsPurchased(Map<String, Object> map) throws Exception {
		return reportProductsDAO.totalProductsPurchased(map);
	}
	
	/**
	 * Products Purchased Report
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> productsPurchased(Map<String, Object> map) throws Exception {
		return reportProductsDAO.productsPurchased(map);
	}
}
