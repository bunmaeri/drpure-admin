package admin.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("commonDAO")
public class CommonDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception{
		return (Map<String, Object>)selectOne("common.selectFileInfo", map);
	}

	/**
	 * order status 코드 목록 조회
	 * @param String
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> orderStatus(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("common.orderStatus", map);
	}
	
	public String orderStatusName(String orderStatusId) throws Exception{
		return (String) selectOne("common.orderStatusName", orderStatusId);
	}
	
	public int userIp(String ip) throws Exception{
		return (int) selectOne("common.userIp", ip);
	}
	
	/**
	 * manufacturer 코드 목록(제조사)
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> manufacturerAll() throws Exception{
		return (List<Map<String, Object>>) selectList("common.manufacturerAll");
	}
	
	/**
	 * 국가별 zone 목록
	 * @param String
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> zonesPerCountry(String country_id) throws Exception{
		return (List<Map<String, Object>>) selectList("common.zonesPerCountry", country_id);
	}
	
	/**
	 * 고객 그룹을 목록조회한다.
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> customerGroups(String language_id) throws Exception{
		return (List<Map<String, Object>>) selectList("common.customerGroups", language_id);
	}
	
	/**
	 * 고객 가입 경로를 목록조회한다.
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> customerJoinPaths(String language_id) throws Exception{
		return (List<Map<String, Object>>) selectList("common.customerJoinPaths", language_id);
	}
	
	/**
	 * location 코드 목록 (원산지)
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> locationAll() throws Exception{
		return (List<Map<String, Object>>) selectList("common.locationAll");
	}
	
	/**
	 * weight_class 코드 목록 (무게 단위)
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> weightClassAll() throws Exception{
		return (List<Map<String, Object>>) selectList("common.weightClassAll");
	}
}
