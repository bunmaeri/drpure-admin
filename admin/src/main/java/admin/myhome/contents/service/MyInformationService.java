package admin.myhome.contents.service;

import java.util.List;
import java.util.Map;

public interface MyInformationService {
	/**
	 * Total Information
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int totalInformation(Map<String, Object> map) throws Exception;
	
	/**
	 * Information
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> information(Map<String, Object> map) throws Exception;

	/**
	 * Information Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> informationInfo(Map<String, Object> map) throws Exception;
	
	/**
	 * Information 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int isInformation(Map<String, Object> map) throws Exception;
	
	/**
	 * Information 수정
	 * @param map
	 * @throws Exception
	 */
	void updateInformation(Map<String, Object> map) throws Exception;
	
	/**
	 * MAX information_id
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int maxInformationId(Map<String, Object> map) throws Exception;
	
	/**
	 * Information 추가
	 * @param map
	 * @throws Exception
	 */
	void addInformation(Map<String, Object> map) throws Exception;
	
	/**
	 * Information 삭제
	 * @param map
	 * @throws Exception
	 */
	void deleteInformation(Map<String, Object> map) throws Exception;
	
	/**
	 * 이미지 저장
	 * @param map
	 * @throws Exception
	 */
	void updateImage(Map<String, Object> map) throws Exception;
}
