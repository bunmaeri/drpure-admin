package admin.marketing.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("newsletterDAO")
public class NewsletterDAO extends AbstractDAO {
	/**
	 * 이메일 템플릿 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> emailTemplateList() throws Exception{
		return (List<Map<String, Object>>) selectList("newsletter.emailTemplateList");
	}

	/**
	 * 대상 고객 그룹
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> newsletterCustomerList() throws Exception{
		return (List<Map<String, Object>>) selectList("newsletter.newsletterCustomerList");
	}
	
	/**
	 * 대상 고객 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> sendCustomerList(Map<String,Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("newsletter.sendCustomerList", map);
	}


}
