package admin.myhome.contents.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.myhome.contents.dao.MyEtcDAO;


@Service("myEtcService")
public class MyEtcServiceImpl implements MyEtcService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="myEtcDAO")
	private MyEtcDAO etcDAO;
	
	/**
	 * Total Contents
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int totalContents(Map<String, Object> map) throws Exception {
		return etcDAO.totalContents(map);
	}
	
	/**
	 * Contents
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> contents(Map<String, Object> map) throws Exception {
		return etcDAO.contents(map);
	}
	
	/**
	 * Contents Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> contentsInfo(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> tempMap = etcDAO.contentsInfo(map);
		resultMap.put("map", tempMap);
		
		return resultMap;
	}
	
	/**
	 * Contents 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int isContents(Map<String, Object> map) throws Exception {
		return etcDAO.isContents(map);
	}
	
	/**
	 * Contents 수정
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateContents(Map<String, Object> map) throws Exception {
		etcDAO.updateContents(map);
	}
	
	/**
	 * MAX contents_id
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int maxContentsId(Map<String, Object> map) throws Exception {
		return etcDAO.maxContentsId(map);
	}
	
	/**
	 * Contents 추가
	 */
	@Override
	public void addContents(Map<String, Object> map) throws Exception {
		etcDAO.addContents(map);
	}
	
	/**
	 * Contents 삭제
	 */
	@Override
	public void deleteContents(Map<String, Object> map) throws Exception {
		etcDAO.deleteContents(map);
	}
}
