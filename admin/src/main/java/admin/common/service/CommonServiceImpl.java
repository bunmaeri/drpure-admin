package admin.common.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.common.dao.CommonDAO;

@Service("commonService")
public class CommonServiceImpl implements CommonService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="commonDAO")
	private CommonDAO commonDAO;

	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return commonDAO.selectFileInfo(map);
	}
	
	/**
	 * Order Status 코드 목록
	 * @param map
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> orderStatus(Map<String, Object> map) throws Exception {
		return commonDAO.orderStatus(map);
	}
	
	/**
	 * manufacturer 코드 목록(제조사)
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> manufacturerAll() throws Exception {
		return commonDAO.manufacturerAll();
	}
	
	/**
	 * 국가별 zone 목록
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> zonesPerCountry(String country_id) throws Exception {
		return commonDAO.zonesPerCountry(country_id);
	}
	
	/**
	 * 고객 그룹을 목록조회한다.
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> customerGroups(String language_id) throws Exception {
		return commonDAO.customerGroups(language_id);
	}
	
	/**
	 * 고객 가입 경로를 목록조회한다.
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> customerJoinPaths(String language_id) throws Exception {
		return commonDAO.customerJoinPaths(language_id);
	}
	
	/**
	 * location 코드 목록 (원산지)
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> locationAll() throws Exception {
		return commonDAO.locationAll();
	}
	
	/**
	 * weight_class 코드 목록 (무게 단위)
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> weightClassAll() throws Exception {
		return commonDAO.weightClassAll();
	}
}
