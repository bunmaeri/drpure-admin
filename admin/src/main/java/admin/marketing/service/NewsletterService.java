package admin.marketing.service;

import java.util.List;
import java.util.Map;

public interface NewsletterService {
	/**
	 * 이메일 템플릿
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> emailTemplateList() throws Exception;

	/**
	 * 대상 고객 그룹
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> newsletterCustomerList() throws Exception;
	
	/**
	 * 대상 고객 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> sendCustomerList(Map<String,Object> map) throws Exception;
}
