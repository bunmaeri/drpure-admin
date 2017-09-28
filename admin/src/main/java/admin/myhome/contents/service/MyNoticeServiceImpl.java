package admin.myhome.contents.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.myhome.contents.dao.MyNoticeDAO;


@Service("myNoticeService")
public class MyNoticeServiceImpl implements MyNoticeService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="myNoticeDAO")
	private MyNoticeDAO myNoticeDAO;
	
	/**
	 * Total Notice
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int totalNotice(Map<String, Object> map) throws Exception {
		return myNoticeDAO.totalNotice(map);
	}
	
	/**
	 * Notice
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> notice(Map<String, Object> map) throws Exception {
		return myNoticeDAO.notice(map);
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
		Map<String, Object> tempMap = myNoticeDAO.noticeInfo(map);
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
		return myNoticeDAO.isNotice(map);
	}
	
	/**
	 * Notice 수정
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateNotice(Map<String, Object> map) throws Exception {
		myNoticeDAO.updateNotice(map);
	}
	
	/**
	 * MAX contents_id
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int maxContentsId(Map<String, Object> map) throws Exception {
		return myNoticeDAO.maxContentsId(map);
	}
	
	/**
	 * Notice 추가
	 */
	@Override
	public void addNotice(Map<String, Object> map) throws Exception {
		myNoticeDAO.addNotice(map);
	}
	
	/**
	 * Notice 삭제
	 */
	@Override
	public void deleteNotice(Map<String, Object> map) throws Exception {
		myNoticeDAO.deleteNotice(map);
	}
}
