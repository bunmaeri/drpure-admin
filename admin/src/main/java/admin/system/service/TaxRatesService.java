package admin.system.service;

import java.util.List;
import java.util.Map;

public interface TaxRatesService {
	/**
	 * Zone 목록
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> geoZoneList() throws Exception;
	
	/**
	 * Tax 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> taxRatesList(Map<String, Object> map) throws Exception;
	
	/**
	 * Tax 수정
	 * @param map
	 * @throws Exception
	 */
	void updateTaxRates(Map<String, Object> map) throws Exception;
	
	/**
	 * Tax 추가
	 * @param map
	 * @throws Exception
	 */
	void addTaxRates(Map<String, Object> map) throws Exception;
	
	/**
	 * Tax 삭제
	 * @param map
	 * @throws Exception
	 */
	void deleteTaxRates(Map<String, Object> map) throws Exception;
}
