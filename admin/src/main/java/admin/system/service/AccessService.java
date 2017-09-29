package admin.system.service;

import java.util.List;
import java.util.Map;

public interface AccessService {
	/**
	 * IP 목록
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> accessIpList() throws Exception;
	
	/**
	 * IP 추가
	 * @param map
	 * @throws Exception
	 */
	void addAccessIp(Map<String, Object> map) throws Exception;
	
	/**
	 * IP 삭제
	 * @param map
	 * @throws Exception
	 */
	void deleteAccessIp(Map<String, Object> map) throws Exception;
	
	/**
	 * 패스워드
	 * @return
	 * @throws Exception
	 */
	String accessIpPassword() throws Exception;
	
	/**
	 * 패스워드 저장
	 * @param map
	 * @throws Exception
	 */
	void updateAccessPassword(Map<String, Object> map) throws Exception;
}
