package admin.contents.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.contents.dao.HealthDAO;


@Service("healthService")
public class HealthServiceImpl implements HealthService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="healthDAO")
	private HealthDAO healthDAO;
	
	/**
	 * Health
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> health(Map<String, Object> map) throws Exception {
		return healthDAO.health(map);
	}
	
	/**
	 * Health Contents 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> healthContents(Map<String, Object> map) throws Exception {
		return healthDAO.healthContents(map);
	}
	
	/**
	 * Total Health Contents
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int totalHealthContents(Map<String, Object> map) throws Exception {
		return healthDAO.totalHealthContents(map);
	}
	
	/**
	 * Health Contents Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> healthInfo(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> tempMap = healthDAO.healthInfo(map);
		resultMap.put("map", tempMap);
		
		return resultMap;
	}
	
	/**
	 * Health Contents Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> healthContentsInfo(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> tempMap = healthDAO.healthContentsInfo(map);
		resultMap.put("map", tempMap);
		
		return resultMap;
	}
	
	/**
	 * Health Contents 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int isHealthContents(Map<String, Object> map) throws Exception {
		return healthDAO.isHealthContents(map);
	}
	
	/**
	 * Health 수정
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateHealth(Map<String, Object> map) throws Exception {
		healthDAO.updateHealth(map);
	}
	
	/**
	 * Health Contents 수정
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateHealthContents(Map<String, Object> map) throws Exception {
		healthDAO.updateHealthContents(map);
	}
	
	/**
	 * MAX Health Contents Id
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int maxHealthContentsId(Map<String, Object> map) throws Exception {
		return healthDAO.maxHealthContentsId(map);
	}
	
	/**
	 * Health Contents 추가
	 */
	@Override
	public void addHealthContents(Map<String, Object> map) throws Exception {
		healthDAO.addHealthContents(map);
	}
	
	/**
	 * Health Contents 삭제
	 */
	@Override
	public void deleteHealthContents(Map<String, Object> map) throws Exception {
		healthDAO.deleteHealthContents(map);
	}
}
