package admin.report.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("reportCustomersDAO")
public class ReportCustomersDAO extends AbstractDAO {
	/**
	 * Total Customers Order Report
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int totalOrder(Map<String, Object> map) throws Exception{
		return (int) selectOne("reportCustomers.totalOrder", map);
	}
	
	/**
	 * Customers Order Report
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> order(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("reportCustomers.order", map);
	}
	
	/**
	 * Total Customers Reward Points Report
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int totalRewardPoints(Map<String, Object> map) throws Exception{
		return (int) selectOne("reportCustomers.totalRewardPoints", map);
	}
	
	/**
	 * Customers Reward Points Report
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> rewardPoints(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("reportCustomers.rewardPoints", map);
	}
	
	/**
	 * Total Customers Donator Report
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int totalDonator(Map<String, Object> map) throws Exception{
		return (int) selectOne("reportCustomers.totalDonator", map);
	}
	
	/**
	 * Customers Donator Report
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> donator(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("reportCustomers.donator", map);
	}
}
