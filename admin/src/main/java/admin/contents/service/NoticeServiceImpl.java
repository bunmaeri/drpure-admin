package admin.contents.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.contents.dao.NoticeDAO;


@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="noticeDAO")
	private NoticeDAO noticeDAO;
	
	/**
	 * Total Notice
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int totalNotice(Map<String, Object> map) throws Exception {
		return noticeDAO.totalNotice(map);
	}
	
	/**
	 * Notice
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> notice(Map<String, Object> map) throws Exception {
		return noticeDAO.notice(map);
	}
	
	/**
	 * Notice Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> noticeInfo(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> tempMap = noticeDAO.noticeInfo(map);
		resultMap.put("map", tempMap);
		
		return resultMap;
	}
	
	/**
	 * Notice 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int isNotice(Map<String, Object> map) throws Exception {
		return noticeDAO.isNotice(map);
	}
	
	/**
	 * Notice 수정
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateNotice(Map<String, Object> map) throws Exception {
		noticeDAO.updateNotice(map);
	}
	
	/**
	 * MAX contents_id
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int maxContentsId(Map<String, Object> map) throws Exception {
		return noticeDAO.maxContentsId(map);
	}
	
	/**
	 * Notice 추가
	 */
	@Override
	public void addNotice(Map<String, Object> map) throws Exception {
		noticeDAO.addNotice(map);
	}
	
	/**
	 * Notice 삭제
	 */
	@Override
	public void deleteNotice(Map<String, Object> map) throws Exception {
		noticeDAO.deleteNotice(map);
	}
}
