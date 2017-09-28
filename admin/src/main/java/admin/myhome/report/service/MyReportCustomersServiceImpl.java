package admin.myhome.report.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.myhome.report.dao.MyReportCustomersDAO;


@Service("myReportCustomersService")
public class MyReportCustomersServiceImpl implements MyReportCustomersService {
Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="myReportCustomersDAO")
	private MyReportCustomersDAO myReportCustomersDAO;
	
	/**
	 * Total Customers Order Report
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int totalOrder(Map<String, Object> map) throws Exception {
		return myReportCustomersDAO.totalOrder(map);
	}
	
	/**
	 * Customers Order Report
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> order(Map<String, Object> map) throws Exception {
		return myReportCustomersDAO.order(map);
	}
	
	/**
	 * Total Customers Reward Points Report
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int totalRewardPoints(Map<String, Object> map) throws Exception {
		return myReportCustomersDAO.totalRewardPoints(map);
	}
	
	/**
	 * Customers Reward Points Report
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> rewardPoints(Map<String, Object> map) throws Exception {
		return myReportCustomersDAO.rewardPoints(map);
	}
	
	/**
	 * Total Customers Donator Report
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int totalDonator(Map<String, Object> map) throws Exception {
		return myReportCustomersDAO.totalDonator(map);
	}
	
	/**
	 * Customers Donator Report
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> donator(Map<String, Object> map) throws Exception {
		return myReportCustomersDAO.donator(map);
	}
}
