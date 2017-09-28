package admin.report.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.report.dao.ReportSalesDAO;


@Service("reportSalesService")
public class ReportSalesServiceImpl implements ReportSalesService {
Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="reportSalesDAO")
	private ReportSalesDAO reportSalesDAO;
	
	/**
	 * Total Sales Order Report
	 * @param int
	 * @return
	 * @throws Exception
	 */
	@Override
	public int totalSalesOrder(Map<String, Object> map) throws Exception {
		return reportSalesDAO.totalSalesOrder(map);
	}
	
	/**
	 * Sales Order Report
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> salesOrder(Map<String, Object> map) throws Exception {
		return reportSalesDAO.salesOrder(map);
	}
	
	/**
	 * Total Sales Tax Report
	 * @param int
	 * @return
	 * @throws Exception
	 */
	@Override
	public int totalSalesTax(Map<String, Object> map) throws Exception {
		return reportSalesDAO.totalSalesTax(map);
	}
	
	/**
	 * Sales Tax Report
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> salesTax(Map<String, Object> map) throws Exception {
		return reportSalesDAO.salesTax(map);
	}
	
	/**
	 * Total Sales Shipping Report
	 * @param int
	 * @return
	 * @throws Exception
	 */
	@Override
	public int totalSalesShipping(Map<String, Object> map) throws Exception {
		return reportSalesDAO.totalSalesShipping(map);
	}
	
	/**
	 * Sales Shipping Report
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> salesShipping(Map<String, Object> map) throws Exception {
		return reportSalesDAO.salesShipping(map);
	}
}
