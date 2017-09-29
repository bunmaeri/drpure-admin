package admin.marketing.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("emailTemplateDAO")
public class EmailTemplateDAO extends AbstractDAO {
	/**
	 * Total 이메일 템플릿
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int totalEmailTemplate(Map<String, Object> map) throws Exception{
		return (int) selectOne("template.totalEmailTemplate", map);
	}
	
	/**
	 * 이메일 템플릿 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> emailTemplateList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("template.emailTemplateList", map);
	}

	/**
	 * 이메일 템플릿 Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> emailTemplateInfo(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("template.emailTemplateInfo", map);
	}
	
	/**
	 * 이메일 템플릿 수정
	 * @param map
	 * @throws Exception
	 */
	public void updateEmailTemplate(Map<String, Object> map) throws Exception{
		update("template.updateEmailTemplate", map);
	}

	
	/**
	 * 이메일 템플릿 추가
	 * @param map
	 * @throws Exception
	 */
	public void addEmailTemplate(Map<String, Object> map) throws Exception{
		insert("template.addEmailTemplate", map);
	}
	
	/**
	 * 이메일 템플릿 삭제
	 * @param map
	 * @throws Exception
	 */
	public void deleteEmailTemplate(Map<String, Object> map) throws Exception{
		delete("template.deleteEmailTemplate", map);
	}
	
	/**
	 * 이메일 템플릿 사용 후 업데이트
	 * @param map
	 * @throws Exception
	 */
	public void updateEmailTemplateUse(Map<String, Object> map) throws Exception{
		update("template.updateEmailTemplateUse", map);
	}
}
