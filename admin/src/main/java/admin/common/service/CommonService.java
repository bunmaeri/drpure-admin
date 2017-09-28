package admin.common.service;

import java.util.List;
import java.util.Map;

public interface CommonService {

	Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;

	/**
	 * Order Status 코드 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> orderStatus(Map<String, Object> map) throws Exception;
	
	/**
	 * manufacturer 코드 목록(제조사)
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> manufacturerAll() throws Exception;
	
	/**
	 * 국가별 zone 목록
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> zonesPerCountry(String country_id) throws Exception;
	
	/**
	 * 고객 그룹을 목록조회한다.
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> customerGroups(String language_id) throws Exception;
	
	/**
	 * 고객 가입 경로를 목록조회한다.
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> customerJoinPaths(String language_id) throws Exception;
	
	/**
	 * location 코드 목록 (원산지)
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> locationAll() throws Exception;
	
	/**
	 * weight_class 코드 목록 (무게 단위)
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> weightClassAll() throws Exception;
}