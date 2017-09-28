package admin.system.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.system.dao.TaxRatesDAO;

@Service("taxRatesService")
public class TaxRatesServiceImpl implements TaxRatesService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="taxRatesDAO")
	private TaxRatesDAO taxRatesDAO;
	
	/**
	 * Zone 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> geoZoneList() throws Exception {
		return taxRatesDAO.geoZoneList();
	}
	
	/**
	 * Tax 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> taxRatesList(Map<String, Object> map) throws Exception {
		return taxRatesDAO.taxRatesList(map);
	}
	
	/**
	 * Tax 수정
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateTaxRates(Map<String, Object> map) throws Exception {
		taxRatesDAO.updateTaxRates(map);
	}
	
	/**
	 * Tax 추가
	 */
	@Override
	public void addTaxRates(Map<String, Object> map) throws Exception {
		taxRatesDAO.addTaxRates(map);
	}
	
	/**
	 * Tax 삭제
	 */
	@Override
	public void deleteTaxRates(Map<String, Object> map) throws Exception {
		taxRatesDAO.deleteTaxRates(map);
	}
}
