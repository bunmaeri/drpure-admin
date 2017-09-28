package admin.contents.service;

import java.util.List;
import java.util.Map;

public interface HealthService {
	/**
	 * Health
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	Map<String, Object> health(Map<String, Object> map) throws Exception;

	/**
	 * Health Contents 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> healthContents(Map<String, Object> map) throws Exception;

	/**
	 * Total Health Contents
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int totalHealthContents(Map<String, Object> map) throws Exception;
	
	/**
	 * Health Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> healthInfo(Map<String, Object> map) throws Exception;
	
	/**
	 * Health Contents Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> healthContentsInfo(Map<String, Object> map) throws Exception;
	
	/**
	 * Health Contents 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int isHealthContents(Map<String, Object> map) throws Exception;
	
	/**
	 * Health 수정
	 * @param map
	 * @throws Exception
	 */
	void updateHealth(Map<String, Object> map) throws Exception;
	
	/**
	 * Health Contents 수정
	 * @param map
	 * @throws Exception
	 */
	void updateHealthContents(Map<String, Object> map) throws Exception;
	
	/**
	 * MAX Health Contents Id
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int maxHealthContentsId(Map<String, Object> map) throws Exception;
	
	/**
	 * Health Contents 추가
	 * @param map
	 * @throws Exception
	 */
	void addHealthContents(Map<String, Object> map) throws Exception;
	
	/**
	 * Health Contents 삭제
	 * @param map
	 * @throws Exception
	 */
	void deleteHealthContents(Map<String, Object> map) throws Exception;
}
