package admin.contents.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("noticeDAO")
public class NoticeDAO extends AbstractDAO {
	/**
	 * Total Notice
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int totalNotice(Map<String, Object> map) throws Exception{
		return (int) selectOne("contentsNotice.totalNotice", map);
	}
	
	/**
	 * Notice
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> notice(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("contentsNotice.notice", map);
	}

	/**
	 * Notice Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> noticeInfo(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("contentsNotice.noticeInfo", map);
	}
	
	/**
	 * Notice 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int isNotice(Map<String, Object> map) throws Exception{
		return (int) selectOne("contentsNotice.isNotice", map);
	}
	
	/**
	 * Notice 수정
	 * @param map
	 * @throws Exception
	 */
	public void updateNotice(Map<String, Object> map) throws Exception{
		update("contentsNotice.updateNotice", map);
	}
	
	/**
	 * MAX contents_id
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int maxContentsId(Map<String, Object> map) throws Exception{
		return (int) selectOne("contentsNotice.maxContentsId", map);
	}
	
	/**
	 * Notice 추가
	 * @param map
	 * @throws Exception
	 */
	public void addNotice(Map<String, Object> map) throws Exception{
		insert("contentsNotice.addNotice", map);
	}
	
	/**
	 * Notice 삭제
	 * @param map
	 * @throws Exception
	 */
	public void deleteNotice(Map<String, Object> map) throws Exception{
		delete("contentsNotice.deleteNotice", map);
	}
	
	/**
	 * 이미지 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateImage(Map<String, Object> map) throws Exception{
		update("contentsNotice.updateImage", map);
	}
}
