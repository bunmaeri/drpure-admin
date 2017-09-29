package admin.system.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.system.dao.AccessDAO;

@Service("accessService")
public class AccessServiceImpl implements AccessService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="accessDAO")
	private AccessDAO DAO;
	
	/**
	 * IP 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> accessIpList() throws Exception {
		return DAO.accessIpList();
	}
	
	/**
	 * IP 추가
	 */
	@Override
	public void addAccessIp(Map<String, Object> map) throws Exception {
		DAO.addAccessIp(map);
	}
	
	/**
	 * IP 삭제
	 */
	@Override
	public void deleteAccessIp(Map<String, Object> map) throws Exception {
		DAO.deleteAccessIp(map);
	}
	
	/**
	 * 패스워드
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public String accessIpPassword() throws Exception {
		return DAO.accessIpPassword();
	}
	
	/**
	 * 패스워드 저장
	 */
	@Override
	public void updateAccessPassword(Map<String, Object> map) throws Exception {
		DAO.updateAccessPassword(map);
	}
}
