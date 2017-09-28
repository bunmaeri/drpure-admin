package admin.contents.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.contents.dao.SecurityDAO;


@Service("securityService")
public class SecurityServiceImpl implements SecurityService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="securityDAO")
	private SecurityDAO securityDAO;
	
	/**
	 * security
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> security(Map<String, Object> map) throws Exception {
		return securityDAO.security(map);
	}
	
	/**
	 * security Contents 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> securityContents(Map<String, Object> map) throws Exception {
		return securityDAO.securityContents(map);
	}
	
	/**
	 * security Contents ALL
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> securityContentsAll(Map<String, Object> map) throws Exception {
		return securityDAO.securityContentsAll(map);
	}
	
	/**
	 * Total security Contents
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int totalSecurityContents(Map<String, Object> map) throws Exception {
		return securityDAO.totalSecurityContents(map);
	}
	
	/**
	 * security Contents Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> securityInfo(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> tempMap = securityDAO.securityInfo(map);
		resultMap.put("map", tempMap);
		
		return resultMap;
	}
	
	/**
	 * security Contents Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> securityContentsInfo(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> tempMap = securityDAO.securityContentsInfo(map);
		resultMap.put("map", tempMap);
		
		return resultMap;
	}
	
	/**
	 * security Contents 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int isSecurityContents(Map<String, Object> map) throws Exception {
		return securityDAO.isSecurityContents(map);
	}
	
	/**
	 * security 수정
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateSecurity(Map<String, Object> map) throws Exception {
		securityDAO.updateSecurity(map);
	}
	
	/**
	 * security Contents 수정
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateSecurityContents(Map<String, Object> map) throws Exception {
		securityDAO.updateSecurityContents(map);
	}
	
	/**
	 * MAX security Contents Id
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int maxSecurityContentsId(Map<String, Object> map) throws Exception {
		return securityDAO.maxSecurityContentsId(map);
	}
	
	/**
	 * security Contents 추가
	 */
	@Override
	public void addSecurityContents(Map<String, Object> map) throws Exception {
		securityDAO.addSecurityContents(map);
	}
	
	/**
	 * security Contents 삭제
	 */
	@Override
	public void deleteSecurityContents(Map<String, Object> map) throws Exception {
		securityDAO.deleteSecurityContents(map);
	}
	
	/**
	 * 질병과 추천생약제 제품 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> securityContentsMedicine(Map<String, Object> map) throws Exception {
		return securityDAO.securityContentsMedicine(map);
	}
	
	/**
	 * 해당 질병과 추천생약제의 전체 제품 삭제
	 */
	@Override
	public void deleteSecurityContentsMedicine(Map<String, Object> map) throws Exception {
		securityDAO.deleteSecurityContentsMedicine(map);
	}
	
	/**
	 * 질병과 추천생약제의 제품 추가
	 */
	@Override
	public void addSecurityContentsMedicine(Map<String, Object> map) throws Exception {
		securityDAO.addSecurityContentsMedicine(map);
	}
	
}
