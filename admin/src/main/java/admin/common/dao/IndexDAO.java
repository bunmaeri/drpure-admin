package admin.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("indexDAO")
public class IndexDAO extends AbstractDAO {
	/**
	 * Total Orders(총 합계)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String totalOrders(Map<String, Object> map) throws Exception{
		return (String) selectOne("index.totalOrders", map);
	}
	
	/**
	 * Sum Orders(기간별)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String sumOrder(Map<String, Object> map) throws Exception{
		return (String) selectOne("index.sumOrder", map);
	}
	
	/**
	 * Total Sales(총 합계)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String totalSales(Map<String, Object> map) throws Exception{
		return (String) selectOne("index.totalSales", map);
	}
	
	/**
	 * Sum Sale(기간별)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String sumSale(Map<String, Object> map) throws Exception{
		return (String) selectOne("index.sumSale", map);
	}
	
	/**
	 * Total Customers(총 합계)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String totalCustomers(Map<String, Object> map) throws Exception{
		return (String) selectOne("index.totalCustomers", map);
	}
	
	/**
	 * Sum Customer(기간별)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String sumCustomer(Map<String, Object> map) throws Exception{
		return (String) selectOne("index.sumCustomer", map);
	}
	
	/**
	 * People Online
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String peopleOnline(Map<String, Object> map) throws Exception{
		return (String) selectOne("index.peopleOnline", map);
	}
	
	/**
	 * Map 데이터 조회
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> mapInfo() throws Exception{
		return (List<Map<String, Object>>) selectList("index.mapInfo");
	}
	
	/**
	 * 가입경로 데이터 조회
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> joinPathInfo() throws Exception{
		return (List<Map<String, Object>>) selectList("index.joinPathInfo");
	}
	
	/**
	 * 최신 주문 데이터 조회(5개)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> recentOrders(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("index.recentOrders", map);
	}
	
	/**
	 * 한달 전 주문(전체)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> dailyOrderTotal(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("index.dailyOrderTotal", map);
	}
	
	/**
	 * 한달 전 주문(Store)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> dailyOrderStoreTotal(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("index.dailyOrderStoreTotal", map);
	}
	
	/**
	 * 나라별 주문 수량
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> dailyOrderCountryTotal(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("index.dailyOrderCountryTotal", map);
	}
}
