package admin.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("taxRatesDAO")
public class TaxRatesDAO extends AbstractDAO {

	/**
	 * Zone 목록
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> geoZoneList() throws Exception{
		return (List<Map<String, Object>>) selectList("system_taxrates.geoZoneList");
	}
	
	/**
	 * Tax 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> taxRatesList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("system_taxrates.taxRatesList", map);
	}
	
	/**
	 * Tax 수정
	 * @param map
	 * @throws Exception
	 */
	public void updateTaxRates(Map<String, Object> map) throws Exception{
		update("system_taxrates.updateTaxRates", map);
	}
	
	/**
	 * Tax 추가
	 * @param map
	 * @throws Exception
	 */
	public void addTaxRates(Map<String, Object> map) throws Exception{
		insert("system_taxrates.addTaxRates", map);
	}
	
	/**
	 * Tax 삭제
	 * @param map
	 * @throws Exception
	 */
	public void deleteTaxRates(Map<String, Object> map) throws Exception{
		delete("system_taxrates.deleteTaxRates", map);
	}
}
