package admin.myhome.report.service;

import java.util.List;
import java.util.Map;

public interface MyReportSalesService {
	/**
	 * Total Sales Order Report
	 * @param int
	 * @return
	 * @throws Exception
	 */
	int totalSalesOrder(Map<String, Object> map) throws Exception;
	
	/**
	 * Sales Order Report
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> salesOrder(Map<String, Object> map) throws Exception;
	
	/**
	 * Total Sales Tax Report
	 * @param int
	 * @return
	 * @throws Exception
	 */
	int totalSalesTax(Map<String, Object> map) throws Exception;
	
	/**
	 * Sales Tax Report
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> salesTax(Map<String, Object> map) throws Exception;
	
	/**
	 * Total Sales Shipping Report
	 * @param int
	 * @return
	 * @throws Exception
	 */
	int totalSalesShipping(Map<String, Object> map) throws Exception;
	
	/**
	 * Sales Shipping Report
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> salesShipping(Map<String, Object> map) throws Exception;
}
