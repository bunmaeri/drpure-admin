package admin.common.service;

import java.util.List;
import java.util.Map;

public interface IndexService {
	/**
	 * Total Orders(총 합계)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	String totalOrders(Map<String, Object> map) throws Exception;
	
	/**
	 * Sum Orders(기간별)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	String sumOrder(Map<String, Object> map) throws Exception;
	
	/**
	 * Total Sales
	 * @param map
	 * @return
	 * @throws Exception
	 */
	String totalSales(Map<String, Object> map) throws Exception;
	
	/**
	 * Sum Sale(기간별)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	String sumSale(Map<String, Object> map) throws Exception;
	
	/**
	 * Total Customers(총 합계)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	String totalCustomers(Map<String, Object> map) throws Exception;
	
	/**
	 * Sum Customer(기간별)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	String sumCustomer(Map<String, Object> map) throws Exception;
	
	/**
	 * People Online
	 * @param map
	 * @return
	 * @throws Exception
	 */
	String peopleOnline(Map<String, Object> map) throws Exception;
	
	/**
	 * Map 데이터 조회
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> mapInfo() throws Exception;
	
	/**
	 * 가입경로 데이터 조회
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> joinPathInfo() throws Exception;
	
	/**
	 * 최신 주문 데이터 조회(5개)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> recentOrders(Map<String, Object> map) throws Exception;
	
	/**
	 * 한달 전 주문(전체)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> dailyOrderTotal(Map<String, Object> map) throws Exception;
	
	/**
	 * 한달 전 주문(Store)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> dailyOrderStoreTotal(Map<String, Object> map) throws Exception;
	
	/**
	 * 나라별 주문 수량
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> dailyOrderCountryTotal(Map<String, Object> map) throws Exception;
}
