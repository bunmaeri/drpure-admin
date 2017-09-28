package admin.contents.service;

import java.util.List;
import java.util.Map;

public interface SecurityService {
	/**
	 * security
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	Map<String, Object> security(Map<String, Object> map) throws Exception;

	/**
	 * security Contents 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> securityContents(Map<String, Object> map) throws Exception;

	/**
	 * security Contents ALL
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> securityContentsAll(Map<String, Object> map) throws Exception;

	/**
	 * Total security Contents
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int totalSecurityContents(Map<String, Object> map) throws Exception;
	
	/**
	 * security Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> securityInfo(Map<String, Object> map) throws Exception;
	
	/**
	 * security Contents Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> securityContentsInfo(Map<String, Object> map) throws Exception;
	
	/**
	 * security Contents 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int isSecurityContents(Map<String, Object> map) throws Exception;
	
	/**
	 * security 수정
	 * @param map
	 * @throws Exception
	 */
	void updateSecurity(Map<String, Object> map) throws Exception;
	
	/**
	 * security Contents 수정
	 * @param map
	 * @throws Exception
	 */
	void updateSecurityContents(Map<String, Object> map) throws Exception;
	
	/**
	 * MAX security Contents Id
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int maxSecurityContentsId(Map<String, Object> map) throws Exception;
	
	/**
	 * security Contents 추가
	 * @param map
	 * @throws Exception
	 */
	void addSecurityContents(Map<String, Object> map) throws Exception;
	
	/**
	 * security Contents 삭제
	 * @param map
	 * @throws Exception
	 */
	void deleteSecurityContents(Map<String, Object> map) throws Exception;
	
	/**
	 * 질병과 추천생약제 제품 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> securityContentsMedicine(Map<String, Object> map) throws Exception;
	
	/**
	 * 해당 질병과 추천생약제의 전체 제품 삭제
	 * @param map
	 * @throws Exception
	 */
	void deleteSecurityContentsMedicine(Map<String, Object> map) throws Exception;
	
	/**
	 * 질병과 추천생약제의 제품 추가
	 * @param map
	 * @throws Exception
	 */
	void addSecurityContentsMedicine(Map<String, Object> map) throws Exception;
}
