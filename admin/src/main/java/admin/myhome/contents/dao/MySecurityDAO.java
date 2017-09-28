package admin.myhome.contents.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("mySecurityDAO")
public class MySecurityDAO extends AbstractDAO {
	/**
	 * security
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> security(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("my_security.security", map);
	}

	/**
	 * security Contents 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> securityContents(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("my_security.securityContents", map);
	}
	
	/**
	 * security Contents ALL 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> securityContentsAll(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("my_security.securityContentsAll", map);
	}

	/**
	 * Total security Contents
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int totalSecurityContents(Map<String, Object> map) throws Exception{
		return (int) selectOne("my_security.totalSecurityContents", map);
	}
	
	/**
	 * security Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> securityInfo(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("my_security.securityInfo", map);
	}
	
	/**
	 * security Contents Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> securityContentsInfo(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("my_security.securityContentsInfo", map);
	}
	
	/**
	 * security Contents 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int isSecurityContents(Map<String, Object> map) throws Exception{
		return (int) selectOne("my_security.isSecurityContents", map);
	}
	
	/**
	 * security 수정
	 * @param map
	 * @throws Exception
	 */
	public void updateSecurity(Map<String, Object> map) throws Exception{
		update("my_security.updateSecurity", map);
	}
	
	/**
	 * security Contents 수정
	 * @param map
	 * @throws Exception
	 */
	public void updateSecurityContents(Map<String, Object> map) throws Exception{
		update("my_security.updateSecurityContents", map);
	}
	
	/**
	 * MAX security Contents Id
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int maxSecurityContentsId(Map<String, Object> map) throws Exception{
		return (int) selectOne("my_security.maxSecurityContentsId", map);
	}
	
	/**
	 * security Contents 추가
	 * @param map
	 * @throws Exception
	 */
	public void addSecurityContents(Map<String, Object> map) throws Exception{
		insert("my_security.addSecurityContents", map);
	}
	
	/**
	 * security Contents 삭제
	 * @param map
	 * @throws Exception
	 */
	public void deleteSecurityContents(Map<String, Object> map) throws Exception{
		delete("my_security.deleteSecurityContents", map);
	}
	
	/**
	 * 질병과 추천생약제 제품 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> securityContentsMedicine(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("my_security.securityContentsMedicine", map);
	}
	
	/**
	 * 해당 질병과 추천생약제의 전체 제품 삭제
	 * @param map
	 * @throws Exception
	 */
	public void deleteSecurityContentsMedicine(Map<String, Object> map) throws Exception{
		delete("my_security.deleteSecurityContentsMedicine", map);
	}
	
	/**
	 * 질병과 추천생약제의 제품 추가
	 * @param map
	 * @throws Exception
	 */
	public void addSecurityContentsMedicine(Map<String, Object> map) throws Exception{
		insert("my_security.addSecurityContentsMedicine", map);
	}
}
