package admin.myhome.contents.service;

import java.util.List;
import java.util.Map;

public interface MyEtcService {
	/**
	 * Total Contents
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int totalContents(Map<String, Object> map) throws Exception;
	
	/**
	 * Contents
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> contents(Map<String, Object> map) throws Exception;

	/**
	 * Contents Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> contentsInfo(Map<String, Object> map) throws Exception;
	
	/**
	 * Contents 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int isContents(Map<String, Object> map) throws Exception;
	
	/**
	 * Contents 수정
	 * @param map
	 * @throws Exception
	 */
	void updateContents(Map<String, Object> map) throws Exception;
	
	/**
	 * MAX contents_id
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int maxContentsId(Map<String, Object> map) throws Exception;
	
	/**
	 * Contents 추가
	 * @param map
	 * @throws Exception
	 */
	void addContents(Map<String, Object> map) throws Exception;
	
	/**
	 * Contents 삭제
	 * @param map
	 * @throws Exception
	 */
	void deleteContents(Map<String, Object> map) throws Exception;
}
