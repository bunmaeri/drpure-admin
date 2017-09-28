package admin.myhome.report.service;

import java.util.List;
import java.util.Map;

public interface MyReportCustomersService {
	/**
	 * Total Customers Order Report
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int totalOrder(Map<String, Object> map) throws Exception;
	
	/**
	 * Customers Order Report
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> order(Map<String, Object> map) throws Exception;
	
	/**
	 * Total Customers Reward Points Report
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int totalRewardPoints(Map<String, Object> map) throws Exception;
	
	/**
	 * Customers Reward Points Report
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> rewardPoints(Map<String, Object> map) throws Exception;
	
	/**
	 * Total Customers Donator Report
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int totalDonator(Map<String, Object> map) throws Exception;
	
	/**
	 * Customers Donator Report
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> donator(Map<String, Object> map) throws Exception;
}
