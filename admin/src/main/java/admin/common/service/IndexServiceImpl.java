package admin.common.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.common.dao.IndexDAO;

@Service("indexService")
public class IndexServiceImpl implements IndexService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="indexDAO")
	private IndexDAO indexDAO;
	
	/**
	 * Total Orders(총 합계)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public String totalOrders(Map<String, Object> map) throws Exception {
		return indexDAO.totalOrders(map);
	}
	
	/**
	 * Sum Orders(기간별)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public String sumOrder(Map<String, Object> map) throws Exception {
		return indexDAO.sumOrder(map);
	}
	
	/**
	 * Total Sales(총 합계)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public String totalSales(Map<String, Object> map) throws Exception {
		return indexDAO.totalSales(map);
	}
	
	/**
	 * Sum Sale(기간별)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public String sumSale(Map<String, Object> map) throws Exception {
		return indexDAO.sumOrder(map);
	}
	
	/**
	 * Total Customers(총 합계)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public String totalCustomers(Map<String, Object> map) throws Exception {
		return indexDAO.totalCustomers(map);
	}
	
	/**
	 * Sum Customer(기간별)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public String sumCustomer(Map<String, Object> map) throws Exception {
		return indexDAO.sumCustomer(map);
	}
	
	/**
	 * People Online
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public String peopleOnline(Map<String, Object> map) throws Exception {
		return indexDAO.peopleOnline(map);
	}
	
	/**
	 * Map 데이터 조회
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> mapInfo() throws Exception {
		return indexDAO.mapInfo();
	}
	
	/**
	 * 가입경로 데이터 조회
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> joinPathInfo() throws Exception {
		return indexDAO.joinPathInfo();
	}
	
	/**
	 * 최신 주문 데이터 조회(5개)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> recentOrders(Map<String, Object> map) throws Exception {
		return indexDAO.recentOrders(map);
	}
	
	/**
	 * 한달 전 주문(전체)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> dailyOrderTotal(Map<String, Object> map) throws Exception {
		return indexDAO.dailyOrderTotal(map);
	}
	
	/**
	 * 한달 전 주문(Store)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> dailyOrderStoreTotal(Map<String, Object> map) throws Exception {
		return indexDAO.dailyOrderStoreTotal(map);
	}
	
	/**
	 * 나라별 주문 수량
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> dailyOrderCountryTotal(Map<String, Object> map) throws Exception {
		return indexDAO.dailyOrderCountryTotal(map);
	}
}
