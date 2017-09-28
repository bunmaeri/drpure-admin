package admin.contents.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.contents.dao.SecurityCategoriesDAO;


@Service("securityCategoriesService")
public class SecurityCategoriesServiceImpl implements SecurityCategoriesService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="securityCategoriesDAO")
	private SecurityCategoriesDAO securityCategoriesDAO;
	
	/**
	 * Total Security 카테고리
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int totalSecurityCategories(Map<String, Object> map) throws Exception {
		return securityCategoriesDAO.totalSecurityCategories(map);
	}
	
	/**
	 * Security 카테고리
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> securityCategories(Map<String, Object> map) throws Exception {
		return securityCategoriesDAO.securityCategories(map);
	}
	
	/**
	 * Security 카테고리 Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> securityCategoryInfo(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> tempMap = securityCategoriesDAO.securityCategoryInfo(map);
		resultMap.put("map", tempMap);
		
		return resultMap;
	}
	
	/**
	 * Security 카테고리 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int isSecurityCategory(Map<String, Object> map) throws Exception {
		return securityCategoriesDAO.isSecurityCategory(map);
	}
	
	/**
	 * Security 카테고리 수정
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateSecurityCategory(Map<String, Object> map) throws Exception {
		securityCategoriesDAO.updateSecurityCategory(map);
	}
	
	/**
	 * Security 카테고리 추가
	 */
	@Override
	public void addSecurityCategory(Map<String, Object> map) throws Exception {
		securityCategoriesDAO.addSecurityCategory(map);
	}
	
	/**
	 * Security 카테고리 삭제
	 */
	@Override
	public void deleteSecurityCategory(Map<String, Object> map) throws Exception {
		securityCategoriesDAO.deleteSecurityCategory(map);
	}
	
	/**
	 * 이미지 저장
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateImage(Map<String, Object> map) throws Exception {
		securityCategoriesDAO.updateImage(map);
	}
}
