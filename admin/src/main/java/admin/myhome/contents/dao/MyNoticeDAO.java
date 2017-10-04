package admin.myhome.contents.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("myNoticeDAO")
public class MyNoticeDAO extends AbstractDAO {
	/**
	 * Total Notice
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int totalNotice(Map<String, Object> map) throws Exception{
		return (int) selectOne("my_contentsNotice.totalNotice", map);
	}
	
	/**
	 * Notice
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> notice(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("my_contentsNotice.notice", map);
	}

	/**
	 * Notice Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> noticeInfo(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("my_contentsNotice.noticeInfo", map);
	}
	
	/**
	 * Notice 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int isNotice(Map<String, Object> map) throws Exception{
		return (int) selectOne("my_contentsNotice.isNotice", map);
	}
	
	/**
	 * Notice 수정
	 * @param map
	 * @throws Exception
	 */
	public void updateNotice(Map<String, Object> map) throws Exception{
		update("my_contentsNotice.updateNotice", map);
	}
	
	/**
	 * MAX contents_id
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int maxContentsId(Map<String, Object> map) throws Exception{
		return (int) selectOne("my_contentsNotice.maxContentsId", map);
	}
	
	/**
	 * Notice 추가
	 * @param map
	 * @throws Exception
	 */
	public void addNotice(Map<String, Object> map) throws Exception{
		insert("my_contentsNotice.addNotice", map);
	}
	
	/**
	 * Notice 삭제
	 * @param map
	 * @throws Exception
	 */
	public void deleteNotice(Map<String, Object> map) throws Exception{
		delete("my_contentsNotice.deleteNotice", map);
	}
	
	/**
	 * 이미지 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateImage(Map<String, Object> map) throws Exception{
		update("my_contentsNotice.updateImage", map);
	}
}
