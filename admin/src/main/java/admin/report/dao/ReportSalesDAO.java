package admin.report.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("reportSalesDAO")
public class ReportSalesDAO extends AbstractDAO {
	/**
	 * Total Sales Order Report
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int totalSalesOrder(Map<String, Object> map) throws Exception{
		return (int) selectOne("reportSales.totalSalesOrder", map);
	}
	
	/**
	 * Sales Order Report
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> salesOrder(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("reportSales.salesOrder", map);
	}
	
	/**
	 * Total Sales Tax Report
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int totalSalesTax(Map<String, Object> map) throws Exception{
		return (int) selectOne("reportSales.totalSalesTax", map);
	}
	
	/**
	 * Sales Tax Report
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> salesTax(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("reportSales.salesTax", map);
	}
	
	/**
	 * Total Sales Shipping Report
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int totalSalesShipping(Map<String, Object> map) throws Exception{
		return (int) selectOne("reportSales.totalSalesShipping", map);
	}
	
	/**
	 * Sales Shipping Report
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> salesShipping(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("reportSales.salesShipping", map);
	}
}
