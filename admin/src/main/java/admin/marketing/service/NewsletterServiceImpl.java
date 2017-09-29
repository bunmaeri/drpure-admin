package admin.marketing.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.marketing.dao.NewsletterDAO;

@Service("newsletterService")
public class NewsletterServiceImpl implements NewsletterService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="newsletterDAO")
	private NewsletterDAO DAO;
	
	/**
	 * 이메일 템플릿
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> emailTemplateList() throws Exception {
		return DAO.emailTemplateList();
	}
	
	/**
	 * 대상 고객 그룹
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> newsletterCustomerList() throws Exception {
		return DAO.newsletterCustomerList();
	}
	
	/**
	 * 대상 고객 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> sendCustomerList(Map<String,Object> map) throws Exception {
		return DAO.sendCustomerList(map);
	}
}
