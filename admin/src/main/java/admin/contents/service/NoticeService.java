package admin.contents.service;

import java.util.List;
import java.util.Map;

public interface NoticeService {
	/**
	 * Total Notice
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int totalNotice(Map<String, Object> map) throws Exception;
	
	/**
	 * Notice
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> notice(Map<String, Object> map) throws Exception;

	/**
	 * Notice Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> noticeInfo(Map<String, Object> map) throws Exception;
	
	/**
	 * Notice 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int isNotice(Map<String, Object> map) throws Exception;
	
	/**
	 * Notice 수정
	 * @param map
	 * @throws Exception
	 */
	void updateNotice(Map<String, Object> map) throws Exception;
	
	/**
	 * MAX contents_id
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int maxContentsId(Map<String, Object> map) throws Exception;
	
	/**
	 * Notice 추가
	 * @param map
	 * @throws Exception
	 */
	void addNotice(Map<String, Object> map) throws Exception;
	
	/**
	 * Notice 삭제
	 * @param map
	 * @throws Exception
	 */
	void deleteNotice(Map<String, Object> map) throws Exception;
	
	/**
	 * 이미지 저장
	 * @param map
	 * @throws Exception
	 */
	void updateImage(Map<String, Object> map) throws Exception;
}
