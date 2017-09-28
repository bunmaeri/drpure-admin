package admin.contents.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("informationDAO")
public class InformationDAO extends AbstractDAO {
	/**
	 * Total Information
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int totalInformation(Map<String, Object> map) throws Exception{
		return (int) selectOne("contentsInformation.totalInformation", map);
	}
	
	/**
	 * Information
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> information(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("contentsInformation.information", map);
	}

	/**
	 * Information Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> informationInfo(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("contentsInformation.informationInfo", map);
	}
	
	/**
	 * Information 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int isInformation(Map<String, Object> map) throws Exception{
		return (int) selectOne("contentsInformation.isInformation", map);
	}
	
	/**
	 * Information 수정
	 * @param map
	 * @throws Exception
	 */
	public void updateInformation(Map<String, Object> map) throws Exception{
		update("contentsInformation.updateInformation", map);
	}
	
	/**
	 * MAX information_id
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int maxInformationId(Map<String, Object> map) throws Exception{
		return (int) selectOne("contentsInformation.maxInformationId", map);
	}
	
	/**
	 * Information 추가
	 * @param map
	 * @throws Exception
	 */
	public void addInformation(Map<String, Object> map) throws Exception{
		insert("contentsInformation.addInformation", map);
	}
	
	/**
	 * Information 삭제
	 * @param map
	 * @throws Exception
	 */
	public void deleteInformation(Map<String, Object> map) throws Exception{
		delete("contentsInformation.deleteInformation", map);
	}
	
	/**
	 * 이미지 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateImage(Map<String, Object> map) throws Exception{
		update("contentsInformation.updateImage", map);
	}
}
