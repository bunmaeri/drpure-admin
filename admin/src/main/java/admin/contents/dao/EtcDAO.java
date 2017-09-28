package admin.contents.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("etcDAO")
public class EtcDAO extends AbstractDAO {
	/**
	 * Total Contents
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int totalContents(Map<String, Object> map) throws Exception{
		return (int) selectOne("etc.totalContents", map);
	}
	
	/**
	 * Contents
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> contents(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("etc.contents", map);
	}

	/**
	 * Contents Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> contentsInfo(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("etc.contentsInfo", map);
	}
	
	/**
	 * Contents 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int isContents(Map<String, Object> map) throws Exception{
		return (int) selectOne("etc.isContents", map);
	}
	
	/**
	 * Contents 수정
	 * @param map
	 * @throws Exception
	 */
	public void updateContents(Map<String, Object> map) throws Exception{
		update("etc.updateContents", map);
	}
	
	/**
	 * MAX contents_id
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int maxContentsId(Map<String, Object> map) throws Exception{
		return (int) selectOne("etc.maxContentsId", map);
	}
	
	/**
	 * Contents 추가
	 * @param map
	 * @throws Exception
	 */
	public void addContents(Map<String, Object> map) throws Exception{
		insert("etc.addContents", map);
	}
	
	/**
	 * Contents 삭제
	 * @param map
	 * @throws Exception
	 */
	public void deleteContents(Map<String, Object> map) throws Exception{
		delete("etc.deleteContents", map);
	}
}
