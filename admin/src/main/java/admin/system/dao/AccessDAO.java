package admin.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("accessDAO")
public class AccessDAO extends AbstractDAO {

	/**
	 * IP 목록
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> accessIpList() throws Exception{
		return (List<Map<String, Object>>) selectList("system_access.accessIpList");
	}
	
	/**
	 * IP 추가
	 * @param map
	 * @throws Exception
	 */
	public void addAccessIp(Map<String, Object> map) throws Exception{
		insert("system_access.addAccessIp", map);
	}
	
	/**
	 * IP 삭제
	 * @param map
	 * @throws Exception
	 */
	public void deleteAccessIp(Map<String, Object> map) throws Exception{
		delete("system_access.deleteAccessIp", map);
	}
	
	/**
	 * 패스워드
	 * @param map
	 * @throws Exception
	 */
	public String accessIpPassword() throws Exception{
		return (String) selectOne("system_access.accessIpPassword");
	}
	
	/**
	 * 패스워드 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateAccessPassword(Map<String, Object> map) throws Exception{
		update("system_access.updateAccessPassword", map);
	}
}
