package admin.contents.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("healthDAO")
public class HealthDAO extends AbstractDAO {
	/**
	 * Health
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> health(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("contentsHealth.health", map);
	}

	/**
	 * Health Contents 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> healthContents(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("contentsHealth.healthContents", map);
	}

	/**
	 * Total Health Contents
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int totalHealthContents(Map<String, Object> map) throws Exception{
		return (int) selectOne("contentsHealth.totalHealthContents", map);
	}
	
	/**
	 * Health Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> healthInfo(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("contentsHealth.healthInfo", map);
	}
	
	/**
	 * Health Contents Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> healthContentsInfo(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("contentsHealth.healthContentsInfo", map);
	}
	
	/**
	 * Health Contents 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int isHealthContents(Map<String, Object> map) throws Exception{
		return (int) selectOne("contentsHealth.isHealthContents", map);
	}
	
	/**
	 * Health 수정
	 * @param map
	 * @throws Exception
	 */
	public void updateHealth(Map<String, Object> map) throws Exception{
		update("contentsHealth.updateHealth", map);
	}
	
	/**
	 * Health Contents 수정
	 * @param map
	 * @throws Exception
	 */
	public void updateHealthContents(Map<String, Object> map) throws Exception{
		update("contentsHealth.updateHealthContents", map);
	}
	
	/**
	 * MAX Health Contents Id
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int maxHealthContentsId(Map<String, Object> map) throws Exception{
		return (int) selectOne("contentsHealth.maxHealthContentsId", map);
	}
	
	/**
	 * Health Contents 추가
	 * @param map
	 * @throws Exception
	 */
	public void addHealthContents(Map<String, Object> map) throws Exception{
		insert("contentsHealth.addHealthContents", map);
	}
	
	/**
	 * Health Contents 삭제
	 * @param map
	 * @throws Exception
	 */
	public void deleteHealthContents(Map<String, Object> map) throws Exception{
		delete("contentsHealth.deleteHealthContents", map);
	}
}
