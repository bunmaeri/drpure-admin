package admin.marketing.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.marketing.dao.EmailTemplateDAO;

@Service("emailTemplateService")
public class EmailTemplateServiceImpl implements EmailTemplateService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="emailTemplateDAO")
	private EmailTemplateDAO emailTemplateDAO;
	
	/**
	 * Total 이메일 템플릿
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int totalEmailTemplate(Map<String, Object> map) throws Exception {
		return emailTemplateDAO.totalEmailTemplate(map);
	}
	
	/**
	 * 이메일 템플릿
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> emailTemplateList(Map<String, Object> map) throws Exception {
		return emailTemplateDAO.emailTemplateList(map);
	}
	
	/**
	 * 이메일 템플릿 Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> emailTemplateInfo(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> tempMap = emailTemplateDAO.emailTemplateInfo(map);
		resultMap.put("map", tempMap);
		
		return resultMap;
	}
	
	/**
	 * 이메일 템플릿 수정
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateEmailTemplate(Map<String, Object> map) throws Exception {
		emailTemplateDAO.updateEmailTemplate(map);
	}
	
	/**
	 * 이메일 템플릿 추가
	 */
	@Override
	public void addEmailTemplate(Map<String, Object> map) throws Exception {
		emailTemplateDAO.addEmailTemplate(map);
	}
	
	/**
	 * 이메일 템플릿 삭제
	 */
	@Override
	public void deleteEmailTemplate(Map<String, Object> map) throws Exception {
		emailTemplateDAO.deleteEmailTemplate(map);
	}
	
	/**
	 * 이메일 템플릿 사용 후 업데이트
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateEmailTemplateUse(Map<String, Object> map) throws Exception {
		emailTemplateDAO.updateEmailTemplateUse(map);
	}
}
