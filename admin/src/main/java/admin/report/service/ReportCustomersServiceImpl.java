package admin.report.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.report.dao.ReportCustomersDAO;


@Service("reportCustomersService")
public class ReportCustomersServiceImpl implements ReportCustomersService {
Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="reportCustomersDAO")
	private ReportCustomersDAO reportCustomersDAO;
	
	/**
	 * Total Customers Order Report
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int totalOrder(Map<String, Object> map) throws Exception {
		return reportCustomersDAO.totalOrder(map);
	}
	
	/**
	 * Customers Order Report
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> order(Map<String, Object> map) throws Exception {
		return reportCustomersDAO.order(map);
	}
	
	/**
	 * Total Customers Reward Points Report
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int totalRewardPoints(Map<String, Object> map) throws Exception {
		return reportCustomersDAO.totalRewardPoints(map);
	}
	
	/**
	 * Customers Reward Points Report
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> rewardPoints(Map<String, Object> map) throws Exception {
		return reportCustomersDAO.rewardPoints(map);
	}
	
	/**
	 * Total Customers Donator Report
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int totalDonator(Map<String, Object> map) throws Exception {
		return reportCustomersDAO.totalDonator(map);
	}
	
	/**
	 * Customers Donator Report
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> donator(Map<String, Object> map) throws Exception {
		return reportCustomersDAO.donator(map);
	}
}
