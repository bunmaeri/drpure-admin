package admin.myhome.contents.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.myhome.contents.dao.MyInformationDAO;


@Service("myInformationService")
public class MyInformationServiceImpl implements MyInformationService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="myInformationDAO")
	private MyInformationDAO myInformationDAO;
	
	/**
	 * Total Information
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int totalInformation(Map<String, Object> map) throws Exception {
		return myInformationDAO.totalInformation(map);
	}
	
	/**
	 * Information
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> information(Map<String, Object> map) throws Exception {
		return myInformationDAO.information(map);
	}
	
	/**
	 * Information Info
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> informationInfo(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> tempMap = myInformationDAO.informationInfo(map);
		resultMap.put("map", tempMap);
		
		return resultMap;
	}
	
	/**
	 * Information 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int isInformation(Map<String, Object> map) throws Exception {
		return myInformationDAO.isInformation(map);
	}
	
	/**
	 * Information 수정
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateInformation(Map<String, Object> map) throws Exception {
		myInformationDAO.updateInformation(map);
	}
	
	/**
	 * MAX information_id
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int maxInformationId(Map<String, Object> map) throws Exception {
		return myInformationDAO.maxInformationId(map);
	}
	
	/**
	 * Information 추가
	 */
	@Override
	public void addInformation(Map<String, Object> map) throws Exception {
		myInformationDAO.addInformation(map);
	}
	
	/**
	 * Information 삭제
	 */
	@Override
	public void deleteInformation(Map<String, Object> map) throws Exception {
		myInformationDAO.deleteInformation(map);
	}
	
	/**
	 * 이미지 저장
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateImage(Map<String, Object> map) throws Exception {
		myInformationDAO.updateImage(map);
	}
}
