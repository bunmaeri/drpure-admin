package admin.marketing.service;

import java.util.List;
import java.util.Map;

public interface EmailTemplateService {
	/**
	 * Total 이메일 템플릿
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int totalEmailTemplate(Map<String, Object> map) throws Exception;
	
	/**
	 * 이메일 템플릿
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> emailTemplateList(Map<String, Object> map) throws Exception;

	/**
	 * 이메일 템플릿 Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> emailTemplateInfo(Map<String, Object> map) throws Exception;
	
	/**
	 * 이메일 템플릿 수정
	 * @param map
	 * @throws Exception
	 */
	void updateEmailTemplate(Map<String, Object> map) throws Exception;
	
	/**
	 * 이메일 템플릿 추가
	 * @param map
	 * @throws Exception
	 */
	void addEmailTemplate(Map<String, Object> map) throws Exception;
	
	/**
	 * 이메일 템플릿 삭제
	 * @param map
	 * @throws Exception
	 */
	void deleteEmailTemplate(Map<String, Object> map) throws Exception;
	
	/**
	 * 이메일 템플릿 사용 후 업데이트
	 * @param map
	 * @throws Exception
	 */
	void updateEmailTemplateUse(Map<String, Object> map) throws Exception;
}
